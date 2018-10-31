package com.hubertyoung.component_acfunmine.ui.sign.control;


import android.graphics.Bitmap;

import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.component_acfunmine.entity.VerificationCodeEntity;

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
		Observable< BaseResponse< Sign > > requestLoginInfo( MyRequestMap map );

		Observable< BaseResponse< VerificationCodeEntity > > requestVerificationCodeInfo();
	}

	interface View extends BaseView {
		boolean getValidationLayoutShown();

		void setValidationLayoutShown();

		void setValidationLayoutText( String text );

		void setValidationImage( Bitmap bitmap );

		void showLoginSuccess( Sign sign );
	}

	abstract class Presenter extends BasePresenter< View, Model > {
		public abstract void requestLoginInfo( String userNameStr, String passwordStr, String validationStr );

		protected abstract void requestVerificationCodeInfo();
	}
}
