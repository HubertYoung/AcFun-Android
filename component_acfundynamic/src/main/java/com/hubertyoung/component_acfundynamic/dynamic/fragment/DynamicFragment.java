package com.hubertyoung.component_acfundynamic.dynamic.fragment;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.billy.cc.core.component.CC;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.baserx.event.Subscribe;
import com.hubertyoung.common.baserx.event.inner.EventBean;
import com.hubertyoung.common.baserx.event.inner.ThreadMode;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.utils.SigninHelper;
import com.hubertyoung.common.utils.bar.BarUtils;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_acfundynamic.R;
import com.hubertyoung.component_acfundynamic.dynamic.adapter.DynamicPagerAdapter;
import com.hubertyoung.component_acfundynamic.dynamic.control.DynamicControl;
import com.hubertyoung.component_acfundynamic.dynamic.model.DynamicModelImp;
import com.hubertyoung.component_acfundynamic.dynamic.presenter.DynamicPresenterImp;
import com.jakewharton.rxbinding3.view.RxView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.concurrent.TimeUnit;

import androidx.viewpager.widget.ViewPager;
import io.reactivex.functions.Consumer;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/16 17:17
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunarticle.mine.fragment
 */
public class DynamicFragment extends BaseFragment< DynamicPresenterImp, DynamicModelImp > implements DynamicControl.View {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;
	private FrameLayout mContent;
	ViewPager mPager;
	SmartTabLayout mTab;
	ImageView moreButton;
	LinearLayout normalLayout;
	ImageView phoneButton;
	ImageView qqButton;
	LinearLayout unLoginLayout;
	ImageView weChatButton;
	private DynamicPagerAdapter mPagerAdapter;
	private DynamicFollowBangumiFragment mFollowBangumiFragment;
	private DynamicAcfunFragment mDynamicAcfunFragment;

	private int selectedPagePosition;


	public DynamicFragment() {
		// Required empty public constructor
	}

	public static DynamicFragment newInstance( String param1, String param2 ) {
		DynamicFragment fragment = new DynamicFragment();
		Bundle args = new Bundle();
		args.putString( ARG_PARAM1, param1 );
		args.putString( ARG_PARAM2, param2 );
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		if ( getArguments() != null ) {
			mParam1 = getArguments().getString( ARG_PARAM1 );
			mParam2 = getArguments().getString( ARG_PARAM2 );
		}
	}

	@Override
	protected void initToolBar() {
		BarUtils.setPaddingSmart( mTab );
	}

	@Override
	protected boolean isRegisterEvent() {
		return true;
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_dynamic_layout;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM( this, mModel );
	}

	private void initAction() {
		RxView.clicks( moreButton )//
				.throttleFirst( 500, TimeUnit.MILLISECONDS )//
				.subscribe( new Consumer< Object >() {
					@Override
					public void accept( Object o ) throws Exception {
						CC.obtainBuilder( "ComponentMine" ).setActionName( "startLoginActivity" ).build().call();
					}
				} );
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		mContent = findViewById( R.id.content );
		normalLayout = findViewById( R.id.normal_layout );
		mTab = findViewById( R.id.general_tab );
		mPager = findViewById( R.id.general_view_pager );
		unLoginLayout = findViewById( R.id.un_login_layout );
		moreButton = findViewById( R.id.iv_more_login );
		phoneButton = findViewById( R.id.iv_phone_login );
		qqButton = findViewById( R.id.iv_qq_login );
		weChatButton = findViewById( R.id.iv_wechat_login );
		initAction();

		boolean isUnLogin = SigninHelper.getInstance().isUnLogin();
		if ( isUnLogin ) {
			showLoginStatus( false );
			initViewpager();
		} else {
			showLoginStatus( true );
		}
	}

	@Override
	protected void lazyLoad() {
		loadData();
	}

	private void initViewpager() {
		mTab.setCustomTabView( R.layout.widget_home_tab_view, R.id.secondary_tab_text );
		mPagerAdapter = new DynamicPagerAdapter( getChildFragmentManager() );
		mFollowBangumiFragment = DynamicFollowBangumiFragment.newInstance( "", "" );
		mDynamicAcfunFragment = DynamicAcfunFragment.newInstance( "", "" );
		mPagerAdapter.add( mFollowBangumiFragment, getString( R.string.bangumi_detail_feed ) );
		mPagerAdapter.add( mDynamicAcfunFragment, getString( R.string.home_ac_dynamic ) );
		mPager.setAdapter( mPagerAdapter );
		mPager.setOffscreenPageLimit( 1 );
		int i = 0;
		mPager.setCurrentItem( 0 );
		mTab.setViewPager( mPager );
		while ( i < mPagerAdapter.getCount() ) {
			View view = mTab.getTabAt( i );
			if ( view instanceof TextView ) {
				TextView textView = ( TextView ) view;
				if ( i == 0 ) {
					textView.setAlpha( 1.0f );
				} else {
					textView.setAlpha( 0.8f );
				}
			}
			i++;
		}
		mPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged( int state ) {

			}

			@Override
			public void onPageScrolled( int position, float positionOffset, int positionOffsetPixels ) {

			}

			@Override
			public void onPageSelected( int position ) {
				selectedPagePosition = position;
				for (int i = 0; i < mPagerAdapter.getCount(); i++) {
					View view = mTab.getTabAt( i );
					if ( view instanceof TextView ) {
						TextView textView = ( TextView ) view;
						if ( i == position ) {
							textView.setAlpha( 1.0f );
						} else {
							textView.setAlpha( 0.8f );
						}
					}
				}
			}
		} );
	}

	private void showLoginStatus( boolean isUnLogin ) {
		if ( isUnLogin ) {
			normalLayout.setVisibility( View.GONE );
			unLoginLayout.setVisibility( View.VISIBLE );
		} else {
			normalLayout.setVisibility( View.VISIBLE );
			unLoginLayout.setVisibility( View.GONE );
		}
	}

	@Subscribe( threadMode = ThreadMode.MAIN_THREAD )
	public void loginStatus( EventBean eventBean ) {
		if ( eventBean.getTag() instanceof String && eventBean.getContent() instanceof String ) {
			String tag = ( String ) eventBean.getTag();
			String content = ( String ) eventBean.getContent();
			if ( TextUtils.equals( tag, Constants.LoginStatus ) && TextUtils.equals( content, Constants.LoginSuccess ) ) {
				showLoginStatus( false );
				initViewpager();
			} else if ( TextUtils.equals( tag, Constants.LogoutStatus ) && TextUtils.equals( content, Constants.LogoutSuccess ) ) {
				mPagerAdapter.clear();
				mFollowBangumiFragment = null;
				mDynamicAcfunFragment = null;
				mPager.setAdapter( null );
				mPagerAdapter = null;
				showLoginStatus( true );
			}

		}
	}

	@Override
	public void loadData() {
		boolean isUnLogin = SigninHelper.getInstance().isUnLogin();
//		if ( isUnLogin ) {
//			a( selectedPagePosition );
//		} else {
//			a( -1 );
//		}
//		MyRequestMap map = new MyRequestMap();
//		mPresenter.requestAllChannel( map );
	}

	@Override
	public void showLoading( String title, int type ) {
//		mViewSkeletonScreen = Skeleton.bind( mArticleViewPager )//
////				.shimmer( true )//
////				.duration( 1200 )//
////				.angle( 20 )//
//				.load( R.layout.view_loading_button )//
//				.show();
	}

	@Override
	public void stopLoading() {
//		if ( mViewSkeletonScreen != null && mViewSkeletonScreen.isShowing() ) {
//			mViewSkeletonScreen.hide();
//		}
	}

	@Override
	public void showErrorTip( String msg ) {
		ToastUtil.showError( msg );
	}

}
