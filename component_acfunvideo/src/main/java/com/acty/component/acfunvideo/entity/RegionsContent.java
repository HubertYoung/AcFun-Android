package com.acty.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

import java.security.acl.Owner;
import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:16
 * @since:V$VERSION
 * @desc:com.acty.component.acfunvideo.entity
 */
public class RegionsContent {
	@SerializedName("actionId")
	public int actionId;
	@SerializedName("advertLists")
	public List<AdvertLists> advertLists;
	@SerializedName("channelId")
	public int channelId;
	@SerializedName("contentId")
	public int contentId;
	@SerializedName("gameInfo")
	public GameInfo gameInfo;
	@SerializedName("hide")
	public int hide;
	@SerializedName("id")
	public int id;
	@SerializedName("image")
	public String image;
	@SerializedName("imgUrl")
	public String imgUrl;
	@SerializedName("intro")
	public String intro;
	@SerializedName("ad")
	public boolean isAd;
	@SerializedName("latestBangumiVideo")
	public NetVideo latestBangumiVideo;
	@SerializedName("owner")
	public Owner owner;
	@SerializedName("regionId")
	public int regionId;
	@SerializedName("releasedAt")
	public long releasedAt;
	@SerializedName("resourceId")
	public String resourceId;
	@SerializedName("shareTagShow")
	public int shareTagShow;
	@SerializedName("spreadType")
	public int spreadType;
	@SerializedName("status")
	public int statu;
	@SerializedName("subTitle")
	public String subTitle;
	@SerializedName("subVideo")
	public VideoDetail subVideo;
	@SerializedName("subVideos")
	public List<VideoDetail> subVideos;
	@SerializedName("title")
	public String title;
	@SerializedName("type")
	public RegionsType type;
	@SerializedName("url")
	public String url;
	@SerializedName("visit")
	public Visits visit;
	@SerializedName("webImage")
	public String webImage;
	public int week;
}
