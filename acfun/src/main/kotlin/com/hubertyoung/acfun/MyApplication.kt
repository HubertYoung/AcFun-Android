package com.hubertyoung.acfun

import com.billy.cc.core.component.CC
import com.hubertyoung.common.CommonApplication

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/4 10:07
 * @since:
 * @see com.hubertyoung.acfun.MyApplication
 */
class MyApplication : CommonApplication() {
	override fun onCreate() {
		super.onCreate()
		// 初始化CC
		CC.enableVerboseLog(BuildConfig.DEBUG)
		CC.enableDebug(BuildConfig.DEBUG)
		CC.enableRemoteCC(BuildConfig.DEBUG)

	}
}