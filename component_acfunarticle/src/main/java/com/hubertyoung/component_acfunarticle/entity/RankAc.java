package com.hubertyoung.component_acfunarticle.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/18 15:15
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunarticle.entity
 */
public class RankAc extends Rank {
	@JSONField(name = "list")
	public List<RankContent> list;

}

