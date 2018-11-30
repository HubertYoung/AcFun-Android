package com.hubertyoung.component.acfunvideo.index.section;

import android.view.View;
import android.widget.ImageView;
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
import com.hubertyoung.component.acfunvideo.entity.Regions;
import com.hubertyoung.component.acfunvideo.index.section.viewholder.BindHeaderTitleViewHolder;
import com.hubertyoung.component.acfunvideo.index.section.viewholder.BottomMenuViewHolder;
import com.hubertyoung.component.acfunvideo.index.section.viewholder.HeadTitleViewHolder;
import com.hubertyoung.component_acfunvideo.R;

import java.util.List;

import android.support.v7.widget.RecyclerView;

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
public class ArticlesRecommendSection extends StatelessSection {
	private BaseActivity mActivity;
	private Regions mRegions;

	public ArticlesRecommendSection( BaseActivity activity ) {
		super( SectionParameters.builder()
				.itemResourceId( R.layout.item_article_with_pic_view )//
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
		return new ArticleNewViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		ArticleNewViewHolder viewHolderArticleNew = ( ArticleNewViewHolder ) holder;
		RegionBodyContent regionBodyContent = mRegions.bodyContents.get( position );
		viewHolderArticleNew.root.setPadding( DisplayUtil.dip2px( 10 ),//
				0,//
				DisplayUtil.dip2px( 10 ),//
				DisplayUtil.dip2px( 0 ) );
		if ( regionBodyContent != null ) {
			viewHolderArticleNew.mFrom.setText( regionBodyContent.channel == null ? mActivity.getString( R.string.common_article ) : regionBodyContent.channel.name );
			viewHolderArticleNew.mTime.setText( StringUtil.formatNumTime( regionBodyContent.time ) );
			viewHolderArticleNew.mTitle.setText( regionBodyContent.title );
			if ( regionBodyContent.images == null || regionBodyContent.images.size() <= 0 ) {
				viewHolderArticleNew.singleImg.setVisibility( View.GONE );
				viewHolderArticleNew.tripleImgLayout.setVisibility( View.GONE );
			} else {
				int size = regionBodyContent.images.size();
				if ( size == 1 ) {
					viewHolderArticleNew.singleImg.setVisibility( View.VISIBLE );
					viewHolderArticleNew.tripleImgLayout.setVisibility( View.GONE );
					ImageLoaderUtil.loadNetImage( regionBodyContent.images.get( 0 ), viewHolderArticleNew.singleImg );
				} else if ( size != 3 ) {
					viewHolderArticleNew.singleImg.setVisibility( View.GONE );
					viewHolderArticleNew.tripleImgLayout.setVisibility( View.GONE );
				} else {
					viewHolderArticleNew.singleImg.setVisibility( View.GONE );
					viewHolderArticleNew.tripleImgLayout.setVisibility( View.GONE );
					ImageLoaderUtil.loadNetImage( regionBodyContent.images.get( 0 ), viewHolderArticleNew.tripleImg1 );
					ImageLoaderUtil.loadNetImage( regionBodyContent.images.get( 1 ), viewHolderArticleNew.tripleImg2 );
					ImageLoaderUtil.loadNetImage( regionBodyContent.images.get( 2 ), viewHolderArticleNew.tripleImg3 );
				}
			}
			if ( regionBodyContent.user != null ) {
				ImageLoaderUtil.loadNetImage( regionBodyContent.user == null ? "" : regionBodyContent.user.img, viewHolderArticleNew.mUploaderAvatar );
				viewHolderArticleNew.mUploaderName.setText( regionBodyContent.user.name );
			}
			if ( regionBodyContent.visit != null ) {
				viewHolderArticleNew.mViews.setText( StringUtil.formatChineseNum( regionBodyContent.visit.views ) );
				viewHolderArticleNew.mComments.setText( StringUtil.formatChineseNum( regionBodyContent.visit.comments ) );
			}
			viewHolderArticleNew.root.setOnClickListener( new View.OnClickListener() {
				@Override
				public void onClick( View v ) {

				}
			} );
		}
	}

	public void setRegions( Regions regions ) {
		this.mRegions = regions;
	}

	public void addRegions( List< RegionBodyContent > regionsList ) {
		this.mRegions.bodyContents.addAll( regionsList );
	}

	static class ArticleNewViewHolder extends RecyclerView.ViewHolder {
		public ImageView articleState;
		public TextView commentText;
		public TextView mComments;
		public LinearLayout mCommentsLayout;
		public TextView mFrom;
		public TextView mTime;
		public TextView mTitle;
		public SimpleDraweeView mUploaderAvatar;
		public TextView mUploaderName;
		public TextView mViews;
		public SimpleDraweeView singleImg;
		public SimpleDraweeView tripleImg1;
		public SimpleDraweeView tripleImg2;
		public SimpleDraweeView tripleImg3;
		public LinearLayout tripleImgLayout;
		public View root;

		ArticleNewViewHolder( View view ) {
			super( view );
			articleState = ( ImageView ) view.findViewById( R.id.item_article_state );
			mComments = ( TextView ) view.findViewById( R.id.item_article_view_comments );
			commentText = ( TextView ) view.findViewById( R.id.comment_text );
			mCommentsLayout = ( LinearLayout ) view.findViewById( R.id.item_article_view_comments_layout );
			mTitle = ( TextView ) view.findViewById( R.id.item_article_view_title );
			singleImg = ( SimpleDraweeView ) view.findViewById( R.id.item_article_view_single_img );
			tripleImg1 = ( SimpleDraweeView ) view.findViewById( R.id.item_article_view_triple_img1 );
			tripleImg2 = ( SimpleDraweeView ) view.findViewById( R.id.item_article_view_triple_img2 );
			tripleImg3 = ( SimpleDraweeView ) view.findViewById( R.id.item_article_view_triple_img3 );
			tripleImgLayout = ( LinearLayout ) view.findViewById( R.id.item_article_view_triple_img_layout );
			mUploaderAvatar = ( SimpleDraweeView ) view.findViewById( R.id.item_article_view_uploader_avatar );
			mUploaderName = ( TextView ) view.findViewById( R.id.item_article_view_uploader );
			mTime = ( TextView ) view.findViewById( R.id.item_article_view_relasetime );
			mViews = ( TextView ) view.findViewById( R.id.item_article_view_views );
			mFrom = ( TextView ) view.findViewById( R.id.item_article_view_subchannel );
			root = view.findViewById( R.id.home_item_root );
		}
	}
}
