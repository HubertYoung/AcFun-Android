package com.hubertyoung.component.acfunvideo.index.fragment;

import android.os.Bundle;
import android.text.TextUtils;

import com.hubertyoung.common.base.AbsLifecycleFragment;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.utils.Utils;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.entity.Regions;
import com.hubertyoung.component.acfunvideo.entity.RegionsEntity;
import com.hubertyoung.component.acfunvideo.index.section.ArticlesNewSection;
import com.hubertyoung.component.acfunvideo.index.section.ArticlesRankRecommendSection;
import com.hubertyoung.component.acfunvideo.index.section.ArticlesRecommendSection;
import com.hubertyoung.component.acfunvideo.index.section.NewRecommendBangumisSection;
import com.hubertyoung.component.acfunvideo.index.section.NewRecommendBannersSection;
import com.hubertyoung.component.acfunvideo.index.section.NewRecommendCarouselsSection;
import com.hubertyoung.component.acfunvideo.index.section.NewRecommendVideosRankSection;
import com.hubertyoung.component.acfunvideo.index.section.NewRecommendVideosSection;
import com.hubertyoung.component.acfunvideo.index.vm.NewRecommendViewModel;
import com.hubertyoung.component_acfunvideo.R;
import com.hubertyoung.component_skeleton.skeleton.RecyclerViewSkeletonScreen;
import com.hubertyoung.component_skeleton.skeleton.Skeleton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <br>
 * function:推荐
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/3 18:39
 * @since:V1.0.0
 * @desc:com.hubertyoung.component.acfunvideo.index.fragment
 */
public class NewRecommendFragment extends AbsLifecycleFragment< NewRecommendViewModel > {
	private static final String ARG_PARAM1 = "id";
	private static final String ARG_PARAM2 = "param2";

	private String channelId = "";
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
	/**
	 * 推荐选择的id
	 */
	private String articlesChannelId = "";
	private ArticlesRankRecommendSection mArticlesRankRecommendSection;
	private ArticlesNewSection mArticlesNewSection;
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
			channelId = getArguments().getString( ARG_PARAM1 );
			mParam2 = getArguments().getString( ARG_PARAM2 );
		}
	}

	@Override
	protected void initToolBar() {

	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_home_recommend;
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		super.initView( savedInstanceState );
		mSrlContainer = ( SmartRefreshLayout ) findViewById( R.id.srl_container );
		mHomeRecommendLis = ( RecyclerView ) findViewById( R.id.home_recommend_lis );
		initRecyclerView();
		initAction();
		loadData();
	}

	public void loadData() {
		mViewModel.requestRecommend( channelId );
	}

	private void loadNewData() {
		mViewModel.requestNewRecommend( channelId, mAdapter.getPageBean().getLoadPage() + "" );
	}

	@Override
	protected void dataObserver() {
		registerObserver( VideoConstants.EVENT_KEY_NEW_RECOMMEND_INFO, RegionsEntity.class ).observe( this, new Observer< RegionsEntity >() {
			@Override
			public void onChanged( RegionsEntity regionsEntity ) {
				List< Regions > regionsList = regionsEntity.regionsList;
				for (Regions regions : regionsList) {
					switch ( regions.schema ) {
						case Utils.carousels:
							showNewRecommendCarouselsSection( regions );
							break;
						case Utils.banners:
							showNewRecommendBannersSection( regions );
							break;
						case Utils.videos:
						case Utils.videos_new:
							showNewRecommendVideosSection( regions );
							break;
						case Utils.videos_rank:
							showNewRecommendVideosRankSection( regions );
							break;
						case Utils.bangumis:
							showNewRecommendBangumisSection( regions );
							break;
						case Utils.articles:
							showArticlesRecommendSection( regions );
							break;
						case Utils.articles_rank:
							showArticlesRankRecommendSection( regions );
							break;
						case Utils.articles_new:
							showArticlesNewRecommendSection( regions );
							break;
					}
				}
			}
		} );
		registerObserver( VideoConstants.EVENT_KEY_NEW_RECOMMEND_ADD_INFO, Regions.class ).observe( this, new Observer< Regions >() {
			@Override
			public void onChanged( Regions regions ) {
				addNewRecommendInfo( regions.changeContents );
			}
		} );
		registerObserver( VideoConstants.EVENT_KEY_NEW_RECOMMEND_STATUS, Integer.class ).observe( this, new Observer< Integer >() {
			@Override
			public void onChanged( Integer integer ) {
				refreshViewInfo( integer );
			}
		} );
	}

	private void initAction() {
//		mSrlContainer.setEnableLoadMore( false );
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
		mAdapter = new SectionedRecyclerViewAdapter( new DiffUtil.ItemCallback< RegionBodyContent >() {
			@Override
			public boolean areItemsTheSame( @NonNull RegionBodyContent oldItem, @NonNull RegionBodyContent newItem ) {
				return oldItem.reqId == newItem.reqId;
			}

			@Override
			public boolean areContentsTheSame( @NonNull RegionBodyContent oldItem, @NonNull RegionBodyContent newItem ) {
				return oldItem == newItem;
			}
		} );
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

	public void showLoading( String title ) {

	}

	@Override
	public void stopLoading() {
		mSrlContainer.finishRefresh();
		mSrlContainer.finishLoadMore();
		if ( mViewSkeletonScreen != null && mViewSkeletonScreen.isShowing() ) {
			mViewSkeletonScreen.hide();
		}
	}

	public void showErrorTip( String msg ) {
		ToastUtil.showError( msg );
	}

	public void addNewRecommendInfo( List< RegionBodyContent > regionsList ) {
		if ( TextUtils.equals( channelId, articlesChannelId ) ) {
			Section section = mAdapter.getSection( Utils.articles_new );
			if ( section != null && section instanceof ArticlesNewSection ) {
				ArticlesNewSection articlesNewSection = ( ArticlesNewSection ) section;
				articlesNewSection.addRegions( regionsList );
				mAdapter.notifyDataSetChanged();
			}
		} else {
			Section section = mAdapter.getSection( Utils.videos_new );
			if ( section != null && section instanceof NewRecommendVideosSection ) {
				NewRecommendVideosSection videosSection = ( NewRecommendVideosSection ) section;
				videosSection.addRegions( regionsList );
				mAdapter.notifyDataSetChanged();
			}
		}
	}

	public void showNewRecommendCarouselsSection( Regions regions ) {
		if ( mAdapter.getPageBean().refresh ) {
			mCarouselsSection = new NewRecommendCarouselsSection( ( BaseActivity ) activity );
			mAdapter.addSection( mCarouselsSection );
		}
		mCarouselsSection.setRegions( regions );
	}

	public void showNewRecommendBannersSection( Regions regions ) {
		if ( mAdapter.getPageBean().refresh ) {
			mBannersSection = new NewRecommendBannersSection( ( BaseActivity ) activity );
			mAdapter.addSection( mBannersSection );
			mBannersSection.setRegions( regions );
		}
	}

	public void showNewRecommendVideosSection( Regions regions ) {
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
	}

	public void showNewRecommendVideosRankSection( Regions regions ) {
		if ( mAdapter.getPageBean().refresh ) {
			mVideosRankSection = new NewRecommendVideosRankSection( ( BaseActivity ) activity );
			mAdapter.addSection( mVideosRankSection );
			mVideosRankSection.setRegions( regions );
		}
	}

	public void showNewRecommendBangumisSection( Regions regions ) {
		if ( mAdapter.getPageBean().refresh ) {
			mBangumisSection = new NewRecommendBangumisSection( ( BaseActivity ) activity );
			mAdapter.addSection( mBangumisSection );
			mBangumisSection.setRegions( regions );
		}
	}

	public void refreshViewInfo( int i ) {
		if ( i == 0 ) {
			mAdapter.removeAllSections();
		} else {
			mAdapter.notifyDataSetChanged();
		}
	}

	public void showArticlesRecommendSection( Regions regions ) {
		if ( mAdapter.getPageBean().refresh ) {
			articlesChannelId = channelId;
			ArticlesRecommendSection mArticlesRecommendSection = new ArticlesRecommendSection( ( BaseActivity ) activity );
			mAdapter.addSection( mArticlesRecommendSection );
			mArticlesRecommendSection.setRegions( regions );
		}
	}

	public void showArticlesRankRecommendSection( Regions regions ) {
		if ( mAdapter.getPageBean().refresh ) {
			mArticlesRankRecommendSection = new ArticlesRankRecommendSection( ( BaseActivity ) activity );
			mAdapter.addSection( mArticlesRankRecommendSection );
			mArticlesRankRecommendSection.setRegions( regions );
		}
	}

	public void showArticlesNewRecommendSection( Regions regions ) {
		if ( mAdapter.getPageBean().refresh ) {
			boolean isArticlesNew = TextUtils.equals( Utils.articles_new, regions.schema );
			mArticlesNewSection = new ArticlesNewSection( ( BaseActivity ) activity );
			if ( isArticlesNew ) {
				mAdapter.addSection( Utils.articles_new, mArticlesNewSection );
			} else {
				mAdapter.addSection( mArticlesNewSection );
			}
			mArticlesNewSection.setRegions( regions );
		}
	}
}
