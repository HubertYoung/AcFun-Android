//package com.hubertyoung.common.base;
//
//import android.os.Bundle;
//import android.widget.TextView;
//
//import com.hubertyoung.common.R;
//import com.hubertyoung.common.data.ItemData;
//import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
//import com.scwang.smartrefresh.layout.SmartRefreshLayout;
//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
//import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
//
//import java.util.Collection;
//import java.util.List;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.RecyclerView;
//
///**
// */
//public abstract class BaseListFragment< T extends AbsViewModel > extends AbsLifecycleFragment< T > implements OnRefreshListener {
//
//	protected RecyclerView mRecyclerView;
//
//	protected SmartRefreshLayout mSrlContainer;
//
//	protected Toolbar mToolbar;
//
//	protected TextView mTitle;
//
//	protected RecyclerView.LayoutManager layoutManager;
//
//	protected SectionedRecyclerViewAdapter mAdapter;
//
//	protected ItemData oldItems;
//
//	protected ItemData newItems;
//
//	@Override
//	protected int getLayoutResource() {
//		return R.layout.fragment_list;
//	}
//
//	@Override
//	public void initView( Bundle state ) {
//		super.initView( state );
//		mRecyclerView = findViewById( R.id.recycler_view );
//		mSrlContainer = findViewById( R.id.srl_container );
//		mToolbar = findViewById( R.id.view_toolbar );
//		mTitle = findViewById( R.id.toolbar_title );
//		oldItems = new ItemData();
//		newItems = new ItemData();
//
//
//		mAdapter = new SectionedRecyclerViewAdapter( null );
//		mRecyclerView.setAdapter( mAdapter );
//		mRecyclerView.setLayoutManager( createLayoutManager() );
////		mRecyclerView.addOnRefreshListener( this );
//		mRecyclerView.addOnScrollListener( new RecyclerView.OnScrollListener() {
//			@Override
//			public void onScrollStateChanged( @NonNull RecyclerView recyclerView, int newState ) {
////				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
////					if (activity != null) {
////						Glide.with(activity).resumeRequests();
////					}
////				} else {
////					if (activity != null) {
////						Glide.with(activity).pauseRequests();
////					}
////				}
//			}
//		} );
//
//		mSrlContainer.setOnRefreshListener( new OnRefreshListener() {
//			@Override
//			public void onRefresh( RefreshLayout refreshLayout ) {
//				mAdapter.getPageBean().refresh = true;
//				mSrlContainer.finishLoadMore();
//				mSrlContainer.setNoMoreData( false );
////				loadData();
//			}
//		} );
//		mSrlContainer.setOnLoadMoreListener( new OnLoadMoreListener() {
//			@Override
//			public void onLoadMore( RefreshLayout refreshLayout ) {
//				mAdapter.getPageBean().refresh = false;
////				loadData();
//			}
//		} );
//	}
//
//	@Override
//	protected void lazyLoad() {
//		if(mAdapter != null) {
//			mAdapter.getPageBean().refresh = false;
//		}
//	}
//	protected void setData( List< ? > collection ) {
//		if ( mAdapter.getPageBean().refresh ) {
//			onLoadMoreSuccess( collection );
//		} else {
//			onRefreshSuccess( collection );
//		}
//	}
//
//	protected void setBannerData( BannerListVo headAdList ) {
//		newItems.add( headAdList );
//	}
//
//	protected void onRefreshSuccess( Collection< ? > collection ) {
//		newItems.addAll( collection );
//		oldItems.clear();
//		oldItems.addAll( newItems );
//		if ( collection.size() < mAdapter.getPageBean().rows ) {
//			mRecyclerView.refreshComplete( oldItems, true );
//		} else {
//			mRecyclerView.refreshComplete( oldItems, false );
//		}
//		isRefresh = false;
//	}
//
//	protected void onLoadMoreSuccess( List< ? > collection ) {
//		isLoading = true;
//		isLoadMore = false;
//		oldItems.addAll( collection );
//		if ( collection.size() < mAdapter.getPageBean().rows ) {
//			mRecyclerView.loadMoreComplete( collection, true );
//		} else {
//			mRecyclerView.loadMoreComplete( collection, false );
//		}
//	}
//
//	/**
//	 * LayoutManager
//	 *
//	 * @return LayoutManager
//	 */
//	protected abstract RecyclerView.LayoutManager createLayoutManager();
//
//
//	protected void setTitle( String titleName ) {
//		mTitle.setText( titleName );
//	}
//
//
//}
