package com.hubertyoung.component_acfundynamic.dynamic.fragment;


import android.os.Bundle;

import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.component_acfundynamic.R;
import com.hubertyoung.component_acfundynamic.dynamic.control.DynamicControl;
import com.hubertyoung.component_acfundynamic.dynamic.model.DynamicModelImp;
import com.hubertyoung.component_acfundynamic.dynamic.presenter.DynamicPresenterImp;


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
public class DynamicFragment extends BaseFragment< DynamicPresenterImp, DynamicModelImp > implements DynamicControl.View {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;


	public DynamicFragment() {
		// Required empty public constructor
	}

	public static DynamicFragment newInstance( String param1, String param2 ) {
		DynamicFragment fragment = new DynamicFragment();
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
//		BarUtils.setPaddingSmart( mTlHead );
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_dynamic_layout;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM( this, mModel );
	}

	private void initAction() {
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		initAction();
		loadData();
	}

	@Override
	public void loadData() {
//		MyRequestMap map = new MyRequestMap();
//		mPresenter.requestAllChannel( map );
	}

	@Override
	public void showLoading( String title, int type ) {
//		mViewSkeletonScreen = Skeleton.bind( mArticleViewPager )//
////				.shimmer( true )//
////				.duration( 1200 )//
////				.angle( 20 )//
//				.load( R.layout.view_loading_button )//
//				.show();
	}

	@Override
	public void stopLoading() {
//		if ( mViewSkeletonScreen != null && mViewSkeletonScreen.isShowing() ) {
//			mViewSkeletonScreen.hide();
//		}
	}

	@Override
	public void showErrorTip( String msg ) {
		ToastUtil.showError( msg );
	}

}
