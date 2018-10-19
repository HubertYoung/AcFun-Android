package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

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
	@JSONField(name = "avatar")
	public String avatar;
	@JSONField(name = "id")
	public int id;
	@JSONField(name = "name")
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
