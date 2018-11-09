package com.hubertyoung.component.acfunvideo.index.fragment;

import android.os.Bundle;
import android.view.View;

import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component.acfunvideo.entity.ChannelOperate;
import com.hubertyoung.component.acfunvideo.index.control.ChannelControl;
import com.hubertyoung.component.acfunvideo.index.model.ChannelModelImp;
import com.hubertyoung.component.acfunvideo.index.presenter.ChannelPresenterImp;
import com.hubertyoung.component.acfunvideo.index.section.ViewChannelSection;
import com.hubertyoung.component.acfunvideo.index.section.ViewActivitySection;
import com.hubertyoung.component_acfunvideo.R;
import com.hubertyoung.component_skeleton.skeleton.RecyclerViewSkeletonScreen;
import com.hubertyoung.component_skeleton.skeleton.Skeleton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class ChannelFragment extends BaseFragment< ChannelPresenterImp, ChannelModelImp > implements ChannelControl.View {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private SmartRefreshLayout mSrlContainer;
	private RecyclerView mRecyclerView;

	private String mParam1;
	private String mParam2;
	private SectionedRecyclerViewAdapter mAdapter;
	private RecyclerViewSkeletonScreen mViewSkeletonScreen;
	private ViewChannelSection mChannelSection;
	private ViewActivitySection mViewActivitySection;
	private int mNext;

	public ChannelFragment() {
	}

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
	protected void initToolBar() {

	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_channel;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM( this, mModel );
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		mSrlContainer = ( SmartRefreshLayout ) findViewById( R.id.srl_container );
		mRecyclerView = ( RecyclerView ) findViewById( R.id.fragment_channel_recycler_view );
		initRecyclerView();
		initAction();
		loadData();
	}

	private void initAction() {
		mSrlContainer.setOnRefreshListener( new OnRefreshListener() {
			@Override
			public void onRefresh( RefreshLayout refreshLayout ) {
				mAdapter.getPageBean().refresh = true;
				mAdapter.getPageBean().page = mAdapter.getPageBean().startPage;
				mSrlContainer.finishLoadMore();
				mSrlContainer.setNoMoreData( false );
				loadData();
			}
		} );
		mSrlContainer.setOnLoadMoreListener( new OnLoadMoreListener() {
			@Override
			public void onLoadMore( RefreshLayout refreshLayout ) {
				mAdapter.getPageBean().refresh = false;
				loadNewData();
			}
		} );
	}

	private void initRecyclerView() {
		mAdapter = new SectionedRecyclerViewAdapter( null );
		GridLayoutManager layoutManager = new GridLayoutManager( activity, 4 );
		layoutManager.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize( int position ) {
				switch ( mAdapter.getSectionItemViewType( position ) ) {
					case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
						return 4;
					case SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER:
						return 4;
					case SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED: {
						int spanSizeLookup = mAdapter.getSectionForPosition( position ).spanSizeLookup;
						return spanSizeLookup;
					}
					default:
						return 4;
				}
			}
		} );

		mChannelSection = new ViewChannelSection( ( BaseActivity ) activity );
		mAdapter.addSection( mChannelSection );
		mViewActivitySection = new ViewActivitySection( ( BaseActivity ) activity );
		mAdapter.addSection( mViewActivitySection );

		mRecyclerView.setHasFixedSize( true );
		mRecyclerView.setLayoutManager( layoutManager );
		mViewSkeletonScreen = Skeleton.bind( mRecyclerView )//
				.adapter( mAdapter )//
				.shimmer( true )//
				.duration( 1200 )//
				.angle( 20 )//
				.load( R.layout.common_item_skeleton )//
				.show();
	}

	@Override
	public void showLoading( String title, int type ) {

	}

	@Override
	public void stopLoading() {
		mSrlContainer.finishRefresh();
		mSrlContainer.finishLoadMore();
		if ( mViewSkeletonScreen != null && mViewSkeletonScreen.isShowing() ) {
			mViewSkeletonScreen.hide();
		}
	}

	@Override
	public void showErrorTip( String msg ) {
		ToastUtil.showError( msg );
	}

	@Override
	public void loadData() {
		MyRequestMap map = new MyRequestMap();
		map.put( "pos", "0" );
		mPresenter.requestChannel( map,0 );
	}

	private void loadNewData() {
		MyRequestMap map = new MyRequestMap();
		map.put( "pos", mNext + "" );
		mPresenter.requestChannel( map, 1 );
	}

	@Override
	public void setChannelOperateInfo( ChannelOperate channelOperate ) {
		mNext = channelOperate.next;
		mChannelSection.setChannelList( channelOperate.channelList );
		mViewActivitySection.setOperateList( channelOperate.operateList );
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void addChannelOperateInfo( ChannelOperate channelOperate ) {
		mNext = channelOperate.next;
		if( channelOperate != null && channelOperate.operateList != null && !channelOperate.operateList.isEmpty()) {
			mViewActivitySection.addOperateList( channelOperate.operateList );
			mAdapter.notifyDataSetChanged();
		}else{
			mSrlContainer.setNoMoreData( true );
		}
	}
}
