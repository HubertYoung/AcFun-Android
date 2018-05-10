package com.hubertyoung.common.baserx;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava调度管理
 */
public class RxSchedulers {
	public static < T > ObservableTransformer< T, T > io_main() {
		return upstream -> upstream.subscribeOn( Schedulers.io() )
								   .doOnSubscribe( disposable -> {
									   //操作
								   } )
								   .observeOn( AndroidSchedulers.mainThread() );
	}
}
