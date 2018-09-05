package com.acty.component.acfunvideo.index.section.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acty.component_acfunvideo.R;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/5 12:15
 * @since:V$VERSION
 * @desc:com.acty.component.acfunvideo.index.section
 */
public class BottomMenuViewHolder extends RecyclerView.ViewHolder {
	public TextView bottom;
	public TextView bottomChange;
	public View bottomChangeLayout;
	public View bottomMoreLayout;

	public BottomMenuViewHolder( View view ) {
		super( view );
		this.bottomChange = ( TextView ) view.findViewById( R.id.region_menu_change );
		this.bottomChangeLayout = ( LinearLayout ) view.findViewById( R.id.region_menu_change_layout );
		this.bottom = ( TextView ) view.findViewById( R.id.region_menu_more );
		this.bottomMoreLayout = ( LinearLayout ) view.findViewById( R.id.region_menu_more_layout );
	}
}
