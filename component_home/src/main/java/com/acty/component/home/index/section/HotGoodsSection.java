package com.acty.component.home.index.section;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acty.component.home.entity.HomeIndexEntity;
import com.acty.component_home.R;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.widget.decoration.HorizontalDividerItemDecoration;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:Yang
 * @date:2018/5/13 9:48 PM
 * @since:V$VERSION
 * @desc:com.acty.component.home.index.section
 */
public class HotGoodsSection extends Section {
	private final BaseActivity activity;
	private List< HomeIndexEntity.HotGoodsListBean > data;

	public HotGoodsSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_hot_goods ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null || data.isEmpty() ? 0 : 1;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new HotGoodsViewHolder( activity, view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		HotGoodsViewHolder viewHolder = ( HotGoodsViewHolder ) holder;
		viewHolder.mTvHomeIndexHead.setText( "人气推荐" );

		viewHolder.mHotGoodsBodySection.setHotGoodsList( data );
		viewHolder.mAdapter.notifyDataSetChanged();
	}

	public void setHotGoodsList( List< HomeIndexEntity.HotGoodsListBean > hotGoodsList ) {
		this.data = hotGoodsList;
	}

	static class HotGoodsViewHolder extends RecyclerView.ViewHolder {
		View view;
		AppCompatTextView mTvHomeIndexHead;
		RecyclerView mRvBody;
		SectionedRecyclerViewAdapter mAdapter;
		HotGoodsBodySection mHotGoodsBodySection;

		HotGoodsViewHolder( BaseActivity activity, View view ) {
			super( view );
			this.view = view;
			this.mTvHomeIndexHead = ( AppCompatTextView ) view.findViewById( R.id.tv_home_index_head );
			this.mRvBody = ( RecyclerView ) view.findViewById( R.id.rv_body );

			LinearLayoutManager manager = new LinearLayoutManager( activity );
			mRvBody.setHasFixedSize( true );
			mRvBody.setNestedScrollingEnabled( false );
			mAdapter = new SectionedRecyclerViewAdapter();
			mHotGoodsBodySection = new HotGoodsBodySection( activity );
			mAdapter.addSection( mHotGoodsBodySection );
			mRvBody.setAdapter( mAdapter );
			mRvBody.setLayoutManager( manager );
			mRvBody.addItemDecoration( new HorizontalDividerItemDecoration.Builder( activity ).colorResId( R.color.line_bg )
					.size( AutoUtils.getPercentHeightSizeBigger( 1 ) )
					.showLastDivider()
					.margin( AutoUtils.getPercentHeightSizeBigger( 10 ) )
					.build() );
		}
	}
}
