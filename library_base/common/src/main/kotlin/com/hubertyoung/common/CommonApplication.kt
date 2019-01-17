package com.hubertyoung.common

import android.app.Application
import android.content.Context
import android.os.Build
import android.support.multidex.MultiDex
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.stetho.Stetho
import com.hubertyoung.common.api.ApiConstants
import com.hubertyoung.common.api.HostType
import com.hubertyoung.common.image.fresco.ImagePipelineConfigFactory
import com.hubertyoung.common.net.config.NetWorkConfiguration
import com.hubertyoung.common.net.http.HttpConnection
import com.hubertyoung.common.utils.display.DisplayUtil
import com.hubertyoung.common.utils.display.ScreenHelper
import com.hubertyoung.common.utils.log.CommonLog
import com.hubertyoung.common.utils.os.AppUtils
import com.hubertyoung.common.utils.os.TimeUtil
import com.hubertyoung.qqplatforms.platforms.qq.QQPlatFormConfig
import com.hubertyoung.wechatplatforms.platforms.weixin.WechatPlatFormConfig
import com.hubertyoung.weiboplatforms.platforms.weibo.WeiboPlatFormConfig
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.tencent.mmkv.MMKV
import com.wlqq.phantom.library.PhantomCore
import com.wlqq.phantom.library.log.ILogReporter
import okhttp3.Headers
import org.acra.ACRA
import org.acra.ReportingInteractionMode
import org.acra.config.ConfigurationBuilder
import org.acra.sender.HttpSender
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/4 09:39
 * @since:
 * @see com.hubertyoung.common.CommonApplication
 */
open class CommonApplication : Application() {

	companion object {
		private lateinit var mBaseApplication: CommonApplication
		@JvmStatic
		fun getAppContext(): CommonApplication {
			return mBaseApplication
		}

		init {
			AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
		}
	}

	override fun onCreate() {
		super.onCreate()
		mBaseApplication = this
		CommonLog.logInit(BuildConfig.DEBUG)

		val rootDir = "mmkv root: " + MMKV.initialize(this)
		CommonLog.logd(rootDir)

		SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
			layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
			layout.setHeaderHeight(150f)
			MaterialHeader(context).setColorSchemeResources(R.color.colorPrimary)
		}
		SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ -> ClassicsFooter(context) }
		ScreenHelper.initCustomDensity(this, 360f)
		// 框架换肤日志打印
//		Slog.DEBUG = BuildConfig.DEBUG;
//		SkinCompatManager.withoutActivity( this )
////				.addStrategy(new CustomSDCardLoader())          // 自定义加载策略，指定SDCard路径
////				.addStrategy(new ZipSDCardLoader())             // 自定义加载策略，获取zip包中的资源
////				.addInflater( new SkinAppCompatViewInflater() )   // 基础控件换肤
//				.addInflater( new SkinMaterialViewInflater() )    // material design
//				.addInflater( new SkinConstraintViewInflater() )  // ConstraintLayout
//				.addInflater( new SkinCardViewInflater() )        // CardView v7
////				.addInflater(new SkinCircleImageViewInflater()) // hdodenhof/CircleImageView
////				.addInflater(new SkinFlycoTabLayoutInflater())  // H07000223/FlycoTabLayout
////				.setSkinStatusBarColorEnable( true )              // 关闭状态栏换肤
////                .setSkinWindowBackgroundEnable(false)           // 关闭windowBackground换肤
//				.setSkinAllActivityEnable( true )                // true: 默认所有的Activity都换肤; false: 只有实现SkinCompatSupportable接口的Activity换肤
//				.loadSkin();
		initFresco()
		//网络框架
		initOkHttpUtils()
		initStetho()

		QQPlatFormConfig.registerShare("1106891112", "QiIFegOZGaRmeC4S")
		WechatPlatFormConfig.registerShare("wxf143a66eb7528d12", "e408746be2a052ae1f294aa91595227c")
		WeiboPlatFormConfig.registerShare("3136498027", "", "http://sns.whalecloud.com/sina2/callback")
	}

	private fun initPhantom() {
		PhantomCore.getInstance()//
				.init(this, PhantomCore.Config()//
						.setDebug(BuildConfig.DEBUG)//
						.setLogLevel(if (BuildConfig.DEBUG) Log.VERBOSE else Log.WARN)//
						.addPhantomService(HostInfoService())//
						.setLogReporter(object : ILogReporter {
							override fun reportException(throwable: Throwable?, message: HashMap<String, Any>?) {
								ACRA.getErrorReporter().handleException(throwable)
							}

							override fun reportEvent(eventId: String?, label: String?, params: HashMap<String, Any>?) {
							}

							override fun reportLog(tag: String?, message: String?) {

							}
						}))//
	}

	private fun initFresco() {
		Fresco.initialize(this, ImagePipelineConfigFactory.getOkHttpImagePipelineConfig(this))
	}

	private fun initStetho() {
		if (AppUtils.isDebuggable()) {
			Stetho.initialize(Stetho.newInitializerBuilder(this).enableDumpapp(Stetho.defaultDumperPluginsProvider(this)).enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build())

		}
	}

	private fun initOkHttpUtils() {
		//		GET /v3/regions?channelId=0 HTTP/1.1
		//		deviceType	1
		//		appVersion	5.4.0
		//		productId	2000
		//		User-agent	acvideo core/5.4.0(Xiaomi;MI 5;8.0.0)
		//		resolution	1080x1920
		//		uuid	d30b763b-963e-473d-af3d-3810853e8ba5
		//		market	portal
		//		requestTime	2018-09-03 19:10:30.645
		//		uid	0
		//		udid	0e282674-678c-3274-bc4f-59c4e1870526
		//		Host	apipc.app.acfun.cn
		//		Connection	Keep-Alive
		//		Accept-Encoding	gzip
		val configuration = NetWorkConfiguration(this).connectTimeOut(10*1000).isCache(true).isDiskCache(true).isMemoryCache(true)
		val stringBuffer = StringBuffer()
		stringBuffer.append("acvideo core/")//
				.append(AppUtils.getAppVersionName())//
				.append("(")//
				.append(Build.BRAND)//
				.append(";")//
				.append(Build.MODEL)//
				.append(";")//
				.append(Build.VERSION.RELEASE)//
				.append(")")
		val builder = Headers.Builder()//
				.add("deviceType", "1")//
				.add("appVersion", AppUtils.getAppVersionName())//
				.add("productId", "2000") //
				.add("User-agent", stringBuffer.toString())//
				.add("resolution", DisplayUtil.getScreenWidth(this).toString() + "x" + DisplayUtil.getScreenHeight(this))//
				.add("uuid", UUID.randomUUID().toString())//
				.add("market", "portal")//TODO 渠道
				.add("requestTime", TimeUtil.getCurrentDate(TimeUtil.dateFormatYMDHMSSSS)!!)//
				.add("uid", "0")//
				.add("acPlatform", "ANDROID_PHONE").add("udid", AppUtils.getUUID())
		configuration.headers = builder.build()
		configuration.connectionPool(5, 5, TimeUnit.MINUTES)
		HttpConnection.setConFiguration(configuration)
	}

	override fun attachBaseContext(base: Context) {
		super.attachBaseContext(base)
		MultiDex.install(this)
		//		If you are using legacy multidex, ensure that ACRA.init(...) is called after Multidex.install().
		val builder = ConfigurationBuilder(this)
		builder.setBuildConfigClass(BuildConfig::class.java)//
				.setFormUri(ApiConstants.getHost(HostType.APP_NEWAPI_HOST))//
				.setReportType(HttpSender.Type.FORM)//
				.setReportingInteractionMode(ReportingInteractionMode.TOAST)//
				.setResToastText(R.string.common_res_app_crash_str)
		ACRA.init(this, builder)

		initPhantom()

		//		boolean isUi = TextUtils.equals( getPackageName(), ProcessUtils.getCurrentProcessName() );
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
		//		Client.isDebugOpen( AppUtils.isDebuggable() );//设置成true的时候将会打开悬浮窗
		//		Client.startWork();

	}

}