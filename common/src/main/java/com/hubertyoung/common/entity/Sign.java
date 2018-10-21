package com.hubertyoung.common.entity;


import com.alibaba.fastjson.annotation.JSONField;

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
	@JSONField(name = "check_real")
	public int check_real;
	@JSONField(name = "expiration")
	public Long expires;
	@JSONField(name = "info")
	public SignInfo info;
	@JSONField(name = "check_password")
	public int isInitPassword;
	@JSONField(name = "oauth")
	public int oauth;
	@JSONField(name = "s2s-code")
	public String s2sCode;
	@JSONField(name = "token")
	public String token;
}
