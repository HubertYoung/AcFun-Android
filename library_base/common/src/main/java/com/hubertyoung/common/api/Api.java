package com.hubertyoung.common.api;

import com.hubertyoung.common.net.http.HttpConnection;
import com.hubertyoung.common.net.request.RetrofitClient;

import okhttp3.OkHttpClient;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2018/12/14 09:52
 * @since:
 * @see Api
 */
interface Api {

	/**
	 * 获取OkHttpClient
	 * @return OkHttpClient 返回okhttp客户端
	 */
	OkHttpClient getOkHttpClient();
	/**
	 * 获取HttpConnection
	 * @return HttpConnection 返回okhttp配置后的实例
	 */
	HttpConnection getHttpConnection();
	/**
	 * 获取RetrofitClient
	 * @return RetrofitClient 返回Retrofit客户端
	 */
	RetrofitClient getRetrofitClient();
}
