package com.hubertyoung.component_acfunmine.api;


import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.component_acfunmine.entity.VerificationCodeEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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
	 * 首页分区页面数据
	 *
	 * @param map
	 * @return
	 */
//	http://apipc.app.acfun.cn/v2/offlines/checkOffline
	@GET( "v2/offlines/checkOffline" )
	Observable< BaseResponse< Boolean > > requestCheckOfflineInfo( @QueryMap Map< String, String > map );
//	http://apipc.app.acfun.cn/v2/user/content/profile?userId=13608720

	@GET( "v2/user/content/profile" )
	Observable< BaseResponse< User > > requestUserInfo( @QueryMap Map< String, String > map );

	@FormUrlEncoded
	@POST( "api/account/signin/normal" )
	@Headers( { "Accept:application/vdata+json+version:1.1", "Content-Type:application/x-www-form-urlencoded; charset=UTF-8" } )
	Observable< BaseResponse< Sign > > requestLoginInfo( @FieldMap Map< String, String > map );

	@GET( "api/account/verification/captcha" )
	@Headers( { "Accept:application/vdata+json+version:1.1", "Content-Type:application/x-www-form-urlencoded; charset=UTF-8" } )
	Observable< BaseResponse< VerificationCodeEntity > > requestVerificationCodeInfo( );

	@FormUrlEncoded
	@POST("api/account/oauth/login")
	Observable< BaseResponse<Sign> > requestPlatformLogin(@FieldMap Map< String, String > map );
}
