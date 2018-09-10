package com.hubertyoung.pay.platforms.weixin;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hubertyoung.pay.payment.IPayable;
import com.hubertyoung.pay.sdk.OnCallback;
import com.hubertyoung.pay.sdk.OtherPlatform;
import com.hubertyoung.pay.sdk.ResultCode;
import com.hubertyoung.pay.tools.PayLogUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;



/**
 * Created by ezy on 17/3/18.
 */

public class WXPayment extends WXBase implements IPayable {


	WXPayment( Activity activity, OtherPlatform platform ) {
		super( activity, platform );
	}

	@Override
	public void pay( String data, final @NonNull OnCallback< String > callback ) {
		PayReq req = new PayReq();
		PayLogUtil.loge( TAG, "data ==> " + data );
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
			PayLogUtil.loge( TAG, "parse error ==> " + e.toString() );
		}
		if ( !TextUtils.isEmpty( req.appId ) ) {
			mApi = WXAPIFactory.createWXAPI( mActivity.getApplicationContext(), req.appId, true );
			mApi.registerApp( req.appId );
		}
		mCallback = callback;
		if ( !mApi.isWXAppInstalled() ) {
			callback.onError( mActivity, ResultCode.RESULT_FAILED, "ddpsdk_error_operate wechat" );
			return;
		}
		mCallback.onStart( mActivity );
		boolean ret = mApi.sendReq( req );
		PayLogUtil.loge( TAG, "send end, pay request  ==> " + ret );

		if ( ret ) {
		}
	}

	@Override
	protected void onResultOk( PayResp resp ) {
		PayLogUtil.loge( TAG, "prepayId = " + resp.prepayId );
		mCallback.onSuccess( mActivity, resp.prepayId );
	}
}
