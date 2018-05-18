package com.acty.component.home.branddetail.control

import com.acty.component.home.entity.BrandDetailBodyEntity
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
 * @desc:com.acty.component.home.branddetail.control
 */
interface BrandDetailListControl {

	interface Model : BaseModel {
		abstract fun requestBrandDetailList(map: MyRequestMap): Observable<BrandDetailBodyEntity>
	}

	interface View : BaseView {
		fun setBrandDetailListInfo(brandDetailBodyEntity: BrandDetailBodyEntity)
	}

	abstract class Presenter : BasePresenter<View, Model>() {
		abstract fun requestBrandDetailList(map: MyRequestMap)
	}
}