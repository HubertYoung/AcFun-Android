package com.hubertyoung.wechatplatforms.platforms.weixin;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.hubertyoung.baseplatform.sdk.IResult;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.tools.PayLogUtil;
import com.hubertyoung.baseplatform.tools.PayXmlPullParser;
import com.hubertyoung.baseplatformlibrary.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.WeakHashMap;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/10 18:01
 * @since:V1.0。0
 * @desc:com.hubertyoung.wechatplatforms.platforms.weixin
 */
abstract class WXBase implements IResult, IWXAPIEventHandler {
	public static final String TAG = WXBase.class.getSimpleName();

	public static int REQUEST_CODE = 1999;

	static WeakHashMap< IResult, Boolean > services = new WeakHashMap<>();

	final protected Activity mActivity;
	final protected OtherPlatform mPlatform;

	protected OnCallback mCallback;

	IWXAPI mApi;

	protected WXBase( Activity activity, OtherPlatform platform ) {
		mActivity = activity;
		mPlatform = platform;
		String wechatID = PayXmlPullParser.getInstance().getWechatID();
		wechatID = TextUtils.isEmpty( wechatID ) ? mPlatform.getAppId() : wechatID;
		if ( !TextUtils.isEmpty( wechatID ) ) {
			mApi = WXAPIFactory.createWXAPI( activity.getApplicationContext(), wechatID, true );
			mApi.registerApp( wechatID );
		}
		services.put( this, true );
	}


	@Override
	public void onResult( int requestCode, int resultCode, Intent data ) {
		if ( mApi != null && requestCode == REQUEST_CODE ) {
			mApi.handleIntent( data, this );
		}
	}

	@Override
	public void onReq( BaseReq req ) {
		PayLogUtil.loge( TAG, "transaction = " + req.transaction + ", type = " + req.getType() + ", openId = " + req.openId );
	}

	@Override
	public void onResp( BaseResp resp ) {
		PayLogUtil.loge( TAG, "transaction = " + resp.transaction + ", type = " + resp.getType() + ", errCode = " + resp.errCode + ", err = " + resp.errStr );

		if ( resp.errCode == BaseResp.ErrCode.ERR_OK ) {
			switch ( resp.getType() ) {
				case ConstantsAPI.COMMAND_SENDAUTH: //授权返回
					onResultOk( ( SendAuth.Resp ) resp );
					break;
				case ConstantsAPI.COMMAND_PAY_BY_WX://支付返回
					onResultOk( ( PayResp ) resp );
					break;
				case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX://分享返回
					onResultOk( ( SendMessageToWX.Resp ) resp );
					break;
			}
		} else if ( resp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL ) {
			mCallback.onError( mActivity, ResultCode.RESULT_CANCELLED, mActivity.getString( R.string.sdk_platform_cancel_auth ) );
		} else {
			mCallback.onError( mActivity, ResultCode.RESULT_FAILED, toMessage( resp ) );
		}
		mCallback.onCompleted( mActivity );
	}

	String toMessage( BaseResp resp ) {
		return "[" + resp.errCode + "]" + ( resp.errStr == null ? "" : resp.errStr );
	}

	protected void onResultOk( PayResp resp ) {

	}

	protected void onResultOk( SendAuth.Resp resp ) {
	}

	protected void onResultOk( SendMessageToWX.Resp resp ) {
	}
}
