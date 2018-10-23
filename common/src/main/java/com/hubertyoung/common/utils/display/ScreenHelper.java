package com.hubertyoung.common.utils.display;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/6/28 13:42
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.utils
 */
public class ScreenHelper {

	private static float sNoncompatDensity;
	private static float sNoncompatScaledDensity;
	/**
	 * 单位dp
	 */
	private static float design_width = 360;

	public static void initCustomDensity( @NonNull final Application application ) {
		application.registerActivityLifecycleCallbacks( new Application.ActivityLifecycleCallbacks() {
			@Override
			public void onActivityCreated( Activity activity, Bundle savedInstanceState ) {
				setCustomDensity( activity, application );
			}

			@Override
			public void onActivityStarted( Activity activity ) {

			}

			@Override
			public void onActivityResumed( Activity activity ) {

			}

			@Override
			public void onActivityPaused( Activity activity ) {

			}

			@Override
			public void onActivityStopped( Activity activity ) {

			}

			@Override
			public void onActivitySaveInstanceState( Activity activity, Bundle outState ) {

			}

			@Override
			public void onActivityDestroyed( Activity activity ) {

			}
		} );
	}

	private static void setCustomDensity( @NonNull Activity activity, @NonNull final Application application ) {
		final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
		if ( sNoncompatDensity == 0 ) {
			sNoncompatDensity = appDisplayMetrics.density;
			sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
			// 监听配置变化，修复切换字体后不变化的情况
			application.registerComponentCallbacks( new ComponentCallbacks() {
				@Override
				public void onConfigurationChanged( Configuration newConfig ) {
					if ( newConfig != null && newConfig.fontScale > 0 ) {
						sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
					}
				}

				@Override
				public void onLowMemory() {

				}
			} );
		}

		// 按宽度design_width 的设计图来适配
		final float targetDensity = Math.min( appDisplayMetrics.widthPixels, appDisplayMetrics.heightPixels ) / design_width;
		final float targetScaledDensity = targetDensity * ( sNoncompatScaledDensity / sNoncompatDensity );
		final int targetDensityDpi = ( int ) ( 160 * targetDensity );

		appDisplayMetrics.density = targetDensity;
		appDisplayMetrics.scaledDensity = targetScaledDensity;
		appDisplayMetrics.densityDpi = targetDensityDpi;

		final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
		activityDisplayMetrics.density = targetDensity;
		activityDisplayMetrics.scaledDensity = targetScaledDensity;
		activityDisplayMetrics.densityDpi = targetDensityDpi;
	}
}
