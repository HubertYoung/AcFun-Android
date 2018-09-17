package com.hubertyoung.component.home.index.section;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
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
 * @date:2018/5/11 10:22 AM
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.home.index.section
 */
class BrandBodySection extends Section {
	private final BaseActivity activity;
	private List< HomeIndexEntity.BrandListBean > data;

	public BrandBodySection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_brand_body ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null ? 0 : data.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new BrandBodyViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		BrandBodyViewHolder viewHolder = ( BrandBodyViewHolder ) holder;
		HomeIndexEntity.BrandListBean bean = data.get( position );
		viewHolder.mTvIndexBrandName.setText( bean.name );
		viewHolder.mTvIndexBrandDec.setText( String.format( "%s元起", bean.floorPrice) );
		ImageLoaderUtils.getInstance().display( activity, viewHolder.mIvIndexBrandIcon, bean.picUrl );
	}
	public void setBrandList( List< HomeIndexEntity.BrandListBean > brandList ) {
		this.data = brandList;
	}
	static class BrandBodyViewHolder extends RecyclerView.ViewHolder {
		View view;
		AppCompatImageView mIvIndexBrandIcon;
		AppCompatTextView mTvIndexBrandName;
		AppCompatTextView mTvIndexBrandDec;

		BrandBodyViewHolder( View view ) {
			super( view );
			this.view = view;
			this.mIvIndexBrandIcon = ( AppCompatImageView ) view.findViewById( R.id.iv_index_brand_icon );
			this.mTvIndexBrandName = ( AppCompatTextView ) view.findViewById( R.id.tv_index_brand_name );
			this.mTvIndexBrandDec = ( AppCompatTextView ) view.findViewById( R.id.tv_index_brand_dec );
		}
	}
}
