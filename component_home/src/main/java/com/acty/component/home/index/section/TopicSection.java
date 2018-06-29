package com.acty.component.home.index.section;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acty.component.home.entity.HomeIndexEntity;
import com.acty.component_home.R;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.widget.decoration.VerticalDividerItemDecoration;
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
 * @date:2018/5/14 11:09 AM
 * @since:V$VERSION
 * @desc:com.acty.component.home.index.section
 */
public class TopicSection extends Section {
	private final BaseActivity activity;
	private List< HomeIndexEntity.TopicListBean > data;

	public TopicSection( BaseActivity activity ) {
		super( new SectionParameters.Builder( R.layout.home_item_topic ).build() );
		this.activity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null || data.isEmpty() ? 0 : 1;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new TopicViewHolder( activity, view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		TopicViewHolder viewHolder = ( TopicViewHolder ) holder;
		viewHolder.mTvHomeIndexHead.setText( "专题精选" );

		viewHolder.mTopicBodySection.setTopicList( data );
		viewHolder.mAdapter.notifyDataSetChanged();
	}

	public void setTopicList( List< HomeIndexEntity.TopicListBean > topicList ) {
		this.data = topicList;
	}

	static class TopicViewHolder extends RecyclerView.ViewHolder {
		SectionedRecyclerViewAdapter mAdapter;
		View view;
		AppCompatTextView mTvHomeIndexHead;
		RecyclerView mRvBody;
		TopicBodySection mTopicBodySection;

		TopicViewHolder( BaseActivity activity, View view ) {
			super( view );
			this.view = view;
			this.mTvHomeIndexHead = ( AppCompatTextView ) view.findViewById( R.id.tv_home_index_head );
			this.mRvBody = ( RecyclerView ) view.findViewById( R.id.rv_body );

			LinearLayoutManager manager = new LinearLayoutManager( activity, LinearLayoutManager.HORIZONTAL, false );
			mRvBody.setHasFixedSize( true );
			mRvBody.setNestedScrollingEnabled( false );
			mAdapter = new SectionedRecyclerViewAdapter();
			mTopicBodySection = new TopicBodySection( activity );
			mAdapter.addSection( mTopicBodySection );
			mRvBody.setAdapter( mAdapter );
			mRvBody.setLayoutManager( manager );
			mRvBody.addItemDecoration( new VerticalDividerItemDecoration.Builder( activity ).colorResId( R.color.white )
					.size( -20 )
					.build() );
		}
	}
}
