package com.hubertyoung.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.baserx.RxManager;
import com.hubertyoung.common.utils.CommonLog;
import com.hubertyoung.common.utils.TUtil;


/***************使用例子*********************/
//1.mvp模式
//public class SampleFragment extends BaseFragment<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
//    }
//
//    @Override
//    public void initView() {
//    }
//}
//2.普通模式
//public class SampleFragment extends BaseFragment {
//    @Override
//    public int getLayoutResource() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//
//    @Override
//    public void initView() {
//    }
//}
public abstract class BaseFragment< T extends BasePresenter, E extends BaseModel > extends com.trello.rxlifecycle2.components.support.RxFragment {
	public String TAG = this.getClass().getSimpleName();
	protected ViewGroup rootView;
	public T mPresenter;
	public E mModel;
	public RxManager mRxManager;
	// 标志位 标志已经初始化完成。
	protected boolean isPrepared;

	//标志位 fragment是否可见
	protected boolean isVisible = true;

	public FragmentActivity activity;
	private boolean mIsRegisterEvent = false;
//	protected View mStatusBarView;

	@Override
	public void onAttach( Activity activity ) {
		super.onAttach( activity );
		mRxManager = new RxManager();
		if ( mIsRegisterEvent ) {
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
		mPresenter = TUtil.getT( this, 0 );
		mModel = TUtil.getT( this, 1 );
		if ( mPresenter != null ) {
			mPresenter.mContext = this.getActivity();
		}
		activity = this.getActivity();
		return rootView;
	}

	@Override
	public void onViewCreated( View view, @Nullable Bundle savedInstanceState ) {
		super.onViewCreated( view, savedInstanceState );

		initToolBar();
		initPresenter();
		initView( savedInstanceState );
	}

//	protected void addStatusBar() {
//		if ( mStatusBarView == null ) {
//			mStatusBarView = new View( getContext() );
//			int screenWidth = getResources().getDisplayMetrics().widthPixels;
//			int statusBarHeight = BarUtils.getStatusBarHeight();
//			ViewGroup.LayoutParams params = new ViewGroup.LayoutParams( screenWidth, statusBarHeight );
//			mStatusBarView.setLayoutParams( params );
//			mStatusBarView.requestLayout();
//			if ( rootView != null ) rootView.addView( mStatusBarView, 0 );
//		}
//	}

	/**
	 * 初始化toolbar
	 */
	protected abstract void initToolBar();

	//获取布局文件
	protected abstract int getLayoutResource();

	//简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
	public abstract void initPresenter();

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

	protected <T extends View> T findViewById(@IdRes int id) {
		return rootView.findViewById(id);
	}
	/**
	 * 含有Bundle通过Class跳转界面
	 **/
	public void startActivityForResult( Class< ? > cls, Bundle bundle, int requestCode ) {
		Intent intent = new Intent();
		intent.setClass( getActivity(), cls );
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
		intent.setClass( getActivity(), cls );
		if ( bundle != null ) {
			intent.putExtras( bundle );
		}
		startActivity( intent );
	}

	/**
	 * 显示fragment 可返回
	 *
	 * @param fragment
	 */
	public void addViewFrame( BaseFragment fragment, int framlayoutID ) {
		FragmentTransaction fragmentTransaction = getFragmentTransaction();
		fragmentTransaction.replace( framlayoutID, fragment );
		fragmentTransaction.addToBackStack( fragment.TAG );
		fragmentTransaction.commitAllowingStateLoss();
	}

	/**
	 * 获取fragment beginTransaction
	 *
	 * @return
	 */
	public FragmentTransaction getFragmentTransaction() {
		return activity.getSupportFragmentManager().beginTransaction();
	}

	/**
	 * 获取上下文
	 *
	 * @return
	 */
	public Context getApplicationContext() {

		return this.activity == null ? ( getActivity() == null ? CommonApplication.getAppContext() : getActivity().getApplicationContext() ) : this.activity.getApplicationContext();
	}

	public FragmentManager getSupportFragmentManager( FragmentActivity activity ) {
		return activity.getSupportFragmentManager();
	}

	protected void finish() {
		int num = getSupportFragmentManager( getActivity() ).getBackStackEntryCount();
		if ( num > 1 ) {
			getSupportFragmentManager( getActivity() ).popBackStack();
		} else {
			activity.finish();
		}
	}

	protected void finish( String tag ) {
		int num = getSupportFragmentManager( getActivity() ).getBackStackEntryCount();
		if ( num > 0 ) {
			getSupportFragmentManager( getActivity() ).popBackStack( tag, 0 );
		} else {
			activity.finish();
		}
	}

	protected void finishAll() {
		try {
			int num = getSupportFragmentManager( getActivity() ).getBackStackEntryCount();
			activity.finish();
			for (int i = 0; i < num; i++) {
				//			FragmentManager.BackStackEntry backstatck = getSupportFragmentManager(getSupportActivity()).getBackStackEntryAt(i);
				getSupportFragmentManager( getActivity() ).popBackStack();
			}
		} catch ( Exception e ) {
			CommonLog.loge( e.getMessage().toString() );
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if ( mRxManager != null && mIsRegisterEvent ) {
			mRxManager.mRxBus.unregister( this );
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if ( mPresenter != null ) mPresenter.onDestroy();
		mRxManager.clear();
	}

	/**
	 * Fragment数据的懒加载
	 */
	@Override
	public void setUserVisibleHint( boolean isVisibleToUser ) {

		super.setUserVisibleHint( isVisibleToUser );
		if ( getUserVisibleHint() ) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;
			onInvisible();
		}
	}


	protected void onVisible() {
		lazyLoad();
	}


	protected void lazyLoad() {
	}


	protected void onInvisible() {
	}


	public void loadData() {

	}

	public void refreshData() {

	}

	protected int getColor( @ColorRes int colorResId ) {
		return ContextCompat.getColor( getApplicationContext(), colorResId );
	}
}
