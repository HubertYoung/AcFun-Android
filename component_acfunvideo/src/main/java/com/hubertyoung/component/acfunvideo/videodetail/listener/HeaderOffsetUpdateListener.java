package com.hubertyoung.component.acfunvideo.videodetail.listener;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.component.acfunvideo.videodetail.activity.VideoDetailActivity;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/2 14:11
 * @since:
 * @see HeaderOffsetUpdateListener
 */
public class HeaderOffsetUpdateListener implements AppBarLayout.OnOffsetChangedListener {
	private VideoDetailActivity mVideoDetailActivity;
	private View mDivider;
	private AppBarLayout mAppBarLayout;
	private SimpleDraweeView mCoverView;
	private TextView mPlayerOpenView;
	private Toolbar mToolbar;
	private Drawable mDrawable;
	private int cacheFlag = -1;
	private int d;

	public HeaderOffsetUpdateListener( VideoDetailActivity videoDetailActivity, AppBarLayout appBarLayout, SimpleDraweeView coverView, Toolbar toolbar, TextView mPlayerOpenView, View divider ) {
		mVideoDetailActivity = videoDetailActivity;
		mAppBarLayout = appBarLayout;
		mCoverView = coverView;
		mToolbar = toolbar;
		this.mPlayerOpenView = mPlayerOpenView;
		mDivider = divider;
//		mToolbar.setVisibility( View.INVISIBLE );
		mDrawable = mDivider.getBackground().mutate();
		mDrawable.setAlpha( 0 );
		mToolbar.getBackground().setAlpha( 0 );
		mPlayerOpenView.setAlpha( 0 );
		refreshDivider();
	}

	@Override
	public void onOffsetChanged( AppBarLayout appBarLayout, int i ) {
		if ( i != cacheFlag ) {
			float f = 0.0f;
			if ( mAppBarLayout.getTotalScrollRange() <= 0 && i > this.d ) {
				this.d = i;
				if ( this.d != 0 && i > this.d ) {
					f = ( ( float ) i ) / ( ( float ) this.d );
				}
			} else if ( mAppBarLayout.getTotalScrollRange() > 0 ) {
				f = ( ( float ) ( -i ) ) / ( ( float ) mAppBarLayout.getTotalScrollRange() );
			}
			this.mDrawable.setAlpha( ( int ) ( 255.0f * f ) );
			mCoverView.setPivotY( ( float ) mCoverView.getHeight() );
			if ( f <= 0.2f ) {
//				mToolbar.setVisibility(View.VISIBLE);
				mToolbar.getBackground().setAlpha( 0 );
				mPlayerOpenView.setAlpha( 0 );
			} else {
//				mToolbar.setVisibility(View.INVISIBLE);
				mToolbar.getBackground().setAlpha( ( int ) ( 255 * f ) );
				mPlayerOpenView.setAlpha( f );
			}
			refreshDivider();
			mVideoDetailActivity.hideSoftInput();
			cacheFlag = i;
			Log.e( "TAG", " f == " + f );

		}
	}

	void refreshDivider() {
		if ( Build.VERSION.SDK_INT >= 16 ) {
			mDivider.setBackground( this.mDrawable );
		} else {
			mDivider.setBackgroundDrawable( this.mDrawable );
		}
	}
}
