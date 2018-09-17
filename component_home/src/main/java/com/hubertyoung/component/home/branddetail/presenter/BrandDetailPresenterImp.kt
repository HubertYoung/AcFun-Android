package com.gent.youxidandan.ddsocial.ui.mytest.presenter

import com.hubertyoung.component.home.branddetail.control.BrandDetailControl
import com.hubertyoung.component.home.entity.BrandDetailEntity
import com.hubertyoung.component.home.entity.HomeIndexEntity
import com.hubertyoung.common.base.BaseActivity
import com.hubertyoung.common.basebean.MyRequestMap
import io.reactivex.annotations.NonNull
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
class BrandDetailPresenterImp : BrandDetailControl.Presenter() {
	override fun requestBrandDetail(map: MyRequestMap) {
		mView.showLoading("Loading...", 0)
		mRxManage.add(mModel.requestBrandDetail(map)
				.compose((mContext as BaseActivity<*, *>)
				.bindToLifecycle<BrandDetailEntity>())
				.subscribe(Consumer<BrandDetailEntity> { brandDetailEntity ->
					mView.stopLoading()
					mView.setBrandDetailInfo(brandDetailEntity)
				}, Consumer<Throwable> { throwable ->
					mView.stopLoading()
					mView.showErrorTip(throwable.message.toString())
				}))
	}
}