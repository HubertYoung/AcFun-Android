package com.hubertyoung.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.hubertyoung.common.CommonApplication;

import java.io.File;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;


/**
 * 作者：JIUU on 2017/6/25 23:09
 * QQ号：1344393464
 * 作用：获取设备信息
 */
public class AppUtils {
	protected static final String PREFS_FILE = "gank_device_id.xml";
	protected static final String PREFS_DEVICE_ID = "gank_device_id";
	protected static String uuid = null;

	private AppUtils() {
		throw new UnsupportedOperationException( "u can't instantiate me..." );
	}

	/**
	 * 获取app版本号
	 *
	 * @return
	 */
	public static String getAppVersionName() {
		try {
			String pkName = CommonApplication.getAppContext().getPackageName();
			String versionName = CommonApplication.getAppContext().getPackageManager().getPackageInfo( pkName, 0 ).versionName;
			return versionName;
		} catch ( Exception e ) {
			// TODO: 2017/6/25 崩溃日志
		}
		return "";
	}

	/**
	 * 获取app版本code
	 *
	 * @return
	 */
	public static int getAppVersionCode() {
		try {
			String pkName = CommonApplication.getAppContext().getPackageName();
			int versionCode = CommonApplication.getAppContext().getPackageManager().getPackageInfo( pkName, 0 ).versionCode;
			return versionCode;
		} catch ( Exception e ) {
			// TODO: 2017/6/25 崩溃日志
			return 0;
		}
	}

	public static String getDeviceID() {
		String strID = Settings.Secure.getString( CommonApplication.getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID );
		if ( strID == null || strID.equals( "" ) ) {
			strID = getRandom( 16 );
		}
		return strID;
	}

	public static String getUUID() {
		if ( uuid == null ) {
			synchronized ( AppUtils.class ) {
				if ( uuid == null ) {
					SharedPreferences sharedPreferences = CommonApplication.getAppContext().getSharedPreferences( PREFS_FILE, Context.MODE_PRIVATE );
					String str = null;
					String string = sharedPreferences.getString( PREFS_DEVICE_ID, null );
					if ( string != null ) {
						uuid = string;
					} else {
						string = Settings.Secure.getString( CommonApplication.getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID );
						boolean isHavePermission = true;
						try {
							if ( "9774d56d682e549c".equals( string ) ) {
								UUID nameUUIDFromBytes;
								if ( ContextCompat.checkSelfPermission( CommonApplication.getAppContext(), "android.permission.READ_PHONE_STATE" ) != PackageManager.PERMISSION_GRANTED ) {
									isHavePermission = false;
								}
								if ( isHavePermission ) {
									str = ( ( TelephonyManager ) CommonApplication.getAppContext().getSystemService( Context.TELEPHONY_SERVICE ) ).getDeviceId();
								}
								if ( str != null ) {
									nameUUIDFromBytes = UUID.nameUUIDFromBytes( str.getBytes( "utf8" ) );
								} else {
									nameUUIDFromBytes = UUID.randomUUID();
								}
								uuid = nameUUIDFromBytes.toString();
							} else {
								uuid = UUID.nameUUIDFromBytes( string.getBytes( "utf8" ) ).toString();
							}
							if ( isHavePermission ) {
								sharedPreferences.edit().putString( PREFS_DEVICE_ID, uuid ).apply();
							}
						} catch ( Throwable e ) {
							throw new RuntimeException( e );
						}
					}
				}
			}
		}
		return uuid;
	}

	private static String getRandom( int var0 ) {
		String var1 = "0123456789abcdefghijklmnopqrstuvwxyz";
		StringBuilder stringBuilder = new StringBuilder( var0 );
		SecureRandom secureRandom = new SecureRandom();

		for (int i = 0; i < var0; ++i) {
			stringBuilder.append( var1.charAt( secureRandom.nextInt( var1.length() ) ) );
		}

		return stringBuilder.toString();
	}


	/**
	 * 判断App是否安装
	 *
	 * @param packageName 包名
	 * @return {@code true}: 已安装<br>{@code false}: 未安装
	 */
	public static boolean isInstallApp( final String packageName ) {
		return !TextUtils.isEmpty( packageName ) && IntentUtils.getLaunchAppIntent( packageName ) != null;
	}

	/**
	 * 安装App(支持7.0)
	 *
	 * @param filePath  文件路径
	 * @param authority 7.0及以上安装需要传入清单文件中的{@code <provider>}的authorities属性
	 *                  <br>参看https://developer.android.com/reference/android/support/v4/content/FileProvider.html
	 */
	public static void installApp( final String filePath, final String authority ) {
		installApp( FileUtils.getFileByPath( filePath ), authority );
	}

	/**
	 * 安装App（支持7.0）
	 *
	 * @param file      文件
	 * @param authority 7.0及以上安装需要传入清单文件中的{@code <provider>}的authorities属性
	 *                  <br>参看https://developer.android.com/reference/android/support/v4/content/FileProvider.html
	 */
	public static void installApp( final File file, final String authority ) {
		if ( !FileUtils.isFileExists( file ) ) return;
		CommonApplication.getAppContext().startActivity( IntentUtils.getInstallAppIntent( file, authority ) );
	}

	/**
	 * 安装App（支持6.0）
	 *
	 * @param activity    activity
	 * @param filePath    文件路径
	 * @param authority   7.0及以上安装需要传入清单文件中的{@code <provider>}的authorities属性
	 *                    <br>参看https://developer.android.com/reference/android/support/v4/content/FileProvider.html
	 * @param requestCode 请求值
	 */
	public static void installApp( final Activity activity, final String filePath, final String authority, final int requestCode ) {
		installApp( activity, FileUtils.getFileByPath( filePath ), authority, requestCode );
	}

	/**
	 * 安装App(支持6.0)
	 *
	 * @param activity    activity
	 * @param file        文件
	 * @param authority   7.0及以上安装需要传入清单文件中的{@code <provider>}的authorities属性
	 *                    <br>参看https://developer.android.com/reference/android/support/v4/content/FileProvider.html
	 * @param requestCode 请求值
	 */
	public static void installApp( final Activity activity, final File file, final String authority, final int requestCode ) {
		if ( !FileUtils.isFileExists( file ) ) return;
		activity.startActivityForResult( IntentUtils.getInstallAppIntent( file, authority ), requestCode );
	}

	/**
	 * 静默安装App
	 * <p>非root需添加权限 {@code <uses-permission android:name="android.permission.INSTALL_PACKAGES" />}</p>
	 *
	 * @param filePath 文件路径
	 * @return {@code true}: 安装成功<br>{@code false}: 安装失败
	 */
	public static boolean installAppSilent( final String filePath ) {
		File file = FileUtils.getFileByPath( filePath );
		if ( !FileUtils.isFileExists( file ) ) return false;
		String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install " + filePath;
		ShellUtils.CommandResult commandResult = ShellUtils.execCmd( command, !isSystemApp(), true );
		return commandResult.successMsg != null && commandResult.successMsg.toLowerCase().contains( "success" );
	}

	/**
	 * 判断App是否是系统应用
	 *
	 * @return {@code true}: 是<br>{@code false}: 否
	 */
	public static boolean isSystemApp() {
		return isSystemApp( CommonApplication.getAppContext().getPackageName() );
	}

	/**
	 * 判断App是否是系统应用
	 *
	 * @param packageName 包名
	 * @return {@code true}: 是<br>{@code false}: 否
	 */
	public static boolean isSystemApp( final String packageName ) {
		if ( TextUtils.isEmpty( packageName ) ) return false;
		try {
			PackageManager pm = CommonApplication.getAppContext().getPackageManager();
			ApplicationInfo ai = pm.getApplicationInfo( packageName, 0 );
			return ai != null && ( ai.flags & ApplicationInfo.FLAG_SYSTEM ) != 0;
		} catch ( PackageManager.NameNotFoundException e ) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 检测当前应用是否是Debug版本
	 *
	 * @return 是否是Debug版本
	 */
	public static boolean isDebuggable() {
		return CommonApplication.getAppContext().getApplicationInfo() != null && ( CommonApplication.getAppContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE ) != 0;
	}

	/**
	 * 跳转到应用市场
	 * 去评分
	 */
	public static void mark() {
		try {
			Intent viewIntent = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=" + CommonApplication.getAppContext().getPackageName() ) );
			CommonApplication.getAppContext().startActivity( viewIntent );
		} catch ( Exception e ) {
			ToastUtil.showWarning( "手机未安装应用市场" );
		}
	}

	/**
	 * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0   支持4.1.2,4.1.23.4.1.rc111这种形式
	 *
	 * @param version1
	 * @param version2
	 * @return
	 */
	public static int compareVersion( String version1, String version2 ) throws Exception {
		if ( TextUtils.isEmpty( version1 ) || TextUtils.isEmpty( version2 ) ) {
			throw new Exception( "compareVersion error:illegal params." );
		}
		String[] versionArray1 = version1.split( "\\." );//注意此处为正则匹配，不能用"."；
		String[] versionArray2 = version2.split( "\\." );
		int idx = 0;
		int minLength = Math.min( versionArray1.length, versionArray2.length );//取最小长度值
		int diff = 0;
		while ( idx < minLength && ( diff = versionArray1[ idx ].length() - versionArray2[ idx ].length() ) == 0//先比较长度
				&& ( diff = versionArray1[ idx ].compareTo( versionArray2[ idx ] ) ) == 0 ) {//再比较字符
			++idx;
		}
		//如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
		diff = ( diff != 0 ) ? diff : versionArray1.length - versionArray2.length;
		return diff;
	}

	public static boolean isWeixinAvilible( Context context ) {
		final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
		List< PackageInfo > pinfo = packageManager.getInstalledPackages( 0 );// 获取所有已安装程序的包信息
		if ( pinfo != null ) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get( i ).packageName;
				if ( pn.equals( "com.tencent.mm" ) ) {
					return true;
				}
			}
		}
		return false;
	}
}
