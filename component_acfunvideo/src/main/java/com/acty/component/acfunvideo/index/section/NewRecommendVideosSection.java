package com.acty.component.acfunvideo.index.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acty.component.acfunvideo.entity.NewRecommendEntity;
import com.acty.component.acfunvideo.index.itemview.RecommendBangumiItemView;
import com.acty.component_acfunvideo.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.DisplayUtil;
import com.hubertyoung.common.utils.StringUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;

import java.util.List;

import butterknife.BindView;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 16:16
 * @since:V$VERSION
 * @desc:com.acty.component.acfunvideo.index.section
 */
public class NewRecommendVideosSection extends Section {
	private Context mContext;
	private NewRecommendEntity data;

	public NewRecommendVideosSection( Context context ) {
		super( new SectionParameters.Builder( R.layout.item_home_video ).headerResourceId( R.layout.widget_region_header_text ).build() );
		this.mContext = context;
	}

	@Override
	public RecyclerView.ViewHolder getHeaderViewHolder( View view ) {
		return new HeadTitleViewHolder( view );
	}

	@Override
	public void onBindHeaderViewHolder( RecyclerView.ViewHolder holder ) {
		HeadTitleViewHolder viewHolder = ( HeadTitleViewHolder ) holder;
		if ( getContentItemsTotal() > 0 ) {
			viewHolder.itemView.setVisibility( View.VISIBLE );

		} else {
			viewHolder.itemView.setVisibility( View.GONE );
		}
	}

	@Override
	public int getContentItemsTotal() {
		if ( data != null ) {
			return data.bodyContents == null ? 0 : data.bodyContents.size();
		} else {
			return 0;
		}

	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new NewRecommendVideosViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		NewRecommendVideosViewHolder viewHolder = ( NewRecommendVideosViewHolder ) holder;
		NewRecommendEntity.BodyContentsBean regionBodyContent = data.bodyContents.get( position );
//		if (regionBodyContent == null) {
//			viewHolder.itemView.setVisibility(View.GONE);
//			viewHolder.playsLayout.setVisibility(View.GONE);
//			return;
//		}
//		viewHolder.itemView.setVisibility(View.VISIBLE);
//		if (regionBodyContent.images == null || regionBodyContent.images.size() <= 0) {
//			tv.acfun.core.utils.Utils.a(r7.al, tv.acfun.core.utils.Utils.a((int) R.color.transparent), viewHolder.mImg);
//		} else {
//			tv.acfun.core.utils.Utils.a(r7.al, (String) regionBodyContent.images.get(0), viewHolder.mImg);
//		}
//		viewHolder.mTitle.setText(regionBodyContent.title);
//		if (regionBodyContent.actionId == 14) {
//			viewHolder.playsLayout.setVisibility(0);
//			viewHolder.mDanmus.setVisibility(8);
//			viewHolder.mPlays.setText(R.string.common_special);
//			viewHolder.mPlays.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//			viewHolder.mPlays.setVisibility(0);
//		} else if (regionBodyContent.actionId == 10) {
//			if (regionBodyContent.visit != null) {
//				viewHolder.playsLayout.setVisibility(0);
//				viewHolder.mPlays.setText(StringUtil.b(r7.al, regionBodyContent.visit.views));
//				viewHolder.mPlays.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_item_video_views, 0, 0, 0);
//				viewHolder.mPlays.setVisibility(0);
//				viewHolder.mDanmus.setText(StringUtil.b(r7.al, regionBodyContent.visit.comments));
//				viewHolder.mDanmus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_item_video_comments, 0, 0, 0);
//				viewHolder.mDanmus.setVisibility(0);
//			} else {
//				viewHolder.playsLayout.setVisibility(8);
//			}
//			viewHolder.mMark.setText(R.string.common_article);
//			viewHolder.mMark.setBackgroundResource(R.drawable.shape_home_item_mark_green);
//			viewHolder.mMark.setVisibility(0);
//		} else if (regionBodyContent.actionId == 17) {
//			viewHolder.playsLayout.setVisibility(8);
//			viewHolder.mMark.setText(R.string.common_game);
//			viewHolder.mMark.setBackgroundResource(R.drawable.shape_home_item_mark_red);
//			viewHolder.mMark.setVisibility(0);
//		} else {
//			if (regionBodyContent.visit != null) {
//				viewHolder.mPlays.setVisibility(0);
//				viewHolder.mDanmus.setVisibility(0);
//				viewHolder.playsLayout.setVisibility(0);
//				viewHolder.mPlays.setText(StringUtil.b(r7.al, regionBodyContent.visit.views));
//				viewHolder.mPlays.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_video_plays, 0, 0, 0);
//				viewHolder.mDanmus.setText(StringUtil.b(r7.al, regionBodyContent.visit.danmakuSize));
//			} else {
//				viewHolder.playsLayout.setVisibility(8);
//			}
//			viewHolder.mMark.setVisibility(8);
//		}
//		viewHolder.root.setTag(Integer.valueOf(i));
//		viewHolder.root.setOnClickListener(r7);
	}

	public void setData( NewRecommendEntity newRecommendEntityList ) {
		this.data = newRecommendEntityList;
	}

	public interface OnItemClickListener {
		void onClickRecommendBangumiItem( View v, NewRecommendEntity.BodyContentsBean bodyContentsBean );
	}

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener( OnItemClickListener onItemClickListener ) {
		mOnItemClickListener = onItemClickListener;
	}


	static class HeadTitleViewHolder extends RecyclerView.ViewHolder {
		SimpleDraweeView regionTopLeftIndicator;
		SimpleDraweeView regionTopLeftNoPic;
		TextView regionTopTitle;
		LinearLayout regionTopLeftLayout;
		TextView headerText3;
		TextView dotTwo;
		TextView headerText2;
		TextView dotOne;
		TextView headerText1;
		LinearLayout regionTopRightMenu;
		RelativeLayout homeTitleRoot;

		HeadTitleViewHolder( View view ) {
			super( view );
			this.regionTopLeftIndicator = ( SimpleDraweeView ) view.findViewById( R.id.region_top_left_indicator );
			this.regionTopLeftNoPic = ( SimpleDraweeView ) view.findViewById( R.id.region_top_left_no_pic );
			this.regionTopTitle = ( TextView ) view.findViewById( R.id.region_top_title );
			this.regionTopLeftLayout = ( LinearLayout ) view.findViewById( R.id.region_top_left_layout );
			this.headerText3 = ( TextView ) view.findViewById( R.id.header_text3 );
			this.dotTwo = ( TextView ) view.findViewById( R.id.dot_two );
			this.headerText2 = ( TextView ) view.findViewById( R.id.header_text2 );
			this.dotOne = ( TextView ) view.findViewById( R.id.dot_one );
			this.headerText1 = ( TextView ) view.findViewById( R.id.header_text1 );
			this.regionTopRightMenu = ( LinearLayout ) view.findViewById( R.id.region_top_right_menu );
			this.homeTitleRoot = ( RelativeLayout ) view.findViewById( R.id.home_title_root );
		}
	}

	static class NewRecommendVideosViewHolder extends RecyclerView.ViewHolder {
		public TextView mDanmus;
		public SimpleDraweeView mImg;
		public TextView mMark;
		public TextView mPlays;
		public TextView mTitle;
		public View playsLayout;

		NewRecommendVideosViewHolder( View view) {
			super(view);
			mImg = ( SimpleDraweeView ) view.findViewById( R.id.recommend_video_item_view_img );
			mPlays = ( TextView ) view.findViewById( R.id.recommend_video_item_view_plays );
			mDanmus = ( TextView ) view.findViewById( R.id.recommend_video_item_view_danmaku );
			playsLayout = ( LinearLayout ) view.findViewById( R.id.recommend_video_item_view_plays_layout );
			mMark = ( TextView ) view.findViewById( R.id.home_item_mark_right_top );
			mTitle = ( TextView ) view.findViewById( R.id.recommend_video_item_view_title );
		}
	}
}
