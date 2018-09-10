package com.hubertyoung.qqplatforms.platforms.qq;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hubertyoung.baseplatform.authorize.IAuthorize;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.tools.Hashon;
import com.hubertyoung.baseplatform.tools.PayLogUtil;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.util.HashMap;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/10 18:06
 * @since:V1.0.0
 * @desc:com.hubertyoung.qqplatforms.platforms.qq
 */
public class QQAuth implements IAuthorize {
	public static final String TAG = QQAuth.class.getSimpleName();

	Activity mActivity;
	OtherPlatform mPlatform;
	Tencent mApi;

	IUiListener mListener;

	private HashMap< String, String > data;

	QQAuth( Activity activity, OtherPlatform platform ) {
		mActivity = activity;
		mPlatform = platform;
		mApi = Tencent.createInstance( platform.getAppId(), mActivity );
	}

	String toMessage( UiError error ) {
		return "[" + error.errorCode + "]" + error.errorMessage;
	}

	@Override
	public void authorize( @NonNull final OnCallback< String > callback ) {
		mListener = new IUiListener() {
			@Override
			public void onComplete( final Object response ) {
				PayLogUtil.loge(TAG,"complete ==> " + response );
				if ( response instanceof JSONObject && ( ( JSONObject ) response ).length() > 0 ) {
					JSONObject jo = ( JSONObject ) response;
					if( data == null) {
						data = new HashMap<String,String>();
					}else{
						data.clear();
					}
					String token = jo.optString( Constants.PARAM_ACCESS_TOKEN );
					String expires = jo.optString( Constants.PARAM_EXPIRES_IN );
					String pfkey = jo.optString( "pfkey" );
					String pf = jo.optString( Constants.PARAM_PLATFORM_ID );
					String expiresIn = jo.optString( Constants.PARAM_EXPIRES_IN );
					String pay_token = jo.optString( "pay_token" );
					final String openId = jo.optString( Constants.PARAM_OPEN_ID );
					data.put( Constants.PARAM_OPEN_ID, openId );
					data.put( "token", token );
					data.put( "pfkey", pfkey );
					data.put( "pf", pf );
					data.put( "expiresIn", expiresIn );
					data.put( "pay_token", pay_token );

					if ( !TextUtils.isEmpty( token ) && !TextUtils.isEmpty( expires ) && !TextUtils.isEmpty( openId ) ) {
						mApi.setAccessToken( token, expires );
						mApi.setOpenId( openId );
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
//                    new PlatformDb(  )

					UserInfo userInfo = new UserInfo( mActivity, mApi.getQQToken() );
					userInfo.getUserInfo( new IUiListener() {
						@Override
						public void onComplete( Object o ) {

							JSONObject jo = ( JSONObject ) o;

							data.put( "icon", jo.optString( "figureurl_qq_1" ) );
							data.put( "iconQzone", jo.optString( "figureurl_qq_2" ) );
							data.put( "nickname", jo.optString( "nickname" ) );
							data.put( "gender", TextUtils.equals( "男", jo.optString( "gender" ) ) ? "0" : "1");
							String fromHashMap = new Hashon().fromHashMap( data );
							if ( callback != null ) {
								callback.onSuccess( mActivity, fromHashMap );
							}
						}

						@Override
						public void onError( UiError uiError ) {
							PayLogUtil.loge(TAG, "error ==> " + uiError.errorDetail );
							if ( callback != null ) {
								callback.onError( mActivity, ResultCode.RESULT_FAILED, toMessage( uiError ) );
							}
						}

						@Override
						public void onCancel() {
							if ( callback != null ) {
								callback.onError( mActivity, ResultCode.RESULT_CANCELLED, "" );
							}
						}
					} );

				} else {
					if ( callback != null ) {
						callback.onError( mActivity, ResultCode.RESULT_FAILED, "" );
					}
				}
				if ( callback != null ) {
					callback.onCompleted( mActivity );
				}
			}


			@Override
			public void onError( UiError e ) {
				PayLogUtil.loge(TAG, "error ==> " + e.errorDetail );
				if ( callback != null ) {
					callback.onError( mActivity, ResultCode.RESULT_FAILED, toMessage( e ) );
				}
			}

			@Override
			public void onCancel() {
				if ( callback != null ) {
					callback.onError( mActivity, ResultCode.RESULT_CANCELLED, "" );
				}
			}
		};
		if ( callback != null ) {
			callback.onStart( mActivity );
		}
		mApi.login( mActivity, "all", mListener );
	}

	@Override
	public void onResult( int requestCode, int resultCode, Intent data ) {
		if ( requestCode == Constants.REQUEST_LOGIN ) {
			Tencent.onActivityResultData( requestCode, resultCode, data, mListener );
		}
	}

}
