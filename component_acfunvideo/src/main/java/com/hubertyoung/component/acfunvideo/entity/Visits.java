package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:28
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class Visits {
	@JSONField(name = "comments")
	public int comments;
	@JSONField(name = "danmakuSize")
	public int danmakuSize;
	@JSONField(name = "goldBanana")
	public int goldBanana;
	@JSONField(name = "goldBananaVoters")
	public int goldBananaVoters;
	@JSONField(name = "score")
	public int score;
	@JSONField(name = "stows")
	public int stows;
	@JSONField(name = "throwBananas")
	public int throwBananas;
	@JSONField(name = "ups")
	public int ups;
	@JSONField(name = "views")
	public int views;
}
