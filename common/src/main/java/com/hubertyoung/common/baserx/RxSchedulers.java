package com.hubertyoung.common.baserx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava调度管理
 */
public class RxSchedulers {
	public static < T > ObservableTransformer< T, T > io_main() {
		return new ObservableTransformer< T, T >() {
			@Override
			public ObservableSource< T > apply( Observable< T > upstream ) {
				return upstream.subscribeOn( Schedulers.io() )
							   .doOnSubscribe( new Consumer< Disposable >() {
								   @Override
								   public void accept( Disposable disposable ) throws Exception {

								   }
							   } )
						.observeOn( AndroidSchedulers.mainThread() );
			}
		};
	}
}
