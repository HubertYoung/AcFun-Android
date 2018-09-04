//package com.hubertyoung.common.utils;
//
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * <br>
// * function:
// * <p>
// *
// * @author:HubertYoung
// * @date:2018/9/4 14:51
// * @since:V$VERSION
// * @desc:com.hubertyoung.common.utils
// */
//public class UnitUtil {
//	public static final String COLON_SEPARATOR = ":";
//	private static final DecimalFormat mDecimalFormat = new DecimalFormat( "00" );
//
//	public static synchronized String formatTime( long time ) {
//		String str;
//		synchronized ( UnitUtil.class ) {
//			StringBuilder builder;
//			if ( time > 60 ) {
//				time %= 60;
//				builder = new StringBuilder();
//				builder.append( mDecimalFormat.format( time / 60 ) );
//				builder.append( COLON_SEPARATOR );
//				builder.append( mDecimalFormat.format( time ) );
//				str = builder.toString();
//			} else {
//				builder = new StringBuilder();
//				builder.append( "00:" );
//				builder.append( mDecimalFormat.format( time ) );
//				str = builder.toString();
//			}
//		}
//		return str;
//	}
//
//	public static synchronized String formatTime( String str, Date date ) {
//		String currentTimeStr;
//		synchronized ( UnitUtil.class ) {
//			long currentTimeMillis = ( System.currentTimeMillis() - date.getTime() ) / 1000;
//			if ( currentTimeMillis < 3600 ) {
//				int floor = ( int ) Math.floor( ( double ) ( currentTimeMillis / 60 ) );
//				if ( floor == 0 ) {
//					floor = 1;
//				}
//				StringBuilder stringBuilder = new StringBuilder();
//				stringBuilder.append( floor );
//				stringBuilder.append( "分钟前" );
//				currentTimeStr = stringBuilder.toString();
//			} else {
//				int floor2 = ( int ) Math.floor( ( double ) ( currentTimeMillis / 3600 ) );
//				if ( floor2 == 0 ) {
//					floor2 = 1;
//				}
//				if ( floor2 < 24 ) {
//					StringBuilder stringBuilder2 = new StringBuilder();
//					stringBuilder2.append( floor2 );
//					stringBuilder2.append( "小时前" );
//					currentTimeStr = stringBuilder2.toString();
//				} else {
//					currentTimeStr = getStrTostrByDate( str, date );
//				}
//			}
//		}
//		return currentTimeStr;
//	}
//
//	public static synchronized String getStrTostrByDate( String str, Date date ) {
//		synchronized ( UnitUtil.class ) {
//			str = new SimpleDateFormat( str ).format( date );
//		}
//		return str;
//	}
//	public static void main( String[] args ) {
//
//		System.out.println( UnitUtil.formatTime( 1536044787 ) );
//	}
//}
