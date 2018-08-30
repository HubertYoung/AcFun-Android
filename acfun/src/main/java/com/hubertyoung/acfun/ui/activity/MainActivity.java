package com.hubertyoung.acfun.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.hubertyoung.acfun.R;
import com.hubertyoung.common.base.BaseActivity;

import butterknife.BindView;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/8/30 19:39
 * @since:V1.0.0
 * @desc:com.hubertyoung.acfun.ui.activity
 */
public class MainActivity extends BaseActivity {

	@BindView( R.id.fl_container )
	FrameLayout mFlContainer;
	@BindView( R.id.bnb_main_view )
	BottomNavigationBar mBnbMainView;

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		mBnbMainView.clearAll();

		mBnbMainView.setMode( BottomNavigationBar.MODE_FIXED );
		mBnbMainView.setBackgroundStyle( BottomNavigationBar.BACKGROUND_STYLE_DEFAULT );
//		new BottomNavigationItem( ( int ) R.drawable.icon_videos_selected, getString( R.string.activity_main_video ) ).a( ( int ) R.drawable.icon_videos )).
//		a( new BottomNavigationItem( ( int ) R.drawable.icon_article_selected, getString( R.string.activity_main_article ) ).a( ( int ) R.drawable.icon_article ) )
//				.a( new BottomNavigationItem( ( int
//				) R.drawable.icon_dynamic_selected, getString( R.string.activity_main_dynamic ) )
//				.a( ( int ) R.drawable.icon_dynamic ) )
//				.a( new BottomNavigationItem( ( int ) R.drawable.icon_mine_selected, getString( R.string.activity_main_mine ) ).a( ( int ) R.drawable.icon_mine ) )
//				.c( ( int ) R.color.them_color )
//				.d( ( int ) R.color.gray )
//				.f( 0 )
//				.a();
		mBnbMainView.addItem( new BottomNavigationItem( R.drawable.icon_videos_selected, getString( R.string.activity_main_video ) ).setInactiveIconResource( R.drawable.icon_videos ) )
				.addItem( new BottomNavigationItem( R.drawable.icon_article_selected, getString( R.string.activity_main_article ) ).setInactiveIconResource( R.drawable.icon_article ) )
				.addItem( new BottomNavigationItem( R.drawable.icon_dynamic_selected, getString( R.string.activity_main_dynamic ) ).setInactiveIconResource( R.drawable.icon_dynamic ) )
				.addItem( new BottomNavigationItem( R.drawable.icon_mine_selected,  getString( R.string.activity_main_mine ) ).setInactiveIconResource( R.drawable.icon_mine ) )
				.setActiveColor( R.color.red )
				.setInActiveColor( R.color.gray  )
				.initialise();
//		bottomNavigationBar.selectTab( lastSelectedPosition, true )

	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {

	}
}
