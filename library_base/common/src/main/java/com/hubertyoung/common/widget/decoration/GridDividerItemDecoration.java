package com.hubertyoung.common.widget.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2019-08-28 11:10
 * @since:V
 * @desc:section
 */
public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    public static final String TAG = "GridMoreTypeDecoration";
    private static final int DEFAULT_SIZE = 2;
    private static final int[] ATTRS = new int[]{ android.R.attr.listDivider };

    //        private int mFirstRowTopMargin = 0; //第一行顶部是否需要间隔
    private boolean isNeedSpace = false;//第一列和最后一列是否需要指定间隔(默认不指定)
//    private boolean isLastRowNeedSpace = false;//最后一行是否需要间隔(默认不需要)
    private boolean isFirstRowNeedSpace = false;//最后一行是否需要间隔(默认不需要)
    int spanCount = 0;
    private Context mContext;
//    private Path mPath = null;

    protected enum DividerType {
        DRAWABLE, PAINT, COLOR
    }

    protected DividerType mDividerType = DividerType.DRAWABLE;
    protected VisibilityProvider mVisibilityProvider;
    protected PaintProvider mPaintProvider;
    protected ColorProvider mColorProvider;
    protected DrawableProvider mDrawableProvider;
    protected SizeProvider mSizeProvider;
    private Paint mPaint;

    /**
     * 意思是存储每一个个HeadView 的之前所有Item包括自己的数量
     */
    LinkedHashMap< Integer, Integer > headPositionTotalCountMap = new LinkedHashMap<>();
    /**
     * 每一个子Item(非HeadView),存储自己对应的headView的Item数量，
     * 主要用于取余计算时，位置换算
     */
    LinkedHashMap< Integer, Integer > subItemPositionCountMap = new LinkedHashMap<>();

    protected GridDividerItemDecoration( Builder builder ) {
        if ( builder.mPaintProvider != null ) {
            mDividerType = DividerType.PAINT;
            mPaintProvider = builder.mPaintProvider;
        } else if ( builder.mColorProvider != null ) {
            mDividerType = DividerType.COLOR;
            mColorProvider = builder.mColorProvider;
            mPaint = new Paint();
            setSizeProvider( builder );
        } else {
            mDividerType = DividerType.DRAWABLE;
            if ( builder.mDrawableProvider == null ) {
                TypedArray a = builder.mContext.obtainStyledAttributes( ATTRS );
                final Drawable divider = a.getDrawable( 0 );
                a.recycle();
                mDrawableProvider = new DrawableProvider() {
                    @Override
                    public Drawable drawableProvider() {
                        return divider;
                    }
                };
            } else {
                mDrawableProvider = builder.mDrawableProvider;
            }
            mSizeProvider = builder.mSizeProvider;
        }
        this.isNeedSpace = builder.isNeedSpace;
        this.mContext = builder.mContext;
//        this.isLastRowNeedSpace = builder.mShowLastDivider;
        this.isFirstRowNeedSpace = builder.mShowFirstDivider;

        mVisibilityProvider = builder.mVisibilityProvider;


//        mPath = new Path();
    }

    private void setSizeProvider( Builder builder ) {
        mSizeProvider = builder.mSizeProvider;
        if ( mSizeProvider == null ) {
            mSizeProvider = new SizeProvider() {
                @Override
                public int dividerSize() {
                    return DEFAULT_SIZE;
                }
            };
        }
    }

    @Override
    public void getItemOffsets( Rect outRect, View view, RecyclerView parent, RecyclerView.State state ) {
        super.getItemOffsets( outRect, view, parent, state );

        int top = 0;
        int left = 0;
        int right = 0;
        int bottom = 0;

        int itemPosition = ( ( RecyclerView.LayoutParams ) view.getLayoutParams() ).getViewLayoutPosition();
        spanCount = getSpanCount( parent );
        int spanSize = getSpanSize( itemPosition, parent );
        Log.e( TAG, "itemPosition ==" + itemPosition );
        Log.e( TAG, "spanCount ==" + spanCount );
        Log.e( TAG, "spanSize ==" + spanSize );

        if ( spanSize == spanCount ) {//这是有类似HeaderView 的情况
//            left = 0;
//            right = 0;
            if ( isFirstRowNeedSpace ) {
                bottom = getDividerSize();
            }
////            top = itemPosition == 0 ? getDividerSize() : 2 * getDividerSize();//为了画分隔线，第一行HeaderView不需要画分隔线
//            top = 0;

            if ( !headPositionTotalCountMap.containsKey( itemPosition ) ) {
                headPositionTotalCountMap.put( itemPosition, itemPosition + 1 );
            }
        } else {//类似HeaderView 下的子item
            if ( !subItemPositionCountMap.containsKey( itemPosition ) ) {
                int headViewTotalCount = headPositionTotalCountMap.size() == 0 ? 0 : getMapTail( headPositionTotalCountMap ).getValue();
                subItemPositionCountMap.put( itemPosition, headViewTotalCount );
            }
            int maxAllDividerWidth = getMaxDividerWidth( view ); //获得最大剩余宽度

            int spaceWidth = 0;//首尾两列与父布局之间的间隔
            if ( isNeedSpace )
                spaceWidth = getDividerSize();

            int eachItemWidth = maxAllDividerWidth / spanCount;//每个Item left+right
            int dividerItemWidth = ( maxAllDividerWidth - 2 * spaceWidth ) / ( spanCount - 1 );//item与item之间的距离

            Log.w( TAG, "subCountMap.get(itemPosition)) ==" + subItemPositionCountMap.get( itemPosition ) );
            Log.w( TAG, "(itemPosition-subCountMap.get(itemPosition)) % spanCount ===" + ( itemPosition - subItemPositionCountMap.get( itemPosition ) ) % spanCount );
            left = ( itemPosition - subItemPositionCountMap.get( itemPosition ) ) % spanCount * ( dividerItemWidth - eachItemWidth ) + spaceWidth;
            right = eachItemWidth - left;
            bottom = getDividerSize();
            top = 0;
        }

        outRect.set( left, top, right, bottom );
    }

    private int getDividerSize() {
        if ( mPaintProvider != null ) {
            return ( int ) mPaintProvider.dividerPaint().getStrokeWidth();
        } else if ( mSizeProvider != null ) {
            return mSizeProvider.dividerSize();
        } else if ( mDrawableProvider != null ) {
            Drawable drawable = mDrawableProvider.drawableProvider();
            return drawable.getIntrinsicWidth();
        }
        throw new RuntimeException( "failed to get size" );
    }

    /**
     * 获取最近一个Entry
     *
     * @param headCountMap
     * @return
     */
    private Map.Entry< Integer, Integer > getMapTail( LinkedHashMap< Integer, Integer > headCountMap ) {
        Iterator< Map.Entry< Integer, Integer > > iterator = headCountMap.entrySet().iterator();
        Map.Entry tail = null;
        while ( iterator.hasNext() ) {
            tail = iterator.next();
        }
        return tail;
    }

    /**
     * 获取Item View的大小，若无则自动分配空间
     * 并根据 屏幕宽度-View的宽度*spanCount 得到屏幕剩余空间
     *
     * @param view
     * @return
     */
    private int getMaxDividerWidth( View view ) {
        int itemWidth = view.getLayoutParams().width;
        int itemHeight = view.getLayoutParams().height;

        int screenWidth = getScreenWidth();

        int maxDividerWidth = screenWidth - itemWidth * spanCount;
        if ( itemHeight < 0 || itemWidth < 0 || ( isNeedSpace && maxDividerWidth <= ( spanCount - 1 ) * getDividerSize() ) ) {
            view.getLayoutParams().width = getAttachCloumnWidth();
//            view.getLayoutParams().height = getAttachCloumnWidth();

            maxDividerWidth = screenWidth - view.getLayoutParams().width * spanCount;
        }
        return maxDividerWidth;
    }

    /**
     * 根据屏幕宽度和item数量分配 item View的width和height
     *
     * @return
     */
    private int getAttachCloumnWidth() {
        int itemWidth = 0;
        int spaceWidth = 0;
        try {
            int width = getScreenWidth();
            if ( isNeedSpace )
                spaceWidth = 2 * getDividerSize();
            itemWidth = ( int ) (( width - spaceWidth ) / spanCount - (0.66 * getDividerSize()));
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return itemWidth;
    }

    @Override
    public void onDraw( Canvas c, RecyclerView parent, RecyclerView.State state ) {
        super.onDraw( c, parent, state );
        draw( c, parent );
    }

    //绘制不同HeadView之间虚线分割线
//    private void draw( Canvas canvas, RecyclerView parent ) {
//        int width = getScreenWidth();
//        int spanCount = getSpanCount( parent );
//
//        int childCount = parent.getChildCount();
//        for ( int i = 0; i < childCount; i++ ) {
//            View child = parent.getChildAt( i );
//            int itemPosition = ( ( RecyclerView.LayoutParams ) child.getLayoutParams() ).getViewLayoutPosition();
//            int spanSize = getSpanSize( itemPosition, parent );
//
////            if ( spanCount == spanSize && itemPosition != 0 ) {
////                mPath.reset();
////                mPath.moveTo( child.getLeft() - 5, child.getTop() - getDividerSize() );
////                mPath.lineTo( width - getDividerSize() + 5, child.getTop() - getDividerSize() );
////                canvas.drawPath( mPath, mPaint );
////            }
////            switch ( mDividerType ) {
////                case DRAWABLE:
////                    Drawable drawable = mDrawableProvider.drawableProvider();
////                    drawable.setBounds( bounds );
////                    drawable.draw( canvas );
////                    break;
////                case PAINT:
////                    mPaint = mPaintProvider.dividerPaint();
////                    canvas.drawLine( bounds.left, bounds.top, bounds.right, bounds.bottom, mPaint );
////                    break;
////                case COLOR:
////                    mPaint.setColor( mColorProvider.dividerColor() );
////                    mPaint.setStrokeWidth( mSizeProvider.dividerSize( groupIndex, parent ) );
////                    canvas.drawLine( bounds.left, bounds.top, bounds.right, bounds.bottom, mPaint );
////                    break;
////            }
//        }
//    }

    private int getScreenWidth() {
        return mContext.getResources().getDisplayMetrics().widthPixels > mContext.getResources().getDisplayMetrics().heightPixels
                ? mContext.getResources().getDisplayMetrics().heightPixels : mContext.getResources().getDisplayMetrics().widthPixels;
    }

    //绘制item分割线
    private void draw( Canvas canvas, RecyclerView parent ) {
        RecyclerView.Adapter adapter = parent.getAdapter();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if ( adapter == null ) {
            return;
        }
        int childCount = parent.getChildCount();
        for ( int i = 0; i < childCount; i++ ) {
            View child = parent.getChildAt( i );
            int childPosition = parent.getChildAdapterPosition( child );

            RecyclerView.LayoutParams layoutParams = ( RecyclerView.LayoutParams ) child.getLayoutParams();
            Rect boundsW = new Rect( 0, 0, 0, 0 );
            Rect boundsWTop = null;
            Rect boundsH = new Rect( 0, 0, 0, 0 );
            Rect boundsHTop = null;
            if ( isNeedSpace ) {
                int relativePosition = -1;
                if ( adapter instanceof SectionedRecyclerViewAdapter ) {
                    SectionedRecyclerViewAdapter sectionAdapter = ( SectionedRecyclerViewAdapter ) adapter;
                    int itemViewType = sectionAdapter.getItemViewType( i );
                    int positionInSection = sectionAdapter.getPositionInSection( childPosition );
                    if ( layoutManager instanceof GridLayoutManager ) {
                        int spanCount = ( ( GridLayoutManager ) layoutManager ).getSpanCount();
                        relativePosition = positionInSection % spanCount;
                    }
                }
//                if ( i == 0 ){
//                    boundsWTop = new Rect( 0, 0, 0, 0 );
//                    boundsHTop = new Rect( 0, 0, 0, 0 );
//                    //画水平分隔线
//                    boundsWTop.left = child.getLeft();
//                    boundsWTop.right = child.getRight() + getDividerSize();
//                    boundsWTop.top = child.getTop() - getDividerSize();
//                    boundsWTop.bottom = child.getTop();
//
//                    //画垂直分割线
//                    boundsHTop.top = child.getTop() - getDividerSize();
//                    boundsHTop.bottom = child.getBottom() + getDividerSize();
//                    boundsHTop.left = child.getLeft() - getDividerSize();
//                    boundsHTop.right = child.getLeft();
//                }
                if ( relativePosition == 0 ) {
                    boundsHTop = new Rect( 0, 0, 0, 0 );
                    //画垂直分割线
                    boundsHTop.top = child.getTop();
                    boundsHTop.bottom = child.getBottom() + getDividerSize();
                    boundsHTop.left = child.getLeft() - getDividerSize();
                    boundsHTop.right = child.getLeft();
                }
                //画水平分隔线
                boundsW.left = child.getLeft();
                boundsW.right = child.getRight();
                boundsW.top = child.getBottom() + layoutParams.bottomMargin;
                boundsW.bottom = boundsW.top + getDividerSize();
                //画垂直分割线
                boundsH.top = child.getTop();
                boundsH.bottom = child.getBottom() + getDividerSize();
                boundsH.left = child.getRight() + layoutParams.rightMargin;
                boundsH.right = boundsH.left + getDividerSize();

                switch ( mDividerType ) {
                    case DRAWABLE:
                        Drawable drawable = mDrawableProvider.drawableProvider();
                        drawable.setBounds( boundsW );
                        drawable.draw( canvas );
                        drawable.setBounds( boundsH );
                        drawable.draw( canvas );
                        if ( boundsWTop != null ) {
                            drawable.setBounds( boundsWTop );
                            drawable.draw( canvas );
                        }
                        if ( boundsHTop != null ) {
                            drawable.setBounds( boundsHTop );
                            drawable.draw( canvas );
                        }
                        break;
                    case PAINT:
                        mPaint = mPaintProvider.dividerPaint();
                        canvas.drawRect( boundsW.left, boundsW.top, boundsW.right, boundsW.bottom, mPaint );
                        if ( boundsWTop != null ) {
                            canvas.drawRect( boundsWTop.left, boundsWTop.top, boundsWTop.right, boundsWTop.bottom, mPaint );
                        }
                        canvas.drawRect( boundsH.left, boundsH.top, boundsH.right, boundsH.bottom, mPaint );
                        if ( boundsHTop != null ) {
                            canvas.drawRect( boundsHTop.left, boundsHTop.top, boundsHTop.right, boundsHTop.bottom, mPaint );
                        }
                        break;
                    case COLOR:
                        mPaint.setColor( mColorProvider.dividerColor() );
                        mPaint.setStrokeWidth( mSizeProvider.dividerSize() );
                        canvas.drawRect( boundsW.left, boundsW.top, boundsW.right, boundsW.bottom, mPaint );
                        if ( boundsWTop != null ) {
                            canvas.drawRect( boundsWTop.left, boundsWTop.top, boundsWTop.right, boundsWTop.bottom, mPaint );
                        }
                        canvas.drawRect( boundsH.left, boundsH.top, boundsH.right, boundsH.bottom, mPaint );
                        if ( boundsHTop != null ) {
                            canvas.drawRect( boundsHTop.left, boundsHTop.top, boundsHTop.right, boundsHTop.bottom, mPaint );
                        }
                        break;
                }
            } else {
                spanCount = getSpanCount( parent );
                int spanSize = getSpanSize( childPosition, parent );
                Log.e( TAG, "itemPosition ==" + childPosition );
                Log.e( TAG, "spanCount ==" + spanCount );
                Log.e( TAG, "spanSize ==" + spanSize );

                if ( spanSize == spanCount ) {//这是有类似HeaderView 的情况
                    if ( isFirstRowNeedSpace ){
                        //画水平分隔线
                        boundsW.left = child.getLeft();
                        boundsW.right = child.getRight();
                        boundsW.top = child.getBottom() + layoutParams.bottomMargin;
                        boundsW.bottom = boundsW.top + getDividerSize();
                    }
                }else {
                    //画水平分隔线
                    boundsW.left = child.getLeft();
                    boundsW.right = child.getRight();
                    boundsW.top = child.getBottom() + layoutParams.bottomMargin;
                    boundsW.bottom = boundsW.top + getDividerSize();
                }
                //画垂直分割线
                boundsH.top = child.getTop();
                boundsH.bottom = child.getBottom() + getDividerSize();
                boundsH.left = child.getRight() + layoutParams.rightMargin;
                boundsH.right = boundsH.left + getDividerSize();

                switch ( mDividerType ) {
                    case DRAWABLE:
                        Drawable drawable = mDrawableProvider.drawableProvider();
                        drawable.setBounds( boundsW );
                        drawable.draw( canvas );
                        Drawable drawable2 = mDrawableProvider.drawableProvider();
                        drawable2.setBounds( boundsH );
                        drawable2.draw( canvas );
                        break;
                    case PAINT:
                        mPaint = mPaintProvider.dividerPaint();
                        canvas.drawRect( boundsW.left, boundsW.top, boundsW.right, boundsW.bottom, mPaint );
                        canvas.drawRect( boundsH.left, boundsH.top, boundsH.right, boundsH.bottom, mPaint );
                        break;
                    case COLOR:
                        mPaint.setColor( mColorProvider.dividerColor() );
                        mPaint.setStrokeWidth( mSizeProvider.dividerSize() );
                        canvas.drawRect( boundsW.left, boundsW.top, boundsW.right, boundsW.bottom, mPaint );
                        canvas.drawRect( boundsH.left, boundsH.top, boundsH.right, boundsH.bottom, mPaint );
                        break;
                }
            }

        }
    }

    /**
     * 判读是否是第一列
     *
     * @param parent
     * @param pos
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isFirstColumn( RecyclerView parent, int pos, int spanCount, int childCount ) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if ( layoutManager instanceof GridLayoutManager ) {
            if ( pos % spanCount == 0 ) {
                return true;
            }
        } else if ( layoutManager instanceof StaggeredGridLayoutManager ) {
            int orientation = ( ( StaggeredGridLayoutManager ) layoutManager )
                    .getOrientation();
            if ( orientation == StaggeredGridLayoutManager.VERTICAL ) {
                if ( pos % spanCount == 0 ) {// 第一列
                    return true;
                }
            } else {

            }
        }
        return false;
    }

    /**
     * 判断是否是最后一列
     *
     * @param parent
     * @param pos
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isLastColumn( RecyclerView parent, int pos, int spanCount, int childCount ) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if ( layoutManager instanceof GridLayoutManager ) {
            if ( ( pos + 1 ) % spanCount == 0 ) {// 如果是最后一列，则不需要绘制右边
                return true;
            }
        } else if ( layoutManager instanceof StaggeredGridLayoutManager ) {
            int orientation = ( ( StaggeredGridLayoutManager ) layoutManager )
                    .getOrientation();
            if ( orientation == StaggeredGridLayoutManager.VERTICAL ) {
                if ( ( pos + 1 ) % spanCount == 0 ) {// 最后一列
                    return true;
                }
            } else {

            }
        }
        return false;
    }

    /**
     * 判读是否是最后一行
     *
     * @param parent
     * @param pos
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isLastRow( RecyclerView parent, int pos, int spanCount, int childCount ) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if ( layoutManager instanceof GridLayoutManager ) {
            int lines = childCount % spanCount == 0 ? childCount / spanCount : childCount / spanCount + 1;
            return lines == pos / spanCount + 1;
        } else if ( layoutManager instanceof StaggeredGridLayoutManager ) {

        }
        return false;
    }

    /**
     * 判断是否是第一行
     *
     * @param parent
     * @param pos
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isFirstRow( RecyclerView parent, int pos, int spanCount, int childCount ) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if ( layoutManager instanceof GridLayoutManager ) {
            if ( ( pos / spanCount + 1 ) == 1 ) {
                return true;
            } else {
                return false;
            }
        } else if ( layoutManager instanceof StaggeredGridLayoutManager ) {

        }
        return false;
    }

    /**
     * 获取列数
     *
     * @param parent
     * @return
     */
    private int getSpanCount( RecyclerView parent ) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if ( layoutManager instanceof GridLayoutManager ) {
            spanCount = ( ( GridLayoutManager ) layoutManager ).getSpanCount();
        } else if ( layoutManager instanceof StaggeredGridLayoutManager ) {
            spanCount = ( ( StaggeredGridLayoutManager ) layoutManager ).getSpanCount();
        }
        return spanCount;
    }

    /**
     * 返回recyclview 设置了setSpanSizeLookup，判断几个单元格合并为一个
     *
     * @param position
     * @return
     */
    private int getSpanSize( int position, RecyclerView parent ) {
        int spanSize = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if ( layoutManager instanceof GridLayoutManager ) {
            spanSize = ( ( GridLayoutManager ) layoutManager ).getSpanSizeLookup().getSpanSize( position );
        } else if ( layoutManager instanceof StaggeredGridLayoutManager ) {
        }
        return spanSize;
    }

    /**
     * Interface for controlling paint instance for divider drawing
     */
    public interface PaintProvider {

        /**
         * Returns {@link Paint} for divider
         *
         * @return Paint instance
         */
        Paint dividerPaint();
    }

    /**
     * Interface for controlling divider color
     */
    public interface ColorProvider {

        /**
         * Returns {@link android.graphics.Color} value of divider
         *
         * @return Color value
         */
        int dividerColor();
    }

    /**
     * Interface for controlling drawable object for divider drawing
     */
    public interface DrawableProvider {

        /**
         * Returns drawable instance for divider
         *
         * @return Drawable instance
         */
        Drawable drawableProvider();
    }

    /**
     * Interface for controlling divider size
     */
    public interface SizeProvider {

        /**
         * Returns size value of divider.
         * Height for horizontal divider, width for vertical divider
         *
         * @return Size of divider
         */
        int dividerSize();
    }

    /**
     * Interface for controlling divider visibility
     */
    public interface VisibilityProvider {

        /**
         * Returns true if divider should be hidden.
         *
         * @param position Divider position (or group index for GridLayoutManager)
         * @param parent   RecyclerView
         * @return True if the divider at position should be hidden
         */
        boolean shouldHideDivider( int position, RecyclerView parent );
    }

    public static class Builder {

        private Context mContext;
        protected Resources mResources;
        private PaintProvider mPaintProvider;
        private ColorProvider mColorProvider;
        private DrawableProvider mDrawableProvider;
        private SizeProvider mSizeProvider;
        private VisibilityProvider mVisibilityProvider = new VisibilityProvider() {
            @Override
            public boolean shouldHideDivider( int position, RecyclerView parent ) {
                return false;
            }
        };
        private boolean isNeedSpace = false;
//        private boolean mShowLastDivider = false;
        private boolean mShowFirstDivider = false;

        public Builder( Context context ) {
            mContext = context;
            mResources = context.getResources();
        }

        public Builder paint( final Paint paint ) {
            return paintProvider( new PaintProvider() {
                @Override
                public Paint dividerPaint() {
                    return paint;
                }
            } );
        }

        public Builder paintProvider( PaintProvider provider ) {
            mPaintProvider = provider;
            return ( Builder ) this;
        }

        public Builder color( final int color ) {
            return colorProvider( new ColorProvider() {
                @Override
                public int dividerColor() {
                    return color;
                }
            } );
        }

        public Builder colorResId( @ColorRes int colorId ) {
            return color( ContextCompat.getColor( mContext, colorId ) );
        }

        public Builder colorProvider( ColorProvider provider ) {
            mColorProvider = provider;
            return ( Builder ) this;
        }

        public Builder drawable( @DrawableRes int id ) {
            return drawable( ContextCompat.getDrawable( mContext, id ) );
        }

        public Builder drawable( final Drawable drawable ) {
            return drawableProvider( new DrawableProvider() {
                @Override
                public Drawable drawableProvider() {
                    return drawable;
                }
            } );
        }

        public Builder drawableProvider( DrawableProvider provider ) {
            mDrawableProvider = provider;
            return ( Builder ) this;
        }

        public Builder size( final int size ) {
            return sizeProvider( new SizeProvider() {
                @Override
                public int dividerSize() {
                    return size;
                }
            } );
        }

        public Builder sizeResId( @DimenRes int sizeId ) {
            return size( mResources.getDimensionPixelSize( sizeId ) );
        }

        public Builder sizeProvider( SizeProvider provider ) {
            mSizeProvider = provider;
            return ( Builder ) this;
        }

//        public Builder visibilityProvider( VisibilityProvider provider ) {
//            mVisibilityProvider = provider;
//            return ( Builder ) this;
//        }

        public Builder showNeedSpace() {
            isNeedSpace = true;
            showFirstDivider();
//            showLastDivider();
            return ( Builder ) this;
        }
        public Builder showFirstDivider() {
            mShowFirstDivider = true;
            return this;
        }
//        public Builder showLastDivider() {
//            mShowLastDivider = true;
//            return this;
//        }
//
//        public Builder positionInsideItem( boolean positionInsideItem ) {
//            mPositionInsideItem = positionInsideItem;
//            return ( Builder ) this;
//        }

        protected void checkBuilderParams() {
            if ( mPaintProvider != null ) {
                if ( mColorProvider != null ) {
                    throw new IllegalArgumentException( "Use setColor method of Paint class to specify line color. Do not provider ColorProvider if you set PaintProvider." );
                }
                if ( mSizeProvider != null ) {
                    throw new IllegalArgumentException( "Use setStrokeWidth method of Paint class to specify line size. Do not provider SizeProvider if you set PaintProvider." );
                }
            }
        }

        public GridDividerItemDecoration build() {
            if ( mShowFirstDivider ){
                isNeedSpace = true;
            }
            checkBuilderParams();
            return new GridDividerItemDecoration( this );
        }
    }
}
