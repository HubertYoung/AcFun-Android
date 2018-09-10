package com.hubertyoung.pay.platforms.weibo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hubertyoung.pay.authorize.IAuthorize;
import com.hubertyoung.pay.sdk.OnCallback;
import com.hubertyoung.pay.sdk.Platform;
import com.hubertyoung.pay.sdk.ResultCode;
import com.hubertyoung.pay.tools.DDPSDKXmlPullParser;
import com.hubertyoung.pay.tools.Hashon;
import com.hubertyoung.pay.tools.PayLogUtil;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

import java.util.HashMap;



/**
 * Created by ezy on 17/3/18.
 */

public class WBAuth implements IAuthorize {
	public static final String TAG = WBAuth.class.getSimpleName();

	Activity mActivity;
	Platform mPlatform;

	SsoHandler mApi;

	WBAuth( Activity activity, Platform platform ) {
		mActivity = activity;
		mPlatform = platform;
		try {
			WbSdk.checkInit();
		} catch ( Exception e ) {
			WbSdk.install( activity.getApplicationContext(), new AuthInfo( activity.getApplicationContext(), DDPSDKXmlPullParser.getInstance()
																																.getSinaWeiboKey(), DDPSDKXmlPullParser.getInstance()
																																									   .getSinaWeiboRedirectUrl(), WBShare.SCOPE ) );
		}

		mApi = new SsoHandler( mActivity );
//		new AuthInfo( mActivity, platform.getAppId(), platform.extra( "redirectUrl" ), "all" )
	}

	@Override
	public void authorize( @NonNull final OnCallback< String > callback ) {
		callback.onStarted( mActivity );
		mApi.authorize( new WbAuthListener() {
			@Override
			public void onSuccess( Oauth2AccessToken oauth2AccessToken ) {
				PayLogUtil.loge( TAG, oauth2AccessToken.toString() );
				if ( oauth2AccessToken.isSessionValid() ) {
					getUserInfo( mActivity, oauth2AccessToken, callback );
				}
			}

			@Override
			public void cancel() {
				PayLogUtil.loge( TAG, "用户取消了登录" );
				callback.onFailed( mActivity, ResultCode.RESULT_CANCELLED, "ddpsdk_cancel_auth" );
			}

			@Override
			public void onFailure( WbConnectErrorMessage wbConnectErrorMessage ) {
				PayLogUtil.loge( TAG, wbConnectErrorMessage.toString() );
				callback.onFailed( mActivity, ResultCode.RESULT_FAILED, wbConnectErrorMessage.toString() );
			}
		} );
	}

	//                    0 = {HashMap$HashMapEntry@7690} "userID" -> "4C83C7E0DB4A21CE3E618E1A99688CD2"
//                    1 = {HashMap$HashMapEntry@7691} "icon" -> "http://q.qlogo.cn/qqapp/1105436386/4C83C7E0DB4A21CE3E618E1A99688CD2/40"
//                    2 = {HashMap$HashMapEntry@7692} "expiresTime" -> "1504574175778"
//                    3 = {HashMap$HashMapEntry@7693} "secret" ->
//                    4 = {HashMap$HashMapEntry@7694} "iconQzone" -> "http://qzapp.qlogo.cn/qzapp/1105436386/4C83C7E0DB4A21CE3E618E1A99688CD2/100"
//                    5 = {HashMap$HashMapEntry@7695} "nickname" -> "白いバラの夜"
//                    6 = {HashMap$HashMapEntry@7696} "token" -> "4CFA38A1B78002604D996629D868291B"
//                    7 = {HashMap$HashMapEntry@7697} "secretType" -> "0"
//                    8 = {HashMap$HashMapEntry@7698} "gender" -> "0"
//                    9 = {HashMap$HashMapEntry@7699} "pfkey" -> "4e070826d9874f95a7b1ca0135a3e3d2"
//                    10 = {HashMap$HashMapEntry@7700} "pf" -> "desktop_m_qq-10000144-android-2002-"
//                    11 = {HashMap$HashMapEntry@7701} "expiresIn" -> "7776000"
//                    12 = {HashMap$HashMapEntry@7702} "pay_token" -> "D3ECC43B74D0C3BE407D413C3E3485B6"
	public void getUserInfo( Context context, final Oauth2AccessToken oauth2AccessToken, @NonNull final OnCallback< String > callback ) {
		final String openId = oauth2AccessToken.getUid();
		PayLogUtil.loge( TAG, "getUserInfo" );
		//不校验token
//		if ( SocialSSOProxy.isTokenValid( context ) ) {
//		PayLogUtil.loge( TAG, "getUserInfo#isTokenValid true" );
		UsersAPI usersAPI = new UsersAPI( context, DDPSDKXmlPullParser.getInstance()
																	  .getSinaWeiboKey(), oauth2AccessToken );
		usersAPI.show( Long.parseLong( openId ), new RequestListener() {
			@Override
			public void onComplete( String s ) {
				PayLogUtil.logi( TAG, "SocialSSOProxy.loginWeibo#getUserInfo onComplete, \n\r" + s );
				User user = User.parse( s );
				if ( user == null ) {
					callback.onFailed( mActivity, ResultCode.RESULT_FAILED, "Sina user parse error." );
					return;
				}
				HashMap< String, String > map = new HashMap<>();
				map.put( "openid", openId );
				map.put( "icon", user.avatar_hd );
				map.put( "expiresTime", "" );
				map.put( "nickname", user.name );
				map.put( "iconQzone", user.profile_image_url );
				map.put( "token", oauth2AccessToken.getToken() );
				map.put( "secretType", user.verified_type + "" );
				map.put( "gender", TextUtils.equals( "f", user.gender ) ? "0" : "1" );
				map.put( "pfkey", "" );
				map.put( "pf", "" );
				map.put( "expiresIn", "" );
				map.put( "pay_token", "" );
				callback.onCompleted( mActivity );
				final String fromHashMap = new Hashon().fromHashMap( map );
				callback.onSucceed( mActivity, fromHashMap );
			}

			@Override
			public void onWeiboException( WeiboException e ) {
				callback.onFailed( mActivity, -1, e.getMessage()
												   .toString() );
			}
		} );
//		} else {
//			PayLogUtil.loge( TAG, "getUserInfo#isTokenValid false" );
//		}
	}

	@Override
	public void onResult( int requestCode, int resultCode, Intent data ) {
		if ( mApi != null ) {
			mApi.authorizeCallBack( requestCode, resultCode, data );
		}
	}
}
