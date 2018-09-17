package com.hubertyoung.component.acfunvideo.index.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hubertyoung.component.acfunvideo.entity.AdvertLists;
import com.hubertyoung.component.acfunvideo.entity.RegionBodyContent;
import com.hubertyoung.component.acfunvideo.entity.Regions;
import com.hubertyoung.component_acfunvideo.R;
import com.hubertyoung.component_banner.banner.BannerEntity;
import com.hubertyoung.component_banner.banner.BannerView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.utils.DisplayUtil;
import com.hubertyoung.common.utils.Utils;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/5 11:01
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.index.section
 */
public class NewRecommendCarouselsSection extends Section {
	private BaseActivity mActivity;
	private Regions regions;

	public NewRecommendCarouselsSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.item_region_slide_banner )//
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
		return new CarouselsViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		CarouselsViewHolder viewHolderSlider = ( CarouselsViewHolder ) holder;
		if ( ( regions.advertLists != null && regions.advertLists.size() != 0 ) || ( regions.bodyContents != null && regions.bodyContents.size() != 0 ) ) {

			ArrayList< BannerEntity > list = new ArrayList<>();
			for (RegionBodyContent bodyContent : regions.bodyContents) {
				list.add( new BannerEntity( "", bodyContent.title, bodyContent.images.get( 0 ), "", 0 ) );
			}
			viewHolderSlider.sliderLayout.instance( mActivity );
			viewHolderSlider.sliderLayout.setCenter( false );
			viewHolderSlider.sliderLayout.delayTime( 3 );
			viewHolderSlider.sliderLayout.build( list );
			//TODO 广告配置
			AdvertLists advertLists = regions.advertLists.get( 0 );
			HashMap< String, String > advert = Utils.initAdvert( 1, advertLists.advertId, advertLists.playerId );
		}
	}

	public void setRegions( Regions regions ) {
		this.regions = regions;
	}


	static class CarouselsViewHolder extends RecyclerView.ViewHolder {
		public ImageView ad;
		public ImageView announcementClose;
		public View announcementLayout;
		public TextView announcementText;
		public LinearLayout rootLayout;
		public BannerView sliderLayout;

		CarouselsViewHolder( View view ) {
			super( view );
			sliderLayout = ( BannerView ) view.findViewById( R.id.region_item_slider );
			ad = ( ImageView ) view.findViewById( R.id.iv_ad );
			announcementText = ( TextView ) view.findViewById( R.id.home_announcement_text );
			announcementClose = ( ImageView ) view.findViewById( R.id.home_announcement_close );
			announcementLayout = ( LinearLayout ) view.findViewById( R.id.home_announcement_layout );
			initBannerView( sliderLayout );
		}

		private void initBannerView( BannerView sliderLayout ) {
			ViewGroup.LayoutParams layoutParams = ( FrameLayout.LayoutParams ) this.sliderLayout.getLayoutParams();
			if ( layoutParams == null || layoutParams.width <= 0 ) {
				int widgetWidth = DisplayUtil.getScreenWidth( sliderLayout.getContext() );
				layoutParams = new FrameLayout.LayoutParams( widgetWidth, ( int ) ( widgetWidth / 2.0833333f ) );
			} else {
				layoutParams.height = ( int ) ( layoutParams.width / 2.0833333f );
			}
			sliderLayout.setLayoutParams( layoutParams );
		}
	}
}
