package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:15
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class RegionHeaderAndBottomContent {
	@JSONField(name ="action")
	public int actionId;
	@JSONField(name ="ad")
	public int ad;
	@JSONField(name ="color")
	public String color;
	@JSONField(name ="href")
	public String contentId;
	@JSONField(name ="img")
	public String image;
	@JSONField(name ="title")
	public String title;
}
