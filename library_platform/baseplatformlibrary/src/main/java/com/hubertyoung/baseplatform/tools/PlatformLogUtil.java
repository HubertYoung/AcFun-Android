package com.hubertyoung.baseplatform.tools;


import android.util.Log;

/**
 */
public class PlatformLogUtil {
	private static String TAG = PlatformLogUtil.class.getSimpleName();
	private static boolean DEBUG_ENABLE = false;// 是否调试模式

	/**
	 * 在application调用初始化
	 * @param debug 是否开启打印日志功能
	 */
	public static void logInit( boolean debug ) {
		DEBUG_ENABLE = debug;
		if ( DEBUG_ENABLE ) {
//			Logger.init( AppConfig.DEBUG_TAG)                 // default PRETTYLOGGER or use just init()
//				  .methodCount(2)                 // default 2
//				  .logLevel(LogLevel.FULL)        // default LogLevel.FULL
//				  .methodOffset(0);                // default 0
		} else {
//			Logger.init()                 // default PRETTYLOGGER or use just init()
//				  .methodCount(3)                 // default 2
//				  .hideThreadInfo()               // default shown
//				  .logLevel(LogLevel.NONE)        // default LogLevel.FULL
//				  .methodOffset(2);
		}
	}

	public static void logd( String tag, String message ) {
		if ( DEBUG_ENABLE ) {
			Log.d( tag, message );
		}
	}

	public static void logd( String message ) {
		if ( DEBUG_ENABLE ) {
			PlatformLogUtil.logd( TAG, message );
		}
	}
	public static void loge( String tag, String message ) {
		if ( DEBUG_ENABLE ) {
			Log.e( tag, message );
		}
	}
	public static void loge( String message ) {
		if ( DEBUG_ENABLE ) {
			Log.e(TAG, message);
		}
	}
	public static void logi( String tag, String message ) {
		if ( DEBUG_ENABLE ) {
			Log.i( tag, message );
		}
	}
	public static void logi( String message) {
		if ( DEBUG_ENABLE ) {
			Log.i(TAG, message);
		}
	}

	public static void logv( String message) {
		if ( DEBUG_ENABLE ) {
			Log.v(TAG, message);
		}
	}
}
