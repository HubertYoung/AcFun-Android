package com.hubertyoung.component_acfunarticle.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/16 18:27
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunarticle.entity
 */
public class Channel {
	@JSONField(name = "article" )
	public List< ServerChannel > article;
	@JSONField(name = "video" )
	public List< ServerChannel > video;

}
