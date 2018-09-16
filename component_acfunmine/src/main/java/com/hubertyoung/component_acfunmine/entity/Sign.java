package com.hubertyoung.component_acfunmine.entity;

import com.google.gson.annotations.SerializedName;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/16 17:18
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.entity
 */
public class Sign {
	@SerializedName( "check_real")
	public int check_real;
	@SerializedName( "expiration")
	public Long expires;
	@SerializedName( "info")
	public SignInfo info;
	@SerializedName( "check_password")
	public int isInitPassword;
	@SerializedName( "oauth")
	public int oauth;
	@SerializedName( "s2s-code")
	public String s2sCode;
	@SerializedName( "token")
	public String token;
}
