package com.hubertyoung.component_acfunarticle.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/18 15:18
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunarticle.entity
 */
public class Visit {
	@JSONField(name ="banana")
	public int banana;
	@JSONField(name ="comments")
	public int comments;
	@JSONField(name ="danmakuSize")
	public int danmakuSize;
	@JSONField(name ="stows")
	public int stows;
	@JSONField(name ="views")
	public int views;
}
