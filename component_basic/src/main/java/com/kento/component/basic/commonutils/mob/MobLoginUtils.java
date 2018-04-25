//package com.kento.component.basic.commonutils.mob;
//
//
//import android.support.v4.app.FragmentActivity;
//
//import com.kento.component.basic.commonutils.mob.interfaces.OnLoginClickListener;
//import com.kento.component.basic.commonutils.mob.interfaces.OnLoginListener;
//
//import java.util.Map;
//
//
///**
// * @author:Yang
// * @date:22/07/17 16:33
// * @since:v1.0
// * @desc:ddframework.gent.common.commonutils.mob
// * @param: 登录工具类
// */
//public class MobLoginUtils {
//
//	private OnLoginClickListener onLoginClickListener;
//	private static volatile MobLoginUtils instance;
//
//	public MobLoginUtils() {
////        MobSDK.init( BaseApplication.getAppContext());
//	}
//
//	public static MobLoginUtils getInstance() {
//		if ( null == instance ) {
//			instance = new MobLoginUtils();
//		}
//		return instance;
//	}
//
//	public void qq( FragmentActivity activity ) {
//		login( activity, AuthorizeVia.QQ );
//	}
//
//	public void weChar( FragmentActivity activity ) {
//		login( activity, AuthorizeVia.Weixin );
//	}
//
//	public void weibo( FragmentActivity activity ) {
//		login( activity, AuthorizeVia.Weibo );
//	}
//
//	private void login( FragmentActivity activity, String platformName ) {
//		LoginApi api = new LoginApi();
//		//设置登陆的平台后执行登陆的方法
//		api.setPlatform( platformName );
//		api.setOnLoginListener( new OnLoginListener() {
//			public boolean onLogin( Map< String, String > res ) {
//				// 在这个方法填写尝试的代码，返回true表示还不能登录，需要注册
//				// 此处全部给回需要注册
//				if ( onLoginClickListener != null ) {
//					onLoginClickListener.onUserInfo( platformName,res );
//				}
//				return true;
//			}
//
//			public boolean onRegister( UserInfo info ) {
//				// 填写处理注册信息的代码，返回true表示数据合法，注册页面可以关闭
//				return true;
//			}
//		} );
//		api.login( activity );
//	}
//
//	public void setOnLoginClickListener( OnLoginClickListener onLoginClickListener ) {
//		this.onLoginClickListener = onLoginClickListener;
//	}
//}
//
