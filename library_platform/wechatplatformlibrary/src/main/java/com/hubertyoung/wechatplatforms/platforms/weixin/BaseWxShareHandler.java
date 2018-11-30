package com.hubertyoung.wechatplatforms.platforms.weixin;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.hubertyoung.baseplatform.PlatformShareConfiguration;
import com.hubertyoung.baseplatform.error.ShareException;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.share.IShareHandler;
import com.hubertyoung.baseplatform.share.SocializeMedia;
import com.hubertyoung.baseplatform.share.shareparam.BaseShareParam;
import com.hubertyoung.baseplatform.share.shareparam.ShareImage;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamAudio;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamImage;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamText;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamVideo;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamWebPage;
import com.hubertyoung.baseplatform.share.shareparam.ShareVideo;
import com.hubertyoung.baseplatform.tools.PlatformLogUtil;
import com.hubertyoung.baseplatform.tools.ShareImageHelper;
import com.hubertyoung.baseplatformlibrary.R;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;


public class BaseWxShareHandler extends WXBase implements IShareHandler {

	private static final int IMAGE_MAX = 32 * 1024;
	private static final int IMAGE_WIDTH = 600;
	private static final int IMAGE_HEIGHT = 800;
	protected ShareImageHelper mImageHelper;
	private Handler mMainHandler = new Handler( Looper.getMainLooper() );
	private BaseShareParam mParams;

	BaseWxShareHandler( Activity activity, OtherPlatform platform ) {
		super( activity, platform );
	}

	@Override
	public void share( BaseShareParam param, PlatformShareConfiguration configuration, OnCallback< String > callback ) throws Exception {
		this.mParams = param;

		mImageHelper = new ShareImageHelper( mActivity, configuration, mImageCallback );

		mImageHelper.saveBitmapToExternalIfNeed(mParams);
		mImageHelper.copyImageToCacheFileDirIfNeed(mParams);

		if ( mParams instanceof ShareParamText ) {
			shareText( ( ShareParamText ) mParams,mCallback );
		} else if ( mParams instanceof ShareParamImage ) {
			shareImage( ( ShareParamImage ) mParams,mCallback );
		} else if ( mParams instanceof ShareParamWebPage ) {
			shareWebPage( ( ShareParamWebPage ) mParams ,mCallback);
		} else if ( mParams instanceof ShareParamAudio ) {
			shareAudio( ( ShareParamAudio ) mParams ,mCallback);
		} else if ( mParams instanceof ShareParamVideo ) {
			shareVideo( ( ShareParamVideo ) mParams ,mCallback);
		}
	}
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

	public void shareText( final ShareParamText params, OnCallback mCallback ) {
		String text = params.getContent();
		if ( TextUtils.isEmpty( text ) ) {
			PlatformLogUtil.logd( "Title or target url is empty or illegal" );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );
		}

		WXTextObject textObj = new WXTextObject();
		textObj.text = text;

		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = textObj;
		msg.description = text;

		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction( "textshare" );
		req.message = msg;
		req.scene = toScene(mPlatform.getName());
		Log.d( TAG, "start share text" );
		shareOnMainThread( req );
	}

	public void shareImage( final ShareParamImage params, OnCallback mCallback ) {
		try {
			mImageHelper.downloadImageIfNeed( params, new Runnable() {
				@Override
				public void run() {
					WXImageObject imgObj = buildWXImageObject( params.getImage() );

					WXMediaMessage msg = new WXMediaMessage();
					msg.mediaObject = imgObj;
					msg.thumbData = mImageHelper.buildThumbData( params.getImage() );

					final SendMessageToWX.Req req = new SendMessageToWX.Req();
					req.transaction = buildTransaction( "imgshareappdata" );
					req.message = msg;
					req.scene = toScene(mPlatform.getName());
					Log.d( TAG, "start share image" );
					shareOnMainThread( req );
				}
			} );
		} catch ( ShareException e ) {
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, e.getMessage().toString() );
		}
	}

	public WXImageObject buildWXImageObject( final ShareImage image ) {
		WXImageObject imgObj = new WXImageObject();

		if ( image == null ) {
			return imgObj;
		}

		if ( image.isLocalImage() ) {
			imgObj.setImagePath( image.getLocalPath() );
		} else if ( !image.isUnknowImage() ) {
			imgObj.imageData = mImageHelper.buildThumbData( image, IMAGE_MAX, IMAGE_WIDTH, IMAGE_HEIGHT, false );
		}

		return imgObj;
	}

	public void shareWebPage( final ShareParamWebPage params, OnCallback mCallback ) {
		if ( TextUtils.isEmpty( params.getTargetUrl() ) ) {
			PlatformLogUtil.logd( "Title or target url is empty or illegal" );
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );
		}

		try {
			mImageHelper.downloadImageIfNeed( params, new Runnable() {
				@Override
				public void run() {

					WXWebpageObject webpage = new WXWebpageObject();
					webpage.webpageUrl = params.getTargetUrl();

					WXMediaMessage msg = new WXMediaMessage( webpage );
					msg.title = params.getTitle();
					msg.description = params.getContent();
					msg.thumbData = mImageHelper.buildThumbData( params.getThumb() );

					SendMessageToWX.Req req = new SendMessageToWX.Req();
					req.transaction = buildTransaction( "webpage" );
					req.message = msg;
					req.scene = toScene(mPlatform.getName());
					Log.d( TAG, "start share webpage" );
					shareOnMainThread( req );
				}
			} );
		} catch ( ShareException e ) {
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, e.getMessage().toString() );
		}
	}

	public void shareAudio( final ShareParamAudio params, OnCallback mCallback ) {
		if ( TextUtils.isEmpty( params.getTargetUrl() ) && TextUtils.isEmpty( params.getAudioUrl() ) ) {
			PlatformLogUtil.logd( "Title or target url is empty or illegal" );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );
		}

		try {
			mImageHelper.downloadImageIfNeed( params, new Runnable() {
				@Override
				public void run() {
					WXMusicObject audio = new WXMusicObject();

					if ( !TextUtils.isEmpty( params.getAudioUrl() ) ) {
						audio.musicUrl = params.getAudioUrl();
					} else {
						audio.musicUrl = params.getTargetUrl();
					}

					WXMediaMessage msg = new WXMediaMessage( audio );
					msg.title = params.getTitle();
					msg.description = params.getContent();
					msg.thumbData = mImageHelper.buildThumbData( params.getThumb() );

					SendMessageToWX.Req req = new SendMessageToWX.Req();
					req.transaction = buildTransaction( "music" );
					req.message = msg;
					req.scene = toScene(mPlatform.getName());
					PlatformLogUtil.logd( "start share audio" );
					shareOnMainThread( req );
				}
			} );
		} catch ( ShareException e ) {
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, e.getMessage().toString() );
		}
	}

	public void shareVideo( final ShareParamVideo params, OnCallback mCallback ) {
		if ( TextUtils.isEmpty( params.getTargetUrl() ) && ( params.getVideo() == null || TextUtils.isEmpty( params.getVideo().getVideoH5Url() ) ) ) {
			PlatformLogUtil.logd( "Title or target url is empty or illegal" );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );
		}

		try {
			mImageHelper.downloadImageIfNeed( params, new Runnable() {
				@Override
				public void run() {
					WXVideoObject video = new WXVideoObject();
					ShareVideo sv = params.getVideo();
					if ( !TextUtils.isEmpty( sv.getVideoH5Url() ) ) {
						video.videoUrl = sv.getVideoH5Url();
					} else {
						video.videoUrl = params.getTargetUrl();
					}

					WXMediaMessage msg = new WXMediaMessage( video );
					msg.title = params.getTitle();
					msg.description = params.getContent();
					msg.thumbData = mImageHelper.buildThumbData( params.getThumb() );

					SendMessageToWX.Req req = new SendMessageToWX.Req();
					req.transaction = buildTransaction( "video" );
					req.message = msg;
					req.scene = toScene(mPlatform.getName());
					PlatformLogUtil.logd( "start share video" );
					shareOnMainThread( req );
				}
			} );
		} catch ( ShareException e ) {
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, e.getMessage().toString() );
		}
	}
	private void shareOnMainThread(final SendMessageToWX.Req req) {
		doOnMainThread(new Runnable() {
			@Override
			public void run() {
				boolean result = mApi.sendReq(req);
				if (!result && mCallback != null) {
					mCallback.onCompleted( mActivity );
					mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );
				}
			}
		});
	}
	@Override
	protected void onResultOk( SendMessageToWX.Resp resp ) {
		mCallback.onCompleted( mActivity );
		mCallback.onSuccess( mActivity, mActivity.getString( R.string.sdk_platform_share_success ) );
	}

	private String buildTransaction( final String type ) {
		return ( type == null ) ? String.valueOf( System.currentTimeMillis() ) : type + System.currentTimeMillis();
	}

	int toScene( String platform ) {
		switch ( platform ) {
			case SocializeMedia.WXSession:
				return 0;
			case SocializeMedia.WXTimeline:
				return 1;
			case SocializeMedia.WXFavorite:
				return 2;
		}
		return 0;
	}

}
