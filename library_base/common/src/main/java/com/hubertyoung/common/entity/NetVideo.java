package com.hubertyoung.common.entity;

import com.google.gson.annotations.SerializedName;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:19
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class NetVideo {
	public static final int ALLLEVEL = -1;
	public static final int CARD_LEVEL = 2;
	public static final int MEDIA_TYPE_NATIVE_PLAYER = 1;
	public static final int MEDIA_TYPE_WEB_PLAYER = 3;
	public static final int REGIESTLEVEL = 0;
	public static final int VIPLEVEL = 1;
	@SerializedName("allowDanmaku")
//	@Column(name = "allowDanmaku")
	public int allowDanmaku;
	@SerializedName("bangumiId")
//	@Column(name = "bangumiId")
	public int mBangumiId;
	@SerializedName("commentId")
//	@Column(name = "commentId")
	public int mCommentId;
	@SerializedName("danmakuId")
//	@Column(name = "danmakuId")
	public String mDanmakuId;
	@SerializedName("mediaType")
//	@Column(name = "mediaType")
	public int mMediaType;
	@SerializedName("sort")
//	@Column(name = "sort")
	public int mSort;
	@SerializedName("sourceId")
//	@Column(name = "sourceId")
	public String mSourceId;
	@SerializedName("sourceIdBackup")
//	@Column(name = "sourceIdBackup")
	public String mSourceIdBackup;
	@SerializedName("sourceType")
//	@Column(name = "sourceType")
	public String mSourceType;
	@SerializedName("sourceTypeBackup")
//	@Column(name = "sourceTypeBackup")
	public String mSourceTypeBackup;
	@SerializedName("startTime")
//	@Column(name = "startTime")
	public long mStartTime;
	@SerializedName("time")
//	@Column(name = "time")
	public long mTime;
	@SerializedName("title")
//	@Column(name = "title")
	public String mTitle;
	@SerializedName("updateTime")
//	@Column(name = "updateTime")
	public long mUpdateTime;
	@SerializedName("url")
//	@Column(name = "url")
	public String mUrl;
	@SerializedName("urlMobile")
//	@Column(name = "urlMobile")
	public String mUrlMobile;
	@SerializedName("urlWeb")
//	@Column(name = "urlWeb")
	public String mUrlWeb;
	@SerializedName("videoId")
//	@Column(autoGen = false, isId = true, name = "videoId")
	public int mVideoId;
	@SerializedName("visibleLevel")
	public int mVisibleLevel;

	public Video convertToVideo() {
		Video video = new Video();
		video.setVid(this.mVideoId);
		video.setUrl(this.mUrlMobile);
		video.setTitle(this.mTitle);
		video.setDanmakuID(this.mDanmakuId);
		video.setSid(this.mSourceId);
		video.setStype(this.mSourceType);
		video.setBid(this.mBangumiId);
		video.setStartTime(this.mStartTime);
		video.setType(this.mSourceType);
		video.setSort(this.mSort);
		video.setAllowDanmaku(this.allowDanmaku);
		video.setVisibleLevel(this.mVisibleLevel);
		video.mMediaType = this.mMediaType;
		return video;
	}
}
