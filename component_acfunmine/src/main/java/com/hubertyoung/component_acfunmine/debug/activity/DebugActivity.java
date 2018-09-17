package com.hubertyoung.component_acfunmine.debug.activity;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.acty.component_acfunmine.R;
import com.hubertyoung.common.base.BaseActivity;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/17 14:43
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunmine.debug.activity
 */
public class DebugActivity extends BaseActivity {

	private LinearLayout mActivityRoot;
	private FrameLayout mContentLayout;
	private Toolbar mToolbar;

	@Override
	public int getLayoutId() {
		return R.layout.acfunmine_activity_debug;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void initView( Bundle savedInstanceState ) {

	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {
		mToolbar = findViewById( R.id.view_toolbar );
		mActivityRoot = findViewById( R.id.activity_root );
		mContentLayout = findViewById( R.id.content_layout );

		setSupportActionBar( mToolbar );
		ActionBar actionBar = getSupportActionBar();
		if ( actionBar != null ) {
			actionBar.setDisplayHomeAsUpEnabled( true );
		}
	}
}
