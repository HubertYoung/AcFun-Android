package com.acty.component.acfunvideo.index.control;


import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;

/**
 * <br>
 * function:首页Control
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 11:17 AM
 * @since:V1.0
 * @desc:com.hubertyoung.component.home.index.control
 */
public interface IndexRootControl {

	interface Model extends BaseModel {
	}

	interface View extends BaseView {
	}

	abstract class Presenter extends BasePresenter< View, Model > {
	}
}
