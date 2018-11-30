package com.hubertyoung.component_acfunarticle.article.section;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.data.StringUtil;
import com.hubertyoung.common.utils.display.DisplayUtil;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.component_acfunarticle.R;
import com.hubertyoung.component_acfunarticle.entity.RankContent;

import java.util.List;

import android.support.v7.widget.RecyclerView;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/18 14:59
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunarticle.article.section
 */
public class ListArticleSection extends StatelessSection {
	private Activity mActivity;
	private List< RankContent > data;

	public ListArticleSection( Activity activity ) {
		super( SectionParameters.builder()
				.itemResourceId( R.layout.item_article_with_pic_view )//
				.build() );
		this.mActivity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null ? 0 : data.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new ViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		ViewHolder viewHolder = ( ViewHolder ) holder;
		RankContent rankContent = data.get( position );
		viewHolder.rootView.setPadding( DisplayUtil.dip2px( 10 ),//
				viewHolder.rootView.getPaddingTop(),//
				DisplayUtil.dip2px( 10 ),//
				viewHolder.rootView.getPaddingBottom());
		if ( !TextUtils.isEmpty( rankContent.title ) ) {
			viewHolder.mTitle.setText( rankContent.title );
		}
		if ( !TextUtils.isEmpty( rankContent.user.name ) ) {
			viewHolder.mUploaderName.setText( rankContent.user.name );
		}
		ImageLoaderUtil.loadNetImage( rankContent.user.img, viewHolder.mUploaderAvatar );
		viewHolder.mTime.setText( StringUtil.formatNumTime( rankContent.time ) );
		viewHolder.mComments.setText( StringUtil.formatChineseNum( rankContent.visit.comments ) );
		viewHolder.mViews.setText( StringUtil.formatChineseNum( rankContent.visit.views ) );
		viewHolder.mFrom.setText( rankContent.channel.name );
	}

	public void setArticleGeneralSecondInfo( List< RankContent > list ) {
		this.data = list;
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
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
		public RelativeLayout rootView;
		public SimpleDraweeView singleImg;
		public SimpleDraweeView tripleImg1;
		public SimpleDraweeView tripleImg2;
		public SimpleDraweeView tripleImg3;
		public LinearLayout tripleImgLayout;

		ViewHolder( View view ) {
			super( view );
			rootView = view.findViewById( R.id.home_item_root );
			mCommentsLayout = view.findViewById( R.id.item_article_view_comments_layout );
			mComments = view.findViewById( R.id.item_article_view_comments );
			mTitle = view.findViewById( R.id.item_article_view_title );
			mUploaderAvatar = view.findViewById( R.id.item_article_view_uploader_avatar );
			singleImg = view.findViewById( R.id.item_article_view_single_img );
			tripleImg1 = view.findViewById( R.id.item_article_view_triple_img1 );
			tripleImg2 = view.findViewById( R.id.item_article_view_triple_img2 );
			tripleImg3 = view.findViewById( R.id.item_article_view_triple_img3 );
			tripleImgLayout = view.findViewById( R.id.item_article_view_triple_img_layout );
			mUploaderName = view.findViewById( R.id.item_article_view_uploader );
			mTime = view.findViewById( R.id.item_article_view_relasetime );
			mViews = view.findViewById( R.id.item_article_view_views );
			mFrom = view.findViewById( R.id.item_article_view_subchannel );
			articleState = view.findViewById( R.id.item_article_state );
			commentText = view.findViewById( R.id.comment_text );

			ViewGroup.LayoutParams layoutParams = mCommentsLayout.getLayoutParams();
			layoutParams.width = ( int ) ( DisplayUtil.getScreenWidth( view.getContext() ) * 0.126667f );
			mCommentsLayout.setLayoutParams( layoutParams );
		}
	}
}
