package com.kento.welcome.commonlibrary;

import android.os.Bundle;

import com.kento.common.base.BaseActivity;

public class MainActivity extends BaseActivity {

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		initAction();
	}

	private void initAction() {

	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {

	}

}
