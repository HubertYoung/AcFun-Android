package com.hubertyoung.component_acfunarticle.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/17 13:57
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunarticle.entity
 */
public class ArticleRecommendEntity {

	public String schema;
	public String img;
	public int showChange;
	public String requestid;
	public String groupid;
	public int show;
	public int id;
	public String title;
	public int maxCount;
	public List< BodyContentsBean > bodyContents;
	public List< AdvertLists > advertLists;

	public static class BodyContentsBean {
		/**
		 * img : ["http://imgs.aixifan.com/test-douw/2018_9_14/1539491109614.jpg"]
		 * requestid : MTUzOTc1NDk3ODI1NF85OTg
		 * groupid : MTUzOTc1NDk3ODI1NF85OTg_0_0
		 * action : 10
		 * href : 4640076
		 * title : 【漫画】月影特工 RELEASE THE SPYCE #00-06
		 */

		public String requestid;
		public String groupid;
		@SerializedName( "action")
		public int actionId;
		@SerializedName("href")
		public String contentId;
		public String title;
		@SerializedName("img")
		public List<String> images;
	}
}
