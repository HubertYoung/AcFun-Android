package com.hubertyoung.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 作者：JIUU on 2017/2/24 11:03
 * QQ号：1344393464
 * 作用：金额数据运算
 */
public class MathUtils {
	public static double add( double d1, double d2 ) {        // 进行加法运算
		BigDecimal b1 = new BigDecimal( d1 );
		BigDecimal b2 = new BigDecimal( d2 );
		return b1.add( b2 )
				 .doubleValue();
	}

	public static double sub( double d1, double d2 ) {        // 进行减法运算
		BigDecimal b1 = new BigDecimal( d1 );
		BigDecimal b2 = new BigDecimal( d2 );
		return b1.subtract( b2 )
				 .doubleValue();
	}

	public static double mul( double d1, double d2 ) {        // 进行乘法运算
		BigDecimal b1 = new BigDecimal( d1 );
		BigDecimal b2 = new BigDecimal( d2 );
		return b1.multiply( b2 )
				 .doubleValue();
	}

	public static double div( double d1, double d2, int len ) {// 进行除法运算
		BigDecimal b1 = new BigDecimal( d1 );
		BigDecimal b2 = new BigDecimal( d2 );
		return b1.divide( b2, len, BigDecimal.
				ROUND_HALF_UP )
				 .doubleValue();
	}

	public static double round( double d, int len ) {     // 进行四舍五入
		//操作
		BigDecimal b1 = new BigDecimal( d );
		BigDecimal b2 = new BigDecimal( 1 );
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量，
		//表示进行四舍五入的操作
		return b1.divide( b2, len, BigDecimal.ROUND_HALF_UP )
				 .doubleValue();
	}


	public static BigDecimal add( BigDecimal b1, BigDecimal b2 ) {        // 进行加法运算
		return b1.add( b2 );
	}

	public static BigDecimal sub( BigDecimal b1, BigDecimal b2 ) {        // 进行减法运算
		return b1.subtract( b2 );
	}

	public static BigDecimal mul( BigDecimal b1, BigDecimal b2 ) {        // 进行乘法运算
		return b1.multiply( b2 );
	}

	public static BigDecimal div( BigDecimal b1, BigDecimal b2, int len ) {// 进行除法运算
		return b1.divide( b2, len, BigDecimal.ROUND_HALF_UP );
	}

	public static BigDecimal round( BigDecimal b1, int len ) {     // 进行四舍五入
		//操作
		BigDecimal b2 = new BigDecimal( 1 );
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量，
		//表示进行四舍五入的操作
		return b1.divide( b2, len, BigDecimal.ROUND_HALF_UP );
	}

	/**
	 * 把一个double类型转化为对应的string类型
	 */
	public static String doubleParseToString( double d1 ) {
		Double d = new Double( d1 );
		BigDecimal bd = new BigDecimal( d.toString() );
		return bd.toPlainString();
	}

	/**
	 * 保留两位小数
	 */
	public static String decimalFormat( float value ) {
		DecimalFormat df = new DecimalFormat( "0.00" );
		return df.format( value );
	}

	public static String numFormat( int num ) {
		if ( num >= 10 * 1000 ) {
			DecimalFormat decimalFormat = new DecimalFormat( "0.0" );
			return decimalFormat.format( num / ( 10 * 1000F ) ) + "万";
		} else {
			return num + "";
		}
	}

	public static void main( String[] args ) {

		System.out.println( numFormat(11100) );
	}

}
