package com.acty.component.home.index.section;

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

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:Yang
 * @date:2018/5/12 11:45 PM
 * @since:V$VERSION
 * @desc:com.acty.component.home.index.section
 */
public class NewGoodsSection extends Section {
	private final BaseActivity activity;
	private List< HomeIndexEntity.NewGoodsListBean > data;

	public NewGoodsSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_new_goods ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null || data.isEmpty() ? 0 : 1;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new NewGoodsViewHolder( activity, view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		NewGoodsViewHolder viewHolder = ( NewGoodsViewHolder ) holder;
		viewHolder.mTvHomeIndexHead.setText( "周一周四 · 新品首发" );

		viewHolder.mNewGoodsBodySection.setNewGoodsList( data );
		viewHolder.sAdapter.notifyDataSetChanged();
	}

	public void setNewGoodsList( List< HomeIndexEntity.NewGoodsListBean > newGoodsList ) {
		this.data = newGoodsList;
	}

	static class NewGoodsViewHolder extends RecyclerView.ViewHolder {
		NewGoodsBodySection mNewGoodsBodySection;
		SectionedRecyclerViewAdapter sAdapter;

		View view;
		AppCompatTextView mTvHomeIndexHead;
		RecyclerView mRvBody;

		NewGoodsViewHolder( BaseActivity activity, View view ) {
			super( view );
			this.view = view;
			this.mTvHomeIndexHead = ( AppCompatTextView ) view.findViewById( R.id.tv_home_index_head );
			this.mRvBody = ( RecyclerView ) view.findViewById( R.id.rv_body );

			GridLayoutManager manager = new GridLayoutManager( activity, 2 );
			mRvBody.setHasFixedSize( true );
			mRvBody.setNestedScrollingEnabled( false );
			sAdapter = new SectionedRecyclerViewAdapter();
			mNewGoodsBodySection = new NewGoodsBodySection( activity );
			sAdapter.addSection( mNewGoodsBodySection );
			mRvBody.setAdapter( sAdapter );
			mRvBody.setLayoutManager( manager );
			GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration( activity, GridDividerItemDecoration.GRID_DIVIDER_VERTICAL );
			dividerItemDecoration.setVerticalDivider( activity.getResources().getDrawable(R.drawable.home_brand_divider) );
			dividerItemDecoration.setHorizontalDivider( activity.getResources().getDrawable(R.drawable.home_brand_divider));
			mRvBody.addItemDecoration( dividerItemDecoration );
		}
	}
}
