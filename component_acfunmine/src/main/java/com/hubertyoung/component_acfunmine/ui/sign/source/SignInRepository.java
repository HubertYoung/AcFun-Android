package com.hubertyoung.component_acfunmine.ui.sign.source;

import android.text.TextUtils;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.baserx.RxSchedulers;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.data.BaseRepository;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_acfunmine.BuildConfig;
import com.hubertyoung.component_acfunmine.api.ApiHomeService;
import com.hubertyoung.component_acfunmine.config.MineConstants;
import com.hubertyoung.component_acfunmine.entity.SignEntity;
import com.hubertyoung.component_acfunmine.entity.VerificationCodeEntity;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/12 11:23
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.ui.sign.source
 */
public class SignInRepository extends BaseRepository {
	public int tryConnectCount;
	private String captchaKey;
	private SignEntity mSignEntity = new SignEntity();

	public void requestLogin( String userNameStr, String passwordStr, String validationStr, String cidKey ) {
//		captcha	eer
//		username	17600696672
//		cid	ELSH6ruK0qva88DD
//		password	fffff
//		key	8a7490e5534761c07da8963e904bad9a
		HashMap map = new HashMap< String, String >();
		if ( !TextUtils.isEmpty( validationStr ) ) {
			map.put( "captcha", validationStr );
			map.put( "key", captchaKey );
		}
		map.put( "username", userNameStr );
		map.put( "cid", cidKey );
		map.put( "password", passwordStr );
		addDisposable( ( Disposable ) Api.getDefault( HostType.APP_HOST_ACCOUNT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getAccountEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiHomeService.class )
				.requestLoginInfo( map )
				.compose( RxSchedulers.io_main() )
				.subscribeOn( AndroidSchedulers.mainThread() )//
				.subscribeWith( new RxSubscriber< BaseResponse< Sign > >() {
					@Override
					protected void showLoading() {
						showDialogLoading("");
					}
					@Override
					public void finishLoading() {
						stopLoading();
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
	}

	public void requestVerificationCodeInfo() {
		addDisposable( Api.getDefault( HostType.APP_HOST_ACCOUNT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getAccountEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiHomeService.class )
				.requestVerificationCodeInfo()
				.compose( RxSchedulers.io_main() )//
				.subscribeOn( AndroidSchedulers.mainThread() )//
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
