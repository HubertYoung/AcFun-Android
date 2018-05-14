package com.acty.component.home.index.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acty.component.home.entity.HomeIndexEntity;
import com.acty.component.home.index.control.IndexRootControl;
import com.acty.component.home.index.model.IndexRootModelImp;
import com.acty.component.home.index.presenter.IndexRootPresenterImp;
import com.acty.component.home.index.section.BannerSection;
import com.acty.component.home.index.section.BrandSection;
import com.acty.component.home.index.section.FloorGoodsSection;
import com.acty.component.home.index.section.HotGoodsSection;
import com.acty.component.home.index.section.NewGoodsSection;
import com.acty.component.home.index.section.TopicSection;
import com.acty.component_banner.banner.BannerEntity;
import com.acty.component_home.R;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.utils.BarUtils;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.common.widget.decoration.HorizontalDividerItemDecoration;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.kento.component_skeleton.skeleton.RecyclerViewSkeletonScreen;
import com.kento.component_skeleton.skeleton.Skeleton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * function:首页
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 10:18 AM
 * @since:V1.0
 * @desc:com.kento.component.home.index.fragment
 */
public class IndexRootFragment extends BaseFragment< IndexRootPresenterImp, IndexRootModelImp > implements IndexRootControl.View {

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private String mParam1;
	private String mParam2;
	@NonNull
	private SmartRefreshLayout mSrlRefreshHomeIndex;
	@NonNull
	private RecyclerView mRvHomeIndex;

	private SectionedRecyclerViewAdapter mAdapter;
	private BannerSection mBannerSection;
	private RecyclerViewSkeletonScreen mViewSkeletonScreen;
	private BrandSection mBrandSection;
	private NewGoodsSection mNewGoodsSection;
	private HotGoodsSection mHotGoodsSection;
	private TopicSection mTopicSection;

	public IndexRootFragment() {
	}

	public static IndexRootFragment newInstance( String param1, String param2 ) {
		IndexRootFragment fragment = new IndexRootFragment();
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
		return R.layout.home_fragment_index_root;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM( this, mModel );
	}

	@Override
	public void onViewCreated( View view, @Nullable Bundle savedInstanceState ) {
		mSrlRefreshHomeIndex = findViewById( R.id.srl_refresh_home_index );
		mRvHomeIndex = findViewById( R.id.rv_home_index );
		super.onViewCreated( view, savedInstanceState );
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		BarUtils.setPaddingSmart( mSrlRefreshHomeIndex );
		initRecyclerView();
		initAction();
		loadData();
	}

	private void initAction() {
		mSrlRefreshHomeIndex.setOnRefreshListener( new OnRefreshListener() {
			@Override
			public void onRefresh( RefreshLayout refreshLayout ) {
				mAdapter.getPageBean().refresh = true;
				mSrlRefreshHomeIndex.finishLoadMore();
				mSrlRefreshHomeIndex.setNoMoreData( false );
				loadData();
			}
		} );
		mSrlRefreshHomeIndex.setOnLoadMoreListener( new OnLoadMoreListener() {
			@Override
			public void onLoadMore( RefreshLayout refreshLayout ) {
				mAdapter.getPageBean().refresh = false;
				loadData();
			}
		} );
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager( activity );
		mRvHomeIndex.setHasFixedSize( true );
		mRvHomeIndex.setLayoutManager( layoutManager );
		mAdapter = new SectionedRecyclerViewAdapter();
		mBannerSection = new BannerSection( ( BaseActivity ) activity );
		mAdapter.addSection( mBannerSection );
		mBrandSection = new BrandSection( ( BaseActivity ) activity );
		mAdapter.addSection( mBrandSection );
		mNewGoodsSection = new NewGoodsSection( ( BaseActivity ) activity );
		mAdapter.addSection( mNewGoodsSection );
		mHotGoodsSection = new HotGoodsSection( ( BaseActivity ) activity );
		mAdapter.addSection( mHotGoodsSection );
		mTopicSection = new TopicSection( ( BaseActivity ) activity );
		mAdapter.addSection( mTopicSection );
		mViewSkeletonScreen = Skeleton.bind( mRvHomeIndex ).adapter( mAdapter ).shimmer( true ).duration( 1200 ).angle( 20 ).load( R.layout.common_item_skeleton ).show();
		mRvHomeIndex.addItemDecoration( new HorizontalDividerItemDecoration.Builder( activity ).colorResId( R.color.line_bg )
				.size( AutoUtils.getPercentHeightSizeBigger( 20 ) )
				.showLastDivider()
				.build() );
	}

	@Override
	public void loadData() {
		MyRequestMap map = new MyRequestMap();
		mPresenter.requestHomeIndex( map );
	}

	@Override
	public void showLoading( String title, int type ) {

	}

	@Override
	public void stopLoading() {
		mSrlRefreshHomeIndex.finishRefresh();
		mSrlRefreshHomeIndex.finishLoadMore();
		if ( mViewSkeletonScreen != null && mViewSkeletonScreen.isShowing() ) {
			mViewSkeletonScreen.hide();
		}
	}

	@Override
	public void showErrorTip( String msg ) {
		ToastUtil.showError( msg );
	}

	@Override
	public void setHomeIndexInfo( HomeIndexEntity homeIndexEntity ) {

		List< HomeIndexEntity.BannerBean > bannerBeanList = homeIndexEntity.banner;
		ArrayList< BannerEntity > bannerList = new ArrayList<>();
		for (HomeIndexEntity.BannerBean bannerBean : bannerBeanList) {
			bannerList.add( new BannerEntity( bannerBean.url, bannerBean.link ) );
		}
		//轮播图
		mBannerSection.setBannerList( bannerList );
		mBannerSection.setChannelList( homeIndexEntity.channel );
		//品牌制造商直供
		mBrandSection.setBrandList( homeIndexEntity.brandList );
		mNewGoodsSection.setNewGoodsList( homeIndexEntity.newGoodsList );
		mHotGoodsSection.setHotGoodsList( homeIndexEntity.hotGoodsList );
		mTopicSection.setTopicList( homeIndexEntity.topicList);
		for (HomeIndexEntity.FloorGoodsListBean floorGoodsListBean : homeIndexEntity.floorGoodsList) {
			FloorGoodsSection floorGoodsSection = new FloorGoodsSection( ( BaseActivity ) activity );
			mAdapter.addSection( floorGoodsSection );
			List< HomeIndexEntity.FloorGoodsListBean.GoodsListBean > goodsList = floorGoodsListBean.goodsList;
			HomeIndexEntity.FloorGoodsListBean.GoodsListBean goodsListBean = new HomeIndexEntity.FloorGoodsListBean.GoodsListBean();
			goodsListBean.goodsId = floorGoodsListBean.floorGoodsid;
			goodsListBean.name = "更多"+floorGoodsListBean.name+"好物";
			goodsList.add( goodsListBean );
			floorGoodsSection.setFloorGoodsBean( floorGoodsListBean );
		}
		mAdapter.notifyDataSetChanged();
	}
}
