package com.hubertyoung.update;

import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.hubertyoung.common.utils.AppUtils;
import com.hubertyoung.common.utils.ToastUtil;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * <br>
 * function:增量更新
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/7/30 18:12
 * @since:V1.0
 * @desc:com.hubertyoung.update
 */
public class AppIncrementalUpdateUtil {
	private final String TAG = getClass().getSimpleName();
	private static AppIncrementalUpdateUtil mAppUtil;

	private AppIncrementalUpdateUtil() {
	}

	public static AppIncrementalUpdateUtil get() {
		if ( mAppUtil == null ) {
			synchronized ( AppIncrementalUpdateUtil.class ) {
				if ( mAppUtil == null ) {
					mAppUtil = new AppIncrementalUpdateUtil();
				}
			}
		}
		return mAppUtil;
	}

	public void incrementalInstall( String oldApk, String newApk, String patch ) {
		final File newApkFile = new File( newApk );
		final File oldApkFile = new File( oldApk );
		final File patchFile = new File( patch );
		Observable.just( "" )//
				.map( new Function< String, Boolean >() {
					@Override
					public Boolean apply( String s ) throws Exception {
						//一定要检查补丁文件是否存在
						if ( !patchFile.exists() ) {
							Log.w( TAG, "doBspatch: patch.patch is not exists" );
							return false;
						}
						if ( !newApkFile.exists() ) {
							Log.d( TAG, "doBspatch: new.apk is not exists" );
						} else {
							newApkFile.delete();
							Log.d( TAG, "doBspatch: new.apk is exists, to del" );
						}

						boolean b = bspatch( oldApkFile.getAbsolutePath(), newApkFile.getAbsolutePath(), patchFile.getAbsolutePath() );
						Log.i( TAG, "bspatch: result is " + b );

						return b;
					}
				} )//
				.subscribeOn( Schedulers.io() )//
				.observeOn( AndroidSchedulers.mainThread() )//
				.subscribe( new Consumer< Boolean >() {
					@Override
					public void accept( Boolean aBoolean ) throws Exception {
						if ( aBoolean ) {
							if ( newApkFile.exists() ) {
								install( newApkFile.getAbsolutePath() );
							}
							ToastUtil.showSuccess( "apk生成到" + oldApkFile.getAbsolutePath() );
//							mContext.startActivity(new Intent(Intent.ACTION_VIEW).setDataAndType( Uri.fromFile(outApkFile),
//									"application/vnd.android.package-archive"));
						} else {
							ToastUtil.showError( "apk生成失败！你到底放了patch包没有！" );
						}
					}
				} );
	}

	public void incrementalDiff( String oldApk, String newApk, String patch ) {
		final File newApkFile = new File( newApk );
		final File oldApkFile = new File( oldApk );
		final File patchFile = new File( patch );
		Observable.just( "" )//
				.map( new Function< String, Boolean >() {
					@Override
					public Boolean apply( String s ) throws Exception {
						//一定要检查补丁文件是否存在
						if ( !patchFile.exists() ) {
							Log.w( TAG, "doBspatch: patch.patch is not exists" );
						} else {
							patchFile.delete();
							Log.d( TAG, "doBspatch: patch.patch is exists, to del" );
						}

						boolean b = diff( oldApkFile.getAbsolutePath(), newApkFile.getAbsolutePath(), patchFile.getAbsolutePath() );
						Log.i( TAG, "bspatch: result is " + b );

						return b;
					}
				} )//
				.subscribeOn( Schedulers.io() )//
				.observeOn( AndroidSchedulers.mainThread() )//
				.subscribe( new Consumer< Boolean >() {
					@Override
					public void accept( Boolean aBoolean ) throws Exception {
						if ( aBoolean ) {
							if ( patchFile.exists() ) {
								ToastUtil.showSuccess( "patch生成到" + patchFile.getAbsolutePath() );
//								mContext.startActivity( new Intent( Intent.ACTION_VIEW ).setDataAndType( Uri.fromFile( patchFile ), "application/vnd.android.package-archive" ) );
							} else {
								ToastUtil.showError( "apk生成失败" );
							}
						} else {
							ToastUtil.showError( "apk生成失败" );
						}
					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( Throwable throwable ) throws Exception {
						Log.e( "TAG", "" );
					}
				} );
	}

	/**
	 * app安装
	 *
	 * @param apkPath
	 */
	private void install( String apkPath ) {
//		Intent i = new Intent(Intent.ACTION_VIEW);
//		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		i.setDataAndType( Uri.fromFile(new File(apkPath)),
//				"application/vnd.android.package-archive");
//		mContext.startActivity(i);
		AppUtils.installApp( apkPath, "com.acty.litemallupdate.fileprovider" );
	}

//	/**
//	 * 获取当前程序的版本名
//	 */
//	public String getVersion() {
//		try {
//			//获取packagemanager的实例
//			PackageManager packageManager = mContext.getPackageManager();
//			//getPackageName()是你当前类的包名，0代表是获取版本信息
//			PackageInfo packInfo = packageManager.getPackageInfo( mContext.getPackageName(), 0 );
//			return packInfo.versionName;
//		} catch ( Exception e ) {
//			e.printStackTrace();
//		}
//		return "";
//	}

	/**
	 * 增量更新：Jni库加载
	 * （module的build.gradle配置ndk {moduleName = 'bsdiff'}）
	 */
	static {
		System.loadLibrary( "update-lib" );
	}

	/**
	 * 增量更新：合并旧apk和补丁
	 *
	 * @param oldApk
	 * @param newApk
	 * @param patch
	 * @return
	 */
	public synchronized static native boolean bspatch( String oldApk, String newApk, String patch );

	public synchronized static native boolean diff( String oldApk, String newApk, String patch );

}
