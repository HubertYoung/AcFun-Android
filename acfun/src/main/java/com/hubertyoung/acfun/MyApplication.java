package com.hubertyoung.acfun;

import android.content.Context;

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
 * @desc:com.hubertyoung.welcome.commonlibrary
 */
public class MyApplication extends CommonApplication {
	@Override
	public void onCreate() {
		super.onCreate();
		// 初始化CC
		CC.enableVerboseLog( BuildConfig.DEBUG );
		CC.enableDebug( BuildConfig.DEBUG );
		CC.enableRemoteCC( BuildConfig.DEBUG );

	}

	@Override
	protected void attachBaseContext( Context base ) {
		super.attachBaseContext( base );

//		boolean isUi = TextUtils.equals( getPackageName(), OSUtil.getCurrentProcessName() );
//		Config.ConfigBuilder apmBuilder = new Config.ConfigBuilder().setAppContext( this )
//				.setRuleRequest( new RuleSyncRequest() )
//				.setUpload( new CollectDataSyncUpload() )
//				.setAppName( AppUtils.getAppName() )
//				.setAppVersion( AppUtils.getAppVersionName() )
//				.setApmid( "apm_demo" );//该ID是在APM的后台进行申请的
//
//		//单进程应用可忽略builder.setDisabled相关配置。
//		if ( !isUi ) { //除了“主进程”，其他进程不需要进行数据上报、清理等逻辑。“主进程”通常为常驻进行，如果无常驻进程，即为UI进程。
//			apmBuilder.setDisabled( ApmTask.FLAG_DATA_CLEAN )
//					.setDisabled( ApmTask.FLAG_CLOUD_UPDATE )
//					.setDisabled( ApmTask.FLAG_DATA_UPLOAD )
//					.setDisabled( ApmTask.FLAG_COLLECT_ANR )
//					.setDisabled( ApmTask.FLAG_COLLECT_FILE_INFO );
//		}
//		//builder.setEnabled(ApmTask.FLAG_COLLECT_ACTIVITY_AOP); //activity采用aop方案时打开，默认关闭即可。
//		apmBuilder.setEnabled( ApmTask.FLAG_LOCAL_DEBUG ); //是否读取本地配置，默认关闭即可。
//		Client.attach( apmBuilder.build() );
//		Client.isDebugOpen( false );//设置成true的时候将会打开悬浮窗
//		Client.startWork();

	}
}
