package com.hubertyoung.common.api;


import android.support.v4.util.ArrayMap;

import com.hubertyoung.common.net.http.HttpConnection;
import com.hubertyoung.common.net.request.RetrofitClient;

import okhttp3.OkHttpClient;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2018/12/14 09:53
 * @since:api实现
 * @see ApiImpl
 */
public class ApiImpl implements Api {
	private static ArrayMap< String, ApiImpl > sRetrofitManager = new ArrayMap<>( HostType.class.getDeclaredFields().length );

	private RetrofitClient mRetrofitClient;
	/**
	 * 调试添加baseurl 用户显示网络切换的host
	 */
	private String baseUrl;

	/**
	 * 构造方法私有
	 */
	private ApiImpl() {
		RetrofitClient retrofitClient = getHttpConnection().getRetofitClinet();
		mRetrofitClient = retrofitClient.setBaseUrl( "" );
	}

	/**
	 * 构造方法私有
	 *
	 * @param hostType 网络配置
	 */
	private ApiImpl( String hostType ) {
		RetrofitClient retrofitClient = getHttpConnection().getRetofitClinet();
		this.mRetrofitClient = retrofitClient;
		this.baseUrl = ApiConstants.getHost( hostType );
	}

	/**
	 * 获得OkHttpClient实例
	 *
	 * @return OkHttpClient
	 */
	@Override
	public OkHttpClient getOkHttpClient() {
		return getHttpConnection().getOkHttpClient();
	}

	@Override
	public HttpConnection getHttpConnection() {
		return HttpConnection.getInstance();
	}

	@Override
	public RetrofitClient getRetrofitClient() {
		return mRetrofitClient;
	}

	/**
	 * 获取默认的ApiImpl
	 *
	 * @param hostType
	 */
	public static ApiImpl getInstance( String hostType ) {
		ApiImpl retrofitManager = sRetrofitManager.get( hostType );
		if ( retrofitManager == null ) {
			synchronized ( ApiImpl.class ) {
				if ( retrofitManager == null ) {
					retrofitManager = new ApiImpl( hostType );
					sRetrofitManager.put( hostType, retrofitManager );
				}
			}
		}
		retrofitManager.getRetrofitClient().setBaseUrl( ApiConstants.getHost( hostType ) );
		return retrofitManager;
	}

	/**
	 * 必须传baseurl 否则报错
	 *
	 * @return
	 */
	public static ApiImpl getInstance() {
		return new ApiImpl();
	}

//	public List< MultipartBody.Part > addFile( String key, List< File > files ) {
//		List< MultipartBody.Part > multipartBodyParts = new ArrayList<>();
//		for (File file : files) {
//			RequestBody requestBody = RequestBody.create( MediaTypes.APPLICATION_OCTET_STREAM_TYPE, file );
//			MultipartBody.Part part = MultipartBody.Part.createFormData( key, file.getName(), requestBody );
//			multipartBodyParts.add( part );
//		}
//		return multipartBodyParts;
//	}
//
//
//	public RequestBody convertToRequestBody( String param ) {
//		RequestBody requestBody = RequestBody.create( MediaType.parse( "image/jpg" ), param );
//		return requestBody;
//	}
}