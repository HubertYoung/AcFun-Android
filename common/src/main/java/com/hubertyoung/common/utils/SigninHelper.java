package com.hubertyoung.common.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.constant.AppSpConfig;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.entity.Token;
import com.hubertyoung.common.entity.User;

import java.util.Date;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/16 17:08
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.utils
 */
public class SigninHelper {
	//	private static final int a = -1;
//	private static final int b = 1;
//	private static final int c = 0;
	private static SigninHelper sSigninHelper;
	private SharedPreferences mSharedPreferences = CommonApplication.getAppContext().getSharedPreferences( AppSpConfig.SIGNINSP, 0 );

	private SigninHelper() {
	}

	public static synchronized SigninHelper getInstance() {
		SigninHelper signinHelper;
		synchronized ( SigninHelper.class ) {
			if ( sSigninHelper == null ) {
				sSigninHelper = new SigninHelper();
			}
			signinHelper = sSigninHelper;
		}
		return signinHelper;
	}

	public int getUserUid() {
		return this.mSharedPreferences.getInt( "uid", 0 );
	}

	public int getUserGroupLevel() {
		return this.mSharedPreferences.getInt( "userGroupLevel", -1 );
	}

	public void setUserGroupLevel( int i ) {
		this.mSharedPreferences.edit().putInt( "userGroupLevel", i ).apply();
	}

	public boolean isUserGroup() {
		return getUserGroupLevel() == Constants.USER_GROUP_LEVEL_FORMAL;
	}

	public String getUsername() {
		return this.mSharedPreferences.getString( "username", "" );
	}

	public String getAvatar() {
		return this.mSharedPreferences.getString( "avatar", "" );
	}

	public String getUserToken() {
		return this.mSharedPreferences.getString( "token", "" );
	}

	public Date getExpireDate() {
		return new Date( this.mSharedPreferences.getLong( "expire", 0 ) );
	}

	private long getExpire() {
		return this.mSharedPreferences.getLong( "expire", 0 );
	}

	public boolean isInitPassword() {
		return this.mSharedPreferences.getInt( "initPassword", 0 ) == 0;
	}

	public boolean isCheckReal() {
		return this.mSharedPreferences.getInt( "check_real", 0 ) != 0;
	}

	public void setInitPassword() {
		this.mSharedPreferences.edit().putInt( "initPassword", 0 ).apply();
	}

	public boolean isThirdChannel() {
		return this.mSharedPreferences.getInt( "thirdChannel", 0 ) != 0;
	}

	public String getMobile() {
		return this.mSharedPreferences.getString( "mobile", "" );
	}

	public boolean isOauth() {
		return this.mSharedPreferences.getInt( "oauth", -1 ) == 1;
	}

	public void setOauth1() {
		this.mSharedPreferences.edit().putInt( "oauth", 1 ).apply();
	}

	public void setOauth0() {
		this.mSharedPreferences.edit().putInt( "oauth", 0 ).apply();
	}

	public void setMobile( String str ) {
		this.mSharedPreferences.edit().putString( "mobile", str ).apply();
	}

	public void setMobileCheck( int i ) {
		this.mSharedPreferences.edit().putInt( "mobileCheck", i ).apply();
	}

	public void completeSetMobileCheck() {
		setMobileCheck( 1 );
	}

	public boolean isMobileCheck() {
		return 1 == this.mSharedPreferences.getInt( "mobileCheck", -1 );
	}

	public boolean isUnLogin() {
		long expire = getExpire();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( expire );
		if ( stringBuilder.toString().length() < 13 ) {
			expire *= 1000;
		}
		Date date = new Date( expire );
		int isEmpty = TextUtils.isEmpty( getUserToken() ) ? 0 : 1;
		boolean before = new Date().before( date );
		if ( isEmpty == 0 || !before ) {
			return false;
		}
		return true;
	}

	public void setUserToken( Token token ) {
		this.mSharedPreferences.edit()
				.putInt( "mobileCheck", token.getMobileCheck() )
				.putInt( "uid", token.getUid() )
				.putInt( "userGroupLevel", token.getUserGroupLevel() )
				.putString( "username", token.getUserName() )
				.putString( "avatar", token.getAvater() )
				.putString( "token", token.getToken() )
				.putLong( "expire", token.getExpire().getTime() )
				.apply();
	}

	public void setUserSign( Sign sign ) {
		this.mSharedPreferences.edit()
				.putInt( "uid", sign.info.userid )
				.putInt( "userGroupLevel", sign.info.groupLevel )
				.putString( "username", sign.info.username )
				.putString( "avatar", sign.info.avatar )
				.putString( "token", sign.token )
				.putLong( "expire", sign.expires.longValue() )
				.putInt( "initPassword", sign.isInitPassword )
				.putInt( "thirdChannel", sign.info.isThirdLogin )
				.putString( "s2sCode", sign.s2sCode )
				.putString( "mobile", sign.info.mobile )
				.putInt( "mobileCheck", sign.info.mobileCheck )
				.putInt( "oauth", sign.oauth )
				.putInt( "check_real", sign.check_real )
				.apply();
	}

	public void setUserInfo( User user ) {
		this.mSharedPreferences.edit().putString( "username", user.getName() ).putString( "avatar", user.getAvatar() ).apply();
	}

//	public void t() {
//		String valueOf = String.valueOf(getUserUid());
//		this.mSharedPreferences.edit().clear().apply();
//		Utils.isSuccess(valueOf, com.b());
//		AcFunApplication b = AcFunApplication.b();
//		String str = SharedPreferencesConst.h;
//		AcFunApplication.b();
//		b.getSharedPreferences(str, 0).edit().clear().apply();
//	}
}
