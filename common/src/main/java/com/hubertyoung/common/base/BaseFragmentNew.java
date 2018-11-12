package com.hubertyoung.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hubertyoung.common.baserx.RxManager;
import com.hubertyoung.common.utils.TUtil;
import com.hubertyoung.common.utils.log.CommonLog;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


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
public abstract class BaseFragmentNew< T extends BasePresenter, E extends BaseModel > extends Fragment {
	public String TAG = this.getClass().getSimpleName();
	protected ViewGroup rootView;
	public T mPresenter;
	public E mModel;
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
		mPresenter = TUtil.getNewInstance( this, 0 );
		mModel = TUtil.getNewInstance( this, 1 );
		if ( mPresenter != null ) {
			mPresenter.mContext = activity;
		}
		if ( isPrepared ) {
			initPresenter();
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

	/**
	 * 显示fragment 可返回
	 *
	 * @param fragment
	 */
	public void addViewFrame( BaseFragmentNew fragment, int framlayoutID ) {
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
		if ( mRxManager != null && isRegisterEvent() ) {
			mRxManager.mRxBus.unregister( this );
		}
		this.activity = null;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if ( mPresenter != null ) mPresenter.onDestroy();
		mRxManager.clear();
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


	public void loadData() {

	}

	public void refreshData() {

	}
}
