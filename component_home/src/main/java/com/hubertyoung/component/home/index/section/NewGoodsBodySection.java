package com.hubertyoung.component.home.index.section;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.hubertyoung.component.home.entity.HomeIndexEntity;
import com.hubertyoung.component_home.R;
import com.hubertyoung.common.ImageLoaderUtils;
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
 * @desc:com.hubertyoung.component.home.index.section
 */
class NewGoodsBodySection extends Section {
	private final BaseActivity activity;
	private List< HomeIndexEntity.NewGoodsListBean > data;

	public NewGoodsBodySection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_new_goods_body ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null ? 0 : data.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new NewGoodsBodyViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		NewGoodsBodyViewHolder viewHolder = ( NewGoodsBodyViewHolder ) holder;
		HomeIndexEntity.NewGoodsListBean bean = data.get( position );
		viewHolder.mTvIndexNewGoodsName.setText( bean.name );
		viewHolder.mTvIndexNewGoodsPrice.setText( String.format( "￥%s", bean.retailPrice ) );
		ImageLoaderUtils.getInstance().display( activity, viewHolder.mIvIndexNewGoodsIcon, bean.listPicUrl );
	}

	public void setNewGoodsList( List< HomeIndexEntity.NewGoodsListBean > data ) {
		this.data = data;
	}

	static class NewGoodsBodyViewHolder extends RecyclerView.ViewHolder {
		View view;
		AppCompatImageView mIvIndexNewGoodsIcon;
		AppCompatTextView mTvIndexNewGoodsName;
		AppCompatTextView mTvIndexNewGoodsPrice;

		NewGoodsBodyViewHolder( View view ) {
			super( view );
			this.view = view;
			this.mIvIndexNewGoodsIcon = ( AppCompatImageView ) view.findViewById( R.id.iv_index_new_goods_icon );
			this.mTvIndexNewGoodsName = ( AppCompatTextView ) view.findViewById( R.id.tv_index_new_goods_name );
			this.mTvIndexNewGoodsPrice = ( AppCompatTextView ) view.findViewById( R.id.tv_index_new_goods_price );
		}
	}
}
