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

package com.kento.common.os;

import android.os.Build;

import java.io.File;
import java.io.IOException;
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

	private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
	private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
	private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

	private static final String KEY_EMUI_VERSION_CODE = "ro.build.hw_emui_api_level";

	public static boolean isMIUI() {
		try {
			final BuildProperties prop = BuildProperties.newInstance();
			return prop.getProperty( KEY_MIUI_VERSION_CODE, null ) != null || prop.getProperty( KEY_MIUI_VERSION_NAME, null ) != null || prop.getProperty( KEY_MIUI_INTERNAL_STORAGE, null ) != null;
		} catch ( final IOException e ) {
			return false;
		}
	}

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

	public static boolean isEMUI() {
		try {
			final BuildProperties prop = BuildProperties.newInstance();
			return prop.getProperty( KEY_EMUI_VERSION_CODE, null ) != null;
		} catch ( final IOException e ) {
			return false;
		}
	}

	public static boolean isFlyme() {
		try {
			// Invoke Build.hasSmartBar()
			Method method = Build.class.getMethod( "hasSmartBar" );
			return ( method != null );
		} catch ( final Exception e ) {
			return false;
		}
	}

	/**
	 * 判断是否Flyme4以上
	 */
	public static boolean isFlyme4Later() {
		return Build.FINGERPRINT.contains( "Flyme_OS_4" ) || Build.VERSION.INCREMENTAL.contains( "Flyme_OS_4" ) || Pattern.compile( "Flyme OS [4|5]", Pattern.CASE_INSENSITIVE )
																														  .matcher( Build.DISPLAY )
																														  .find();
	}

	/**
	 * 判断当前系统是否是Android4.0
	 *
	 * @return 0：是；小于0：小于4.0；大于0：大于4.0
	 */
	public static int isAPI14() {
		return Build.VERSION.SDK_INT - 14;
	}
}