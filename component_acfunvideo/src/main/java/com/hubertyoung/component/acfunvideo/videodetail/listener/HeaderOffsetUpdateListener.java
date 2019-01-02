package com.hubertyoung.component.acfunvideo.videodetail.listener;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.LinearLayout;

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
	private LinearLayout mPlayerViewBar;
	private Drawable mDrawable;
	private int cacheFlag = -1;
	private int d;

	public HeaderOffsetUpdateListener( VideoDetailActivity videoDetailActivity, AppBarLayout appBarLayout, SimpleDraweeView coverView, LinearLayout playerViewBar, View divider ) {
		this.mVideoDetailActivity = videoDetailActivity;
		this.mAppBarLayout = appBarLayout;
		this.mCoverView = coverView;
		this.mPlayerViewBar = playerViewBar;
		this.mDivider = divider;
		mPlayerViewBar.setVisibility( View.INVISIBLE );
		this.mDrawable = mDivider.getBackground().mutate();
		this.mDrawable.setAlpha( 0 );
		refreshDivider();
	}

	@Override
	public void onOffsetChanged( AppBarLayout appBarLayout, int i ) {
		if (i != this.cacheFlag ) {
			float f = 0.0f;
			if (mAppBarLayout.getTotalScrollRange() <= 0 && i > this.d) {
				this.d = i;
				if (this.d != 0 && i > this.d) {
					f = ((float) i) / ((float) this.d);
				}
			} else if (mAppBarLayout.getTotalScrollRange() > 0) {
				f = ((float) (-i)) / ((float) mAppBarLayout.getTotalScrollRange());
			}
			this.mDrawable.setAlpha((int) (255.0f * f));
			mCoverView.setPivotY((float) mCoverView.getHeight());
			if (f >= 0.3f) {
				mPlayerViewBar.setVisibility(View.VISIBLE);
				mPlayerViewBar.setAlpha(f);
			} else {
				mPlayerViewBar.setVisibility(View.INVISIBLE);
			}
			refreshDivider();
			mVideoDetailActivity.hideSoftInput();
			this.cacheFlag = i;
		}
	}
	void refreshDivider() {
		if (Build.VERSION.SDK_INT >= 16) {
			mDivider.setBackground(this.mDrawable);
		} else {
			mDivider.setBackgroundDrawable(this.mDrawable);
		}
	}
}
