package com.hubertyoung.component.acfunvideo.api;


import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.entity.Regions;
import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.component.acfunvideo.entity.BangumiDetailBeanRaw;
import com.hubertyoung.component.acfunvideo.entity.ChannelOperate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
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
public interface ApiHomeService {

	@GET( "mobile/domain_android.cfg" )
	Flowable< HashMap< String, String > > requestDomainAndroidCfg();

	/**
	 * 首页index
	 *
	 * @param map
	 * @return
	 */
	@GET( "v3/regions" )
	Flowable< BaseResponse< List< Regions > > > requestRecommend( @QueryMap Map< String, String > map );

	/**
	 * 首页加载更多
	 *
	 * @param channelId
	 * @param map
	 * @return
	 */
	@GET( "v3/regions/new/{channelId}" )
	Flowable< BaseResponse< List< RegionBodyContent > > > requestNewRecommend( @Path( "channelId" ) String channelId, @QueryMap Map< String, String > map );

	/**
	 * 首页分区页面数据
	 *
	 * @param map
	 * @return
	 */
	@GET( "v3/channels/channelOperate" )
	Flowable< BaseResponse< ChannelOperate > > requestChannelOperate( @QueryMap Map< String, String > map );

	/**
	 * 首页加载更多
	 *
	 * @param contentId
	 * @return
	 */
	@GET( "v3/bangumis/{contentId}" )
	Flowable< BaseResponse< BangumiDetailBeanRaw > > requestBangumisDetail( @Path( "contentId" ) String contentId );

//
//	/**
//	 * 商品列表
//	 *
//	 * @param map
//	 * @return
//	 */
//	@GET( "goods/list" )
//	Flowable< BaseResponse< BrandDetailBodyEntity > > requestBrandDetailList( @QueryMap Map< String, String > map );
}
