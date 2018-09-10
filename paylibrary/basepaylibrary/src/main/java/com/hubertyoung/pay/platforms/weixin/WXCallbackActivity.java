package com.hubertyoung.pay.platforms.weixin;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hubertyoung.pay.sdk.IResult;
import com.hubertyoung.pay.tools.PayLogUtil;


public class WXCallbackActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
		PayLogUtil.loge(WXBase.TAG, "==> " + ((getIntent() == null) ? "" : getIntent().getExtras()));

        for (IResult service: WXBase.services.keySet()) {
            if (service != null) {
                service.onResult(WXBase.REQUEST_CODE, Activity.RESULT_OK, intent);
            }
        }
        finish();
    }
}