package com.hubertyoung.component_acfunarticle.article.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.utils.BarUtils;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.component_acfunarticle.R;
import com.hubertyoung.component_acfunarticle.entity.Channel;
import com.hubertyoung.component_acfunarticle.entity.ServerChannel;
import com.hubertyoung.component_acfunarticle.article.adapter.ArticlePagerAdapter;
import com.hubertyoung.component_acfunarticle.article.control.ArticleControl;
import com.hubertyoung.component_acfunarticle.article.model.ArticleModelImp;
import com.hubertyoung.component_acfunarticle.article.presenter.ArticlePresenterImp;
import com.hubertyoung.component_skeleton.skeleton.Skeleton;
import com.hubertyoung.component_skeleton.skeleton.ViewSkeletonScreen;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/16 17:17
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunarticle.mine.fragment
 */
public class ArticleFragment extends BaseFragment< ArticlePresenterImp, ArticleModelImp > implements ArticleControl.View {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;

	private LinearLayout mContent;
	private Toolbar mTlHead;
	private SmartTabLayout mArticleViewTab;
	private ImageView mIvSearch;
	private ViewPager mArticleViewPager;
	private ViewSkeletonScreen mViewSkeletonScreen;
	private ArticlePagerAdapter mArticlePagerAdapter;

	public ArticleFragment() {
		// Required empty public constructor
	}

	public static ArticleFragment newInstance( String param1, String param2 ) {
		ArticleFragment fragment = new ArticleFragment();
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
		BarUtils.setPaddingSmart( mTlHead );
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_article_layout;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM( this, mModel );
	}

	private void initAction() {
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		mContent = findViewById( R.id.content );
		mTlHead = findViewById( R.id.tl_head );
		mArticleViewTab = findViewById( R.id.article_view_tab );
		mIvSearch = findViewById( R.id.iv_search );
		mArticleViewPager = findViewById( R.id.article_view_pager );
		initAction();
		loadData();
	}

	@Override
	public void loadData() {
		MyRequestMap map = new MyRequestMap();
		mPresenter.requestAllChannel( map );
	}

	@Override
	public void showLoading( String title, int type ) {
		mViewSkeletonScreen = Skeleton.bind( mArticleViewPager )//
//				.shimmer( true )//
//				.duration( 1200 )//
//				.angle( 20 )//
				.load( R.layout.view_loading_button )//
				.show();
	}

	@Override
	public void stopLoading() {
		if ( mViewSkeletonScreen != null && mViewSkeletonScreen.isShowing() ) {
			mViewSkeletonScreen.hide();
		}
	}

	@Override
	public void showErrorTip( String msg ) {
		ToastUtil.showError( msg );
	}

	@Override
	public void setAllChannelInfo( Channel channel ) {
		initViewPager( channel.article );
	}

	private void initViewPager( List< ServerChannel > article ) {
		this.mArticleViewTab.setCustomTabView( R.layout.widget_secondary_tab_view, R.id.secondary_tab_text );
		if ( mArticlePagerAdapter == null ) {
			mArticlePagerAdapter = new ArticlePagerAdapter( activity, getSupportFragmentManager( activity ) );
		}
		mArticlePagerAdapter.setInfo( article );
		this.mArticleViewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
			public void onPageScrollStateChanged( int i ) {
			}

			public void onPageScrolled( int i, float f, int i2 ) {
			}

			public void onPageSelected( int i ) {
				selectorTextView( i );
			}
		} );


		this.mArticleViewPager.setAdapter( mArticlePagerAdapter );
		this.mArticleViewPager.setCurrentItem( 0 );
		this.mArticleViewTab.setViewPager( mArticleViewPager );
		selectorTextView( 0 );
	}

	private void selectorTextView( int i ) {
		for (int j = 0; j < mArticlePagerAdapter.getCount(); j++) {
			View tab = mArticleViewTab.getTabAt( j );
			if ( tab instanceof TextView ) {
				TextView textView = ( TextView ) tab;
				if ( i == j ) {
					textView.setTypeface( Typeface.DEFAULT_BOLD );
					textView.setTextSize( 17.0f );
					textView.setAlpha( 1.0f );
				} else {
					textView.setTypeface( Typeface.DEFAULT );
					textView.setTextSize( 15.0f );
					textView.setAlpha( 0.8f );
				}
			}
		}
	}
}
