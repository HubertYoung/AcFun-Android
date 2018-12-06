package com.hubertyoung.common.api;


import android.support.v4.util.ArrayMap;

import com.hubertyoung.common.net.http.HttpUtils;
import com.hubertyoung.common.net.request.RetrofitClient;

import okhttp3.OkHttpClient;

public class Api {
	private static ArrayMap< Integer, Api > sRetrofitManager = new ArrayMap<>( HostType.TYPE_COUNT );

	private RetrofitClient mRetrofitClient;
	private String baseUrl;

	/**
	 * 构造方法私有
	 */
	private Api() {
		RetrofitClient retrofitClient = HttpUtils.getInstance().getRetofitClinet();
		mRetrofitClient = retrofitClient.setBaseUrl( "" );
	}

	/**
	 * 构造方法私有
	 *
	 * @param hostType 网络配置
	 */
	private Api( int hostType ) {
		RetrofitClient retrofitClient = HttpUtils.getInstance().getRetofitClinet();
		this.mRetrofitClient = retrofitClient;
		this.baseUrl = ApiConstants.getHost( hostType );
	}

	/**
	 * 获得OkHttpClient实例
	 *
	 * @return
	 */
	public OkHttpClient getOkHttpClient() {
		return HttpUtils.getInstance().getOkHttpClient();
	}

	public HttpUtils getHttpUtils() {
		return HttpUtils.getInstance();
	}

	public RetrofitClient getRetrofitClient() {
		return mRetrofitClient;
	}

	/**
	 * @param hostType
	 */
	public static Api getDefault( int hostType ) {
		Api retrofitManager = sRetrofitManager.get( hostType );
		if ( retrofitManager == null ) {
			retrofitManager = new Api( hostType );
			sRetrofitManager.put( hostType, retrofitManager );
		}
		retrofitManager.getRetrofitClient().setBaseUrl( ApiConstants.getHost( hostType ) );
		return retrofitManager;
	}

	/**
	 * 必须传baseurl 否则报错
	 *
	 * @return
	 */
	public static Api getDefault() {
		return new Api();
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