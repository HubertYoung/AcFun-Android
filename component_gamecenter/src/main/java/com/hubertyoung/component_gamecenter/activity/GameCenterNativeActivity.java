package com.hubertyoung.component_gamecenter.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hubertyoung.common.base.AbsLifecycleActivity;
import com.hubertyoung.component_gamecenter.R;
import com.hubertyoung.component_gamecenter.fragment.GameCenterFragment;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * <br>
 * function: 游戏中心
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/21 15:22
 * @since:V5.7.0
 * @desc:com.hubertyoung.component_gamecenter
 */
public class GameCenterNativeActivity extends AbsLifecycleActivity {

	private Toolbar actionBar;
	private ImageView updateDot;
	private RelativeLayout menuGameCenterManage;

	@Override
	public int getLayoutId() {
		return R.layout.activity_game_center;
	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		super.initView( savedInstanceState );
		actionBar = findViewById(R.id.view_toolbar);
		updateDot = findViewById(R.id.game_center_manage_update_dot);
		menuGameCenterManage = findViewById(R.id.menu_game_center_manage);
		getSupportFragmentManager().beginTransaction().add(R.id.container, GameCenterFragment.newInstance( "","" )).commit();
		initAction();
	}

	private void initAction() {
		RxView.clicks( menuGameCenterManage )
				.throttleFirst( 500, TimeUnit.MILLISECONDS )
				.subscribe( new Consumer< Object >() {
					@Override
					public void accept( Object o ) throws Exception {
						// TODO: 2018/11/21 游戏管理页面
//						IntentHelper.a((Activity ) this, GameManagerActivity.class);
					}
				} );
	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {

	}
}
