package com.hubertyoung.component.acfunvideo.index.section.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hubertyoung.component_acfunvideo.R;

import android.support.v7.widget.RecyclerView;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/5 12:15
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.index.section
 */
public class BottomMenuViewHolder extends RecyclerView.ViewHolder {
	public TextView bottom;
	public TextView bottomChange;
	public View bottomChangeLayout;
	public View bottomMoreLayout;
	public View vBottomLine;

	public BottomMenuViewHolder( View view ) {
		super( view );
		this.bottomChange = ( TextView ) view.findViewById( R.id.region_menu_change );
		this.bottomChangeLayout = ( LinearLayout ) view.findViewById( R.id.region_menu_change_layout );
		this.bottom = ( TextView ) view.findViewById( R.id.region_menu_more );
		this.bottomMoreLayout = ( LinearLayout ) view.findViewById( R.id.region_menu_more_layout );
		this.vBottomLine = ( View ) view.findViewById( R.id.v_bottom_line );
	}
}
