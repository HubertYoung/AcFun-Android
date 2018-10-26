package com.hubertyoung.component_acfunarticle.entity;

import com.google.gson.annotations.SerializedName;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:14
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class AdvertLists {
	@SerializedName("advert_id")
	public int advertId;
	@SerializedName("advert_type")
	public int advertType;
	@SerializedName("channel_id")
	public int channelId;
	@SerializedName("player_id")
	public long playerId;
	@SerializedName("position_id")
	public int positionId;
}
