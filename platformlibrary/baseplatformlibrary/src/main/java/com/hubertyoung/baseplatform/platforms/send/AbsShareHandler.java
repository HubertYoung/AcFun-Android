package com.hubertyoung.baseplatform.platforms.send;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;

import com.hubertyoung.baseplatform.PlatformShareConfiguration;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.share.IShareHandler;
import com.hubertyoung.baseplatform.share.shareparam.BaseShareParam;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamAudio;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamImage;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamText;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamVideo;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamWebPage;
import com.hubertyoung.baseplatform.tools.ShareImageHelper;
import com.hubertyoung.baseplatformlibrary.R;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/14 10:12
 * @since:V$VERSION
 * @desc:com.hubertyoung.baseplatform.platforms.send
 */
public abstract class AbsShareHandler implements IShareHandler {

	protected Activity mActivity;
	private Handler mMainHandler = new Handler( Looper.getMainLooper() );
	protected OnCallback< String > mCallback;
	private BaseShareParam mParams;
	protected ShareImageHelper mImageHelper;

	public AbsShareHandler( Activity activity ) {
		this.mActivity = activity;
	}

	@Override
	public void share( BaseShareParam params, PlatformShareConfiguration configuration, OnCallback< String > callback ) throws Exception {
		this.mParams = params;
		this.mCallback = callback;
		mImageHelper = new ShareImageHelper( mActivity, configuration, mImageCallback );

		mImageHelper.saveBitmapToExternalIfNeed(params);
		mImageHelper.copyImageToCacheFileDirIfNeed(params);

		if ( params instanceof ShareParamText ) {
			shareText( ( ShareParamText ) params,mCallback );
		} else if ( params instanceof ShareParamImage ) {
			shareImage( ( ShareParamImage ) params,mCallback );
		} else if ( params instanceof ShareParamWebPage ) {
			shareWebPage( ( ShareParamWebPage ) params ,mCallback);
		} else if ( params instanceof ShareParamAudio ) {
			shareAudio( ( ShareParamAudio ) params ,mCallback);
		} else if ( params instanceof ShareParamVideo ) {
			shareVideo( ( ShareParamVideo ) params ,mCallback);
		}
	}

	public abstract void shareText( ShareParamText params, OnCallback< String > callback );

	public abstract void shareImage( ShareParamImage params, OnCallback< String > callback );

	public abstract void shareWebPage( ShareParamWebPage params, OnCallback< String > callback );

	public abstract void shareAudio( ShareParamAudio params, OnCallback< String > callback );

	public abstract void shareVideo( ShareParamVideo params, OnCallback< String > callback );

	private ShareImageHelper.Callback mImageCallback = new ShareImageHelper.Callback() {
		@Override
		public void onProgress( int msgId ) {
			postProgress( msgId );
		}

		@Override
		public void onImageDownloadFailed() {
			if ( mCallback != null ) {
				mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
			}
		}
	};

	protected void postProgress( final int msgRes ) {
		if ( mActivity != null ) {
			postProgress( mActivity.getString( msgRes ) );
		}
	}

	protected void postProgress( final String msg ) {
		doOnMainThread( new Runnable() {
			@Override
			public void run() {
				if ( mCallback != null ) {
					mCallback.onProgress( mActivity, mParams, msg );
				}
			}
		} );
	}

	protected void doOnMainThread( Runnable runnable ) {
		if ( mMainHandler != null ) {
			mMainHandler.post( runnable );
		}
	}

	boolean isIntentAvailable( Context context, Intent intent ) {
		PackageManager pm = context.getPackageManager();
		List list = pm.queryIntentActivities( intent, PackageManager.MATCH_DEFAULT_ONLY );
		return list.size() > 0;
	}

	boolean isApplicationInstalled( Context context, String packageName ) {
		PackageManager packageManager = context.getPackageManager();
		List< PackageInfo > list = packageManager.getInstalledPackages( 0 );
		for (int i = 0; i < list.size(); i++) {
			if ( list.get( i ).packageName.equalsIgnoreCase( packageName ) ) {
				return true;
			}
		}
		return false;
	}

}
