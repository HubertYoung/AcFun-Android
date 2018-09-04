package com.acty.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <br>
 * function:推荐
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/3 20:17
 * @since:V$VERSION
 * @desc:com.acty.component.acfunvideo.entity
 */
public class NewRecommendEntity {

	public String schema;
	public String img;
	public int showChange;
	public int show;
	public int id;
	public String title;
	public int maxCount;
	public BottomTextBean bottomText;
	public TopContentBean topContent;
	public int channel;
	public List< BodyContentsBean > bodyContents;
	public List< AdvertListsBean > advertLists;
	public List< HeaderTextBean > headerText;

	public static class BottomTextBean {
		/**
		 * action : 11
		 * title : 查看完整榜单
		 */

		public int action;
		public String title;
	}

	public static class TopContentBean {
		/**
		 * img : ["http://imgs.aixifan.com/content/2018_09_03/1535983360.png"]
		 * channel : {"name":"生活娱乐","id":86}
		 * action : 1
		 * href : 4562904
		 * time : 1535983410000
		 * visit : {"banana":4342,"stows":388,"comments":134,"danmakuSize":689,"views":144714}
		 * title : 电影最TOP 109: 那些年阿汤哥玩过的命，一口气看完《碟中谍》1-5
		 * user : {"img":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201705/02135130i1uit9xz.jpg","name":"电影最TOP","id":4471111}
		 */

		public ChannelBean channel;
		public int action;
		public String href;
		public long time;
		public VisitBean visit;
		public String title;
		public UserBean user;
		public List< String > img;

		public static class ChannelBean {
			/**
			 * name : 生活娱乐
			 * id : 86
			 */

			public String name;
			public int id;
		}

		public static class VisitBean {
			/**
			 * banana : 4342
			 * stows : 388
			 * comments : 134
			 * danmakuSize : 689
			 * views : 144714
			 */

			public int banana;
			public int stows;
			public int comments;
			public int danmakuSize;
			public int views;
		}

		public static class UserBean {
			/**
			 * img : http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201705/02135130i1uit9xz.jpg
			 * name : 电影最TOP
			 * id : 4471111
			 */

			public String img;
			public String name;
			public int id;
		}
	}

	public static class BodyContentsBean {
		@SerializedName( "action")
		public int actionId;
		@SerializedName( "ad")
		public int ad;
//		@SerializedName( "channel")
//		public RegionsType channel;
//		@SerializedName( "children")
//		public List<RegionBodyContent> children;
		@SerializedName( "href")
		public String contentId;
		@SerializedName( "extendsStatus")
		public int extendsStatus;
		@SerializedName( "img")
		public List<String> images;
		@SerializedName( "intro")
		public String intro;
		@SerializedName( "lastUpdate")
		public String lastUpdate;
//		@SerializedName( "latestBangumiVideo")
//		public NetVideo latestBangumiVideo;
		@SerializedName( "reqId")
		public String reqId;
		@SerializedName( "stowCount")
		public int stowCount;
//		@SerializedName( "subVideo")
//		public VideoDetail subVideo;
//		@SerializedName( "subVideos")
//		public List<VideoDetail> subVideos;
//		@SerializedName( "time")
//		public long time;
//		@SerializedName( "title")
//		public String title;
//		@SerializedName( "type")
//		public RegionsType type;
//		@SerializedName( "updateTime")
//		public String updateTime;
//		@SerializedName( "user")
//		public RegionsType user;
//		@SerializedName( "videoId")
//		public int videoId;
//		@SerializedName( "visit")
//		public RegionVisitContent visit;
	}

	public static class AdvertListsBean {
		/**
		 * player_id : 25
		 * advert_type : 14
		 * channel_id : 0
		 * advert_id : 81
		 * position_id : 2
		 */

		public int player_id;
		public int advert_type;
		public int channel_id;
		public int advert_id;
		public int position_id;
	}

	public static class HeaderTextBean {
		/**
		 * action : 7
		 * href : 0
		 * title : 全站排行榜
		 */

		public int action;
		public String href;
		public String title;
	}
}
