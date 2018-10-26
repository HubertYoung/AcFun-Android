package com.hubertyoung.component_acfunarticle.entity;

import com.google.gson.annotations.SerializedName;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/18 15:16
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunarticle.entity
 */
public class RankContent extends RankContentBase {
	@SerializedName("channel")
	public RankChannel channel;
	@SerializedName("time")
	public long time;
	@SerializedName("user")
	public RankUser user;
	@SerializedName("visit")
	public Visit visit;
}