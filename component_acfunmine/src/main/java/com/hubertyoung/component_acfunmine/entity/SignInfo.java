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
public class SignInfo {
	@SerializedName( "avatar")
	public String avatar;
	@SerializedName( "group-level")
	public int groupLevel;
	@SerializedName( "third-channel")
	public int isThirdLogin;
	@SerializedName( "mobile")
	public String mobile;
	@SerializedName( "mobile-check")
	public int mobileCheck;
	@SerializedName( "userid")
	public int userid;
	@SerializedName( "username")
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
