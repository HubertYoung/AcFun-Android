package com.hubertyoung.component.acfunvideo.videodetail.callback;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.hubertyoung.common.widget.circularreveal.RevealFrameLayout;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;

import static android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED;
import static android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL;
import static android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP;

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
	private final CollapsingToolbarLayout mPlayerContainer;

	public AcFunVideoAllCallBack( CollapsingToolbarLayout playerContainer, AppBarLayout appBarLayout, RevealFrameLayout videoBar, ViewPager titlePager ) {
		this.mPlayerContainer = playerContainer;
		this.mAppBarLayout = appBarLayout;
		this.mVideoBar = videoBar;
		this.mTitlePager = titlePager;
	}

	private void setCollapsingToolbarLayoutFlag( boolean isExit ) {
		ViewGroup.LayoutParams layoutParams = mPlayerContainer.getLayoutParams();
		if ( !( layoutParams instanceof AppBarLayout.LayoutParams ) ) {
			return;
		}
		if ( isExit ) {
			( ( AppBarLayout.LayoutParams ) layoutParams ).setScrollFlags( SCROLL_FLAG_SNAP | SCROLL_FLAG_EXIT_UNTIL_COLLAPSED );
			mPlayerContainer.requestLayout();
		} else {
			( ( AppBarLayout.LayoutParams ) layoutParams ).setScrollFlags( SCROLL_FLAG_SCROLL | SCROLL_FLAG_SNAP | SCROLL_FLAG_EXIT_UNTIL_COLLAPSED );
			mPlayerContainer.requestLayout();
		}
	}

	private void stopScrollStatus( boolean flag ) {
		if ( flag ) {
			mAppBarLayout.setExpanded( true, false );
//		D();TODO 隐藏键盘
			mVideoBar.setVisibility( View.INVISIBLE );
			setCollapsingToolbarLayoutFlag( true );
		}else{
			setCollapsingToolbarLayoutFlag( false );
		}
	}

	@Override
	public void onStartPrepared( String url, Object... objects ) {
		Log.e( "TAG", "onStartPrepared" );
		stopScrollStatus(true);
	}


	@Override
	public void onPrepared( String url, Object... objects ) {
		Log.e( "TAG", "onPrepared" );
		stopScrollStatus(true);
	}

	@Override
	public void onClickStartIcon( String url, Object... objects ) {
		Log.e( "TAG", "onClickStartIcon" );
		stopScrollStatus(true);
	}

	@Override
	public void onClickStartError( String url, Object... objects ) {
		Log.e( "TAG", "onClickStartError" );
		stopScrollStatus(false);
	}

	@Override
	public void onClickStop( String url, Object... objects ) {
		Log.e( "TAG", "onClickStop" );
		stopScrollStatus(false);
	}

	@Override
	public void onClickStopFullscreen( String url, Object... objects ) {
		Log.e( "TAG", "onClickStopFullscreen" );

	}

	@Override
	public void onClickResume( String url, Object... objects ) {
		Log.e( "TAG", "onClickResume" );
		stopScrollStatus(true);
	}

	@Override
	public void onClickResumeFullscreen( String url, Object... objects ) {
		Log.e( "TAG", "onClickResumeFullscreen" );

	}

	@Override
	public void onClickSeekbar( String url, Object... objects ) {
		Log.e( "TAG", "onClickSeekbar" );

	}

	@Override
	public void onClickSeekbarFullscreen( String url, Object... objects ) {
		Log.e( "TAG", "onClickSeekbarFullscreen" );

	}

	@Override
	public void onAutoComplete( String url, Object... objects ) {
		Log.e( "TAG", "onAutoComplete" );
		stopScrollStatus(false);
	}

	@Override
	public void onEnterFullscreen( String url, Object... objects ) {
		Log.e( "TAG", "onEnterFullscreen" );

	}

	@Override
	public void onQuitFullscreen( String url, Object... objects ) {
		Log.e( "TAG", "onQuitFullscreen" );

	}

	@Override
	public void onQuitSmallWidget( String url, Object... objects ) {
		Log.e( "TAG", "onQuitSmallWidget" );

	}

	@Override
	public void onEnterSmallWidget( String url, Object... objects ) {
		Log.e( "TAG", "onEnterSmallWidget" );

	}

	@Override
	public void onTouchScreenSeekVolume( String url, Object... objects ) {
		Log.e( "TAG", "onTouchScreenSeekVolume" );

	}

	@Override
	public void onTouchScreenSeekPosition( String url, Object... objects ) {
		Log.e( "TAG", "onTouchScreenSeekPosition" );

	}

	@Override
	public void onTouchScreenSeekLight( String url, Object... objects ) {
		Log.e( "TAG", "onTouchScreenSeekLight" );

	}

	@Override
	public void onPlayError( String url, Object... objects ) {
		Log.e( "TAG", "onPlayError" );
		stopScrollStatus(false);
	}

	@Override
	public void onClickStartThumb( String url, Object... objects ) {
		Log.e( "TAG", "onClickStartThumb" );
		stopScrollStatus(true);
	}

	@Override
	public void onClickBlank( String url, Object... objects ) {
		Log.e( "TAG", "onClickBlank" );

	}

	@Override
	public void onClickBlankFullscreen( String url, Object... objects ) {
		Log.e( "TAG", "onClickBlankFullscreen" );

	}
}
