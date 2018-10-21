package com.hubertyoung.common.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/16 13:30
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.entity
 */
public class User implements Serializable {
	@JSONField(name = "userImg" )
	private String avatar;
	@JSONField(name = "banana" )
	private int bananaCount;
	@JSONField(name = "contributes" )
	private String contributes;
	@JSONField(name = "followed" )
	private String followed;
	@JSONField(name = "following" )
	private String following;
	@JSONField(name = "bananaGold" )
	private int goldBananaCount;
	@JSONField(name = "level" )
	private int level;
	@JSONField(name = "mobile" )
	private String mobile;
	@JSONField(name = "mobileCheck" )
	private int mobileCheck;
	@JSONField(name = "username" )
	private String name;
	@JSONField(name = "nextLevelNeed" )
	private int nextLevelExp;
	@JSONField(name = "currExp" )
	private int previousLevelExp;
	@JSONField(name = "gender" )
	private int sex;
	@JSONField(name = "signature" )
	private String signature;
	@JSONField(name = "userId" )
	private int uid;
	@JSONField(name = "exp" )
	private int userExp;
	@JSONField(name = "userGroupLevel" )
	private int userGroupLevel;

	public User() {
	}

	public String getContributes() {
		return this.contributes;
	}

	public void setContributes( String str ) {
		this.contributes = str;
	}

	public String getFollowing() {
		return this.following;
	}

	public void setFollowing( String str ) {
		this.following = str;
	}

	public String getFollowed() {
		return this.followed;
	}

	public void setFollowed( String str ) {
		this.followed = str;
	}

	public int getBananaCount() {
		return this.bananaCount;
	}

	public void setBananaCount( int i ) {
		this.bananaCount = i;
	}

	public int getGoldBananaCount() {
		return this.goldBananaCount;
	}

	public void setGoldBananaCount( int i ) {
		this.goldBananaCount = i;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile( String str ) {
		this.mobile = str;
	}

	public int getMobileCheck() {
		return this.mobileCheck;
	}

	public void setMobileCheck( int i ) {
		this.mobileCheck = i;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel( int i ) {
		this.level = i;
	}

	public void setUserExp( int i ) {
		this.userExp = i;
	}

	public void setPreviousLevelExp( int i ) {
		this.previousLevelExp = i;
	}

	public void setNextLevelExp( int i ) {
		this.nextLevelExp = i;
	}

	public float getUserExp() {
		return ( float ) this.userExp;
	}

	public float getPreviousLevelExp() {
		return ( float ) this.previousLevelExp;
	}

	public float getNextLevelExp() {
		return ( float ) this.nextLevelExp;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid( int i ) {
		this.uid = i;
	}

	public int getSex() {
		return this.sex;
	}

	public void setSex( int i ) {
		this.sex = i;
	}

	public String getName() {
		return this.name;
	}

	public void setName( String str ) {
		this.name = str;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar( String str ) {
		this.avatar = str;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature( String str ) {
		this.signature = str;
	}

	public int getUserGroupLevel() {
		return this.userGroupLevel;
	}

	public void setUserGroupLevel( int i ) {
		this.userGroupLevel = i;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( "User{bananaCount=" );
		stringBuilder.append( this.bananaCount );
		stringBuilder.append( ", goldBananaCount=" );
		stringBuilder.append( this.goldBananaCount );
		stringBuilder.append( ", uid=" );
		stringBuilder.append( this.uid );
		stringBuilder.append( ", sex=" );
		stringBuilder.append( this.sex );
		stringBuilder.append( ", name='" );
		stringBuilder.append( this.name );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", avatar='" );
		stringBuilder.append( this.avatar );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", signature='" );
		stringBuilder.append( this.signature );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", userExp=" );
		stringBuilder.append( this.userExp );
		stringBuilder.append( ", previousLevelExp=" );
		stringBuilder.append( this.previousLevelExp );
		stringBuilder.append( ", nextLevelExp=" );
		stringBuilder.append( this.nextLevelExp );
		stringBuilder.append( ", level=" );
		stringBuilder.append( this.level );
		stringBuilder.append( ", mobileCheck=" );
		stringBuilder.append( this.mobileCheck );
		stringBuilder.append( ", mobile='" );
		stringBuilder.append( this.mobile );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", userGroupLevel=" );
		stringBuilder.append( this.userGroupLevel );
		stringBuilder.append( ", contributes=" );
		stringBuilder.append( this.contributes );
		stringBuilder.append( ", following=" );
		stringBuilder.append( this.following );
		stringBuilder.append( ", followed=" );
		stringBuilder.append( this.followed );
		stringBuilder.append( '}' );
		return stringBuilder.toString();
	}
}
