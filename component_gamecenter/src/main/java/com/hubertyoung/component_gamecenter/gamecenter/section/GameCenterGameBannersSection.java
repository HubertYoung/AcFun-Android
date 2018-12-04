package com.hubertyoung.component_gamecenter.gamecenter.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.entity.Regions;
import com.hubertyoung.common.entity.RegionsContent;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.component_gamecenter.R;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2018/12/4 16:39
 * @since:
 * @see GameCenterGameBannersSection
 */
public class GameCenterGameBannersSection extends StatelessSection {
	private BaseActivity mActivity;
	private Regions regions;

	public GameCenterGameBannersSection( BaseActivity activity ) {
		super( SectionParameters.builder().itemResourceId( R.layout.item_region_game_center_anli )//
//				.headerResourceId( R.layout.widget_region_header_text )//
//				.footerResourceId( R.layout.widget_region_bottom_menu )//
				.build() );
		this.mActivity = activity;
	}
	public int getSpanSizeLookup( int position ) {
		return 6;
	}

	@Override
	public int getContentItemsTotal() {
		return regions == null || regions.contents == null || regions.contents.isEmpty() ? 0 : 1;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new GameBannersViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		GameBannersViewHolder viewHolderAnli = ( GameBannersViewHolder ) holder;
		RegionsContent regionsContent2 = regions.contents.get( 0 );
		ImageLoaderUtil.loadNetImage( regionsContent2.image, viewHolderAnli.img );
		viewHolderAnli.itemView.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
//				GameCenterAdapter.this.a(KanasConstants.bP, 1, regionsContent2.isAd, regionsContent2.title, regionsContent2.actionId, regionsContent2.url);
//				Bundle bundle = new Bundle();
//				String str = GameDetailActivity.g;
//				StringBuilder stringBuilder = new StringBuilder();
//				stringBuilder.append(Constants.ANALYTICS_RESOURCE_GC_BOTTOM_BANNER);
//				stringBuilder.append(regionsContent2.resourceId);
//				bundle.putString(str, stringBuilder.toString());
//				Utils.a(GameCenterAdapter.this.al, regionsContent2, bundle);
//				str = Utils.a(regionsContent2);
//				stringBuilder = new StringBuilder();
//				stringBuilder.append(Constants.ANALYTICS_RESOURCE_GC_BOTTOM_BANNER);
//				stringBuilder.append(regionsContent2.resourceId);
//				AnalyticsUtil.e("Android", str, stringBuilder.toString());
			}
		} );
	}

	public void setRegions( Regions regions ) {
		this.regions = regions;
	}

	static class GameBannersViewHolder extends RecyclerView.ViewHolder {
		SimpleDraweeView img;

		GameBannersViewHolder( View view ) {
			super( view );
			this.img = ( SimpleDraweeView ) view.findViewById( R.id.item_game_center_anli_img );
		}
	}
}
