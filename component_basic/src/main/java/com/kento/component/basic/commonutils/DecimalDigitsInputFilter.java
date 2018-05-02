//package com.kento.component.basic.commonutils;
//
//import android.text.InputFilter;
//import android.text.Spanned;
//
///**
// * <br>
// * function:限制输入小数点后两位
// * <p>
// *
// * @author:Yang
// * @date:2018/3/9 下午10:24
// * @since:V$VERSION
// * @desc:ddframework.gent.common.commonutils
// */
//public class DecimalDigitsInputFilter implements InputFilter {
//
//	private int decimalDigits;
//
//	/**
//	 * Constructor.
//	 *
//	 * @param decimalDigits maximum decimal digits
//	 */
//	public DecimalDigitsInputFilter( int decimalDigits ) {
//		this.decimalDigits = decimalDigits;
//	}
//
//	@Override
//	public CharSequence filter( CharSequence source, int start, int end, Spanned dest, int dstart, int dend ) {
//
//
//		int dotPos = -1;
//		int len = dest.length();
//		for (int i = 0; i < len; i++) {
//			char c = dest.charAt( i );
//			if ( c == '.' || c == ',' ) {
//				dotPos = i;
//				break;
//			}
//		}
//		if ( dotPos >= 0 ) {
//
//			// protects against many dots
//			if ( source.equals( "." ) || source.equals( "," ) ) {
//				return "";
//			}
//			// if the text is entered before the dot
//			if ( dend <= dotPos ) {
//				return null;
//			}
//			if ( len - dotPos > decimalDigits ) {
//				return "";
//			}
//		}
//
//		return null;
//	}
//
//}