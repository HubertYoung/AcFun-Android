package com.hubertyoung.common.widget;

import android.app.Activity;
import android.app.Dialog;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.R;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.log.CommonLog;

import android.support.annotation.StringRes;


public class LoadingDialog {
	private Dialog mDialog;
	private final SimpleDraweeView mSimpleDraweeView;
	private final TextView mText;


	public LoadingDialog( Activity activity ) {
		mDialog = new Dialog( activity, R.style.notitle_dialog );
		mDialog.setContentView( R.layout.loading_dialog );
		mSimpleDraweeView = mDialog.findViewById( R.id.loading_dialog_gif );
		mText = mDialog.findViewById( R.id.loading_dialog_text );
		ImageLoaderUtil.loadResourceImage( R.drawable.image_loading_holder, mSimpleDraweeView );
	}

	public void setText( String str ) {
		mText.setText( str );
	}

	public void setText( @StringRes int stringRes ) {
		mText.setText( stringRes );
	}

	public void show() {
		try {
			if ( mDialog != null && !mDialog.isShowing() ) {
				mDialog.show();
			}
		} catch ( Throwable e ) {
			CommonLog.loge( e );
		}
	}

	public boolean isShowing() {
		if ( mDialog != null ) {
			return mDialog.isShowing();
		}
		return false;
	}

	public void dismiss() {
		try {
			if ( mDialog != null && mDialog.isShowing() ) {
				mDialog.dismiss();
			}
		} catch ( Throwable e ) {
			CommonLog.loge( e );
		}
	}
}
