package com.hubertyoung.component.home.api;


import com.hubertyoung.component.home.entity.BrandDetailBodyEntity;
import com.hubertyoung.component.home.entity.BrandDetailEntity;
import com.hubertyoung.component.home.entity.HomeIndexEntity;
import com.hubertyoung.common.net.response.BaseResponse;

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
	@GET( "home/index" )
	Flowable< BaseResponse< HomeIndexEntity > > requestHomeIndex( @QueryMap Map< String, String > map );

	/**
	 * 商品详情
	 * @param map
	 * @return
	 */
	@GET( "goods/category" )
	Flowable< BaseResponse< BrandDetailEntity > > requestBrandDetail( @QueryMap Map< String, String > map );
	/**
	 * 商品列表
	 * @param map
	 * @return
	 */
	@GET("goods/list" )
	Flowable< BaseResponse< BrandDetailBodyEntity > > requestBrandDetailList( @QueryMap Map< String, String > map );
}
