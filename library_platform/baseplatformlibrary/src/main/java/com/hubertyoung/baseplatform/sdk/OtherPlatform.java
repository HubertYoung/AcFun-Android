package com.hubertyoung.baseplatform.sdk;


import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class OtherPlatform {
    final String name;
    final String appId;

    final String appSecret;

    Map<String, String> extra;

    public OtherPlatform( @NonNull String name, @NonNull String appId, String appSecret ) {
        this.name = name;
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public String getName() {
        return name;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }
    public String extra(String key) {
        return extra == null ? "" : extra.get(key);
    }

    public OtherPlatform extra( String key, String value) {
        if (extra == null) {
            extra = new HashMap<>();
        }
        extra.put(key, value);
        return this;
    }
}
