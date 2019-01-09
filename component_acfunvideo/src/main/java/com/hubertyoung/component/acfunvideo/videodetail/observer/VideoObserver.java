package com.hubertyoung.component.acfunvideo.videodetail.observer;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.hubertyoung.component.acfunvideo.videoplayer.video.AcFunVideoPlayer;
import com.shuyu.gsyvideoplayer.GSYVideoADManager;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/9 15:11
 * @since:
 * @see VideoObserver
 */
public class VideoObserver implements LifecycleObserver {
	private AcFunVideoPlayer mStandardGSYVideoPlayer;

	public VideoObserver( AcFunVideoPlayer standardGSYVideoPlayer ) {
		this.mStandardGSYVideoPlayer = standardGSYVideoPlayer;
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
	public void resumeListener() {
		if ( mStandardGSYVideoPlayer != null ) {
			mStandardGSYVideoPlayer.onVideoResume();
		}
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
	public void pauseListener() {
		if ( mStandardGSYVideoPlayer != null ) {
			mStandardGSYVideoPlayer.onVideoPause();
		}
	}
	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	public void startListener() {
		GSYVideoADManager.releaseAllVideos();
//		if ( mStandardGSYVideoPlayer != null ) {
//			mStandardGSYVideoPlayer.onVideoReset();
//		}
	}
}
