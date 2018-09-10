package com.hubertyoung.weiboplatforms.platforms.weibo;

import android.support.annotation.NonNull;

import com.hubertyoung.baseplatform.AuthorizeSDK;
import com.hubertyoung.baseplatform.ShareSDK;
import com.hubertyoung.baseplatform.authorize.AuthorizeVia;
import com.hubertyoung.baseplatform.sdk.DefaultFactory;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/10 18:20
 * @since:V1.0.0
 * @desc:com.hubertyoung.qqplatforms.platforms.qq
 */
public class WeiboPlatFormConfig {
	public static void registerShare(  @NonNull String appId, @NonNull String redirectUrl) {
		OtherPlatform platform = new OtherPlatform( AuthorizeVia.Weibo, appId).extra("redirectUrl", redirectUrl);
		AuthorizeSDK.register(new DefaultFactory(platform, WBAuth.class));
		ShareSDK.register(new DefaultFactory(platform, WBShare.class));
	}
}
