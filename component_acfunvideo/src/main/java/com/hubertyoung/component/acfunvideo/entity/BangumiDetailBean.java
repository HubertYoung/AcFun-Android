package com.hubertyoung.component.acfunvideo.entity;


import com.hubertyoung.common.entity.NetVideo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BangumiDetailBean implements Serializable {

	public static final int TARGET_URL_TYPE_BROWSER = 1;
	public static final int TARGET_URL_TYPE_WEBVIEW = 0;
	private static final long serialVersionUID = -5023235677306915143L;
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
	public ArrayList< Tag > tags;
	public String targetUrl;
	public String title;
	public String titleImage;
	public String updateContent;
	public ArrayList< VideoGroupContentBean > videoGroupContent;
	public ArrayList< VideoGroupTitleBean > videoGroupTitle;
	public String viewsCount;

	public static class RelatedBangumisBean implements Serializable {

		private static final long serialVersionUID = 8958128830589914045L;
		public String id;
		public String name;
	}

	public static class ShareBean implements Serializable {
		private static final long serialVersionUID = -6810109182479286225L;
		public String shareUrl;
	}

	public static class VideoGroupTitleBean implements Serializable {
		private static final long serialVersionUID = 642703479890803985L;
		public String id;
		public String name;
	}
	public static class VideoGroupContentBean implements Serializable {
		private static final long serialVersionUID = 520792536937521119L;
		public String id;
		public ArrayList< NetVideo > list;
	}
}
