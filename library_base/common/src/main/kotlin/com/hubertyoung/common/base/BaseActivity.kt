package com.hubertyoung.common.base

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.stetho.common.LogUtil
import com.hubertyoung.base.bean.EnvironmentBean
import com.hubertyoung.base.bean.ModuleBean
import com.hubertyoung.base.listener.OnEnvironmentChangeListener
import com.hubertyoung.baseplatform.AuthorizeSDK
import com.hubertyoung.baseplatform.ShareSDK
import com.hubertyoung.common.BuildConfig
import com.hubertyoung.common.R
import com.hubertyoung.common.baserx.RxManager
import com.hubertyoung.common.image.fresco.ImageLoaderUtil
import com.hubertyoung.common.utils.LoadingThemeUtil
import com.hubertyoung.common.utils.activitymanager.AppActivityManager
import com.hubertyoung.common.utils.bar.BarUtils
import com.hubertyoung.common.utils.log.CommonLog
import com.hubertyoung.common.utils.os.AppUtils
import com.hubertyoung.component_skeleton.skeleton.ViewReplacer

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/4 15:17
 * @since:
 * @see com.hubertyoung.common.base.BaseActivity
 */
abstract class BaseActivity : AppCompatActivity(), OnEnvironmentChangeListener {
	open var TAG = this.javaClass.simpleName
	companion object {
		private val SAVED_STATE_STATUS_BAR_TRANSLUCENT = "saved_state_status_bar_translucent"
	}

	protected lateinit var mContext: BaseActivity
	protected open var mRxManager: RxManager ?= null
	private var isConfigChange = false
	private var statusBarTranslucent: Boolean = false

	protected var mViewReplacer: ViewReplacer? = null
	protected var mLoadingLayout: View? = null
	protected var mErrorLayout: View? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		isConfigChange = false
		mRxManager = RxManager()
		if (isRegisterEvent()) {
			mRxManager?.mRxBus?.register(this)
		}
		// 把actvity放到application栈中管理
		AppActivityManager.getAppManager().addActivity(this)
		// 无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE)
		// 设置竖屏
		requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

		if (savedInstanceState != null) {
			statusBarTranslucent = savedInstanceState.getBoolean(SAVED_STATE_STATUS_BAR_TRANSLUCENT)
			BarUtils.setStatusBarTranslucent(window, statusBarTranslucent)
		}

		doBeforeSetContentView()
		if (getLayoutId() != 0) {
			setContentView(getLayoutId())
			mContext = this
			initView(savedInstanceState)
			//初始化ToolBar
			initToolBar()
			if (isNeedRefresh()) {
				if (refreshContentView() == null) {
					throw RuntimeException("If isNeedRefresh is true,Then refreshContentView is not null")
				}
				mViewReplacer = ViewReplacer(refreshContentView())
				showLoadingLayout()
			}
//			EnvironmentSwitcher.addOnEnvironmentChangeListener(this)
		} else {
			LogUtil.e("--->bindLayout() return 0")
		}
	}

	open fun refreshContentView(): View? {
		return null
	}

	open fun isNeedRefresh(): Boolean {
		return false
	}

	private fun showLoadingLayout() {
		if (mViewReplacer != null) {
			if (mLoadingLayout == null) {
				mLoadingLayout = LayoutInflater.from(mContext).inflate(R.layout.widget_loading_holder, null)
			}
			mViewReplacer!!.replace(mLoadingLayout)

			if (mViewReplacer!!.currentView != null) {
				mViewReplacer!!.currentView.visibility = View.VISIBLE
				val simpleDraweeView = mViewReplacer!!.currentView.findViewById<SimpleDraweeView>(R.id.widget_loading_holder_gif)
				simpleDraweeView.hierarchy.setPlaceholderImage(LoadingThemeUtil.getPageLoadingImages())
				ImageLoaderUtil.loadNetImage(LoadingThemeUtil.getPageLoadingFileImages(), simpleDraweeView)
			}
		}
	}

	protected fun showErrorLayout(result: String) {
		if (mViewReplacer != null) {
			if (mErrorLayout == null) {
				mErrorLayout = LayoutInflater.from(this).inflate(R.layout.widget_error_holder, null)
			}
			val tvErrorContent = mErrorLayout!!.findViewById<TextView>(R.id.tv_error_content)
			if (!TextUtils.isEmpty(result)) {
				tvErrorContent.text = result
			} else {
				tvErrorContent.setText(R.string.error_page_title)
			}
			mViewReplacer!!.replace(mErrorLayout)
			if (mViewReplacer!!.currentView != null) {
				mViewReplacer!!.currentView.visibility = View.VISIBLE
				val refreshClick = mViewReplacer!!.currentView.findViewById<TextView>(R.id.refresh_click)
				refreshClick.setOnClickListener { loadData() }
			}
		} else {
			stopLoading()
		}
	}

	protected open fun stopLoading() {
		if (mViewReplacer != null) {
			mViewReplacer!!.restore()
		}
	}

	/**
	 * 设置layout前配置
	 */
	open fun doBeforeSetContentView() {

	}

	/*********************子类实现*****************************/
	/**
	 * 获取布局文件
	 */
	protected abstract fun getLayoutId(): Int

	/**
	 * 初始化view
	 */
	abstract fun initView(savedInstanceState: Bundle?)

	protected abstract fun loadData()

	/**
	 * 初始化toolBar
	 */
	abstract fun initToolBar()

	/**
	 * 是否开启事件订阅
	 *
	 * @return
	 */
	protected fun isRegisterEvent(): Boolean {
		return false
	}

//	protected  void onStateRefresh(){
//
//	}

	/**
	 * 通过Class跳转界面
	 */
	fun startActivity(cls: Class<*>) {
		startActivity(cls, null)
	}

	/**
	 * 通过Class跳转界面
	 */
	fun startActivityForResult(cls: Class<*>, requestCode: Int) {
		startActivityForResult(cls, null, requestCode)
	}

	/**
	 * 含有Bundle通过Class跳转界面
	 */
	fun startActivityForResult(cls: Class<*>, bundle: Bundle?, requestCode: Int) {
		val intent = Intent()
		intent.setClass(this, cls)
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
		intent.setClass(this, cls)
		if (bundle != null) {
			intent.putExtras(bundle)
		}
		startActivity(intent)
	}

	override fun onResume() {
		super.onResume()
		//debug版本不统计crash
		if (!BuildConfig.DEBUG) {
			//友盟统计
			//			MobclickAgent.onResume(this);
		}
	}

	override fun onPause() {
		super.onPause()
		//debug版本不统计crash
		if (!BuildConfig.DEBUG) {
			//友盟统计
			//			MobclickAgent.onPause(this);
		}
	}

	override fun onConfigurationChanged(newConfig: Configuration) {
		super.onConfigurationChanged(newConfig)
		isConfigChange = true
	}

	override fun onDestroy() {
//		EnvironmentSwitcher.removeOnEnvironmentChangeListener(this)
		super.onDestroy()
		CommonLog.loge("activity: $TAG onDestroy()")
		if (isRegisterEvent()) mRxManager?.mRxBus?.unregister(this)
		mRxManager?.clear()
		if (!isConfigChange) {
			AppActivityManager.getAppManager().finishActivity(this)
		}

	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.putBoolean(SAVED_STATE_STATUS_BAR_TRANSLUCENT, statusBarTranslucent)
	}

	override fun onEnvironmentChanged(module: ModuleBean, oldEnvironment: EnvironmentBean, newEnvironment: EnvironmentBean) {
		if (AppUtils.isDebuggable()) {
			val str = "${module.name} 由 ${oldEnvironment.name} 环境，Url= ${oldEnvironment.url},切换为 ${newEnvironment.name} 环境，Url=${newEnvironment.url}"
			Log.e(TAG, str)
			Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
		}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		AuthorizeSDK.onHandleResult(this, requestCode, resultCode, data)
		ShareSDK.onHandleResult(this, requestCode, resultCode, data)
		super.onActivityResult(requestCode, resultCode, data)
	}
}