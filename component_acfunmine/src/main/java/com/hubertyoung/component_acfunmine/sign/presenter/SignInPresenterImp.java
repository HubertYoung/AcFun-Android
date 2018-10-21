package com.hubertyoung.component_acfunmine.sign.presenter;


import android.text.TextUtils;
import android.util.Log;

import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.utils.NetworkUtil;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.component_acfunmine.R;
import com.hubertyoung.component_acfunmine.sign.control.SignInControl;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

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
				requestLogin(userNameStr, passwordStr, validationStr,Constants.cidKey );
			} else {
				ToastUtil.showError( R.string.login_view_image_code_empty_error_text );
				return;
			}
			return;
		}
		ToastUtil.showError( R.string.net_status_not_work );
	}

	private void requestLogin( String userNameStr, String passwordStr, String validationStr, String cidKey ) {
//		captcha	eer
//		username	17600696672
//		cid	ELSH6ruK0qva88DD
//		password	fffff
//		key	8a7490e5534761c07da8963e904bad9a
		MyRequestMap map = new MyRequestMap();
		if ( !TextUtils.isEmpty( validationStr ) ) {
			map.put( "captcha", validationStr );
			map.put( "key", captchaKey );
		}
		map.put( "username", userNameStr );
		map.put( "cid", cidKey );
		map.put( "password", passwordStr );
		mView.showLoading( "Loading...", 0 );
		mRxManage.add( mModel.requestLoginInfo( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribe( new Consumer< Sign >() {

					@Override
					public void accept( @NonNull Sign sign ) throws Exception {
						Log.e( "TAG", "" );
					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( @NonNull Throwable throwable ) throws Exception {
						mView.stopLoading();
						mView.showErrorTip( throwable.getMessage() );
					}
				} ) );
	}
}
