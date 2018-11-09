package com.hubertyoung.component_acfunmine.ui.sign.presenter;


import android.text.TextUtils;

import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.common.utils.SigninHelper;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.utils.os.NetworkUtil;
import com.hubertyoung.common.widget.ImageUtil;
import com.hubertyoung.component_acfunmine.R;
import com.hubertyoung.component_acfunmine.entity.VerificationCodeEntity;
import com.hubertyoung.component_acfunmine.ui.sign.control.SignInControl;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/16 13:07
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunmine.mine.presenter
 */
public class SignInPresenterImp extends SignInControl.Presenter {
	private int tryConnectCount;
	private String captchaKey;

	@Override
	public void requestLoginInfo( String userNameStr, String passwordStr, String validationStr ) {
		if ( NetworkUtil.isNetAvailable() ) {
			if ( TextUtils.isEmpty( userNameStr ) || TextUtils.isEmpty( passwordStr ) ) {
				ToastUtil.showError( R.string.login_view_empty_error_text );
			} else if ( TextUtils.isEmpty( passwordStr ) || passwordStr.contains( " " ) ) {
				ToastUtil.showError( R.string.activity_signin_password_validation );
			} else if ( tryConnectCount < 3 || !TextUtils.isEmpty( validationStr ) ) {
				requestLogin( userNameStr, passwordStr, validationStr, Constants.cidKey );
			} else {
				ToastUtil.showError( R.string.login_view_image_code_empty_error_text );
			}
		} else {
			ToastUtil.showError( R.string.net_status_not_work );
		}
	}

	private void requestLogin( String userNameStr, String passwordStr, String validationStr, String cidKey ) {
//		captcha	eer
//		username	17600696672
//		cid	ELSH6ruK0qva88DD
//		password	fffff
//		key	8a7490e5534761c07da8963e904bad9a
		HashMap map = new HashMap<String,String>();
		if ( !TextUtils.isEmpty( validationStr ) ) {
			map.put( "captcha", validationStr );
			map.put( "key", captchaKey );
		}
		map.put( "username", userNameStr );
		map.put( "cid", cidKey );
		map.put( "password", passwordStr );
		mRxManage.add( mModel.requestLoginInfo( map )//
				.subscribeWith( new RxSubscriber< BaseResponse< Sign > >() {
					@Override
					protected void showLoading() {
						mView.showLoading( "Loading...", 0 );
					}

					@Override
					public void onComplete() {
						mView.stopLoading();
					}

					@Override
					public void onSuccess( BaseResponse< Sign > response ) {
						if ( response.errno == 0 ) {
							//success
//						{
//							"token": "935b4692999e2fc654146ab35c21595e",
//								"expiration": 1543015656,
//								"check_password": 0,
//								"check_real": 0,
//								"oauth": 0,
//								"acPasstoken": "ChVpbmZyYS5hY2Z1bi5wYXNzdG9rZW4SYDo57DP6CfhrceiovnpfJl5BwiW9eZT26NWQbClyfMTe4
// -wGVcqUdLc_UM4ev2dRxVjUHpHJsObDvWLFMP85t9WEuVoUAJ1c_lwm6V31iq7mnx09dj8sbS1-aNZrLuhunRoSY5RL5hdSyg5ILxKRSiTMY7LUIiD6Te0ntRdYMdM4ZURXXiAOHrapG573gd9Oe-F8t-OOqygFMAE",
//								"acSecurity": "gPTOOAPz9QJJ5tQ6DxbXFQ==",
//								"acPostHint": "3332540703dc3d5982c019ddf863c42dea5c",
//								"passCheck": true,
//								"info": {
//							"avatar": "http:\/\/cdn.aixifan.com\/dotnet\/20120923\/style\/image\/avatar.jpg",
//									"username": "hubert520",
//									"userid": 13608720,
//									"mobile": "17600696672",
//									"group-level": 0,
//									"mobile-check": 1
//						},
//							"s2s-code": "22015c03c407892f84112603e131217d"
//						}
							Sign sign = response.getData();
							SigninHelper.getInstance().setUserSign( sign );
							mView.showLoginSuccess( sign );
						} else {
							tryConnectCount++;
							if ( response.errno == 20285 ) {
								tryConnectCount = 3;
								response.errordesc = mContext.getResources().getString( R.string.login_view_need_input_image_code_text );
							}
							if ( tryConnectCount > 2 && !mView.getValidationLayoutShown() ) {
								mView.setValidationLayoutShown();
							}
							if ( tryConnectCount > 2 ) {
								requestVerificationCodeInfo();
								mView.setValidationLayoutText( "" );
							}
							mView.showErrorTip( response.errordesc );
						}
					}

					@Override
					public void onFailure( String msg ) {
						mView.showErrorTip( msg );
					}
				} ) );
	}

	@Override
	public void requestVerificationCodeInfo() {
		mRxManage.add( mModel.requestVerificationCodeInfo()//
				.subscribeOn( AndroidSchedulers.mainThread() )//
				.subscribeWith( new RxSubscriber< BaseResponse< VerificationCodeEntity > >() {
					@Override
					public void onSuccess( BaseResponse< VerificationCodeEntity > verificationCodeEntityBaseResponse ) {
						VerificationCodeEntity codeInfo = verificationCodeEntityBaseResponse.getData();
						captchaKey = codeInfo.key;
						mView.setValidationImage( ImageUtil.customBase64ToBitmap( codeInfo.image ) );
					}

					@Override
					public void onFailure( String msg ) {

					}
				} ) );
	}
}
