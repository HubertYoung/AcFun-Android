//package com.hubertyoung.common.api;
//
//import com.hubertyoung.base.annotation.Environment;
//import com.hubertyoung.base.annotation.Module;
//
///**
// * <br>
// * function:
// * <p>
// *
// * @author:HubertYoung
// * @date:2018/9/21 17:27
// * @since:V$VERSION
// * @desc:com.hubertyoung.component_acfunmine.api
// */
//public class EnvironmentConfig {
//	/**
//	 * 整个 App 的环境
//	 */
//	@Module
//	private class App {
//		@Environment(url = ApiConstants.APP_NEWAPI_HOST, isRelease = true, alias = "正式")
//		private String online;
//
//		@Environment(url =  ApiConstants.NETEAST_TEST_HOST, alias = "测试")
//		private String test;
//	}
//
//	/**
//	 * 特殊模块 我的 的环境
//	 */
//	@Module(alias = "我的")
//	private class Mine {
//		@Environment(url = ApiConstants.APP_NEWAPI_HOST, isRelease = true, alias = "正式")
//		private String online;
//
//		@Environment(url = ApiConstants.NETEAST_TEST_HOST, alias = "测试")
//		private String test;
//	}
//	/**
//	 * 特殊模块 账号 的环境
//	 */
//	@Module(alias = "账号")
//	private class Account {
//		@Environment(url = ApiConstants.APP_ACCOUNT_HOST, isRelease = true, alias = "正式")
//		private String online;
//
//		@Environment(url = ApiConstants.NETEAST_TEST_HOST, alias = "测试")
//		private String test;
//	}
//}
