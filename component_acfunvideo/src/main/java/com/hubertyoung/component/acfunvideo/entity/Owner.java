package com.hubertyoung.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:28
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class Owner {
	@SerializedName( "avatar")
	public String avatar;
	@SerializedName( "id")
	public int id;
	@SerializedName( "name")
	public String name;

	public String getName() {
		return this.name;
	}

	public void setName(String str) {
		this.name = str;
	}

	public User convertToUser() {
		User user = new User();
		user.setAvatar(this.avatar);
		user.setUid(this.id);
		user.setName(this.name);
		return user;
	}
}
