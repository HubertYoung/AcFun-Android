package com.hubertyoung.component.acfunvideo.index.section.viewholder;

import android.view.View;

import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.utils.display.DisplayUtil;
import com.hubertyoung.component.acfunvideo.entity.Regions;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/5 12:19
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.index.section
 */
public class BindBottomMenuViewHolder{
	private BottomMenuViewHolder viewHolder;
	private BaseActivity mActivity;

	public BindBottomMenuViewHolder( BaseActivity activity, BottomMenuViewHolder viewHolder ) {
		this.mActivity = activity;
		this.viewHolder = viewHolder;
	}

	public void viewBindData( int contentItemsTotal, Regions regions ) {
		if (regions.bottomText == null) {
			viewHolder.bottomMoreLayout.setVisibility(View.GONE);
		} else {
			viewHolder.bottom.setText(regions.bottomText.title);
			viewHolder.bottomMoreLayout.setVisibility(View.VISIBLE);
		}
		if (regions.showChange == 1) {
			viewHolder.bottomChangeLayout.setVisibility(View.VISIBLE);
		} else {
			viewHolder.bottomChangeLayout.setVisibility(View.GONE);
		}
		viewHolder.itemView.setPadding( 0,//
				DisplayUtil.dip2px( 10 ),//
				0,//
				0 );
		viewHolder.bottomChangeLayout.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {

			}
		} );
		viewHolder.bottom.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {

			}
		} );
	}
}
