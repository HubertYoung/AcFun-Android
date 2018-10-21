package com.hubertyoung.common.entity;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/16 17:17
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.entity
 */
public class Token implements Serializable{
	@JSONField(name = "userImg" )
	private String avater;
	@JSONField(name = "expires" )
	private Date expire;
	@JSONField(name = "mobileCheck" )
	private int mobileCheck;
	@JSONField(name = "access_token" )
	private String token;
	@JSONField(name = "userId" )
	private int uid;
	@JSONField(name = "userGroupLevel" )
	private int userGroupLevel;
	@JSONField(name = "username" )
	private String userName;

	public int getMobileCheck() {
		return this.mobileCheck;
	}

	public void setMobileCheck( int i ) {
		this.mobileCheck = i;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid( int i ) {
		this.uid = i;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName( String str ) {
		this.userName = str;
	}

	public String getAvater() {
		return this.avater;
	}

	public void setAvater( String str ) {
		this.avater = str;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken( String str ) {
		this.token = str;
	}

	public Date getExpire() {
		return this.expire;
	}

	public void setExpire( Date date ) {
		this.expire = date;
	}

	public int getUserGroupLevel() {
		return this.userGroupLevel;
	}

	public void setUserGroupLevel( int i ) {
		this.userGroupLevel = i;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( "Token{uid=" );
		stringBuilder.append( this.uid );
		stringBuilder.append( ", userName='" );
		stringBuilder.append( this.userName );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", avater='" );
		stringBuilder.append( this.avater );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", token='" );
		stringBuilder.append( this.token );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", expire=" );
		stringBuilder.append( this.expire );
		stringBuilder.append( ", userGroupLevel=" );
		stringBuilder.append( this.userGroupLevel );
		stringBuilder.append( ", mobileCheck=" );
		stringBuilder.append( this.mobileCheck );
		stringBuilder.append( '}' );
		return stringBuilder.toString();
	}
}
