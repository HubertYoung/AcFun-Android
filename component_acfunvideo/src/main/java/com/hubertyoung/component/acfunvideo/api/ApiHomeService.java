package com.hubertyoung.component.acfunvideo.api;


import com.hubertyoung.component.acfunvideo.entity.ChannelOperate;
import com.hubertyoung.component.acfunvideo.entity.RegionBodyContent;
import com.hubertyoung.component.acfunvideo.entity.Regions;
import com.hubertyoung.common.net.response.BaseResponse;

import java.util.List;
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
public interface ApiHomeService {
	/**
	 * 首页index
	 *
	 * @param map
	 * @return
	 */
	@GET( "v3/regions" )
	Observable< BaseResponse< List< Regions > > > requestRecommend( @QueryMap Map< String, String > map );

	/**
	 * 首页加载更多
	 * @param map
	 * @return
	 */
	@GET( "v3/regions/new/0" )
	Observable< BaseResponse< List< RegionBodyContent > > > requestNewRecommend( @QueryMap Map< String, String > map );

	/**
	 * 首页分区页面数据
	 * @param map
	 * @return
	 */
	@GET( "v3/channels/channelOperate" )
	Observable<BaseResponse<ChannelOperate >> requestChannelOperate( @QueryMap Map<String, String> map );

//	/**
//	 * 商品详情
//	 *
//	 * @param map
//	 * @return
//	 */
//	@GET( "goods/category" )
//	Observable< BaseResponse< BrandDetailEntity > > requestBrandDetail( @QueryMap Map< String, String > map );
//
//	/**
//	 * 商品列表
//	 *
//	 * @param map
//	 * @return
//	 */
//	@GET( "goods/list" )
//	Observable< BaseResponse< BrandDetailBodyEntity > > requestBrandDetailList( @QueryMap Map< String, String > map );
}
