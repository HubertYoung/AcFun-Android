package com.hubertyoung.common.api

import android.support.v4.util.ArrayMap
import com.hubertyoung.common.utils.log.CommonLog
import java.util.*

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/4 13:38
 * @since:
 * @see com.hubertyoung.common.api.ApiConstants
 */
object ApiConstants {

	const val BASE_PATH = "/"
	const val APP_NEWAPI_HOST = "http://apipc.app.acfun.cn"

	const val APP_UPDATE_HOST = "http://h5.app.acfun.cn"
	const val APP_WEBAPI_HOST = "http://webapi.app.acfun.cn"
	const val APP_CDN_HOST = "http://cdn.aixifan.com"
	const val APP_SEARCH_HOST = "http://search.app.acfun.cn"
	const val APP_ROOT_HOST = "http://mobile.app.acfun.cn"
	const val APP_REPORT_HOST = "http://appreport.app.acfun.cn"
	const val APP_DANMAKU_HOST = "ws://danmu.app.acfun.cn:443"
	const val APP_ICAO_HOST = "http://fanju.app.acfun.cn"
	const val APP_COMMENT_HOST = "http://danmu.aixifan.com"
	const val APP_SHARE_HOST = "http://www.acfun.cn"
	const val APP_API_HOST = "http://api.app.acfun.cn"
	const val APP_LIVE_HOST = "http://live.acfun.cn"
	const val APP_SLL_HOST = "https://ssl.app.acfun.cn"
	const val APP_ACCOUNT_HOST = "https://account.app.acfun.cn"
	const val NETEAST_TEST_HOST = "http://testpc.app.acfun.cn"

	private var appAllHost: ArrayMap<String, String> = ArrayMap(HostType::class.java.declaredFields.size)

	init {
		appAllHost[HostType.APP_NEWAPI_HOST] = APP_NEWAPI_HOST
		appAllHost[HostType.APP_UPDATE_HOST] = APP_UPDATE_HOST
		appAllHost[HostType.APP_WEBAPI_HOST] = APP_WEBAPI_HOST
		appAllHost[HostType.APP_CDN_HOST] = APP_CDN_HOST
		appAllHost[HostType.APP_SEARCH_HOST] = APP_SEARCH_HOST
		appAllHost[HostType.APP_ROOT_HOST] = APP_ROOT_HOST
		appAllHost[HostType.APP_REPORT_HOST] = APP_REPORT_HOST
		appAllHost[HostType.APP_DANMAKU_HOST] = APP_DANMAKU_HOST
		appAllHost[HostType.APP_ICAO_HOST] = APP_ICAO_HOST
		appAllHost[HostType.APP_COMMENT_HOST] = APP_COMMENT_HOST
		appAllHost[HostType.APP_SHARE_HOST] = APP_SHARE_HOST
		appAllHost[HostType.APP_API_HOST] = APP_API_HOST
		appAllHost[HostType.APP_LIVE_HOST] = APP_LIVE_HOST
		appAllHost[HostType.APP_SLL_HOST] = APP_SLL_HOST
		appAllHost[HostType.APP_ACCOUNT_HOST] = APP_ACCOUNT_HOST
	}

	private fun replaceUrl(key: String, url: String): String? {
		var curValue: String?
		curValue = appAllHost[key]
		if (curValue != null || appAllHost.containsKey(key)) {
			curValue = appAllHost.put(key, url)
		}
		return curValue
	}

	@JvmStatic
	fun replaceAllUrl(urlMap: HashMap<String, String>) {
		val stringBuffer = StringBuffer()
		for ((key, value) in urlMap) {
			val replaceUrl = replaceUrl(key, value)
			stringBuffer.append(replaceUrl)
			stringBuffer.append("\r\n")
		}
		stringBuffer.insert(0, "替换url状态：")
		CommonLog.logi(stringBuffer.toString())
	}

	/**
	 * 获取对应的host
	 *
	 * @param hostType host类型
	 * @return host
	 */
	@JvmStatic
	fun getHost(hostType: String): String {
		var host: String? = APP_NEWAPI_HOST
		if (appAllHost.containsKey(hostType)) {
			host = appAllHost[hostType]
		}
		return host + BASE_PATH
	}
}