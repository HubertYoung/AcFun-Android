package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

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
	@JSONField(name = "advertLists")
	public List<AdvertLists> advertLists;
	@JSONField(name = "belong")
	public int belong;
	@JSONField(name = "bodyContents")
	public List<RegionBodyContent> bodyContents;
	@JSONField(name = "bottomText")
	public RegionHeaderAndBottomContent bottomText;
	public List<RegionBodyContent> changeContents;
	public int changeCount = 0;
	@JSONField(name = "channel")
	public int channel;
	@JSONField(name = "channelId")
	public int channelId;
	@JSONField(name = "children")
	public List<Regions> children;
	@JSONField(name = "contentCount")
	public int contentCount;
	@JSONField(name = "contents")
	public List<RegionsContent> contents;
	@JSONField(name = "goText")
	public String goText;
	@JSONField(name = "headerText")
	public List<RegionHeaderAndBottomContent> headerText;
	@JSONField(name = "hide")
	public int hide;
	@JSONField(name = "id")
	public int id;
	@JSONField(name = "image")
	public String image;
	@JSONField(name = "img")
	public String img;
	@JSONField(name = "menuCount")
	public int menuCount;
	@JSONField(name = "menus")
	public List<RegionsMenu> menus;
	@JSONField(name = "name")
	public String name;
	@JSONField(name = "schema")
	public String schema;
	@JSONField(name = "show")
	public int show;
	@JSONField(name = "showChange")
	public int showChange = 0;
	@JSONField(name = "showLine")
	public int showLine;
	@JSONField(name = "showMore")
	public int showMore;
	@JSONField(name = "showName")
	public int showName;
	@JSONField(name = "sort")
	public int sort;
	@JSONField(name = "statu")
	public int statu;
	@JSONField(name = "title")
	public String title;
	@JSONField(name = "topContent")
	public RegionBodyContent topContent;
	@JSONField(name = "type")
	public RegionsType type;
	@JSONField(name = "url")
	public String url;
}
