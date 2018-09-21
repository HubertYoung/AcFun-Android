package com.hubertyoung.component_acfunmine.api;

import com.hubertyoung.base.annotation.Environment;
import com.hubertyoung.base.annotation.Module;
import com.hubertyoung.common.api.ApiConstants;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/21 17:27
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.api
 */
public class EnvironmentConfig {
	/**
	 * 特殊模块 Music 的环境
	 */
	@Module(alias = "我的")
	private class Music {
		@Environment(url = ApiConstants.NETEAST_HOST, isRelease = true, alias = "正式")
		private String online;

		@Environment(url = ApiConstants.NETEAST_TEST_HOST, alias = "测试")
		private String test;
	}
}
