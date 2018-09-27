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
 * @date:2018/5/14 11:14 AM
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.home.index.section
 */
class TopicBodySection extends Section {
	private final BaseActivity activity;
	private List< HomeIndexEntity.TopicListBean > data;

	public TopicBodySection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_topic_body ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null ? 0 : data.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new TopicBodyViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		TopicBodyViewHolder viewHolder = ( TopicBodyViewHolder ) holder;
		HomeIndexEntity.TopicListBean bean = data.get( position );
		viewHolder.mTvIndexTopicName.setText( bean.title );
		viewHolder.mTvIndexTopicDec.setText( bean.subtitle );
		viewHolder.mTvIndexTopicPrice.setText( String.format( "￥%s元起", bean.priceInfo ) );
		ImageLoaderUtils.getInstance().display( activity, viewHolder.mIvIndexTopicIcon, bean.itemPicUrl );

	}

	public void setTopicList( List< HomeIndexEntity.TopicListBean > data ) {
		this.data = data;
	}

	static class TopicBodyViewHolder extends RecyclerView.ViewHolder {
		View view;
		AppCompatImageView mIvIndexTopicIcon;
		AppCompatTextView mTvIndexTopicName;
		AppCompatTextView mTvIndexTopicPrice;
		AppCompatTextView mTvIndexTopicDec;

		TopicBodyViewHolder( View view ) {
			super( view );
			this.view = view;
			this.mIvIndexTopicIcon = ( AppCompatImageView ) view.findViewById( R.id.iv_index_topic_icon );
			this.mTvIndexTopicName = ( AppCompatTextView ) view.findViewById( R.id.tv_index_topic_name );
			this.mTvIndexTopicPrice = ( AppCompatTextView ) view.findViewById( R.id.tv_index_topic_price );
			this.mTvIndexTopicDec = ( AppCompatTextView ) view.findViewById( R.id.tv_index_topic_dec );
		}
	}
}
