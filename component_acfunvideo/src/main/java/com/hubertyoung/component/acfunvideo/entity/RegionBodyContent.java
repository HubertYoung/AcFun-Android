package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:09
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class RegionBodyContent {
	@JSONField(name = "action")
	public int actionId;
	@JSONField(name ="ad")
	public int ad;
	@JSONField(name ="channel")
	public RegionsType channel;
	@JSONField(name ="children")
	public List<RegionBodyContent> children;
	@JSONField(name ="href")
	public String contentId;
	@JSONField(name ="extendsStatus")
	public int extendsStatus;
	@JSONField(name ="img")
	public List<String> images;
	@JSONField(name ="intro")
	public String intro;
	@JSONField(name ="lastUpdate")
	public String lastUpdate;
	@JSONField(name ="latestBangumiVideo")
	public NetVideo latestBangumiVideo;
	@JSONField(name ="reqId")
	public String reqId;
	@JSONField(name ="stowCount")
	public int stowCount;
	@JSONField(name ="subVideo")
	public VideoDetail subVideo;
	@JSONField(name ="subVideos")
	public List<VideoDetail> subVideos;
	@JSONField(name ="time")
	public long time;
	@JSONField(name ="title")
	public String title;
	@JSONField(name ="type")
	public RegionsType type;
	@JSONField(name ="updateTime")
	public String updateTime;
	@JSONField(name ="user")
	public RegionsType user;
	@JSONField(name ="videoId")
	public int videoId;
	@JSONField(name ="visit")
	public RegionVisitContent visit;
}
