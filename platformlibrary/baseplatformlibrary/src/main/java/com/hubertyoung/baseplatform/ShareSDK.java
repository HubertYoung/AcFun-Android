package com.hubertyoung.baseplatform;

import android.app.Activity;
import android.content.Intent;

import com.hubertyoung.baseplatform.sdk.DefaultCallback;
import com.hubertyoung.baseplatform.sdk.IFactory;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OnSuccess;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.sdk.Sdk;
import com.hubertyoung.baseplatform.share.IShareable;
import com.hubertyoung.baseplatform.share.ShareData;
import com.hubertyoung.baseplatform.share.image.resource.ImageResource;
import com.hubertyoung.baseplatform.share.media.IMediaObject;
import com.hubertyoung.baseplatform.share.media.MoImage;


public class ShareSDK {
	static Sdk< IShareable > sdk = new Sdk<>();

	public static void setDefaultCallback( OnCallback callback ) {
		sdk.setDefaultCallback( callback );
	}

	public static < T extends IShareable > void register( String name, String appId, Class< T > clazz ) {
		sdk.register( name, appId, clazz );
	}

	public static < T extends IShareable > void register( IFactory< T > factory ) {
		sdk.register( factory );
	}

	private ShareData mData = new ShareData();

	private Activity mActivity;

	private ShareSDK( Activity activity, String text, IMediaObject media ) {
		mActivity = activity;
		mData.text = text;
		mData.media = media;
	}

	// 文本
	public static ShareSDK make( Activity activity, String text ) {
		return new ShareSDK( activity, text, null );
	}

	// 图片
	public static ShareSDK make( Activity activity, ImageResource image ) {
		return new ShareSDK( activity, null, new MoImage( image ) );
	}

	// 图片、音乐、视频、文件
	public static ShareSDK make( Activity activity, IMediaObject media ) {
		return new ShareSDK( activity, null, media );
	}

	public static ShareSDK make( Activity activity, String text, IMediaObject media ) {
		return new ShareSDK( activity, text, media );
	}

	public ShareSDK withUrl( String value ) {
		mData.url = value;
		return this;
	}

	public ShareSDK withTitle( String value ) {
		mData.title = value;
		return this;
	}

	public ShareSDK withDescription( String value ) {
		mData.description = value;
		return this;
	}

	public ShareSDK withThumb( ImageResource thumb ) {
		mData.thumb = thumb;
		return this;
	}

	public void share( String platform ) {
		share( platform, new DefaultCallback( sdk.getDefaultCallback(), null ) );
	}

	public void share( String platform, OnSuccess< String > listener ) {
		share( platform, new DefaultCallback( sdk.getDefaultCallback(), listener ) );
	}

	public void share( String platform, OnCallback< String > callback ) {
		if ( !sdk.isSupport( platform ) ) {
			callback.onError( mActivity, ResultCode.RESULT_FAILED,"" );
			return;
		}
		IShareable api = sdk.get( mActivity, platform );
		if ( api == null ) {
			return;
		}
		api.share( mData, callback );
	}

	public static void onHandleResult( Activity activity, int requestCode, int resultCode, Intent data ) {
		sdk.onHandleResult( activity, requestCode, resultCode, data );
	}

}