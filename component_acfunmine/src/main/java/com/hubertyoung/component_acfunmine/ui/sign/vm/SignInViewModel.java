package com.hubertyoung.component_acfunmine.ui.sign.vm;

import android.app.Application;
import android.text.TextUtils;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.utils.os.NetworkUtil;
import com.hubertyoung.component_acfunmine.R;
import com.hubertyoung.component_acfunmine.ui.sign.source.SignInRepository;

import androidx.annotation.NonNull;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/12 11:25
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.ui.sign.vm
 */
public class SignInViewModel extends AbsViewModel< SignInRepository > {

	public SignInViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestLoginInfo( String userNameStr, String passwordStr, String validationStr ) {
		if ( NetworkUtil.isNetAvailable() ) {
			if ( TextUtils.isEmpty( userNameStr ) || TextUtils.isEmpty( passwordStr ) ) {
				ToastUtil.showError( R.string.login_view_empty_error_text );
			} else if ( TextUtils.isEmpty( passwordStr ) || passwordStr.contains( " " ) ) {
				ToastUtil.showError( R.string.activity_signin_password_validation );
			} else if ( mRepository.tryConnectCount < 3 || !TextUtils.isEmpty( validationStr ) ) {
				mRepository.requestLogin( userNameStr, passwordStr, validationStr, Constants.cidKey );
			} else {
				ToastUtil.showError( R.string.login_view_image_code_empty_error_text );
			}
		} else {
			ToastUtil.showError( R.string.net_status_not_work );
		}
	}

	public void requestVerificationCodeInfo() {
		mRepository.requestVerificationCodeInfo();
	}
}
