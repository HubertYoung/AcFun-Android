package com.hubertyoung.common.net.transformer;


import com.hubertyoung.common.net.exception.ServerException;
import com.hubertyoung.common.net.response.BaseResponse;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 作者：JIUU on 2017-7-10 16:00:51
 * QQ号：1344393464
 * 作用：异常处理类
 */

public class ErrorTransformer< T > implements FlowableTransformer< BaseResponse< T >, T > {

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
	public Publisher< T > apply( Flowable< BaseResponse< T > > upstream ) {
		return upstream.map( new Function< BaseResponse< T >, T >() {
			@Override
			public T apply( @NonNull BaseResponse< T > tBaseRespose ) throws Exception {
				if ( !tBaseRespose.success() ) {
					throw new ServerException( tBaseRespose.getErrordesc() , tBaseRespose.getStatus() );
				}
//                //服务器请求数据成功，返回里面的数据实体
				if ( ( tBaseRespose.getData() == null )){
					try {
						return ( T ) tBaseRespose.getErrordesc();
					} catch ( Exception e ) {

					}
				}
				return tBaseRespose.getData();
			}
		} );
//				.onErrorResumeNext( new Function< Throwable, Publisher< ? extends T > >() {
//					@Override
//					public Publisher< ? extends T > apply( Throwable throwable ) throws Exception {
//						throwable.printStackTrace();
//						//如果是测试环境 访问错误证明服务没开 强制改成正式环境
////							   if ( AppUtils.isDebuggable() ) SPUtils.setSharedIntData( "switchUrlIndex", 0 );
//						return Flowable.error( ExceptionHandle.handleException( throwable ) );
//					}
//				} );
	}
}
