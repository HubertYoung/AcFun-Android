package com.kento.common.api;


import android.util.SparseArray;

import com.kento.common.net.config.MediaTypes;
import com.kento.common.net.http.HttpUtils;
import com.kento.common.net.request.RetrofitClient;
import com.kento.common.utils.AppUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class Api {
	private static SparseArray< Api > sRetrofitManager = new SparseArray<>( HostType.TYPE_COUNT );

	private final RetrofitClient retrofitClient;

	/**
	 * 构造方法私有
	 */
	private Api() {
		RetrofitClient retofitClinet = HttpUtils.getInstance()
												.getRetofitClinet();
		retrofitClient = retofitClinet.setBaseUrl( "" );
	}

	/**
	 * 构造方法私有
	 *
	 * @param hostType 网络配置
	 */
	private Api( int hostType, boolean isUrlDebug ) {
		if ( isUrlDebug ) {
			RetrofitClient retofitClinet = HttpUtils.getInstance()
													.getRetofitClinet();
			retrofitClient = retofitClinet.setBaseUrl( ApiConstants.getDebugHost( hostType ) );
		} else {
			RetrofitClient retofitClinet = HttpUtils.getInstance()
													.getRetofitClinet();
			retrofitClient = retofitClinet.setBaseUrl( ApiConstants.getHost( hostType ) );
		}
	}

	/**
	 * 获得OkHttpClient实例
	 *
	 * @return
	 */
	public OkHttpClient getOkHttpClient() {
		return HttpUtils.getInstance()
						.getOkHttpClient();
	}

	public HttpUtils getHttpUtils() {
		return HttpUtils.getInstance();
	}

	public RetrofitClient getRetrofitClient() {
		return retrofitClient;
	}

	/**
	 * @param hostType
	 */
	public static Api getDefault( int hostType ) {
		Api retrofitManager = sRetrofitManager.get( hostType );
		if ( retrofitManager == null ) {
			retrofitManager = new Api( hostType, false );
			sRetrofitManager.put( hostType, retrofitManager );
		}
		if ( AppUtils.isDebuggable() ) {
//			int index = SPUtils.getSharedIntData( AppConstant.SWITCHURLINDEX );
			retrofitManager = new Api( hostType, true );
		}
		return retrofitManager;

	}

	/**
	 * 必须传baseurl 否则报错
	 * @return
	 */
	public static Api getDefault() {
		return new Api();
	}

	public List< MultipartBody.Part > addFile( String key, List< File > files ) {
		List< MultipartBody.Part > multipartBodyParts = new ArrayList<>();
		for (File file : files) {
			RequestBody requestBody = RequestBody.create( MediaTypes.APPLICATION_OCTET_STREAM_TYPE, file );
			MultipartBody.Part part = MultipartBody.Part.createFormData( key, file.getName(), requestBody );
			multipartBodyParts.add( part );
		}
		return multipartBodyParts;
	}


	public RequestBody convertToRequestBody( String param ) {
		RequestBody requestBody = RequestBody.create( MediaType.parse( "image/jpg" ), param );
		return requestBody;
	}
}