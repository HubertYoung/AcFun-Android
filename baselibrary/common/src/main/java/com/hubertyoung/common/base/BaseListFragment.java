package com.hubertyoung.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.R;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.LoadingThemeUtil;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.utils.os.NetworkUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component_skeleton.skeleton.ViewReplacer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

/**
 */
public abstract class BaseListFragment< T extends AbsViewModel > extends AbsLifecycleFragment< T > {

    private RecyclerView mRecyclerView;

    private SmartRefreshLayout mSrlContainer;

    private Toolbar mToolbar;

    private TextView mTitle;

    private SectionedRecyclerViewAdapter mAdapter;

    private ViewReplacer mViewReplacer;
    private View mLoadingLayout;
    private View mErrorLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView( Bundle state ) {
        super.initView( state );
        mRecyclerView = findViewById( R.id.recycler_view );
        mSrlContainer = findViewById( R.id.srl_container );
        mToolbar = findViewById( R.id.view_toolbar );
        mTitle = findViewById( R.id.toolbar_title );

        mAdapter = new SectionedRecyclerViewAdapter( null );
        mRecyclerView.setLayoutManager( createLayoutManager() );
        mRecyclerView.setHasFixedSize( true );
        mRecyclerView.addOnScrollListener( new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged( @NonNull RecyclerView recyclerView, int newState ) {
//				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//					if (activity != null) {
//						Glide.with(activity).resumeRequests();
//					}
//				} else {
//					if (activity != null) {
//						Glide.with(activity).pauseRequests();
//					}
//				}
            }
        } );
        mRecyclerView.setAdapter( mAdapter );
        mViewReplacer = new ViewReplacer( mSrlContainer );

        showLoadingLayout();

        mSrlContainer.setOnRefreshListener( new OnRefreshListener() {
            @Override
            public void onRefresh( RefreshLayout refreshLayout ) {
                mAdapter.getPageBean().refresh = true;
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

    private void showLoadingLayout() {
        if ( mViewReplacer != null ) {
            if ( mLoadingLayout == null ) {
                mLoadingLayout = LayoutInflater.from( activity ).inflate( R.layout.widget_loading_holder, null );
            }
            mViewReplacer.replace( mLoadingLayout );

            if ( mViewReplacer.getCurrentView() != null ) {
                mViewReplacer.getCurrentView().setVisibility( View.VISIBLE );
                SimpleDraweeView simpleDraweeView = mViewReplacer.getCurrentView().findViewById( R.id.widget_loading_holder_gif );
                simpleDraweeView.getHierarchy().setPlaceholderImage( LoadingThemeUtil.getPageLoadingImages() );
                ImageLoaderUtil.loadNetImage( LoadingThemeUtil.getPageLoadingFileImages(), simpleDraweeView );
            }
        }
    }

    protected abstract void loadNewData();

    protected abstract void loadData();

    @Override
    protected void lazyLoad() {
        if ( NetworkUtil.isNetAvailable() ) {
            showLoadingLayout();
            loadData();
        } else {
            ToastUtil.showError( activity.getString( R.string.net_status_not_work ) );
            showErrorLayout();
        }
    }

    @Override
    public void stopLoading() {
        mSrlContainer.finishRefresh();
        mSrlContainer.finishLoadMore();
        if ( mViewReplacer != null ) {
            mViewReplacer.restore();
        }
    }

    /**
     * LayoutManager
     *
     * @return LayoutManager
     */
    protected abstract RecyclerView.LayoutManager createLayoutManager();

    /**
     * addSection
     *
     * @param section
     */
    protected void addSection( Section section ) {
        if ( mAdapter != null ) {
            mAdapter.addSection( section );
        }
    }

    /**
     * show error layout
     */
    @Override
    protected void showErrorLayout() {
        if ( mViewReplacer != null && mAdapter != null && mAdapter.mPageBean.refresh ) {
            if ( mErrorLayout == null ) {
                mErrorLayout = LayoutInflater.from( activity ).inflate( R.layout.widget_error_holder, null );
            }
            mViewReplacer.replace( mErrorLayout );
            if ( mViewReplacer.getCurrentView() != null ) {
                mViewReplacer.getCurrentView().setVisibility( View.VISIBLE );
                TextView refreshClick = mViewReplacer.getCurrentView().findViewById( R.id.refresh_click );
                refreshClick.setOnClickListener( v -> {
                    lazyLoad();
                } );
            }
        }else {
            stopLoading();
        }
    }

    /**
     * setTitleName
     *
     * @param titleName
     * @param isCenter
     */
    protected void setTitle( String titleName, boolean isCenter ) {
        if ( isCenter ) {
            mTitle.setText( titleName );
            mTitle.setVisibility( View.VISIBLE );
            mToolbar.setVisibility( View.GONE );
            mToolbar.setTitle( "" );
        } else {
            mTitle.setVisibility( View.GONE );
            mToolbar.setVisibility( View.VISIBLE );
            mToolbar.setTitle( titleName );
        }
    }

    /**
     * setToolbarVisible
     *
     * @param isShow
     */
    protected void setTitleLayoutVisible( boolean isShow ) {
        mToolbar.setVisibility( isShow ? View.VISIBLE : View.GONE );
    }


    @Override
    protected void initToolBar() {

    }

    public SectionedRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }

    public SmartRefreshLayout getSrlContainer() {
        return mSrlContainer;
    }
}
