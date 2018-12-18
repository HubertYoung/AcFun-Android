package com.hubertyoung.component.acfunvideo.bangumidetail.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.AbsLifecycleActivity;
import com.hubertyoung.common.utils.bar.BarUtils;
import com.hubertyoung.common.utils.os.AppUtils;
import com.hubertyoung.common.widget.HeightWrappingViewPager;
import com.hubertyoung.component.acfunvideo.bangumidetail.vm.BangumiDetailViewModel;
import com.hubertyoung.component_acfunvideo.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * desc: BangumiDetailActivityNew 视频详情
 *
 * @author:HubertYoung
 * @date: 2018/12/18 16:55
 * @since:
 * @see BangumiDetailViewModel
 */
public class BangumiDetailActivityNew extends AbsLifecycleActivity< BangumiDetailViewModel > {
	public static final String BANGUMIDETAILID = "bangumi_detail_id";
	private SimpleDraweeView sdvBackground;
	private TextView tvTitle;
	private TextView tvDescribe;
	private TextView tvPlayZhengPian;
	private TextView tvPlayNumber;
	private TextView tvFavouriteNumber;
	private TextView tvUpdateStatus;
	private TextView tvCommentNumber;
	private TextView tvFavourite;
	private TextView tvDownload;
	private TextView tvShare;
	private RadioGroup llRelatedBangumi;
	private SmartTabLayout tabBangumiDetailDown;
	private HeightWrappingViewPager vpBangumiDetailDown;
	private LinearLayout llBangumiDetailCanNotPlay;
	private LinearLayout llBangumiDetailTagsLayout;
	private Toolbar toolbar;
	private String mContentId;

	public static void launch( Context context, String contentId ) {
		Intent intent = new Intent( context, BangumiDetailActivityNew.class );
		if ( !( context instanceof Activity ) ) {
			//调用方没有设置context或app间组件跳转，context为application
			intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		}
		Bundle bundle = new Bundle();
		bundle.putString( BangumiDetailActivityNew.BANGUMIDETAILID, contentId );
		intent.putExtras( bundle );
		context.startActivity( intent );
	}

	@Override
	public void doBeforeSetContentView() {
		super.doBeforeSetContentView();
		BarUtils.setStatusBarTranslucent( getWindow(), true );
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_bangumi_detail_new;
	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		super.initView( savedInstanceState );
		if ( getIntent() != null && getIntent().getExtras() != null) {
			mContentId = getIntent().getExtras().getString( BangumiDetailActivityNew.BANGUMIDETAILID );
		}
		if ( AppUtils.isDebuggable() ){
			mContentId = "5022158";
		}
		sdvBackground = findViewById( R.id.sdv_background );
		tvTitle = findViewById( R.id.tv_title );
		tvDescribe = findViewById( R.id.tv_describe );
		tvPlayZhengPian = findViewById( R.id.tv_play_zheng_pian );
		tvPlayNumber = findViewById( R.id.tv_play_number );
		tvFavouriteNumber = findViewById( R.id.tv_favourite_number );
		tvUpdateStatus = findViewById( R.id.tv_update_status );
		tvCommentNumber = findViewById( R.id.tv_comment_number );
		tvFavourite = findViewById( R.id.tv_favourite );
		tvDownload = findViewById( R.id.tv_download );
		tvShare = findViewById( R.id.tv_share );
		llRelatedBangumi = findViewById( R.id.ll_related_bangumi );
		tabBangumiDetailDown = findViewById( R.id.tab_bangumi_detail_down );
		vpBangumiDetailDown = findViewById( R.id.vp_bangumi_detail_down );
		llBangumiDetailCanNotPlay = findViewById( R.id.ll_bangumi_detail_can_not_play );
		llBangumiDetailTagsLayout = findViewById( R.id.ll_bangumi_detail_tags_layout );
		toolbar = findViewById( R.id.toolbar );
		initAction();
		loadData();
	}

	private void initAction() {
		toolbar.setNavigationOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				finish();
			}
		} );

	}

	@Override
	protected void loadData() {
		mViewModel.requestBangumisDetail( mContentId );

	}

	@Override
	public void initToolBar() {
		if ( toolbar != null ) {
			toolbar.setTitle( "" );
			setSupportActionBar( toolbar );
			getSupportActionBar().setDisplayHomeAsUpEnabled( true );
		}
	}
}

