package com.kento.common;

import android.app.Application;
import android.content.Context;

import com.kento.common.utils.CommonLog;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:Yang
 * @date:2018/4/19 5:12 PM
 * @since:V1.0
 * @desc:com.kento.component.basic
 */

public class CommonApplication extends Application {

	private static CommonApplication mBaseApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		mBaseApplication = this;
		CommonLog.logInit( BuildConfig.DEBUG );
	}

	public static Context getAppContext() {
		return mBaseApplication;
	}
}
