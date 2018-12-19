package com.hubertyoung.component.acfunvideo.entity;


import com.hubertyoung.common.entity.NetVideo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BangumiDetailBeanRaw implements Serializable {
	private static final int ALL_PLATFORM_CAN_PLAY = -1;
	private static final int ANDROID_PLATFORM_CAN_PLAY = 1;
	public boolean acfunOnly;
	public int channelId;
	public String comments;
	public int contentsCount;
	public String coverImageV;
	public List< EpisodesBean > episodes;
	public List< GroupsBean > groups;
	public int id;
	public String intro;
	public boolean isComment;
	public boolean isDownload;
	public int parentChannelId;
	public int platformControl;
	public String shareUrl;
	public String stowCount;
	public ArrayList< Tag > tags;
	public String targetUrl;
	public String title;
	public String updateContent;
	public int userId;
	public List< VideoGroupContentListBean > videoGroupContent;
	public String viewsCount;

	public static class EpisodesBean implements Serializable {
		public int id;
		public String name;
		public int sort;
	}

	public static class GroupsBean implements Serializable {
		public int id;
		public int isDefault;
		public String name;
		public int sort;
	}

	public static class VideoGroupContentBean implements Serializable {
		public int allowDanmaku;
		public int bangumiId;
		public int danmakuId;
		public int sort;
		public String sourceId;
		public String sourceIdBackup;
		public String sourceType;
		public String sourceTypeBackup;
		public int startTime;
		public String title;
		public int updateTime;
		public String urlMobile;
		public String urlWeb;
		public int videoId;
		public int visibleLevel;
	}

	public static class VideoGroupContentListBean implements Serializable {
		public String id;
		public ArrayList< NetVideo > list;
	}

	public BangumiDetailBean convertRawToBangumiDetailBean() {
		BangumiDetailBean bangumiDetailBean = new BangumiDetailBean();
		bangumiDetailBean.id = String.valueOf( this.id );
		bangumiDetailBean.title = this.title;
		bangumiDetailBean.intro = this.intro;
		bangumiDetailBean.titleImage = this.coverImageV;
		bangumiDetailBean.viewsCount = this.viewsCount;
		bangumiDetailBean.favouriteCount = this.stowCount;
		bangumiDetailBean.updateContent = this.updateContent;
		bangumiDetailBean.commentCount = this.comments;
		bangumiDetailBean.isCanComment = this.isComment;
		bangumiDetailBean.isCanDownload = this.isDownload;
		boolean z = false;
		Object obj = this.platformControl == -1 ? 1 : null;
		Object obj2 = this.platformControl == 1 ? 1 : null;
		if ( !( obj == null && obj2 == null ) ) {
			z = true;
		}
		bangumiDetailBean.isCanPlay = z;
		bangumiDetailBean.targetUrl = this.targetUrl;
		bangumiDetailBean.share = convertToShareBean();
		bangumiDetailBean.relatedBangumis = convertToRelatedBangumis( this.episodes );
		bangumiDetailBean.videoGroupTitle = convertToVideoGroupTitles( this.groups );
		bangumiDetailBean.videoGroupContent = convertToVideoGroupContent( this.videoGroupContent );
		bangumiDetailBean.tags = this.tags;
		bangumiDetailBean.channelId = this.channelId;
		bangumiDetailBean.parentChannelId = this.parentChannelId;
		return bangumiDetailBean;
	}

	private ArrayList< BangumiDetailBean.VideoGroupContentBean > convertToVideoGroupContent( List< VideoGroupContentListBean > list ) {
		ArrayList< BangumiDetailBean.VideoGroupContentBean > arrayList = new ArrayList();
		if ( list != null && !list.isEmpty() ) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				VideoGroupContentListBean videoGroupContentListBean = list.get( i );
				if ( videoGroupContentListBean != null ) {
					ArrayList< NetVideo > videoList = videoGroupContentListBean.list;
					ArrayList arrayList2 = new ArrayList();
					if ( videoList == null || videoList.isEmpty() ) {
						videoList = arrayList2;
					}
					BangumiDetailBean.VideoGroupContentBean videoGroupContentBean = new BangumiDetailBean.VideoGroupContentBean();
					videoGroupContentBean.list = videoList;
					arrayList.add( videoGroupContentBean );
				}
			}
		}
		return arrayList;
	}

	private BangumiDetailBean.ShareBean convertToShareBean() {
		BangumiDetailBean.ShareBean shareBean = new BangumiDetailBean.ShareBean();
		shareBean.shareUrl = this.shareUrl;
		return shareBean;
	}

	private ArrayList< BangumiDetailBean.VideoGroupTitleBean > convertToVideoGroupTitles( List< GroupsBean > list ) {
		ArrayList< BangumiDetailBean.VideoGroupTitleBean > arrayList = new ArrayList();
		if ( list != null && !list.isEmpty() ) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				BangumiDetailBean.VideoGroupTitleBean videoGroupTitleBean = new BangumiDetailBean.VideoGroupTitleBean();
				GroupsBean groupsBean = list.get( i );
				if ( groupsBean != null ) {
					videoGroupTitleBean.id = String.valueOf( groupsBean.id );
					videoGroupTitleBean.name = String.valueOf( groupsBean.name );
					arrayList.add( videoGroupTitleBean );
				}
			}
		}
		return arrayList;
	}

	private List< BangumiDetailBean.RelatedBangumisBean > convertToRelatedBangumis( List< EpisodesBean > list ) {
		List arrayList = new ArrayList();
		if ( list != null && !list.isEmpty() ) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				BangumiDetailBean.RelatedBangumisBean relatedBangumisBean = new BangumiDetailBean.RelatedBangumisBean();
				EpisodesBean episodesBean = ( EpisodesBean ) list.get( i );
				if ( episodesBean != null ) {
					relatedBangumisBean.id = String.valueOf( episodesBean.id );
					relatedBangumisBean.name = String.valueOf( episodesBean.name );
					arrayList.add( relatedBangumisBean );
				}
			}
		}
		return arrayList;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( "BangumiDetailBeanRaw{id=" );
		stringBuilder.append( this.id );
		stringBuilder.append( ", mContentText='" );
		stringBuilder.append( this.title );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", intro='" );
		stringBuilder.append( this.intro );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", coverImageV='" );
		stringBuilder.append( this.coverImageV );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", userId=" );
		stringBuilder.append( this.userId );
		stringBuilder.append( ", acfunOnly=" );
		stringBuilder.append( this.acfunOnly );
		stringBuilder.append( ", viewsCount='" );
		stringBuilder.append( this.viewsCount );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", comments='" );
		stringBuilder.append( this.comments );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", stowCount='" );
		stringBuilder.append( this.stowCount );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", contentsCount=" );
		stringBuilder.append( this.contentsCount );
		stringBuilder.append( ", targetUrl='" );
		stringBuilder.append( this.targetUrl );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", updateContent='" );
		stringBuilder.append( this.updateContent );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", shareUrl='" );
		stringBuilder.append( this.shareUrl );
		stringBuilder.append( '\'' );
		stringBuilder.append( ", tags=" );
		stringBuilder.append( this.tags );
		stringBuilder.append( ", groups=" );
		stringBuilder.append( this.groups );
		stringBuilder.append( ", episodes=" );
		stringBuilder.append( this.episodes );
		stringBuilder.append( ", videoGroupContent=" );
		stringBuilder.append( this.videoGroupContent );
		// TODO: 2018/12/18 ASCIIPropertyListParser.DICTIONARY_END_TOKEN
//        stringBuilder.append(ASCIIPropertyListParser.DICTIONARY_END_TOKEN);
		return stringBuilder.toString();
	}
}
