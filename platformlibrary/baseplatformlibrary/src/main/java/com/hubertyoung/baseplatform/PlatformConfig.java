//package com.hubertyoung.baseplatform;
//
//import android.support.annotation.NonNull;
//import android.support.v4.app.FragmentActivity;
//
//import com.hubertyoung.baseplatform.authorize.AuthorizeVia;
//import com.hubertyoung.baseplatform.payment.PaymentVia;
//import com.hubertyoung.baseplatform.platforms.send.SendShare;
//import com.hubertyoung.baseplatform.sdk.DefaultFactory;
//import com.hubertyoung.baseplatform.sdk.OtherPlatform;
//import com.hubertyoung.baseplatform.share.ShareTo;
//
///**
// * @author:Yang
// * @date:2017/11/17 13:38
// * @since:V1.0.0
// * @desc:ddframework.gent.common.framwork
// * @param:
// */
//public class PlatformConfig {
//
//    public static void useWeibo( @NonNull String appId, @NonNull String redirectUrl) {
//        OtherPlatform platform = new OtherPlatform( AuthorizeVia.Weibo, appId).extra("redirectUrl", redirectUrl);
//        AuthorizeSDK.register(new DefaultFactory(platform, WBAuth.class));
//        ShareSDK.register(new DefaultFactory(platform, WBShare.class));
//    }
//
//    public static void useQQ(@NonNull String appId) {
//        AuthorizeSDK.register(AuthorizeVia.QQ, appId, QQAuth.class);
//
//        ShareSDK.register( ShareTo.QQ, appId, TXShare.class);
//        ShareSDK.register(ShareTo.QZone, appId, TXShare.class);
//        ShareSDK.register(ShareTo.ToQQ, "", SendShare.class);
//    }
//
//    public static void useWeixin(@NonNull String appId) {
//        AuthorizeSDK.register(AuthorizeVia.Weixin, appId, WXAuth.class);
//
//        ShareSDK.register(ShareTo.WXSession, appId, WXShare.class);
//        ShareSDK.register(ShareTo.WXTimeline, appId, WXShare.class);
//        ShareSDK.register(ShareTo.WXFavorite, appId, WXShare.class);
//        ShareSDK.register(ShareTo.ToWXSession, "", SendShare.class);
//        ShareSDK.register(ShareTo.ToWXTimeline, "", SendShare.class);
//    }
//    public static void useFaceBook() {
////        AuthorizeSDK.register(AuthorizeVia.Facebook, "", FaceBookAuth.class);
//
////        ShareSDK.register( ShareTo.QQ, appId, TXShare.class);
////        ShareSDK.register(ShareTo.QZone, appId, TXShare.class);
////        ShareSDK.register(ShareTo.ToQQ, "", SendShare.class);
//    }
//    public static void useGoogle( @NonNull String appId ) {
////        AuthorizeSDK.register(AuthorizeVia.Google, appId, GoogleAuth.class);
//    }
//    public static void useTwitter( @NonNull String appId ) {
////        AuthorizeSDK.register(AuthorizeVia.Twitter, appId, TwitterAuth.class);
//    }
//    public static void usePayments( FragmentActivity activity ) {
//        PaymentSDK.register( PaymentVia.Wxpay, WXPayment.class);
//        EnvUtils.setEnv( EnvUtils.EnvEnum.SANDBOX);
//        PaymentSDK.register(PaymentVia.Alipay, Alipay.class);
////        GooglePay.init(activity);
////        PaymentSDK.register(PaymentVia.Googlepay, GooglePay.class);
//    }
//}
