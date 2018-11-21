package com.hubertyoung.common.entity;

import com.google.gson.annotations.SerializedName;

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
	@SerializedName( "action" )
	public int actionId;
	@SerializedName( "ad" )
	public int ad;
	@SerializedName( "channel" )
	public RegionsType channel;
	@SerializedName( "children" )
	public List< RegionBodyContent > children;
	@SerializedName( "href" )
	public String contentId;
	@SerializedName( "extendsStatus" )
	public int extendsStatus;
	@SerializedName( "groupid" )
	public String groupId;
	@SerializedName( "img" )
	public List< String > images;
	@SerializedName( "intro" )
	public String intro;
	@SerializedName( "lastUpdate" )
	public String lastUpdate;
	@SerializedName( "latestBangumiVideo" )
	public NetVideo latestBangumiVideo;
	@SerializedName( "reqId" )
	public String reqId;
	@SerializedName( "stowCount" )
	public int stowCount;
	@SerializedName( "subVideo" )
	public VideoDetail subVideo;
	@SerializedName( "subVideos" )
	public List< VideoDetail > subVideos;
	@SerializedName( "time" )
	public long time;
	@SerializedName( "title" )
	public String title;
	@SerializedName( "type" )
	public RegionsType type;
	@SerializedName( "updateTime" )
	public String updateTime;
	@SerializedName( "user" )
	public RegionsType user;
	@SerializedName( "videoId" )
	public int videoId;
	@SerializedName( "visit" )
	public RegionVisitContent visit;
}
