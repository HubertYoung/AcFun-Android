package com.hubertyoung.common.entity;

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
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class VideoDetail {
	@SerializedName("album")
	public VideoDetailAlbum album;
	@SerializedName("detailsShow")
	public int detailsShow;
	@SerializedName("isComment")
	public boolean isComment;
	@SerializedName("channelId")
	public int mChannelId;
	@SerializedName("contentId")
	public int mContentId;
	@SerializedName("cover")
	public String mCoverUrl;
	@SerializedName("description")
	public String mDescription;
	@SerializedName("isArticle")
	public int mIsArticle;
	@SerializedName("isRecommend")
	public int mIsRecommend;
	@SerializedName("owner")
	public Owner mOwner;
	@SerializedName("parentChannelId")
	public int mParentChannelId;
	@SerializedName("releaseDate")
	public long mReleaseDate;
	@SerializedName("tags")
	public ArrayList<String> mTags;
	@SerializedName("title")
	public String mTitle;
	@SerializedName("topLevel")
	public int mTopLevel;
	@SerializedName("videos")
	public ArrayList<NetVideo > mVideos;
	@SerializedName("viewOnly")
	public int mViewOnly;
	@SerializedName("visit")
	public Visits mVisit;
	@SerializedName("redirect")
	public String redirect;
	@SerializedName("shareUrl")
	public String shareUrl;

	public FullContent convertToFullContent() {
		FullContent fullContent = new FullContent();
		fullContent.setCid(mContentId);
		fullContent.setChannelId(mChannelId);
		fullContent.setParentChannelId(mParentChannelId);
		fullContent.setShareUrl(shareUrl);
		if (mVisit != null) {
			fullContent.setComments(mVisit.comments);
			fullContent.setViews(mVisit.views);
			fullContent.setStows(mVisit.stows);
			fullContent.setDanmakuCount(mVisit.danmakuSize);
			fullContent.setGoldBananaCount(mVisit.goldBanana);
		}
		fullContent.setCover(mCoverUrl);
		fullContent.setDescription(mDescription);
		fullContent.setReleaseDate(mReleaseDate);
		fullContent.setTags(mTags);
		fullContent.setTitle(mTitle);
		fullContent.setViewOnly(mViewOnly);
		if (mOwner != null) {
			fullContent.setUser(mOwner.convertToUser());
		}
		fullContent.setTopLevel(mTopLevel);
		fullContent.setIsArticle(mIsArticle);
		fullContent.setIsRecommend(mIsRecommend);
		fullContent.setComment(isComment);
		List arrayList = new ArrayList();
		if (mVideos != null) {
			Iterator it = mVideos.iterator();
			while (it.hasNext()) {
				arrayList.add(((NetVideo) it.next()).convertToVideo());
			}
		}
		fullContent.setVideos(arrayList);
		if (album != null) {
			fullContent.setAlbumCoverImg(album.coverImageV);
			fullContent.setAlbumID(album.albumID);
			fullContent.setAlbumTitle(album.title);
		}
		fullContent.setDetailsShow(detailsShow);
		fullContent.setRedirect(redirect);
		return fullContent;
	}
}
