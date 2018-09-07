package com.acty.component.acfunvideo.index.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.acty.component.acfunvideo.index.adapter.HomePagerAdapter;
import com.acty.component_acfunvideo.R;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.utils.BarUtils;
import com.hubertyoung.common.utils.ToastUtil;
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
	private HomePagerAdapter mHomePagerAdapter;
	private NewRecommendFragment mNewRecommendFragment;
	private ChannelFragment mChannelFragment;

	private boolean isShowGameIcon = true;
	private boolean isGameNew = false;
	private int mCurrentPostion = 0;

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
		BarUtils.setPaddingSmart( mToolbar );
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
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		initViewPager();
		initAction();
		initData();
		loadData();
	}

	private void initData() {
		if ( isShowGameIcon ) {
			mIvGame.setVisibility( View.VISIBLE );
			mIvGame.setBackgroundResource( isGameNew ? R.mipmap.ic_game_center_new : R.mipmap.ic_game_center );
		} else {
			mIvGame.setVisibility( View.GONE );
		}
	}

	private void initAction() {
		mHomeViewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled( int position, float positionOffset, int positionOffsetPixels ) {

			}

			@Override
			public void onPageSelected( int position ) {
				mCurrentPostion = position;
			}

			@Override
			public void onPageScrollStateChanged( int state ) {

			}
		} );
		mIvSearch.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				ToastUtil.showSuccess( "mIvSearch" );
			}
		} );
		mIvGame.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				ToastUtil.showSuccess( "mIvGame" );
			}
		} );
	}

	private void initViewPager() {
		mHomeViewTab.setCustomTabView( R.layout.widget_home_page_tab_view, R.id.tab_text );
		mHomePagerAdapter = new HomePagerAdapter( getChildFragmentManager() );
		mNewRecommendFragment = NewRecommendFragment.newInstance("","");
		mChannelFragment = ChannelFragment.newInstance( "","" );

		mHomePagerAdapter.add( mNewRecommendFragment, activity.getString( R.string.recommend_text ) );
		mHomePagerAdapter.add( mChannelFragment, activity.getString( R.string.common_channel ) );
		mHomeViewPager.setAdapter( mHomePagerAdapter );
		mHomeViewPager.setOffscreenPageLimit( 1 );
		mHomeViewPager.setCurrentItem( 0 );
		mHomeViewTab.setViewPager( mHomeViewPager );
	}
}
