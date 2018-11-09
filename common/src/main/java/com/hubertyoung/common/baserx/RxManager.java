package com.hubertyoung.common.baserx;


import com.hubertyoung.common.baserx.event.BusManager;
import com.hubertyoung.common.baserx.event.IBus;
import com.hubertyoung.common.baserx.event.inner.EventBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * 用于管理单个presenter的RxBus的事件和Rxjava相关代码的生命周期处理
 */
public class RxManager {
	public IBus mRxBus = BusManager.getInstance();
	//管理rxbus订阅
	private Map< String, Flowable< ? > > mObservables = new HashMap<>();
	/*管理Observables 和 Subscribers订阅*/
	private CompositeDisposable composite = new CompositeDisposable();

	/**
	 * 单纯的Observables 和 Subscribers管理
	 *
	 * @param disposable
	 */
	public void add( Disposable disposable ) {
		/*订阅管理*/
		composite.add( disposable );
	}

	/**
	 * 单个presenter生命周期结束，取消订阅和所有rxbus观察
	 */
	public void clear() {
		composite.clear();// 取消所有订阅
		for (Map.Entry< String, Flowable< ? > > entry : mObservables.entrySet()) {
			mRxBus.unregister( entry.getKey());// 移除rxbus观察
		}
	}

	//发送rxbus
	public void post( Object tag, Object content ) {
		mRxBus.post( new EventBean( tag, content ) );
	}
}
