package com.hubertyoung.component_acfundynamic.dynamic.source;

import com.hubertyoung.common.api.ApiImpl;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.entity.RecommendBangumiEntity;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component_acfundynamic.api.ApiDynamicService;

import java.util.HashMap;

import io.reactivex.Flowable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/18 14:33
 * @since:V5.2.1
 * @desc:com.hubertyoung.component_acfundynamic.dynamic.source
 */
public class DynamicFollwBangumiRepository extends AbsRepository {

	public Flowable< RecommendBangumiEntity > requestRecommendBangumi( String pageNo, String pageSize ) {
		HashMap map = new HashMap<String,String>();
		map.put( "pageNo", pageNo );
		map.put( "pageSize", pageSize );
		return ApiImpl.getInstance( HostType.APP_NEWAPI_HOST )
				.getRetrofitClient()
//				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG) )
				.builder( ApiDynamicService.class )
				.requestRecommendBangumi( map )
				.compose( new DefaultTransformer() );
	}
}
