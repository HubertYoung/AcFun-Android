package com.hubertyoung.aggregation.dialog;

import android.graphics.drawable.Drawable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/13 16:03
 * @since:V$VERSION
 * @desc:aggregation
 */
public class BottomShareEntity {
	public String title;
	public String platform;
	public Drawable icon;

	public BottomShareEntity(String platform, String title, Drawable icon ) {
		this.platform = platform;
		this.title = title;
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "BottomShareEntity{" + "title='" + title + '\'' + ", platform='" + platform + '\'' + ", icon=" + icon + '}';
	}
}
