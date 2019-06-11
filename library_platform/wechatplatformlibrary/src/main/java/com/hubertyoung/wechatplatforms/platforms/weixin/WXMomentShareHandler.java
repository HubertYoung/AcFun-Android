package com.hubertyoung.wechatplatforms.platforms.weixin;

import android.app.Activity;

import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamImage;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamWebPage;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * Time::2018/9/14 15:34
 * @since:V1.0.0
 * Pkg:com.hubertyoung.wechatplatforms.platforms.weixin
 */
public class WXMomentShareHandler extends BaseWxShareHandler {
	WXMomentShareHandler( Activity activity, OtherPlatform platform ) {
		super( activity, platform );
	}

    @Override
    public void shareImage( ShareParamImage params, OnCallback mCallback ) {
        if (params.getImage() != null && (!params.getImage().isUnknowImage())) {
            super.shareImage(params,mCallback);
        } else {
            ShareParamWebPage webpage = new ShareParamWebPage(params.getTitle(), params.getContent(), params.getTargetUrl());
            webpage.setThumb(params.getImage());
            shareWebPage(webpage,mCallback);
        }
    }
}

