package com.hubertyoung.component_acfunmine.ui.mine.model;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component_acfunmine.BuildConfig;
import com.hubertyoung.component_acfunmine.api.ApiHomeService;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.component_acfunmine.ui.mine.control.MineControl;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

import io.reactivex.Observable;

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
	public Observable< Boolean > requestCheckOfflineInfo( MyRequestMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG) )
				.builder( ApiHomeService.class )
				.requestCheckOfflineInfo( map.map )
				.compose( new DefaultTransformer() );
//		new TextView(mContext);
	}

	@Override
	public Observable< User > requestUserInfo( MyRequestMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG) )
				.builder( ApiHomeService.class )
				.requestUserInfo( map.map )
				.compose( new DefaultTransformer() );
	}
}
