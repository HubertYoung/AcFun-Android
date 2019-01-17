package com.hubertyoung.common.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import com.hubertyoung.common.baserx.LiveBus
import com.hubertyoung.common.stateview.StateConstants
import com.hubertyoung.common.stateview.StateEntity
import com.hubertyoung.common.utils.TUtil
import java.util.*

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/7 11:15
 * @since:
 * @see com.hubertyoung.common.base.AbsLifecycleFragment
 */
abstract class AbsLifecycleFragment<VM : AbsViewModel<*>> : BaseFragment() {

	protected var mViewModel: VM? = null
	//
	protected lateinit var mStateEventKey: String

	protected lateinit var mStateEventTag: String

	private val events = ArrayList<Any>()
	/**
	 * ViewPager + fragment tag
	 *
	 * @return
	 */
	protected open val stateEventTag: String = ""

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
	override fun initView(state: Bundle?) {
		mViewModel = VMProviders(this, TUtil.getInstance(this, 0))
		if (null != mViewModel) {
			dataObserver()
			mStateEventKey = TAG
			mStateEventTag = stateEventTag
			events.add(StringBuilder(mStateEventKey).append(mStateEventTag).toString())
			LiveBus.getDefault().subscribe(mStateEventKey, mStateEventTag, StateEntity::class.java).observe(this, observer)
		}
	}

	/**
	 * create ViewModelProviders
	 *
	 * @return ViewModel
	 */
	fun VMProviders(fragment: Fragment, modelClass: Class<ViewModel>?): VM? {
		return if (modelClass == null) {
			null
		} else ViewModelProviders.of(fragment).get<ViewModel>(modelClass) as VM

	}

	protected open fun dataObserver() {

	}

	protected open fun <T> registerObserver(eventKey: String, tClass: Class<T>): MutableLiveData<T> {

		return registerObserver(eventKey, null, tClass)
	}

	protected open fun <T> registerObserver(eventKey: String, tag: String?, tClass: Class<T>): MutableLiveData<T> {
		val event: String = if (TextUtils.isEmpty(tag)) {
			eventKey
		} else {
			eventKey + tag
		}
		events.add(event)
		return LiveBus.getDefault().subscribe(eventKey, tag, tClass)
	}

	protected open fun showErrorLayout(result: String) {}

	protected open fun stopLoading() {

	}

	protected open fun showLoading(title: String) {

	}

	override fun onDestroyView() {
		super.onDestroyView()
		if (events.size > 0) {
			for (i in events.indices) {
				LiveBus.getDefault().clear(events[i])
			}
		}
	}
}