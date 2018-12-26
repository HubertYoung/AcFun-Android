package com.hubertyoung.component.acfunvideo.index.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.billy.cc.core.component.CC;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.base.BaseListFragment;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.entity.Regions;
import com.hubertyoung.common.entity.RegionsEntity;
import com.hubertyoung.common.utils.Utils;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.index.section.ArticlesNewSection;
import com.hubertyoung.component.acfunvideo.index.section.ArticlesRankRecommendSection;
import com.hubertyoung.component.acfunvideo.index.section.ArticlesRecommendSection;
import com.hubertyoung.component.acfunvideo.index.section.NewRecommendBangumisSection;
import com.hubertyoung.component.acfunvideo.index.section.NewRecommendBannersSection;
import com.hubertyoung.component.acfunvideo.index.section.NewRecommendCarouselsSection;
import com.hubertyoung.component.acfunvideo.index.section.NewRecommendVideosRankSection;
import com.hubertyoung.component.acfunvideo.index.section.NewRecommendVideosSection;
import com.hubertyoung.component.acfunvideo.index.vm.NewRecommendViewModel;

import java.util.HashMap;
import java.util.List;

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
public class NewRecommendFragment extends BaseListFragment< NewRecommendViewModel > {
	private static final String ARG_PARAM1 = "id";
	private static final String ARG_PARAM2 = "param2";

	private String channelId = "";
	private String mParam2;
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
	protected String getStateEventTag() {
		return channelId;
	}

	@Override
	protected void initToolBar() {

	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		super.initView( savedInstanceState );
		setTitleLayoutVisible( false );
	}

	@Override
	public void loadData() {
		mViewModel.requestRecommend( channelId );
	}

	@Override
	public void loadNewData() {
		mViewModel.requestNewRecommend( channelId, getAdapter().getPageBean().getLoadPage() + "" );
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
				getAdapter().notifyDataSetChanged();
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

	public void showLoading( String title ) {
		super.showLoading( title );
	}

	@Override
	protected RecyclerView.LayoutManager createLayoutManager() {
		GridLayoutManager layoutManager = new GridLayoutManager( activity, 6 );
		layoutManager.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize( int position ) {
				switch ( getAdapter().getSectionItemViewType( position ) ) {
					case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
						return 6;
					case SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER:
						return 6;
					case SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED: {
						int spanSizeLookup = getAdapter().getSectionForPosition( position ).spanSizeLookup;
						return spanSizeLookup;
					}
					default:
						return 6;
				}
			}
		} );
		return layoutManager;
	}

	public void showErrorTip( String msg ) {
		ToastUtil.showError( msg );
	}

	public void addNewRecommendInfo( List< RegionBodyContent > regionsList ) {
		if ( TextUtils.equals( channelId, articlesChannelId ) ) {
			Section section = getAdapter().getSection( Utils.articles_new );
			if ( section != null && section instanceof ArticlesNewSection ) {
				ArticlesNewSection articlesNewSection = ( ArticlesNewSection ) section;
				articlesNewSection.addRegions( regionsList );
				getAdapter().notifyDataSetChanged();
			}
		} else {
			Section section = getAdapter().getSection( Utils.videos_new );
			if ( section != null && section instanceof NewRecommendVideosSection ) {
				NewRecommendVideosSection videosSection = ( NewRecommendVideosSection ) section;
				videosSection.addRegions( regionsList );
				getAdapter().notifyDataSetChanged();
			}
		}
	}

	public void showNewRecommendCarouselsSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			mCarouselsSection = new NewRecommendCarouselsSection( ( BaseActivity ) activity );
			getAdapter().addSection( mCarouselsSection );
			mCarouselsSection.setOnItemClickListener( new NewRecommendCarouselsSection.OnBannerItemClickListener() {
				@Override
				public void onBannerItemClick( View v, RegionBodyContent bodyContent ) {
					HashMap< String, Object > map = new HashMap<>();
					map.put( "actionId", bodyContent.actionId );
					map.put( "contentId", bodyContent.contentId );
					CC.obtainBuilder( "ComponentAcFunIndex" )//
							.setContext( activity ).setParams( map )//
							.setActionName( "Activity" )//
							.build()//
							.call();
				}
			} );
		}
		mCarouselsSection.setRegions( regions );
	}

	public void showNewRecommendBannersSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			mBannersSection = new NewRecommendBannersSection( ( BaseActivity ) activity );
			getAdapter().addSection( mBannersSection );
			mBannersSection.setRegions( regions );
			mBannersSection.setOnItemClickListener( new NewRecommendBannersSection.OnItemClickListener() {
				@Override
				public void onSingleBannerClick( View v, RegionBodyContent regionBodyContent ) {


				}
			} );
		}
	}

	public void showNewRecommendVideosSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			boolean isVideoNew = TextUtils.equals( Utils.videos_new, regions.schema );
			mNewBangumiSection = new NewRecommendVideosSection( ( BaseActivity ) activity, isVideoNew );
			if ( isVideoNew ) {
				getAdapter().addSection( Utils.videos_new, mNewBangumiSection );
			} else {
				getAdapter().addSection( mNewBangumiSection );
			}
			mNewBangumiSection.setRegions( regions );
			mNewBangumiSection.setOnItemClickListener( new NewRecommendVideosSection.OnItemClickListener() {
				@Override
				public void onClickRecommendBangumiItem( View v, RegionBodyContent bodyContentsBean ) {
//					if (bodyContentsBean.actionId == 11) {
//						bodyContentsBean.contentId = "-1";
//					}
//					if (regionBodyContent.actionId == 1) {
//						IntentHelper.a(this.al, Integer.valueOf(regionBodyContent.contentId).intValue(), "recommend", regionBodyContent.reqId, regionBodyContent.groupId);
//					} else if (regionBodyContent.actionId == 10) {
//						IntentHelper.b(this.al, Integer.valueOf(regionBodyContent.contentId).intValue(), "recommend", regionBodyContent.reqId, regionBodyContent.groupId);
//					} else {
//						tv.acfun.core.utils.Utils.a(this.al, regionBodyContent.actionId, regionBodyContent.contentId, null);
//					}
					HashMap< String, Object > map = new HashMap<>();
					map.put( "actionId", bodyContentsBean.actionId );
					map.put( "contentId", bodyContentsBean.contentId );
					map.put( "reqId", bodyContentsBean.reqId );
					map.put( "groupId", bodyContentsBean.groupId );
					CC.obtainBuilder( "ComponentAcFunIndex" )//
							.setContext( activity ).setParams( map )//
							.setActionName( "Activity" )//
							.build()//
							.call();
				}
			} );
		}
	}

	public void showNewRecommendVideosRankSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			mVideosRankSection = new NewRecommendVideosRankSection( ( BaseActivity ) activity );
			getAdapter().addSection( mVideosRankSection );
			mVideosRankSection.setRegions( regions );
		}
	}

	public void showNewRecommendBangumisSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			mBangumisSection = new NewRecommendBangumisSection( ( BaseActivity ) activity );
			getAdapter().addSection( mBangumisSection );
			mBangumisSection.setRegions( regions );
		}
	}

	public void refreshViewInfo( int i ) {
		if ( i == 0 ) {
			getAdapter().removeAllSections();
		} else {
			getAdapter().notifyDataSetChanged();
		}
	}

	public void showArticlesRecommendSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			articlesChannelId = channelId;
			ArticlesRecommendSection mArticlesRecommendSection = new ArticlesRecommendSection( ( BaseActivity ) activity );
			getAdapter().addSection( mArticlesRecommendSection );
			mArticlesRecommendSection.setRegions( regions );
		}
	}

	public void showArticlesRankRecommendSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			mArticlesRankRecommendSection = new ArticlesRankRecommendSection( ( BaseActivity ) activity );
			getAdapter().addSection( mArticlesRankRecommendSection );
			mArticlesRankRecommendSection.setRegions( regions );
		}
	}

	public void showArticlesNewRecommendSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			boolean isArticlesNew = TextUtils.equals( Utils.articles_new, regions.schema );
			mArticlesNewSection = new ArticlesNewSection( ( BaseActivity ) activity );
			if ( isArticlesNew ) {
				getAdapter().addSection( Utils.articles_new, mArticlesNewSection );
			} else {
				getAdapter().addSection( mArticlesNewSection );
			}
			mArticlesNewSection.setRegions( regions );
		}
	}
}
