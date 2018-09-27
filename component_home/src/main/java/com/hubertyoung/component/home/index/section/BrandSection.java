package com.hubertyoung.component.home.index.section;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.hubertyoung.component.home.entity.HomeIndexEntity;
import com.hubertyoung.component_home.R;
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
 * @date:2018/5/10 8:09 PM
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.home.index.section
 */
public class BrandSection extends Section {
	private final BaseActivity activity;
	private List< HomeIndexEntity.BrandListBean > data;

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

		viewHolder.sBrandBodySection.setBrandList( data );
		viewHolder.sAdapter.notifyDataSetChanged();
	}

	public void setBrandList( List< HomeIndexEntity.BrandListBean > brandList ) {
		this.data = brandList;
	}

	static class BrandViewHolder extends RecyclerView.ViewHolder {
		SectionedRecyclerViewAdapter sAdapter;
		BrandBodySection sBrandBodySection;

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
			GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration( activity, GridDividerItemDecoration.GRID_DIVIDER_VERTICAL );
			dividerItemDecoration.setVerticalDivider( activity.getResources().getDrawable(R.drawable.home_brand_divider) );
			dividerItemDecoration.setHorizontalDivider( activity.getResources().getDrawable(R.drawable.home_brand_divider));
			mRvBody.addItemDecoration( dividerItemDecoration );
		}
	}
}
