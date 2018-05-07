package com.kento.common.utils;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * @author:Yang
 * @date:2017/8/22 19:02
 * @since:v1.0
 * @desc:com.kento.common.commonutils
 * @param:底部导航条Indicator的长短
 */
public class TabIndicator {

	public static void setIndicator( Activity activity, TabLayout tabs, int leftDip, int rightDip ) {
		Class< ? > tabLayout = tabs.getClass().getSuperclass();
		Field tabStrip = null;
		try {
			tabStrip = tabLayout.getDeclaredField( "mTabStrip" );
		} catch ( NoSuchFieldException e ) {
			e.printStackTrace();
		}

		tabStrip.setAccessible( true );
		LinearLayout ll_tab = null;
		try {
			ll_tab = ( LinearLayout ) tabStrip.get( tabs );
		} catch ( IllegalAccessException e ) {
			e.printStackTrace();
		}
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager()
				.getDefaultDisplay()
				.getMetrics( displayMetrics );
		int left = ( int ) ( displayMetrics.density * leftDip );
		int right = ( int ) ( displayMetrics.density * rightDip );

		for (int i = 0; i < ll_tab.getChildCount(); i++) {
			View child = ll_tab.getChildAt( i );
			child.setPadding( 0, 0, 0, 0 );
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.MATCH_PARENT, 1 );
			params.leftMargin = left;
			params.rightMargin = right;
			child.setLayoutParams( params );
			child.invalidate();
		}
	}
}
