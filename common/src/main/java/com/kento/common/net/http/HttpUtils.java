package com.kento.common.net.http;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.kento.common.CommonApplication;
import com.kento.common.net.callback.UCallback;
import com.kento.common.net.config.NetWorkConfiguration;
import com.kento.common.net.cookie.PersistentCookieJar;
import com.kento.common.net.cookie.cache.SetCookieCache;
import com.kento.common.net.cookie.persistence.SharedPrefsCookiePersistor;
import com.kento.common.net.interceptor.LogInterceptor;
import com.kento.common.net.interceptor.UploadProgressInterceptor;
import com.kento.common.net.request.RetrofitClient;
import com.kento.common.utils.CommonLog;
import com.kento.common.utils.NetworkUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 作者：JIUU on 2017-7-10 16:00:51
 * QQ号：1344393464
 * 作用：对OkHttpClient进行配置
 */
public class HttpUtils {

	public static final String TAG = "HttpUtils";
	private String userAgentHeaderValue = "Android";
	//    获得HttpUtils实例
	private static HttpUtils mInstance;
	//    OkHttpClient对象
	private OkHttpClient mOkHttpClient;
	private static NetWorkConfiguration configuration;
	/**
	 * 是否加载本地缓存数据
	 * 默认为TRUE
	 */
	private boolean isLoadDiskCache = true;

	private PersistentCookieJar persistentCookieJar;

	protected UCallback uploadCallback;//上传进度回调
	private final OkHttpClient.Builder mBuilder;

	/**
	 * ---> 针对无网络情况
	 * 是否加载本地缓存数据
	 *
	 * @param isCache true为加载 false不进行加载
	 * @return
	 */
	public HttpUtils setLoadDiskCache( boolean isCache ) {
		this.isLoadDiskCache = isCache;
		return this;
	}

	/**
	 * ---> 针对有网络情况
	 * 是否加载内存缓存数据
	 * 默认为False
	 */
	private boolean isLoadMemoryCache = false;

	/**
	 * 是否加载内存缓存数据
	 *
	 * @param isCache true为加载 false不进行加载
	 * @return
	 */
	public HttpUtils setLoadMemoryCache( boolean isCache ) {
		this.isLoadMemoryCache = isCache;
		return this;
	}

	public HttpUtils() {
		/**进行默认配置
		 *    未配置configuration ,
		 *
		 */
		if ( configuration == null ) {
			configuration = new NetWorkConfiguration( CommonApplication.getAppContext() );
		}
		//增加头部信息
		Interceptor headerInterceptor = new Interceptor() {
			@Override
			public Response intercept( Chain chain ) throws IOException {
				Request build = chain.request()
									 .newBuilder()
									 .addHeader( "Content-Type", "application/json" )
									 .build();
				return chain.proceed( build );
			}
		};
		mBuilder = new OkHttpClient.Builder();
		//                   网络缓存拦截器
		mBuilder.addInterceptor( interceptor )
				.addNetworkInterceptor( new StethoInterceptor() )
				.addNetworkInterceptor( interceptor )
				.addInterceptor( headerInterceptor )
				//                    自定义网络Log显示
				.addInterceptor( new LogInterceptor() )
				.readTimeout( configuration.getReadTimeOut(), TimeUnit.MILLISECONDS )
				.connectTimeout( configuration.getConnectTimeOut(), TimeUnit.MILLISECONDS )
				.connectionPool( configuration.getConnectionPool() )
//                    .cookieJar( new SimpleCookieJar() )
				.retryOnConnectionFailure( true );
//		if(uploadCallback != null) {
//			mBuilder.addNetworkInterceptor( new UploadProgressInterceptor( uploadCallback ) );
//		}
		if ( configuration.getIsCache() ) {
			mOkHttpClient = mBuilder.cache( configuration.getDiskCache() )
									.build();
		} else {
			mOkHttpClient = mBuilder.build();

		}
		//设置cookie
		addCookie();
		/**
		 *
		 *  判断是否在AppLication中配置Https证书
		 *
		 */
		if ( configuration.getCertificates() != null ) {
			mOkHttpClient = getOkHttpClient().newBuilder()
											 .sslSocketFactory( HttpsUtils.getSslSocketFactory( null, null, configuration.getCertificates() ) )
											 .build();
		}
	}

	/**
	 * 设置网络配置参数
	 *
	 * @param configuration
	 */
	public static void setConFiguration( NetWorkConfiguration configuration ) {
		if ( configuration == null ) {
			throw new IllegalArgumentException( "ImageLoader configuration can not be initialized with null" );
		} else {
			if ( HttpUtils.configuration == null ) {
				CommonLog.logd( "Initialize NetWorkConfiguration with configuration" );
				HttpUtils.configuration = configuration;
			} else {
				CommonLog.logd( "Try to initialize NetWorkConfiguration which had already been initialized before. To re-init NetWorkConfiguration " + "with " + "" + "" + "new configuration " );
			}
		}
		if ( configuration != null ) {
			CommonLog.logi( "ConFiguration" + configuration.toString() );
		}
	}

	public RetrofitClient getRetofitClinet() {
		CommonLog.logi( "configuration:" + configuration.toString() );
		return new RetrofitClient( configuration.getBaseUrl(), mOkHttpClient );
	}

	/**
	 * 设置HTTPS客户端带证书访问
	 *
	 * @param certificates 本地证书
	 */
	public HttpUtils setCertificates( InputStream... certificates ) {
		mOkHttpClient = getOkHttpClient().newBuilder()
										 .sslSocketFactory( HttpsUtils.getSslSocketFactory( null, null, certificates ) )
										 .build();
		return this;
	}

	public HttpUtils setCallback( UCallback uploadCallback ) {
		if ( uploadCallback != null ) {
			mOkHttpClient = getOkHttpClient().newBuilder()
											 .addNetworkInterceptor( new UploadProgressInterceptor( uploadCallback ) )
											 .build();
		}
		return this;
	}

	/**
	 * 设置是否打印网络日志
	 *
	 * @param falg
	 */
	public HttpUtils setDBugLog( boolean falg ) {
		if ( falg ) {
			mOkHttpClient = getOkHttpClient().newBuilder()
											 .addNetworkInterceptor( new HttpLoggingInterceptor().setLevel( HttpLoggingInterceptor.Level.BODY ) )
											 .build();
		}
		return this;
	}

	/**
	 * 设置Coolie
	 *
	 * @return
	 */
	public HttpUtils addCookie() {
		persistentCookieJar = new PersistentCookieJar( new SetCookieCache(), new SharedPrefsCookiePersistor( CommonApplication.getAppContext() ) );
		mOkHttpClient = getOkHttpClient().newBuilder()
										 .cookieJar( persistentCookieJar )
										 .build();
		return this;
//        mOkHttpClient = getOkHttpClient().newBuilder()
//                                         .addInterceptor( new AddCookiesInterceptor( BaseApplication.getAppContext(), "ch" ) )
//                                         .addInterceptor( new ReceivedCookiesInterceptor( BaseApplication.getAppContext() ) )
//                                         .build();
//        return this;
	}

	/**
	 * 获得OkHttpClient实例
	 *
	 * @return
	 */
	public OkHttpClient getOkHttpClient() {
		return mOkHttpClient;
	}

	/**
	 * 网络拦截器
	 * 进行网络操作的时候进行拦截
	 */
	final Interceptor interceptor = new Interceptor() {

		private static final String USER_AGENT_HEADER_NAME = "User-Agent";

		@Override
		public Response intercept( Chain chain ) throws IOException {
			Request request = chain.request();
			CacheControl cacheControl = request.cacheControl();
			/**
			 *  断网后是否加载本地缓存数据
			 *
			 */
			if ( !NetworkUtil.isNetAvailable( configuration.context ) && isLoadDiskCache ) {
				request = request.newBuilder()
								 .removeHeader( USER_AGENT_HEADER_NAME )
								 .addHeader( USER_AGENT_HEADER_NAME, userAgentHeaderValue )
								 .cacheControl( CacheControl.FORCE_CACHE )
								 .build();
			}
//            加载内存缓存数据
			else if ( isLoadMemoryCache ) {
				request = request.newBuilder()
								 .removeHeader( USER_AGENT_HEADER_NAME )
								 .addHeader( USER_AGENT_HEADER_NAME, userAgentHeaderValue )
								 .cacheControl( CacheControl.FORCE_CACHE )
								 .build();
			}
			/**
			 *  加载网络数据
			 */
			else {
				request = request.newBuilder()
								 .removeHeader( USER_AGENT_HEADER_NAME )
								 .addHeader( USER_AGENT_HEADER_NAME, userAgentHeaderValue )
								 .cacheControl( CacheControl.FORCE_NETWORK )
								 .build();
			}
			Response response = chain.proceed( request );
//            有网进行内存缓存数据
			/**
			 * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
			 * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
			 */
			if ( NetworkUtil.isNetAvailable( configuration.context ) && configuration.getIsMemoryCache() ) {
				response.newBuilder()

						.header( "Cache-Control", "public, max-age=" + configuration.getmemoryCacheTime() )
						.removeHeader( "Pragma" )
						.build();
			} else {
//              进行本地缓存数据
				/**
				 * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
				 * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
				 */
				if ( configuration.getIsDiskCache() ) {
					response.newBuilder()
							.removeHeader( USER_AGENT_HEADER_NAME )
							.addHeader( USER_AGENT_HEADER_NAME, userAgentHeaderValue )
							.header( "Cache-Control", "public, only-if-cached, max-stale=" + configuration.getDiskCacheTime() )
							.removeHeader( "Pragma" )
							.build();
				}
			}
			return response;
//			Request request = chain.request();
//			String cacheControl = request.cacheControl()
//										 .toString();
//			if ( !NetworkUtil.isNetAvailable( BaseApplication.getAppContext() ) ) {
//				request = request.newBuilder()
//								 .cacheControl( TextUtils.isEmpty( cacheControl ) ? CacheControl.FORCE_NETWORK : CacheControl.FORCE_CACHE )
//								 .build();
//			}
//			Response originalResponse = chain.proceed( request );
//			if ( NetworkUtil.isNetAvailable( BaseApplication.getAppContext() ) ) {
//				//有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
//
//				return originalResponse.newBuilder()
//									   .header( "Cache-Control", cacheControl )
//									   .removeHeader( "Pragma" )
//									   .build();
//			} else {
//				return originalResponse.newBuilder()
//									   .header( "Cache-Control", "public, only-if-cached, max-stale=" + (60 * 60 * 24 * 2) )
//									   .removeHeader( "Pragma" )
//									   .build();
//			}
		}
	};

	public PersistentCookieJar getPersistentCookieJar() {
		return persistentCookieJar;
	}

	/**
	 * 获取请求网络实例
	 *
	 * @return
	 */
	public static HttpUtils getInstance() {
		if ( mInstance == null ) {
			synchronized ( HttpUtils.class ) {
				if ( mInstance == null ) {
					mInstance = new HttpUtils();
				}
			}
		}
		return mInstance;
	}


}
