package com.hubertyoung.wechatplatforms.platforms.weixin;

import android.support.annotation.NonNull;

import com.hubertyoung.baseplatform.AuthorizeSDK;
import com.hubertyoung.baseplatform.PaymentSDK;
import com.hubertyoung.baseplatform.ShareSDK;
import com.hubertyoung.baseplatform.authorize.AuthorizeVia;
import com.hubertyoung.baseplatform.payment.PaymentVia;
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
public class WechatPlatFormConfig {
	public static void registerShare( @NonNull String appId, @NonNull String appSecret ) {
		AuthorizeSDK.register( AuthorizeVia.Wechat, appId, appSecret, WXAuth.class );

		ShareSDK.register( SocializeMedia.WXSession, appId, appSecret, WXChatShareHandler.class );
		ShareSDK.register( SocializeMedia.WXTimeline, appId, appSecret, WXMomentShareHandler.class );
		ShareSDK.register( SocializeMedia.WXFavorite, appId, appSecret, WXFavoriteShareHandler.class );
		ShareSDK.register( SocializeMedia.ToWXSession, appId, appSecret, SendShare.class );
		ShareSDK.register( SocializeMedia.ToWXTimeline, appId, appSecret, SendShare.class );
	}

	public static void registerPay() {
		PaymentSDK.register( PaymentVia.Wxpay, WXPayment.class );
	}
}
