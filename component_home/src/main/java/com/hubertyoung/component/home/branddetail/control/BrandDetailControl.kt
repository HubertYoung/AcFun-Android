package com.hubertyoung.component.home.branddetail.control

import com.hubertyoung.component.home.entity.BrandDetailEntity
import com.hubertyoung.common.base.BaseModel
import com.hubertyoung.common.base.BasePresenter
import com.hubertyoung.common.base.BaseView
import com.hubertyoung.common.basebean.MyRequestMap
import io.reactivex.Observable

/**
 * <br>
 * function:
 * <p>
 * @author:HubertYoung
 * @date:2018/5/15 17:44
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.home.branddetail.control
 */
interface BrandDetailControl {

	interface Model : BaseModel {
		fun requestBrandDetail(map: MyRequestMap): Flowable<BrandDetailEntity>
	}

	interface View : BaseView {
		fun setBrandDetailInfo(brandDetailEntity: BrandDetailEntity)
	}

	abstract class Presenter : BasePresenter<View, Model>() {
		abstract fun requestBrandDetail(map: MyRequestMap)
	}
}