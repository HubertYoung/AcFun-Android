package com.hubertyoung.pay;

import android.app.Activity;
import android.content.Intent;

import com.hubertyoung.pay.payment.IPayable;
import com.hubertyoung.pay.sdk.DefaultCallback;
import com.hubertyoung.pay.sdk.IFactory;
import com.hubertyoung.pay.sdk.OnCallback;
import com.hubertyoung.pay.sdk.OnSuccess;
import com.hubertyoung.pay.sdk.ResultCode;
import com.hubertyoung.pay.sdk.Sdk;


public class PaymentSDK {
    static Sdk<IPayable > sdk = new Sdk<>();

    public static void setDefaultCallback(OnCallback callback) {
        sdk.setDefaultCallback(callback);
    }

    public static <T extends IPayable> void register(String name, Class<T> clazz) {
        sdk.register(name, "", clazz);
    }

    public static <T extends IPayable>  void register(IFactory<T> factory) {
        sdk.register(factory);
    }

    public static void pay( Activity activity, String platform, String data, OnSuccess<String> listener) {
        pay(activity, platform, data, new DefaultCallback(sdk.getDefaultCallback(), listener));
    }

    public static void pay( Activity activity, String platform, String data, OnCallback<String> callback) {
        if (!sdk.isSupport(platform)) {
            callback.onError( activity, ResultCode.RESULT_FAILED,"ddpsdk_not_supported_auth" );
            return;
        }
        IPayable api = sdk.get(activity, platform);
        if (api == null) {
            return;
        }
        api.pay(data, callback);
    }

    public static void onHandleResult( Activity activity, int requestCode, int resultCode, Intent data) {
        sdk.onHandleResult(activity, requestCode, resultCode, data);
    }
}