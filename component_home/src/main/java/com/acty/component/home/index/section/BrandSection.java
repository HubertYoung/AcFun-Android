package com.acty.component.home.index.section;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acty.component.home.entity.HomeIndexEntity;
import com.acty.component_home.R;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.widget.decoration.HorizontalDividerItemDecoration;
import com.hubertyoung.common.widget.decoration.VerticalDividerItemDecoration;
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
 * @date:2018/5/10 8:09 PM
 * @since:V$VERSION
 * @desc:com.acty.component.home.index.section
 */
public class BrandSection extends Section {
	private final BaseActivity activity;
	private List< HomeIndexEntity.BrandListBean > data;
	private static SectionedRecyclerViewAdapter sAdapter;
	private static BrandBodySection sBrandBodySection;

	public BrandSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_brand ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null || data.isEmpty() ? 0 : 1;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new BrandViewHolder( activity, view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		BrandViewHolder viewHolder = ( BrandViewHolder ) holder;
		viewHolder.mTvHomeIndexHead.setText( "品牌制造商直供" );

		sBrandBodySection.setBrandList( data );
		sAdapter.notifyDataSetChanged();
	}

	public void setBrandList( List< HomeIndexEntity.BrandListBean > brandList ) {
		this.data = brandList;
	}

	static class BrandViewHolder extends RecyclerView.ViewHolder {
		View view;
		RecyclerView mRvBody;
		AppCompatTextView mTvHomeIndexHead;

		BrandViewHolder( BaseActivity activity, View view ) {
			super( view );
			this.view = view;
			this.mRvBody = ( RecyclerView ) view.findViewById( R.id.rv_body );
			this.mTvHomeIndexHead = ( AppCompatTextView ) view.findViewById( R.id.tv_home_index_head );


			GridLayoutManager manager = new GridLayoutManager( activity, 2 );
			mRvBody.setHasFixedSize( true );
			mRvBody.setNestedScrollingEnabled( false );
			sAdapter = new SectionedRecyclerViewAdapter();
			sBrandBodySection = new BrandBodySection( activity );
			sAdapter.addSection( sBrandBodySection );
			mRvBody.setAdapter( sAdapter );
			mRvBody.setLayoutManager( manager );
			mRvBody.addItemDecoration( new HorizontalDividerItemDecoration.Builder( activity ).colorResId( R.color.line_bg ).size( AutoUtils.getPercentHeightSizeBigger( 5 ) ).build() );
			mRvBody.addItemDecoration( new VerticalDividerItemDecoration.Builder( activity ).colorResId( R.color.line_bg ).size( AutoUtils.getPercentHeightSizeBigger( 5 ) ).build() );
		}
	}
}
