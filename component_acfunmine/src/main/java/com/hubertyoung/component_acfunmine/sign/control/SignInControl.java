package com.hubertyoung.component_acfunmine.sign.control;


import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.entity.Sign;

import io.reactivex.Observable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/16 13:06
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunmine.mine.control
 */
public interface SignInControl {

	interface Model extends BaseModel {
		Observable<Sign > requestLoginInfo( MyRequestMap map );
	}

	interface View extends BaseView {
	}

	abstract class Presenter extends BasePresenter< View, Model > {
		public abstract void requestLoginInfo( String userNameStr, String passwordStr, String validationStr );
	}
}
