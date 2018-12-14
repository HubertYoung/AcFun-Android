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

import android.support.v4.util.ArrayMap;

import com.hubertyoung.common.utils.log.CommonLog;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc:url配置信息
 * @author:HubertYoung
 * @date 2018/12/14 13:50
 * @since:
 * @see ApiConstants
 */
public class ApiConstants {
	public static final String BASE_PATH = "/";
	public static final String APP_NEWAPI_HOST = "http://apipc.app.acfun.cn";
	public static final String APP_UPDATE_HOST = "http://h5.app.acfun.cn";
	public static final String APP_WEBAPI_HOST = "http://webapi.app.acfun.cn";
	public static final String APP_CDN_HOST = "http://cdn.aixifan.com";
	public static final String APP_SEARCH_HOST = "http://search.app.acfun.cn";
	public static final String APP_ROOT_HOST = "http://mobile.app.acfun.cn";
	public static final String APP_REPORT_HOST = "http://appreport.app.acfun.cn";
	public static final String APP_DANMAKU_HOST = "ws://danmu.app.acfun.cn:443";
	public static final String APP_ICAO_HOST = "http://fanju.app.acfun.cn";
	public static final String APP_COMMENT_HOST = "http://danmu.aixifan.com";
	public static final String APP_SHARE_HOST = "http://www.acfun.cn";
	public static final String APP_API_HOST = "http://api.app.acfun.cn";
	public static final String APP_LIVE_HOST = "http://live.acfun.cn";

	public static final String APP_SLL_HOST = "https://ssl.app.acfun.cn";
	public static final String APP_ACCOUNT_HOST = "https://account.app.acfun.cn";

	public static final String NETEAST_TEST_HOST = "http://testpc.app.acfun.cn";

	private static ArrayMap< String, String > appAllHost;

	static {
		appAllHost = new ArrayMap<>( HostType.class.getDeclaredFields().length );
		appAllHost.put( HostType.APP_NEWAPI_HOST, APP_NEWAPI_HOST );
		appAllHost.put( HostType.APP_UPDATE_HOST, APP_UPDATE_HOST );
		appAllHost.put( HostType.APP_WEBAPI_HOST, APP_WEBAPI_HOST );
		appAllHost.put( HostType.APP_CDN_HOST, APP_CDN_HOST );
		appAllHost.put( HostType.APP_SEARCH_HOST, APP_SEARCH_HOST );
		appAllHost.put( HostType.APP_ROOT_HOST, APP_ROOT_HOST );
		appAllHost.put( HostType.APP_REPORT_HOST, APP_REPORT_HOST );
		appAllHost.put( HostType.APP_DANMAKU_HOST, APP_DANMAKU_HOST );
		appAllHost.put( HostType.APP_ICAO_HOST, APP_ICAO_HOST );
		appAllHost.put( HostType.APP_COMMENT_HOST, APP_COMMENT_HOST );
		appAllHost.put( HostType.APP_SHARE_HOST, APP_SHARE_HOST );
		appAllHost.put( HostType.APP_API_HOST, APP_API_HOST );
		appAllHost.put( HostType.APP_LIVE_HOST, APP_LIVE_HOST );
		appAllHost.put( HostType.APP_SLL_HOST, APP_SLL_HOST );
		appAllHost.put( HostType.APP_ACCOUNT_HOST, APP_ACCOUNT_HOST );
	}

	public static String replaceUrl( String key, String url ) {
		String curValue;
		if ( ( ( curValue = appAllHost.get( key ) ) != null ) || appAllHost.containsKey( key ) ) {
			curValue = appAllHost.put( key, url );
		}
		return curValue;
	}

	public static void replaceAllUrl( HashMap< String, String > urlMap ) {
		StringBuffer stringBuffer = new StringBuffer();
		for (Map.Entry< String, String > entry : urlMap.entrySet()) {
			String replaceUrl = replaceUrl( entry.getKey(), entry.getValue() );
			stringBuffer.append( replaceUrl );
			stringBuffer.append( "\r\n" );
		}
		stringBuffer.insert( 0,"替换url状态：" );
		CommonLog.logi( stringBuffer.toString() );
	}

	/**
	 * 获取对应的host
	 *
	 * @param hostType host类型
	 * @return host
	 */
	public static String getHost( String hostType ) {
		String host = APP_NEWAPI_HOST;
		if ( appAllHost.containsKey( hostType ) ) {
			host = appAllHost.get( hostType );
		}
		return host + BASE_PATH;
	}
}
