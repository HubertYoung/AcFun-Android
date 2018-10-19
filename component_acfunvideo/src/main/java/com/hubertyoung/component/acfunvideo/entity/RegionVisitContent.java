package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:29
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class RegionVisitContent {
	@JSONField(name = "banana")
	public int banana;
	@JSONField(name = "comments")
	public int comments;
	@JSONField(name = "danmakuSize")
	public int danmakuSize;
	@JSONField(name = "show")
	public String show;
	@JSONField(name = "stows")
	public int stows;
	@JSONField(name = "views")
	public int views;
}
