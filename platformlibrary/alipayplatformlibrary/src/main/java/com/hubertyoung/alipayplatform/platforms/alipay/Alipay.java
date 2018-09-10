package com.hubertyoung.alipayplatform.platforms.alipay;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;
import com.hubertyoung.baseplatform.payment.IPayable;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.tools.PayLogUtil;


public class Alipay implements IPayable {
	public static final String TAG = Alipay.class.getSimpleName();

	Activity mActivity;

	Alipay( Activity activity, OtherPlatform platform ) {
		mActivity = activity;
	}

	@Override
	public void pay( final String data, final OnCallback< String > callback ) {

		new AsyncTask< String, Void, Result >() {
			@Override
			protected void onPreExecute() {
				callback.onStart( mActivity );
			}

			@Override
			protected Result doInBackground( String... params ) {
				try {
					final PayTask task = new PayTask( mActivity );
					return new Result( task.payV2( data, true ) );
				} catch ( Exception e ) {
					PayLogUtil.loge( "TAG", "e->" + e );
					return null;
				}
			}

			@Override
			protected void onPostExecute( Result result ) {
				if ( result == null ) {
					callback.onError( mActivity, ResultCode.RESULT_FAILED, "" );
					callback.onCompleted( mActivity );
					return;
				}
				String message = "[" + result.resultStatus + "]" + result.resultText;
				if ( result.isSuccess() ) {
					callback.onSuccess( mActivity, result.result );
				} else if ( result.isPending() ) {
					callback.onError( mActivity, ResultCode.RESULT_PENDING, message );
				} else if ( result.isCancelled() ) {
					callback.onError( mActivity, ResultCode.RESULT_CANCELLED, message );
				} else {
					callback.onError( mActivity, ResultCode.RESULT_FAILED, message );
				}
				callback.onCompleted( mActivity );
			}
		}.execute( data );

	}

	@Override
	public void onResult( int requestCode, int resultCode, Intent data ) {
		PayLogUtil.loge( resultCode + "" );
	}
}