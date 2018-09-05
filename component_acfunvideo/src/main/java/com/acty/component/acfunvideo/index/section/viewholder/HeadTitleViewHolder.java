package com.acty.component.acfunvideo.index.section.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acty.component_acfunvideo.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/5 11:03
 * @since:V$VERSION
 * @desc:com.acty.component.acfunvideo.index.section
 */
public class HeadTitleViewHolder extends RecyclerView.ViewHolder {
	public TextView dotOne;
	public TextView dotTwo;
	public SimpleDraweeView leftIndicator;
	public LinearLayout leftLayout;
	public SimpleDraweeView leftNoPic;
	public LinearLayout rightMenu;
	public TextView rightMenuText1;
	public TextView rightMenuText2;
	public TextView rightMenuText3;
	public TextView title;

	public HeadTitleViewHolder( View view ) {
		super( view );
		leftIndicator = ( SimpleDraweeView ) view.findViewById( R.id.region_top_left_indicator );
		leftNoPic = ( SimpleDraweeView ) view.findViewById( R.id.region_top_left_no_pic );
		title = ( TextView ) view.findViewById( R.id.region_top_title );
		leftLayout = ( LinearLayout ) view.findViewById( R.id.region_top_left_layout );
		rightMenuText3 = ( TextView ) view.findViewById( R.id.header_text3 );
		dotTwo = ( TextView ) view.findViewById( R.id.dot_two );
		rightMenuText2 = ( TextView ) view.findViewById( R.id.header_text2 );
		dotOne = ( TextView ) view.findViewById( R.id.dot_one );
		rightMenuText1 = ( TextView ) view.findViewById( R.id.header_text1 );
		rightMenu = ( LinearLayout ) view.findViewById( R.id.region_top_right_menu );
	}
}
