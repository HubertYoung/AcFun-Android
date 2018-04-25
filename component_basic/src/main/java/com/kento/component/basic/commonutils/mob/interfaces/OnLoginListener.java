package com.kento.component.basic.commonutils.mob.interfaces;

import com.kento.component.basic.commonutils.mob.UserInfo;

import java.util.Map;


/**
 * 第三方登录操作过程中会回调这个接口中的方法，不同方法衔接第
 * 三方登录与用户应用登录/注册的逻辑，故使用第三方登录时一定要实
 * 现本接口的不同方法，否则第三方登录是没有意义的。
 */
public interface OnLoginListener {

	/**
	 * 登录完成调用此接口，返回登录者在第三方社交平台上的用户数据。实现此方法时要根据
	 * res参数中的用户资料，结合开发者应用的用户系统数据，判断此登录者是否需要先注册，
	 * 如果需要注册，则返回true
	 */
	public boolean onLogin( Map< String, String > res );

	/**
	 * 完成注册信息填写后此方法将被调用，开发者可于此处根据info参数判断注册者填写的信
	 * 息是否合法。若合法，则调用自身应用的注册服务进入注册流程并返回true以关闭注册页
	 * 面，否则注册页将不会关闭，用户需要修改注册信息
	 */
	public boolean onRegister( UserInfo info );

}
