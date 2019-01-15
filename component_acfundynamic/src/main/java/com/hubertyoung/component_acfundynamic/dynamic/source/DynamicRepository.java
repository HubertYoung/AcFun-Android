package com.hubertyoung.component_acfundynamic.dynamic.source;

import com.hubertyoung.common.api.ApiImpl;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component_acfundynamic.api.ApiDynamicService;

import io.reactivex.Flowable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/18 14:33
 * @since:V5.2.7
 * @desc:com.hubertyoung.component_acfundynamic.dynamic.source
 */
public class DynamicRepository extends AbsRepository {

	public Flowable< User > requestAllChannel() {
		return ApiImpl.getInstance( HostType.APP_NEWAPI_HOST )
				.getRetrofitClient()
//				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG) )
				.builder( ApiDynamicService.class )
				.requestUserInfo( )
				.compose( new DefaultTransformer() );
	}
}
