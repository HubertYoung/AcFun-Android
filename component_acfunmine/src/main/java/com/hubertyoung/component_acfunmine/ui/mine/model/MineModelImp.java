package com.hubertyoung.component_acfunmine.ui.mine.model;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component_acfunmine.BuildConfig;
import com.hubertyoung.component_acfunmine.api.ApiHomeService;
import com.hubertyoung.component_acfunmine.ui.mine.control.MineControl;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

import java.util.Map;

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
public class MineModelImp implements MineControl.Model {
	@Override
	public Flowable< Boolean > requestCheckOfflineInfo( MyRequestMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG) )
				.builder( ApiHomeService.class )
				.requestCheckOfflineInfo( map.map )
				.compose( new DefaultTransformer() );
//		new TextView(mContext);
	}

	@Override
	public Flowable< User > requestUserInfo( MyRequestMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG) )
				.builder( ApiHomeService.class )
				.requestUserInfo( map.map )
				.compose( new DefaultTransformer() );
	}

	@Override
	public Flowable< Sign > requestPlatformLogin( Map< String, String > map ) {
		return Api.getDefault( HostType.APP_HOST_ACCOUNT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getAccountEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiHomeService.class )
				.requestPlatformLogin( map )
				.compose( new DefaultTransformer<>());
	}
}
