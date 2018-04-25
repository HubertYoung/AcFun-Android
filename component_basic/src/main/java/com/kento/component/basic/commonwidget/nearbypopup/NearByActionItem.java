//package com.kento.component.basic.commonwidget.nearbypopup;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//
///**
// * @author:Yang
// * @date:2017/8/24 17:44
// * @since:V1.0
// * @desc:ddframework.gent.common.commonwidget.nearbypopup
// * @param:功能描述：弹窗内部子类项（绘制标题和图标）
// */
//public class NearByActionItem {
//	//定义图片对象
//	public Drawable mDrawable;
//	//定义文本对象
//	public CharSequence mTitle;
//
//	public NearByActionItem( Drawable drawable, CharSequence title){
//		this.mDrawable = drawable;
//		this.mTitle = title;
//	}
//
//	public NearByActionItem( Context context, int titleId, int drawableId){
//		this.mTitle = context.getResources().getText(titleId);
//		this.mDrawable = context.getResources().getDrawable(drawableId);
//	}
//
//	public NearByActionItem( Context context, CharSequence title, int drawableId) {
//		this.mTitle = title;
//		this.mDrawable = drawableId == 0 ? null : context.getResources().getDrawable(drawableId);
//	}
//}
