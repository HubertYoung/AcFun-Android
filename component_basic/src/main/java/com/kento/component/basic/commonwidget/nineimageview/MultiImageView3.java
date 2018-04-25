package com.kento.component.basic.commonwidget.nineimageview;//package ddframework.gent.common.commonwidget.NineImageView;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.drawable.Drawable;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.text.TextUtils;
//import android.util.AttributeSet;
//import android.util.DisplayMetrics;
//import android.util.TypedValue;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.bumptech.glide.load.DataSource;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.request.target.Target;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import ddframework.gent.common.R;
//import ddframework.gent.common.commonwidget.glideimageview.GlideImageLoader;
//
//
///**
// * @author:Yang
// * @date:2017/7/18 13:13
// * @since:v1.0
// * @desc:ddframework.gent.common.commonwidget
// * @param:显示1~N张图片的View
// */
//public class MultiImageView extends LinearLayout {
//
//    public static final int MODE_GRID = 0;          //网格模式，4张图2X2布局
//    public static final int MODE_FILL = 1;          //填充模式，类似于微信
//
////    private int singleImageWidth;
////    private int singleImageHeight;
////    private int singleImageMaxWidth;
////    private int singleImageMinWidth;
//
//    private int maxImageSize = 9;                   // 最大显示的图片数
//    private int gridSpacing = 5;                    // 宫格间距，单位dp
//    private int mode = MODE_GRID;                   // 默认使用GRID模式
//
//    private int columnCount;    // 列数
//    private int rowCount;       // 行数
//    private int gridWidth;      // 宫格宽度
//    private int gridHeight;     // 宫格高度
//
//    private List< ImageView > imageViews;
//    //裁剪前的总数
//    private List< ImageAttr > imageAttrs;
//    //裁剪后显示的
//    private List< ImageAttr > attrList;
//
//    public MultiImageView( Context context ) {
//        this( context, null );
//    }
//
//    public MultiImageView( Context context, AttributeSet attrs ) {
//        this( context, attrs, 0 );
//    }
//
//    public MultiImageView( Context context, AttributeSet attrs, int defStyleAttr ) {
//        super( context, attrs, defStyleAttr );
//        init( context, attrs );
//    }
//
//    private void init( Context context, AttributeSet attrs ) {
////        int WIDTH = DisplayUtil.getScreenWidth( context ) - DisplayUtil.dip2px( 15 * 2 );
////        singleImageMaxWidth = WIDTH * 2 / 3;
////        singleImageMinWidth = WIDTH / 3;
////        singleImageMaxWidth = WIDTH ;
////        singleImageMinWidth = WIDTH ;
////        singleImageWidth = singleImageMinWidth;
////        singleImageHeight = singleImageMinWidth;
//
//        imageViews = new ArrayList<>();
//
//        DisplayMetrics displayMetrics = context.getResources()
//                                               .getDisplayMetrics();
//        gridSpacing = ( int ) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, gridSpacing, displayMetrics );
//        TypedArray typedArray = context.obtainStyledAttributes( attrs, R.styleable.NineGridView );
//        gridSpacing = ( int ) typedArray.getDimension( R.styleable.NineGridView_ngv_gridSpacing, gridSpacing );
//        maxImageSize = typedArray.getInt( R.styleable.NineGridView_ngv_maxSize, maxImageSize );
//        mode = typedArray.getInt( R.styleable.NineGridView_ngv_mode, mode );
//        typedArray.recycle();
//    }
//
//    @Override
//    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
//        super.onMeasure( widthMeasureSpec, heightMeasureSpec );
////        if ( imageAttrs == null ) return;
////        int width = MeasureSpec.getSize( widthMeasureSpec );
////        int height = 0;
////        int totalWidth = width - getPaddingLeft() - getPaddingRight();
////        if ( imageAttrs.size() == 1 ) {
////            gridWidth = totalWidth;
////            gridHeight = totalWidth;
////        } else {
////            gridWidth = gridHeight = ( totalWidth - gridSpacing * 2 ) / columnCount;
////        }
////        width = gridWidth * columnCount + gridSpacing * ( columnCount - 1 ) + getPaddingLeft() + getPaddingRight();
////        height = gridHeight * rowCount + gridSpacing * ( rowCount - 1 ) + getPaddingTop() + getPaddingBottom();
////        setMeasuredDimension( width, height );
////
//        if ( measureWidth( widthMeasureSpec ) > 0 ) {
//            if ( imageAttrs != null && imageAttrs.size() > 0 ) {
//                setList( imageAttrs );
//            }
//        }
//
////        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
//
//    /**
//     * Determines the width of this view
//     *
//     * @param measureSpec A measureSpec packed into an int
//     * @return The width of the view, honoring constraints from measureSpec
//     */
//    private int measureWidth( int measureSpec ) {
//        int result = 0;
//        int specMode = MeasureSpec.getMode( measureSpec );
//        int specSize = MeasureSpec.getSize( measureSpec );
//
//        if ( specMode == MeasureSpec.EXACTLY ) {
//            // We were told how big to be
//            result = specSize;
//        } else {
//            // Measure the text
//            // result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
//            // + getPaddingRight();
//            if ( specMode == MeasureSpec.AT_MOST ) {
//                // Respect AT_MOST value if that was what is called for by
//                // measureSpec
//                result = Math.min( result, specSize );
//            }
//        }
//        return result;
//    }
//
////    @Override
////    protected void onLayout( boolean changed, int l, int t, int r, int b ) {
////        layoutChildrenView();
////    }
////
////    private void layoutChildrenView() {
////        if ( imageAttrs == null ) return;
////        int childrenCount = imageAttrs.size();
////        for (int i = 0; i < childrenCount; i++) {
////            ImageView imageView = ( ImageView ) getChildAt( i );
//////            ImageAttr imageAttr = imageAttrs.get( i );
////            if ( imageView == null ) continue;
////
////            int rowNum = i / columnCount;
////            int columnNum = i % columnCount;
////            int left = ( gridWidth + gridSpacing ) * columnNum + getPaddingLeft();
////            int top = ( gridHeight + gridSpacing ) * rowNum + getPaddingTop();
////            int right = left + gridWidth;
////            int bottom = top + gridHeight;
////            imageView.layout( left, top, right, bottom );
////
////        }
////    }
//
//    public void setList( @NonNull List< ImageAttr > lists ) {
//        this.removeAllViews();
//        if ( lists == null || lists.isEmpty() ) {
//            setVisibility( GONE );
//            return;
//        }
//        setVisibility( VISIBLE );
//        if ( maxImageSize > 0 && lists.size() > maxImageSize ) {
//            attrList = lists.subList( 0, maxImageSize );
//        }
//        int imageCount = attrList.size(); // 再次获取图片数量
//
//        // 默认是3列显示，行数根据图片的数量决定
//        rowCount = imageCount / 3 + ( imageCount % 3 == 0 ? 0 : 1 );
//        columnCount = 3;
//        // Grid模式下，显示4张使用2X2模式
//        if ( mode == MODE_GRID ) {
//            if ( imageCount == 4 || imageCount == 2 ) {
//                columnCount = 2;
//            } else if ( imageCount == 1 ) {
//                columnCount = 1;
//            } else {
//                columnCount = 3;
//            }
//        }
//
//        // 保证View的复用，避免重复创建
////        if ( imageAttrs == null ) {
//        for (int i = 0; i < imageCount; i++) {
//            ImageView imageView = getImageView( i );
//            addView( imageView, generateDefaultLayoutParams() );
//        }
////        } else {
////            int oldViewCount = imageAttrs.size();
////            if ( oldViewCount > imageCount ) {
////                removeViews( imageCount, oldViewCount - imageCount );
////            } else if ( oldViewCount < imageCount ) {
////                for (int i = oldViewCount; i < imageCount; i++) {
////                    ImageView imageView = getImageView( i );
////                    addView( imageView, generateDefaultLayoutParams() );
////                }
////            }
////        }
//
//        // 修改最后一个条目，决定是否显示更多
//        if ( lists.size() > maxImageSize ) {
//            View child = getChildAt( maxImageSize - 1 );
//            if ( child instanceof ImageViewWrapper ) {
//                ImageViewWrapper imageView = ( ImageViewWrapper ) child;
//                imageView.setMoreNum( lists.size() - maxImageSize );
//            }
//        }
//
//        imageAttrs = lists;
////        layoutChildrenView( );
////        this.postInvalidate();
//    }
//
//    // 获得ImageView，并保证ImageView的重用
//    private ImageView getImageView( int position ) {
//        ImageAttr imageAttr = attrList.get( position );
//        String url = TextUtils.isEmpty( imageAttr.thumbnailUrl ) ? imageAttr.url : imageAttr.thumbnailUrl;
//        ImageView imageView = new ImageViewWrapper( getContext() );
//        imageView.setTag( R.string.zone_img_position, position );
//        imageView.setId( url.hashCode() );
//        imageView.setOnClickListener( v -> {
//            if ( mOnItemClickListener != null ) {
//                mOnItemClickListener.onItemClick( v, position );
//            }
//        } );
//        imageViews.add( imageView );
//
//        GlideImageLoader imageLoader = GlideImageLoader.create( imageView );
//
//        RequestOptions requestOptions = imageLoader.requestOptions( R.color.colorAccent )
//                                                   .centerCrop()
//                                                   .diskCacheStrategy( DiskCacheStrategy.ALL );
//
//        imageLoader.requestBuilder( url, requestOptions )
//                   .transition( DrawableTransitionOptions.withCrossFade() )
//                   .listener( new RequestListener< Drawable >() {
//                       @Override
//                       public boolean onLoadFailed( @Nullable GlideException e, Object model, Target< Drawable > target, boolean isFirstResource ) {
//                           return false;
//                       }
//
//                       @Override
//                       public boolean onResourceReady( Drawable resource, Object model, Target< Drawable > target, DataSource dataSource, boolean
//                               isFirstResource ) {
//
//                           imageAttr.realWidth = resource.getIntrinsicWidth();
//                           imageAttr.realHeight = resource.getIntrinsicHeight();
////                           if ( count == 1 ) {
////                               setSingleImageWidthHeight( resource );
////                           }
//                           return false;
//                       }
//                   } )
//                   .into( imageView );
//        return imageView;
//    }
//
//    public void setOnItemClickListener( OnItemClickListener mOnItemClickListener ) {
//        this.mOnItemClickListener = mOnItemClickListener;
//    }
//
//    private OnItemClickListener mOnItemClickListener;
//
//    public interface OnItemClickListener {
//        public void onItemClick( View view, int position );
//    }
//
//    public void setGridSpacing( int spacing ) {
//        gridSpacing = spacing;
//    }
//
//    public void setMaxSize( int maxSize ) {
//        maxImageSize = maxSize;
//    }
//
//    public int getMaxSize() {
//        return maxImageSize;
//    }
//
//}