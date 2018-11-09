package com.hubertyoung.component.acfunvideo.index.control;


import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;

import java.util.HashMap;

import io.reactivex.Flowable;

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
public interface HomePageControl {

	interface Model extends BaseModel {
		Flowable< java.util.HashMap > requestDomainAndroidCfg( HashMap map );
	}

	interface View extends BaseView {
	}

	abstract class Presenter extends BasePresenter< View, Model > {
		public abstract void requestDomainAndroidCfg( HashMap map );
	}
}
