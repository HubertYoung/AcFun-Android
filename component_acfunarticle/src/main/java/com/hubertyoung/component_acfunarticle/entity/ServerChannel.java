package com.hubertyoung.component_acfunarticle.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/16 18:22
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunarticle.entity
 */
public class ServerChannel {
	public static final int CAN_CONTRIBUTE = 1;
	@SerializedName( "contributeStatus" )
	public int contributeStatus;
	@SerializedName( "id" )
	public int id;
	@SerializedName( "img" )
	public String img;
	@SerializedName( "name" )
	public String name;
	@SerializedName( "pid" )
	public int pid;
	@SerializedName( "realm" )
	public List< ServerChannel > realm;
	@SerializedName( "children" )
	public List< ServerChannel > subChannels;

	public String toString() {
		return this.name;
	}
}
