package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

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
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class RegionsContent {
	@JSONField(name ="actionId")
	public int actionId;
	@JSONField(name ="advertLists")
	public List<AdvertLists> advertLists;
	@JSONField(name ="channelId")
	public int channelId;
	@JSONField(name ="contentId")
	public int contentId;
	@JSONField(name ="gameInfo")
	public GameInfo gameInfo;
	@JSONField(name ="hide")
	public int hide;
	@JSONField(name ="id")
	public int id;
	@JSONField(name ="image")
	public String image;
	@JSONField(name ="imgUrl")
	public String imgUrl;
	@JSONField(name ="intro")
	public String intro;
	@JSONField(name ="ad")
	public boolean isAd;
	@JSONField(name ="latestBangumiVideo")
	public NetVideo latestBangumiVideo;
	@JSONField(name ="owner")
	public Owner owner;
	@JSONField(name ="regionId")
	public int regionId;
	@JSONField(name ="releasedAt")
	public long releasedAt;
	@JSONField(name ="resourceId")
	public String resourceId;
	@JSONField(name ="shareTagShow")
	public int shareTagShow;
	@JSONField(name ="spreadType")
	public int spreadType;
	@JSONField(name ="status")
	public int statu;
	@JSONField(name ="subTitle")
	public String subTitle;
	@JSONField(name ="subVideo")
	public VideoDetail subVideo;
	@JSONField(name ="subVideos")
	public List<VideoDetail> subVideos;
	@JSONField(name ="title")
	public String title;
	@JSONField(name ="type")
	public RegionsType type;
	@JSONField(name ="url")
	public String url;
	@JSONField(name ="visit")
	public Visits visit;
	@JSONField(name ="webImage")
	public String webImage;
	public int week;
}
