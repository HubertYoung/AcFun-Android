package com.hubertyoung.common.api

import android.support.v4.util.ArrayMap
import com.hubertyoung.common.net.http.HttpConnection
import com.hubertyoung.common.net.request.RetrofitClient
import okhttp3.OkHttpClient

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/4 14:31
 * @since:
 * @see com.hubertyoung.common.api.ApiImpl
 */
class ApiImpl : Api {

	companion object {
		val sRetrofitManager = ArrayMap<String, ApiImpl>(HostType::class.java.declaredFields.size)

		private lateinit var mRetrofitClient: RetrofitClient
		/**
		 * 调试添加baseurl 用户显示网络切换的host
		 */
		private var baseUrl: String = ""

		/**
		 * 必须传baseurl 否则报错
		 *
		 * @return
		 */
		@JvmStatic
		fun getInstance(): ApiImpl {
			return ApiImpl()
		}

		@JvmStatic
		fun getInstance(hostType: String): ApiImpl {
			var retrofitManager = sRetrofitManager[hostType]
			if (retrofitManager == null) {
				synchronized(ApiImpl::class.java) {
					if (retrofitManager == null) {
						retrofitManager = ApiImpl(hostType)
						sRetrofitManager[hostType] = retrofitManager
					}
				}
			}
			retrofitManager!!.getRetrofitClient().setBaseUrl(ApiConstants.getHost(hostType))
			return retrofitManager as ApiImpl
		}
	}

	/**
	 * 构造方法私有
	 */
	constructor() {
		val retrofitClient = getHttpConnection().retofitClinet
		mRetrofitClient = retrofitClient.setBaseUrl("")
	}

	/**
	 * 构造方法私有
	 *
	 * @param hostType 网络配置
	 */
	constructor(hostType: String) {
		ApiImpl()
		baseUrl = ApiConstants.getHost(hostType)
	}

	/**
	 * 获得OkHttpClient实例
	 *
	 * @return OkHttpClient
	 */
	override fun getOkHttpClient(): OkHttpClient {
		return getHttpConnection().okHttpClient
	}

	override fun getHttpConnection(): HttpConnection {
		return HttpConnection.getInstance()
	}

	override fun getRetrofitClient(): RetrofitClient {
		return mRetrofitClient
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