package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:18
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class RegionsType {
	@JSONField(name ="id")
	public int id;
	@JSONField(name ="img")
	public String img;
	@JSONField(name ="intro")
	public String intro;
	@JSONField(name ="name")
	public String name;
	@JSONField(name ="value")
	public String value;
	public int viewType;
}
