package com.hubertyoung.component.acfunvideo.index.section;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.display.DisplayUtil;
import com.hubertyoung.common.utils.data.StringUtil;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.component.acfunvideo.entity.Regions;
import com.hubertyoung.component.acfunvideo.index.section.viewholder.BindHeaderTitleViewHolder;
import com.hubertyoung.component.acfunvideo.index.section.viewholder.BottomMenuViewHolder;
import com.hubertyoung.component.acfunvideo.index.section.viewholder.HeadTitleViewHolder;
import com.hubertyoung.component_acfunvideo.R;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/17 15:04
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.index.section
 */
public class ArticlesRankRecommendSection extends Section {
	private BaseActivity mActivity;
	private Regions mRegions;

	public ArticlesRankRecommendSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.item_article_rank_view )//
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
		BottomMenuViewHolder viewHolder = ( BottomMenuViewHolder ) holder;
		viewHolder.bottomMoreLayout.setVisibility( View.GONE );
		viewHolder.vBottomLine.setVisibility( View.GONE );
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
	public int getSpanSizeLookup( int position ) {
		return 6;
	}

	@Override
	public int getContentItemsTotal() {
		if ( mRegions != null ) {
			int count = mRegions.topContent == null ? 0 : 1;
			return mRegions.bodyContents == null ? count : mRegions.bodyContents.size();
		} else {
			return 0;
		}
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new ArticlesRankViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		RegionBodyContent regionBodyContent = mRegions.bodyContents.get( position );
		ArticlesRankViewHolder viewHolderArticleRank = ( ArticlesRankViewHolder ) holder;
		viewHolderArticleRank.root.setPadding( DisplayUtil.dip2px( 10 ),//
				0,//
				DisplayUtil.dip2px( 10 ),//
				DisplayUtil.dip2px( 0 ) );
		if ( position == 0 ) {
			viewHolderArticleRank.ranking.setBackgroundResource( R.drawable.rank_top1 );
		}
		if ( position == 1 ) {
			viewHolderArticleRank.ranking.setBackgroundResource( R.drawable.rank_top2 );
		}
		if ( position == 2 ) {
			viewHolderArticleRank.ranking.setBackgroundResource( R.drawable.rank_top3 );
		}
		if ( position > 2 ) {
			viewHolderArticleRank.ranking.setBackgroundResource( R.drawable.rank_top4 );
		}

		viewHolderArticleRank.ranking.setText( position + "" );
		viewHolderArticleRank.ranking.setVisibility( View.VISIBLE );
		if ( regionBodyContent != null ) {
			viewHolderArticleRank.title.setText( regionBodyContent.title );
			ImageLoaderUtil.loadNetImage( regionBodyContent.user == null ? "" : regionBodyContent.user.img, viewHolderArticleRank.img );
			viewHolderArticleRank.userName.setText( regionBodyContent.user == null ? "" : regionBodyContent.user.name );
			viewHolderArticleRank.comments.setText( StringUtil.formatChineseNum( regionBodyContent.visit.comments ) );
			viewHolderArticleRank.views.setText( StringUtil.formatChineseNum( regionBodyContent.visit.views ) );
			viewHolderArticleRank.from.setText( regionBodyContent.channel.name );
			viewHolderArticleRank.root.setOnClickListener( new View.OnClickListener() {
				@Override
				public void onClick( View v ) {
					ToastUtil.showSuccess( position + "" );
				}
			} );
		}
	}

	public void setRegions( Regions regions ) {
		this.mRegions = regions;
	}

	static class ArticlesRankViewHolder extends RecyclerView.ViewHolder {
		public TextView comments;
		public TextView from;
		public SimpleDraweeView img;
		public TextView ranking;
		public View root;
		public TextView title;
		public TextView userName;
		public TextView views;

		ArticlesRankViewHolder( View view ) {
			super( view );
			ranking = ( TextView ) view.findViewById( R.id.article_list_item_view_rank_tag );
			title = ( TextView ) view.findViewById( R.id.item_article_view_title );
			img = ( SimpleDraweeView ) view.findViewById( R.id.item_article_view_uploader_avatar );
			userName = ( TextView ) view.findViewById( R.id.item_article_view_uploader );
			comments = ( TextView ) view.findViewById( R.id.item_article_view_comments );
			views = ( TextView ) view.findViewById( R.id.item_article_view_views );
			from = ( TextView ) view.findViewById( R.id.item_article_view_subchannel );
			root = ( LinearLayout ) view.findViewById( R.id.content_layout );
		}
	}
}
