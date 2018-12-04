package com.hubertyoung.common.entity;

import com.google.gson.annotations.SerializedName;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:15
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class RegionHeaderAndBottomContent {
	@SerializedName("action")
	public int actionId;
	@SerializedName("ad")
	public int ad;
	@SerializedName("color")
	public String color;
	@SerializedName("href")
	public String contentId;
	@SerializedName("img")
	public String image;
	@SerializedName("title")
	public String title;
}
