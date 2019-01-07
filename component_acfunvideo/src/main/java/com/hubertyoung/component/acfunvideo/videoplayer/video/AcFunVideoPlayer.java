package com.hubertyoung.component.acfunvideo.videoplayer.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hubertyoung.component_acfunvideo.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/7 16:11
 * @since:
 * @see AcFunVideoPlayer
 */
public class AcFunVideoPlayer extends StandardGSYVideoPlayer {

	public AcFunVideoPlayer( Context context, Boolean fullFlag ) {
		super( context, fullFlag );
	}

	public AcFunVideoPlayer( Context context ) {
		super( context );
	}

	public AcFunVideoPlayer( Context context, AttributeSet attrs ) {
		super( context, attrs );
	}

	@Override
	public int getLayoutId() {
		return R.layout.widget_video_layout_acfun;
	}

	@Override
	protected void updateStartImage() {
		if ( mStartButton instanceof ImageView ) {
			ImageView imageView = ( ImageView ) mStartButton;
			if ( mCurrentState == CURRENT_STATE_PLAYING ) {
				imageView.setImageResource( R.drawable.selector_video_click_pause );
			} else if ( mCurrentState == CURRENT_STATE_ERROR ) {
				imageView.setImageResource( R.drawable.selector_video_click_error );
			} else {
				imageView.setImageResource( R.drawable.selector_video_click_play );
			}
		}
	}
}
