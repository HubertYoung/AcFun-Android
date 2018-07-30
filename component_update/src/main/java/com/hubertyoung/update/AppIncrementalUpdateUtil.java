package com.hubertyoung.update;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.hubertyoung.common.utils.AppUtils;

import java.io.File;

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
	private static final String NEW_APK_PATH = "new.apk";
	private static final String PATCH_PATH = "tencent/QQfile_recv/patch.patch";
	private static AppIncrementalUpdateUtil mAppUtil;
	private Context mContext;

	private AppIncrementalUpdateUtil(Context context) {
		mContext = context;
	}

	public static AppIncrementalUpdateUtil get(Context context) {
		if (mAppUtil == null) {
			synchronized (AppIncrementalUpdateUtil.class) {
				if (mAppUtil == null) {
					mAppUtil = new AppIncrementalUpdateUtil(context);
				}
			}
		}
		return mAppUtil;
	}

	public Thread doBspatchThread = new Thread() {

		@Override
		public void run() {
			final File newApk = new File( Environment.getExternalStorageDirectory(), NEW_APK_PATH);
			final File patch = new File(Environment.getExternalStorageDirectory(), PATCH_PATH);

			//一定要检查补丁文件是否存在
			if (!patch.exists()) {
				Log.w(TAG, "doBspatch: patch.patch is not exists");
				return;
			}
			if (!newApk.exists()) {
				Log.d(TAG, "doBspatch: new.apk is not exists");
			} else {
				newApk.delete();
				Log.d(TAG, "doBspatch: new.apk is exists, to del");
			}

			int i = bspatch(extract(),
					newApk.getAbsolutePath(),
					patch.getAbsolutePath());
			Log.i(TAG, "bspatch: result is " + i);


			if (newApk.exists()) {
				install(newApk.getAbsolutePath());
			}
		}
	};

	/**
	 * app安装
	 *
	 * @param apkPath
	 */
	public void install(String apkPath) {
//		Intent i = new Intent(Intent.ACTION_VIEW);
//		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		i.setDataAndType( Uri.fromFile(new File(apkPath)),
//				"application/vnd.android.package-archive");
//		mContext.startActivity(i);
		AppUtils.installApp( apkPath,"com.hubertyoung.component_update" );
	}

	/**
	 * 获取当前程序的版本名
	 */
	public String getVersion() {
		try {
			//获取packagemanager的实例
			PackageManager packageManager = mContext.getPackageManager();
			//getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
			return packInfo.versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 提取当前app的apk路径
	 *
	 * @return
	 */
	public String extract() {
		ApplicationInfo applicationInfo = mContext.getApplicationContext().getApplicationInfo();
		String apkPath = applicationInfo.sourceDir;
		Log.d(TAG, "extract: " + apkPath);
		return apkPath;
	}

	/**
	 * 增量更新：Jni库加载
	 * （module的build.gradle配置ndk {moduleName = 'bsdiff'}）
	 */
	static {
		System.loadLibrary("update-lib");
	}

	/**
	 * 增量更新：合并旧apk和补丁
	 *
	 * @param oldApk
	 * @param newApk
	 * @param patch
	 * @return
	 */
	public native int bspatch(String oldApk, String newApk, String patch);

}
