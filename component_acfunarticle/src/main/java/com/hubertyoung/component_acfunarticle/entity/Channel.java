package com.hubertyoung.component_acfunarticle.entity;

import com.google.gson.annotations.SerializedName;

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
	@SerializedName("article" )
	public List< ServerChannel > article;
	@SerializedName("video" )
	public List< ServerChannel > video;

}
