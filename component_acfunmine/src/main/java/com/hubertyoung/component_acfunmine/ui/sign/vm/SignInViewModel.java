package com.hubertyoung.component_acfunmine.ui.sign.vm;

import android.app.Application;
import android.text.TextUtils;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.utils.os.NetworkUtil;
import com.hubertyoung.component_acfunmine.R;
import com.hubertyoung.component_acfunmine.config.MineConstants;
import com.hubertyoung.component_acfunmine.entity.SignEntity;
import com.hubertyoung.component_acfunmine.entity.VerificationCodeEntity;
import com.hubertyoung.component_acfunmine.ui.sign.activity.SignInActivity;
import com.hubertyoung.component_acfunmine.ui.sign.source.SignInRepository;

import android.support.annotation.NonNull;

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

	private SignEntity mSignEntity = new SignEntity();
	public int tryConnectCount;
	private String captchaKey;

	public SignInViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestLoginInfo( String userNameStr, String passwordStr, String validationStr ) {
		if ( NetworkUtil.isNetAvailable() ) {
			if ( TextUtils.isEmpty( userNameStr ) || TextUtils.isEmpty( passwordStr ) ) {
				ToastUtil.showError( R.string.login_view_empty_error_text );
			} else if ( TextUtils.isEmpty( passwordStr ) || passwordStr.contains( " " ) ) {
				ToastUtil.showError( R.string.activity_signin_password_validation );
			} else if ( tryConnectCount < 3 || !TextUtils.isEmpty( validationStr ) ) {
				addDisposable( mRepository.requestLogin( userNameStr, passwordStr, validationStr, Constants.cidKey, captchaKey )//
						.subscribeWith( new RxSubscriber< BaseResponse< Sign > >() {
							@Override
							protected void showLoading() {
								showLoadingLayout( SignInActivity.class.getSimpleName(), "" );
							}

							@Override
							public void finishLoading() {
								stopLoading( SignInActivity.class.getSimpleName() );
							}

							@Override
							public void onSuccess( BaseResponse< Sign > response ) {
								mSignEntity.mSignBaseResponse = response;
								sendData( MineConstants.EVENT_KEY_SIGN, mSignEntity );
							}

							@Override
							public void onFailure( String msg ) {
								ToastUtil.showError( msg );
							}
						} ) );
			} else {
				ToastUtil.showError( R.string.login_view_image_code_empty_error_text );
			}
		} else {
			ToastUtil.showError( R.string.net_status_not_work );
		}
	}

	public void requestVerificationCodeInfo() {
		addDisposable( mRepository.requestVerificationCodeInfo()//
				.subscribeWith( new RxSubscriber< BaseResponse< VerificationCodeEntity > >() {
					@Override
					public void onSuccess( BaseResponse< VerificationCodeEntity > verificationCodeEntityBaseResponse ) {
						VerificationCodeEntity codeInfo = verificationCodeEntityBaseResponse.getData();
						captchaKey = codeInfo.key;
//						mView.setValidationImage( ImageUtil.customBase64ToBitmap( codeInfo.image ) );
						sendData( MineConstants.EVENT_KEY_SIGN_VERIFICATION_CODE, codeInfo.image );
					}

					@Override
					public void onFailure( String msg ) {

					}
				} ) );
	}
}
