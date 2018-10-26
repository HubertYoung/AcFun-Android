package com.hubertyoung.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

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
	@SerializedName("comments")
	public int comments;
	@SerializedName("danmakuSize")
	public int danmakuSize;
	@SerializedName("goldBanana")
	public int goldBanana;
	@SerializedName("goldBananaVoters")
	public int goldBananaVoters;
	@SerializedName("score")
	public int score;
	@SerializedName("stows")
	public int stows;
	@SerializedName("throwBananas")
	public int throwBananas;
	@SerializedName("ups")
	public int ups;
	@SerializedName("views")
	public int views;
}
