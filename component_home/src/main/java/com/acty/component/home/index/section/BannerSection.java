package com.acty.component.home.index.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acty.component_banner.banner.BannerEntity;
import com.acty.component_banner.banner.BannerView;
import com.acty.component_home.R;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;

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

	public BannerSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_banner_body ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null || data.isEmpty() ? 0 : 1;
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
	}

	public void setBannerList( List< BannerEntity > bannerList ) {
		this.data = bannerList;
	}

	static class BannerViewHolder extends RecyclerView.ViewHolder {
		View view;
		BannerView mBvHomeIndex;

		BannerViewHolder( View view ) {
			super( view );
			this.view = view;
			this.mBvHomeIndex = ( BannerView ) view.findViewById( R.id.bv_home_index );
		}
	}
}
