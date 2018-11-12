package com.hubertyoung.common.baserx;


import com.google.gson.JsonParseException;
import com.hubertyoung.common.net.exception.ResponeThrowable;
import com.hubertyoung.common.net.exception.ServerException;
import com.hubertyoung.common.utils.os.NetworkUtil;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.HttpException;

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
public abstract class RxSubscriber<T> extends DisposableSubscriber<T> {

    @Override
    protected void onStart() {
        super.onStart();
        showLoading();
        if (!NetworkUtil.isNetAvailable()) {
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
    public void onError(Throwable e) {
        String message = null;
        if (e instanceof UnknownHostException ) {
            message = "没有网络";
        } else if (e instanceof HttpException) {
            message = "网络错误";
        } else if (e instanceof SocketTimeoutException ) {
            message = "网络连接超时";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException ) {
            message = "解析错误";
        } else if (e instanceof ConnectException ) {
            message = "连接失败";
        } else if (e instanceof ServerException ) {
            message = ((ServerException) e).result;
        }else if ( e instanceof ResponeThrowable ){
            message = ((ResponeThrowable) e).result;
        }
        finishLoading();
        onFailure(message);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    /**
     * success
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * failure
     *
     * @param msg
     */
    public abstract void onFailure(String msg);
}
