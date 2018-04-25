package com.kento.component.basic.net.processserver;

import android.content.Intent;
import android.text.TextUtils;

import com.kento.component.basic.BaseApplication;
import com.kento.component.basic.commonconstant.AppConfig;
import com.kento.component.basic.net.config.NetStatus;


/**
 * @author:Yang
 * @date:2017/12/6 14:53
 * @since:v1.0
 * @desc:ddframework.gent.common.net.processserver
 * @param:
 */
public class ProcessServerException {
	private String status;

	public ProcessServerException( String status ) {
		this.status = status;
	}

	public void send() {
		if ( TextUtils.equals( NetStatus.User_Not_Login.getIndex(), status ) || TextUtils.equals( NetStatus.Account_Login_Other_Device.getIndex(), status ) ) {
			//发送登录的广播
			Intent intent = new Intent( AppConfig.ACTION_LOGIN_ACTIVITY );
			BaseApplication.getAppContext()
						   .sendBroadcast( intent );
		}
	}
}
