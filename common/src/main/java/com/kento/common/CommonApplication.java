package com.kento.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.kento.common.utils.CommonLog;
import com.kento.common.widget.skin.auto.SkinHookAutoLayoutViewInflater;
import com.kento.common.widget.skin.cardview.SkinCardViewInflater;
import com.kento.common.widget.skin.constraintview.SkinConstraintViewInflater;
import com.kento.common.widget.skin.materialview.SkinMaterialViewInflater;

import skin.support.SkinCompatManager;

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
//@AcraCore( includeDropBoxSystemTags = true, reportFormat = StringFormat.KEY_VALUE_LIST )
////@AcraToast( resText = R.string.common_res_app_crash_str )
//@AcraHttpSender( uri = "http://d1bustest.d1-bus.com/socialbus/api/coupon/getCouponList",
//		httpMethod = HttpSender.Method.POST,
//		connectionTimeout = 15 * 1000,
//		socketTimeout = 15 * 1000,
//		dropReportsOnTimeout = true )
public class CommonApplication extends Application {

	private static CommonApplication mBaseApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		mBaseApplication = this;
		CommonLog.logInit( BuildConfig.DEBUG );

		SkinCompatManager.withoutActivity( this )                          // 基础控件换肤初始化
				.addHookInflater( new SkinHookAutoLayoutViewInflater() ).addInflater( new SkinMaterialViewInflater() )    // material design
				.addInflater( new SkinConstraintViewInflater() )  // ConstraintLayout
				.addInflater( new SkinCardViewInflater() )        // CardView v7
//						 .addInflater(new SkinCircleImageViewInflater()) // hdodenhof/CircleImageView
//						 .addInflater( new SkinFlycoTabLayoutInflater() )  // H07000223/FlycoTabLayout
				.loadSkin();                                  // 加载当前皮肤库(保存在SharedPreferences中)
	}

	@Override
	protected void attachBaseContext( Context base ) {
		super.attachBaseContext( base );
		MultiDex.install( this );
//		If you are using legacy multidex, ensure that ACRA.init(...) is called after Multidex.install().
//		ACRA.init( this );
	}

	public static Context getAppContext() {
		return mBaseApplication;
	}
}
