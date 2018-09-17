package com.hubertyoung.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:27
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class User {
	@SerializedName( "userImg")
	private String avatar;
	@SerializedName( "banana")
	private int bananaCount;
	@SerializedName( "contributes")
	private String contributes;
	@SerializedName( "followed")
	private String followed;
	@SerializedName( "following")
	private String following;
	@SerializedName( "bananaGold")
	private int goldBananaCount;
	@SerializedName( "level")
	private int level;
	@SerializedName( "mobile")
	private String mobile;
	@SerializedName( "mobileCheck")
	private int mobileCheck;
	@SerializedName( "username")
	private String name;
	@SerializedName( "nextLevelNeed")
	private int nextLevelExp;
	@SerializedName( "currExp")
	private int previousLevelExp;
	@SerializedName( "gender")
	private int sex;
	@SerializedName( "signature")
	private String signature;
	@SerializedName( "userId")
	private int uid;
	@SerializedName( "exp")
	private int userExp;
	@SerializedName( "userGroupLevel")
	private int userGroupLevel;

	public String getContributes() {
		return this.contributes;
	}

	public void setContributes(String str) {
		this.contributes = str;
	}

	public String getFollowing() {
		return this.following;
	}

	public void setFollowing(String str) {
		this.following = str;
	}

	public String getFollowed() {
		return this.followed;
	}

	public void setFollowed(String str) {
		this.followed = str;
	}

	public int getBananaCount() {
		return this.bananaCount;
	}

	public void setBananaCount(int i) {
		this.bananaCount = i;
	}

	public int getGoldBananaCount() {
		return this.goldBananaCount;
	}

	public void setGoldBananaCount(int i) {
		this.goldBananaCount = i;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String str) {
		this.mobile = str;
	}

	public int getMobileCheck() {
		return this.mobileCheck;
	}

	public void setMobileCheck(int i) {
		this.mobileCheck = i;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int i) {
		this.level = i;
	}

	public void setUserExp(int i) {
		this.userExp = i;
	}

	public void setPreviousLevelExp(int i) {
		this.previousLevelExp = i;
	}

	public void setNextLevelExp(int i) {
		this.nextLevelExp = i;
	}

	public float getUserExp() {
		return (float) this.userExp;
	}

	public float getPreviousLevelExp() {
		return (float) this.previousLevelExp;
	}

	public float getNextLevelExp() {
		return (float) this.nextLevelExp;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int i) {
		this.uid = i;
	}

	public int getSex() {
		return this.sex;
	}

	public void setSex(int i) {
		this.sex = i;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String str) {
		this.name = str;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String str) {
		this.avatar = str;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String str) {
		this.signature = str;
	}

	public int getUserGroupLevel() {
		return this.userGroupLevel;
	}

	public void setUserGroupLevel(int i) {
		this.userGroupLevel = i;
	}
}
