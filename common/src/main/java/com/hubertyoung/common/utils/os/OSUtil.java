/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hubertyoung.common.utils.os;

import android.os.Build;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Android系统工具箱
 */
public class OSUtil {
	/**
	 * 根据/system/bin/或/system/xbin目录下是否存在su文件判断是否已ROOT
	 *
	 * @return true：已ROOT
	 */
	public static boolean isRoot() {
		try {
			return new File( "/system/bin/su" ).exists() || new File( "/system/xbin/su" ).exists();
		} catch ( Exception e ) {
			return false;
		}
	}

	private static final String ROM_MIUI = "MIUI";
	private static final String ROM_EMUI = "EMUI";
	private static final String ROM_FLYME = "FLYME";
	private static final String ROM_OPPO = "OPPO";
	private static final String ROM_SMARTISAN = "SMARTISAN";
	private static final String ROM_VIVO = "VIVO";
	private static final String ROM_QIKU = "QIKU";

	private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
	private static final String KEY_VERSION_EMUI = "ro.build.version.emui";
	private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";
	private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";
	private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";

	private static String sName;
	private static String sVersion;

	/**
	 * 判断是否为MIUI6以上
	 */
	public static boolean isMIUI6Later() {
		try {
			Class< ? > clz = Class.forName( "android.os.SystemProperties" );
			Method mtd = clz.getMethod( "get", String.class );
			String val = ( String ) mtd.invoke( null, "ro.miui.ui.version.name" );
			val = val.replaceAll( "[vV]", "" );
			int version = Integer.parseInt( val );
			return version >= 6;
		} catch ( Exception e ) {
			return false;
		}
	}

	public static boolean isEmui() {
		return check( ROM_EMUI );
	}

	public static boolean isMiui() {
		return check( ROM_MIUI );
	}

	public static boolean isVivo() {
		return check( ROM_VIVO );
	}

	public static boolean isOppo() {
		return check( ROM_OPPO );
	}

	public static boolean isFlyme() {
		return check( ROM_FLYME );
	}

	public static boolean is360() {
		return check( ROM_QIKU ) || check( "360" );
	}

	public static boolean isSmartisan() {
		return check( ROM_SMARTISAN );
	}

	public static String getName() {
		if ( sName == null ) {
			check( "" );
		}
		return sName;
	}

	public static String getVersion() {
		if ( sVersion == null ) {
			check( "" );
		}
		return sVersion;
	}

	private static boolean check( String rom ) {
		if ( sName != null ) {
			return sName.equals( rom );
		}

		if ( !TextUtils.isEmpty( sVersion = getProp( KEY_VERSION_MIUI ) ) ) {
			sName = ROM_MIUI;
		} else if ( !TextUtils.isEmpty( sVersion = getProp( KEY_VERSION_EMUI ) ) ) {
			sName = ROM_EMUI;
		} else if ( !TextUtils.isEmpty( sVersion = getProp( KEY_VERSION_OPPO ) ) ) {
			sName = ROM_OPPO;
		} else if ( !TextUtils.isEmpty( sVersion = getProp( KEY_VERSION_VIVO ) ) ) {
			sName = ROM_VIVO;
		} else if ( !TextUtils.isEmpty( sVersion = getProp( KEY_VERSION_SMARTISAN ) ) ) {
			sName = ROM_SMARTISAN;
		} else {
			sVersion = Build.DISPLAY;
			if ( sVersion.toUpperCase().contains( ROM_FLYME ) ) {
				sName = ROM_FLYME;
			} else {
				sVersion = Build.UNKNOWN;
				sName = Build.MANUFACTURER.toUpperCase();
			}
		}
		return sName.equals( rom );
	}

	private static String getProp( String name ) {
		String line;
		BufferedReader input = null;
		try {
			Process p = Runtime.getRuntime().exec( "getprop " + name );
			input = new BufferedReader( new InputStreamReader( p.getInputStream() ), 1024 );
			line = input.readLine();
			input.close();
		} catch ( IOException ex ) {
			return null;
		} finally {
			if ( input != null ) {
				try {
					input.close();
				} catch ( IOException e ) {
					e.printStackTrace();
				}
			}
		}
		return line;
	}

	/**
	 * 判断是否Flyme4以上
	 */
	public static boolean isFlyme4Later() {
		return Build.FINGERPRINT.contains( "Flyme_OS_4" ) || Build.VERSION.INCREMENTAL.contains( "Flyme_OS_4" ) || Pattern.compile( "Flyme OS [4|5]", Pattern.CASE_INSENSITIVE )
				.matcher( Build.DISPLAY )
				.find();
	}

}