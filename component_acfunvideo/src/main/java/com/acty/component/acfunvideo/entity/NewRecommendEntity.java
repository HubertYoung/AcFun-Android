package com.acty.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		 * img : ["http://imgs.aixifan.com/content/2018_09_03/1535950354.jpg"]
		 * channel : {"name":"美食","id":89}
		 * action : 1
		 * href : 4561672
		 * time : 1535950377000
		 * visit : {"banana":153,"stows":8,"comments":26,"danmakuSize":116,"views":16861}
		 * title : 【品城记】顺德︱跟小姐姐去吃猪杂粥，想吃到新鲜猪杂要靠抢的！
		 * user : {"img":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201702/28151925lez1dr3x.jpg","name":"品城记Video","id":10725427}
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
			 * name : 美食
			 * id : 89
			 */

			public String name;
			public int id;
		}

		public static class VisitBean {
			/**
			 * banana : 153
			 * stows : 8
			 * comments : 26
			 * danmakuSize : 116
			 * views : 16861
			 */

			public int banana;
			public int stows;
			public int comments;
			public int danmakuSize;
			public int views;
		}

		public static class UserBean {
			/**
			 * img : http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201702/28151925lez1dr3x.jpg
			 * name : 品城记Video
			 * id : 10725427
			 */

			public String img;
			public String name;
			public int id;
		}
	}

	public static class BodyContentsBean {
		@SerializedName("avgScore")
		public float mAvgScore;
		@SerializedName("id")
		public int mBangumiId;
		@SerializedName("cover")
		public String mCover;
		@SerializedName("coverHorizontal")
		public String mCoverHorizontal;
		@SerializedName("coverVertical")
		public String mCoverVertical;
		@SerializedName("hasScore")
		public int mHasScore;
		@SerializedName("intro")
		public String mIntro;
		// TODO: 2018/9/4 这里
		@SerializedName("lastUpdateTime")
		public Date mLastUpdateTime;
		@SerializedName("lastVideoName")
		public String mLastVideoName;
		@SerializedName("score1Count")
		public int mScore1Count;
		@SerializedName("score2Count")
		public int mScore2Count;
		@SerializedName("score3Count")
		public int mScore3Count;
		@SerializedName("score4Count")
		public int mScore4Count;
		@SerializedName("score5Count")
		public int mScore5Count;
		@SerializedName("status")
		public int mStatus;
		@SerializedName("title")
		public String mTitle;
		@SerializedName("totalCount")
		public int mTotalCount;
		@SerializedName("totalScore")
		public int mTotalScore;
		@SerializedName("type")
		public int mType;
		@SerializedName("stows")
		public int stows;
		@SerializedName("userScore")
		public int userScore;
		@SerializedName("week")
		public int week;

		public String getAbbrTitle() {
			if (this.mTitle == null) {
				return "";
			}
			Matcher matcher = Pattern.compile("【.*?】").matcher(this.mTitle);
			return matcher.find() ? matcher.replaceFirst("") : this.mTitle;
		}
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

	@Override
	public String toString() {
		return "NewRecommendEntity{" + "schema='" + schema + '\'' + ", img='" + img + '\'' + ", showChange=" + showChange + ", show=" + show + ", id=" + id + ", title='" + title + '\'' + ", " +
				"maxCount=" + maxCount + ", bottomText=" + bottomText + ", topContent=" + topContent + ", channel=" + channel + ", bodyContents=" + bodyContents + ", advertLists=" + advertLists + "," +
				" headerText=" + headerText + '}';
	}
}
