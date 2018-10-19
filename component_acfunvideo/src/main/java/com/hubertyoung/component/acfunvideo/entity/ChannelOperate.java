package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

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
	@JSONField(name = "channelList" )
	public List< ChannelData > channelList;
	@JSONField(name = "next" )
	public int next;
	@JSONField(name = "operateList" )
	public List< ChannelActivity > operateList;

	public static class ChannelData {
		@JSONField(name = "id" )
		public int id;
		@JSONField(name = "img" )
		public String img;
		@JSONField(name = "name" )
		public String name;
	}

	public static class ChannelActivity {
		@JSONField(name = "action" )
		public int action;
		@JSONField(name = "activityStatus" )
		public int activityStatus;
		@JSONField(name = "activityTime" )
		public String activityTime;
		@JSONField(name = "href" )
		public String href;
		@JSONField(name = "img" )
		public String img;
		@JSONField(name = "title" )
		public String title;
	}
}
