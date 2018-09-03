package com.hubertyoung.common.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author:Yang
 * @date:2017/8/9 22:30
 * @since:v1.0
 * @desc:com.hubertyoung.common.commonutils
 * @param:输入框弹出管理
 */
public class KeyBordUtil {
	/**
	 * 显示和隐藏软键盘 View ： EditText、TextView isShowing : true = show , false = hide
	 *
	 * @param context
	 * @param view
	 * @param isShow
	 */
	public static void popSoftKeyboard( Context context, View view, boolean isShow ) {
		InputMethodManager imm = ( InputMethodManager ) context.getSystemService( Context.INPUT_METHOD_SERVICE );
		if ( imm != null && view != null ) {
			if ( isShow ) {
				view.requestFocus();
				imm.showSoftInput( view, InputMethodManager.SHOW_IMPLICIT );
			} else {
				imm.hideSoftInputFromWindow( view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
			}
		}
	}

	/**
	 * 显示软键盘
	 *
	 * @param view
	 */
	public static void showSoftKeyboard( View view ) {
		Context context = view.getContext();
		InputMethodManager imm = ( InputMethodManager ) context.getSystemService( Context.INPUT_METHOD_SERVICE );
		view.setFocusable( true );
		view.setFocusableInTouchMode( true );
		view.requestFocus();
		imm.showSoftInput( view, InputMethodManager.SHOW_IMPLICIT );
	}

	/**
	 * 隐藏软键盘
	 *
	 * @param view
	 */
	public static void hideSoftKeyboard( View view ) {
		Context context = view.getContext();
		InputMethodManager imm = ( InputMethodManager ) context.getSystemService( Context.INPUT_METHOD_SERVICE );
		imm.hideSoftInputFromWindow( view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY );
	}

}
