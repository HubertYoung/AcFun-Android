package com.hubertyoung.component_gamecenter.gamecenter.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.entity.Regions;
import com.hubertyoung.common.entity.RegionsContent;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.component_gamecenter.R;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2018/12/4 15:32
 * @since:
 * @see GameCenterGameListVHorizSection
 */
public class GameCenterGameListVHorizSection extends StatelessSection {
	private BaseActivity mActivity;
	private Regions regions;

	public GameCenterGameListVHorizSection( BaseActivity activity ) {
		super( SectionParameters.builder().itemResourceId( R.layout.item_region_game_center_list )//
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
		return regions == null || regions.contents == null || regions.contents.isEmpty() ? 0 : regions.contents.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new GameListViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		GameListViewHolder viewHolderGameList = ( GameListViewHolder ) holder;
		RegionsContent regionsContent = regions.contents.get( position );
		if ( regionsContent != null ) {
			ImageLoaderUtil.loadNetImage( regionsContent.gameInfo.icon, viewHolderGameList.img );
			viewHolderGameList.title.setText( regionsContent.gameInfo.name );
			if ( regionsContent.gameInfo.tag == null || regionsContent.gameInfo.tag.size() == 0 ) {
				viewHolderGameList.tag.setVisibility( View.GONE );
			} else {
				viewHolderGameList.tag.setText( regionsContent.gameInfo.tag.get( 0 ) );
				viewHolderGameList.tag.setVisibility( View.VISIBLE );
			}
			viewHolderGameList.description.setText( regionsContent.gameInfo.desc );
			if ( "hot".equals( regionsContent.gameInfo.corner ) ) {
				viewHolderGameList.icon.setImageResource( R.drawable.ic_game_center_list_hot );
				viewHolderGameList.icon.setVisibility( View.VISIBLE );
			} else if ( "new".equals( regionsContent.gameInfo.corner ) ) {
				viewHolderGameList.icon.setImageResource( R.drawable.ic_game_center_list_new );
				viewHolderGameList.icon.setVisibility( View.VISIBLE );
			} else {
				viewHolderGameList.icon.setVisibility( View.GONE );
			}
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append( Constants.ANALYTICS_RESOURCE_GC_GAME_LIST );
			stringBuilder.append( regionsContent.gameInfo.gameId );
//			a( viewHolderGameList, stringBuilder.toString(), regionsContent.gameInfo );
			viewHolderGameList.itemView.setOnClickListener( new View.OnClickListener() {
				public void onClick( View view ) {
//					Bundle bundle = new Bundle();
//					bundle.putString( "name", regionsContent.gameInfo.name );
//					String str = "action";
//					int i = 1;
//					if ( regionsContent.gameInfo.gameStatus == 1 ) {
//						i = 0;
//					}
//					bundle.putInt( str, i );
//					bundle.putString( "id", regionsContent.url );
//					KanasCommonUtil.c( KanasConstants.bY, bundle );
//					bundle = new Bundle();
//					str = GameDetailActivity.g;
//					StringBuilder stringBuilder = new StringBuilder();
//					stringBuilder.append( Constants.ANALYTICS_RESOURCE_GC_GAME_LIST );
//					stringBuilder.append( regionsContent.gameInfo.gameId );
//					bundle.putString( str, stringBuilder.toString() );
//					Utils.a( GameCenterAdapter.this.al, regionsContent, bundle );
//					str = Utils.a( regionsContent );
//					stringBuilder = new StringBuilder();
//					stringBuilder.append( Constants.ANALYTICS_RESOURCE_GC_GAME_LIST );
//					stringBuilder.append( regionsContent.gameInfo.gameId );
//					AnalyticsUtil.e( "Android", str, stringBuilder.toString() );
				}
			} );
		}
	}

	public void setRegions( Regions regions ) {
		this.regions = regions;
	}

	static class GameListViewHolder extends RecyclerView.ViewHolder {
		SimpleDraweeView img;
		ImageView icon;
		ProgressBar progressBar;
		TextView progressText;
		TextView buttonText;
		TextView title;
		TextView tag;
		TextView description;
		RelativeLayout mHomeItemRoot;

		GameListViewHolder( View view ) {
			super( view );
			this.img = ( SimpleDraweeView ) view.findViewById( R.id.item_game_center_list_img );
			this.icon = ( ImageView ) view.findViewById( R.id.item_game_center_list_img_icon );
			this.progressBar = ( ProgressBar ) view.findViewById( R.id.download_button_progress );
			this.progressText = ( TextView ) view.findViewById( R.id.download_button_progress_text );
			this.buttonText = ( TextView ) view.findViewById( R.id.download_button_text );
			this.title = ( TextView ) view.findViewById( R.id.item_game_center_list_title );
			this.tag = ( TextView ) view.findViewById( R.id.item_game_center_list_tag );
			this.description = ( TextView ) view.findViewById( R.id.item_game_center_list_description );
			this.mHomeItemRoot = ( RelativeLayout ) view.findViewById( R.id.home_item_root );
		}
	}
}
