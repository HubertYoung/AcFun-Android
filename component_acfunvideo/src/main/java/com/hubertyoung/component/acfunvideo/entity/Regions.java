package com.hubertyoung.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.entity.RegionsType;

import java.util.List;

/**
 * <br>
 * function:推荐
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/3 20:17
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class Regions {
	@SerializedName("advertLists")
	public List<AdvertLists> advertLists;
	@SerializedName("belong")
	public int belong;
	@SerializedName("bodyContents")
	public List<RegionBodyContent > bodyContents;
	@SerializedName("bottomText")
	public RegionHeaderAndBottomContent bottomText;
	public List<RegionBodyContent> changeContents;
	public int changeCount = 0;
	@SerializedName("channel")
	public int channel;
	@SerializedName("channelId")
	public int channelId;
	@SerializedName("children")
	public List<Regions> children;
	@SerializedName("contentCount")
	public int contentCount;
	@SerializedName("contents")
	public List<RegionsContent> contents;
	@SerializedName("goText")
	public String goText;
	@SerializedName("headerText")
	public List<RegionHeaderAndBottomContent> headerText;
	@SerializedName("hide")
	public int hide;
	@SerializedName("id")
	public int id;
	@SerializedName("image")
	public String image;
	@SerializedName("img")
	public String img;
	@SerializedName("menuCount")
	public int menuCount;
	@SerializedName("menus")
	public List<RegionsMenu> menus;
	@SerializedName("name")
	public String name;
	@SerializedName("schema")
	public String schema;
	@SerializedName("show")
	public int show;
	@SerializedName("showChange")
	public int showChange = 0;
	@SerializedName("showLine")
	public int showLine;
	@SerializedName("showMore")
	public int showMore;
	@SerializedName("showName")
	public int showName;
	@SerializedName("sort")
	public int sort;
	@SerializedName("statu")
	public int statu;
	@SerializedName("title")
	public String title;
	@SerializedName("topContent")
	public RegionBodyContent topContent;
	@SerializedName("type")
	public RegionsType type;
	@SerializedName("url")
	public String url;
}
