package com.hubertyoung.common.net.transformer;


import com.hubertyoung.common.net.exception.ExceptionHandle;
import com.hubertyoung.common.net.exception.ServerException;
import com.hubertyoung.common.net.response.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 作者：JIUU on 2017-7-10 16:00:51
 * QQ号：1344393464
 * 作用：异常处理类
 */

public class ErrorTransformer< T > implements ObservableTransformer< BaseResponse< T >, T > {

	public static < T > ErrorTransformer< T > create() {
		return new ErrorTransformer<>();
	}

	private static ErrorTransformer instance = null;

	private ErrorTransformer() {
	}

	/**
	 * 双重校验锁单例(线程安全)
	 */
	public static < T > ErrorTransformer< T > getInstance() {
		if ( instance == null ) {
			synchronized ( ErrorTransformer.class ) {
				if ( instance == null ) {
					instance = new ErrorTransformer();
				}
			}
		}
		return instance;
	}

	@Override
	public ObservableSource< T > apply( @NonNull Observable< BaseResponse< T > > upstream ) {
		return upstream.map( new Function< BaseResponse< T >, T >() {
			@Override
			public T apply( @NonNull BaseResponse< T > tBaseRespose ) throws Exception {
				if ( !tBaseRespose.success() ) {
					throw new ServerException( tBaseRespose.getResult() , tBaseRespose.getStatus() );
				}
//				if ( !tBaseRespose.success() && !tBaseRespose.isFailed() ) {
//					DDLogUtils.loge( "http", tBaseRespose.toString() );
//					//如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
//					throw new ServerException( tBaseRespose.getResult(), tBaseRespose.getStatus() );
//				}
//                //服务器请求数据成功，返回里面的数据实体
				if ( ( tBaseRespose.getData() == null )){
					try {
						return ( T ) tBaseRespose.getResult();
					} catch ( Exception e ) {

					}
				}
				return tBaseRespose.getData();
			}
		} )
					   .onErrorResumeNext( new Function< Throwable, ObservableSource< ? extends T > >() {
						   @Override
						   public ObservableSource< ? extends T > apply( @NonNull Throwable throwable ) throws Exception {
							   throwable.printStackTrace();
							   //如果是测试环境 访问错误证明服务没开 强制改成正式环境
//							   if ( AppUtils.isDebuggable() ) SPUtils.setSharedIntData( "switchUrlIndex", 0 );
							   return Observable.error( ExceptionHandle.handleException( throwable ) );
						   }
					   } );
	}
}
