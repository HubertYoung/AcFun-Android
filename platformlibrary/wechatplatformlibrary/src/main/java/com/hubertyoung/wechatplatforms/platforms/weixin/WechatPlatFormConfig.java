package com.hubertyoung.wechatplatforms.platforms.weixin;

import android.support.annotation.NonNull;

import com.hubertyoung.baseplatform.AuthorizeSDK;
import com.hubertyoung.baseplatform.PaymentSDK;
import com.hubertyoung.baseplatform.ShareSDK;
import com.hubertyoung.baseplatform.authorize.AuthorizeVia;
import com.hubertyoung.baseplatform.payment.PaymentVia;
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
public class WechatPlatFormConfig {
	public static void registerShare( @NonNull String appId ) {
		AuthorizeSDK.register( AuthorizeVia.Weixin, appId, WXAuth.class );

		ShareSDK.register( ShareTo.WXSession, appId, WXShare.class );
		ShareSDK.register( ShareTo.WXTimeline, appId, WXShare.class );
		ShareSDK.register( ShareTo.WXFavorite, appId, WXShare.class );
		ShareSDK.register( ShareTo.ToWXSession, "", SendShare.class );
		ShareSDK.register( ShareTo.ToWXTimeline, "", SendShare.class );
	}
	public static void registerPay( ) {
		PaymentSDK.register( PaymentVia.Wxpay, WXPayment.class);
	}
}
