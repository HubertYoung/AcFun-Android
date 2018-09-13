package com.hubertyoung.weiboplatforms.platforms.weibo;

import android.support.annotation.NonNull;

import com.hubertyoung.baseplatform.AuthorizeSDK;
import com.hubertyoung.baseplatform.PlatformSDKConfig;
import com.hubertyoung.baseplatform.ShareSDK;
import com.hubertyoung.baseplatform.sdk.DefaultFactory;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.share.ShareTo;

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
	public static void registerShare(  @NonNull String appId,@NonNull String appSecret, @NonNull String redirectUrl) {
		OtherPlatform platform = new OtherPlatform( ShareTo.Weibo, appId, appSecret ).extra( PlatformSDKConfig.REDIRECTURL, redirectUrl);
		AuthorizeSDK.register(new DefaultFactory(platform, WBAuth.class));
		ShareSDK.register(new DefaultFactory(platform, WBShare.class));
	}
}
