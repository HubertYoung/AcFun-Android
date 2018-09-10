package com.hubertyoung.baseplatform;

import android.app.Activity;
import android.content.Intent;

import com.hubertyoung.baseplatform.authorize.IAuthorize;
import com.hubertyoung.baseplatform.sdk.DefaultCallback;
import com.hubertyoung.baseplatform.sdk.IFactory;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OnSuccess;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.sdk.Sdk;


public class AuthorizeSDK {

	static Sdk< IAuthorize > sdk = new Sdk<>();

	public static void setDefaultCallback( OnCallback callback ) {
		sdk.setDefaultCallback( callback );
	}

	public static < T extends IAuthorize > void register( String name, String appId,String appSecret, Class< T > clazz ) {
		sdk.register( name, appId,appSecret, clazz );
	}

	public static < T extends IAuthorize > void register( IFactory< T > factory ) {
		sdk.register( factory );
	}

	public static void authorize( Activity activity, String platform, OnSuccess< String > listener ) {
		authorize( activity, platform, new DefaultCallback( sdk.getDefaultCallback(), listener ) );
	}

	public static void authorize( Activity activity, String platform, OnCallback< String > callback ) {
		if ( !sdk.isSupport( platform ) ) {
			callback.onError( activity, ResultCode.RESULT_FAILED, "ddpsdk_not_supported_auth" );
			return;
		}
		IAuthorize api = sdk.get( activity, platform );
		if ( api == null ) {
			return;
		}
		api.authorize( callback );
	}

	public static void onHandleResult( Activity activity, int requestCode, int resultCode, Intent data ) {
		sdk.onHandleResult( activity, requestCode, resultCode, data );
	}

	public static Sdk< IAuthorize > getSdk() {
		return sdk;
	}

}