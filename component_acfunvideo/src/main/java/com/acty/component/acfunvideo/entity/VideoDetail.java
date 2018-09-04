package com.acty.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

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
 * @desc:com.acty.component.acfunvideo.entity
 */
public class VideoDetail {
	@SerializedName( "album")
	public VideoDetailAlbum album;
	@SerializedName( "detailsShow")
	public int detailsShow;
	@SerializedName( "isComment")
	public boolean isComment;
	@SerializedName( "channelId")
	public int mChannelId;
	@SerializedName( "contentId")
	public int mContentId;
	@SerializedName( "cover")
	public String mCoverUrl;
	@SerializedName( "description")
	public String mDescription;
	@SerializedName( "isArticle")
	public int mIsArticle;
	@SerializedName( "isRecommend")
	public int mIsRecommend;
	@SerializedName( "owner")
	public Owner mOwner;
	@SerializedName( "parentChannelId")
	public int mParentChannelId;
	@SerializedName( "releaseDate")
	public long mReleaseDate;
	@SerializedName( "tags")
	public ArrayList<String> mTags;
	@SerializedName( "title")
	public String mTitle;
	@SerializedName( "topLevel")
	public int mTopLevel;
	@SerializedName( "videos")
	public ArrayList<NetVideo> mVideos;
	@SerializedName( "viewOnly")
	public int mViewOnly;
	@SerializedName( "visit")
	public Visits mVisit;
	@SerializedName( "redirect")
	public String redirect;
	@SerializedName( "shareUrl")
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
