package com.acty.component.home.index.section;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acty.component.home.entity.HomeIndexEntity;
import com.acty.component_home.R;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.widget.decoration.GridDividerItemDecoration;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:Yang
 * @date:2018/5/14 2:42 PM
 * @since:V$VERSION
 * @desc:com.acty.component.home.index.section
 */
public class FloorGoodsSection extends Section {
	private final BaseActivity activity;
	private HomeIndexEntity.FloorGoodsListBean data;

	public FloorGoodsSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_floor_goods ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null || data.goodsList == null ? 0 : 1;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new FloorGoodsViewHolder( activity, view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		FloorGoodsViewHolder viewHolder = ( FloorGoodsViewHolder ) holder;
		viewHolder.mTvHomeIndexHead.setText( data.name );

		viewHolder.mFloorGoodsBodySection.setFloorGoodsList( data.goodsList);
		viewHolder.mAdapter.notifyDataSetChanged();
	}

	public void setFloorGoodsBean( HomeIndexEntity.FloorGoodsListBean floorGoodsListBean ) {
		this.data = floorGoodsListBean;
	}

	static class FloorGoodsViewHolder extends RecyclerView.ViewHolder {
		View view;
		AppCompatTextView mTvHomeIndexHead;
		RecyclerView mRvBody;
		SectionedRecyclerViewAdapter mAdapter;
		FloorGoodsBodySection mFloorGoodsBodySection;

		FloorGoodsViewHolder( BaseActivity activity, View view ) {
			super( view );
			this.view = view;
			this.mTvHomeIndexHead = ( AppCompatTextView ) view.findViewById( R.id.tv_home_index_head );
			this.mRvBody = ( RecyclerView ) view.findViewById( R.id.rv_body );

			GridLayoutManager manager = new GridLayoutManager( activity, 2 );
			mRvBody.setHasFixedSize( true );
			mRvBody.setNestedScrollingEnabled( false );
			mAdapter = new SectionedRecyclerViewAdapter();
			mFloorGoodsBodySection = new FloorGoodsBodySection( activity );
			mAdapter.addSection( mFloorGoodsBodySection );
			mRvBody.setAdapter( mAdapter );
			mRvBody.setLayoutManager( manager );
			GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration( activity, GridDividerItemDecoration.GRID_DIVIDER_VERTICAL );
			dividerItemDecoration.setVerticalSize( 5 , R.color.line_bg );
			dividerItemDecoration.setHorizontalSize( 5 , R.color.line_bg );
			mRvBody.addItemDecoration( dividerItemDecoration );
		}
	}

	static class FloorGoodsFootViewHolder extends RecyclerView.ViewHolder {
		View view;
		AppCompatTextView mTvIndexFloorGoodsMoreName;
		AppCompatImageView mTvIndexFloorGoodsMoreIcon;

		FloorGoodsFootViewHolder( View view ) {
			super( view );
			this.view = view;
			this.mTvIndexFloorGoodsMoreName = ( AppCompatTextView ) view.findViewById( R.id.tv_index_floor_goods_more_name );
			this.mTvIndexFloorGoodsMoreIcon = ( AppCompatImageView ) view.findViewById( R.id.tv_index_floor_goods_more_icon );
		}
	}
}
