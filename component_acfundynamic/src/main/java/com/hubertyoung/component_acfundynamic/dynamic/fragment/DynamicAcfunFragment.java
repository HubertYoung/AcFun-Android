package com.hubertyoung.component_acfundynamic.dynamic.fragment;


import android.os.Bundle;

import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.base.BaseFragmentNew;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component_acfundynamic.R;
import com.hubertyoung.component_acfundynamic.dynamic.control.DynamicAcfunControl;
import com.hubertyoung.component_acfundynamic.dynamic.model.DynamicAcfunModelImp;
import com.hubertyoung.component_acfundynamic.dynamic.presenter.DynamicAcfunPresenterImp;
import com.hubertyoung.component_acfundynamic.dynamic.section.DynamicAcfunSection;
import com.hubertyoung.component_skeleton.skeleton.RecyclerViewSkeletonScreen;
import com.hubertyoung.component_skeleton.skeleton.Skeleton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <br>
 * function:AC动态
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/31 16:21
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfundynamic.dynamic.fragment
 */
public class DynamicAcfunFragment extends BaseFragmentNew< DynamicAcfunPresenterImp, DynamicAcfunModelImp > implements DynamicAcfunControl.View {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private String mParam1;
	private String mParam2;
	private SmartRefreshLayout srlContainer;
	private RecyclerView rvDynamicAcfun;
	private SectionedRecyclerViewAdapter mAdapter;
	private RecyclerViewSkeletonScreen mViewSkeletonScreen;
	private DynamicAcfunSection mDynamicAcfunSection;


	public DynamicAcfunFragment() {
	}

	public static DynamicAcfunFragment newInstance( String param1, String param2 ) {
		DynamicAcfunFragment fragment = new DynamicAcfunFragment();
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
		return R.layout.fragment_dynamic_acfun;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM( this, mModel );
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		srlContainer = ( SmartRefreshLayout ) findViewById( R.id.srl_container );
		rvDynamicAcfun = ( RecyclerView ) findViewById( R.id.rv_dynamic_acfun );
		initAction();
		initRecyclerView();
		initData();
	}

	private void initRecyclerView() {
		mAdapter = new SectionedRecyclerViewAdapter( null );
		GridLayoutManager layoutManager = new GridLayoutManager( activity, 6 );
		layoutManager.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize( int position ) {
				switch ( mAdapter.getSectionItemViewType( position ) ) {
					case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
						return 6;
					case SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER:
						return 6;
					case SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED: {
						int spanSizeLookup = mAdapter.getSectionForPosition( position ).spanSizeLookup;
						return spanSizeLookup;
					}
					default:
						return 6;
				}
			}
		} );

		mDynamicAcfunSection = new DynamicAcfunSection( ( BaseActivity ) activity );
		mAdapter.addSection( mDynamicAcfunSection );

		rvDynamicAcfun.setHasFixedSize( true );
		rvDynamicAcfun.setLayoutManager( layoutManager );
		mViewSkeletonScreen = Skeleton.bind( rvDynamicAcfun )//
				.adapter( mAdapter )//
				.shimmer( true )//
				.duration( 1200 )//
				.angle( 20 )//
				.load( R.layout.common_item_skeleton )//
				.show();
	}

	private void initAction() {
		srlContainer.setOnRefreshListener( new OnRefreshListener() {
			@Override
			public void onRefresh( RefreshLayout refreshLayout ) {
				mAdapter.getPageBean().refresh = true;
				mAdapter.getPageBean().page = mAdapter.getPageBean().startPage;
				srlContainer.finishLoadMore();
				srlContainer.setNoMoreData( false );
				loadData();
			}
		} );
		srlContainer.setOnLoadMoreListener( new OnLoadMoreListener() {
			@Override
			public void onLoadMore( RefreshLayout refreshLayout ) {
				mAdapter.getPageBean().refresh = false;
				loadData();
			}
		} );
	}

	private void initData() {

	}

	@Override
	protected void lazyLoad() {
		loadData();
	}

	@Override
	public void loadData() {
//		http://apipc.app.acfun.cn/v3/regions/recommendUp?pageNo=1&pageSize=20
		HashMap map = new HashMap<String,String>();
		map.put( "pageNo", mAdapter.getPageBean().getLoadPage() + "" );
		map.put( "pageSize", mAdapter.getPageBean().rows + "" );
		mPresenter.requestRecommendUp( map );
	}

	@Override
	public void showLoading( String title, int type ) {

	}

	@Override
	public void stopLoading() {
		srlContainer.finishRefresh();
		srlContainer.finishLoadMore();
		if ( mViewSkeletonScreen != null && mViewSkeletonScreen.isShowing() ) {
			mViewSkeletonScreen.hide();
		}
	}

	@Override
	public void showErrorTip( String msg ) {
		ToastUtil.showError( msg );
	}

	@Override
	public void setRecommendUpInfo( List< RegionBodyContent > regionBodyContentList ) {
		if ( mAdapter.getPageBean().refresh ) {
			mDynamicAcfunSection.setRecommendUpInfo( regionBodyContentList );
		} else {
			if ( regionBodyContentList != null && regionBodyContentList.size() > 0 ) {
				mDynamicAcfunSection.addRecommendUpInfo( regionBodyContentList );
			} else {
				srlContainer.setNoMoreData( true );
			}
		}
		mAdapter.notifyDataSetChanged();
	}
}
