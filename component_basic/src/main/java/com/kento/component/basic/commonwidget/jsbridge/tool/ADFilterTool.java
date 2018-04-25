package com.kento.component.basic.commonwidget.jsbridge.tool;

import android.content.Context;
import android.content.res.Resources;

import com.kento.component.basic.R;


/**
 * 作者：JIUU on 2017/6/22 17:53
 * QQ号：1344393464
 * 作用：广告拦截工具
 */
public class ADFilterTool {
    public static boolean hasAd( Context context, String url) {
        Resources res = context.getResources();
        String[] adUrls = res.getStringArray( R.array.adBlockUrl);
        for (String adUrl : adUrls) {
            if (url.contains(adUrl)) {
                return true;
            }
        }
        return false;
    }
}