package com.hubertyoung.common.api

import com.hubertyoung.common.net.http.HttpConnection
import com.hubertyoung.common.net.request.RetrofitClient
import okhttp3.OkHttpClient

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/4 13:36
 * @since:
 * @see com.hubertyoung.common.Api
 */
interface Api {
	/**
	 * 获取OkHttpClient
	 * @return OkHttpClient 返回okhttp客户端
	 */
	fun getOkHttpClient(): OkHttpClient

	/**
	 * 获取HttpConnection
	 * @return HttpConnection 返回okhttp配置后的实例
	 */
	fun getHttpConnection(): HttpConnection

	/**
	 * 获取RetrofitClient
	 * @return RetrofitClient 返回Retrofit客户端
	 */
	fun getRetrofitClient(): RetrofitClient
}