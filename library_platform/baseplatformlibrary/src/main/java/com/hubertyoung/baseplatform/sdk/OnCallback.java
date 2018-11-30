package com.hubertyoung.baseplatform.sdk;

import android.app.Activity;

import com.hubertyoung.baseplatform.share.shareparam.BaseShareParam;

/**
 * <br>
 * function:方法回调
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/10 16:10
 * @since:V1.0.0
 * @desc:com.hubertyoung.pay.sdk
 */
public interface OnCallback<T> {
    void onStart( Activity activity );
    void onCompleted( Activity activity );
    void onSuccess( Activity activity, T result );
    void onError( Activity activity, int code, String message );

    void onProgress(  Activity activity,BaseShareParam params, T msg );
}
