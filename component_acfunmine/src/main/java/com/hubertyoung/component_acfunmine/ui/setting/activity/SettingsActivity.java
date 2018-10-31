package com.hubertyoung.component_acfunmine.ui.setting.activity;

import android.os.Bundle;

import com.hubertyoung.component_acfunmine.R;
import com.hubertyoung.common.base.BaseActivity;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/16 18:14
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunmine.setting.activity
 */
public class SettingsActivity extends BaseActivity {

	@Override
	public int getLayoutId() {
		return R.layout.activity_settings;
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

	}

	public static void launch( BaseActivity activity ) {
		activity.startActivity( SettingsActivity.class );
	}
}
