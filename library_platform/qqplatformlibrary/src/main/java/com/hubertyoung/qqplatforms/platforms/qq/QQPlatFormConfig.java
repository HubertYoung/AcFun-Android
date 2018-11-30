package com.hubertyoung.qqplatforms.platforms.qq;

import androidx.annotation.NonNull;

import com.hubertyoung.baseplatform.AuthorizeSDK;
import com.hubertyoung.baseplatform.ShareSDK;
import com.hubertyoung.baseplatform.authorize.AuthorizeVia;
import com.hubertyoung.baseplatform.platforms.send.SendShare;
import com.hubertyoung.baseplatform.share.SocializeMedia;

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
public class QQPlatFormConfig {
	public static void registerShare( @NonNull String appId, @NonNull String appSecret ) {
		AuthorizeSDK.register( AuthorizeVia.QQ, appId, appSecret, QQAuth.class );

		ShareSDK.register( SocializeMedia.QQ, appId, appSecret, QQChatShareHandler.class );
		ShareSDK.register( SocializeMedia.QZone, appId, appSecret, QQZoneShareHandler.class );
		ShareSDK.register( SocializeMedia.ToQQ, "", appSecret, SendShare.class );
	}
}
