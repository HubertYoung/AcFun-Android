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
public class RankUser {
	@JSONField(name = "id")
	public int id;
	@JSONField(name = "img")
	public String img;
	@JSONField(name = "name")
	public String name;
}
