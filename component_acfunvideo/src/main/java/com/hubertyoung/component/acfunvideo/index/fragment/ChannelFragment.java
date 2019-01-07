package com.hubertyoung.component.acfunvideo.index.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hubertyoung.common.base.BaseListFragment;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.entity.ChannelOperate;
import com.hubertyoung.component.acfunvideo.index.section.ViewActivitySection;
import com.hubertyoung.component.acfunvideo.index.section.ViewChannelSection;
import com.hubertyoung.component.acfunvideo.index.vm.ChannelViewModel;

/**
 * <br>
 * function:首页视频分区tab
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/7 16:34
 * @since:V1.0.0
 * @desc:com.hubertyoung.component.acfunvideo.index.fragment
 */
public class ChannelFragment extends BaseListFragment< ChannelViewModel > {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;
	private ViewChannelSection mChannelSection;
	private ViewActivitySection mViewActivitySection;
	private int mNext;

	public static ChannelFragment newInstance( String param1, String param2 ) {
		ChannelFragment fragment = new ChannelFragment();
		Bundle args = new Bundle();
		args.putString( ARG_PARAM1, param1 );
		args.putString( ARG_PARAM2, param2 );
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		if ( getArguments() != null ) {
			mParam1 = getArguments().getString( ARG_PARAM1 );
			mParam2 = getArguments().getString( ARG_PARAM2 );
		}
	}

	@Override
	public void initView( Bundle state ) {
		super.initView( state );
		setTitleLayoutVisible( false );
		mChannelSection = new ViewChannelSection( mActivity );
		addSection( mChannelSection );
		mViewActivitySection = new ViewActivitySection( mActivity );
		addSection( mViewActivitySection );
	}

	@Override
	public void showLoading( String title ) {
		super.showLoading( title );
		Log.e( "TAG", "" );
	}

	@Override
	protected RecyclerView.LayoutManager createLayoutManager() {
		GridLayoutManager layoutManager = new GridLayoutManager( mActivity, 4 );
		layoutManager.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize( int position ) {
				switch ( getAdapter().getSectionItemViewType( position ) ) {
					case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
						return 4;
					case SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER:
						return 4;
					case SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED: {
						int spanSizeLookup = getAdapter().getSectionForPosition( position ).spanSizeLookup;
						return spanSizeLookup;
					}
					default:
						return 4;
				}
			}
		} );
		return layoutManager;
	}

	//	public void showErrorTip( String msg ) {
//		ToastUtil.showError( msg );
//	}
	@Override
	protected void loadData() {
		mViewModel.requestChannel( "0", 0 );
	}

	@Override
	protected void loadNewData() {
		mViewModel.requestChannel( mNext + "", 1 );
	}

	@Override
	protected void dataObserver() {
		registerObserver( VideoConstants.EVENT_KEY_CHANNEL_OPERATE, ChannelOperate.class ).observe( this, new Observer< ChannelOperate >() {
			@Override
			public void onChanged( ChannelOperate channelOperate ) {
				setChannelOperateInfo( channelOperate );
			}
		} );
		registerObserver( VideoConstants.EVENT_KEY_CHANNEL_OPERATE_ADD, ChannelOperate.class ).observe( this, new Observer< ChannelOperate >() {
			@Override
			public void onChanged( ChannelOperate channelOperate ) {
				addChannelOperateInfo( channelOperate );
			}
		} );
	}

	public void setChannelOperateInfo( ChannelOperate channelOperate ) {
		mNext = channelOperate.next;
		mChannelSection.setChannelList( channelOperate.channelList );
		mViewActivitySection.setOperateList( channelOperate.operateList );
		getAdapter().notifyDataSetChanged();
	}

	public void addChannelOperateInfo( ChannelOperate channelOperate ) {
		mNext = channelOperate.next;
		if ( channelOperate != null && channelOperate.operateList != null && !channelOperate.operateList.isEmpty() ) {
			mViewActivitySection.addOperateList( channelOperate.operateList );
			getAdapter().notifyDataSetChanged();
		} else {
			getSrlContainer().setNoMoreData( true );
		}
	}
}
