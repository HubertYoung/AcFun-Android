package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:25
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class VideoDetail {
	@JSONField(name = "album")
	public VideoDetailAlbum album;
	@JSONField(name = "detailsShow")
	public int detailsShow;
	@JSONField(name = "isComment")
	public boolean isComment;
	@JSONField(name = "channelId")
	public int mChannelId;
	@JSONField(name = "contentId")
	public int mContentId;
	@JSONField(name = "cover")
	public String mCoverUrl;
	@JSONField(name = "description")
	public String mDescription;
	@JSONField(name = "isArticle")
	public int mIsArticle;
	@JSONField(name = "isRecommend")
	public int mIsRecommend;
	@JSONField(name = "owner")
	public Owner mOwner;
	@JSONField(name = "parentChannelId")
	public int mParentChannelId;
	@JSONField(name = "releaseDate")
	public long mReleaseDate;
	@JSONField(name = "tags")
	public ArrayList<String> mTags;
	@JSONField(name = "title")
	public String mTitle;
	@JSONField(name = "topLevel")
	public int mTopLevel;
	@JSONField(name = "videos")
	public ArrayList<NetVideo> mVideos;
	@JSONField(name = "viewOnly")
	public int mViewOnly;
	@JSONField(name = "visit")
	public Visits mVisit;
	@JSONField(name = "redirect")
	public String redirect;
	@JSONField(name = "shareUrl")
	public String shareUrl;

	public FullContent convertToFullContent() {
		FullContent fullContent = new FullContent();
		fullContent.setCid(this.mContentId);
		fullContent.setChannelId(this.mChannelId);
		fullContent.setParentChannelId(this.mParentChannelId);
		fullContent.setShareUrl(this.shareUrl);
		if (this.mVisit != null) {
			fullContent.setComments(this.mVisit.comments);
			fullContent.setViews(this.mVisit.views);
			fullContent.setStows(this.mVisit.stows);
			fullContent.setDanmakuCount(this.mVisit.danmakuSize);
			fullContent.setGoldBananaCount(this.mVisit.goldBanana);
		}
		fullContent.setCover(this.mCoverUrl);
		fullContent.setDescription(this.mDescription);
		fullContent.setReleaseDate(this.mReleaseDate);
		fullContent.setTags(this.mTags);
		fullContent.setTitle(this.mTitle);
		fullContent.setViewOnly(this.mViewOnly);
		if (this.mOwner != null) {
			fullContent.setUser(this.mOwner.convertToUser());
		}
		fullContent.setTopLevel(this.mTopLevel);
		fullContent.setIsArticle(this.mIsArticle);
		fullContent.setIsRecommend(this.mIsRecommend);
		fullContent.setComment(this.isComment);
		List arrayList = new ArrayList();
		if (this.mVideos != null) {
			Iterator it = this.mVideos.iterator();
			while (it.hasNext()) {
				arrayList.add(((NetVideo) it.next()).convertToVideo());
			}
		}
		fullContent.setVideos(arrayList);
		if (this.album != null) {
			fullContent.setAlbumCoverImg(this.album.coverImageV);
			fullContent.setAlbumID(this.album.albumID);
			fullContent.setAlbumTitle(this.album.title);
		}
		fullContent.setDetailsShow(this.detailsShow);
		fullContent.setRedirect(this.redirect);
		return fullContent;
	}
}
