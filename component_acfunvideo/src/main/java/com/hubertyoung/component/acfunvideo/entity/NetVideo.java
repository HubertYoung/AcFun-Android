package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

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
	@JSONField(name ="allowDanmaku")
//	@Column(name = "allowDanmaku")
	public int allowDanmaku;
	@JSONField(name ="bangumiId")
//	@Column(name = "bangumiId")
	public int mBangumiId;
	@JSONField(name ="commentId")
//	@Column(name = "commentId")
	public int mCommentId;
	@JSONField(name ="danmakuId")
//	@Column(name = "danmakuId")
	public String mDanmakuId;
	@JSONField(name ="mediaType")
//	@Column(name = "mediaType")
	public int mMediaType;
	@JSONField(name ="sort")
//	@Column(name = "sort")
	public int mSort;
	@JSONField(name ="sourceId")
//	@Column(name = "sourceId")
	public String mSourceId;
	@JSONField(name ="sourceIdBackup")
//	@Column(name = "sourceIdBackup")
	public String mSourceIdBackup;
	@JSONField(name ="sourceType")
//	@Column(name = "sourceType")
	public String mSourceType;
	@JSONField(name ="sourceTypeBackup")
//	@Column(name = "sourceTypeBackup")
	public String mSourceTypeBackup;
	@JSONField(name ="startTime")
//	@Column(name = "startTime")
	public long mStartTime;
	@JSONField(name ="time")
//	@Column(name = "time")
	public long mTime;
	@JSONField(name ="title")
//	@Column(name = "title")
	public String mTitle;
	@JSONField(name ="updateTime")
//	@Column(name = "updateTime")
	public long mUpdateTime;
	@JSONField(name ="url")
//	@Column(name = "url")
	public String mUrl;
	@JSONField(name ="urlMobile")
//	@Column(name = "urlMobile")
	public String mUrlMobile;
	@JSONField(name ="urlWeb")
//	@Column(name = "urlWeb")
	public String mUrlWeb;
	@JSONField(name ="videoId")
//	@Column(autoGen = false, isId = true, name = "videoId")
	public int mVideoId;
	@JSONField(name ="visibleLevel")
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
