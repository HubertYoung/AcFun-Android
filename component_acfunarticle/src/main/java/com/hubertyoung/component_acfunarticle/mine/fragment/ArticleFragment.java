package com.hubertyoung.component_acfunarticle.mine.fragment;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.utils.BarUtils;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.component_acfunarticle.R;
import com.hubertyoung.component_acfunarticle.mine.control.ArticleControl;
import com.hubertyoung.component_acfunarticle.mine.model.ArticleModelImp;
import com.hubertyoung.component_acfunarticle.mine.presenter.ArticlePresenterImp;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

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
	}

	@Override
	public void showLoading( String title, int type ) {

	}

	@Override
	public void stopLoading() {

	}

	@Override
	public void showErrorTip( String msg ) {
		ToastUtil.showSuccess( msg );
	}

}
