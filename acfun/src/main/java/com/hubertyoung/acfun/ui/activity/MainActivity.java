package com.hubertyoung.acfun.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.billy.cc.core.component.CC;
import com.hubertyoung.acfun.R;
import com.hubertyoung.acfun.ui.fragment.PlaceHolderFragment;
import com.hubertyoung.common.base.AbsLifecycleActivity;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.utils.bar.BarUtils;

import java.util.ArrayList;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/8/30 19:39
 * @since:V1.0.0
 * @desc:com.hubertyoung.acfun.ui.activity
 */
public class MainActivity extends AbsLifecycleActivity {

	private FrameLayout mFlContainer;
	private BottomNavigationBar mBnbMainView;
	private ArrayList< BaseFragment > mFragments = new ArrayList<>();
	private BaseFragment mFragment;
	private long keyDownFirstTime;
	/**
	 * 上一个tab索引
	 */
	private int prePosition;

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void doBeforeSetContentView() {
		BarUtils.setStatusBarTranslucent( getWindow(), true );
//		BarUtils.statusBarLightMode(getWindow(), true);
	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		super.initView( savedInstanceState );
		mFlContainer = findViewById( R.id.fl_container );
		mBnbMainView = findViewById( R.id.bnb_main_view );

		initFragment( savedInstanceState );

		mBnbMainView.clearAll();

		mBnbMainView.setMode( BottomNavigationBar.MODE_FIXED );
		mBnbMainView.setBackgroundStyle( BottomNavigationBar.BACKGROUND_STYLE_DEFAULT );
		mBnbMainView.addItem( new BottomNavigationItem( R.drawable.icon_videos_selected, getString( R.string.activity_main_video ) ).setInactiveIconResource( R.drawable.icon_videos ) )
				.addItem( new BottomNavigationItem( R.drawable.icon_article_selected, getString( R.string.activity_main_article ) ).setInactiveIconResource( R.drawable.icon_article ) )
				.addItem( new BottomNavigationItem( R.drawable.icon_dynamic_selected, getString( R.string.activity_main_dynamic ) ).setInactiveIconResource( R.drawable.icon_dynamic ) )
				.addItem( new BottomNavigationItem( R.drawable.icon_mine_selected, getString( R.string.activity_main_mine ) ).setInactiveIconResource( R.drawable.icon_mine ) )
				.setActiveColor( R.color.them_color )
				.setInActiveColor( R.color.gray )
				.initialise();
		initAction();
//		mBnbMainView.selectTab( 0 );

	}

	@Override
	protected void stopLoading() {

	}

	@Override
	protected void showLoading( String title ) {

	}

	private void initFragment( Bundle savedInstanceState ) {
//		mFragments.clear();
//		mFragments.add( getFragment( "ComponentAcFunIndex", "getHomePageFragment" ) );
//		mFragments.add( getFragment( "ComponentArticle", "getArticleFragment" ) );
//		mFragments.add( getFragment( "ComponentDynamic", "getDynamicFragment" ) );
//		mFragments.add( getFragment( "ComponentMine", "getMineRootFragment" ) );

		BaseFragment firstFragment = findFragment( getFragment( "ComponentAcFunIndex", "getHomePageFragment" ).getClass() );
		if ( firstFragment == null ) {
			mFragments.add( getFragment( "ComponentAcFunIndex", "getHomePageFragment" ) );
			mFragments.add( getFragment( "ComponentArticle", "getArticleFragment" ) );
			mFragments.add( getFragment( "ComponentDynamic", "getDynamicFragment" ) );
			mFragments.add( getFragment( "ComponentMine", "getMineRootFragment" ) );

			loadMultipleRootFragment( R.id.fl_container, 0,//
					mFragments.get( 0 ),//
					mFragments.get( 1 ),//
					mFragments.get( 2 ),//
					mFragments.get( 3 ) );
		} else {
			// 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
			if ( mFragments != null ) {
				mFragments.clear();
			} else {
				mFragments = new ArrayList<>();
			}
			// 这里我们需要拿到mFragments的引用
			mFragments.add( firstFragment );
			mFragments.add( findFragment( getFragment( "ComponentArticle", "getArticleFragment" ).getClass() ) );
			mFragments.add( findFragment( getFragment( "ComponentDynamic", "getDynamicFragment" ).getClass() ) );
			mFragments.add( findFragment( getFragment( "ComponentMine", "getMineRootFragment" ).getClass() ) );

		}
	}

	/**
	 * 根据组件名和动作名获取对应fragment，如果没有加载相应组件返回PlaceholderFragment
	 *
	 * @param componentName 组件名
	 * @param actionName    动作名
	 * @return 对应的fragment
	 */
	private BaseFragment getFragment( String componentName, String actionName ) {
		Fragment fragment = CC.obtainBuilder( componentName ).setActionName( actionName ).build().call().getDataItem( "fragment" );
		if ( fragment == null ) {
			fragment = PlaceHolderFragment.newInstance( componentName, actionName );
		}
		return ( BaseFragment ) fragment;
	}

	private void initAction() {
		mBnbMainView.setTabSelectedListener( new BottomNavigationBar.OnTabSelectedListener() {
			@Override
			public void onTabSelected( int position ) {
				showHideFragment( mFragments.get( position ), mFragments.get( prePosition ) );
				prePosition = position;
			}

			@Override
			public void onTabUnselected( int position ) {
			}

			@Override
			public void onTabReselected( int position ) {

			}
		} );
	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {

	}
}
