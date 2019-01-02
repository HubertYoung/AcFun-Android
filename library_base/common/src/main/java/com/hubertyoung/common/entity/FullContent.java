package com.hubertyoung.common.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:26
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class FullContent implements Serializable {
	private static final long serialVersionUID = 1761165828577857741L;

	public static final int AVAILABLE_TO_GUEST = 0;
	private String albumCoverImg;
	private int albumID;
	private String albumTitle;
	@SerializedName("channelId")
	private int channelId;
	@SerializedName("contentId")
	private int cid;
	@SerializedName("channelName")
	public String channelName;
	@SerializedName("comments")
	private int comments;
	@SerializedName("cover")
	private String cover;
//	@JSONField(serialize = false)
	private int danmakuCount;
	@SerializedName("description")
	private String description;
	@SerializedName("detailsShow")
	public int detailsShow;
//	@JSONField(serialize = false)
	private int goldBananaCount;
	@SerializedName("isArticle")
	private int isArticle;
	@SerializedName("isComment")
	private boolean isComment;
	@SerializedName("isRecommend")
	private int isRecommend;
	@SerializedName("parentChannelId")
	private int parentChannelId;
	@SerializedName("redirect")
	public String redirect;
	@SerializedName("releaseDate")
	private long releaseDate;
	@SerializedName("shareUrl")
	private String shareUrl;
	@SerializedName("stows")
	private int stows;
	@SerializedName("tags")
	private ArrayList<String> tags;
	@SerializedName("time")
	private int time;
	@SerializedName("title")
	private String title;
	@SerializedName("toplevel")
	private int topLevel;
	@SerializedName("user")
	private User user;
	@SerializedName("videos")
	private List<Video > videos;
	@SerializedName("viewOnly")
	private int viewOnly;
	@SerializedName("views")
	private int views;

	public int getDetailsShow() {
		return this.detailsShow;
	}

	public void setDetailsShow(int i) {
		this.detailsShow = i;
	}

	public String getRedirect() {
		return this.redirect;
	}

	public void setRedirect(String str) {
		this.redirect = str;
	}

	public int getAlbumID() {
		return this.albumID;
	}

	public void setAlbumID(int i) {
		this.albumID = i;
	}

	public String getAlbumTitle() {
		return this.albumTitle;
	}

	public void setAlbumTitle(String str) {
		this.albumTitle = str;
	}

	public String getAlbumCoverImg() {
		return this.albumCoverImg;
	}

	public void setAlbumCoverImg(String str) {
		this.albumCoverImg = str;
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int i) {
		this.time = i;
	}

	public int getCid() {
		return this.cid;
	}

	public void setCid(int i) {
		this.cid = i;
	}

	public List<Video> getVideos() {
		return this.videos;
	}

	public void setVideos(List<Video> list) {
		this.videos = list;
	}

	public int getViews() {
		return this.views;
	}

	public void setViews(int i) {
		this.views = i;
	}

	public int getComments() {
		return this.comments;
	}

	public void setComments(int i) {
		this.comments = i;
	}

	public int getStows() {
		return this.stows;
	}

	public void setStows(int i) {
		this.stows = i;
	}

	public long getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(long j) {
		this.releaseDate = j;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String str) {
		this.description = str;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<String> getTags() {
		return this.tags;
	}

	public void setTags(ArrayList<String> arrayList) {
		this.tags = arrayList;
	}

	public int getViewOnly() {
		return this.viewOnly;
	}

	public void setViewOnly(int i) {
		this.viewOnly = i;
	}

	public int getChannelId() {
		return this.channelId;
	}

	public void setChannelId(int i) {
		this.channelId = i;
	}

	public int getParentChannelId() {
		return this.parentChannelId;
	}

	public void setParentChannelId(int i) {
		this.parentChannelId = i;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String str) {
		this.title = str;
	}

	public String getCover() {
		return this.cover;
	}

	public void setCover(String str) {
		this.cover = str;
	}

	public int getTopLevel() {
		return this.topLevel;
	}

	public void setTopLevel(int i) {
		this.topLevel = i;
	}

	public int getIsRecommend() {
		return this.isRecommend;
	}

	public void setIsRecommend(int i) {
		this.isRecommend = i;
	}

	public int getIsArticle() {
		return this.isArticle;
	}

	public void setIsArticle(int i) {
		this.isArticle = i;
	}

	public int getDanmakuCount() {
		return this.danmakuCount;
	}

	public void setDanmakuCount(int i) {
		this.danmakuCount = i;
	}

	public int getGoldBananaCount() {
		return this.goldBananaCount;
	}

	public void setGoldBananaCount(int i) {
		this.goldBananaCount = i;
	}

	public void setComment(boolean z) {
		this.isComment = z;
	}

	public boolean isComment() {
		return this.isComment;
	}

	public void setShareUrl(String str) {
		this.shareUrl = str;
	}

	public String getShareUrl() {
		return this.shareUrl;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String str) {
		this.channelName = str;
	}
}
