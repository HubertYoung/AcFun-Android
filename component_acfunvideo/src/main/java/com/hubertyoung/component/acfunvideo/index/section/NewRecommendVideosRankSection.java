package com.hubertyoung.component.acfunvideo.index.section;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.data.StringUtil;
import com.hubertyoung.common.utils.display.DisplayUtil;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.common.entity.Regions;
import com.hubertyoung.component.acfunvideo.index.section.viewholder.BindBottomMenuViewHolder;
import com.hubertyoung.component.acfunvideo.index.section.viewholder.BindHeaderTitleViewHolder;
import com.hubertyoung.component.acfunvideo.index.section.viewholder.BottomMenuViewHolder;
import com.hubertyoung.component.acfunvideo.index.section.viewholder.HeadTitleViewHolder;
import com.hubertyoung.component_acfunvideo.R;

import android.support.v7.widget.RecyclerView;

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
public class NewRecommendVideosRankSection extends StatelessSection {
	private BaseActivity mActivity;
	private Regions mRegions;

	public NewRecommendVideosRankSection( BaseActivity activity ) {
		super(SectionParameters.builder()
				.itemResourceId( R.layout.item_videos_rank_view )//
				.headerResourceId( R.layout.widget_region_header_text )//
				.footerResourceId( R.layout.widget_region_bottom_menu )//
				.build() );
		this.mActivity = activity;
	}

	@Override
	public RecyclerView.ViewHolder getFooterViewHolder( View view ) {
		return new BottomMenuViewHolder( view );
	}

	@Override
	public void onBindFooterViewHolder( RecyclerView.ViewHolder holder ) {
		BindBottomMenuViewHolder viewHolder = new BindBottomMenuViewHolder( mActivity, (BottomMenuViewHolder) holder );
		viewHolder.viewBindData( getContentItemsTotal(), mRegions );
	}

	@Override
	public RecyclerView.ViewHolder getHeaderViewHolder( View view ) {
		return new HeadTitleViewHolder( view );
	}

	@Override
	public void onBindHeaderViewHolder( RecyclerView.ViewHolder holder ) {
		BindHeaderTitleViewHolder viewHolder = new BindHeaderTitleViewHolder( mActivity, ( HeadTitleViewHolder ) holder );
		viewHolder.viewBindData( getContentItemsTotal(), mRegions );
	}

	@Override
	public int getContentItemsTotal() {
		if ( mRegions != null ) {
			return mRegions.bodyContents == null ? 0 : Math.min( mRegions.bodyContents.size(), Integer.MAX_VALUE );
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
		return new VideosRankViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		VideosRankViewHolder viewHolder = ( VideosRankViewHolder ) holder;
		RegionBodyContent bodyContent = mRegions.bodyContents.get( position );
		viewHolder.root.setPadding( DisplayUtil.dip2px( 10 ),//
				0,//
				DisplayUtil.dip2px( 10 ),//
				0 );
		if ( position == 0 ) {
			viewHolder.ranking.setBackgroundResource( R.drawable.banana_rank_no1 );
			viewHolder.ranking.setVisibility( View.VISIBLE );
		}
		if ( position == 1 ) {
			viewHolder.ranking.setBackgroundResource( R.drawable.banana_rank_no2 );
			viewHolder.ranking.setVisibility( View.VISIBLE );
		}
		if ( position == 2 ) {
			viewHolder.ranking.setBackgroundResource( R.drawable.banana_rank_no3 );
			viewHolder.ranking.setVisibility( View.VISIBLE );
		}
		if ( position > 2 ) {
			viewHolder.ranking.setVisibility( View.GONE );
		}
		if ( bodyContent != null ) {
			viewHolder.title.setText( bodyContent.title );
			if ( bodyContent.images == null || bodyContent.images.size() <= 0 ) {
				ImageLoaderUtil.loadResourceImage( R.color.transparent, viewHolder.img );
			} else {
				ImageLoaderUtil.loadNetImage( bodyContent.images.get( 0 ), viewHolder.img );
			}
			viewHolder.upName.setText( bodyContent.user.name );
			viewHolder.counts.setText( StringUtil.formatChineseNum( bodyContent.visit.banana ) );
		}
	}

	public void setRegions( Regions regions ) {
		this.mRegions = regions;
	}

	static class VideosRankViewHolder extends RecyclerView.ViewHolder {
		public TextView counts;
		public SimpleDraweeView img;
		public TextView ranking;
		public View root;
		public TextView title;
		public TextView upName;

		VideosRankViewHolder( View view ) {
			super( view );
			this.img = ( SimpleDraweeView ) view.findViewById( R.id.video_rank_img );
			this.ranking = ( TextView ) view.findViewById( R.id.video_rank_ranking );
			this.title = ( TextView ) view.findViewById( R.id.banana_rank_title );
			this.upName = ( TextView ) view.findViewById( R.id.banana_rank_up_name );
			this.counts = ( TextView ) view.findViewById( R.id.banana_rank_counts );
			this.root = ( LinearLayout ) view.findViewById( R.id.home_item_root );
		}
	}

}
