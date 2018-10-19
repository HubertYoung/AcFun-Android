package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:23
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class BangumiVideo {
	@JSONField(name ="bangumiId")
	public int bid;
	@JSONField(name ="danmakuId")
	public String danmakuID;
	public int order;
	@JSONField(name ="sourceId")
	public String sid;
	@JSONField(name ="sort")
	public int sort;
	@JSONField(name ="startTime")
	public long startTime;
	@JSONField(name ="sourceType")
	public String stype;
	@JSONField(name ="title")
	public String title;
	@JSONField(name ="type")
	public String type;
	@JSONField(name ="url")
	public String url;
	@JSONField(name ="id")
	public int vid;

}
