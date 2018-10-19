package com.hubertyoung.component.acfunvideo.entity;

import com.alibaba.fastjson.annotation.JSONField;

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
	@JSONField(name = "id")
	public int albumID;
	@JSONField(name = "coverImageV")
	public String coverImageV;
	@JSONField(name = "title")
	public String title;
}
