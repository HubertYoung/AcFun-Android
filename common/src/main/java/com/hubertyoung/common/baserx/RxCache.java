package com.hubertyoung.common.baserx;

import com.hubertyoung.common.utils.cache.ACache;

import java.io.Serializable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * des:处理服务器数据的缓存
 */
//################################使用例子#############################
/*
Flowable<LoginData> fromNetwork = Api.getDefault()
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
	public static < T > Flowable< T > load( final String cacheKey, final int expireTime, Flowable< T > fromNetwork, boolean forceRefresh ) {
		Flowable< T > fromCache = Flowable.create( new FlowableOnSubscribe< T >() {
			@Override
			public void subscribe( FlowableEmitter< T > emitter ) throws Exception {
				//获取缓存
				T cache = ( T ) ACache.get().getAsObject( cacheKey );
				if ( cache != null ) {
					emitter.onNext( cache );
				} else {
					emitter.onComplete();
				}
			}
		}, BackpressureStrategy.BUFFER ).subscribeOn( Schedulers.io() ).observeOn( AndroidSchedulers.mainThread() );
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
			return Flowable.concat( fromCache, fromNetwork ).firstElement().toFlowable();
		}
	}
}
