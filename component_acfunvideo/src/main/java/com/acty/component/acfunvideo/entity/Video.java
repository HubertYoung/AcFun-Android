package com.acty.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:21
 * @since:V$VERSION
 * @desc:com.acty.component.acfunvideo.entity
 */
public class Video {
	public static final String IQIYI = "iqiyi";
	public static final String LETV = "letv";
	public static final String LETV2 = "letv2";
	public static final String TUDOU = "tudou";
	public static final String TUDOU2 = "tudou2";
	public static final String YOUKU = "youku";
	@SerializedName("allowDanmaku")
	private int allowDanmaku;
	@SerializedName("bangumiId")
//	@Column(name = "bid")
	private int bid;
	@SerializedName("danmakuId")
//	@Column(name = "danmakuid")
	private String danmakuID;
	public boolean isAutoPlay;
	private boolean isLocal = false;
//	@Column(name = "mediaType")
	public int mMediaType;
//	@JSONField(serialize = false)
//	@Column(name = "corder")
	private int order;
	@SerializedName("sourceId")
//	@Column(name = "sid")
	private String sid;
	@SerializedName("sort")
//	@Column(name = "sort")
	private int sort;
	@SerializedName("startTime")
//	@Column(name = "starttime")
	private long startTime;
	@SerializedName("sourceType")
//	@Column(name = "stype")
	private String stype;
	@SerializedName("name")
//	@Column(name = "title")
	private String title;
	@SerializedName("type")
//	@Column(name = "type")
	private String type;
	@SerializedName("url")
//	@Column(name = "url")
	private String url;
	@SerializedName("videoId")
//	@Column(autoGen = false, isId = true, name = "vid")
	private int vid;
	@SerializedName("visibleLevel")
	public int visibleLevel;

	public boolean isLocal() {
		return this.isLocal;
	}

	public void setLocal(boolean z) {
		this.isLocal = z;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int i) {
		this.sort = i;
	}

	public int getBid() {
		return this.bid;
	}

	public void setBid(int i) {
		this.bid = i;
	}

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int i) {
		this.order = i;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String str) {
		this.url = str;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String str) {
		this.title = str;
	}

	public int getVid() {
		return this.vid;
	}

	public void setVid(int i) {
		this.vid = i;
	}

	public String getDanmakuID() {
		return this.danmakuID;
	}

	public void setDanmakuID(String str) {
		this.danmakuID = str;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String str) {
		this.sid = str;
	}

	public String getStype() {
		if (this.stype == null) {
			return "";
		}
		return this.stype;
	}

	public void setStype(String str) {
		this.stype = str;
	}

	public long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(long j) {
		this.startTime = j;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String str) {
		this.type = str;
	}

	public int getAllowDanmaku() {
		return this.allowDanmaku;
	}

	public void setAllowDanmaku(int i) {
		this.allowDanmaku = i;
	}

	public int getVisibleLevel() {
		return this.visibleLevel;
	}

	public void setVisibleLevel(int i) {
		this.visibleLevel = i;
	}

	public static List<Video> parseFromBangumiVideo( List<BangumiVideo> list) {
		if (list == null) {
			return null;
		}
		List<Video> arrayList = new ArrayList();
		for (BangumiVideo bangumiVideo : list) {
			Video video = new Video();
			video.setVid(bangumiVideo.vid);
			video.setBid(bangumiVideo.bid);
			video.setDanmakuID(bangumiVideo.danmakuID);
			video.setOrder(bangumiVideo.bid);
			video.setSid(bangumiVideo.sid);
			video.setStartTime(bangumiVideo.startTime);
			video.setStype(bangumiVideo.stype);
			video.setTitle(bangumiVideo.title);
			video.setType(bangumiVideo.type);
			video.setUrl(bangumiVideo.url);
			video.setSort(bangumiVideo.sort);
			arrayList.add(video);
		}
		return arrayList;
	}

	public int compareTo(Object obj) {
		Video video = (Video) obj;
		if (this.sort > video.sort) {
			return 1;
		}
		return this.sort < video.sort ? -1 : 0;
	}
}
