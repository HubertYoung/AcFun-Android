package com.hubertyoung.common.baserx;


import com.hubertyoung.common.net.exception.ExceptionHandle;
import com.hubertyoung.common.net.exception.ResponeThrowable;
import com.hubertyoung.common.utils.os.NetworkUtil;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/2 16:46
 * @since:V1.0.0
 * @desc:com.hubertyoung.common.baserx
 */
public abstract class RxSubscriber< T > extends DisposableSubscriber< T > {

    public RxSubscriber() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLoading();
        if ( !NetworkUtil.isNetAvailable() ) {
            onNoNetWork();
            cancel();
            return;
        }
    }

    @Override
    public void onComplete() {
        finishLoading();
    }

    protected void finishLoading() {
    }

    protected void showLoading() {

    }

    protected void onNoNetWork() {

    }

    @Override
    public void onError( Throwable e ) {
        ResponeThrowable responeThrowable = ExceptionHandle.handleException( e );
        finishLoading();
        onFailure( responeThrowable.result );
    }

    @Override
    public void onNext( T t ) {
        onSuccess( t );
    }

    /**
     * success
     *
     * @param t
     */
    public abstract void onSuccess( T t );

    /**
     * failure
     *
     * @param msg
     */
    public abstract void onFailure( String msg );
}
