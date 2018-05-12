package com.acty.component.home.index.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acty.component.home.entity.HomeIndexEntity;
import com.acty.component_home.R;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:Yang
 * @date:2018/5/12 11:54 PM
 * @since:V$VERSION
 * @desc:com.acty.component.home.index.section
 */
class NewGoodsBodySection extends Section{
	private final BaseActivity activity;
	private List< HomeIndexEntity.NewGoodsListBean > data;

	public NewGoodsBodySection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_brand_body ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null ? 0 : data.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return null;
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {

	}

	public void setNewGoodsList( List< HomeIndexEntity.NewGoodsListBean > data ) {
		this.data = data;
	}
}
