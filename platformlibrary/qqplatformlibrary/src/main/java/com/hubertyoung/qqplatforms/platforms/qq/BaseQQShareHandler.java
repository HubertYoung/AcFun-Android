package com.hubertyoung.qqplatforms.platforms.qq;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hubertyoung.baseplatform.platforms.send.AbsShareHandler;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.tools.PlatformLogUtil;
import com.hubertyoung.baseplatform.tools.PlatformXmlPullParser;
import com.hubertyoung.baseplatformlibrary.R;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/14 14:41
 * @since:V$VERSION
 * @desc:com.hubertyoung.qqplatforms.platforms.qq
 */
public abstract class BaseQQShareHandler extends AbsShareHandler {

	protected Tencent mApi;
	protected OtherPlatform mPlatform;

	public BaseQQShareHandler( Activity activity, @NonNull OtherPlatform platform ) {
		super( activity );
		mPlatform = platform;
		String appId = PlatformXmlPullParser.getInstance().getQQID();
		appId = TextUtils.isEmpty( appId ) ? mPlatform.getAppId() : appId;
		if ( TextUtils.isEmpty( appId ) ) {
			appId = PlatformXmlPullParser.getInstance().getQQID();
		}
		if ( TextUtils.isEmpty( appId ) ) {
			throw new RuntimeException( "Please set QQ appid dev info." );
		}
		mApi = Tencent.createInstance( appId, mActivity );
	}

	/**
	 * 必须在主线程分享
	 *
	 * @param activity
	 * @param params
	 */
	protected void doShareToQQ( final Activity activity, final Bundle params ) {
		doOnMainThread( new Runnable() {
			@Override
			public void run() {
				PlatformLogUtil.logd( "real start share" );
				onShare( activity, mApi, params, mUiListener );
				if ( activity != null && !isMobileQQSupportShare( activity.getApplicationContext() ) ) {
					PlatformLogUtil.logd( "qq has not install" );
					if ( mCallback != null ) {
						mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_qq_uninstall ) );
					}
				}
			}
		} );
	}

	protected abstract void onShare( Activity activity, Tencent tencent, Bundle params, IUiListener iUiListener );

	protected final IUiListener mUiListener = new IUiListener() {
		@Override
		public void onCancel() {
			PlatformLogUtil.logd( "share cancel" );
			if ( mCallback != null ) {
				mCallback.onCompleted( mActivity );
				mCallback.onError( mActivity, ResultCode.RESULT_CANCELLED, mActivity.getString( R.string.sdk_platform_cancel_auth ) );
			}
		}

		@Override
		public void onComplete( Object response ) {
			PlatformLogUtil.logd( "share succss" );
			if ( mCallback != null ) {
				mCallback.onCompleted( mActivity );
				mCallback.onSuccess( mActivity, mActivity.getString( R.string.sdk_platform_share_success ) );
			}
		}

		@Override
		public void onError( UiError e ) {
			PlatformLogUtil.logd( "share failed" );
			if ( mCallback != null ) {
				mCallback.onCompleted( mActivity );
				mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
			}
		}
	};

	//copy from tencent sdk
	private boolean isMobileQQSupportShare( Context context ) {
		PackageManager packageManager = context.getPackageManager();
		PackageInfo packageInfo = null;
		try {
			packageInfo = packageManager.getPackageInfo( "com.tencent.mobileqq", 0 );
			return compareVersion( packageInfo.versionName, "4.1" ) >= 0;
		} catch ( PackageManager.NameNotFoundException var4 ) {
			return false;
		}
	}

	private int compareVersion( String versionName, String defaultValue ) {
		if ( versionName == null && defaultValue == null ) {
			return 0;
		} else if ( versionName != null && defaultValue == null ) {
			return 1;
		} else if ( versionName == null && defaultValue != null ) {
			return -1;
		} else {
			String[] var2 = versionName.split( "\\." );
			String[] var3 = defaultValue.split( "\\." );
			try {
				int var4;
				for (var4 = 0; var4 < var2.length && var4 < var3.length; ++var4) {
					int var5 = Integer.parseInt( var2[ var4 ] );
					int var6 = Integer.parseInt( var3[ var4 ] );
					if ( var5 < var6 ) {
						return -1;
					}
					if ( var5 > var6 ) {
						return 1;
					}
				}
				return var2.length > var4 ? 1 : ( var3.length > var4 ? -1 : 0 );
			} catch ( NumberFormatException e ) {
				return versionName.compareTo( defaultValue );
			}
		}
	}
}
