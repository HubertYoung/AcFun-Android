package com.hubertyoung.component_acfunarticle.mine.fragment;


import android.os.Bundle;

import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component_acfunarticle.R;
import com.hubertyoung.component_acfunarticle.entity.ArticleRecommendEntity;
import com.hubertyoung.component_acfunarticle.mine.control.ArticleRecommendControl;
import com.hubertyoung.component_acfunarticle.mine.model.ArticleRecommendModelImp;
import com.hubertyoung.component_acfunarticle.mine.presenter.ArticleRecommendPresenterImp;
import com.hubertyoung.component_skeleton.skeleton.RecyclerViewSkeletonScreen;
import com.hubertyoung.component_skeleton.skeleton.Skeleton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * <br>
 * function:推荐
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/17 13:34
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunarticle.mine.fragment
 */
public class ArticleRecommendFragment extends BaseFragment<ArticleRecommendPresenterImp,ArticleRecommendModelImp > implements ArticleRecommendControl.View {
	private static final String ARG_PARAM1 = "channelId";
	private static final String ARG_PARAM2 = "param2";
	private String mChannelId;
	private String mParam2;

	private SmartRefreshLayout mSrlContainer;
	private RecyclerView mRecyclerView;
	private SectionedRecyclerViewAdapter mAdapter;
	private RecyclerViewSkeletonScreen mViewSkeletonScreen;


	public ArticleRecommendFragment() {
		// Required empty public constructor
	}

	public static ArticleRecommendFragment newInstance( String channelId, String param2 ) {
		ArticleRecommendFragment fragment = new ArticleRecommendFragment();
		Bundle args = new Bundle();
		args.putString( ARG_PARAM1, channelId );
		args.putString( ARG_PARAM2, param2 );
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		if ( getArguments() != null ) {
			mChannelId = getArguments().getString( ARG_PARAM1 );
			mParam2 = getArguments().getString( ARG_PARAM2 );
		}
	}

	@Override
	protected void initToolBar() {

	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_article_recommend;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM( this, mModel );
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		mSrlContainer = ( SmartRefreshLayout ) findViewById( R.id.srl_container );
		mRecyclerView = ( RecyclerView ) findViewById( R.id.fragment_article_recommend_recycler_view );
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

	@Override
	public void loadData() {
		MyRequestMap map = new MyRequestMap();
		map.put( "channelId", mChannelId );
		mPresenter.requestArticleRecommend( map );
	}

	private void loadNewData() {

	}

	private void initRecyclerView() {
		mAdapter = new SectionedRecyclerViewAdapter( null);
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
	public void setArticleRecommend( List< ArticleRecommendEntity > articleRecommendEntityList ) {

	}
}
