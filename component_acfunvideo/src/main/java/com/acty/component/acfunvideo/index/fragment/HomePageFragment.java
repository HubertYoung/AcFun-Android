package com.acty.component.acfunvideo.index.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.acty.component_acfunvideo.R;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.utils.BarUtils;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * <br>
 * function:首页
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 10:18 AM
 * @since:V1.0
 * @desc:com.hubertyoung.component.home.index.fragment
 */
public class HomePageFragment extends BaseFragment {

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private String mParam1;
	private String mParam2;
	private Toolbar mToolbar;
	private SmartTabLayout mHomeViewTab;
	private ImageView mIvSearch;
	private ImageView mIvGame;
	private ViewPager mHomeViewPager;

	public HomePageFragment() {
	}

	public static HomePageFragment newInstance( String param1, String param2 ) {
		HomePageFragment fragment = new HomePageFragment();
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
		BarUtils.setPaddingSmart(mToolbar);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_home_page_view;
	}

	@Override
	public void onViewCreated( View view, @Nullable Bundle savedInstanceState ) {
		mToolbar = ( Toolbar ) findViewById( R.id.toolbar );
		mHomeViewTab = ( SmartTabLayout ) findViewById( R.id.home_view_tab );
		mIvSearch = ( ImageView ) findViewById( R.id.iv_search );
		mIvGame = ( ImageView ) findViewById( R.id.iv_game );
		mHomeViewPager = ( ViewPager ) findViewById( R.id.home_view_pager );
		super.onViewCreated( view, savedInstanceState );
	}

	@Override
	public void initPresenter() {
//		mPresenter.setVM( this, mModel );
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
//		initRecyclerView();
//		initAction();
		loadData();
	}

	@Override
	public void loadData() {
		MyRequestMap map = new MyRequestMap();
//		mPresenter.requestHomeIndex( map );
	}
}
