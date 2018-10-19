package com.hubertyoung.component_acfunarticle.entity;

import com.alibaba.fastjson.annotation.JSONField;

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
	@JSONField(name ="channel")
	public RankChannel channel;
	@JSONField(name ="time")
	public long time;
	@JSONField(name ="user")
	public RankUser user;
	@JSONField(name ="visit")
	public Visit visit;
}