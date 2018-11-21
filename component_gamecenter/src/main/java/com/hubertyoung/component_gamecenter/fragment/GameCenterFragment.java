package com.hubertyoung.component_gamecenter.fragment;


import android.os.Bundle;

import com.hubertyoung.common.base.BaseListFragment;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class GameCenterFragment extends BaseListFragment {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private String mParam1;
	private String mParam2;

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
}
