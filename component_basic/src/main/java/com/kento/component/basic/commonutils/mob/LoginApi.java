//package com.kento.component.basic.commonutils.mob;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Handler;
//import android.os.Handler.Callback;
//import android.os.Looper;
//import android.os.Message;
//import android.support.v4.app.FragmentActivity;
//import android.widget.Toast;
//
//import com.kento.component.basic.BaseApplication;
//import com.kento.component.basic.commonutils.mob.interfaces.OnLoginListener;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class LoginApi implements Callback {
//	private static final int MSG_AUTH_CANCEL = 1;
//	private static final int MSG_AUTH_ERROR = 2;
//	private static final int MSG_AUTH_COMPLETE = 3;
//
//	private OnLoginListener loginListener;
//	private String platform;
//	private Context context;
//	private Handler handler;
//
//	public LoginApi() {
//		handler = new Handler( Looper.getMainLooper(), this );
//	}
//
//	public void setPlatform( String platform ) {
//		this.platform = platform;
//	}
//
//	public void setOnLoginListener( OnLoginListener login ) {
//		this.loginListener = login;
//	}
//
//	public void login( FragmentActivity activity ) {
//		this.context = BaseApplication.getAppContext();
//		if ( platform == null ) {
//			return;
//		}
//		//        if ( plat.isAuthValid() ) {
////            plat.removeAccount( true );
////            return;
////        }
//		AuthorizeSDK.authorize( activity, platform, new OnCallback< String >() {
//			@Override
//			public void onStarted( Activity activity ) {
//
//			}
//
//			@Override
//			public void onCompleted( Activity activity ) {
//
//			}
//
//			@Override
//			public void onSucceed( Activity activity, String result ) {
//				Map< String, String > map = new Hashon().fromJson( result );
//				Message msg = new Message();
//				msg.what = MSG_AUTH_COMPLETE;
//				msg.obj = map;
//				handler.sendMessage( msg );
//			}
//
//			@Override
//			public void onFailed( Activity activity, int code, String message ) {
//				if ( code == ResultCode.RESULT_FAILED ) {
//					Message msg = new Message();
//					msg.what = MSG_AUTH_ERROR;
//					msg.arg2 = code;
//					msg.obj = message;
//					handler.sendMessage( msg );
//				} else if ( code == ResultCode.RESULT_CANCELLED ) {
//					Message msg = new Message();
//					msg.what = MSG_AUTH_CANCEL;
//					msg.arg2 = code;
//					msg.obj = message;
//					handler.sendMessage( msg );
//				}
//			}
//		} );
//	}
//
//	/**
//	 * 处理操作结果
//	 */
//	public boolean handleMessage( Message msg ) {
//		switch ( msg.what ) {
//			case MSG_AUTH_CANCEL: {
//				// 取消
//				Toast.makeText( context, "canceled", Toast.LENGTH_SHORT )
//					 .show();
//			}
//			break;
//			case MSG_AUTH_ERROR: {
//				// 失败
////				Throwable t = ( Throwable ) msg.obj;
////				String text = "caught error: " + t.getMessage();
////				Toast.makeText( context, text, Toast.LENGTH_SHORT )
////					 .show();
////				t.printStackTrace();
//				Toast.makeText( context, "ERROR: " + msg, Toast.LENGTH_SHORT )
//					 .show();
//			}
//			break;
//			case MSG_AUTH_COMPLETE: {
//				// 成功
//				Map< String, String > map = ( HashMap< String, String > ) msg.obj;
////                PlatformDb platformDb = ( PlatformDb ) objs[ 0 ];
//
//				if ( loginListener != null ) {
//					loginListener.onLogin( map );
////					RegisterPage.setOnLoginListener(loginListener);
////					RegisterPage.setPlatform(plat);
////					Intent intent=new Intent(context, RegisterPage.class);
////					intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
////					context.startActivity(intent);
//				}
//			}
//			break;
//		}
//		return false;
//	}
//
//}
