package com.hubertyoung.baseplatform.platforms.send;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.share.SocializeMedia;
import com.hubertyoung.baseplatform.share.shareparam.ShareImage;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamAudio;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamImage;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamText;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamVideo;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamWebPage;
import com.hubertyoung.baseplatformlibrary.R;

import androidx.annotation.NonNull;

public class SendShare extends AbsShareHandler {
	public static final String TAG = SendShare.class.getSimpleName();

	static final String PACKAGE_QQ = "com.tencent.mobileqq";
	static final String PACKAGE_WX = "com.tencent.mm";

	OtherPlatform mPlatform;

	SendShare( Activity activity, OtherPlatform platform ) {
		super( activity );
		mPlatform = platform;
	}

	@NonNull
	private Intent getIntent() {
		Intent intent = new Intent( Intent.ACTION_SEND );

		if ( mPlatform.getName().equals( SocializeMedia.ToQQ ) ) {
			intent.setClassName( PACKAGE_QQ, "com.tencent.mobileqq.activity.JumpActivity" );
		} else if ( mPlatform.getName().equals( SocializeMedia.ToWXSession ) ) {
			intent.setClassName( PACKAGE_WX, "com.tencent.mm.ui.tools.ShareImgUI" );
		} else {
			intent.setClassName( PACKAGE_WX, "com.tencent.mm.ui.tools.ShareToTimeLineUI" );
		}
		return intent;
	}

	private boolean isQQUnInstalled( @NonNull OnCallback< String > callback ) {
		if ( mPlatform.getName().equals( SocializeMedia.ToQQ ) ) {
			if ( !isApplicationInstalled( mActivity, PACKAGE_QQ ) ) {
				callback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_qq_uninstall ) );
				return true;
			}
		} else {
			if ( !isApplicationInstalled( mActivity, PACKAGE_WX ) ) {
				callback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_wechat_uninstall ) );
				return true;
			}
		}
		return false;
	}

	@Override
	public void shareText( ShareParamText params, OnCallback< String > callback ) {
		if ( isQQUnInstalled( callback ) ) return;

		Intent intent = getIntent();
		intent.setType( "text/plain" );
		intent.putExtra( "Kdescription", params.getContent() );
		intent.putExtra( Intent.EXTRA_TEXT, params.getTitle() );
		intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );

		startActivity( callback, intent );
	}


	@Override
	public void shareImage( ShareParamImage params, OnCallback< String > callback ) {
		if ( isQQUnInstalled( callback ) ) return;
		Intent intent = getIntent();

		intent.setType( "image/*" );
		intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION );

		ShareImage shareImage = params.getImage();
		if ( shareImage.isBitmapImage() ) {
			final Uri uri = Uri.parse( MediaStore.Images.Media.insertImage( mActivity.getContentResolver(), shareImage.getBitmap(), null, null ) );
			intent.putExtra( Intent.EXTRA_STREAM, uri );
		} else if ( shareImage.isLocalImage() ) {
			intent.putExtra( Intent.EXTRA_STREAM, Uri.fromFile( shareImage.getLocalFile() ) );
		} else {
			intent.putExtra( Intent.EXTRA_STREAM, Uri.parse( shareImage.getNetImageUrl() ) );
		}
		startActivity( callback, intent );
	}

	@Override
	public void shareWebPage( ShareParamWebPage params, OnCallback< String > callback ) {
		if ( isQQUnInstalled( callback ) ) return;
		callback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );
	}

	@Override
	public void shareAudio( ShareParamAudio params, OnCallback< String > callback ) {
		if ( isQQUnInstalled( callback ) ) return;
		callback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );
	}

	@Override
	public void shareVideo( ShareParamVideo params, OnCallback< String > callback ) {
		if ( isQQUnInstalled( callback ) ) return;
		callback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );
	}

	private void startActivity( OnCallback< String > callback, Intent intent ) {
		if ( isIntentAvailable( mActivity, intent ) ) {
			mActivity.startActivity( intent );
		} else {
			callback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
			return;
		}
	}

	@Override
	public void onResult( int requestCode, int resultCode, Intent data ) {
	}
}
