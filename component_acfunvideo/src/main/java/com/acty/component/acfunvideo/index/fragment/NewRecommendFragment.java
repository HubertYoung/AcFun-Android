package com.acty.component.acfunvideo.index.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.acty.component.acfunvideo.entity.RegionBodyContent;
import com.acty.component.acfunvideo.entity.Regions;
import com.acty.component.acfunvideo.index.control.NewRecommendControl;
import com.acty.component.acfunvideo.index.model.NewRecommendModelImp;
import com.acty.component.acfunvideo.index.presenter.NewRecommendPresenterImp;
import com.acty.component.acfunvideo.index.section.NewRecommendBangumisSection;
import com.acty.component.acfunvideo.index.section.NewRecommendBannersSection;
import com.acty.component.acfunvideo.index.section.NewRecommendCarouselsSection;
import com.acty.component.acfunvideo.index.section.NewRecommendVideosRankSection;
import com.acty.component.acfunvideo.index.section.NewRecommendVideosSection;
import com.acty.component_acfunvideo.R;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.common.utils.Utils;
import com.hubertyoung.common.widget.sectioned.Section;
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
	private NewRecommendVideosSection mNewBangumiSection;
	private NewRecommendVideosRankSection mVideosRankSection;
	private NewRecommendCarouselsSection mCarouselsSection;
	private NewRecommendBannersSection mBannersSection;
	private NewRecommendBangumisSection mBangumisSection;
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
		mPresenter.requestRecommend( map );
	}

	private void loadNewData() {
		MyRequestMap map = new MyRequestMap();
		map.put( "pageNo", mAdapter.getPageBean().getLoadPage() + "" );
		mPresenter.requestNewRecommend( map );
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
		mAdapter = new SectionedRecyclerViewAdapter();
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
		mHomeRecommendLis.setHasFixedSize( true );
		mHomeRecommendLis.setLayoutManager( layoutManager );
		mViewSkeletonScreen = Skeleton.bind( mHomeRecommendLis )//
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
	public void addNewRecommendInfo( List< RegionBodyContent > regionsList ) {
		Section section = mAdapter.getSection( Utils.videos_new );
		if ( section != null && section instanceof NewRecommendVideosSection ) {
			NewRecommendVideosSection videosSection = ( NewRecommendVideosSection ) section;
			videosSection.addRegions( regionsList );
			mAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void setRecommendInfo( List< Regions > regionsList ) {
//		mNewBangumiSection.setData(newRecommendEntityList);
		mAdapter.removeAllSections();
		for (Regions regions : regionsList) {
			switch ( regions.schema ) {
				case Utils.carousels:
					if ( mAdapter.getPageBean().refresh ) {
						mCarouselsSection = new NewRecommendCarouselsSection( ( BaseActivity ) activity );
						mAdapter.addSection( mCarouselsSection );
					}
					mCarouselsSection.setRegions( regions );
					break;
				case Utils.banners:
					if ( mAdapter.getPageBean().refresh ) {
						mBannersSection = new NewRecommendBannersSection( ( BaseActivity ) activity );
						mAdapter.addSection( mBannersSection );
						mBannersSection.setRegions( regions );
					}
					break;

				case Utils.videos:
				case Utils.videos_new:
					if ( mAdapter.getPageBean().refresh ) {
						boolean isVideoNew = TextUtils.equals( Utils.videos_new, regions.schema );
						mNewBangumiSection = new NewRecommendVideosSection( ( BaseActivity ) activity, isVideoNew );
						if ( isVideoNew ) {
							mAdapter.addSection( Utils.videos_new, mNewBangumiSection );
						} else {
							mAdapter.addSection( mNewBangumiSection );
						}
						mNewBangumiSection.setRegions( regions );
					}
					break;
				case Utils.videos_rank:
					if ( mAdapter.getPageBean().refresh ) {
						mVideosRankSection = new NewRecommendVideosRankSection( ( BaseActivity ) activity );
						mAdapter.addSection( mVideosRankSection );
						mVideosRankSection.setRegions( regions );
					}
					break;
				case Utils.bangumis:
					if ( mAdapter.getPageBean().refresh ) {
						mBangumisSection = new NewRecommendBangumisSection( ( BaseActivity ) activity );
						mAdapter.addSection( mBangumisSection );
						mBangumisSection.setRegions( regions );
					}
					break;
			}
		}
		mAdapter.notifyDataSetChanged();
	}
}
