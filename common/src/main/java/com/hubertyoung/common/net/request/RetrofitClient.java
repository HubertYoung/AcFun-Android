package com.hubertyoung.common.net.request;


import android.text.TextUtils;

import com.hubertyoung.common.net.factory.ExGsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者：JIUU on 2017-7-10 16:00:51
 * QQ号：1344393464
 * 作用：RetrofitClient
 */
public class RetrofitClient {

	private static OkHttpClient mOkHttpClient;
	//    初始化BaseUrl
	private static String baseUrl;

	private static Retrofit retrofit;

	/**
	 * RetrofitClient 构造 函数
	 * 获取OKhttpClient 实例
	 *
	 * @param mOkHttpClient
	 */
	public RetrofitClient( String baseUrl, OkHttpClient mOkHttpClient ) {
		this.baseUrl = baseUrl;
		this.mOkHttpClient = mOkHttpClient;
	}


	/**
	 * 修改BaseUrl地址
	 *
	 * @param baseUrl
	 */
	public RetrofitClient setBaseUrl( String baseUrl ) {
		this.baseUrl = baseUrl;
		return this;
	}

	/**
	 * 获得对应的ApiServcie对象
	 *
	 * @param service
	 * @param <T>
	 * @return
	 */
//    public  <T> T  builder(Class<T> service)
//    {
//        if(baseUrl==null)
//        {
//            throw new RuntimeException("baseUrl is null!");
//        }
//        if (service == null) {
//            throw new RuntimeException("api Service is null!");
//        }
//        retrofit=new Retrofit.Builder()
//                .client(mOkHttpClient)
//                .baseUrl(baseUrl)
//                .addConverterFactory( ExGsonConverterFactory.create())
//                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
//                .build();
//        return retrofit.create(service);
//    }
	public < T > T builder( Class< T > service ) {
		if ( TextUtils.isEmpty( baseUrl ) ) {
			throw new RuntimeException( "baseUrl is null!" );
		}
		if ( service == null ) {
			throw new RuntimeException( "api Service is null!" );
		}
		retrofit = new Retrofit.Builder().client( mOkHttpClient )
				.baseUrl( baseUrl )
				//增加返回值为String的支持
				.addConverterFactory( ScalarsConverterFactory.create() )
				.addConverterFactory( ExGsonConverterFactory.create() )
				.addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
				.build();
		return retrofit.create( service );
	}
}
