package com.kento.component.basic.commonwidget.banner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kento.component.basic.R;
import com.kento.component.basic.commonutils.CardSLifeLogUtils;
import com.kento.component.basic.commonutils.DisplayUtil;
import com.kento.component.basic.commonutils.imageloader.ImageLoaderUtils;
import com.kento.component.basic.commonwidget.BannerViewPager;
import com.kento.component.basic.commonwidget.banner.transformer.ABaseTransformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：JIUU on 2017-7-11 11:00:51
 * QQ号：1344393464
 * 作用：自定义Banner无限轮播控件
 */
public class BannerView extends LinearLayout implements BannerAdapter.ViewPagerOnItemClickListener {
	private static final String TAG = BannerView.class.getSimpleName();
	//默认轮播时间，3s
	private int delayTime = 3 * 1000;
	private int cacheDelayTime = delayTime;

	private List< View > imageViewList;

	private List< BannerEntity > bannerList;

	//选中显示Indicator
	private int selectRes = R.drawable.banner_shape_dots_select;

	//非选中显示Indicator
	private int unSelcetRes = R.drawable.banner_shape_dots_default;

	//当前页的下标
	private int currrentPos;

	private BannerAdapter bannerAdapter;
	private BannerViewOnItemClickListener mBannerViewOnItemClickListener;
	private BannerViewPager viewPager;
	private LinearLayout points;
	private CompositeDisposable compositeDisposable;
//	private JCVideoPlayerStandard videoPlayerStandard;
	private Disposable disposable;
	private int currentItem = 1;
	private int count = 0;
	private int lastPosition = 1;
	private boolean isMargin = false;
	private AppCompatActivity activity;

	public void setmViewPagerOnItemClickListener( BannerViewOnItemClickListener mBannerViewOnItemClickListener ) {

		this.mBannerViewOnItemClickListener = mBannerViewOnItemClickListener;
	}

	public void setOnHiddenChanged( boolean hidden ) {
		if ( hidden ) {
//			if ( videoPlayerStandard != null && videoPlayerStandard.currentState == videoPlayerStandard.CURRENT_STATE_PLAYING ) {
//				videoPlayerStandard.onEvent( JCUserAction.ON_CLICK_PAUSE );
//				JCMediaManager.instance().mediaPlayer.pause();
//				videoPlayerStandard.onStatePause();
//			}
		}
	}

	public interface BannerViewOnItemClickListener {

		void onItemClick( String url, String title, String dataJson );
	}

	public BannerView( Context context ) {

		this( context, null );
	}

	public BannerView( Context context, AttributeSet attrs ) {

		this( context, attrs, 0 );
	}

	public BannerView( Context context, AttributeSet attrs, int defStyleAttr ) {

		super( context, attrs, defStyleAttr );
		View view = LayoutInflater.from( context )
								  .inflate( R.layout.layout_custom_banner, this, true );
		viewPager = ( BannerViewPager ) view.findViewById( R.id.layout_banner_viewpager );
		points = ( LinearLayout ) view.findViewById( R.id.layout_banner_points_group );

		imageViewList = new ArrayList<>();
		initViewPagerScroll();
		setBannerAnimation( ABaseTransformer.class );
	}

	private void initViewPagerScroll() {
		try {
			Field mField = ViewPager.class.getDeclaredField( "mScroller" );
			mField.setAccessible( true );
			BannerScroller mScroller = new BannerScroller( viewPager.getContext() );
			mScroller.setDuration( 800 );
			mField.set( viewPager, mScroller );
		} catch ( Exception e ) {
			CardSLifeLogUtils.loge( e );
		}
	}

	/**
	 * 设置轮播间隔时间
	 *
	 * @param time 轮播间隔时间，单位秒
	 */
	public BannerView delayTime( int time ) {

		this.delayTime = time * 1000;
		this.cacheDelayTime = delayTime;
		return this;
	}

	/**
	 * 使能显示左右图片间距
	 *
	 * @param isMargin 轮播间隔时间，单位秒
	 */
	public BannerView enableMargin( boolean isMargin ) {

		this.isMargin = isMargin;
		return this;
	}

	/**
	 * @param activity
	 * @return
	 */
	public BannerView instance( AppCompatActivity activity ) {

		this.activity = activity;
		return this;
	}

	public BannerView setBannerAnimation( Class< ? extends ViewPager.PageTransformer > transformer ) {
		try {
			setPageTransformer( true, transformer.newInstance() );
		} catch ( Exception e ) {
			CardSLifeLogUtils.loge( "Please set the PageTransformer class" );
		}
		return this;
	}

	public BannerView setPageTransformer( boolean reverseDrawingOrder, ViewPager.PageTransformer transformer ) {
		viewPager.setPageTransformer( reverseDrawingOrder, transformer );
		return this;
	}

	/**
	 * 设置Points资源 Res
	 *
	 * @param selectRes   选中状态
	 * @param unselcetRes 非选中状态
	 */
	public void setPointsRes( int selectRes, int unselcetRes ) {

		this.selectRes = selectRes;
		this.unSelcetRes = unselcetRes;
	}

	/**
	 * 图片轮播需要传入参数
	 */
	public void build( List< BannerEntity > list ) {

		if ( list == null || list.size() <= 0 ) {
			this.setBackgroundResource( R.drawable.banner_default );
			postInvalidate();
			return;
		}
		destory();
		count = list.size();
//        if ( pointSize == 2 ) {
//            bannerList.addAll( list );
//        }
		//判断是否清空 指示器点
		if ( points.getChildCount() != 0 ) {
			points.removeAllViewsInLayout();
		}

		bannerList = new ArrayList<>();
		if ( count > 1 ) {

			//初始化与个数相同的指示器点
			for (int i = 0; i < count; i++) {
				View dot = new View( getContext() );
				dot.setBackgroundResource( unSelcetRes );
				LayoutParams params = new LayoutParams( DisplayUtil.dip2px( 5 ), DisplayUtil.dip2px( 5 ) );
				params.leftMargin = DisplayUtil.dip2px( 5 );
				dot.setLayoutParams( params );
				dot.setEnabled( false );
				points.addView( dot );
			}
			points.getChildAt( 0 )
				  .setBackgroundResource( selectRes );


			for (int i = 0; i <= count + 1; i++) {
				BannerEntity entity = null;
				if ( i == 0 ) {
					entity = list.get( count - 1 );
				} else if ( i == count + 1 ) {
					entity = list.get( 0 );
				} else {
					entity = list.get( i - 1 );
				}
				bannerList.add( entity );

				initBannerType( entity );
			}
		} else {
			for (int i = 0; i < count; i++) {
				BannerEntity entity = null;
				entity = list.get( 0 );
				bannerList.add( entity );

				initBannerType( entity );
			}
		}
		currentItem = 1;
		//监听图片轮播，改变指示器状态
		viewPager.clearOnPageChangeListeners();
		if ( count > 1 ) {
			viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {

				@Override
				public void onPageScrolled( int position, float positionOffset, int positionOffsetPixels ) {
//					if ( videoPlayerStandard != null && videoPlayerStandard.currentState == videoPlayerStandard.CURRENT_STATE_PLAYING ) {
//						videoPlayerStandard.onEvent( JCUserAction.ON_CLICK_PAUSE );
//						JCMediaManager.instance().mediaPlayer.pause();
//						videoPlayerStandard.onStatePause();
//					}
				}

				@Override
				public void onPageSelected( int position ) {

					if ( points.getChildCount() > 1 ) {
						points.getChildAt( ( lastPosition - 1 + count ) % count )
							  .setBackgroundResource( unSelcetRes );
						points.getChildAt( ( position - 1 + count ) % count )
							  .setBackgroundResource( selectRes );
						lastPosition = position;
					}
//                if ( position == 0 ) position = count;
//                if ( position > count ) position = 1;
				}

				@Override
				public void onPageScrollStateChanged( int state ) {
					switch ( state ) {
						case ViewPager.SCROLL_STATE_IDLE:
							if ( currentItem == 0 ) {
								viewPager.setCurrentItem( count, false );
							} else if ( currentItem == count + 1 ) {
								viewPager.setCurrentItem( 1, false );
							}
							break;
						case ViewPager.SCROLL_STATE_DRAGGING:
							if ( currentItem == count + 1 ) {
								viewPager.setCurrentItem( 1, false );
							} else if ( currentItem == 0 ) {
								viewPager.setCurrentItem( count, false );
							}
							break;
					}
					currentItem = viewPager.getCurrentItem();
				}
			} );
		}
		bannerAdapter = new BannerAdapter( imageViewList );
		viewPager.setAdapter( bannerAdapter );
//        viewPager.setCurrentItem(1000 % pointSize + 1000);//使用户看不到边界
//        bannerAdapter.notifyDataSetChanged();
		viewPager.setFocusable( true );
		viewPager.setCurrentItem( 1 );
		viewPager.setOffscreenPageLimit( 2 );
		viewPager.setPageMargin( DisplayUtil.dip2px( 10 ) );
		if ( isMargin ) {
			RelativeLayout.LayoutParams layoutParams = ( RelativeLayout.LayoutParams ) viewPager.getLayoutParams();
			layoutParams.setMargins( DisplayUtil.dip2px( 20 ), 0, DisplayUtil.dip2px( 20 ), 0 );
		}

		bannerAdapter.setmViewPagerOnItemClickListener( this );
		if ( count > 1 ) {
			viewPager.setScrollable( true );
			startScroll();
		} else {
			viewPager.setScrollable( false );
		}
	}

	/**
	 * 初始化banner类型
	 *
	 * @param entity
	 */
	private void initBannerType( BannerEntity entity ) {
		if ( entity.type == 0 ) {
//			ShapeImageView mImageView = new ShapeImageView( getContext() );
			View view = LayoutInflater.from( getContext() )
									  .inflate( R.layout.shape_imageview, null );
			ImageView mImageView = ( ImageView ) view.findViewById( R.id.iv_banner_view );
//			mImageView.setRadius( getContext().getResources()
//											  .getDimensionPixelOffset( R.dimen.DIMEN_4PX ) );
			ImageLoaderUtils.getInstance()
							.display( getContext(), mImageView, entity.Pic, true, R.drawable.banner_default );
			imageViewList.add( mImageView );
		} else if ( entity.type == 1 ) {
//			videoPlayerStandard = new JCVideoPlayerStandard( getContext() );

			initJcVideoListener();

//			videoPlayerStandard.setUp( entity.videoUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, entity.title );
//			ImageLoaderUtils.getInstance()
//							.display( getContext(), videoPlayerStandard.thumbImageView, entity.Pic, false, R.drawable.banner_default );
//			imageViewList.add( videoPlayerStandard );
//				if ( TextUtils.equals( "gif", entity.type ) ) {
//				ImageView mImageView = new ImageView( getContext() );
//				ImageLoaderUtils.getInstance()
//								.displayGif( getContext(), mImageView, entity.Pic );
//				imageViewList.add( mImageView );
//			} else

		}
	}

	/**
	 * 初始化jc播放器
	 */
	private void initJcVideoListener() {
//		if ( videoPlayerStandard != null ) {
//			videoPlayerStandard.setJcVideoPlayerListener( new JCVideoPlayerListener() {
//				@Override
//				public void onState( String pause ) {
//					switch ( pause ) {
//						//停止播放
//						case JCVideoPlayerListener.PAUSE:
//							if ( isStopScroll ) {
//								startScroll();
//							}
//							break;
//						//开始播放
//						case JCVideoPlayerListener.PLAYING:
//							stopScroll();
//							break;
//						//自动播放结束
//						case JCVideoPlayerListener.AUTOCOMPLETE:
//							if ( isStopScroll ) {
//								startScroll();
//							}
//							break;
//						//视频缓冲准备阶段
//						case JCVideoPlayerListener.PREPARING:
//							stopScroll();
//							break;
//					}
//				}
//			} );
//		}
	}

	private boolean isStopScroll = false;

	/**
	 * 图片开始轮播
	 */
	private void startScroll() {
		if ( count > 1 ) {
			isStopScroll = false;
			if ( compositeDisposable != null && !compositeDisposable.isDisposed() ) compositeDisposable.dispose();
			compositeDisposable = new CompositeDisposable();
			//显示播放的页面
			Flowable< Long > longFlowable = Flowable.timer( delayTime, TimeUnit.MILLISECONDS )

													.subscribeOn( Schedulers.io() )
													.observeOn( AndroidSchedulers.mainThread() );
			if ( activity != null ) {
//				longFlowable.compose( activity.bindToLifecycle() );
			}
			disposable = longFlowable.subscribe( aLong -> {
				if ( isStopScroll && count < 2 ) {
					return;
				}
				isStopScroll = true;
				currentItem = currentItem % ( count + 1 ) + 1;
				if ( currentItem == 1 ) {
					viewPager.setCurrentItem( currentItem, false );
					delayTime = 0;
					startScroll();
				} else {
					viewPager.setCurrentItem( currentItem );
					delayTime = cacheDelayTime;
					startScroll();
				}
			} );

			compositeDisposable.add( disposable );
		}
	}

	@Override
	public boolean dispatchTouchEvent( MotionEvent ev ) {
		int action = ev.getAction();
		if ( action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_OUTSIDE ) {
//                if ( isStopScroll ) {
			isStopScroll = false;
			startScroll();
//                        }
		} else if ( action == MotionEvent.ACTION_DOWN ) {
			isStopScroll = true;
			stopScroll();
		}
		return super.dispatchTouchEvent( ev );
	}

	/**
	 * 图片停止轮播
	 */
	private void stopScroll() {

		isStopScroll = true;
		if ( compositeDisposable != null && !compositeDisposable.isDisposed() ) {
			compositeDisposable.clear();
		}
		if ( disposable != null && !disposable.isDisposed() ) {
			disposable.dispose();
		}
	}

	public void destory() {
		if ( compositeDisposable != null && !compositeDisposable.isDisposed() ) {
			compositeDisposable.clear();
		}
		if ( disposable != null && !disposable.isDisposed() ) {
			disposable.dispose();
		}
		imageViewList.clear();
	}

	public BannerAdapter getBannerAdapter() {
		return bannerAdapter;
	}

	/**
	 * 设置ViewPager的Item点击回调事件
	 */
	@Override
	public void onItemClick() {
		if ( mBannerViewOnItemClickListener != null ) {
			mBannerViewOnItemClickListener.onItemClick( bannerList.get( currrentPos ).url, bannerList.get( currrentPos ).title, bannerList.get( currrentPos ).dataJson );
		}

	}
}
