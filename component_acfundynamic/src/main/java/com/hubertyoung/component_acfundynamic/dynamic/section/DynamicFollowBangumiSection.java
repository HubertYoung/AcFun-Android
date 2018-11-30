package com.hubertyoung.component_acfundynamic.dynamic.section;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.component_acfundynamic.R;

import java.util.List;

import android.support.v7.widget.RecyclerView;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/31 17:17
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfundynamic.dynamic.section
 */
public class DynamicFollowBangumiSection extends StatelessSection {
	private final BaseActivity mActivity;
	private List< RegionBodyContent > data;

	public DynamicFollowBangumiSection( BaseActivity activity ) {
		super( SectionParameters.builder()
				.itemResourceId( R.layout.item_region_recommend_bangumi )//
//				.headerResourceId( R.layout.widget_region_header_text )//
//				.footerResourceId( R.layout.widget_region_bottom_menu )//
//				.emptyResourceId( R.layout.item_region_empty_bangumi )
				.build() );
		this.mActivity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null ? 0 : data.size();
	}

	@Override
	public int getSpanSizeLookup( int position ) {
		return 6;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new DynamicRecommendViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		DynamicRecommendViewHolder viewHolderDynamicRecommend = ( DynamicRecommendViewHolder ) holder;
		RegionBodyContent regionBodyContent2 = data.get( position );
		if ( regionBodyContent2 != null ) {
			viewHolderDynamicRecommend.root.setVisibility( View.VISIBLE );
			viewHolderDynamicRecommend.title.setText( regionBodyContent2.title );
			if ( regionBodyContent2.extendsStatus == 0 ) {
				viewHolderDynamicRecommend.update.setText( regionBodyContent2.lastUpdate );
				viewHolderDynamicRecommend.updateNo.setVisibility( View.INVISIBLE );
			} else {
				TextView textView = viewHolderDynamicRecommend.update;
				StringBuilder stringBuilder2 = new StringBuilder();
				stringBuilder2.append( regionBodyContent2.updateTime );
				stringBuilder2.append( " · 已更新至" );
				textView.setText( stringBuilder2.toString() );
				viewHolderDynamicRecommend.updateNo.setVisibility( View.VISIBLE );
				viewHolderDynamicRecommend.updateNo.setText( regionBodyContent2.lastUpdate );
			}
			viewHolderDynamicRecommend.intro.setText( regionBodyContent2.intro );
			if ( regionBodyContent2.images.get( 0 ) != null ) {
				ImageLoaderUtil.loadNetImage( regionBodyContent2.images.get( 0 ), viewHolderDynamicRecommend.img );
			} else {
				ImageLoaderUtil.loadResourceImage( R.mipmap.bangumi_default_pic, viewHolderDynamicRecommend.img );
			}
			viewHolderDynamicRecommend.followLayout.setVisibility( View.VISIBLE );
			viewHolderDynamicRecommend.follow.setVisibility( View.VISIBLE );
			viewHolderDynamicRecommend.followImg.setVisibility( View.INVISIBLE );
			if ( Integer.parseInt( regionBodyContent2.contentId ) == 0 ) {
				viewHolderDynamicRecommend.followLayout.setBackgroundResource( R.drawable.shape_bg_clocked_in );
				viewHolderDynamicRecommend.follow.setTextColor( mActivity.getResources().getColor( R.color.text_deep_gray_color ) );
				viewHolderDynamicRecommend.follow.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0, 0 );
				viewHolderDynamicRecommend.follow.setText( "已追番" );
				viewHolderDynamicRecommend.followImg.setBackgroundResource( R.drawable.animation_unfollow_loading );
			} else {
				viewHolderDynamicRecommend.followLayout.setBackgroundResource( R.drawable.shape_bg_clock_in );
				viewHolderDynamicRecommend.follow.setTextColor( mActivity.getResources().getColor( R.color.white ) );
				viewHolderDynamicRecommend.follow.setTextColor( mActivity.getResources().getColor( R.color.white ) );
				viewHolderDynamicRecommend.follow.setCompoundDrawablesWithIntrinsicBounds( R.drawable.fav_bangumi_icon, 0, 0, 0 );
				viewHolderDynamicRecommend.follow.setText( "追番" );
				viewHolderDynamicRecommend.followImg.setBackgroundResource( R.drawable.animation_follow_loading );
			}
			viewHolderDynamicRecommend.followLayout.setOnClickListener( new View.OnClickListener() {
				public void onClick( View view ) {
//					if ( SigninHelper.a().u() ) {
//						if ( FavBangumiAdapter.this.a_( Integer.parseInt( regionBodyContent2.contentId ) ) ) {
//							ApiHelper.a()
//									.b( FavBangumiAdapter.this.av, Integer.parseInt( regionBodyContent2.contentId ), -1, new FollowBangumiCallback( viewHolderDynamicRecommend.follow,
//											viewHolderDynamicRecommend.followImg, Integer
//											.parseInt( regionBodyContent2.contentId ), false ) );
//						} else {
//							ApiHelper.a()
//									.b( FavBangumiAdapter.this.av, Integer.parseInt( regionBodyContent2.contentId ), 1, new FollowBangumiCallback( viewHolderDynamicRecommend.follow,
//											viewHolderDynamicRecommend.followImg, Integer
//											.parseInt( regionBodyContent2.contentId ), true ) );
//						}
//						return;
//					}
//					IntentHelper.f( FavBangumiAdapter.this.al );
				}
			} );
			viewHolderDynamicRecommend.root.setOnClickListener( new View.OnClickListener() {
				@Override
				public void onClick( View v ) {

				}
			} );
		} else {
			viewHolderDynamicRecommend.root.setVisibility( View.GONE );
		}
	}

	public void setRecommendBangumiInfo( List< RegionBodyContent > regionBodyContentList ) {
		this.data = regionBodyContentList;
	}

	public void addRecommendBangumiInfo( List< RegionBodyContent > regionBodyContentList ) {
		this.data.addAll( regionBodyContentList );
	}

	static class DynamicRecommendViewHolder extends RecyclerView.ViewHolder {
		public TextView follow;
		public ImageView followImg;
		public RelativeLayout followLayout;
		public SimpleDraweeView img;
		public TextView intro;
		public LinearLayout root;
		public TextView title;
		public TextView update;
		public TextView updateNo;

		DynamicRecommendViewHolder( View view ) {
			super( view );
			title = ( TextView ) view.findViewById( R.id.bangumi_title );
			update = ( TextView ) view.findViewById( R.id.bangumi_update );
			updateNo = ( TextView ) view.findViewById( R.id.bangumi_update_no );
			follow = ( TextView ) view.findViewById( R.id.follow );
			followImg = ( ImageView ) view.findViewById( R.id.follow_img );
			followLayout = ( RelativeLayout ) view.findViewById( R.id.follow_layout );
			intro = ( TextView ) view.findViewById( R.id.bangumi_intro );
			img = ( SimpleDraweeView ) view.findViewById( R.id.bangumi_cover );
			root = ( LinearLayout ) view.findViewById( R.id.item_root );
		}
	}
}
