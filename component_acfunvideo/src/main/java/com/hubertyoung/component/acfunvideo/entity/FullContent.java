package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

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
public class FullContent {
	public static final int AVAILABLE_TO_GUEST = 0;
	private String albumCoverImg;
	private int albumID;
	private String albumTitle;
	@JSONField(name = "channelId")
	private int channelId;
	@JSONField(name = "contentId")
	private int cid;
	@JSONField(name = "comments")
	private int comments;
	@JSONField(name = "cover")
	private String cover;
//	@JSONField(serialize = false)
	private int danmakuCount;
	@JSONField(name = "description")
	private String description;
	@JSONField(name = "detailsShow")
	public int detailsShow;
//	@JSONField(serialize = false)
	private int goldBananaCount;
	@JSONField(name = "isArticle")
	private int isArticle;
	@JSONField(name = "isComment")
	private boolean isComment;
	@JSONField(name = "isRecommend")
	private int isRecommend;
	@JSONField(name = "parentChannelId")
	private int parentChannelId;
	@JSONField(name = "redirect")
	public String redirect;
	@JSONField(name = "releaseDate")
	private long releaseDate;
	@JSONField(name = "shareUrl")
	private String shareUrl;
	@JSONField(name = "stows")
	private int stows;
	@JSONField(name = "tags")
	private ArrayList<String> tags;
	@JSONField(name = "time")
	private int time;
	@JSONField(name = "title")
	private String title;
	@JSONField(name = "toplevel")
	private int topLevel;
	@JSONField(name = "user")
	private User user;
	@JSONField(name = "videos")
	private List<Video> videos;
	@JSONField(name = "viewOnly")
	private int viewOnly;
	@JSONField(name = "views")
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
}
