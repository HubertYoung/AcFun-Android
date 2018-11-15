package com.hubertyoung.component.acfunvideo.index.section;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.display.DisplayUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.component.acfunvideo.entity.Regions;
import com.hubertyoung.component.acfunvideo.index.section.viewholder.BindBottomMenuViewHolder;
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
 * @date:2018/9/4 16:16
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.index.section
 */
public class NewRecommendBangumisSection extends Section {
	private BaseActivity mActivity;
	private Regions mRegions;

	public NewRecommendBangumisSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.item_home_bangumi )//
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
		BindBottomMenuViewHolder viewHolder = new BindBottomMenuViewHolder( mActivity, ( BottomMenuViewHolder ) holder );
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
			int count = mRegions.topContent == null ? 0 : 1;
			return mRegions.bodyContents == null ? count : Math.min( mRegions.bodyContents.size() + count, mRegions.show + count );
		} else {
			return 0;
		}
	}

	@Override
	public int getSpanSizeLookup( int position ) {
		if ( mRegions.topContent == null ) {
			return 2;
		} else {
			if ( position == 0 ) {
				return 6;
			} else {
				return 2;
			}
		}
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new BangumisViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		BangumisViewHolder viewHolderBangumi = ( BangumisViewHolder ) holder;
		int count = mRegions.topContent == null ? 0 : 1;
		if ( mRegions.topContent != null && position == 0 ) {
			RegionBodyContent topContent = mRegions.topContent;
			viewHolderBangumi.text.setVisibility( View.VISIBLE );
			viewHolderBangumi.initCover( viewHolderBangumi.cover );
			viewHolderBangumi.time.setText( topContent.updateTime );
			showData( viewHolderBangumi, topContent );

		} else {
			int realPosition = position - count;
			RegionBodyContent regionBodyContent = mRegions.bodyContents.get( realPosition );
//			if ( regionBodyContent == null ) {
//				viewHolderBangumi.root.setVisibility( View.GONE );
//				return;
//			}
			if ( realPosition % 3 == 0 ) {
				viewHolderBangumi.root.setPadding( DisplayUtil.dip2px( 10 ),//
						0,//
						DisplayUtil.dip2px( 10 / 3 ),//
						DisplayUtil.dip2px( 10 ) );
			} else if ( realPosition % 3 == 1 ) {
				viewHolderBangumi.root.setPadding( DisplayUtil.dip2px( 6.3f ),//
						0,//
						DisplayUtil.dip2px( 6.3f ),//
						DisplayUtil.dip2px( 10 ) );
			} else if ( realPosition % 3 == 2 ) {
				viewHolderBangumi.root.setPadding( DisplayUtil.dip2px( 10 / 3 ),//
						0,//
						DisplayUtil.dip2px( 10 ),//
						DisplayUtil.dip2px( 10 ) );
			}
			viewHolderBangumi.cover.setAspectRatio(0.7518f);
			viewHolderBangumi.text.setVisibility( View.VISIBLE );
			showData( viewHolderBangumi, regionBodyContent );
		}
	}

	private void showData( BangumisViewHolder viewHolderBangumi, RegionBodyContent topContent ) {
		if ( topContent.extendsStatus == 0 ) {
			viewHolderBangumi.text.setText( mActivity.getString( R.string.bangumi_rss_update_end ) );
			viewHolderBangumi.updateText.setVisibility( View.GONE );
		} else {
			viewHolderBangumi.text.setText( topContent.lastUpdate );
			viewHolderBangumi.updateText.setVisibility( View.VISIBLE );
		}
		viewHolderBangumi.title.setText( topContent.title );
		if ( topContent.images == null || topContent.images.size() <= 0 ) {
			ImageLoaderUtil.loadResourceImage( R.color.transparent, viewHolderBangumi.cover );
		} else {
			ImageLoaderUtil.loadNetImage( topContent.images.get( 0 ), viewHolderBangumi.cover );
		}

		viewHolderBangumi.itemView.setOnClickListener( new View.OnClickListener() {
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

	static class BangumisViewHolder extends RecyclerView.ViewHolder {
		public SimpleDraweeView cover;
		public TextView text;
		public TextView time;
		public TextView title;
		public TextView updateText;
		public View root;

		BangumisViewHolder( View view ) {
			super( view );
			cover = ( SimpleDraweeView ) view.findViewById( R.id.cover );
			time = ( TextView ) view.findViewById( R.id.time );
			title = ( TextView ) view.findViewById( R.id.title );
			updateText = ( TextView ) view.findViewById( R.id.update_text );
			text = ( TextView ) view.findViewById( R.id.text );
			root = ( View ) view.findViewById( R.id.home_item_root );
		}

		protected void initCover( SimpleDraweeView cover ) {
			ViewGroup.LayoutParams layoutParams = cover.getLayoutParams();
			if ( layoutParams == null || layoutParams.width <= 0 ) {
				int screenWidth = DisplayUtil.getScreenWidth( cover.getContext() );
				layoutParams = new FrameLayout.LayoutParams( screenWidth, ( int ) ( screenWidth / 2.3152175f ) );
			} else {
				layoutParams.height = ( int ) ( layoutParams.width / 2.3152175f );
			}
			this.cover.setLayoutParams( layoutParams );
			root.setPadding( DisplayUtil.dip2px( 10 ),//
					0,//
					DisplayUtil.dip2px( 10 ),//
					DisplayUtil.dip2px( 10 ) );
		}
	}
}
