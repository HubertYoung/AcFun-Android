package com.hubertyoung.pay.platforms.weibo;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hubertyoung.pay.sdk.IResult;
import com.hubertyoung.pay.sdk.OnCallback;
import com.hubertyoung.pay.sdk.OtherPlatform;
import com.hubertyoung.pay.sdk.ResultCode;
import com.hubertyoung.pay.share.IShareable;
import com.hubertyoung.pay.share.ShareData;
import com.hubertyoung.pay.share.image.resource.ImageResource;
import com.hubertyoung.pay.share.media.MoImage;
import com.hubertyoung.pay.tools.DDPSDKXmlPullParser;
import com.hubertyoung.pay.tools.PayLogUtil;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;

import java.util.Map;
import java.util.WeakHashMap;


/**
 * Created by ezy on 17/3/18.
 */

public class WBShare implements IShareable, WbShareCallback {
	public static final String TAG = WBShare.class.getSimpleName();
	/**
	 * Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博
	 * 核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权页中有权利
	 * 选择赋予应用的功能。
	 * <p>
	 * 我们通过新浪微博开放平台-->管理中心-->我的应用-->接口管理处，能看到我们目前已有哪些接口的
	 * 使用权限，高级权限需要进行申请。
	 * <p>
	 * 目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
	 * <p>
	 * 有关哪些 OpenAPI 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
	 * 关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
	 */
	public static final String SCOPE = "email,direct_messages_read,direct_messages_write," + "friendships_groups_read,friendships_groups_write,statuses_to_me_read," + "follow_app_official_microblog," +
			"" + "invitation_write";
	public static int REQUEST_CODE = 1998;

	static Map< IResult, Boolean > services = new WeakHashMap<>();


	Activity mActivity;
	OtherPlatform mPlatform;

	WbShareHandler mApi;
	OnCallback< String > mCallback;

	WBShare( Activity activity, OtherPlatform platform ) {
		mActivity = activity;
		mPlatform = platform;
		try {
			WbSdk.checkInit();
		} catch ( Exception e ) {
			WbSdk.install( activity.getApplicationContext(), new AuthInfo( activity.getApplicationContext(), DDPSDKXmlPullParser.getInstance().getSinaWeiboKey(), DDPSDKXmlPullParser.getInstance()
					.getSinaWeiboRedirectUrl(), WBShare.SCOPE ) );
		}

		mApi = new WbShareHandler( mActivity );
		mApi.registerApp();
		mApi.setProgressColor( 0xff33b5e5 );
		services.put( this, true );
	}

	TextObject toText( String text ) {
		TextObject object = new TextObject();
		object.text = text;
		return object;
	}

	ImageObject toImage( ImageResource resource ) {
		ImageObject object = new ImageObject();
		object.imagePath = resource.toUri();
		if ( TextUtils.isEmpty( object.imagePath ) ) {
			object.imageData = resource.toBytes();
		}
		return object;
	}

	@Override
	public void share( @NonNull final ShareData data, @NonNull final OnCallback< String > callback ) {

//        if (!mApi.isWeiboAppInstalled()) {
//            callback.onError(mActivity, ResultCode.RESULT_FAILED, "您未安装微博!");
//            return;
//        }
		WeiboMultiMessage message = new WeiboMultiMessage();

		if ( data.hasText() ) {
			message.textObject = toText( data.hasUrl() ? ( data.text + " " + data.url ) : data.text );
		}

		if ( data.media instanceof MoImage ) {
			message.imageObject = toImage( ( ( MoImage ) data.media ).resource );
		}

		if ( message.textObject == null && message.imageObject == null ) {
			// unsupported
			callback.onError( mActivity, ResultCode.RESULT_FAILED, "不支持的分享类型" );
			return;
		}

		mCallback = callback;
		mCallback.onStart( mActivity );
		mApi.shareMessage( message, false );
//		SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
//		request.transaction = String.valueOf( System.currentTimeMillis() );
//		request.multiMessage = message;
//		request.packageName = mActivity.getPackageName();
//				AuthInfo authInfo = new AuthInfo( mActivity, DDPSDKXmlPullParser.getInstance()
//																				.getSinaWeiboKey(), DDPSDKXmlPullParser.getInstance()
//																													   .getSinaWeiboRedirectUrl(), "all" );
//				Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken( mActivity );
//				String token = "";
//				if ( accessToken != null ) {
//					token = accessToken.getToken();
//				}
//		mApi.sendRequest( mActivity, request );
	}


	@Override
	public void onResult( int requestCode, int resultCode, Intent data ) {
		PayLogUtil.loge( TAG, "==> requestCode = " + requestCode + ", " + data );
		if ( mApi != null && requestCode == REQUEST_CODE ) {
			mApi.doResultIntent( data, this );
		}
	}

	@Override
	public void onWbShareSuccess() {
		mCallback.onCompleted( mActivity );
		mCallback.onSuccess( mActivity, "" );
	}

	@Override
	public void onWbShareCancel() {
		mCallback.onCompleted( mActivity );
		mCallback.onError( mActivity, ResultCode.RESULT_CANCELLED, TAG + "==> onWbShareCancel()" );
	}

	@Override
	public void onWbShareFail() {
		mCallback.onCompleted( mActivity );
		mCallback.onError( mActivity, ResultCode.RESULT_FAILED, TAG + "==> onWbShareFail()" );
	}
}
