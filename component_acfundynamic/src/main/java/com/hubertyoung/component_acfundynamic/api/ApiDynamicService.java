package com.hubertyoung.component_acfundynamic.api;


import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.component_acfundynamic.entity.RecommendBangumiEntity;

import java.util.Map;

import io.reactivex.Flowable;
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
	@GET( "v2/user/content/profile" )
	Flowable< BaseResponse< User > > requestUserInfo();

	@GET( "v3/regions/recommendBangumi" )
	Flowable< BaseResponse< RecommendBangumiEntity > > requestRecommendBangumi( @QueryMap Map< String, String > map );
	@GET( "v3/regions/recommendUp" )
	Flowable< BaseResponse< RecommendBangumiEntity > > requestRecommendUp( @QueryMap Map< String, String > map );
}
