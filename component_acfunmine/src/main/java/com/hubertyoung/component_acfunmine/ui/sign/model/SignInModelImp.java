package com.hubertyoung.component_acfunmine.ui.sign.model;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.baserx.RxSchedulers;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.component_acfunmine.BuildConfig;
import com.hubertyoung.component_acfunmine.api.ApiHomeService;
import com.hubertyoung.component_acfunmine.entity.VerificationCodeEntity;
import com.hubertyoung.component_acfunmine.ui.sign.control.SignInControl;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

import io.reactivex.Flowable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/16 13:06
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunmine.mine.model
 */
public class SignInModelImp implements SignInControl.Model {
	@Override
	public Flowable< BaseResponse< Sign > > requestLoginInfo( MyRequestMap map ) {
		return Api.getDefault( HostType.APP_HOST_ACCOUNT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getAccountEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiHomeService.class )
				.requestLoginInfo( map.map )
				.compose( RxSchedulers.io_main() );
	}

	@Override
	public Flowable< BaseResponse< VerificationCodeEntity > > requestVerificationCodeInfo() {
		return Api.getDefault( HostType.APP_HOST_ACCOUNT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getAccountEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiHomeService.class )
				.requestVerificationCodeInfo()
				.compose( RxSchedulers.io_main() );
	}
}
