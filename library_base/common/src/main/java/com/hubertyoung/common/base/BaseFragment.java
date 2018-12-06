package com.hubertyoung.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hubertyoung.common.baserx.RxManager;

import me.yokeyword.fragmentation.SupportFragment;


public abstract class BaseFragment extends SupportFragment {
	public String TAG = this.getClass().getSimpleName();
	protected ViewGroup rootView;
	public RxManager mRxManager;
	// 标志位 标志已经初始化完成。
	protected boolean isPrepared = true;;

	public FragmentActivity activity;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.activity = (FragmentActivity) context;
		mRxManager = new RxManager();
		if ( isRegisterEvent() ) {
			mRxManager.mRxBus.register( this );
		}
	}

	@Nullable
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
		if ( rootView == null ) rootView = ( ViewGroup ) inflater.inflate( getLayoutResource(), container, false );
		ViewGroup parent = ( ViewGroup ) rootView.getParent();
		if ( parent != null ) {
			parent.removeView( rootView );
		}
		if ( isPrepared ) {
			initView( savedInstanceState );
			initToolBar();
		}

		return rootView;
	}

	@Override
	public void onViewCreated( View view, @Nullable Bundle savedInstanceState ) {
		super.onViewCreated( view, savedInstanceState );
		boolean isVis = isHidden() || getUserVisibleHint();
		if (isVis && isPrepared) {
			lazyLoad();
			isPrepared = false;
		}
	}

	/**
	 * 是否开启事件订阅
	 *
	 * @return
	 */
	protected boolean isRegisterEvent() {
		return false;
	}
	/**
	 * 初始化toolbar
	 */
	protected abstract void initToolBar();

	//获取布局文件
	protected abstract int getLayoutResource();

	//初始化view
	protected abstract void initView( Bundle savedInstanceState );


	/**
	 * 通过Class跳转界面
	 **/
	public void startActivity( Class< ? > cls ) {
		startActivity( cls, null );
	}

	/**
	 * 通过Class跳转界面
	 **/
	public void startActivityForResult( Class< ? > cls, int requestCode ) {
		startActivityForResult( cls, null, requestCode );
	}

	protected <V extends View> V findViewById(@IdRes int id) {
		return rootView.findViewById(id);
	}
	/**
	 * 含有Bundle通过Class跳转界面
	 **/
	public void startActivityForResult( Class< ? > cls, Bundle bundle, int requestCode ) {
		Intent intent = new Intent();
		intent.setClass( activity, cls );
		if ( bundle != null ) {
			intent.putExtras( bundle );
		}
		startActivityForResult( intent, requestCode );
	}

	/**
	 * 含有Bundle通过Class跳转界面
	 **/
	public void startActivity( Class< ? > cls, Bundle bundle ) {
		Intent intent = new Intent();
		intent.setClass( activity, cls );
		if ( bundle != null ) {
			intent.putExtras( bundle );
		}
		startActivity( intent );
	}

//	/**
//	 * 显示fragment 可返回
//	 *
//	 * @param fragment
//	 */
//	public void addViewFrame( BaseFragment fragment, int framlayoutID ) {
//		FragmentTransaction fragmentTransaction = getFragmentTransaction();
//		fragmentTransaction.replace( framlayoutID, fragment );
//		fragmentTransaction.addToBackStack( fragment.TAG );
//		fragmentTransaction.commitAllowingStateLoss();
//	}
//
//	/**
//	 * 获取fragment beginTransaction
//	 *
//	 * @return
//	 */
//	public FragmentTransaction getFragmentTransaction() {
//		return activity.getSupportFragmentManager().beginTransaction();
//	}
//
//	public FragmentManager getSupportFragmentManager( FragmentActivity activity ) {
//		return activity.getSupportFragmentManager();
//	}
//
//	protected void finish() {
//		int num = getSupportFragmentManager( getActivity() ).getBackStackEntryCount();
//		if ( num > 1 ) {
//			getSupportFragmentManager( getActivity() ).popBackStack();
//		} else {
//			activity.finish();
//		}
//	}
//
//	protected void finish( String tag ) {
//		int num = getSupportFragmentManager( getActivity() ).getBackStackEntryCount();
//		if ( num > 0 ) {
//			getSupportFragmentManager( getActivity() ).popBackStack( tag, 0 );
//		} else {
//			activity.finish();
//		}
//	}
//
//	protected void finishAll() {
//		try {
//			int num = getSupportFragmentManager( getActivity() ).getBackStackEntryCount();
//			activity.finish();
//			for (int i = 0; i < num; i++) {
//				//			FragmentManager.BackStackEntry backstatck = getSupportFragmentManager(getSupportActivity()).getBackStackEntryAt(i);
//				getSupportFragmentManager( getActivity() ).popBackStack();
//			}
//		} catch ( Exception e ) {
//			CommonLog.loge( e.getMessage().toString() );
//		}
//	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if ( mRxManager != null && isRegisterEvent() ) {
			mRxManager.mRxBus.unregister( this );
		}
		this.activity = null;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if(mRxManager != null) {
			mRxManager.clear();
		}
		this.activity = null;
	}
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) {
			onVisible();
		} else {
			onInvisible();
		}
	}
	@Override
	public void setUserVisibleHint( boolean isVisibleToUser ) {
		super.setUserVisibleHint( isVisibleToUser );
		if ( isVisibleToUser) {
			onVisible();
		} else {
			onInvisible();
		}
	}


	protected void onVisible() {
		if (isPrepared && isResumed()) {
			lazyLoad();
			isPrepared = false;
		}
	}


	protected void lazyLoad() {
	}


	protected void onInvisible() {
	}

}
