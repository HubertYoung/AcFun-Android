package com.acty.component.home.branddetail.control

import com.hubertyoung.common.base.BaseModel
import com.hubertyoung.common.base.BasePresenter
import com.hubertyoung.common.base.BaseView

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

	interface Model : BaseModel

	interface View : BaseView

	abstract class Presenter : BasePresenter<View, Model>()
}