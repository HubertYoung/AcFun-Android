package com.hubertyoung.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 23:26
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.entity
 */
public class VideoDetailAlbum {
	@SerializedName("id")
	public int albumID;
	@SerializedName("coverImageV")
	public String coverImageV;
	@SerializedName("title")
	public String title;
}
