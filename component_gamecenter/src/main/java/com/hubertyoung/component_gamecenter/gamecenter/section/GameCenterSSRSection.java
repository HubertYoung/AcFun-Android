package com.hubertyoung.component_gamecenter.gamecenter.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.entity.Regions;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.component_gamecenter.R;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2018/12/4 14:53
 * @since:V5.7.0
 * @see GameCenterSSRSection
 */
public class GameCenterSSRSection extends StatelessSection {

	private BaseActivity mActivity;
	private Regions regions;

	public GameCenterSSRSection( BaseActivity activity ) {
		super( SectionParameters.builder()//
				.itemResourceId( R.layout.item_region_game_center_ssr )//
//				.headerResourceId( R.layout.widget_region_header_text )//
//				.footerResourceId( R.layout.widget_region_bottom_menu )//
				.build() );
		this.mActivity = activity;
	}

	@Override
	public int getSpanSizeLookup( int position ) {
		return 6;
	}

	@Override
	public int getContentItemsTotal() {
		if ( regions != null && !regions.contents.isEmpty() ) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new SSRViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		SSRViewHolder viewHolder = ( SSRViewHolder ) holder;
		ImageLoaderUtil.loadNetImage( regions.contents.get( 0 ).image, viewHolder.img );
		viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				ToastUtil.showWarning( regions.toString() );
			}
		} );
	}

	public void setRegions( Regions regions ) {
		this.regions = regions;
	}

	static class SSRViewHolder extends RecyclerView.ViewHolder {
		SimpleDraweeView img;

		SSRViewHolder( View view ) {
			super( view );
			this.img = ( SimpleDraweeView ) view.findViewById( R.id.item_game_center_ssr_img );
		}
	}
}
