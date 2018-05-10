package com.hubertyoung.common.net.processserver;

/**
 * @author:Yang
 * @date:2017/12/6 14:53
 * @since:v1.0
 * @desc:com.kento.common.net.processserver
 * @param:
 */
public class ProcessServerException {
	private int status;

	public ProcessServerException( int status ) {
		this.status = status;
	}

	public void send() {
//		if ( TextUtils.equals( NetStatus.User_Not_Login.getIndex(), status ) || TextUtils.equals( NetStatus.Account_Login_Other_Device.getIndex(), status ) ) {
//			//TODO 发送登录的广播
//			Intent intent = new Intent( AppConfig.ACTION_LOGIN_ACTIVITY );
//			CommonApplication.getAppContext()
//						   .sendBroadcast( intent );
//		}
	}
}
