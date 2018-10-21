/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.hubertyoung.common.api;

public class ApiConstants {
	public static final String BASE_PATH = "/";
	public static final String NETEAST_HOST = "http://apipc.app.acfun.cn";
	public static final String NETEAST_TEST_HOST = "http://testpc.app.acfun.cn";
	public static final String APP_HOST_SLL = "https://ssl.app.acfun.cn";
	public static final String APP_HOST_ACCOUNT = "https://account.app.acfun.cn";
//	public static final String NETEAST_HOST = "http://192.168.2.60:9006";

	/**
	 * 获取对应的host
	 *
	 * @param hostType host类型
	 * @return host
	 */
	public static String getHost( int hostType ) {
		String host = "";
		switch ( hostType ) {
			case HostType.MY_RESULT:
				host = NETEAST_HOST;
				break;
			case HostType.MY_TEST_DOWN:
				break;
			case HostType.APP_HOST_SLL:
				host = APP_HOST_SLL;
				break;
			case HostType.APP_HOST_ACCOUNT:
				host = APP_HOST_ACCOUNT;
				break;
			case HostType.NEWS_DETAIL_HTML_PHOTO:
				host = "http://kaku.com/";
				break;
			case HostType.TEST:
				break;
			default:
				host = "";
				break;
		}
		return host + BASE_PATH;
	}
}
