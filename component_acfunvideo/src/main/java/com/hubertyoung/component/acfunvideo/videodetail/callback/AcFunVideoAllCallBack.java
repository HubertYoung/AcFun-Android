package com.hubertyoung.component.acfunvideo.videodetail.callback;

import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;

import com.hubertyoung.common.widget.circularreveal.RevealFrameLayout;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/9 09:22
 * @since:
 * @see AcFunVideoAllCallBack
 */
public class AcFunVideoAllCallBack implements VideoAllCallBack {
	private final AppBarLayout mAppBarLayout;
	private final RevealFrameLayout mVideoBar;
	private final ViewPager mTitlePager;

	public AcFunVideoAllCallBack( AppBarLayout appBarLayout, RevealFrameLayout videoBar, ViewPager titlePager ) {
		this.mAppBarLayout = appBarLayout;
		this.mVideoBar = videoBar;
		this.mTitlePager = titlePager;
	}

	@Override
	public void onStartPrepared( String url, Object... objects ) {

	}

	@Override
	public void onPrepared( String url, Object... objects ) {

	}

	@Override
	public void onClickStartIcon( String url, Object... objects ) {

	}

	@Override
	public void onClickStartError( String url, Object... objects ) {

	}

	@Override
	public void onClickStop( String url, Object... objects ) {

	}

	@Override
	public void onClickStopFullscreen( String url, Object... objects ) {

	}

	@Override
	public void onClickResume( String url, Object... objects ) {

	}

	@Override
	public void onClickResumeFullscreen( String url, Object... objects ) {

	}

	@Override
	public void onClickSeekbar( String url, Object... objects ) {

	}

	@Override
	public void onClickSeekbarFullscreen( String url, Object... objects ) {

	}

	@Override
	public void onAutoComplete( String url, Object... objects ) {

	}

	@Override
	public void onEnterFullscreen( String url, Object... objects ) {

	}

	@Override
	public void onQuitFullscreen( String url, Object... objects ) {

	}

	@Override
	public void onQuitSmallWidget( String url, Object... objects ) {

	}

	@Override
	public void onEnterSmallWidget( String url, Object... objects ) {

	}

	@Override
	public void onTouchScreenSeekVolume( String url, Object... objects ) {

	}

	@Override
	public void onTouchScreenSeekPosition( String url, Object... objects ) {

	}

	@Override
	public void onTouchScreenSeekLight( String url, Object... objects ) {

	}

	@Override
	public void onPlayError( String url, Object... objects ) {

	}

	@Override
	public void onClickStartThumb( String url, Object... objects ) {

	}

	@Override
	public void onClickBlank( String url, Object... objects ) {

	}

	@Override
	public void onClickBlankFullscreen( String url, Object... objects ) {

	}
}
