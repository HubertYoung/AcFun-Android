//package com.kento.component.basic.commonutils;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Build;
//
//import com.kento.common.R;
//import com.kento.common.commonwidget.dialog.dialog.listener.OnBtnClickL;
//import com.kento.common.commonwidget.dialog.dialog.widget.NormalDialog;
//
///**
// * Created by ${lei} on 2017/7/17.
// * Desc:应用程序权限设置页面
// * Since：v1.0
// */
//
//public class PermissionSettingUtils {
//
//	private final Activity mActivity;
//
//	public PermissionSettingUtils( Activity activity ) {
//		this.mActivity = activity;
//	}
//
//	/**
//	 * @author:lei
//	 * @date:2017/7/17
//	 * @since:v1.0
//	 * @desc: 跳到权限设置页面 跳到应用的详情页面，可以通过应用详情跳转到权限页面
//	 * @param:
//	 */
//	private void getAppDetailSettingIntent() {
//		Intent localIntent = new Intent();
//		localIntent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
//		if ( Build.VERSION.SDK_INT >= 9 ) {
//			localIntent.setAction( "android.settings.APPLICATION_DETAILS_SETTINGS" );
//			localIntent.setData( Uri.fromParts( "package", mActivity.getPackageName(), null ) );
//		} else if ( Build.VERSION.SDK_INT <= 8 ) {
//			localIntent.setAction( Intent.ACTION_VIEW );
//			localIntent.setClassName( "com.android.settings", "com.android.settings.InstalledAppDetails" );
//			localIntent.putExtra( "com.android.settings.ApplicationPkgName", mActivity.getPackageName() );
//		}
//		mActivity.startActivity( localIntent );
//	}
//
//
//	/**
//	 * @author:lei
//	 * @date:2017/7/17
//	 * @since: v1.0
//	 * @desc: 未获取权限，跳转到设置页面
//	 * @param:
//	 */
//	public void showDialogToSetting( String... permission ) {
//		StringBuffer buffer = new StringBuffer();
//		if ( null == permission || 0 == permission.length ) {
//			return;
//		} else {
//			for (int i = 0; i < permission.length; i++) {
//				buffer.append( permission[ i ] )
//					  .append( "，" );
//			}
//		}
//
//		final NormalDialog dialog = new NormalDialog( mActivity );
//		dialog.content( mActivity.getString( R.string.need_open ) + buffer.toString() + mActivity.getString( R.string.get_permission_to_setting ) )//
//			  .show();
//
//		dialog.setOnBtnClickL( new OnBtnClickL() {
//			@Override
//			public void onBtnClick() {
////                        ToastUitl.showShort("取消");
//				dialog.dismiss();
//			}
//		}, new OnBtnClickL() {
//			@Override
//			public void onBtnClick() {
////                        ToastUitl.showShort("确定");
//				//跳转到设置页面
//				getAppDetailSettingIntent();
//				dialog.dismiss();
//			}
//		} );
//	}
//
//
//}
