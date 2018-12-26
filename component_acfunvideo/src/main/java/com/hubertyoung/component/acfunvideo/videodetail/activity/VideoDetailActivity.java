package com.hubertyoung.component.acfunvideo.videodetail.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hubertyoung.common.base.AbsLifecycleActivity;
import com.hubertyoung.component.acfunvideo.videodetail.vm.VideoDetailViewModel;
import com.hubertyoung.component_acfunvideo.R;

/**
 * desc: VideoDetailActivity
 *
 * @author:HubertYoung
 * @date: 2018/12/18 15:15
 * @since:
 * @see VideoDetailViewModel
 */
public class VideoDetailActivity extends AbsLifecycleActivity< VideoDetailViewModel > {

	public static String contentId = "contentId";
	public static String reqId = "reqId";
	public static String groupId = "groupId";
	public static String from = "from";

	public static void launch( Context context, int contentId, String reqId, String groupId, String from ) {
		Intent intent = new Intent( context, VideoDetailActivity.class );
		if ( !( context instanceof Activity ) ) {
			//调用方没有设置context或app间组件跳转，context为application
			intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		}
		Bundle bundle = new Bundle();
		bundle.putInt( VideoDetailActivity.contentId, contentId );
		bundle.putString( VideoDetailActivity.reqId, reqId );
		bundle.putString( VideoDetailActivity.groupId, groupId );
		bundle.putString( VideoDetailActivity.from, from );
		intent.putExtras( bundle );
		context.startActivity( intent );
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_detail_video;
	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {

	}

}

