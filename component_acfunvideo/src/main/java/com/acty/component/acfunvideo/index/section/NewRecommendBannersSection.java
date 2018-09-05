package com.acty.component.acfunvideo.index.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.acty.component.acfunvideo.entity.RegionBodyContent;
import com.acty.component.acfunvideo.entity.Regions;
import com.acty.component_acfunvideo.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;

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
public class NewRecommendBannersSection extends Section {
	private BaseActivity mActivity;
	private Regions regions;

	public NewRecommendBannersSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.item_region_single_banner )//
//				.headerResourceId( R.layout.widget_region_header_text )//
//				.footerResourceId( R.layout.widget_region_bottom_menu )//
				.build() );
		this.mActivity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		if ( regions != null ) {
			return regions.bodyContents == null ? 0 : 1;
		} else {
			return 0;
		}
	}

	@Override
	public int getSpanSizeLookup( int position ) {
		return 6;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new BannersViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		BannersViewHolder viewHolder = ( BannersViewHolder ) holder;
		if ( regions.bodyContents != null && regions.bodyContents.size() != 0 ) {
			RegionBodyContent bodyContent = regions.bodyContents.get( 0 );
			if ( bodyContent.ad == 1 ) {
				viewHolder.ad.setVisibility( View.GONE );
			} else {
				viewHolder.ad.setVisibility( View.GONE );
			}
			if ( bodyContent.images == null || bodyContent.images.size() <= 0 ) {
				ImageLoaderUtil.loadResourceImage( R.color.transparent, viewHolder.ivBanner1 );
			} else {
				ImageLoaderUtil.loadNetImage( bodyContent.images.get( 0 ), viewHolder.ivBanner1 );
			}
			viewHolder.ivBanner1.setOnClickListener( new View.OnClickListener() {
				public void onClick( View view ) {
//						StringBuilder stringBuilder = new StringBuilder();
//						stringBuilder.append(i3 + 1);
//						stringBuilder.append(" clicked");
//						if (bodyContent != null) {
//							if (bodyContent.ad == 1) {
//								SensorsAnalyticsUtil.a(HomeListAdapter.this.aq, bodyContent.title, HomeListAdapter.this.ar);
//							}
//							SensorsAnalyticsUtil.a(bodyContent.title, bodyContent.actionId);
//						}
//						HomeListAdapter.this.a(KanasConstants.aQ, i2, bodyContent.ad == 1, bodyContent.title, bodyContent.actionId, bodyContent.contentId);
//						HomeListAdapter.this.f(bodyContent);
				}
			} );
		} else {
			if ( regions.advertLists == null || regions.advertLists.size() <= 0 ) {
				// TODO: 2018/9/5 广告处理
			} else {
				viewHolder.ivBanner1.setVisibility( View.GONE );
				viewHolder.divider.setVisibility( View.GONE );
				viewHolder.ad.setVisibility( View.GONE );
				viewHolder.itemView.setVisibility( View.GONE );
			}
		}
	}

	public void setRegions( Regions regions ) {
		this.regions = regions;
	}

	static class BannersViewHolder extends RecyclerView.ViewHolder {
		public ImageView ad;
		public View divider;
		public SimpleDraweeView ivBanner1;

		BannersViewHolder( View view ) {
			super( view );
			this.ivBanner1 = ( SimpleDraweeView ) view.findViewById( R.id.region_item_banner_1 );
			this.ad = ( ImageView ) view.findViewById( R.id.iv_ad );
			this.divider = ( View ) view.findViewById( R.id.divider );
		}
	}
}
