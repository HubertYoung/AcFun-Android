package com.hubertyoung.pay.platforms.weixin;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hubertyoung.pay.authorize.IAuthorize;
import com.hubertyoung.pay.sdk.OnCallback;
import com.hubertyoung.pay.sdk.OtherPlatform;
import com.hubertyoung.pay.sdk.ResultCode;
import com.hubertyoung.pay.tools.DDPSDKXmlPullParser;
import com.hubertyoung.pay.tools.Hashon;
import com.hubertyoung.pay.tools.PayLogUtil;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;



/**
 * Created by ezy on 17/3/18.
 */

public class WXAuth extends WXBase implements IAuthorize {

	private HashMap< String, String > data;

	WXAuth( Activity activity, OtherPlatform platform ) {
		super( activity, platform );
	}


	@Override
	public void authorize( @NonNull OnCallback< String > callback ) {
		mCallback = callback;
		if ( !mApi.isWXAppInstalled() ) {
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, "ddpsdk_error_operate wechat" );
			return;
		}

		SendAuth.Req req = new SendAuth.Req();
		req.scope = "snsapi_userinfo";
		req.state = "wechat";

		PayLogUtil.loge( TAG, "send start" );
		mCallback.onStart( mActivity );
		if ( !this.mApi.sendReq( req ) ) {
			callback.onError( mActivity, -1, "sendReq fail" );
			PayLogUtil.loge( "wxapi sendReq fail" );
		}
		PayLogUtil.loge( "send end " + req.scope );
	}

	@Override
	protected void onResultOk( SendAuth.Resp resp ) {
		switch ( resp.errCode ) {
			case BaseResp.ErrCode.ERR_OK:       //授权成功
				if ( data == null ) {
					data = new HashMap< String, String >();
				} else {
					data.clear();
				}

//				data.put( "code", resp.code );
//
//				String fromHashMap = new Hashon().fromHashMap( data );
				getToken( resp.code );
//				if ( mCallback != null ) {
//					mCallback.onSuccess( mActivity, fromHashMap );
//				}
				break;

			case BaseResp.ErrCode.ERR_USER_CANCEL:      //授权取消
				if ( mCallback != null ) {
					mCallback.onError( mActivity, resp.errCode, resp.errStr );
				}
				break;

			default:    //授权失败
				CharSequence err = TextUtils.concat( new CharSequence[]{ "weixin auth error (", String.valueOf( resp.errCode ), "):", resp.errStr } );
				if ( mCallback != null ) {
					mCallback.onError( mActivity, resp.errCode, resp.errStr );
				}
				break;
		}


	}

	public void getToken( final String code ) {

		final String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + DDPSDKXmlPullParser.getInstance()
																												.getWechatID() + "&secret=" + DDPSDKXmlPullParser.getInstance()
																																								 .getWechatSecret() + "&code=%s&grant_type=authorization_code";

		new Thread( new Runnable() {
			@Override
			public void run() {
				try {
					URL url = new URL( String.format( tokenUrl, code ) );
					HttpURLConnection urlConn = ( HttpURLConnection ) url.openConnection();
					InputStream in = new BufferedInputStream( urlConn.getInputStream() );
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
					String result = inputStream2String( in );
//					JSONObject info = new JSONObject( result );
					//当前token的有效市场只有7200s，需要利用refresh_token去获取新token，考虑当前需要利用token的只有获取用户信息，手动设置token超时为30天
//						SocialToken token = new SocialToken(info.getString("openid"), info.getString("access_token"), info.getString("refresh_token"), /*info.getLong("expires_in")*/ 30 * 24 * 60 *
// 60);
//						token.setResult(result);
					getUserInfo( result );
				} catch ( Exception e ) {
					if ( mCallback != null ) {
						mCallback.onError( mActivity, -1, e.getMessage()
															.toString() );
					}
				}
			}
		} ).start();

	}

	public void getUserInfo( final String resultToken ) {
		final String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
//		if (SocialSSOProxy.isTokenValid(context)) {// TODO: 2017/9/9 先不校验token
		new Thread( new Runnable() {
			@Override
			public void run() {
				try {
					JSONObject resultObject = new JSONObject( resultToken );
					String openid = resultObject.getString( "openid" );
					String accessToken = resultObject.getString( "access_token" );
					String refreshToken = resultObject.getString( "refresh_token" );
					URL url = new URL( String.format( userInfoUrl, accessToken, openid ) );
					HttpURLConnection urlConn = ( HttpURLConnection ) url.openConnection();
					InputStream in = new BufferedInputStream( urlConn.getInputStream() );

					String result = inputStream2String( in );
					JSONObject info = new JSONObject( result );
//					String name = info.getString( "nickname" );
//					int gender = info.getInt( "sex" );
//					String icon = info.getString( "headimgurl" );

					data.put( "openid", openid );
					data.put( "token", accessToken );
					data.put( "pfkey", "" );
					data.put( "pf", "" );
					data.put( "expiresIn", "" );
					data.put( "pay_token", "" );
					data.put( "icon", info.optString( "headimgurl" ) );
					data.put( "iconQzone", "" );
					data.put( "nickname", info.optString( "nickname" ) );
					data.put( "gender", info.optInt( "sex" ) + "" );
					final String fromHashMap = new Hashon().fromHashMap( data );
					new Handler( Looper.getMainLooper() ).post( new Runnable() {
						@Override
						public void run() {
							if ( mCallback != null ) {
								mCallback.onSuccess( mActivity, fromHashMap );
							}
						}
					} );
				} catch ( Exception e ) {
					if ( mCallback != null ) {
						mCallback.onError( mActivity, -1, e.getMessage()
															.toString() );
					}
				}
			}
		} ).start();
//		}
	}

	private static String inputStream2String( InputStream is ) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i;
		while ( ( i = is.read() ) != -1 ) {
			baos.write( i );
		}
		return baos.toString();
	}
}
