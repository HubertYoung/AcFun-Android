package com.hubertyoung.component.acfunvideo.index.section;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.component.acfunvideo.entity.ChannelOperate;
import com.hubertyoung.component_acfunvideo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/16 15:33
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.index.section
 */
public class ViewActivitySection extends Section {
	private BaseActivity mActivity;
	private List< ChannelOperate.ChannelActivity > data;

	public ViewActivitySection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.item_channel_activity_banner )//
				.headerResourceId( R.layout.item_channel_title )//
//				.footerResourceId( R.layout.widget_region_bottom_menu )//
				.build() ); this.mActivity = activity;
	}

	@Override
	public RecyclerView.ViewHolder getHeaderViewHolder( View view ) {
		return super.getHeaderViewHolder( view );
	}

	@Override
	public int getSpanSizeLookup( int position ) {
		return 4;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null ? 0 : data.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new ViewActivityViewHolder(view);
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		ViewActivityViewHolder viewActivityHolder = (ViewActivityViewHolder) holder;
		ChannelOperate.ChannelActivity channelActivity = data.get( position );
		ImageLoaderUtil.loadNetImage(  channelActivity.img, viewActivityHolder.imageView);
			viewActivityHolder.name.setText(channelActivity.title);
			viewActivityHolder.time.setText(channelActivity.activityTime);

			int backgroundResource = R.drawable.shape_channel_operate_start_bg;
			int text = R.string.channel_operate_start_text;
			if (channelActivity.activityStatus == 0) {
				backgroundResource = R.drawable.shape_channel_operate_begin_bg;
				text = R.string.channel_operate_begin_text;
			} else if (channelActivity.activityStatus == 2) {
				backgroundResource = R.drawable.shape_channel_operate_end_bg;
				text = R.string.channel_operate_end_text;
			}

			viewActivityHolder.status.setBackgroundResource(backgroundResource);
			viewActivityHolder.status.setText(text);
	}

	public void setOperateList( List< ChannelOperate.ChannelActivity > operateList ) {
		this.data = operateList;
	}

	public void addOperateList( List< ChannelOperate.ChannelActivity > operateList ) {
		if ( this.data == null ) data = new ArrayList<>(  );
		data.addAll( operateList );
	}

	static class ViewActivityViewHolder extends RecyclerView.ViewHolder {
		SimpleDraweeView imageView;
		TextView status;
		TextView time;
		TextView name;

		ViewActivityViewHolder( View view) {
			super(view);
			imageView = ( SimpleDraweeView ) view.findViewById( R.id.item_channel_activity_banner_view );
			status = ( TextView ) view.findViewById( R.id.item_channel_activity_banner_status );
			time = ( TextView ) view.findViewById( R.id.item_channel_activity_banner_time );
			name = ( TextView ) view.findViewById( R.id.item_channel_activity_banner_name );
		}
	}
}
