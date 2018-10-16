package com.hubertyoung.common;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.hubertyoung.common.api.ApiConstants;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.image.fresco.ImagePipelineConfigFactory;
import com.hubertyoung.common.net.config.NetWorkConfiguration;
import com.hubertyoung.common.net.http.HttpUtils;
import com.hubertyoung.common.utils.AppUtils;
import com.hubertyoung.common.utils.CommonLog;
import com.hubertyoung.common.utils.DisplayUtil;
import com.hubertyoung.common.utils.ScreenHelper;
import com.hubertyoung.common.utils.TimeUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.tencent.mmkv.MMKV;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.config.ConfigurationBuilder;
import org.acra.sender.HttpSender;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import okhttp3.Headers;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:Yang
 * @date:2018/4/19 5:12 PM
 * @since:V1.0
 * @desc:com.hubertyoung.component.basic
 */
public class CommonApplication extends Application {

	private static CommonApplication mBaseApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		mBaseApplication = this;
		CommonLog.logInit( BuildConfig.DEBUG );

		String rootDir = "mmkv root: " + MMKV.initialize(this);
		CommonLog.logd( rootDir );

		SmartRefreshLayout.setDefaultRefreshHeaderCreator( new DefaultRefreshHeaderCreator() {
			@NonNull
			@Override
			public RefreshHeader createRefreshHeader( @NonNull Context context, @NonNull RefreshLayout layout ) {
				layout.setPrimaryColorsId( R.color.colorPrimary, android.R.color.white );//全局设置主题颜色
				layout.setHeaderHeight( 150 );
				return new MaterialHeader( context );
			}
		} );
		ScreenHelper.initCustomDensity( this );
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
		initFresco();
		//网络框架
		initOkHttpUtils();
		initStetho();

	}

	private void initFresco() {
		Fresco.initialize( this, ImagePipelineConfigFactory.getOkHttpImagePipelineConfig( this ) );
	}

	private void initStetho() {
		if ( AppUtils.isDebuggable() ) {
			Stetho.initialize( Stetho.newInitializerBuilder( this )
					.enableDumpapp( Stetho.defaultDumperPluginsProvider( this ) )
					.enableWebKitInspector( Stetho.defaultInspectorModulesProvider( this ) )
					.build() );

		}
	}

	private void initOkHttpUtils() {
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
		NetWorkConfiguration configuration = new NetWorkConfiguration( this ).isCache( true ).isDiskCache( true ).isMemoryCache( true );
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append( "acvideo core/" )//
				.append( AppUtils.getAppVersionName() )//
				.append( "(" )//
				.append( Build.BRAND )//
				.append( ";" )//
				.append( Build.MODEL )//
				.append( ";" )//
				.append( Build.VERSION.RELEASE )//
				.append( ")" );
		Headers.Builder builder = new Headers.Builder()//
				.add( "deviceType", "1" )//
				.add( "appVersion", AppUtils.getAppVersionName() )//
				.add( "productId", "2000" ) //
				.add( "User-agent", stringBuffer.toString() )//
				.add( "resolution", DisplayUtil.getScreenWidth( this ) + "x" + DisplayUtil.getScreenHeight( this ) )//
				.add( "uuid", UUID.randomUUID().toString() )//
				.add( "market", "portal" )//
				.add( "requestTime", TimeUtil.getCurrentDate( TimeUtil.dateFormatYMDHMSSSS ) )//
				.add( "uid", "0" )//
				.add( "udid", AppUtils.getUUID() );
		configuration.setHeaders( builder.build() );
		HttpUtils.setConFiguration( configuration );
	}

	@Override
	protected void attachBaseContext( Context base ) {
		super.attachBaseContext( base );
		MultiDex.install( this );
//		If you are using legacy multidex, ensure that ACRA.init(...) is called after Multidex.install().
		ConfigurationBuilder builder = new ConfigurationBuilder( this );
		builder.setBuildConfigClass( BuildConfig.class )//
				.setFormUri( ApiConstants.getHost( HostType.MY_RESULT ) )//
				.setReportType( HttpSender.Type.FORM )//
				.setReportingInteractionMode( ReportingInteractionMode.TOAST )//
				.setResToastText( R.string.common_res_app_crash_str );
		ACRA.init( this, builder );
	}

	public static Context getAppContext() {
		return mBaseApplication;
	}
}
