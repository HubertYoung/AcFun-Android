package com.hubertyoung.pay;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.hubertyoung.pay.authorize.IAuthorize;
import com.hubertyoung.pay.sdk.DefaultCallback;
import com.hubertyoung.pay.sdk.IFactory;
import com.hubertyoung.pay.sdk.OnCallback;
import com.hubertyoung.pay.sdk.OnSucceed;
import com.hubertyoung.pay.sdk.ResultCode;
import com.hubertyoung.pay.sdk.Sdk;


public class AuthorizeSDK {

	static Sdk< IAuthorize > sdk = new Sdk<>();

	public static void setDefaultCallback( OnCallback callback ) {
		sdk.setDefaultCallback( callback );
	}

	public static < T extends IAuthorize > void register( String name, String appId, Class< T > clazz ) {
		sdk.register( name, appId, clazz );
	}

	public static < T extends IAuthorize > void register( IFactory< T > factory ) {
		sdk.register( factory );
	}

	public static void authorize( FragmentActivity activity, String platform, OnSucceed< String > listener ) {
		authorize( activity, platform, new DefaultCallback( sdk.getDefaultCallback(), listener ) );
	}

	public static void authorize( FragmentActivity activity, String platform, OnCallback< String > callback ) {
		if ( !sdk.isSupport( platform ) ) {
			callback.onFailed( activity, ResultCode.RESULT_FAILED, "ddpsdk_not_supported_auth" );
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