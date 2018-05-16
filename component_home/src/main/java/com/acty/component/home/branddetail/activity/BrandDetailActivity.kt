package com.acty.component.home.branddetail.activity

import android.os.Bundle
import com.acty.component.home.branddetail.control.BrandDetailControl
import com.acty.component_home.R
import com.gent.youxidandan.ddsocial.ui.mytest.model.TestActivity2ModelImp
import com.gent.youxidandan.ddsocial.ui.mytest.presenter.TestActivity2PresenterImp
import com.hubertyoung.common.base.BaseActivity
import com.hubertyoung.common.utils.BarUtils

/**
 * <br>
 * function:品牌详情
 * <p>
 * @author:HubertYoung
 * @date:2018/5/15 2018/5/15
 * @since:V1.0
 * @desc:com.acty.component.home.branddetail.activity
 */
class BrandDetailActivity : BaseActivity<TestActivity2PresenterImp, TestActivity2ModelImp>(), BrandDetailControl.View {

	override fun getLayoutId(): Int {
		return R.layout.home_activity_brand_detail
	}

	override fun initPresenter() {
		mPresenter.setVM(this, mModel)
	}

	override fun doBeforeSetContentView() {
		BarUtils.statusBarLightMode(this, true, 0f)
		BarUtils.immersiveStatusBar(window, 0f)
	}

	override fun initView(savedInstanceState: Bundle?) {
	}

	override fun loadData() {
	}

	override fun initToolBar() {
	}

	override fun showLoading(title: String?, type: Int) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun stopLoading() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun showErrorTip(msg: String?) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

}
