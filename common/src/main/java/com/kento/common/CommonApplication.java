package com.kento.common;

import android.app.Application;
import android.content.Context;

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

public class CommonApplication extends Application {

	private static CommonApplication mBaseApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		mBaseApplication = this;
		CommonLog.logInit( BuildConfig.DEBUG );

		SkinCompatManager.withoutActivity( this )                          // 基础控件换肤初始化
				.addHookInflater( new SkinHookAutoLayoutViewInflater() )
				.addInflater( new SkinMaterialViewInflater() )    // material design
				.addInflater( new SkinConstraintViewInflater() )  // ConstraintLayout
				.addInflater( new SkinCardViewInflater() )        // CardView v7
//						 .addInflater(new SkinCircleImageViewInflater()) // hdodenhof/CircleImageView
//						 .addInflater( new SkinFlycoTabLayoutInflater() )  // H07000223/FlycoTabLayout
				.loadSkin();                                  // 加载当前皮肤库(保存在SharedPreferences中)
	}

	public static Context getAppContext() {
		return mBaseApplication;
	}
}
