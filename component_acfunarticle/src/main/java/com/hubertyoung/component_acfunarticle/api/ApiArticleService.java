package com.hubertyoung.component_acfunarticle.api;


import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.component_acfunarticle.entity.ArticleRecommendEntity;
import com.hubertyoung.component_acfunarticle.entity.Channel;
import com.hubertyoung.component_acfunarticle.entity.RankAc;

import java.util.List;
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
public interface ApiArticleService {

	@GET( "v3/channels/allChannels" )
	Flowable< BaseResponse< Channel > > requestAllChannel();

	@GET( "v3/regions" )
	Flowable< BaseResponse< List< ArticleRecommendEntity > > > requestArticleRecommend( @QueryMap Map< String, String > map );

	@GET( "v3/regions/search" )
	Flowable< BaseResponse< RankAc > > requestArticleGeneralSecond( @QueryMap Map< String, String > map );
}
