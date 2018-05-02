package com.kento.component.basic;

import android.content.Context;

import com.kento.common.CommonApplication;

import org.acra.ACRA;
import org.acra.annotation.AcraCore;
import org.acra.annotation.AcraHttpSender;
import org.acra.annotation.AcraToast;
import org.acra.data.StringFormat;
import org.acra.sender.HttpSender;

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
@AcraCore(
		includeDropBoxSystemTags = true,
		reportFormat = StringFormat.KEY_VALUE_LIST)
@AcraToast( resText = R.string.app_crash_str )
@AcraHttpSender( uri = "http://d1bustest.d1-bus.com/socialbus/api/coupon/getCouponList",
		httpMethod = HttpSender.Method.POST,
		connectionTimeout = 15 * 1000,
		socketTimeout = 15 * 1000,
		dropReportsOnTimeout = true)
public class BasicApplication extends CommonApplication {

	private static BasicApplication mBaseApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		mBaseApplication = this;
	}

	@Override
	protected void attachBaseContext( Context base ) {
		super.attachBaseContext( base );
//		If you are using legacy multidex, ensure that ACRA.init(...) is called after Multidex.install().
		ACRA.init( this );
	}

	public static Context getAppContext() {
		return mBaseApplication;
	}
}
