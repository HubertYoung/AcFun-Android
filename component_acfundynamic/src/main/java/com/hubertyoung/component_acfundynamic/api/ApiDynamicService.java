package com.hubertyoung.component_acfundynamic.api;


import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.component_acfundynamic.entity.RecommendBangumiEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 11:33 AM
 * @since:V1.0
 * @desc:api
 */
public interface ApiDynamicService {
	@GET( "v3/regions/recommendBangumi" )
	Observable< BaseResponse< RecommendBangumiEntity > > requestRecommendBangumi( @QueryMap Map< String, String > map );
	@GET( "v3/regions/recommendUp" )
	Observable< BaseResponse< RecommendBangumiEntity > > requestRecommendUp( @QueryMap Map< String, String > map );
}
