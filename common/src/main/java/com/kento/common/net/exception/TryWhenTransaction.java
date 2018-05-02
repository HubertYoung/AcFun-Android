package com.kento.common.net.exception;//package ddframework.gent.common.net.exception;
//
//import java.net.UnknownHostException;
//import java.util.concurrent.TimeUnit;
//
//import ddframework.gent.common.commonutils.CommonLog;
//import io.reactivex.Observable;
//import io.reactivex.ObservableSource;
//import io.reactivex.functions.Function;
//
///**
// * <br>
// * function:
// * <p>
// *
// * @author:Yang
// * @date:2018/3/7 下午7:01
// * @since:V$VERSION
// * @desc:ddframework.gent.common.net.exception
// */
//public class TryWhenTransaction implements Function< Observable< Object >, ObservableSource< ? > > {
//	/***
//	 * 重试间隔时间
//	 */
//	private long mInterval;
//	private int retryCount = 0;
//
//	public TryWhenTransaction( long interval ) {
//		mInterval = interval;
//	}
//
//	@Override
//	public ObservableSource< ? > apply( Observable< Object > objectObservable ) throws Exception {
//		return objectObservable.flatMap( new Function< Object, ObservableSource<?> >() {
//			@Override
//			public ObservableSource< ? > apply( Object o ) throws Exception {
//
//					if ( throwable instanceof UnknownHostException ) {
//						//若没打开网络则停止重试
//						return Observable.error( throwable );
//					} else if ( throwable instanceof NullPointerException ) CommonLog.loge( throwable.getMessage()
//																											 .toString() );
//					//重试三次
//					if ( ++retryCount < 3 ) return Observable.timer( 5, TimeUnit.SECONDS );
//					else return Observable.error( new TryWhenException() );//超过最大次数终止
//				}
//			}
//		} );
//	}
//}
