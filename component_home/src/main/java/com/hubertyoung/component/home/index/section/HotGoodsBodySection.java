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
 * @date:2018/5/13 9:57 PM
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.home.index.section
 */
class HotGoodsBodySection extends Section {
	private final BaseActivity activity;
	private List< HomeIndexEntity.HotGoodsListBean > data;

	public HotGoodsBodySection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_hot_goods_body ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null ? 0 : data.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new HotGoodsBodyViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		HotGoodsBodyViewHolder viewHolder = ( HotGoodsBodyViewHolder ) holder;
		HomeIndexEntity.HotGoodsListBean bean = data.get( position );
		viewHolder.mTvIndexHotGoodsName.setText( bean.name );
		viewHolder.mTvIndexHotGoodsPrice.setText( String.format( "￥%s", bean.retailPrice ) );
		ImageLoaderUtils.getInstance().display( activity, viewHolder.mIvIndexHotGoodsIcon, bean.listPicUrl );
	}

	public void setHotGoodsList( List< HomeIndexEntity.HotGoodsListBean > data ) {
		this.data = data;
	}

	static class HotGoodsBodyViewHolder extends RecyclerView.ViewHolder {
		View view;
		AppCompatImageView mIvIndexHotGoodsIcon;
		AppCompatTextView mTvIndexHotGoodsName;
		AppCompatTextView mTvIndexHotGoodsPrice;

		HotGoodsBodyViewHolder( View view ) {
			super( view );
			this.view = view;
			this.mIvIndexHotGoodsIcon = ( AppCompatImageView ) view.findViewById( R.id.iv_index_hot_goods_icon );
			this.mTvIndexHotGoodsName = ( AppCompatTextView ) view.findViewById( R.id.tv_index_hot_goods_name );
			this.mTvIndexHotGoodsPrice = ( AppCompatTextView ) view.findViewById( R.id.tv_index_hot_goods_price );
		}
	}
}
