package com.acty.component.home.index.section;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.acty.component.home.entity.HomeIndexEntity;
import com.acty.component_home.R;
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
 * @date:2018/5/14 2:46 PM
 * @since:V$VERSION
 * @desc:com.acty.component.home.index.section
 */
class FloorGoodsBodySection extends Section {
	private final BaseActivity activity;
	private List< HomeIndexEntity.FloorGoodsListBean.GoodsListBean > data;
	private static final int TYPE_1 = 1;//item
	private static final int TYPE_2 = 2;//more
	private String title;

	public FloorGoodsBodySection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_floor_goods_body ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null ? 0 : data.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new FloorGoodsBodyViewHolder( view, itemType );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		FloorGoodsBodyViewHolder viewHolder = ( FloorGoodsBodyViewHolder ) holder;
		HomeIndexEntity.FloorGoodsListBean.GoodsListBean bean = data.get( position );
		switch ( viewHolder.itemType ) {
			case TYPE_1:
				viewHolder.mClIndexFloorGoods.setVisibility( View.VISIBLE );
				viewHolder.mClIndexFloorGoodsMore.setVisibility( View.GONE );
				viewHolder.mTvIndexFloorGoodsName.setText( bean.name );
				viewHolder.mTvIndexFloorGoodsPrice.setText( String.format( "￥%s", bean.retailPrice ) );
				ImageLoaderUtils.getInstance().display( activity, viewHolder.mIvIndexFloorGoodsIcon, bean.listPicUrl );
				break;
			case TYPE_2:
				viewHolder.mClIndexFloorGoods.setVisibility( View.GONE );
				viewHolder.mClIndexFloorGoodsMore.setVisibility( View.VISIBLE );
				viewHolder.mTvIndexFloorGoodsMoreName.setText( bean.name );
				break;
		}

	}

	public void setFloorGoodsList( List< HomeIndexEntity.FloorGoodsListBean.GoodsListBean > goodsList, String title ) {
		this.data = goodsList;
		this.title = title;
	}

	@Override
	public int getItemViewType( int position ) {
		Log.e( "TAG", "position ==> " + position );
		return TextUtils.isEmpty( data.get( position ).goodsSn ) ? TYPE_2 : TYPE_1;
//		return 0;
	}

	static class FloorGoodsBodyViewHolder extends RecyclerView.ViewHolder {
		int itemType;
		View view;
		AppCompatImageView mIvIndexFloorGoodsIcon;
		AppCompatTextView mTvIndexFloorGoodsName;
		AppCompatTextView mTvIndexFloorGoodsPrice;
		ConstraintLayout mClIndexFloorGoods;
		ConstraintLayout mClIndexFloorGoodsMore;
		AppCompatTextView mTvIndexFloorGoodsMoreName;
		AppCompatImageView mTvIndexFloorGoodsMoreIcon;

		FloorGoodsBodyViewHolder( View view, int itemType ) {
			super( view );
			this.view = view;
			this.itemType = itemType;
			this.mIvIndexFloorGoodsIcon = ( AppCompatImageView ) view.findViewById( R.id.iv_index_floor_goods_icon );
			this.mTvIndexFloorGoodsName = ( AppCompatTextView ) view.findViewById( R.id.tv_index_floor_goods_name );
			this.mTvIndexFloorGoodsPrice = ( AppCompatTextView ) view.findViewById( R.id.tv_index_floor_goods_price );
			this.mClIndexFloorGoods = ( ConstraintLayout ) view.findViewById( R.id.cl_index_floor_goods );
			this.mClIndexFloorGoodsMore = ( ConstraintLayout ) view.findViewById( R.id.cl_index_floor_goods_more );
			this.mTvIndexFloorGoodsMoreName = ( AppCompatTextView ) view.findViewById( R.id.tv_index_floor_goods_more_name );
			this.mTvIndexFloorGoodsMoreIcon = ( AppCompatImageView ) view.findViewById( R.id.tv_index_floor_goods_more_icon );
		}
	}
}
