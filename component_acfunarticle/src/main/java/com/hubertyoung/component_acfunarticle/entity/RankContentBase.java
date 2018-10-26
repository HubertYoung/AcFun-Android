package com.hubertyoung.component_acfunarticle.entity;

import com.google.gson.annotations.SerializedName;

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
	@SerializedName("action" )
	public int action;
	@SerializedName("href" )
	public String href;
	@SerializedName("img" )
	public List< String > img;
	@SerializedName("title" )
	public String title;

	public String getFirstImg() {
		return ( this.img == null || this.img.size() <= 0 ) ? "" : ( String ) this.img.get( 0 );
	}
}
