package com.hubertyoung.baseplatform.sdk;

import android.app.Activity;

import com.hubertyoung.baseplatform.share.shareparam.BaseShareParam;


public class DefaultCallback< T > implements OnCallback< T > {
    OnCallback< T > onCallback;
    OnSuccess< T > onSuccess;

    public DefaultCallback( OnCallback< T > callback, OnSuccess< T > success) {
        onCallback = callback;
        onSuccess = success;
    }

    @Override
    public void onStart( Activity activity) {
        if (onCallback != null) {
            onCallback.onStart(activity);
        }
    }

    @Override
    public void onCompleted(Activity activity) {
        if (onCallback != null) {
            onCallback.onCompleted(activity);
        }
    }

    @Override
    public void onError( Activity activity, int code, String message) {
        if (onCallback != null) {
            onCallback.onError(activity, code, message);
        }
    }

    @Override
    public void onProgress( Activity activity, BaseShareParam params, T msg ) {
        if (onCallback != null) {
            onCallback.onProgress(activity, params, msg);
        }
    }

    @Override
    public void onSuccess( Activity activity, T result) {
        if (onCallback != null) {
            onCallback.onSuccess(activity, result);
        }
        if (onSuccess != null) {
            onSuccess.onSuccess(result);
        }
    }
}
