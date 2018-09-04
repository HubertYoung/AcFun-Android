package com.acty.component.acfunvideo.index.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acty.component.acfunvideo.entity.NewRecommendEntity;
import com.acty.component.acfunvideo.index.control.NewRecommendControl;
import com.acty.component.acfunvideo.index.model.NewRecommendModelImp;
import com.acty.component.acfunvideo.index.presenter.NewRecommendPresenterImp;
import com.acty.component.acfunvideo.index.section.NewRecommendVideosSection;
import com.acty.component_acfunvideo.R;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.common.utils.Utils;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component_skeleton.skeleton.RecyclerViewSkeletonScreen;
import com.hubertyoung.component_skeleton.skeleton.Skeleton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * <br>
 * function:推荐
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/3 18:39
 * @since:V1.0.0
 * @desc:com.acty.component.acfunvideo.index.fragment
 */
public class NewRecommendFragment extends BaseFragment< NewRecommendPresenterImp, NewRecommendModelImp > implements NewRecommendControl.View {
	private static final String ARG_PARAM1 = "id";
	private static final String ARG_PARAM2 = "param2";

	private String id;
	private String mParam2;
	private SmartRefreshLayout mSrlContainer;
	private RecyclerView mHomeRecommendLis;
	private SectionedRecyclerViewAdapter mAdapter;
	private RecyclerViewSkeletonScreen mViewSkeletonScreen;
//	private NewBangumiSection mNewBangumiSection;

	public static NewRecommendFragment newInstance( String param1, String param2 ) {
		NewRecommendFragment fragment = new NewRecommendFragment();
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
			id = getArguments().getString( ARG_PARAM1 );
			mParam2 = getArguments().getString( ARG_PARAM2 );
		}
	}

	@Override
	public void onViewCreated( View view, @Nullable Bundle savedInstanceState ) {
		mSrlContainer = ( SmartRefreshLayout ) findViewById( R.id.srl_container );
		mHomeRecommendLis = ( RecyclerView ) findViewById( R.id.home_recommend_lis );
		super.onViewCreated( view, savedInstanceState );
	}

	@Override
	protected void initToolBar() {

	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_home_recommend;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM( this, mModel );
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		initRecyclerView();
		initAction();
		loadData();
	}

	@Override
	public void loadData() {
		MyRequestMap map = new MyRequestMap();
		map.put( "channelId", "0" );
		mPresenter.requestNewRecommend( map );
	}

	private void initAction() {
		mSrlContainer.setOnRefreshListener( new OnRefreshListener() {
			@Override
			public void onRefresh( RefreshLayout refreshLayout ) {
//				mAdapter.getPageBean().refresh = true;
//				mSrlRefreshHomeIndex.finishLoadMore();
//				mSrlRefreshHomeIndex.setNoMoreData( false );
				loadData();
			}
		} );
		mSrlContainer.setOnLoadMoreListener( new OnLoadMoreListener() {
			@Override
			public void onLoadMore( RefreshLayout refreshLayout ) {
//				mAdapter.getPageBean().refresh = false;
				loadData();
			}
		} );
//		mBannerSection.setOnItemClickListener( new BannerSection.OnItemClickListener() {
//			@Override
//			public void onItemClickBanner( String url, String title, String dataJson ) {
//				ToastUtil.showSuccess( "跳转url ==> " + url );
//			}
//
//			@Override
//			public void onItemClickChannel( View v, String channelId, String title ) {
//				BrandDetailActivity.Companion.launch( ( BaseActivity ) activity, channelId );
//			}
//		} );
	}

	private void initRecyclerView() {
		mAdapter = new SectionedRecyclerViewAdapter();
		GridLayoutManager layoutManager = new GridLayoutManager( activity, 6 );
		layoutManager.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize( int position ) {
				switch ( mAdapter.getSectionItemViewType( position ) ) {
					case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
						return 6;
					default:
						return 3;
				}
			}
		} );
		mHomeRecommendLis.setHasFixedSize( true );
		mHomeRecommendLis.setLayoutManager( layoutManager );
//		mBannerSection = new BannerSection( ( BaseActivity ) activity );
//		mAdapter.addSection( mBannerSection );

//		mNewGoodsSection = new NewGoodsSection( ( BaseActivity ) activity );
//		mAdapter.addSection( mNewGoodsSection );
//		mHotGoodsSection = new HotGoodsSection( ( BaseActivity ) activity );
//		mAdapter.addSection( mHotGoodsSection );
//		mTopicSection = new TopicSection( ( BaseActivity ) activity );
//		mAdapter.addSection( mTopicSection );
		mViewSkeletonScreen = Skeleton.bind( mHomeRecommendLis )//
				.adapter( mAdapter )//
				.shimmer( true )//
				.duration( 1200 )//
				.angle( 20 )//
				.load( R.layout.common_item_skeleton )//
				.show();
//		mHomeRecommendLis.addItemDecoration( //
//				new HorizontalDividerItemDecoration.Builder( activity )//
//						.colorResId( R.color.line_bg )//
//						.size( 20 )//
//						.showLastDivider()//
//						.build() //
//		);
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
	public void setNewRecommendInfo( List< NewRecommendEntity > newRecommendEntityList ) {
//		mNewBangumiSection.setData(newRecommendEntityList);
		for (NewRecommendEntity recommendEntity : newRecommendEntityList) {
			switch (recommendEntity.schema) {
			    case Utils.videos:
					NewRecommendVideosSection mNewBangumiSection = new NewRecommendVideosSection( ( BaseActivity ) activity );
					mAdapter.addSection( mNewBangumiSection );
					mNewBangumiSection.setData( recommendEntity );
			        break;
			}
		}

		mAdapter.notifyDataSetChanged();
	}
}
