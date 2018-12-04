package com.hubertyoung.component_gamecenter.gamecenter.section;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.entity.GameInfo;
import com.hubertyoung.common.entity.Regions;
import com.hubertyoung.common.entity.RegionsContent;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.display.DisplayUtil;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.decoration.VerticalDividerItemDecoration;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.component_gamecenter.R;

import java.util.List;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2018/12/4 15:32
 * @since:
 * @see GameCenterGameListVerticSection
 */
public class GameCenterGameListVerticSection extends StatelessSection {
	private BaseActivity mActivity;
	private Regions regions;
	private SectionedRecyclerViewAdapter mAdapter;
	private RecommendGameSection mRecommendGameSection;

	public GameCenterGameListVerticSection( BaseActivity activity ) {
		super( SectionParameters.builder().itemResourceId( R.layout.item_region_game_center_recommend_container )//
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
		return new GameListVerticViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		GameListVerticViewHolder viewHolder = ( GameListVerticViewHolder ) holder;
		mAdapter = new SectionedRecyclerViewAdapter( null );
		if ( mAdapter == null || mRecommendGameSection == null ) {
			viewHolder.container.setLayoutManager( new LinearLayoutManager( mActivity, LinearLayoutManager.HORIZONTAL, false ) );
			viewHolder.container.setHasFixedSize( true );
			viewHolder.container.addItemDecoration( //
					new VerticalDividerItemDecoration.Builder( mActivity )//
							.colorResId( R.color.white )//
							.size( DisplayUtil.dip2px( 10 ) )//
							.showLastDivider()//
							.showFirstDivider()
							.build() );
			mRecommendGameSection = new RecommendGameSection( mActivity );
			mAdapter.addSection( mRecommendGameSection );
			viewHolder.container.setAdapter( mAdapter );
		}
		mRecommendGameSection.setGameListInfo( regions.contents );
		mAdapter.notifyDataSetChanged();
	}

	public void setRegions( Regions regions ) {
		this.regions = regions;
	}

	static class GameListVerticViewHolder extends RecyclerView.ViewHolder {
		RecyclerView container;

		GameListVerticViewHolder( View view ) {
			super( view );
			this.container = ( RecyclerView ) view.findViewById( R.id.item_game_center_recommend_container );
		}
	}


	private class RecommendGameSection extends StatelessSection {
		private BaseActivity mActivity;
		private List< RegionsContent > data;

		public RecommendGameSection( BaseActivity activity ) {
			super( SectionParameters.builder().itemResourceId( R.layout.item_region_game_center_recommend )//
//				.headerResourceId( R.layout.widget_region_header_text )//
//				.footerResourceId( R.layout.widget_region_bottom_menu )//
					.build() );
			this.mActivity = activity;
		}

		@Override
		public int getContentItemsTotal() {
			return data == null ? 0 : data.size();
		}

		@Override
		public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
			return new RecommendGameViewHolder( view );
		}

		@Override
		public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
			RecommendGameViewHolder viewHolder = ( RecommendGameViewHolder ) holder;
			RegionsContent regionsContent = data.get( position );
			if ( regionsContent != null && regionsContent.gameInfo != null ) {
				ImageLoaderUtil.loadNetImage( regionsContent.image, viewHolder.img );
				viewHolder.title.setText( regionsContent.gameInfo.name );
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append( Constants.ANALYTICS_RESOURCE_GC_PROMO );
				stringBuilder.append( regionsContent.resourceId );
				downloadApk( viewHolder, stringBuilder.toString(), regionsContent.gameInfo );
				viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
					public void onClick( View view ) {
						ToastUtil.showWarning( regionsContent.toString() );
//						Bundle bundle = new Bundle();
//						String str = GameDetailActivity.g;
//						StringBuilder stringBuilder = new StringBuilder();
//						stringBuilder.append( Constants.ANALYTICS_RESOURCE_GC_PROMO );
//						stringBuilder.append( regionsContent.resourceId );
//						bundle.putString( str, stringBuilder.toString() );
//						Utils.a( GameCenterAdapter.this.al, regionsContent, bundle );
//						str = Utils.a( regionsContent );
//						stringBuilder = new StringBuilder();
//						stringBuilder.append( Constants.ANALYTICS_RESOURCE_GC_PROMO );
//						stringBuilder.append( regionsContent.resourceId );
//						AnalyticsUtil.e( "Android", str, stringBuilder.toString() );
					}
				} );
			}
		}

		public void setGameListInfo( List< RegionsContent > contents ) {
			this.data = contents;
		}
	}

	private void downloadApk( RecommendGameViewHolder viewHolderGameListDownloadItem, String str, GameInfo gameInfo ) {
//		if ( gameInfo.gameStatus == 0 ) {
//			int a = this.o.a( gameInfo.androidUrl, gameInfo.packageName );
//			if ( a == DownloadGameManager.a ) {
//				if ( this.o.b( gameInfo.androidUrl ) ) {
//					viewHolderGameListDownloadItem.buttonText.setText( R.string.game_center_button_text_update );
//				} else {
//					viewHolderGameListDownloadItem.buttonText.setText( R.string.game_center_button_text_download );
//				}
//				viewHolderGameListDownloadItem.buttonText.setOnClickListener( new View.OnClickListener() {
//					public void onClick( View view ) {
//						if ( NetUtil.c( GameCenterAdapter.this.al ) ) {
//							if ( GameCenterAdapter.this.o != null ) {
//								GameCenterAdapter.this.o.a( gameInfo.androidUrl, gameInfo.packageName, gameInfo.name, Utils.a( gameInfo.gameId, Constants.ANALYTICS_PAGE_GAME_CENTER, str ), gameInfo
//										.icon, gameInfo.desc );
//								AnalyticsUtil.a( gameInfo.gameId, Constants.ANALYTICS_PAGE_GAME_CENTER, str );
//							}
//							return;
//						}
//						ToastUtil.a( GameCenterAdapter.this.al, ( int ) R.string.common_error_601 );
//					}
//				} );
//				viewHolderGameListDownloadItem.buttonText.setVisibility( 0 );
//			} else if ( a == DownloadGameManager.d ) {
//				viewHolderGameListDownloadItem.progressBar.setProgress( DownloadGameManager.b( gameInfo.androidUrl ) );
//				viewHolderGameListDownloadItem.progressText.setText( R.string.game_center_button_text_continue );
//				viewHolderGameListDownloadItem.buttonText.setVisibility( 8 );
//				viewHolderGameListDownloadItem.progressText.setOnClickListener( new View.OnClickListener() {
//					public void onClick( View view ) {
//						if ( NetUtil.c( GameCenterAdapter.this.al ) ) {
//							if ( GameCenterAdapter.this.o != null ) {
//								GameCenterAdapter.this.o.a( gameInfo.androidUrl, gameInfo.packageName, gameInfo.name, gameInfo.gameId, gameInfo.icon, gameInfo.desc );
//							}
//							return;
//						}
//						ToastUtil.a( GameCenterAdapter.this.al, ( int ) R.string.common_error_601 );
//					}
//				} );
//			} else if ( a == DownloadGameManager.c ) {
//				viewHolderGameListDownloadItem.progressText.setText( R.string.game_center_button_text_pending );
//				viewHolderGameListDownloadItem.progressBar.setProgress( DownloadGameManager.b( gameInfo.androidUrl ) );
//				viewHolderGameListDownloadItem.buttonText.setVisibility( 8 );
//				viewHolderGameListDownloadItem.progressText.setOnClickListener( null );
//			} else if ( a == DownloadGameManager.b ) {
//				if ( this.p != null && this.p.getDownloadFileInfo() != null && !TextUtils.isEmpty( this.p.getUrl() ) && this.p.getUrl().equals( gameInfo.androidUrl ) ) {
//					int d = ( int ) ( ( this.p.getDownloadFileInfo().d() * 100 ) / this.p.getDownloadFileInfo().j() );
//					viewHolderGameListDownloadItem.progressBar.setProgress( d );
//					TextView textView = viewHolderGameListDownloadItem.progressText;
//					StringBuilder stringBuilder = new StringBuilder();
//					stringBuilder.append( d );
//					stringBuilder.append( "%" );
//					textView.setText( stringBuilder.toString() );
//					viewHolderGameListDownloadItem.buttonText.setVisibility( 8 );
//					viewHolderGameListDownloadItem.progressText.setOnClickListener( new View.OnClickListener() {
//						public void onClick( View view ) {
//							GameCenterAdapter.this.o.a( gameInfo.androidUrl );
//						}
//					} );
//				}
//			} else if ( a == DownloadGameManager.e ) {
//				viewHolderGameListDownloadItem.buttonText.setText( R.string.game_center_button_text_install );
//				viewHolderGameListDownloadItem.buttonText.setVisibility( 0 );
//				viewHolderGameListDownloadItem.buttonText.setOnClickListener( new View.OnClickListener() {
//					public void onClick( View view ) {
//						DownloadGameManager.a( GameCenterAdapter.this.al, gameInfo.androidUrl, gameInfo.packageName, gameInfo.name );
//					}
//				} );
//			} else if ( a == DownloadGameManager.f ) {
//				viewHolderGameListDownloadItem.buttonText.setText( R.string.game_center_button_text_open );
//				viewHolderGameListDownloadItem.buttonText.setVisibility( 0 );
//				viewHolderGameListDownloadItem.buttonText.setOnClickListener( new View.OnClickListener() {
//					public void onClick( View view ) {
//						DownloadGameManager.b( GameCenterAdapter.this.al, gameInfo.androidUrl, gameInfo.packageName, gameInfo.name );
//						AnalyticsUtil.d( gameInfo.gameId, Constants.ANALYTICS_PAGE_GAME_CENTER, str );
//					}
//				} );
//			}
//		} else if ( gameInfo.gameStatus == 1 ) {
//			viewHolderGameListDownloadItem.buttonText.setText( R.string.game_center_button_text_reservation );
//			viewHolderGameListDownloadItem.buttonText.setVisibility( View.VISIBLE );
//			viewHolderGameListDownloadItem.buttonText.setOnClickListener( new View.OnClickListener() {
//				public void onClick( View view ) {
//					RegionsContent regionsContent = new RegionsContent();
//					regionsContent.actionId = 4;
//					regionsContent.url = gameInfo.bookUrl;
//					AnalyticsUtil.e( "Android", Utils.a( regionsContent ), str );
//					Bundle bundle = new Bundle();
//					bundle.putString( "url", gameInfo.bookUrl );
//					IntentHelper.a( GameCenterAdapter.this.al, WebViewActivity.class, bundle );
//				}
//			} );
//		}
	}

	static class RecommendGameViewHolder extends RecyclerView.ViewHolder {
		SimpleDraweeView img;
		ProgressBar progressBar;
		TextView progressText;
		TextView buttonText;
		TextView title;

		RecommendGameViewHolder( View view ) {
			super( view );
			this.img = ( SimpleDraweeView ) view.findViewById( R.id.item_game_center_recommend_img );
			this.progressBar = ( ProgressBar ) view.findViewById( R.id.download_button_progress );
			this.progressText = ( TextView ) view.findViewById( R.id.download_button_progress_text );
			this.buttonText = ( TextView ) view.findViewById( R.id.download_button_text );
			this.title = ( TextView ) view.findViewById( R.id.item_game_center_recommend_text );
		}
	}
}
