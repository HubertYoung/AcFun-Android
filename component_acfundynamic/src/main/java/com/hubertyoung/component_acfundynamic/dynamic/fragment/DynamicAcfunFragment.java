package com.hubertyoung.component_acfundynamic.dynamic.fragment;


import android.os.Bundle;

import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.component_acfundynamic.R;

/**
 * <br>
 * function:AC动态
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/31 16:21
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfundynamic.dynamic.fragment
 */
public class DynamicAcfunFragment extends BaseFragment {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private String mParam1;
	private String mParam2;


	public DynamicAcfunFragment() {
	}

	public static DynamicAcfunFragment newInstance( String param1, String param2 ) {
		DynamicAcfunFragment fragment = new DynamicAcfunFragment();
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
		return R.layout.fragment_dynamic_acfun;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	protected void initView( Bundle savedInstanceState ) {

	}

}
