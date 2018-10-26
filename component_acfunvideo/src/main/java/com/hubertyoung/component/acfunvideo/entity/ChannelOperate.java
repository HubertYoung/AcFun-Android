package com.hubertyoung.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/7 16:56
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class ChannelOperate {
	@SerializedName("channelList" )
	public List< ChannelData > channelList;
	@SerializedName("next" )
	public int next;
	@SerializedName("operateList" )
	public List< ChannelActivity > operateList;

	public static class ChannelData {
		@SerializedName("id" )
		public int id;
		@SerializedName("img" )
		public String img;
		@SerializedName("name" )
		public String name;
	}

	public static class ChannelActivity {
		@SerializedName("action" )
		public int action;
		@SerializedName("activityStatus" )
		public int activityStatus;
		@SerializedName("activityTime" )
		public String activityTime;
		@SerializedName("href" )
		public String href;
		@SerializedName("img" )
		public String img;
		@SerializedName("title" )
		public String title;
	}
}
