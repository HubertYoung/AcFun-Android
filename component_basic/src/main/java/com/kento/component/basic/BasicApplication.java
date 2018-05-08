package com.kento.component.basic;

import android.content.Context;

import com.kento.common.CommonApplication;

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

public class BasicApplication extends CommonApplication {

	private static BasicApplication mBaseApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		mBaseApplication = this;
	}


	public static Context getAppContext() {
		return mBaseApplication;
	}
}
