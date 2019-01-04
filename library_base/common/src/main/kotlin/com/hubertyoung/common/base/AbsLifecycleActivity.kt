package com.hubertyoung.common.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.hubertyoung.common.baserx.LiveBus
import com.hubertyoung.common.stateview.StateConstants
import com.hubertyoung.common.stateview.StateEntity
import com.hubertyoung.common.utils.TUtil
import java.util.*

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/4 17:16
 * @since:
 * @see com.hubertyoung.common.base.AbsLifecycleActivity
 */
abstract class AbsLifecycleActivity<VM : AbsViewModel<*>> : BaseActivity() {

	open var mViewModel: VM? = null
	//
	lateinit var mStateEventKey: String

	lateinit var mStateEventTag: String

	private val events = ArrayList<String>()

	override fun initView(savedInstanceState: Bundle?) {
		mViewModel = VMProviders(this, TUtil.getInstance(this, 0))
		if (null != mViewModel) {
			dataObserver()
			mStateEventKey = TAG
			mStateEventTag = getStateEventTag()
			events.add(StringBuilder(mStateEventKey).append(mStateEventTag).toString())
			LiveBus.getDefault().subscribe(mStateEventKey, mStateEventTag, StateEntity::class.java).observe(this, observer)
		}
	}

	/**
	 * ViewPager + fragment tag
	 *
	 * @return
	 */
	protected fun getStateEventTag(): String {
		return ""
	}

	protected fun VMProviders(activity: AppCompatActivity, modelClass: Class<ViewModel>?): VM? {
		return if (modelClass == null) {
			null
		} else ViewModelProviders.of(activity).get<ViewModel>(modelClass) as VM

	}

	protected open fun dataObserver() {

	}

	protected open fun showLoading(title: String) {

	}

	protected fun <T> registerObserver(eventKey: String, tClass: Class<T>): MutableLiveData<T> {

		return registerObserver(eventKey, null, tClass)
	}

	protected fun <T> registerObserver(eventKey: String, tag: String?, tClass: Class<T>): MutableLiveData<T> {
		val event: String = if (TextUtils.isEmpty(tag)) {
			eventKey
		} else {
			eventKey + tag
		}
		events.add(event)
		return LiveBus.getDefault().subscribe(eventKey, tag, tClass)
	}


	protected var observer: Observer<StateEntity> = Observer<StateEntity> { stateEntity ->
		val state = stateEntity!!.code
		if (!TextUtils.isEmpty(state)) {
			when (state) {
				StateConstants.ERROR_STATE.code -> showErrorLayout(stateEntity.result)
				StateConstants.NET_WORK_STATE.code -> {
				}
				StateConstants.LOADING_STATE.code -> showLoading("")
				StateConstants.SUCCESS_STATE.code -> stopLoading()
			}
		}
	}
}
