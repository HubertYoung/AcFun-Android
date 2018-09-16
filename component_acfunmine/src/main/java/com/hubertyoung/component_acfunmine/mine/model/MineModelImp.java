package com.hubertyoung.component_acfunmine.mine.model;

import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component_acfunmine.api.ApiHomeService;
import com.hubertyoung.component_acfunmine.entity.User;
import com.hubertyoung.component_acfunmine.mine.control.MineControl;

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
				.builder( ApiHomeService.class )
				.requestCheckOfflineInfo( map.map )
				.compose( new DefaultTransformer() );
	}

	@Override
	public Observable< User > requestUserInfo( MyRequestMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestUserInfo( map.map )
				.compose( new DefaultTransformer() );
	}
}
