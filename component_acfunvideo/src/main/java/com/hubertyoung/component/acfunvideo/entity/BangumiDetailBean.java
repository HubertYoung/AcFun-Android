package com.hubertyoung.component.acfunvideo.entity;


import com.hubertyoung.common.entity.NetVideo;

import java.io.Serializable;
import java.util.List;


public class BangumiDetailBean implements Serializable {
	public static final int TARGET_URL_TYPE_BROWSER = 1;
	public static final int TARGET_URL_TYPE_WEBVIEW = 0;
	public int channelId;
	public String commentCount;
	public String favouriteCount;
	public String id;
	public String intro;
	public boolean isCanComment;
	public boolean isCanDownload;
	public boolean isCanPlay;
	public int parentChannelId;
	public List< RelatedBangumisBean > relatedBangumis;
	public ShareBean share;
	public List< Tag > tags;
	public String targetUrl;
	public String title;
	public String titleImage;
	public String updateContent;
	public List< List< NetVideo > > videoGroupContent;
	public List< VideoGroupTitleBean > videoGroupTitle;
	public String viewsCount;

	public static class RelatedBangumisBean implements Serializable {
		public String id;
		public String name;
	}

	public static class ShareBean implements Serializable {
		public String shareUrl;
	}

	public static class VideoGroupTitleBean implements Serializable {
		public String id;
		public String name;
	}
}
