package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

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
	@JSONField(name = "advert_id")
	public int advertId;
	@JSONField(name = "advert_type")
	public int advertType;
	@JSONField(name = "channel_id")
	public int channelId;
	@JSONField(name = "player_id")
	public long playerId;
	@JSONField(name = "position_id")
	public int positionId;
}
