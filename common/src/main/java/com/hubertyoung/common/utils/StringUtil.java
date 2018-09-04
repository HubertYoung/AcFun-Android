package com.hubertyoung.common.utils;

import android.content.Context;
import android.text.TextUtils;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.R;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 17:20
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.utils
 */
public class StringUtil {
	//	public static final long KB = 1024;
	public static final BigDecimal KB = BigDecimal.valueOf( 1024 );
	//	public static final long MB = 1024 * 1024;
	public static final BigDecimal MB = KB.multiply( KB );
	//	public static final long e = 1024 * 1024 * 1024;
	public static final BigDecimal GB = KB.multiply( MB );
	//	public static final long g = 1024 * 1024 * 1024 * 1024;
	public static final BigDecimal TB = KB.multiply( GB );
	//	public static final long i = 1024 * 1024 * 1024 * 1024 * 1024;
	public static final BigDecimal PB = KB.multiply( TB );
	//	public static final long k = 1024 * 1024 * 1024 * 1024 * 1024 * 1024;
	public static final BigDecimal EB = KB.multiply( PB );
	//	public static final BigDecimal m = BigDecimal.valueOf( 1024 ).multiply( BigDecimal.valueOf( EB ) );
//	public static final BigDecimal n = KB.multiply( m );
	private static final long o = 31457280;
	private static final String[] forbiddenWords = CommonApplication.getAppContext().getResources().getStringArray( R.array.forbidden_words );

	public static String a( int i, int i2 ) {
		long round = Math.round( Math.pow( 10.0d, ( double ) i2 ) );
		if ( ( ( long ) i ) < round ) {
			return String.valueOf( i );
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( round - 1 );
		stringBuilder.append( "+" );
		return stringBuilder.toString();
	}

	public static String random() {
		return String.valueOf( new Random().nextLong() );
	}

	public static String formattedStorage( long j ) {
		return formattedStorage( BigDecimal.valueOf( j ) );
	}

	private static String formattedStorage( BigDecimal bigDecimal ) {
		StringBuilder stringBuilder;
		if ( bigDecimal.divide( EB, 1 ).compareTo( BigDecimal.ZERO ) > 0 ) {
			stringBuilder = new StringBuilder();
			stringBuilder.append( String.valueOf( bigDecimal.divide( EB ).setScale( 1, 4 ) ) );
			stringBuilder.append( "E" );
			return stringBuilder.toString();
		} else if ( bigDecimal.divide( PB, 1 ).compareTo( BigDecimal.ZERO ) > 0 ) {
			stringBuilder = new StringBuilder();
			stringBuilder.append( String.valueOf( bigDecimal.divide( PB ).setScale( 1, 4 ) ) );
			stringBuilder.append( "P" );
			return stringBuilder.toString();
		} else if ( bigDecimal.divide( TB, 1 ).compareTo( BigDecimal.ZERO ) > 0 ) {
			stringBuilder = new StringBuilder();
			stringBuilder.append( String.valueOf( bigDecimal.divide( TB ).setScale( 1, 4 ) ) );
			stringBuilder.append( "T" );
			return stringBuilder.toString();
		} else if ( bigDecimal.divide( GB, 1 ).compareTo( BigDecimal.ZERO ) > 0 ) {
			stringBuilder = new StringBuilder();
			stringBuilder.append( String.valueOf( bigDecimal.divide( GB ).setScale( 1, 4 ) ) );
			stringBuilder.append( "G" );
			return stringBuilder.toString();
		} else if ( bigDecimal.divide( MB, 1 ).compareTo( BigDecimal.ZERO ) > 0 ) {
			stringBuilder = new StringBuilder();
			stringBuilder.append( String.valueOf( bigDecimal.divide( MB ).setScale( 1, 4 ) ) );
			stringBuilder.append( "M" );
			return stringBuilder.toString();
		} else if ( bigDecimal.divide( KB, 1 ).compareTo( BigDecimal.ZERO ) > 0 ) {
			stringBuilder = new StringBuilder();
			stringBuilder.append( String.valueOf( bigDecimal.divide( KB ).setScale( 1, 4 ) ) );
			stringBuilder.append( "K" );
			return stringBuilder.toString();
		} else {
			stringBuilder = new StringBuilder();
			stringBuilder.append( String.valueOf( bigDecimal ) );
			stringBuilder.append( " bytes" );
			return stringBuilder.toString();
		}
	}

	public static boolean isForbiddenWords( String str ) {
		if ( TextUtils.isEmpty( str ) ) {
			return false;
		}
		for (CharSequence contains : forbiddenWords) {
			if ( str.contains( contains ) ) {
				return true;
			}
		}
		return false;
	}

	public static boolean b( String str ) {
		return str.contains( ";" ) || //
				str.contains( "<" ) || //
				str.contains( ">" ) || //
				str.contains( "%3C" ) || //
				str.contains( "%3E" ) || //
				str.contains( "'" ) || //
				str.contains( "\"" ) || //
				str.contains( "*" ) || //
				str.contains( " " );
	}

	public static String formattingTime( long times ) {
		int currentTimeMillis = ( int ) ( ( System.currentTimeMillis() - times ) / 1000 );
		if ( currentTimeMillis < 900 ) {
			return CommonApplication.getAppContext().getResources().getString( R.string.common_update_time_moments_ago );
		}
		if ( currentTimeMillis >= 900 && currentTimeMillis <= 3600 ) {
			return CommonApplication.getAppContext().getResources().getString( R.string.common_update_time_minutes_ago );
		}
		if ( currentTimeMillis > 3600 && currentTimeMillis <= 86400 ) {
			return CommonApplication.getAppContext().getResources().getString( R.string.common_update_time_hours_ago );
		}
		if ( currentTimeMillis < 86400 || currentTimeMillis > 604800 ) {
			return CommonApplication.getAppContext().getResources().getString( R.string.common_update_time_weeks_ago );
		}
		return CommonApplication.getAppContext().getResources().getString( R.string.common_update_time_few_days );
	}

	public static String formattingTimeByPlaceholder( long time ) {
		int currentTimeMillis = ( int ) ( ( System.currentTimeMillis() - time ) / 1000 );
		if ( currentTimeMillis < 900 ) {
			return CommonApplication.getAppContext().getResources().getString( R.string.common_update_time_moments_ago );
		}
		if ( currentTimeMillis >= 900 && currentTimeMillis <= 3600 ) {
			return CommonApplication.getAppContext().getResources().getString( R.string.common_exact_update_time_minutes_ago, Integer.valueOf( currentTimeMillis / 60 ) );
		} else if ( currentTimeMillis > 3600 && currentTimeMillis <= 86400 ) {
			return CommonApplication.getAppContext().getResources().getString( R.string.common_exact_update_time_hours_ago, Integer.valueOf( currentTimeMillis / 3600 ) );
		} else if ( currentTimeMillis < 86400 || currentTimeMillis > 604800 ) {
			return new SimpleDateFormat( CommonApplication.getAppContext().getResources().getString( R.string.data_time_format_text ) ).format( new Date( time ) );
		} else {
			return CommonApplication.getAppContext().getResources().getString( R.string.common_exact_update_time_few_days, Integer.valueOf( currentTimeMillis / 86400 ) );
		}
	}

	public static String matcher( String str ) {
		if ( TextUtils.isEmpty( str ) ) {
			return "";
		}
		String group1 = "";
		String group2 = "";
		String group3 = "";
		Matcher matcher = Regular.matcher( "(.*)(\\[at\\].*\\[\\/at\\])(.*)", str );
		if ( matcher.find() ) {
			if ( matcher.groupCount() > 0 ) {
				group1 = matcher.group( 1 );
			}
			if ( matcher.groupCount() > 1 ) {
				group2 = matcher.group( 2 ).replaceAll( "\\[at\\](.*)\\[\\/at\\]", "@$1" );
			}
			if ( matcher.groupCount() > 2 ) {
				group3 = matcher.group( 3 );
			}
			StringBuilder stringBuilder;
			if ( TextUtils.isEmpty( group3 ) ) {
				stringBuilder = new StringBuilder();
				stringBuilder.append( group2 );
				stringBuilder.append( " " );
				stringBuilder.append( group1 );
				group1 = stringBuilder.toString();
			} else {
				stringBuilder = new StringBuilder();
				stringBuilder.append( group2 );
				stringBuilder.append( group3 );
				group1 = stringBuilder.toString();
			}
		}
		return group1;
	}

	public static boolean isSampleMobile( String str ) {
		return Pattern.compile( "^(1)\\d{10}$" ).matcher( str ).matches();
	}

	public static boolean isLetter( String str ) {
		return Pattern.compile( "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,}$" ).matcher( str ).find();
	}

	public static boolean isMobile( String str ) {
		return Pattern.compile( "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$" )
				.matcher( str )
				.matches() || Pattern.compile( "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$" ).matcher( str ).matches();
	}

	public static String formatNickNameStr( String str ) {
		if ( isSampleMobile( str ) ) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append( str.substring( 0, 3 ) );
			stringBuilder.append( "******" );
			stringBuilder.append( str.substring( 9 ) );
			return stringBuilder.toString();
		} else if ( !isLetter( str ) ) {
			return str;
		} else {
			int lastIndexOf = str.lastIndexOf( "@" );
			StringBuilder stringBuilder2 = new StringBuilder();
			stringBuilder2.append( str.substring( 0, 1 ) );
			stringBuilder2.append( "****" );
			stringBuilder2.append( str.substring( lastIndexOf ) );
			return stringBuilder2.toString();
		}
	}

	public static String formatNum( int i ) {
		if ( i > 100000 ) {
			double doubleValue = new BigDecimal( i / 10000 ).setScale( 1, 4 ).doubleValue();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append( doubleValue );
			stringBuilder.append( "W" );
			String numStr = stringBuilder.toString();
			if ( numStr.contains( ".0W" ) ) {
				return numStr.replace( ".0W", "W" );
			}
			return numStr;
		}
		StringBuilder builder = new StringBuilder();
		builder.append( i );
		return builder.toString();
	}

	public static String formatChineseNum( Context context, int num ) {
		if ( num > 100000 ) {
			double doubleValue = new BigDecimal( num / 10000 ).setScale( 1, 4 ).doubleValue();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append( doubleValue );
			String stringBuilder2 = stringBuilder.toString();
			if ( stringBuilder2.contains( ".0" ) ) {
				stringBuilder2 = stringBuilder2.replace( ".0", "" );
			}
			StringBuilder stringBuilder3 = new StringBuilder();
			stringBuilder3.append( stringBuilder2 );
			stringBuilder3.append( context.getResources().getString( R.string.wan_text ) );
			return stringBuilder3.toString();
		}
		StringBuilder builder = new StringBuilder();
		builder.append( num );
		return builder.toString();
	}


	public static String escapeCharacter( String str ) {
		return str.replace( "&quot;", "\"" ).replace( "&amp;", "&" ).replace( "&lt;", "<" ).replace( "&gt;", ">" );
	}

	public static String week( Context context, int day ) {
		return ( context == null || day < 1 || day > 7 ) ? "未知" : context.getResources().getStringArray( R.array.weekdays )[ day - 1 ];
	}

	public static String expressionImgCompile( String str ) {
		Matcher matcher = Pattern.compile( "/emotion/images/(ac|ac2|ac3|ais|tsj|brd|td|blizzard)/\\d+\\.gif" ).matcher( str );
		return matcher.find() ? matcher.group() : str;
	}

	public static List< String > imgCompileList( String str ) {
		List< String > arrayList = new ArrayList();
		Matcher matcher = Pattern.compile( "<img.*?src=\\\"(.*?)\\\".*?>" ).matcher( str );
		int i = 0;
		while ( matcher.find() ) {
			if ( matcher.start() > i ) {
				arrayList.add( str.substring( i, matcher.start() ) );
			}
			arrayList.add( str.substring( matcher.start(), matcher.end() ) );
			i = matcher.end();
		}
		if ( i != str.length() ) {
			arrayList.add( str.substring( i, str.length() ) );
		}
		return arrayList;
	}

	public static String imgCompile( String str ) {
		Matcher matcher = Pattern.compile( "<(img|IMG)(.*?)(/>|></img>|>)" ).matcher( str );
		boolean find = matcher.find();
		String str2 = null;
		if ( find ) {
			while ( find ) {
				Matcher matcher2 = Pattern.compile( "(src|SRC)=(\"|')(.*?)(\"|')" ).matcher( matcher.group( 2 ) );
				if ( matcher2.find() ) {
					str2 = matcher2.group( 3 );
				}
				find = matcher.find();
			}
		}
		return str2;
	}
}
