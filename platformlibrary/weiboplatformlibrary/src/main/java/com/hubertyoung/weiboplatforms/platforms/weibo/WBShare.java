package com.hubertyoung.weiboplatforms.platforms.weibo;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.hubertyoung.baseplatform.PlatformSDKConfig;
import com.hubertyoung.baseplatform.platforms.send.AbsShareHandler;
import com.hubertyoung.baseplatform.sdk.IResult;
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
import com.hubertyoung.baseplatform.tools.PlatformXmlPullParser;
import com.hubertyoung.baseplatformlibrary.R;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;


public class WBShare extends AbsShareHandler implements WbShareCallback {
	public static final String TAG = WBShare.class.getSimpleName();
	/**
	 * Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博
	 * 核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权页中有权利
	 * 选择赋予应用的功能。
	 * <p>
	 * 我们通过新浪微博开放平台-->管理中心-->我的应用-->接口管理处，能看到我们目前已有哪些接口的
	 * 使用权限，高级权限需要进行申请。
	 * <p>
	 * 目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
	 * <p>
	 * 有关哪些 OpenAPI 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
	 * 关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
	 */
	public static final String SCOPE = "email,direct_messages_read,direct_messages_write," + "friendships_groups_read,friendships_groups_write,statuses_to_me_read," + "follow_app_official_microblog," +
			"invitation_write";
	public static int REQUEST_CODE = 1998;
	OtherPlatform mPlatform;
	static Map< IResult, Boolean > services = new WeakHashMap<>();

	WbShareHandler mApi;

	private WeiboMultiMessage mWeiboMessage;
	private SsoHandler mSsoHandler;

	WBShare( Activity activity, OtherPlatform platform ) {
		super(activity);
		mPlatform = platform;
		String sinaWeiboKey = PlatformXmlPullParser.getInstance().getSinaWeiboKey();
		sinaWeiboKey = TextUtils.isEmpty( sinaWeiboKey ) ? mPlatform.getAppId() : sinaWeiboKey;
		String sinaWeiboRedirectUrl = PlatformXmlPullParser.getInstance().getSinaWeiboRedirectUrl();
		sinaWeiboRedirectUrl = TextUtils.isEmpty( sinaWeiboRedirectUrl ) ? mPlatform.extra( PlatformSDKConfig.REDIRECTURL ) : sinaWeiboRedirectUrl;

		try {
			WbSdk.checkInit();
		} catch ( Exception e ) {
			WbSdk.install( activity.getApplicationContext(), //
					new AuthInfo( activity.getApplicationContext(),//
							sinaWeiboKey, //
							sinaWeiboRedirectUrl, WBShare.SCOPE ) );
		}

		mApi = new WbShareHandler( mActivity );
		mApi.registerApp();
		mApi.setProgressColor( 0xff33b5e5 );
		services.put( this, true );
	}

	@Override
	public void onResult( int requestCode, int resultCode, Intent data ) {
		PlatformLogUtil.loge( TAG, "==> requestCode = " + requestCode + ", " + data );
		if ( mApi != null && requestCode == REQUEST_CODE ) {
			mApi.doResultIntent( data, this );
		}
	}

	@Override
	public void onWbShareSuccess() {
		mCallback.onCompleted( mActivity );
		mCallback.onSuccess( mActivity, "" );
	}

	@Override
	public void onWbShareCancel() {
		mCallback.onCompleted( mActivity );
		mCallback.onError( mActivity, ResultCode.RESULT_CANCELLED, mActivity.getString( R.string.sdk_platform_cancel_auth ));
	}

	@Override
	public void onWbShareFail() {
		mCallback.onCompleted( mActivity );
		mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
	}

	@Override
	public void shareText( ShareParamText params, OnCallback< String > callback ) {
		checkContent(params);

		final WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
		weiboMessage.textObject = getTextObj(params);

		allInOneShare(weiboMessage);
	}

	@Override
	public void shareImage( ShareParamImage params, OnCallback< String > callback ) {
		checkContent(params);
		checkImage(params.getImage());

		doOnWorkThread(new Runnable() {
			@Override
			public void run() {
				final WeiboMultiMessage weiboMessage = new WeiboMultiMessage();

				weiboMessage.textObject = getTextObj(params);
				weiboMessage.imageObject = getImageObj(params.getImage());

				allInOneShare(weiboMessage);
			}
		});
	}

	@Override
	public void shareWebPage( ShareParamWebPage params, OnCallback< String > callback ) {
		checkContent(params);
		if (TextUtils.isEmpty(params.getTargetUrl())) {
			PlatformLogUtil.logd( "Target url is empty or illegal" );
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
		}

		doOnWorkThread(new Runnable() {
			@Override
			public void run() {
				final WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
				weiboMessage.textObject = getTextObj(params);

				try {
					checkImage(params.getThumb());
					weiboMessage.imageObject = getImageObj(params.getThumb());
				} catch (Exception e) {
					weiboMessage.textObject = getTextObj(params);
				}

				allInOneShare(weiboMessage);
			}
		});
	}

	@Override
	public void shareAudio( ShareParamAudio params, OnCallback< String > callback ) {
		checkContent(params);
		if (TextUtils.isEmpty(params.getTargetUrl())) {
			PlatformLogUtil.logd( "Target url is empty or illegal" );
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
		}
		if (params.getAudio() == null) {
			PlatformLogUtil.logd( "Audio is empty or illegal" );
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
		}

		doOnWorkThread(new Runnable() {
			@Override
			public void run() {
				final WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
				weiboMessage.textObject = getTextObj(params);

				try {
					checkImage(params.getThumb());
					weiboMessage.imageObject = getImageObj(params.getThumb());
				} catch (Exception e) {
					weiboMessage.textObject = getTextObj(params);
				}

				allInOneShare(weiboMessage);
			}
		});
	}

	@Override
	public void shareVideo( ShareParamVideo params, OnCallback< String > callback ) {
		checkContent(params);
		if (TextUtils.isEmpty(params.getTargetUrl())) {
			PlatformLogUtil.logd( "Target url is empty or illegal" );
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
		}
		if (params.getVideo() == null) {
			PlatformLogUtil.logd( "Video is empty or illegal" );
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
		}

		doOnWorkThread(new Runnable() {
			@Override
			public void run() {
				final WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
				weiboMessage.textObject = getTextObj(params);

				try {
					checkImage(params.getThumb());
					weiboMessage.imageObject = getImageObj(params.getThumb());
				} catch (Exception e) {
					weiboMessage.textObject = getTextObj(params);
				}

				allInOneShare(weiboMessage);
			}
		});
	}
	/**
	 * 创建图片消息对象。
	 *
	 * @return 图片消息对象。
	 */
	private ImageObject getImageObj( ShareImage image) {
		ImageObject imageObject = new ImageObject();

		if (image == null) {
			return imageObject;
		}

		if (image.isLocalImage()) {
			imageObject.imagePath = image.getLocalPath();
		} else {
			imageObject.imageData = mImageHelper.buildThumbData(image);
		}
		return imageObject;
	}
	protected void doOnWorkThread(final Runnable runnable) {
		mImageHelper.getShareConfiguration().getTaskExecutor().execute(new Runnable() {
			@Override
			public void run() {
				try {
					runnable.run();
				} catch (Exception e) {
					e.printStackTrace();

					if (mCallback!= null) {
						doOnMainThread(new Runnable() {
							@Override
							public void run() {
								mCallback.onCompleted( mActivity );
								mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
							}
						});
					}
				}
			}
		});
	}

	private String getToken() {
		Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(mActivity);
		String token = null;
		if (mAccessToken != null) {
			token = mAccessToken.getToken();
		}
		return token;
	}

	private void allInOneShare(final WeiboMultiMessage weiboMessage) {
		final String token = getToken();
		if (TextUtils.isEmpty(token)) {
			mWeiboMessage = weiboMessage;
			Log.d(TAG, "authorize when allInOneShare");
			mSsoHandler = new SsoHandler(mActivity);
			mSsoHandler.authorize(mAuthListener);
		} else {
			mWeiboMessage = null;
			mSsoHandler = null;
			doOnMainThread(new Runnable() {
				@Override
				public void run() {
//					postProgressStart();
					Log.d(TAG, "share message when allInOneShare");
					mApi.shareMessage(weiboMessage, false);
				}
			});
		}
	}

	private WbAuthListener mAuthListener = new WbAuthListener() {

		@Override
		public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
			Log.d(TAG, "auth success");
			mSsoHandler = null;
			if (oauth2AccessToken.isSessionValid()) {
				AccessTokenKeeper.writeAccessToken(mActivity, oauth2AccessToken);
				if (mWeiboMessage != null) {
					allInOneShare(mWeiboMessage);
				}
				return;
			}

			if (mCallback == null) {
				return;
			}
			PlatformLogUtil.logd( "无效的token" );
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
		}

		@Override
		public void cancel() {
			PlatformLogUtil.logd( "auth cancel");
			if (mCallback != null) {
				mCallback.onCompleted( mActivity );
				mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_cancel_auth ) );
			}
			mSsoHandler = null;
		}

		@Override
		public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
			PlatformLogUtil.logd( "auth failure");
			if (mCallback != null) {
				mCallback.onCompleted( mActivity );
				mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
			}
			mSsoHandler = null;
		}
	};


	/**
	 * 创建文本消息对象。
	 *
	 * @return 文本消息对象。
	 */
	private TextObject getTextObj( BaseShareParam params) {
		TextObject textObject = new TextObject();

		if (params != null) {
			textObject.title = params.getTitle();
			textObject.text = params.getContent();
			textObject.actionUrl = params.getTargetUrl();
			if (!TextUtils.isEmpty(textObject.actionUrl)) {
				textObject.text = String.format("%s %s", textObject.text, textObject.actionUrl);
			}
		}

		return textObject;
	}
	private void checkImage(ShareImage image) {
		if (image == null) {
			PlatformLogUtil.logd( "Image cannot be null" );
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
		}

		if (image.isLocalImage()) {
			if (TextUtils.isEmpty(image.getLocalPath()) || !new File(image.getLocalPath()).exists()) {
				PlatformLogUtil.logd( "Image path is empty or illegal" );
				mCallback.onCompleted( mActivity );
				mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
			}
		} else if (image.isNetImage()) {
			if (TextUtils.isEmpty(image.getNetImageUrl())) {
				PlatformLogUtil.logd( "Image url is empty or illegal" );
				mCallback.onCompleted( mActivity );
				mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
			}
		} else if (image.isResImage()){
			PlatformLogUtil.logd( "Unsupport image type" );
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
		}
		else if (image.isBitmapImage()) {
			if (image.getBitmap().isRecycled()) {
				PlatformLogUtil.logd( "Cannot share recycled bitmap." );
				mCallback.onCompleted( mActivity );
				mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
			}
		} else {
			PlatformLogUtil.logd( "Invaild image" );
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
		}

	}

	private void checkContent(BaseShareParam params) {
		if (TextUtils.isEmpty(params.getContent())) {
			PlatformLogUtil.logd( "Content is empty or illegal" );
			mCallback.onCompleted( mActivity );
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_share_error ) );
		}
	}
}
