package com.hubertyoung.common.entity;

import com.google.gson.annotations.SerializedName;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:23
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class BangumiVideo {
	@SerializedName("bangumiId")
	public int bid;
	@SerializedName("danmakuId")
	public String danmakuID;
	public int order;
	@SerializedName("sourceId")
	public String sid;
	@SerializedName("sort")
	public int sort;
	@SerializedName("startTime")
	public long startTime;
	@SerializedName("sourceType")
	public String stype;
	@SerializedName("title")
	public String title;
	@SerializedName("type")
	public String type;
	@SerializedName("url")
	public String url;
	@SerializedName("id")
	public int vid;

}
