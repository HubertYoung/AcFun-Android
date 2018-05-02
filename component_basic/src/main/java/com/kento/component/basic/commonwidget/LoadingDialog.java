//package com.kento.component.basic.commonwidget;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.kento.component.basic.R;
//import com.kento.common.utils.AppUtils;
//
//import java.lang.ref.WeakReference;
//
//
//
///**
// * description:弹窗浮动加载进度条
// * Created by xsf
// * on 2016.07.17:22
// */
//public class LoadingDialog {
//	/**
//	 * 加载数据对话框
//	 */
//	private static Dialog mLoadingDialog;
//	private static TextView sLoadingText;
//
//	/**
//	 * 显示加载对话框
//	 *
//	 * @param activity   上下文
//	 * @param msg        对话框显示内容
//	 * @param cancelable 对话框是否可以取消
//	 */
//	public static Dialog showDialogForLoading( Activity activity, String msg, boolean cancelable ) {
//		WeakReference< Activity > activityWeakReference = new WeakReference<>( activity );
//		View view = LayoutInflater.from( activityWeakReference.get() )
//								  .inflate( R.layout.dialog_loading, null );
//		sLoadingText = ( TextView ) view.findViewById( R.id.id_tv_loading_dialog_text );
//		sLoadingText.setText( msg );
//
//		mLoadingDialog = new Dialog( activityWeakReference.get(), R.style.CustomProgressDialog );
//		//debug 模式可以点击取消方便调试
//		mLoadingDialog.setCancelable( AppUtils.isDebuggable() ? true : cancelable );
//		mLoadingDialog.setCanceledOnTouchOutside( false );
//		mLoadingDialog.setContentView( view, new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT ) );
//		mLoadingDialog.show();
//		return mLoadingDialog;
//	}
//
//	public static Dialog showDialogForLoading( Activity context ) {
//		View view = LayoutInflater.from( context )
//								  .inflate( R.layout.dialog_loading, null );
//		sLoadingText = ( TextView ) view.findViewById( R.id.id_tv_loading_dialog_text );
//		sLoadingText.setText( "加载中..." );
//
//		mLoadingDialog = new Dialog( context, R.style.CustomProgressDialog );
//		mLoadingDialog.setCancelable( true );
//		mLoadingDialog.setCanceledOnTouchOutside( false );
//		mLoadingDialog.setContentView( view, new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT ) );
//		mLoadingDialog.show();
//		return mLoadingDialog;
//	}
//
//	public static boolean isShowing() {
//		return ( mLoadingDialog != null && mLoadingDialog.isShowing() );
//	}
//
//	/**
//	 * 关闭加载对话框
//	 */
//	public static void cancelDialogForLoading() {
//		if ( mLoadingDialog != null ) {
//			mLoadingDialog.cancel();
//		}
//	}
//
//	public static void setText( String text ) {
//		if ( sLoadingText != null ) {
//			sLoadingText.setText( text );
//		}
//	}
//}
