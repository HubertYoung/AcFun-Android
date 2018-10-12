package com.hubertyoung.common.baserx;

import com.hubertyoung.common.utils.ACache;

import java.io.Serializable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * des:处理服务器数据的缓存
 */
//################################使用例子#############################
/*
Observable<LoginData> fromNetwork = Api.getDefault()
        .login(phone, password)
        .compose(RxHelper.handleResult());

        RxCache.load(context,cacheKey,1000*60*30,fromNetwork,false)
        .subscribe(new RxSubscribe<LoginData>(context, "登录中...") {
@Override
protected void _onNext(LoginData data) {
        showToast(R.string.login_success);
        }

@Override
protected void _onError(String message) {
        showToast(message);
        }
        });
 */


public class RxCache {
	/**
	 * @param cacheKey
	 * @param expireTime
	 * @param fromNetwork
	 * @param forceRefresh
	 * @param <T>
	 * @return
	 */
	public static < T > Observable< T > load( final String cacheKey, final int expireTime, Observable< T > fromNetwork, boolean forceRefresh ) {
		Observable< T > fromCache = Observable.create( new ObservableOnSubscribe< T >() {
			@Override
			public void subscribe( ObservableEmitter< T > emitter ) throws Exception {
				//获取缓存
				T cache = ( T ) ACache.get().getAsObject( cacheKey );
				if ( cache != null ) {
					emitter.onNext( cache );
				} else {
					emitter.onComplete();
				}
			}
		} ).subscribeOn( Schedulers.io() ).observeOn( AndroidSchedulers.mainThread() );
		/**
		 * 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
		 */
		fromNetwork = fromNetwork.map( new Function< T, T >() {
			@Override
			public T apply( T t ) throws Exception {
				//保存缓存
				ACache.get().put( cacheKey, ( Serializable ) t, expireTime );
				return t;
			}
		} );
		//强制刷新则返回接口数据
		if ( forceRefresh ) {
			return fromNetwork;
		} else {
			//优先返回缓存
			return Observable.concat( fromCache, fromNetwork ).firstElement().toObservable();
		}
	}
}
