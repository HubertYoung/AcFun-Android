package com.hubertyoung.baseplatform;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.hubertyoung.baseplatform.sdk.DefaultCallback;
import com.hubertyoung.baseplatform.sdk.IFactory;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OnSuccess;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.sdk.Sdk;
import com.hubertyoung.baseplatform.share.IShareHandler;
import com.hubertyoung.baseplatform.share.shareparam.BaseShareParam;
import com.hubertyoung.baseplatformlibrary.R;

import java.lang.ref.WeakReference;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/11 15:52
 * @since:V1.0.0
 * @desc:com.hubertyoung.baseplatform
 */
public final class ShareSDK {
	static Sdk< IShareHandler > sdk = new Sdk<>();

	public static void setDefaultCallback( OnCallback callback ) {
		sdk.setDefaultCallback( callback );
	}

	public static < T extends IShareHandler > void register( String name, String appId, String appSecret, Class< T > clazz ) {
		sdk.register( name, appId, appSecret, clazz );
	}

	public static < T extends IShareHandler > void register( IFactory< T > factory ) {
		sdk.register( factory );
	}

	private BaseShareParam param;
	private PlatformShareConfiguration configuration;

	private WeakReference< Activity > mActivityWeakReference;

	private ShareSDK( Activity activity, BaseShareParam param, PlatformShareConfiguration configuration ) {
		mActivityWeakReference = new WeakReference( activity );
		this.param = param;
		this.configuration = configuration;
	}

	@NonNull
	public static ShareSDK make( Activity activity, BaseShareParam param, PlatformShareConfiguration configuration ) {
		if (activity == null) {
			throw new NullPointerException();
		}
		ShareSDK shareSDK = new ShareSDK( activity, param ,configuration);
		return shareSDK;
	}

	public void share( String platform ) {
		share( platform, new DefaultCallback( sdk.getDefaultCallback(), null ) );
	}

	public void share( String platform, OnSuccess< String > listener ) {
		share( platform, new DefaultCallback( sdk.getDefaultCallback(), listener ) );
	}

	public void share( String platform, OnCallback< String > callback ) {
		if ( !sdk.isSupport( platform ) ) {
			callback.onError( mActivityWeakReference.get(), ResultCode.RESULT_FAILED, mActivityWeakReference.get().getString( R.string.sdk_platform_not_supported_auth ) );
			return;
		}
		IShareHandler api = sdk.get( mActivityWeakReference.get(), platform );
		if ( api == null ) {
			return;
		}
		if ( param == null ) {
			callback.onError( mActivityWeakReference.get(), ResultCode.RESULT_FAILED, mActivityWeakReference.get().getString( R.string.sdk_platform_unsupported_sharing_types ) );
			return;
		}
		try {
			api.share( param, configuration, callback );
		} catch ( Exception e ) {
			callback.onError( mActivityWeakReference.get(), ResultCode.RESULT_FAILED, mActivityWeakReference.get().getString( R.string.sdk_platform_unsupported_sharing_types ) );
		}
	}

	public static void onHandleResult( Activity activity, int requestCode, int resultCode, Intent data ) {
		sdk.onHandleResult( activity, requestCode, resultCode, data );
	}

}