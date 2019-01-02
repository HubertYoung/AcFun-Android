package com.hubertyoung.component.acfunvideo.videodetail.fragment;

import android.os.Bundle;

import com.hubertyoung.common.base.AbsLifecycleFragment;
import com.hubertyoung.common.entity.FullContent;
import com.hubertyoung.component.acfunvideo.videodetail.activity.VideoDetailActivity;
import com.hubertyoung.component.acfunvideo.videodetail.vm.VideoDetailCommentViewModel;
import com.hubertyoung.component_acfunvideo.R;

/**
 * desc: VideoDetailCommentFragment
 *
 * @author:HubertYoung
 * @date: 2019/01/02 14:49
 * @since:
 * @see VideoDetailCommentViewModel
 */
public class VideoDetailCommentFragment extends AbsLifecycleFragment< VideoDetailCommentViewModel > {

	public static VideoDetailCommentFragment newInstance( FullContent fullContent, int floor, String from, boolean isHapame, String requestId, String groupId, int videoId ) {
		Bundle bundle = new Bundle();
		bundle.putSerializable( "fullContent", fullContent );
		bundle.putInt( "floor", floor );
		bundle.putString( "from", from );
		bundle.putBoolean( "isHapame", isHapame );
		bundle.putString( "requestId", requestId );
		bundle.putString( VideoDetailActivity.groupId, groupId );
		bundle.putInt( "videoId", videoId );
		VideoDetailCommentFragment videoDetailCommentFragment = new VideoDetailCommentFragment();
		videoDetailCommentFragment.setArguments( bundle );
		return videoDetailCommentFragment;
	}

	@Override
	protected void initView( Bundle state ) {
		super.initView( state );
	}

	@Override
	public int getLayoutResource() {
		return R.layout.video_detail_comment_fragment;
	}

	@Override
	protected void initToolBar() {

	}

}

