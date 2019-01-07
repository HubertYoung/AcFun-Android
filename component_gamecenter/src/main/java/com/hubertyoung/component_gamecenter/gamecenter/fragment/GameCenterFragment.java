package com.hubertyoung.component_gamecenter.gamecenter.fragment;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.base.BaseListFragment;
import com.hubertyoung.common.entity.Regions;
import com.hubertyoung.common.entity.RegionsEntity;
import com.hubertyoung.common.utils.Utils;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component_gamecenter.config.GameCenterConstants;
import com.hubertyoung.component_gamecenter.gamecenter.section.GameCenterCarouselsSection;
import com.hubertyoung.component_gamecenter.gamecenter.section.GameCenterGameBannersSection;
import com.hubertyoung.component_gamecenter.gamecenter.section.GameCenterGameListVHorizSection;
import com.hubertyoung.component_gamecenter.gamecenter.section.GameCenterGameListVerticSection;
import com.hubertyoung.component_gamecenter.gamecenter.section.GameCenterNewsDownUpSection;
import com.hubertyoung.component_gamecenter.gamecenter.section.GameCenterSSRSection;
import com.hubertyoung.component_gamecenter.gamecenter.vm.GameCenterViewModel;

import java.util.List;

/**
 * <br>
 * function:游戏中心
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/21 15:54
 * @since:V5.7.0
 * @desc:com.hubertyoung.component_gamecenter.fragment
 */
public class GameCenterFragment extends BaseListFragment< GameCenterViewModel > {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private String mParam1;
	private String mParam2;
	private GameCenterCarouselsSection mCarouselsSection;
	private GameCenterSSRSection mGameCenterSSRSection;
	private GameCenterNewsDownUpSection mNewsDownUpSection;
	private GameCenterGameListVerticSection mGameListVerticSection;
	private GameCenterGameListVHorizSection mVHorizSection;
	private GameCenterGameBannersSection mGameBannersSection;

	public static GameCenterFragment newInstance( String param1, String param2 ) {
		GameCenterFragment fragment = new GameCenterFragment();
		Bundle args = new Bundle();
		args.putString( ARG_PARAM1, param1 );
		args.putString( ARG_PARAM2, param2 );
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		super.initView( savedInstanceState );
		setTitleLayoutVisible( false );
	}

	@Override
	protected void loadNewData() {

	}

	@Override
	protected void loadData() {
		mViewModel.requestGameCenter();
	}

	@Override
	protected void dataObserver() {
		registerObserver( GameCenterConstants.EVENT_KEY_GAMECENTER, RegionsEntity.class ).observe( this, new Observer< RegionsEntity >() {
			@Override
			public void onChanged( RegionsEntity regionsEntity ) {
				List< Regions > regionsList = regionsEntity.regionsList;
				for (Regions regions : regionsList) {
					switch ( regions.type.value ) {
						case Utils.carousels:
							showGameCenterCarouselsSection( regions );
							break;
						case Utils.shiouqi:
							showGameCenterShiouqiSection( regions );
							break;
						case Utils.news_down_up:
							showGameCenterNewsDownUpSection( regions );
						case Utils.game_list_vertic:
							showGameCenterGameListVerticSection( regions );
							break;
						case Utils.game_list_horiz:
							showGameCenterGameListHorizSection( regions );
							break;
						case Utils.game_banners:
							showGameCenterGameBannersSection( regions );
							break;
					}
				}
				getAdapter().notifyDataSetChanged();
			}
		} );
	}

	private void showGameCenterGameBannersSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			mGameBannersSection = new GameCenterGameBannersSection( ( BaseActivity ) mActivity );
			getAdapter().addSection( mGameBannersSection );
		}
		mGameBannersSection.setRegions( regions );
	}

	private void showGameCenterGameListHorizSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			mVHorizSection = new GameCenterGameListVHorizSection( ( BaseActivity ) mActivity );
			getAdapter().addSection( mVHorizSection );
		}
		mVHorizSection.setRegions( regions );
	}

	private void showGameCenterGameListVerticSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			mGameListVerticSection = new GameCenterGameListVerticSection( ( BaseActivity ) mActivity );
			getAdapter().addSection( mGameListVerticSection );
		}
		mGameListVerticSection.setRegions( regions );
	}

	private void showGameCenterNewsDownUpSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			mNewsDownUpSection = new GameCenterNewsDownUpSection( ( BaseActivity ) mActivity );
			getAdapter().addSection( mNewsDownUpSection );
		}
		mNewsDownUpSection.setRegions( regions );
	}

	private void showGameCenterShiouqiSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			mGameCenterSSRSection = new GameCenterSSRSection( ( BaseActivity ) mActivity );
			getAdapter().addSection( mGameCenterSSRSection );
		}
		mGameCenterSSRSection.setRegions( regions );
	}

	private void showGameCenterCarouselsSection( Regions regions ) {
		if ( getAdapter().getPageBean().refresh ) {
			mCarouselsSection = new GameCenterCarouselsSection( ( BaseActivity ) mActivity );
			getAdapter().addSection( mCarouselsSection );
		}
		mCarouselsSection.setRegions( regions );
	}

	@Override
	protected RecyclerView.LayoutManager createLayoutManager() {
		GridLayoutManager layoutManager = new GridLayoutManager( mActivity, 6 );
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
}
