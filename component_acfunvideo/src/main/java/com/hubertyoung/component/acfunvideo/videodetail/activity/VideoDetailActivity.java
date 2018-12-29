package com.hubertyoung.component.acfunvideo.videodetail.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.AbsLifecycleActivity;
import com.hubertyoung.common.entity.FullContent;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.entity.Video;
import com.hubertyoung.common.entity.VideoDetail;
import com.hubertyoung.common.utils.Utils;
import com.hubertyoung.common.utils.bar.BarUtils;
import com.hubertyoung.common.utils.log.CommonLog;
import com.hubertyoung.common.widget.circularreveal.RevealFrameLayout;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.videodetail.vm.VideoDetailViewModel;
import com.hubertyoung.component_acfunvideo.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * desc: VideoDetailActivity
 *
 * @author:HubertYoung
 * @date: 2018/12/18 15:15
 * @since:
 * @see VideoDetailViewModel
 */
public class VideoDetailActivity extends AbsLifecycleActivity< VideoDetailViewModel > {

	public static final String contentId = "contentId";
	public static final String reqId = "reqId";
	public static final String groupId = "groupId";
	public static final String from = "from";
	private CoordinatorLayout mClContent;
	private Toolbar mToolbar;
	private View bottomBar;
	private AppBarLayout mAppBarLayout;
	private CollapsingToolbarLayout mPlayerContainer;
	private FrameLayout mPlayerViewContainer;
	private SimpleDraweeView mCoverView;
	private ImageView mIconVideoPlay;
	private TextView mtextTitle;
	private LinearLayout mDanmakuContainer;
	private SimpleDraweeView danmakuAvatar;
	private EditText mDanmakuInput;
	private ImageView mDanmakuSender;
	private RevealFrameLayout mVideoBar;
	private LinearLayout mPlayerViewBar;
	private TextView mPlayerOpenView;
	private ImageView videoDetailReturn;
	private ImageView report;
	private LinearLayout mTabLayout;
	private SmartTabLayout mTitleTab;
	private View mDivider;
	private ViewPager mTitlePager;
	private LinearLayout commentDetailLayout;
	private ImageView activityDetailVideoFrameClose;
	private FrameLayout commentDetailFrame;
	private ImageView commentInputBg;
	private SimpleDraweeView userAvatar;
	private TextView commentInput;
	private RelativeLayout bottomCommentNumber;
	private ImageView videoDetailCommentBottomNumberImg;
	private TextView bottomCommentNumberText;
	private View mCoverContainer;
	// TODO: 2018/12/26 AcFunPlayerView
	Object m;
	private InputMethodManager mInputMethodManager;
	private Intent mIntent;

	boolean n;
	boolean o;
	private String q;
	private String r;
	private int u;
	private String v;
	private User x;
	private Video y;
	private StandardGSYVideoPlayer mStandardGSYVideoPlayer;

	/**
	 * 是否显示详情
	 */
	private boolean H;

	/**
	 * 视频相关信息
	 */
	private FullContent w;

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
		mViewModel.requestVideoDetailInfo( u, q );
	}

	@SuppressLint( "WrongConstant" )
	@Override
	public void initView( Bundle savedInstanceState ) {
		super.initView( savedInstanceState );
		if ( Build.VERSION.SDK_INT >= 21 ) {
			getWindow().setStatusBarColor( ViewCompat.MEASURED_STATE_MASK );
		}

		mClContent = findViewById( R.id.cl_content );
		mToolbar = findViewById( R.id.view_toolbar );
		bottomBar = findViewById( R.id.comment_bottom_bar );
		mCoverContainer = findViewById( R.id.video_detail_cover_container );
		mAppBarLayout = findViewById( R.id.video_detail_appbar );
		mPlayerContainer = findViewById( R.id.video_detail_player_container );
		mPlayerViewContainer = findViewById( R.id.player_container );
		mCoverView = findViewById( R.id.detail_video_cover );
		mIconVideoPlay = findViewById( R.id.iv_video_play );
		mtextTitle = findViewById( R.id.text_title );
		mDanmakuContainer = findViewById( R.id.video_detail_danmaku_container );
		danmakuAvatar = findViewById( R.id.video_detail_danmaku_avatar );
		mDanmakuInput = findViewById( R.id.video_detail_danmaku_input );
		mDanmakuSender = findViewById( R.id.video_detail_danmaku_sender );
		mVideoBar = findViewById( R.id.fl_video_detail_bar );
		mPlayerViewBar = findViewById( R.id.video_detail_player_bar );
		mPlayerOpenView = findViewById( R.id.video_detail_play );
		videoDetailReturn = findViewById( R.id.video_detail_return );
		report = findViewById( R.id.detail_report_layout );
		mTabLayout = findViewById( R.id.ll_video_detail_tab );
		mTitleTab = findViewById( R.id.video_detail_tab );
		mDivider = findViewById( R.id.v_divider );
		mTitlePager = findViewById( R.id.title_pager );
		commentDetailLayout = findViewById( R.id.activity_detail_video_frame_layout );
		activityDetailVideoFrameClose = findViewById( R.id.activity_detail_video_frame_close );
		commentDetailFrame = findViewById( R.id.activity_detail_video_frame );
		commentInputBg = findViewById( R.id.activity_detail_video_pop_bg );
		userAvatar = findViewById( R.id.comment_video_item_view_uploader_avatar );
		commentInput = findViewById( R.id.video_detail_comment_input );
		bottomCommentNumber = findViewById( R.id.video_detail_comment_bottom_comment );
		videoDetailCommentBottomNumberImg = findViewById( R.id.video_detail_comment_bottom_number_img );
		bottomCommentNumberText = findViewById( R.id.video_detail_comment_bottom_number );

		mDanmakuInput.setOnFocusChangeListener( new View.OnFocusChangeListener() {
			public void onFocusChange( View view, boolean z ) {
//				if ( z && VideoDetailActivity.this.m != null ) {
//					VideoDetailActivity.this.m.a( 2, 1 );
//				}
//				if ( VideoDetailActivity.this.F ) {
//					VideoDetailActivity.this.mDanmakuInput.clearFocus();
//					VideoDetailActivity.this.F = false;
//				}
			}
		} );
		mDanmakuInput.setOnEditorActionListener( new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction( TextView v, int actionId, KeyEvent event ) {
				if ( actionId == EditorInfo.IME_ACTION_SEND //
						|| actionId == EditorInfo.IME_ACTION_DONE //
						|| ( event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && event.getAction() == KeyEvent.ACTION_DOWN ) ) {
					VideoDetailActivity.this.a( VideoDetailActivity.this.mDanmakuInput.getText().toString().trim() );
					VideoDetailActivity.this.mDanmakuInput.clearFocus();
				}
				return true;
			}
		} );
		this.mIntent = getIntent();
		// TODO: 2018/12/26 乐视投屏
//		if ( !AcFunApplication.b().f() ) {
//			AcFunApplication.b().e();
//		}
		this.mInputMethodManager = ( InputMethodManager ) getSystemService( "input_method" );
		this.mTitleTab.setCustomTabView( R.layout.widget_tab_video_ditail_page, R.id.detail_tab_text );
		getIntentInfo();
		y();
		loadData();
	}

	@Override
	public void doBeforeSetContentView() {
		BarUtils.setStatusBarTranslucent( getWindow(), true );
	}

	@Override
	public boolean isNeedRefresh() {
		return true;
	}

	@Override
	protected View refreshContentView() {
		return mClContent;
	}

	private void y() {
		ActionBar supportActionBar = getSupportActionBar();
		if ( supportActionBar != null ) {
			supportActionBar.setHomeButtonEnabled( true );
			supportActionBar.setDisplayHomeAsUpEnabled( true );
			supportActionBar.setDisplayShowHomeEnabled( false );
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append( "AC " );
			stringBuilder.append( this.u );
			supportActionBar.setTitle( stringBuilder.toString() );
			supportActionBar.show();
		}
	}

	private void getIntentInfo() {
		this.u = mIntent.getIntExtra( contentId, 0 );
		this.v = mIntent.getStringExtra( groupId );
		this.r = mIntent.getStringExtra( reqId );
		this.q = mIntent.getStringExtra( from );
//		PushProcessHelper.a(getIntent(), this);
		u = 4818594;
		q = "recommend";

		TextView textView = this.mtextTitle;
		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append(AssistPushConsts.MSG_KEY_ACTION);
		stringBuilder.append( "AC" );
		stringBuilder.append( this.u );
		textView.setText( stringBuilder.toString() );
	}

	@Override
	public void initToolBar() {
		if ( mToolbar != null ) {
			mToolbar.setTitle( getTitle().toString() );
			setSupportActionBar( mToolbar );
			getSupportActionBar().setDisplayHomeAsUpEnabled( true );
		}
		if ( getSupportActionBar() != null ) {
			getSupportActionBar().hide();
		}
	}

	public void a( String str ) {
		if ( m != null ) {
			// TODO: 2018/12/26 设置内容 猜测为弹幕
//			this.m.a(str);
			mDanmakuInput.setText( "" );
			hideSoftInput();
		}
	}

	private void hideSoftInput() {
		if ( this.mInputMethodManager != null ) {
			this.mInputMethodManager.hideSoftInputFromWindow( mDanmakuInput.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
		}
	}

	@Override
	protected void dataObserver() {
		super.dataObserver();
		registerObserver( VideoConstants.EVENT_KEY_VIDEODETAIL, VideoDetail.class ).observe( this, new Observer< VideoDetail >() {

			@Override
			public void onChanged( VideoDetail videoDetail ) {
				FullContent fullContent = videoDetail.convertToFullContent();
				if ( getSupportActionBar() != null ) {
					getSupportActionBar().hide();
				}
				w = fullContent;
				CommonLog.logi( fullContent.getDetailsShow() + "~~~~" + fullContent.getRedirect() + "~~~~~" );
				if ( fullContent.getDetailsShow() == 0 ) {
					Utils.startActivity( mContext, 5, fullContent.getRedirect(), null );
					ActivityCompat.finishAfterTransition( mContext );
					return;
				}
				H = fullContent.getDetailsShow() == 2;
				if ( H ) {
					bottomCommentNumberText.setBackgroundResource( R.drawable.shape_bg_green_round );
				}
				int comments = fullContent.getComments();
				if ( comments > 0 ) {
					bottomCommentNumberText.setVisibility( View.VISIBLE );
					bottomCommentNumberText.setText( comments > 999 ? "999+" : String.valueOf( comments ) );
				} else {
					bottomCommentNumberText.setVisibility( View.GONE );
				}
				x = fullContent.getUser();
				y = fullContent.getVideos().get( 0 );
				mStandardGSYVideoPlayer = new StandardGSYVideoPlayer( mContext );
				mStandardGSYVideoPlayer.setVisibility( View.VISIBLE );
				if ( mPlayerViewContainer.getChildAt( 0 ) instanceof StandardGSYVideoPlayer ) {
					mPlayerViewContainer.removeViewAt( 0 );
				}
				mPlayerViewContainer.addView( mStandardGSYVideoPlayer, 0, new FrameLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT ) );
				mStandardGSYVideoPlayer.setUp(fullContent.getVideos().get( 0 ).getUrl(), true, "测试视频");
//				k();
//				z();
//				this.m.a();
//				this.m.a(new ShowBottomBarListener() {
//					public void a() {
//						if (VideoDetailActivity.this.H) {
//							VideoDetailActivity.this.bottomBar.setVisibility(0);
//						} else if (VideoDetailActivity.this.mTitlePager.getCurrentItem() == 1) {
//							VideoDetailActivity.this.bottomBar.setVisibility(0);
//						} else {
//							VideoDetailActivity.this.bottomBar.setVisibility(0);
//						}
//					}
//
//					public void b() {
//						VideoDetailActivity.this.bottomBar.setVisibility(8);
//					}
//				});
//				ag_();
			}
		} );
	}
}

