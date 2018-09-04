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
import com.hubertyoung.common.utils.DisplayUtil;
import com.hubertyoung.common.utils.StringUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;

import java.util.List;

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
public class NewBangumiSection extends Section {
	private Context mContext;
	private List< NewRecommendEntity > data;

	public NewBangumiSection( Context context ) {
		super( new SectionParameters.Builder( R.layout.new_bangumi_head_view ).build() );
		this.mContext = context;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null ? 0 : data.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new NewBangumiViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		NewBangumiViewHolder viewHolder = ( NewBangumiViewHolder ) holder;
		viewHolder.mNewBangumiHeadViewContainer.removeAllViews();
		RecommendBangumiItemView recommendBangumiItemView = new RecommendBangumiItemView( mContext );
		NewRecommendEntity newRecommendEntity = data.get( position );
		List< NewRecommendEntity.BodyContentsBean > list = newRecommendEntity.bodyContents;
		int a = DisplayUtil.px2dip( 9.0f );
		if ( list != null && list.size() > 0 ) {
			for (int i = 0; i < list.size(); i++) {
				View view = recommendBangumiItemView.init();
				view.setBackgroundResource( R.drawable.selector_transparent_light );
				viewHolder.mNewBangumiHeadViewContainer.addView( view );
				LinearLayout.LayoutParams layoutParams = ( LinearLayout.LayoutParams ) view.getLayoutParams();
				if ( i == 0 ) {
					layoutParams.leftMargin = DisplayUtil.px2dip( 15.0f );
				} else {
					layoutParams.leftMargin = a;
				}
				view.setLayoutParams( layoutParams );

				RecommendBangumiItemView.ViewHolder bangumiViewHolder = ( RecommendBangumiItemView.ViewHolder ) view.getTag();
				NewRecommendEntity.BodyContentsBean bodyContentsBean = list.get( i );
//				Utils.a(getActivity(), searchBangumi.mCover, bangumiViewHolder.mCover);
				bangumiViewHolder.mTitle.setText( bodyContentsBean.mTitle );
				if ( bodyContentsBean.mStatus == 0 ) {
					bangumiViewHolder.mText.setText( mContext.getString( R.string.bangumi_rss_update_end ) );
				} else {
//					bangumiViewHolder.mText.setText(Utils.a(getActivity(), searchBangumi.mLastVideoName));
					bangumiViewHolder.mText.setText( bodyContentsBean.mLastVideoName );
				}
				bangumiViewHolder.mText.setVisibility( View.VISIBLE );
				bangumiViewHolder.mStows.setText( mContext.getString( R.string.bangumi_rss_stows, StringUtil.formatChineseNum(mContext, bodyContentsBean.stows ) ) );
				bangumiViewHolder.mStows.setVisibility( View.VISIBLE );

				if ( mOnItemClickListener != null ) {
					view.setOnClickListener( new View.OnClickListener() {
						@Override
						public void onClick( View v ) {
							mOnItemClickListener.onClickRecommendBangumiItem( v,bodyContentsBean );
						}
					} );
				}
			}
		}
	}

	public void setData( List< NewRecommendEntity > newRecommendEntityList ) {
		this.data = newRecommendEntityList;
	}

	public interface OnItemClickListener {
		void onClickRecommendBangumiItem( View v, NewRecommendEntity.BodyContentsBean bodyContentsBean );
	}

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener( OnItemClickListener onItemClickListener ) {
		mOnItemClickListener = onItemClickListener;
	}

	static class NewBangumiViewHolder extends RecyclerView.ViewHolder {
		TextView mPartNameViewName;
		TextView mPartNameViewName2;
		LinearLayout mNewBangumiHeadViewContainer;
		TextView mWidgetMoreHotMenuText;
		RelativeLayout mWidgetMoreHotMenuLayout;
		TextView mSecondaryPartNameViewName;
		TextView mSecondaryPartNameViewName2;

		NewBangumiViewHolder( View view ) {
			super( view );
			mPartNameViewName = ( TextView ) view.findViewById( R.id.recommend_video_view_name ).findViewById( R.id.part_name_view_name );
			mPartNameViewName2 = ( TextView ) view.findViewById( R.id.recommend_video_view_name ).findViewById( R.id.part_name_view_name2 );
			mNewBangumiHeadViewContainer = ( LinearLayout ) view.findViewById( R.id.new_bangumi_head_view_container );
			mWidgetMoreHotMenuText = ( TextView ) view.findViewById( R.id.widget_more_hot_menu_text );
			mWidgetMoreHotMenuLayout = ( RelativeLayout ) view.findViewById( R.id.widget_more_hot_menu_layout );
			mSecondaryPartNameViewName = ( TextView ) view.findViewById( R.id.recommend_secondary_view_name ).findViewById( R.id.part_name_view_name );
			mSecondaryPartNameViewName2 = ( TextView ) view.findViewById( R.id.recommend_secondary_view_name ).findViewById( R.id.part_name_view_name2 );
		}
	}
}
