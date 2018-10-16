package com.hubertyoung.common.utils;


import com.tencent.mmkv.MMKV;


/**
 * 对SharedPreference文件中的各种类型的数据进行存取操作
 */
public class SPUtils {


	private static MMKV sp;

	private static void init() {
		if ( sp == null ) {
//			sp = PreferenceManager.getDefaultSharedPreferences( CommonApplication.getAppContext() );
			sp = MMKV.mmkvWithID("myData");
		}
	}

	public static void setSharedIntData( String key, int value ) {
		if ( sp == null ) {
			init();
		}
		sp.edit().putInt( key, value );
	}

	public static int getSharedIntData( String key ) {
		if ( sp == null ) {
			init();
		}
		return sp.getInt( key, 0 );
	}

	public static void setSharedlongData( String key, long value ) {
		if ( sp == null ) {
			init();
		}
		sp.edit().putLong( key, value );
	}

	public static long getSharedlongData( String key ) {
		if ( sp == null ) {
			init();
		}
		return sp.getLong( key, 0l );
	}

	public static void setSharedFloatData( String key, float value ) {
		if ( sp == null ) {
			init();
		}
		sp.edit().putFloat( key, value );
	}

	public static Float getSharedFloatData( String key ) {
		if ( sp == null ) {
			init();
		}
		return sp.getFloat( key, 0f );
	}

	public static void setSharedBooleanData( String key, boolean value ) {
		if ( sp == null ) {
			init();
		}
		sp.edit().putBoolean( key, value );
	}

	public static Boolean getSharedBooleanData( String key ) {
		if ( sp == null ) {
			init();
		}
		return sp.getBoolean( key, false );
	}

	public static Boolean getSharedBooleanData( String key, boolean defValue ) {
		if ( sp == null ) {
			init();
		}
		return sp.getBoolean( key, defValue );
	}

	public static void setSharedStringData( String key, String value ) {
		if ( sp == null ) {
			init();
		}
		sp.edit().putString( key, value );
	}

	public static String getSharedStringData( String key ) {
		if ( sp == null ) {
			init();
		}
		return sp.getString( key, "" );
	}

	public static String getSharedStringData( String key, String defValue ) {
		if ( sp == null ) {
			init();
		}
		return sp.getString( key, defValue );
	}
}