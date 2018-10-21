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
public class SignInfo {
	@JSONField(name = "avatar")
	public String avatar;
	@JSONField(name = "group-level")
	public int groupLevel;
	@JSONField(name = "third-channel")
	public int isThirdLogin;
	@JSONField(name = "mobile")
	public String mobile;
	@JSONField(name = "mobile-check")
	public int mobileCheck;
	@JSONField(name = "userid")
	public int userid;
	@JSONField(name = "username")
	public String username;

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SignInfo{avatar='");
		stringBuilder.append(this.avatar);
		stringBuilder.append('\'');
		stringBuilder.append(", username='");
		stringBuilder.append(this.username);
		stringBuilder.append('\'');
		stringBuilder.append(", userid=");
		stringBuilder.append(this.userid);
		stringBuilder.append(", groupLevel=");
		stringBuilder.append(this.groupLevel);
		stringBuilder.append(", isThirdLogin=");
		stringBuilder.append(this.isThirdLogin);
		stringBuilder.append(", mobile='");
		stringBuilder.append(this.mobile);
		stringBuilder.append('\'');
		stringBuilder.append(", mobileCheck=");
		stringBuilder.append(this.mobileCheck);
		stringBuilder.append('}');
		return stringBuilder.toString();
	}
}
