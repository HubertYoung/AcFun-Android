package com.hubertyoung.common.utils;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 17:51
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.utils
 */
public class Regular {
	public static final String PARAMETERS_SEPARATOR = "&";

	public static String addressToEscape( String str ) {
		return ( str == null || str.length() <= 0 ) ? str : str.replaceAll( "&amp;", PARAMETERS_SEPARATOR ).replaceAll( "&quot;", "\"" ).replaceAll( "&gt;", ">" ).replaceAll( "&lt;", "<" );
	}

	public static Matcher matcher( String str, String str2 ) {
		return Pattern.compile( str ).matcher( str2 );
	}

	public static int intValue( String str ) {
		try {
			return Integer.valueOf( str ).intValue();
		} catch ( NumberFormatException unused ) {
			return -1;
		}
	}

	/**
	 * @param str "UTF-8"
	 * @return
	 */
	public static String MD5( String str ) {
		if ( str == null || str.length() <= 0 ) {
			return "";
		}
		try {
			MessageDigest instance = MessageDigest.getInstance( "MD5" );
			instance.update( str.getBytes() );
			return toHex( instance.digest() );
		} catch ( Exception unused ) {
			return "";
		}
	}

	private static String toHex( byte[] bytes ) {

		final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		StringBuilder ret = new StringBuilder( bytes.length * 2 );
		for (int i = 0; i < bytes.length; i++) {
			ret.append( HEX_DIGITS[ ( bytes[ i ] >> 4 ) & 0x0f ] );
			ret.append( HEX_DIGITS[ bytes[ i ] & 0x0f ] );
		}
		return ret.toString();
	}
}
