package com.kento.component.basic.commonutils;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.kento.common.CommonApplication;
import com.kento.component.basic.commonconstant.AppConfig;

import java.util.Locale;


/**
 * 作者：JIUU on 2017/6/27 17:00
 * QQ号：1344393464
 * 作用：语言切换工具类
 */
public class LanguageUtils {
	//    中文（中国）：values-zh-rCN
//    中文（台湾）：values-zh-rTW
//    中文（香港）：values-zh-rHK
//    英语（美国）：values-en-rUS
	private LanguageUtils() {
	}

	private static class SingletonHolder {
		private static LanguageUtils instance = new LanguageUtils();
	}

	public static LanguageUtils getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * 设置当前语言
	 *
	 * @param language
	 */
	public void setLanguage( Locale language, Class< ? > cla ) {
		//获取当前资源对象
		Resources resources = CommonApplication.getAppContext()
											 .getResources();
		//获取设置对象
		Configuration configuration = resources.getConfiguration();
		//获取屏幕参数
		DisplayMetrics displayMetrics = resources.getDisplayMetrics();
		//设置本地语言
		configuration.locale = language;

		resources.updateConfiguration( configuration, displayMetrics );

		SPUtils.setSharedStringData( AppConfig.LANG, configuration.locale.toString() );

		//发送结束所有activity的广播
		Intent intent = new Intent( AppConfig.ACTION_FINISH_ALL_ACTIVITY );
		CommonApplication.getAppContext()
					   .sendBroadcast( intent );
		Intent activityIntent = new Intent( CommonApplication.getAppContext(), cla );
		activityIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		CommonApplication.getAppContext()
					   .startActivity( activityIntent );
	}
}
