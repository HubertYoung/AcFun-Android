package com.hubertyoung.wechatplatforms.platforms.weixin;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hubertyoung.baseplatform.payment.IPayable;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.tools.PlatformLogUtil;
import com.hubertyoung.baseplatformlibrary.R;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/11 13:15
 * @since:V1.0.0
 * @desc:com.hubertyoung.wechatplatforms.platforms.weixin
 */
public class WXPayment extends WXBase implements IPayable {
	WXPayment( Activity activity, OtherPlatform platform ) {
		super( activity, platform );
	}

	@Override
	public void pay( String data, final @NonNull OnCallback< String > callback ) {
		PayReq req = new PayReq();
		PlatformLogUtil.loge( TAG, "data ==> " + data );
		try {
			JSONObject o = new JSONObject( data );
			req.appId = o.getString( "appid" );
			req.partnerId = o.getString( "partnerid" );
			req.prepayId = o.getString( "prepayid" );
			req.packageValue = o.getString( "package" );
			req.nonceStr = o.getString( "noncestr" );
			req.timeStamp = o.getString( "timestamp" );
			req.sign = o.getString( "sign" );
			req.transaction = req.nonceStr;
		} catch ( Exception e ) {
			PlatformLogUtil.loge( TAG, "parse error ==> " + e.toString() );
		}
		if ( !TextUtils.isEmpty( req.appId ) ) {
			mApi = WXAPIFactory.createWXAPI( mActivity.getApplicationContext(), req.appId, true );
			mApi.registerApp( req.appId );
		}
		mCallback = callback;
		if ( !mApi.isWXAppInstalled() ) {
			callback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_wechat_uninstall ) );
			return;
		}
		mCallback.onStart( mActivity );
		boolean ret = mApi.sendReq( req );
		PlatformLogUtil.loge( TAG, "send end, pay request  ==> " + ret );

		if ( ret ) {
		}
	}

	@Override
	protected void onResultOk( PayResp resp ) {
		PlatformLogUtil.loge( TAG, "prepayId = " + resp.prepayId );
		mCallback.onSuccess( mActivity, resp.prepayId );
	}
}
