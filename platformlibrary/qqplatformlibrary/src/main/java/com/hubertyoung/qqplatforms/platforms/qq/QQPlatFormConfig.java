package com.hubertyoung.qqplatforms.platforms.qq;

import android.support.annotation.NonNull;

import com.hubertyoung.baseplatform.AuthorizeSDK;
import com.hubertyoung.baseplatform.ShareSDK;
import com.hubertyoung.baseplatform.authorize.AuthorizeVia;
import com.hubertyoung.baseplatform.platforms.send.SendShare;
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
public class QQPlatFormConfig {
	public static void registerShare( @NonNull String appId, @NonNull String appSecret ) {
		AuthorizeSDK.register( AuthorizeVia.QQ, appId, appSecret, QQAuth.class );

		ShareSDK.register( ShareTo.QQ, appId, appSecret, TXShare.class );
		ShareSDK.register( ShareTo.QZone, appId, appSecret, TXShare.class );
		ShareSDK.register( ShareTo.ToQQ, "", appSecret, SendShare.class );
	}
}
