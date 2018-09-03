package com.acty.litemall.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.acty.litemall.R;
import com.acty.litemall.ui.fragment.PlaceHolderFragment;
import com.billy.cc.core.component.CC;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.utils.BarUtils;
import com.hubertyoung.common.utils.CommonLog;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * <br>
 * function:首页
 * <p>
 *
 * @author:Yang
 * @date:2018/5/8 3:56 PM
 * @since:V1.0
 * @desc:com.hubertyoung.welcome.commonlibrary
 */
public class MainActivity extends BaseActivity {

	@BindView( R.id.fl_container )
	FrameLayout mFlContainer;
	@BindView( R.id.ll_home_page )
	LinearLayout mLlHomePage;
	@BindView( R.id.ll_home_classification )
	LinearLayout mLlHomeClassification;
	@BindView( R.id.ll_home_cart )
	LinearLayout mLlHomeCart;
	@BindView( R.id.ll_home_mine )
	LinearLayout mLlHomeMine;
	private BaseFragment mFragment;
	private int position = 0;
	private ArrayList< BaseFragment > mFragments = new ArrayList<>();
	private long keyDownFirstTime;
	private ArrayList< View > bottomView = new ArrayList<>(  );

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void doBeforeSetContentView() {
		BarUtils.statusBarLightMode( this, true );
	}

	@Override
	public void initView( Bundle savedInstanceState ) {
//		if (!CC.hasComponent("ComponentUser")) {
//			// 没有登录也没有用户组件
//			ToastUtil.showError("没有登录组件");
//		} else if (!CacheDataSource.getLoginState()) {
//			// 没有登录但有登录组件，先进行登录
//			// 跳转用户组件-登录界面
//			ToastUtil.showWarning("请先登录");
//			CC.obtainBuilder("ComponentUser")
//					.setActionName("toLo1ginActivityClearTask")
//					.build()
//					.call();
//		} else {
		initBottomView();
		initFragment( savedInstanceState );
		initAction();
//		}
	}

	private void initAction() {
		for (int i = 0; i < bottomView.size(); i++) {
			int finalI = i;
			RxView.clicks( bottomView.get( i ) )
					.throttleFirst( 500, TimeUnit.MILLISECONDS )
					.subscribeOn( AndroidSchedulers.mainThread() )
					.subscribe( new Consumer< Object >() {
						@Override
						public void accept( Object o ) throws Exception {
							position = finalI;
							changeFragment( position );
						}
					} );
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
		if ( fragment == null) {
			fragment = PlaceHolderFragment.newInstance( componentName, actionName);
		}
		return ( BaseFragment )fragment;
	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {

	}

	private void initFragment( Bundle savedInstanceState ) {
		mFragments.clear();
		mFragments.add( getFragment( "ComponentIndex", "getIndexRootFragment" ) );
		mFragments.add( getFragment( "ComponentClassification", "getClassificationFragment" ) );
		mFragments.add( getFragment( "ComponentCart", "getCartRootFragment" ) );
		mFragments.add( getFragment( "ComponentMine", "getMineRootFragment" ) );
		changeFragment( position );
	}

	private void changeFragment( int index ) {
		BaseFragment toFragment = getFragment( index );
		changeBottomUi( index );
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

	private void changeBottomUi( int index ) {
		for (int j = 0; j < bottomView.size(); j++) {
			bottomView.get( j ).setSelected( index == j );
		}
	}

	private void initBottomView() {
		bottomView.clear();
		bottomView.add( mLlHomePage );
		bottomView.add( mLlHomeClassification );
		bottomView.add( mLlHomeCart );
		bottomView.add( mLlHomeMine );
	}
}
