package com.acty.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:18
 * @since:V$VERSION
 * @desc:com.acty.component.acfunvideo.entity
 */
public class RegionsType {
	@SerializedName("id")
	public int id;
	@SerializedName("img")
	public String img;
	@SerializedName("intro")
	public String intro;
	@SerializedName("name")
	public String name;
	@SerializedName("value")
	public String value;
	public int viewType;
}
