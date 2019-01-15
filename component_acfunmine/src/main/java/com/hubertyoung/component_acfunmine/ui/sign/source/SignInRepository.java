package com.hubertyoung.component_acfunmine.ui.sign.source;

import android.text.TextUtils;

import com.hubertyoung.common.api.ApiImpl;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.baserx.RxSchedulers;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.component_acfunmine.api.ApiHomeService;
import com.hubertyoung.component_acfunmine.entity.VerificationCodeEntity;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

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
public class SignInRepository extends AbsRepository {

	public Flowable< BaseResponse< Sign > > requestLogin( String userNameStr, String passwordStr, String validationStr, String cidKey, String captchaKey ) {
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
		return ApiImpl.getInstance( HostType.APP_ACCOUNT_HOST )
				.getRetrofitClient()
//				.setBaseUrl( EnvironmentSwitcher.getAccountEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiHomeService.class )
				.requestLoginInfo( map )
				.compose( RxSchedulers.io_main() )
				.subscribeOn( AndroidSchedulers.mainThread() );

	}

	public Flowable< BaseResponse< VerificationCodeEntity > > requestVerificationCodeInfo() {
		return ApiImpl.getInstance( HostType.APP_ACCOUNT_HOST )
				.getRetrofitClient()
//				.setBaseUrl( EnvironmentSwitcher.getAccountEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiHomeService.class )
				.requestVerificationCodeInfo()
				.compose( RxSchedulers.io_main() )//
				.subscribeOn( AndroidSchedulers.mainThread() );
	}
}
