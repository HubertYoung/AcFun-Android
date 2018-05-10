package com.acty.component.home.index.section;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acty.component.home.entity.HomeIndexEntity;
import com.acty.component_banner.banner.BannerEntity;
import com.acty.component_banner.banner.BannerView;
import com.acty.component_dynamicsoreview.dynamicsoreview.DynamicSoreView;
import com.acty.component_dynamicsoreview.dynamicsoreview.Interface.IDynamicSore;
import com.acty.component_home.R;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * <br>
 * function:广告section
 * <p>
 *
 * @author:Yang
 * @date:2018/5/10 6:02 PM
 * @since:V$VERSION
 * @desc:com.acty.component.home.index.section
 */
public class BannerSection extends Section {
	private BaseActivity activity;
	private List< BannerEntity > data;
	private List< HomeIndexEntity.ChannelBean > channelData;

	public BannerSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_banner_body ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		if ( data == null || data.isEmpty() || channelData == null || channelData.isEmpty() ) {
			return 0;
		}else {
			return 1;
		}
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new BannerViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		BannerViewHolder viewHolder = ( BannerViewHolder ) holder;
		viewHolder.mBvHomeIndex.instance( activity );
		viewHolder.mBvHomeIndex.setCenter( false );
		viewHolder.mBvHomeIndex.delayTime( 3 );
		viewHolder.mBvHomeIndex.build( data );

		initDynamicSoreView( viewHolder, channelData );
	}

	private void initDynamicSoreView( BannerViewHolder viewHolder, List< HomeIndexEntity.ChannelBean > channelData ) {
		viewHolder.mDsHomeIndex.setiDynamicSore( new IDynamicSore< HomeIndexEntity.ChannelBean >() {

			@Override
			public void setGridView( View view, int type, List< HomeIndexEntity.ChannelBean > data ) {
				GridLayoutManager manager = new GridLayoutManager( activity, 9 );
				viewHolder.mDsHomeIndex.setNumColumns( manager, 9 );

				RecyclerView rvBody = ( RecyclerView ) view.findViewById( R.id.rv_body );
				rvBody.setPadding( 0, AutoUtils.getPercentHeightSizeBigger( 20 ), 0, AutoUtils.getPercentHeightSizeBigger( 20 ) );
				rvBody.setHasFixedSize( true );

				rvBody.setNestedScrollingEnabled( false );
				SectionedRecyclerViewAdapter menuAdapter = new SectionedRecyclerViewAdapter();
				DynamicSection cardShareMenuBodySection = new DynamicSection( activity, data );
				menuAdapter.addSection( cardShareMenuBodySection );
				rvBody.setAdapter( menuAdapter );
				rvBody.setLayoutManager( manager );
				menuAdapter.notifyDataSetChanged();
				cardShareMenuBodySection.setOnItemClickListener( new DynamicSection.OnItemClickListener() {
					@Override
					public void onitemClick( View v, String Pic, String title ) {
						ToastUtil.showSuccess( title );
					}
				} );
			}
		} );
		viewHolder.mDsHomeIndex.setGridView( R.layout.home_item_dynamicsoreview_body )
				.init( channelData );
	}

	public void setBannerList( List< BannerEntity > bannerList ) {
		this.data = bannerList;
	}

	public void setChannelList( List< HomeIndexEntity.ChannelBean > channel ) {
		this.channelData = channel;
	}

	static class BannerViewHolder extends RecyclerView.ViewHolder {
		View view;
		BannerView mBvHomeIndex;
		DynamicSoreView mDsHomeIndex;

		BannerViewHolder( View view ) {
			super( view );
			this.view = view;
			this.mBvHomeIndex = view.findViewById( R.id.bv_home_index );
			this.mDsHomeIndex = view.findViewById( R.id.ds_home_index );
		}
	}
}
