package com.hubertyoung.pay.platforms.alipay;


import android.app.Activity;
import android.content.Intent;

import com.hubertyoung.pay.payment.IPayable;
import com.hubertyoung.pay.sdk.OnCallback;
import com.hubertyoung.pay.sdk.Platform;
import com.hubertyoung.pay.tools.PayLogUtil;


public class Alipay implements IPayable {
	public static final String TAG = Alipay.class.getSimpleName();

	Activity mActivity;

	Alipay( Activity activity, Platform platform ) {
		mActivity = activity;
	}

	@Override
	public void pay( final String data, final OnCallback< String > callback ) {

//		new AsyncTask< String, Void, Result >() {
//			@Override
//			protected void onPreExecute() {
//				callback.onStarted( mActivity );
//			}
//
//			@Override
//			protected Result doInBackground( String... params ) {
//				try {
//					final PayTask task = new PayTask( mActivity );
//					return new Result( task.payV2( data, true ) );
//				} catch ( Exception e ) {
//					Log.e( "TAG", "e->" + e );
//					return null;
//				}
//			}
//
//			@Override
//			protected void onPostExecute( Result result ) {
//				if ( result == null ) {
//					callback.onFailed( mActivity, ResultCode.RESULT_FAILED, "" );
//					callback.onCompleted( mActivity );
//					return;
//				}
//				String message = "[" + result.resultStatus + "]" + result.resultText;
//				if ( result.isSuccess() ) {
//					callback.onSucceed( mActivity, result.result );
//				} else if ( result.isPending() ) {
//					callback.onFailed( mActivity, ResultCode.RESULT_PENDING, message );
//				} else if ( result.isCancelled() ) {
//					callback.onFailed( mActivity, ResultCode.RESULT_CANCELLED, message );
//				} else {
//					callback.onFailed( mActivity, ResultCode.RESULT_FAILED, message );
//				}
//				callback.onCompleted( mActivity );
//			}
//		}.execute( data );

	}

	@Override
	public void onResult( int requestCode, int resultCode, Intent data ) {
		PayLogUtil.loge( resultCode + "" );
	}
}