package com.kento.component.home.index.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kento.common.base.BaseFragment;
import com.kento.common.basebean.MyRequestMap;
import com.kento.common.utils.BarUtils;
import com.kento.common.utils.ToastUtil;
import com.kento.component.home.entity.HomeIndexEntity;
import com.kento.component.home.index.control.IndexRootControl;
import com.kento.component.home.index.model.IndexRootModelImp;
import com.kento.component.home.index.presenter.IndexRootPresenterImp;
import com.kento.component_banner.banner.BannerEntity;
import com.kento.component_banner.banner.BannerView;
import com.kento.component_home.R;
import com.kento.component_home.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <br>
 * function:首页
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 10:18 AM
 * @since:V1.0
 * @desc:com.kento.component.home.index.fragment
 */
public class IndexRootFragment extends BaseFragment<IndexRootPresenterImp, IndexRootModelImp> implements IndexRootControl.View {

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	@Nullable
	@BindView( R2.id.bv_home_index )
	public BannerView mBvHomeIndex;
	private String mParam1;
	private String mParam2;

	public IndexRootFragment() {
	}

	public static IndexRootFragment newInstance( String param1, String param2 ) {
		IndexRootFragment fragment = new IndexRootFragment();
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

	}

	@Override
	protected int getLayoutResource() {
		return R.layout.home_fragment_index_root;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM( this,mModel );
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		BarUtils.setPaddingSmart( mBvHomeIndex );
		MyRequestMap map = new MyRequestMap();
		mPresenter.requestHomeIndex( map );

	}

	@Override
	public void showLoading( String title, int type ) {

	}

	@Override
	public void stopLoading() {

	}

	@Override
	public void showErrorTip( String msg ) {
		ToastUtil.showError( msg );
	}

	@Override
	public void setHomeIndexInfo( HomeIndexEntity homeIndexEntity ) {

		List< HomeIndexEntity.BannerBean > bannerBeanList = homeIndexEntity.banner;
		ArrayList< BannerEntity > list = new ArrayList<>();
		for (HomeIndexEntity.BannerBean bannerBean : bannerBeanList) {
			list.add( new BannerEntity( bannerBean.url,bannerBean.link ) );
		}
		mBvHomeIndex.instance( activity );
		mBvHomeIndex.setCenter( false );
		mBvHomeIndex.delayTime( 3 );
		mBvHomeIndex.build( list );

	}
}
