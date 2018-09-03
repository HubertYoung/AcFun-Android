package com.hubertyoung.component_multiimageview.multiimageview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.hubertyoung.common.ImageLoaderUtils;
import com.hubertyoung.component_multiimageview.R;

import java.util.List;


/**
 * @author:Yang
 * @date:2017/7/18 13:13
 * @since:v1.0
 * @desc:com.hubertyoung.common.commonwidget
 * @param:显示1~N张图片的View
 */
public class MultiImageView extends LinearLayout {
	private static int MAX_WIDTH = 0;

	// 照片的Url列表
	private List< ImageAttr > imagesList;

	/**
	 * 长度 单位为Pixel
	 **/
	private int pxOneMaxWandH;  // 单张图最大允许宽高
	private int pxMoreWandH = 0;// 多张图的宽高
	private int gridSpacing = 6;                    // 宫格间距，单位dp

	private int MAX_PER_ROW_COUNT = 3;// 每行显示最大数

	private LayoutParams onePicPara;
	private LayoutParams morePara, moreParaColumnFirst;
	private LayoutParams moreParaColumnLast;

	private LayoutParams rowPara;

	private OnItemClickListener mOnItemClickListener;
	private int maxImageSize = 9;
	// 最终照片的Url列表
	private List< ImageAttr > attrList;
	// 最终照片的Url数量
	private int imageCount;

	public MultiImageView setOnItemClickListener( OnItemClickListener onItemClickListener ) {
		mOnItemClickListener = onItemClickListener;
		return this;
	}

	public MultiImageView( Context context ) {
		super( context );
	}

	public MultiImageView( Context context, AttributeSet attrs ) {
		super( context, attrs );
		init( context, attrs );
	}

	private void init( Context context, AttributeSet attrs ) {
//        int WIDTH = DisplayUtil.getScreenWidth( context ) - DisplayUtil.dip2px( 15 * 2 );

//        imageViews = new ArrayList<>();

		DisplayMetrics displayMetrics = context.getResources()
											   .getDisplayMetrics();
		gridSpacing = ( int ) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, gridSpacing, displayMetrics );
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NineGridView);
//        gridSpacing = (int) typedArray.getDimension(R.styleable.NineGridView_ngv_gridSpacing, gridSpacing);
//        maxImageSize = typedArray.getInt(R.styleable.NineGridView_ngv_maxSize, maxImageSize);
//        mode = typedArray.getInt(R.styleable.NineGridView_ngv_mode, mode);
//        typedArray.recycle();
	}

	public MultiImageView setList( List< ImageAttr > lists ) throws IllegalArgumentException {
		if ( lists == null ) {
			throw new IllegalArgumentException( "imageList is null..." );
		}
		imagesList = lists;
		imageCount = imagesList.size();
		if ( imageCount == 4 || imageCount == 2 ) {
			MAX_PER_ROW_COUNT = 2;
		} else if ( imageCount == 1 ) {
			MAX_PER_ROW_COUNT = 3;
		} else {
			MAX_PER_ROW_COUNT = 3;
		}
		if ( MAX_WIDTH > 0 ) {
			pxMoreWandH = ( MAX_WIDTH - gridSpacing * ( MAX_PER_ROW_COUNT - 1 ) * 2 ) / MAX_PER_ROW_COUNT;//解决右侧图片和内容对不齐问题

			pxOneMaxWandH = MAX_WIDTH * 2 / MAX_PER_ROW_COUNT + getPaddingLeft() + getPaddingRight();
			initImageLayoutParams();
		}

		initView();
		return this;
	}

	@Override
	protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
		if ( MAX_WIDTH == 0 ) {
			int width = measureWidth( widthMeasureSpec );
			if ( width > 0 ) {
				MAX_WIDTH = width;
				if ( imagesList != null && imagesList.size() > 0 ) {
					setList( imagesList );
				}
			}
		}
		super.onMeasure( widthMeasureSpec, heightMeasureSpec );
	}

	/**
	 * Determines the width of this view
	 *
	 * @param measureSpec A measureSpec packed into an int
	 * @return The width of the view, honoring constraints from measureSpec
	 */
	private int measureWidth( int measureSpec ) {
		int result = 0;
		int specMode = MeasureSpec.getMode( measureSpec );
		int specSize = MeasureSpec.getSize( measureSpec );

		if ( specMode == MeasureSpec.EXACTLY ) {
			// We were told how big to be
			result = specSize;
		} else {
			// Measure the text
			// result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
			// + getPaddingRight();
			if ( specMode == MeasureSpec.AT_MOST ) {
				// Respect AT_MOST value if that was what is called for by
				// measureSpec
				result = Math.min( result, specSize );
			}
		}
		return result;
	}

	private void initImageLayoutParams() {
		int wrap = LayoutParams.MATCH_PARENT;
		int match = LayoutParams.MATCH_PARENT;
		//pxOneMaxWandH
		onePicPara = new LayoutParams( match, wrap );
		onePicPara.setMargins( 0, gridSpacing, 0, gridSpacing );

		moreParaColumnFirst = new LayoutParams( pxMoreWandH, pxMoreWandH );
		moreParaColumnFirst.setMargins( 0, gridSpacing, gridSpacing, gridSpacing );

		moreParaColumnLast = new LayoutParams( pxMoreWandH, pxMoreWandH );
		moreParaColumnLast.setMargins( gridSpacing, gridSpacing, 0, gridSpacing );

		morePara = new LayoutParams( pxMoreWandH, pxMoreWandH );
		morePara.setMargins( gridSpacing, gridSpacing, gridSpacing, gridSpacing );

		rowPara = new LayoutParams( match, wrap );
	}

	// 根据imageView的数量初始化不同的View布局,还要为每一个View作点击效果
	private void initView() {
		this.setOrientation( VERTICAL );
		this.removeAllViews();
		if ( MAX_WIDTH == 0 ) {
			//为了触发onMeasure()来测量MultiImageView的最大宽度，MultiImageView的宽设置为match_parent
			addView( new View( getContext() ) );
			return;
		}

		if ( imagesList == null || imagesList.size() == 0 ) {
			return;
		}

		if ( maxImageSize > 0 && imagesList.size() > maxImageSize ) {
			attrList = imagesList.subList( 0, maxImageSize );
			imageCount = attrList.size(); // 再次获取图片数量
		}

//		if ( imagesList.size() == 1 ) {
//			addView( createImageView( 0, false ) );
//		} else {
//            int allCount = imagesList.size();
			int rowCount = imageCount / MAX_PER_ROW_COUNT + ( imageCount % MAX_PER_ROW_COUNT > 0 ? 1 : 0 );// 行数
			for (int rowCursor = 0; rowCursor < rowCount; rowCursor++) {
				LinearLayout rowLayout = new LinearLayout( getContext() );
				rowLayout.setOrientation( LinearLayout.HORIZONTAL );

				rowLayout.setLayoutParams( rowPara );
				if ( rowCursor != 0 ) {
					rowLayout.setPadding( 0, 0, 0, 0 );
				}

				int columnCount = imageCount % MAX_PER_ROW_COUNT == 0 ? MAX_PER_ROW_COUNT : imageCount % MAX_PER_ROW_COUNT;//每行的列数
				if ( rowCursor != rowCount - 1 ) {
					columnCount = MAX_PER_ROW_COUNT;
				}
				addView( rowLayout );

				int rowOffset = rowCursor * MAX_PER_ROW_COUNT;// 行偏移
				for (int columnCursor = 0; columnCursor < columnCount; columnCursor++) {
					int position = columnCursor + rowOffset;
					rowLayout.addView( createImageView( position, true ) );
				}
			}
//		}
		// 修改最后一个条目，决定是否显示更多
		if ( imagesList.size() > maxImageSize ) {
			LinearLayout childLinearLayout = ( LinearLayout ) getChildAt( getChildCount() - 1 );
			View child = childLinearLayout.getChildAt( childLinearLayout.getChildCount() - 1 );
			if ( child instanceof ImageViewWrapper ) {
				ImageViewWrapper imageView = ( ImageViewWrapper ) child;
				imageView.setMoreNum( imagesList.size() - maxImageSize );
			}
		}
	}

	private ImageView createImageView( int position, final boolean isMultiImage ) {
		String url = imagesList.get( position ).Pic;
		ImageViewWrapper imageView = new ImageViewWrapper( getContext() );
		if ( isMultiImage ) {
			imageView.setScaleType( ScaleType.CENTER_CROP );
			imageView.setLayoutParams( position % MAX_PER_ROW_COUNT == 0 ? moreParaColumnFirst : position % MAX_PER_ROW_COUNT == ( MAX_PER_ROW_COUNT - 1 ) ? moreParaColumnLast : morePara );
		} else {
			imageView.setAdjustViewBounds( true );
			imageView.setScaleType( ScaleType.CENTER_CROP );
			imageView.setMaxHeight( pxOneMaxWandH );
			imageView.setLayoutParams( onePicPara );
		}

		imageView.setTag( R.string.zone_img_position, position );
		if ( !TextUtils.isEmpty( url ) ) {
			imageView.setId( url.hashCode() );
		}
		imageView.setColorFilterEnabled( mOnItemClickListener != null );
		if ( mOnItemClickListener != null ) {
			imageView.setOnClickListener( new OnClickListener() {
				@Override
				public void onClick( View v ) {
					mOnItemClickListener.onItemClick( v, ( Integer ) v.getTag( R.string.zone_img_position ) );
				}
			} );
		}
		ImageLoaderUtils.getInstance()
						.display( getContext(), imageView, ImageLoaderUtils.getInstance()
																		   .getImageUrl( url ) );
		return imageView;
	}

	public interface OnItemClickListener {
		void onItemClick( View view, int position );
	}
}