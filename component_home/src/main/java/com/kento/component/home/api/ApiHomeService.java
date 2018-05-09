package com.kento.component.home.api;


import com.kento.common.net.response.BaseResponse;
import com.kento.component.home.entity.HomeIndexEntity;

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
	Observable< BaseResponse< HomeIndexEntity > > requestHomeIndex( @QueryMap Map< String, String > map );
}
