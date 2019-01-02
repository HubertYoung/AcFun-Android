package com.hubertyoung.component.acfunvideo.videodetail.fragment;

import android.os.Bundle;

import com.hubertyoung.common.base.AbsLifecycleFragment;
import com.hubertyoung.common.entity.FullContent;
import com.hubertyoung.component.acfunvideo.videodetail.vm.VideoDetailRelevantViewModel;
import com.hubertyoung.component_acfunvideo.R;

/**
 * desc: VideoDetailRelevantFragment
 *
 * @author:HubertYoung
 * @date: 2019/01/02 14:45
 * @since:
 * @see VideoDetailRelevantViewModel
 */
public class VideoDetailRelevantFragment extends AbsLifecycleFragment< VideoDetailRelevantViewModel > {

	public static VideoDetailRelevantFragment newInstance( FullContent fullContent ) {
		Bundle bundle = new Bundle();
		bundle.putSerializable( "fullContent", fullContent );
//		VideoDetailRelevantFragment videoDetailRelevantFragment = new VideoDetailRelevantFragment(iVideoDetailView);
		VideoDetailRelevantFragment videoDetailRelevantFragment = new VideoDetailRelevantFragment();
		videoDetailRelevantFragment.setArguments( bundle );
		return videoDetailRelevantFragment;
	}

	@Override
	protected void initView( Bundle state ) {
		super.initView( state );
	}

	@Override
	public int getLayoutResource() {
		return R.layout.video_detail_relevant_fragment;
	}

	@Override
	protected void initToolBar() {

	}

}

