package com.hubertyoung.acfun;

import com.billy.cc.core.component.CC;
import com.hubertyoung.common.CommonApplication;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:Yang
 * @date:2018/4/19 5:44 PM
 * @since:V$VERSION
 * @desc:com.kento.welcome.commonlibrary
 */
public class MyApplication extends CommonApplication {
	@Override
	public void onCreate() {
		super.onCreate();
		// 初始化CC
		CC.enableVerboseLog( BuildConfig.DEBUG);
		CC.enableDebug(BuildConfig.DEBUG);
		CC.enableRemoteCC(BuildConfig.DEBUG);

	}
}
