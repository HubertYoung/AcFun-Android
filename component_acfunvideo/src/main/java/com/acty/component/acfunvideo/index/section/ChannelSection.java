package com.acty.component.acfunvideo.index.section;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acty.component.acfunvideo.entity.ChannelOperate;
import com.acty.component_acfunvideo.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.DisplayUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/5 11:01
 * @since:V$VERSION
 * @desc:com.acty.component.acfunvideo.index.section
 */
public class ChannelSection extends Section {
	private BaseActivity mActivity;
	private List< ChannelOperate.ChannelData > data;

	public ChannelSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.item_channel )//
//				.headerResourceId( R.layout.widget_region_header_text )//
//				.footerResourceId( R.layout.widget_region_bottom_menu )//
				.build() );
		this.mActivity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		if ( data != null ) {
			return data.size();
		} else {
			return 0;
		}
	}

	@Override
	public int getSpanSizeLookup( int position ) {
		return 1;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new ViewChannelHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		ViewChannelHolder viewChannelHolder = ( ViewChannelHolder ) holder;
		ChannelOperate.ChannelData channelData = data.get( position );
		if ( !( data == null || TextUtils.isEmpty( channelData.img ) ) ) {
			ImageLoaderUtil.loadNetImage( channelData.img, viewChannelHolder.mImageView, false, null );
		}
		viewChannelHolder.mTextView.setText( channelData.name );
	}

	public void setChannelList( List< ChannelOperate.ChannelData > channelList ) {
		this.data = channelList;
	}

	static class ViewChannelHolder extends RecyclerView.ViewHolder {
		public SimpleDraweeView mImageView;
		public TextView mTextView;

		ViewChannelHolder( View view ) {
			super( view );
			mImageView = ( SimpleDraweeView ) view.findViewById( R.id.item_channel_img );
			mTextView = ( TextView ) view.findViewById( R.id.item_channel_text );
			int width = ( int ) ( DisplayUtil.getScreenWidth( view.getContext() ) * 0.106666f );
			LinearLayout.LayoutParams layoutParams = ( LinearLayout.LayoutParams ) mImageView.getLayoutParams();
			layoutParams.width = width;
			layoutParams.height = width;
			mImageView.setLayoutParams( layoutParams );
		}
	}
}
