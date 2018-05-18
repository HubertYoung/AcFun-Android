package com.gent.youxidandan.ddsocial.ui.mytest.presenter

import com.acty.component.home.branddetail.control.BrandDetailListControl
import com.acty.component.home.entity.BrandDetailBodyEntity
import com.acty.component.home.entity.BrandDetailEntity
import com.hubertyoung.common.base.BaseActivity
import com.hubertyoung.common.basebean.MyRequestMap
import io.reactivex.functions.Consumer

/**
 * <br>
 * function:
 * <p>
 * @author:HubertYoung
 * @date:2018/5/15 2018/5/15
 * @since:V1.0
 * @desc:com.gent.youxidandan.ddsocial.ui.mytest.presenter
 */
class BrandDetailListPresenterImp : BrandDetailListControl.Presenter() {
	override fun requestBrandDetailList(map: MyRequestMap) {
		mView.showLoading("Loading...", 0)
		mRxManage.add(mModel.requestBrandDetailList(map)
				.compose((mContext as BaseActivity<*, *>).bindToLifecycle<BrandDetailBodyEntity>())
				.subscribe(Consumer<BrandDetailBodyEntity> { brandDetailBodyEntity ->
					mView.stopLoading()
					mView.setBrandDetailListInfo(brandDetailBodyEntity)
				}, Consumer<Throwable> { throwable ->
					mView.stopLoading()
					mView.showErrorTip(throwable.message.toString())
				}))
	}
}