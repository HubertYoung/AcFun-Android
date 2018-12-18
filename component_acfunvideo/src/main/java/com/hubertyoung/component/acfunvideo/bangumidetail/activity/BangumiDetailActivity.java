package com.hubertyoung.component.acfunvideo.bangumidetail.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hubertyoung.common.base.AbsLifecycleActivity;
import com.hubertyoung.component.acfunvideo.bangumidetail.vm.BangumiDetailViewModel;
import com.hubertyoung.component_acfunvideo.R;

/**
 * desc: BangumiDetailActivity 视频详情
 *
 * @author:HubertYoung
 * @date: 2018/12/18 16:55
 * @since:
 * @see BangumiDetailViewModel
 */
public class BangumiDetailActivity extends AbsLifecycleActivity< BangumiDetailViewModel > {
	public static final String BANGUMIDETAILID = "bangumi_detail_id";

	public static void launch( Context context, String contentId ) {
		Intent intent = new Intent( context, BangumiDetailActivity.class );
		if ( !( context instanceof Activity ) ) {
			//调用方没有设置context或app间组件跳转，context为application
			intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		}
		Bundle bundle = new Bundle();
		bundle.putString( BangumiDetailActivity.BANGUMIDETAILID, contentId );
		intent.putExtras( bundle );
		context.startActivity( intent );
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_bangumi_detail;
	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {

	}
}

