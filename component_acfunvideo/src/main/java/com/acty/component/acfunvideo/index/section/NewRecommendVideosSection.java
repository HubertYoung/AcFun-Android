package com.acty.component.acfunvideo.index.section;

import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acty.component.acfunvideo.entity.RegionBodyContent;
import com.acty.component.acfunvideo.entity.Regions;
import com.acty.component_acfunvideo.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.DisplayUtil;
import com.hubertyoung.common.utils.IntentUtils;
import com.hubertyoung.common.utils.StringUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;

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
	private BaseActivity mActivity;
	private Regions mRegions;

	public NewRecommendVideosSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.item_home_video ).headerResourceId( R.layout.widget_region_header_text ).build() );
		this.mActivity = activity;
	}

	@Override
	public RecyclerView.ViewHolder getHeaderViewHolder( View view ) {
		return new HeadTitleViewHolder( view );
	}

	@Override
	public void onBindHeaderViewHolder( RecyclerView.ViewHolder holder ) {
		HeadTitleViewHolder viewHolderTitle = ( HeadTitleViewHolder ) holder;
		if ( getContentItemsTotal() > 0 ) {
			viewHolderTitle.itemView.setVisibility( View.VISIBLE );
			if ( mRegions.bottomText != null ) {
				viewHolderTitle.leftLayout.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick( View v ) {

					}
				} );
			}
			viewHolderTitle.title.setText( mRegions.title );
			if ( TextUtils.isEmpty( mRegions.img ) ) {
				viewHolderTitle.leftIndicator.setVisibility( View.GONE );
				viewHolderTitle.leftNoPic.setVisibility( View.VISIBLE );
			} else {
				viewHolderTitle.leftIndicator.setVisibility( View.VISIBLE );
				viewHolderTitle.leftNoPic.setVisibility( View.GONE );
				ImageLoaderUtil.loadNetImage( mRegions.img, viewHolderTitle.leftIndicator );
			}
			if ( viewHolderTitle.rightMenu != null && mRegions.headerText != null && mRegions.headerText.size() > 0 ) {
				viewHolderTitle.rightMenu.setVisibility( View.VISIBLE );
				switch ( mRegions.headerText.size() ) {
					case 1:
						viewHolderTitle.rightMenuText1.setVisibility( View.VISIBLE );
						viewHolderTitle.rightMenuText2.setVisibility( View.GONE );
						viewHolderTitle.rightMenuText3.setVisibility( View.GONE );
						viewHolderTitle.dotOne.setVisibility( View.GONE );
						viewHolderTitle.dotTwo.setVisibility( View.GONE );
						viewHolderTitle.rightMenuText1.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 15 ) } );
						viewHolderTitle.rightMenuText1.setText( mRegions.headerText.get( 0 ).title );
						rightMenuText1Click( viewHolderTitle.rightMenuText1, //
								mRegions.headerText.get( 0 ).actionId, //
								mRegions.headerText.get( 0 ).contentId, //
								mRegions, mRegions.headerText.get( 0 ).title );
						break;
					case 2:
						viewHolderTitle.rightMenuText1.setVisibility( View.VISIBLE );
						viewHolderTitle.rightMenuText2.setVisibility( View.VISIBLE );
						viewHolderTitle.rightMenuText3.setVisibility( View.GONE );
						viewHolderTitle.dotOne.setVisibility( View.VISIBLE );
						viewHolderTitle.dotTwo.setVisibility( View.GONE );
						viewHolderTitle.rightMenuText1.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 6 ) } );
						viewHolderTitle.rightMenuText2.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 6 ) } );
						viewHolderTitle.rightMenuText1.setText( mRegions.headerText.get( 1 ).title );
						rightMenuText1Click( viewHolderTitle.rightMenuText1, //
								mRegions.headerText.get( 1 ).actionId, //
								mRegions.headerText.get( 1 ).contentId, //
								mRegions, mRegions.headerText.get( 1 ).title );
						viewHolderTitle.rightMenuText2.setText( mRegions.headerText.get( 0 ).title );
						rightMenuText1Click( viewHolderTitle.rightMenuText2,//
								mRegions.headerText.get( 0 ).actionId,//
								mRegions.headerText.get( 0 ).contentId,//
								mRegions, mRegions.headerText.get( 0 ).title );
						break;
					case 3:
						viewHolderTitle.rightMenuText1.setVisibility( View.VISIBLE );
						viewHolderTitle.rightMenuText2.setVisibility( View.VISIBLE );
						viewHolderTitle.rightMenuText3.setVisibility( View.VISIBLE );
						viewHolderTitle.dotOne.setVisibility( View.VISIBLE );
						viewHolderTitle.dotTwo.setVisibility( View.VISIBLE );
						viewHolderTitle.rightMenuText1.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 5 ) } );
						viewHolderTitle.rightMenuText2.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 5 ) } );
						viewHolderTitle.rightMenuText3.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 5 ) } );
						viewHolderTitle.rightMenuText1.setText( mRegions.headerText.get( 2 ).title );
						rightMenuText1Click( viewHolderTitle.rightMenuText1, //
								mRegions.headerText.get( 2 ).actionId,//
								mRegions.headerText.get( 2 ).contentId,//
								mRegions, mRegions.headerText.get( 2 ).title );
						viewHolderTitle.rightMenuText2.setText( mRegions.headerText.get( 1 ).title );
						rightMenuText1Click( viewHolderTitle.rightMenuText2, //
								mRegions.headerText.get( 1 ).actionId, //
								mRegions.headerText.get( 1 ).contentId, //
								mRegions, mRegions.headerText.get( 1 ).title );
						viewHolderTitle.rightMenuText3.setText( mRegions.headerText.get( 0 ).title );
						rightMenuText1Click( viewHolderTitle.rightMenuText3, //
								mRegions.headerText.get( 0 ).actionId, //
								mRegions.headerText.get( 0 ).contentId,//
								mRegions, mRegions.headerText.get( 0 ).title );
						break;
				}
			}
			viewHolderTitle.rightMenu.setVisibility( View.GONE );
		} else {
			viewHolderTitle.itemView.setVisibility( View.GONE );
		}
	}

	private void rightMenuText1Click( TextView rightMenuText1, int actionId, String contentId, Regions regions, String title ) {
		rightMenuText1.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
				if ( regions != null ) {
//					SensorsAnalyticsUtil.e(regions2.title);
				}
				if ( actionId == 11 ) {
					IntentUtils.startActivity( mActivity, actionId, "-1", null );
				} else {
					IntentUtils.startActivity( mActivity, actionId, title, null );
				}
			}
		} );
	}

	@Override
	public int getContentItemsTotal() {
		if ( mRegions != null ) {
			return mRegions.bodyContents == null ? 0 : mRegions.bodyContents.size();
		} else {
			return 0;
		}

	}

	@Override
	public int getItemViewType( int position ) {
//		if ( mRegions.topContent == null ){
//			return 1;
//		}else{
			return 2;
//		}
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new NewRecommendVideosViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		NewRecommendVideosViewHolder viewHolder = ( NewRecommendVideosViewHolder ) holder;
		RegionBodyContent regionBodyContent = mRegions.bodyContents.get( position );

		if ( regionBodyContent == null ) {
			viewHolder.itemView.setVisibility( View.GONE );
			viewHolder.playsLayout.setVisibility( View.GONE );
			return;
		} else {
			viewHolder.itemView.setVisibility( View.VISIBLE );
		}
		if ( regionBodyContent.images == null || regionBodyContent.images.size() <= 0 ) {
			ImageLoaderUtil.loadResourceImage( R.color.transparent, viewHolder.mImg );
		} else {
			ImageLoaderUtil.loadNetImage( regionBodyContent.images.get( 0 ), viewHolder.mImg );
		}

		viewHolder.root.setPadding( position % 2 == 0 ? DisplayUtil.dip2px( 10 ) : DisplayUtil.dip2px( 5 ),//
				0,//
				position % 2 == 1 ? DisplayUtil.dip2px( 10 ) : DisplayUtil.dip2px( 5 ),//
				DisplayUtil.dip2px( 10 ));

		viewHolder.mTitle.setText( regionBodyContent.title );
		if ( regionBodyContent.actionId == 14 ) {
			viewHolder.playsLayout.setVisibility( View.VISIBLE );
			viewHolder.mDanmus.setVisibility( View.GONE );
			viewHolder.mPlays.setText( R.string.common_special );
			viewHolder.mPlays.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0, 0 );
			viewHolder.mPlays.setVisibility( View.VISIBLE );
		} else if ( regionBodyContent.actionId == 10 ) {
			if ( regionBodyContent.visit != null ) {
				viewHolder.playsLayout.setVisibility( View.VISIBLE );
				viewHolder.mPlays.setText( StringUtil.formatChineseNum( mActivity, regionBodyContent.visit.views ) );
				viewHolder.mPlays.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_home_item_video_views, 0, 0, 0 );
				viewHolder.mPlays.setVisibility( View.VISIBLE );
				viewHolder.mDanmus.setText( StringUtil.formatChineseNum( mActivity, regionBodyContent.visit.comments ) );
				viewHolder.mDanmus.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_home_item_video_comments, 0, 0, 0 );
				viewHolder.mDanmus.setVisibility( View.VISIBLE );
			} else {
				viewHolder.playsLayout.setVisibility( View.GONE );
			}
			viewHolder.mMark.setText( R.string.common_article );
			viewHolder.mMark.setBackgroundResource( R.drawable.shape_home_item_mark_green );
			viewHolder.mMark.setVisibility( View.VISIBLE );
		} else if ( regionBodyContent.actionId == 17 ) {
			viewHolder.playsLayout.setVisibility( View.GONE );
			viewHolder.mMark.setText( R.string.common_game );
			viewHolder.mMark.setBackgroundResource( R.drawable.shape_home_item_mark_red );
			viewHolder.mMark.setVisibility( View.VISIBLE );
		} else {
			if ( regionBodyContent.visit != null ) {
				viewHolder.mPlays.setVisibility( View.VISIBLE );
				viewHolder.mDanmus.setVisibility( View.VISIBLE );
				viewHolder.playsLayout.setVisibility( View.VISIBLE );
				viewHolder.mPlays.setText( StringUtil.formatChineseNum( mActivity, regionBodyContent.visit.views ) );
				viewHolder.mPlays.setCompoundDrawablesWithIntrinsicBounds( R.mipmap.icon_video_plays, 0, 0, 0 );
				viewHolder.mDanmus.setText( StringUtil.formatChineseNum( mActivity, regionBodyContent.visit.danmakuSize ) );
			} else {
				viewHolder.playsLayout.setVisibility( View.GONE );
			}
			viewHolder.mMark.setVisibility( View.GONE );
		}
		viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {

			}
		} );
	}

	public void setRegions( Regions mRegions ) {
		this.mRegions = mRegions;
	}

	public interface OnItemClickListener {
		void onClickRecommendBangumiItem( View v, Regions bodyContentsBean );
	}

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener( OnItemClickListener onItemClickListener ) {
		mOnItemClickListener = onItemClickListener;
	}


	static class HeadTitleViewHolder extends RecyclerView.ViewHolder {
		public TextView dotOne;
		public TextView dotTwo;
		public SimpleDraweeView leftIndicator;
		public LinearLayout leftLayout;
		public SimpleDraweeView leftNoPic;
		public LinearLayout rightMenu;
		public TextView rightMenuText1;
		public TextView rightMenuText2;
		public TextView rightMenuText3;
		public TextView title;

		HeadTitleViewHolder( View view ) {
			super( view );
			leftIndicator = ( SimpleDraweeView ) view.findViewById( R.id.region_top_left_indicator );
			leftNoPic = ( SimpleDraweeView ) view.findViewById( R.id.region_top_left_no_pic );
			title = ( TextView ) view.findViewById( R.id.region_top_title );
			leftLayout = ( LinearLayout ) view.findViewById( R.id.region_top_left_layout );
			rightMenuText3 = ( TextView ) view.findViewById( R.id.header_text3 );
			dotTwo = ( TextView ) view.findViewById( R.id.dot_two );
			rightMenuText2 = ( TextView ) view.findViewById( R.id.header_text2 );
			dotOne = ( TextView ) view.findViewById( R.id.dot_one );
			rightMenuText1 = ( TextView ) view.findViewById( R.id.header_text1 );
			rightMenu = ( LinearLayout ) view.findViewById( R.id.region_top_right_menu );
		}

	}

	static class NewRecommendVideosViewHolder extends RecyclerView.ViewHolder {
		public TextView mDanmus;
		public SimpleDraweeView mImg;
		public TextView mMark;
		public TextView mPlays;
		public TextView mTitle;
		public View playsLayout;
		public RelativeLayout root;

		NewRecommendVideosViewHolder( View view ) {
			super( view );
			mImg = ( SimpleDraweeView ) view.findViewById( R.id.recommend_video_item_view_img );
			mPlays = ( TextView ) view.findViewById( R.id.recommend_video_item_view_plays );
			mDanmus = ( TextView ) view.findViewById( R.id.recommend_video_item_view_danmaku );
			playsLayout = ( LinearLayout ) view.findViewById( R.id.recommend_video_item_view_plays_layout );
			mMark = ( TextView ) view.findViewById( R.id.home_item_mark_right_top );
			mTitle = ( TextView ) view.findViewById( R.id.recommend_video_item_view_title );
			root = ( RelativeLayout ) view.findViewById( R.id.home_item_root );
		}
	}
}
