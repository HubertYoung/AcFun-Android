package com.hubertyoung.acfun.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.billy.cc.core.component.CC;
import com.hubertyoung.acfun.R;
import com.hubertyoung.acfun.ui.fragment.PlaceHolderFragment;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.utils.BarUtils;
import com.hubertyoung.common.utils.CommonLog;

import java.util.ArrayList;

import butterknife.BindView;

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
public class MainActivity extends BaseActivity {

	@BindView( R.id.fl_container )
	FrameLayout mFlContainer;
	@BindView( R.id.bnb_main_view )
	BottomNavigationBar mBnbMainView;
	private ArrayList< BaseFragment > mFragments = new ArrayList<>();
	private BaseFragment mFragment;
	private long keyDownFirstTime;

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void initPresenter() {

	}
	@Override
	public void doBeforeSetContentView() {
		BarUtils.setStatusBarTranslucent(getWindow(), true);
		BarUtils.statusBarLightMode(getWindow(), true);
	}
	@Override
	public void initView( Bundle savedInstanceState ) {
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
		mBnbMainView.selectTab( 0 );
	}

	private void initFragment( Bundle savedInstanceState ) {
		mFragments.clear();
		mFragments.add( getFragment( "ComponentAcFunIndex", "getHomePageFragment" ) );
		mFragments.add( getFragment( "ComponentClassification", "getClassificationFragment" ) );
		mFragments.add( getFragment( "ComponentCart", "getCartRootFragment" ) );
		mFragments.add( getFragment( "ComponentMine", "getMineRootFragment" ) );
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
				changeFragment( position );
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

	private void changeFragment( int index ) {
		BaseFragment toFragment = getFragment( index );
		switchFragment( mFragment, toFragment );
	}


	/**
	 * 把对应的fragment绑定到acticity中
	 *
	 * @param fromFragment 原页面
	 * @param toFragment   现在点击的页面
	 */
	public void switchFragment( BaseFragment fromFragment, BaseFragment toFragment ) {

		CommonLog.loge( "TAG", fromFragment + "---" + toFragment );
		if ( mFragment != toFragment ) {//防止多次点击进入
			mFragment = toFragment;
			if ( toFragment != null ) {//不是一个页面了
				FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
				if ( !toFragment.isAdded() ) {
					//没有添加
					if ( fromFragment != null ) ft.hide( fromFragment );//隐藏页面
					//添加显示
					ft.add( R.id.fl_container, toFragment, toFragment.TAG ).commitAllowingStateLoss();
				} else {
					//如果添加了页面
					if ( fromFragment != null ) ft.hide( fromFragment ); //隐藏页面
					ft.show( toFragment ).commitAllowingStateLoss();//直接显示页面
				}
			}
		} else {
			long keyDownSecondTime = System.currentTimeMillis();
			if ( keyDownSecondTime - keyDownFirstTime > 0.5 * 1000 ) {
				keyDownFirstTime = System.currentTimeMillis();
				( ( BaseFragment ) fromFragment ).refreshData();
			}
		}
	}

	private BaseFragment getFragment( int position ) {
		if ( mFragments != null && mFragments.size() > 0 ) {
			return mFragments.get( position );
		}
		return null;
	}
}
