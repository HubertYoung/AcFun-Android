package com.hubertyoung.qqplatforms.platforms.qq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hubertyoung.baseplatform.error.ShareException;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.share.shareparam.BaseShareParam;
import com.hubertyoung.baseplatform.share.shareparam.ShareImage;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamAudio;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamImage;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamText;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamVideo;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamWebPage;
import com.hubertyoung.baseplatform.tools.PlatformLogUtil;
import com.hubertyoung.baseplatformlibrary.R;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * Time::2018/9/14 14:58
 * @since:V1.0.0
 * Pkg:com.hubertyoung.qqplatforms.platforms.qq
 */
public class QQChatShareHandler extends BaseQQShareHandler {

	QQChatShareHandler( @NonNull Activity activity, @NonNull OtherPlatform platform ) {
		super( activity, platform );
	}

	@Override
	public void onResult( int requestCode, int resultCode, Intent data ) {
		if ( requestCode == Constants.REQUEST_QQ_SHARE) {
			Tencent.onActivityResultData( requestCode, resultCode, data, mUiListener );
		}
	}

	@Override
	public void shareText( ShareParamText params, OnCallback< String > callback ) {
		PlatformLogUtil.logd( "share text" );
		shareImageText( params, null, callback );
	}

	@Override
	public void shareImage( final ShareParamImage params, OnCallback< String > callback ) {
		final ShareImage image = params.getImage();
		if ( image == null || ( !image.isLocalImage() && !image.isNetImage() ) ) {
			shareImageText( params, params.getImage(), callback );
		} else {
			shareImage( params, params.getImage(), callback );
		}
	}

	@Override
	public void shareWebPage( ShareParamWebPage params, OnCallback< String > callback ) {
		PlatformLogUtil.logd( "share web page" );
		shareImageText( params, params.getThumb(), callback );
	}

	@Override
	public void shareAudio( ShareParamAudio params, OnCallback< String > callback ) {
		if ( TextUtils.isEmpty( params.getTitle() ) || TextUtils.isEmpty( params.getTargetUrl() ) ) {
			PlatformLogUtil.logd( "Title or target url is empty or illegal" );
			callback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );
		}
		if ( TextUtils.isEmpty( params.getAudioUrl() ) ) {
			PlatformLogUtil.logd( "Audio url is empty or illegal" );
			callback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );

		}

		PlatformLogUtil.logd( "share audio" );
		final Bundle bundle = new Bundle();
		ShareImage thumb = params.getThumb();
		bundle.putInt( QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_AUDIO );
		bundle.putString( QQShare.SHARE_TO_QQ_TITLE, params.getTitle() );
		bundle.putString( QQShare.SHARE_TO_QQ_SUMMARY, params.getContent() );
		bundle.putString( QQShare.SHARE_TO_QQ_TARGET_URL, params.getTargetUrl() );

		if ( thumb != null ) {
			if ( thumb.isNetImage() ) {
				bundle.putString( QQShare.SHARE_TO_QQ_IMAGE_URL, thumb.getNetImageUrl() );
			} else if ( thumb.isLocalImage() ) {
				bundle.putString( QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, thumb.getLocalPath() );
			}
		}

		bundle.putString( QQShare.SHARE_TO_QQ_AUDIO_URL, params.getAudioUrl() );
		doShareToQQ( mActivity, bundle );
	}

	@Override
	public void shareVideo( ShareParamVideo params, OnCallback< String > callback ) {
		PlatformLogUtil.logd( "share video" );
		shareImageText( params, params.getThumb(), callback );
	}

	/**
	 * 图文模式，title、targetURL不能为空
	 *
	 * @param params
	 * @param image
	 * @param callback
	 * @throws ShareException
	 */
	private void shareImageText( BaseShareParam params, ShareImage image, OnCallback< String > callback ) {
		if ( TextUtils.isEmpty( params.getTitle() ) || TextUtils.isEmpty( params.getTargetUrl() ) ) {
			PlatformLogUtil.logd( "Title or target url is empty or illegal" );
			callback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );
		}

		PlatformLogUtil.logd( "share image text" );
		final Bundle bundle = new Bundle();
		bundle.putInt( QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT );
		bundle.putString( QQShare.SHARE_TO_QQ_TITLE, params.getTitle() );
		bundle.putString( QQShare.SHARE_TO_QQ_SUMMARY, params.getContent() );
		bundle.putString( QQShare.SHARE_TO_QQ_TARGET_URL, params.getTargetUrl() );

		if ( image != null ) {
			if ( image.isNetImage() ) {
				bundle.putString( QQShare.SHARE_TO_QQ_IMAGE_URL, image.getNetImageUrl() );
			} else if ( image.isLocalImage() ) {
				bundle.putString( QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, image.getLocalPath() );
			}
		}

		doShareToQQ( mActivity, bundle );
	}

	/**
	 * 纯图模式，localPath不能为空
	 *
	 * @param params
	 * @param image
	 */
	private void shareImage( BaseShareParam params, final ShareImage image, OnCallback< String > callback ) {
		try {
			mImageHelper.downloadImageIfNeed( image, new Runnable() {
				@Override
				public void run() {
					PlatformLogUtil.logd( "share image" );
					final Bundle bundle = new Bundle();
					bundle.putInt( QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE );

					if ( image.isLocalImage() ) {
						bundle.putString( QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, image.getLocalPath() );
					}

					doShareToQQ( mActivity, bundle );
				}
			} );
		} catch ( ShareException e ) {
			PlatformLogUtil.logd( e.getMessage().toString() );
			callback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );
		}
	}

	@Override
	protected void onShare( Activity activity, Tencent tencent, Bundle params, IUiListener iUiListener ) {
		tencent.shareToQQ( activity, params, iUiListener );
	}
}
