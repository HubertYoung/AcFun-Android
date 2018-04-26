package com.kento.component_banner.banner;

/**
 * @author:Yang
 * @date:2017/8/7 14:52
 * @since:v1.0
 * @desc:ddframework.gent.common.commonwidget.banner
 * @param:Banner模型类
 */
public class BannerEntity {
	public String Pic;
	public String title;
	public String dataJson;
	public String url;
	public String videoUrl;
	public int type;//0=图片or1=视频

	public BannerEntity() {
	}
	public BannerEntity(String Pic,String url) {
		this.Pic = Pic;
		this.url = url;
	}

	public BannerEntity( String url, String title, String Pic, String dataJson, int type ) {

		this.url = url;
		this.title = title;
		this.Pic = Pic;
		this.dataJson = dataJson;
		this.type = type;
	}

	@Override
	public String toString() {
		return "BannerEntity{" + "Pic='" + Pic + '\'' + ", title='" + title + '\'' + ", dataJson='" + dataJson + '\'' + ", url='" + url + '\'' + ", " +
				"videoUrl='" + videoUrl + '\'' + ", type=" + type + '}';
	}
}
