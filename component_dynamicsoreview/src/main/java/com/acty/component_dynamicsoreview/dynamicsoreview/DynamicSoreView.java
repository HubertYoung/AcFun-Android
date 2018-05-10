package com.acty.component_dynamicsoreview.dynamicsoreview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.acty.component_dynamicsoreview.R;
import com.acty.component_dynamicsoreview.dynamicsoreview.Interface.IDynamicSore;
import com.acty.component_dynamicsoreview.dynamicsoreview.adapter.ViewPagerAdapter;
import com.hubertyoung.common.utils.CommonLog;
import com.hubertyoung.common.utils.DisplayUtil;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * <br>
 * function:动态设置轮播菜单
 * <p>
 *
 * @author:Yang
 * @date:2018/2/6 下午4:32
 * @since:V1.0
 * @desc:com.kento.common.commonwidget.dynamicsoreview
 */
public class DynamicSoreView< T > extends LinearLayout {
	Context mContext;
	private ViewPager viewPager;
	private LinearLayout llIndicator;

	//选中点
	@DrawableRes
	private int RadioSelect;
	//未选中点
	@DrawableRes
	private int RadioUnselected;
	//圆点间距
	private int distance;

	//每页展示几个
	private int number;
	//展示数据的gridView
	private Integer gridView;
	//总页数
	private int page;
	//数据List
	private List< T > dataList;

	List< View > listSoreView = new ArrayList<>();
	View soreView;


	//接口
	private IDynamicSore iDynamicSore;

	//设置接口
	public IDynamicSore getiDynamicSore() {
		return iDynamicSore;
	}

	public void setiDynamicSore( IDynamicSore iDynamicSore ) {
		this.iDynamicSore = iDynamicSore;
	}

	public DynamicSoreView( Context context, AttributeSet attrs ) {
		super( context, attrs );
		mContext = context;
		LayoutInflater.from( context ).inflate( R.layout.layout_custom_dynamic, this, true );
		viewPager = ( ViewPager ) findViewById( R.id.layout_banner_viewpager );
		llIndicator = ( LinearLayout ) findViewById( R.id.layout_banner_points_group );

		TypedArray typedArray = context.obtainStyledAttributes( attrs, R.styleable.DynamicSoreView );
		if ( typedArray != null ) {
			//选中点
			RadioSelect = typedArray.getResourceId( R.styleable.DynamicSoreView_SoreRadioSelect, 0 );
			//未选中点
			RadioUnselected = typedArray.getResourceId( R.styleable.DynamicSoreView_SoreRadioUnselected, 0 );
			//圆点间距
			distance = AutoUtils.getPercentHeightSizeBigger( typedArray.getInteger( R.styleable.DynamicSoreView_SoreDistance, 10 ) );
			//每页显示几个
			number = typedArray.getInteger( R.styleable.DynamicSoreView_SoreNumber, 8 );
			typedArray.recycle();
		}

	}

	//初始化ViewPager
	private void initViewPager() {
		listSoreView = new ArrayList<>();
		for (int i = 0; i < page; i++) {
			if ( gridView == 0 ) {
				throw new RuntimeException( "gridView is null" );
			} else {
				//循环拿到传入的View
				soreView = LayoutInflater.from( getContext() ).inflate( gridView, null );
			}
			//通过接口回掉的形式返回当前的View,实现接口后开源拿到每个View然后进行操作
			if ( iDynamicSore != null ) {
				List< T > data;
				int total = dataList.size();
				if ( i == page - 1 ) {
					//添加按钮
					data = new ArrayList<>();
					for (int j = i * number; j < total; j++) {
						data.add( dataList.get( j ) );
					}
				} else {
					data = new ArrayList<>();
					int size;
					if ( total < number ) {
						size = total;
					} else {
						size = ( i + 1 ) * number;
					}
					for (int j = i * number; j < size; j++) {
						data.add( dataList.get( j ) );
					}
				}
				iDynamicSore.setGridView( soreView, i, data );
			}
			//将获取到的View添加到List中
			listSoreView.add( soreView );
		}
		//设置viewPager的Adapter
		viewPager.setAdapter( new ViewPagerAdapter( listSoreView ) );
		setAnimation( FadeInOutPageTransformer.class );
		//初始化LinearLayout，加入指示器
		initLinearLayout( viewPager, page, llIndicator );
	}

	public DynamicSoreView< T > setAnimation( Class< ? extends ViewPager.PageTransformer > transformer ) {
		try {
			setPageTransformer( true, transformer.newInstance() );
		} catch ( Exception e ) {
			CommonLog.loge( "Please set the PageTransformer class" );
		}
		return this;
	}

	public DynamicSoreView< T > setPageTransformer( boolean reverseDrawingOrder, ViewPager.PageTransformer transformer ) {
		viewPager.setPageTransformer( reverseDrawingOrder, transformer );
		return this;
	}

	/**
	 * 设置指示器，设置ViewPager滑动事件监听
	 *
	 * @param viewPager    --ViewPager
	 * @param pageSize     --View的页数
	 * @param linearLayout --LinearLayout
	 */
	private void initLinearLayout( ViewPager viewPager, int pageSize, LinearLayout linearLayout ) {
		linearLayout.removeAllViews();
		//定义数组放置指示器的点，pageSize是View个数
		final ImageView[] imageViews = new ImageView[ pageSize ];
		for (int i = 0; i < pageSize; i++) {
			//创建ImageView
			ImageView image = new ImageView( mContext );
			//将ImageView放入数组
			imageViews[ i ] = image;
			//默认选中第一个
			if ( i == 0 ) {
				//选中的点
				image.setImageResource( RadioSelect );
			} else {
				//未选中的点
				image.setImageResource( RadioUnselected );
			}
			//设置宽高
			LayoutParams params = new LayoutParams( DisplayUtil.dip2px( 6 ), DisplayUtil.dip2px( 6 ) );
			params.setMargins( distance, 0, distance, 0 );
			//将点添加到LinearLayout中
			linearLayout.addView( image, params );
		}

		//ViewPager的滑动事件
		viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged( int arg0 ) {
			}

			@Override
			public void onPageScrolled( int arg0, float arg1, int arg2 ) {
			}

			@Override
			public void onPageSelected( int arg0 ) {
				//arg0当前ViewPager
				for (int i = 0; i < imageViews.length; i++) {
					//设置为选中的点
					imageViews[ arg0 ].setImageResource( RadioSelect );
					//判断当前的点i如果不等于当前页的话就设置为未选中
					if ( arg0 != i ) {
						imageViews[ i ].setImageResource( RadioUnselected );
					}
				}
			}
		} );
	}

	/**
	 * 设置view
	 *
	 * @param gridView
	 * @return
	 */
	public DynamicSoreView setGridView( Integer gridView ) {
		this.gridView = gridView;
		return this;
	}

	/**
	 * 设置view
	 *
	 * @return
	 */
	public DynamicSoreView setNumColumns( GridLayoutManager manager, int number ) {
		//设置每行GridView个数
		manager.setSpanCount( number );
		return this;
	}

	public int getNumber() {
		return number;
	}

	/**
	 * 设置初始化
	 */
	public DynamicSoreView init( List< T > t ) {
		this.dataList = t;
		this.page = ( int ) Math.ceil( ( double ) t.size() / number );//计算出有几页/这里用了ceil函数凑整，2.1=3
		initViewPager();
		return this;
	}
}
