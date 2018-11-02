package com.hubertyoung.common.baserx;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava调度管理
 */
public class RxSchedulers {
	public static < T > FlowableTransformer< T, T > io_main() {
		return new FlowableTransformer< T, T >() {
			@Override
			public Publisher< T > apply( Flowable< T > upstream ) {
				return upstream.subscribeOn( Schedulers.io() ).doOnSubscribe( new Consumer< Subscription >() {
					@Override
					public void accept( Subscription subscription ) throws Exception {

					}
				} ).observeOn( AndroidSchedulers.mainThread() );
			}
		};
	}
}
