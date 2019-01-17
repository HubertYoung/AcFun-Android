package com.hubertyoung.common.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hubertyoung.common.baserx.RxManager

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/7 10:48
 * @since:
 * @see com.hubertyoung.common.base.BaseFragment
 */
abstract class BaseFragment : Fragment() {
	open var TAG = this.javaClass.simpleName
	private lateinit var rootView: ViewGroup
	protected open var mRxManager: RxManager? = null
	// 标志位 标志已经初始化完成。
	var isPrepared = true;

	protected lateinit var mActivity: FragmentActivity

	override fun onAttach(context: Context) {
		super.onAttach(context)
		mActivity = context as FragmentActivity
		mRxManager = RxManager()
		if (isRegisterEvent()) {
			mRxManager?.mRxBus?.register(this)
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		rootView = inflater.inflate(getLayoutResource(), container, false) as ViewGroup
		val parent = rootView.parent
		if (parent != null) {
			(parent as ViewGroup).removeView(rootView)
		}
		if (isPrepared) {
			initView(savedInstanceState)
			initToolBar()
		}

		return rootView
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val isVis = isHidden || userVisibleHint
		if (isVis && isPrepared) {
			lazyLoad()
			isPrepared = false
		}
	}

	/**
	 * 是否开启事件订阅
	 *
	 * @return
	 */
	protected open fun isRegisterEvent(): Boolean {
		return false
	}

	/**
	 * 初始化toolbar
	 */
	protected abstract fun initToolBar()

	//获取布局文件
	protected abstract fun getLayoutResource(): Int

	//初始化view
	protected abstract fun initView(savedInstanceState: Bundle?)


	/**
	 * 通过Class跳转界面
	 */
	protected open fun startActivity(cls: Class<*>) {
		startActivity(cls, null)
	}

	/**
	 * 通过Class跳转界面
	 */
	protected open fun startActivityForResult(cls: Class<*>, requestCode: Int) {
		startActivityForResult(cls, null, requestCode)
	}

	protected fun <V : View> findViewById(@IdRes id: Int): V {
		return rootView.findViewById(id)
	}

	/**
	 * 含有Bundle通过Class跳转界面
	 */
	protected open fun startActivityForResult(cls: Class<*>, bundle: Bundle?, requestCode: Int) {
		val intent = Intent()
		intent.setClass(mActivity, cls)
		if (bundle != null) {
			intent.putExtras(bundle)
		}
		startActivityForResult(intent, requestCode)
	}

	/**
	 * 含有Bundle通过Class跳转界面
	 */
	fun startActivity(cls: Class<*>, bundle: Bundle?) {
		val intent = Intent()
		intent.setClass(mActivity, cls)
		if (bundle != null) {
			intent.putExtras(bundle)
		}
		startActivity(intent)
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

	override fun onDestroy() {
		super.onDestroy()
		if (mRxManager != null && isRegisterEvent()) {
			mRxManager?.mRxBus?.unregister(this)
		}
//		this.mActivity = null
	}

	override fun onDestroyView() {
		super.onDestroyView()
		if (mRxManager != null) {
			mRxManager?.clear()
		}
//		this.mActivity = null
	}

	override fun onHiddenChanged(hidden: Boolean) {
		super.onHiddenChanged(hidden)
		if (!hidden) {
			onVisible()
		} else {
			onInvisible()
		}
	}

	override fun setUserVisibleHint(isVisibleToUser: Boolean) {
		super.setUserVisibleHint(isVisibleToUser)
		if (isVisibleToUser) {
			onVisible()
		} else {
			onInvisible()
		}
	}


	fun onVisible() {
		if (isPrepared && isResumed) {
			lazyLoad()
			isPrepared = false
		}
	}


	protected open fun lazyLoad() {}


	protected open fun onInvisible() {}

}