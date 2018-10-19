package com.hubertyoung.component_acfunarticle.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/18 15:17
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunarticle.entity
 */
public class RankContentBase {
	@JSONField(name = "action" )
	public int action;
	@JSONField(name = "href" )
	public String href;
	@JSONField(name = "img" )
	public List< String > img;
	@JSONField(name = "title" )
	public String title;

	public String getFirstImg() {
		return ( this.img == null || this.img.size() <= 0 ) ? "" : ( String ) this.img.get( 0 );
	}
}
