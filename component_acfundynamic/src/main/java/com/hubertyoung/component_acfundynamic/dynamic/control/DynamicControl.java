package com.hubertyoung.component_acfundynamic.dynamic.control;


import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/16 17:17
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunarticle.mine.control
 */
public interface DynamicControl {

	interface Model extends BaseModel {
	}

	interface View extends BaseView {
	}

	abstract class Presenter extends BasePresenter< View, Model > {
	}
}
