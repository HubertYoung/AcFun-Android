package com.hubertyoung.component.acfunvideo.videodetail.activity;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
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
import com.hubertyoung.common.entity.Video;
import com.hubertyoung.common.entity.VideoDetail;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.SigninHelper;
import com.hubertyoung.common.utils.Utils;
import com.hubertyoung.common.utils.bar.BarUtils;
import com.hubertyoung.common.utils.log.CommonLog;
import com.hubertyoung.common.widget.circularreveal.RevealFrameLayout;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.entity.PlayerVideoInfo;
import com.hubertyoung.component.acfunvideo.videodetail.adapter.VideoDetailPagerAdapter;
import com.hubertyoung.component.acfunvideo.videodetail.fragment.VideoDetailCommentFragment;
import com.hubertyoung.component.acfunvideo.videodetail.fragment.VideoDetailRelevantFragment;
import com.hubertyoung.component.acfunvideo.videodetail.listener.HeaderOffsetUpdateListener;
import com.hubertyoung.component.acfunvideo.videodetail.vm.VideoDetailViewModel;
import com.hubertyoung.component.acfunvideo.videoplayer.video.AcFunVideoPlayer;
import com.hubertyoung.component_acfunvideo.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	//	private ImageView mIconVideoPlay;
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
	private InputMethodManager mInputMethodManager;
	private Intent mIntent;

	boolean n;
	boolean o;
	private String mFrom;
	private String mReqId;
	private int mContentId;
	private String mGroupId;
	//	private User x;
	private Video mVideo;
	private AcFunVideoPlayer mStandardGSYVideoPlayer;

	/**
	 * 是否显示详情
	 */
	private boolean isDetailsShow;

	private VideoDetailPagerAdapter mVideoDetailPagerAdapter;
	private VideoDetailRelevantFragment mVideoDetailRelevantFragment;
	private VideoDetailCommentFragment mVideoDetailCommentFragment;

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
		getMViewModel().requestVideoDetailInfo( mContentId, mFrom );
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
//		mIconVideoPlay = findViewById( R.id.iv_video_play );
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
		mIntent = getIntent();
		// TODO: 2018/12/26 乐视投屏
//		if ( !AcFunApplication.isInclude().f() ) {
//			AcFunApplication.isInclude().e();
//		}
		mInputMethodManager = ( InputMethodManager ) getSystemService( "input_method" );
		mTitleTab.setCustomTabView( R.layout.widget_tab_video_ditail_page, R.id.detail_tab_text );
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
	public View refreshContentView() {
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
			stringBuilder.append( mContentId );
			supportActionBar.setTitle( stringBuilder.toString() );
			supportActionBar.show();
		}
	}

	private void getIntentInfo() {
		mContentId = mIntent.getIntExtra( contentId, 0 );
		mGroupId = mIntent.getStringExtra( groupId );
		mReqId = mIntent.getStringExtra( reqId );
		mFrom = mIntent.getStringExtra( from );
//		PushProcessHelper.a(getIntent(), this);
		mContentId = 4818594;
		mFrom = "recommend";

		TextView textView = mtextTitle;
		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append(AssistPushConsts.MSG_KEY_ACTION);
		stringBuilder.append( "AC" );
		stringBuilder.append( mContentId );
		textView.setText( stringBuilder.toString() );
	}

	@Override
	public void initToolBar() {
//		if ( mToolbar != null ) {
		BarUtils.setPaddingSmart( mToolbar );
		BarUtils.setPaddingSmart( mClContent );
		mToolbar.setTitle( getTitle().toString() );
		setSupportActionBar( mToolbar );
		getSupportActionBar().setDisplayHomeAsUpEnabled( true );
		if ( getSupportActionBar() != null ) {
			getSupportActionBar().hide();
		}
	}

	public void a( String str ) {
		if ( mPlayerViewContainer != null ) {
			// TODO: 2018/12/26 设置内容 猜测为弹幕
//			m.a(str);
			mDanmakuInput.setText( "" );
			hideSoftInput();
		}
	}

	public void hideSoftInput() {
		if ( mInputMethodManager != null ) {
			mInputMethodManager.hideSoftInputFromWindow( mDanmakuInput.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
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
				CommonLog.logi( fullContent.getDetailsShow() + "~~~~" + fullContent.getRedirect() + "~~~~~" );
				if ( fullContent.getDetailsShow() == 0 ) {
					Utils.startActivity( mContext, 5, fullContent.getRedirect(), null );
					ActivityCompat.finishAfterTransition( mContext );
					return;
				}
				isDetailsShow = fullContent.getDetailsShow() == 2;
				if ( isDetailsShow ) {
					bottomCommentNumberText.setBackgroundResource( R.drawable.shape_bg_green_round );
				}
				int comments = fullContent.getComments();
				if ( comments > 0 ) {
					bottomCommentNumberText.setVisibility( View.VISIBLE );
					bottomCommentNumberText.setText( comments > 999 ? "999+" : String.valueOf( comments ) );
				} else {
					bottomCommentNumberText.setVisibility( View.GONE );
				}
				mVideo = fullContent.getVideos().get( 0 );
				mStandardGSYVideoPlayer = new AcFunVideoPlayer( mContext );
				mStandardGSYVideoPlayer.setVisibility( View.VISIBLE );
				if ( mPlayerViewContainer.getChildAt( 0 ) instanceof StandardGSYVideoPlayer ) {
					mPlayerViewContainer.removeViewAt( 0 );
				}

				mPlayerViewContainer.addView( mStandardGSYVideoPlayer );
				// TODO: 2019/1/2 默认播放第一条
				setVideoInfo( fullContent.getVideos().get( 0 ), fullContent, false );
				// TODO: 2019/1/2 未知
//				k();
				initViewPager( fullContent );
//				m.a();
//				m.a(new ShowBottomBarListener() {
//					public void a() {
//						if (VideoDetailActivity.H) {
//							VideoDetailActivity.bottomBar.setVisibility(0);
//						} else if (VideoDetailActivity.mTitlePager.getCurrentItem() == 1) {
//							VideoDetailActivity.bottomBar.setVisibility(0);
//						} else {
//							VideoDetailActivity.bottomBar.setVisibility(0);
//						}
//					}
//
//					public void isInclude() {
//						VideoDetailActivity.bottomBar.setVisibility(8);
//					}
//				});
//				ag_();
			}
		} );
	}

	private void initViewPager( FullContent fullContent ) {
		ImageLoaderUtil.loadNetImage( fullContent.getCover(), this.mCoverView );
		ImageLoaderUtil.loadNetImage( SigninHelper.getInstance().getAvatar(), this.danmakuAvatar );
		initBehavior();
		initAdapter( fullContent );
//		this.m.a(new OnBackImageClickListener() {
//			public void a(int i) {
//				KanasCommonUtil.c(KanasConstants.ci, null);
//				if (VideoDetailActivity.this.m.f()) {
//					VideoDetailActivity.this.af_();
//				}
//			}
//		});
//		this.m.a(new OnPlayerStateChangeListener() {
//			public void a(int i) {
//				if (i == 16386) {
//					VideoDetailActivity.this.mAppBarLayout.setExpanded(true, false);
//					VideoDetailActivity.this.D();
//					VideoDetailActivity.this.mVideoBar.setVisibility(4);
//				} else if (i == PlayerState.o) {
//					VideoDetailActivity.this.mVideoBar.setVisibility(0);
//					VideoDetailActivity.this.mTitlePager.setVisibility(0);
//				}
//				VideoDetailActivity.this.a(VideoDetailActivity.this.A());
//			}
//
//			public void isInclude(int i) {
//				boolean isInclude = VideoDetailActivity.this.A();
//				if (isInclude) {
//					VideoDetailActivity.this.mAppBarLayout.setExpanded(true);
//				}
//				VideoDetailActivity.this.a(isInclude);
//			}
//
//			public void a(Video video) {
//				EventHelper.a().a(new OnNotifyPlayingVideoEvent(video));
//			}
//
//			public void a() {
//				VideoDetailActivity.this.isInclude(true);
//			}
//
//			public void c(int i) {
//				if (i == 16386) {
//					VideoDetailActivity.this.mTitlePager.setVisibility(8);
//				} else if (i == PlayerState.o) {
//					VideoDetailActivity.this.mTitlePager.setVisibility(0);
//				}
//			}
//		});
//		if (NetworkUtils.e(ac_()) && PreferenceUtil.c()) {
//			this.E.postDelayed(new Runnable() {
//				public void run() {
//					VideoDetailActivity.this.mCoverView.performClick();
//				}
//			}, 500);
//		}
	}

	private void initAdapter( FullContent fullContent ) {
		int mVid;
		this.mVideoDetailPagerAdapter = new VideoDetailPagerAdapter( getSupportFragmentManager() );
		mVideoDetailRelevantFragment = VideoDetailRelevantFragment.newInstance( fullContent );
		String analyticsID = "commentatvideodetailspage";// UmengCustomAnalyticsIDs.V
		if ( this.mVideo == null ) {
			mVid = 0;
		} else {
			mVid = this.mVideo.getVid();
		}
		mVideoDetailCommentFragment = VideoDetailCommentFragment.newInstance( fullContent, 0, analyticsID, isDetailsShow, mReqId, mGroupId, mVid );
		// TODO: 2019/1/2 设置 IVideoDetailView
//		mVideoDetailCommentFragment.a( ( IVideoDetailView ) this );
		if ( isDetailsShow ) {
			mVideoDetailPagerAdapter.setData( mVideoDetailCommentFragment, "评论" );
			mTitleTab.setVisibility( View.GONE );
			report.setVisibility( View.GONE );
			mPlayerViewBar.setBackgroundColor( getResources().getColor( R.color.progress_bar_hapame_played ) );
//			mIconVideoPlay.setBackground( getResources().getDrawable( R.drawable.hapame_play ) );
			mDanmakuSender.setBackground( getResources().getDrawable( R.drawable.hapame_danmu_send ) );
			bottomBar.setVisibility( View.VISIBLE );
		} else {
			mVideoDetailPagerAdapter.setData( mVideoDetailRelevantFragment, "简介" );
			mVideoDetailPagerAdapter.setData( mVideoDetailCommentFragment, "评论" );
			mTitleTab.setVisibility( View.VISIBLE );
			report.setVisibility( View.VISIBLE );
			mPlayerViewBar.setBackgroundColor( getResources().getColor( R.color.them_color ) );
//			mIconVideoPlay.setBackground( getResources().getDrawable( R.mipmap.ic_video_play ) );
			mDanmakuSender.setBackground( getResources().getDrawable( R.drawable.icon_send_danmu ) );
		}
		mTitlePager.setAdapter( mVideoDetailPagerAdapter );
		mTitleTab.setViewPager( mTitlePager );
		if ( mVideoDetailPagerAdapter.getCount() == 2 ) {
			TextView textView = mTitleTab.getTabAt( 1 ).findViewById( R.id.count );
			if ( textView != null && fullContent.getComments() > 0 ) {
				textView.setVisibility( View.VISIBLE );
				textView.setText( String.valueOf( fullContent.getComments() ) );
			}
		}
		ImageLoaderUtil.loadNetImage( SigninHelper.getInstance().getAvatar(), userAvatar );
		initTitlePagerListener();
	}

	private void initTitlePagerListener() {
		this.mTitlePager.addOnPageChangeListener( new ViewPager.SimpleOnPageChangeListener() {
			public void onPageSelected( int i ) {
				//统计计数
//				String str = "info";
//				if (!(mVideoDetailPagerAdapter == null || mTitleTab == null)) {
//					if (i == 1) {
//						bottomCommentNumber.setVisibility(8);
//						I = I + 1;
//						J = System.currentTimeMillis();
//						str = "comment";
//					} else {
//						bottomCommentNumber.setVisibility(0);
//						str = "info";
//						K = K + (System.currentTimeMillis() - J);
//					}
//				}
				hideSoftInput();
//				Bundle bundle = new Bundle();
//				bundle.putInt("ac_id", u);
//				bundle.putString(KanasConstants.af, str);
//				KanasCommonUtil.a(KanasConstants.D, bundle);
//				bundle = new Bundle();
//				bundle.putString(KanasConstants.af, str);
//				KanasCommonUtil.c(KanasConstants.bN, bundle);
			}
		} );
	}

	private void initBehavior() {
		TypedValue typedValue = new TypedValue();
		getTheme().resolveAttribute( android.R.attr.actionBarSize, typedValue, true );
		if ( this.isDetailsShow ) {
			this.mPlayerContainer.setMinimumHeight( getResources().getDimensionPixelSize( typedValue.resourceId ) );
		} else {
			this.mPlayerContainer.setMinimumHeight( getResources().getDimensionPixelSize( typedValue.resourceId ) );
		}
		mAppBarLayout.addOnOffsetChangedListener( new HeaderOffsetUpdateListener( this, mAppBarLayout, mCoverView, mPlayerViewBar, mDivider ) );
		CoordinatorLayout.LayoutParams layoutParams = ( CoordinatorLayout.LayoutParams ) this.mAppBarLayout.getLayoutParams();
		AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
		behavior.setDragCallback( new AppBarLayout.Behavior.DragCallback() {
			public boolean canDrag( @NonNull AppBarLayout appBarLayout ) {
				return mStandardGSYVideoPlayer.isInPlayingState();
			}
		} );
		layoutParams.setBehavior( behavior );
		mDanmakuContainer.setPivotY( 0.0f );
		// TODO: 2019/1/7 暂时显示布局
		mDanmakuContainer.setVisibility( View.VISIBLE );
		LayoutTransition layoutTransition = this.mPlayerContainer.getLayoutTransition();
		if ( layoutTransition != null ) {
			Animator ofFloat = ObjectAnimator.ofFloat( null, "scaleY", new float[]{ 0.0f, 1.0f } );
			ofFloat.setDuration( layoutTransition.getDuration( 2 ) );
			layoutTransition.setAnimator( LayoutTransition.APPEARING, ofFloat );
			layoutTransition.setStartDelay( LayoutTransition.APPEARING, 0 );
			layoutTransition.setInterpolator( LayoutTransition.APPEARING, layoutTransition.getInterpolator( 0 ) );
		}
	}

	public void setVideoInfo( Video video, FullContent fullContent, boolean isOnce ) {
		if ( mPlayerViewContainer != null ) {
//			mPlayerViewContainer.a(mCoverContainer.getHeight() + 40);
			PlayerVideoInfo playerVideoInfo = new PlayerVideoInfo( video, fullContent.getParentChannelId(), fullContent.getChannelId(), fullContent.getCid(), 2, fullContent.getTitle() );
			playerVideoInfo.setUploaderData( fullContent.getUser() );
			playerVideoInfo.setVideoList( fullContent.getVideos() );
			playerVideoInfo.setAllowPlayWithMobileOnce( isOnce );
			playerVideoInfo.setVideoCover( fullContent.getCover() );
			playerVideoInfo.setFrom( mFrom );
			playerVideoInfo.setDes( fullContent.getDescription() );
			playerVideoInfo.setHapame( isDetailsShow );
			playerVideoInfo.setShareUrl( fullContent.getShareUrl() );
			playerVideoInfo.setReleaseTime( new SimpleDateFormat( "yyyy-MM-dd HH:mm" ).format( new Date( fullContent.getReleaseDate() ) ) );
			playerVideoInfo.setVideoDetailFrom( true );
			playerVideoInfo.setReqId( mReqId );
			playerVideoInfo.setGroupId( mGroupId );
			playerVideoInfo.setTitle( fullContent.getTitle() );
//			mPlayerViewContainer.a( playerVideoInfo );
			mtextTitle.setVisibility( View.GONE );
			mCoverContainer.setVisibility( View.GONE );
			mPlayerViewContainer.setVisibility( View.VISIBLE );
//			mPlayerViewContainer.isInclude();
//			EventHelper.a().a(new OnNotifyPlayingVideoEvent(video));
			mPlayerOpenView.setText( R.string.activity_video_detail_resume_play_tip );

			mStandardGSYVideoPlayer.setUp( "https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo/7_ffe10cbf6500021d8f2b04a0cd1d0203.mp4", true, playerVideoInfo.getVideoTitle() );
//			<com.facebook.drawee.view.SimpleDraweeView
//			android:id="@+id/detail_video_cover"
//			android:layout_width="match_parent"
//			android:layout_height="200dp"
//			app:actualImageScaleType="centerCrop"
//			app:failureImage="@color/background_gray_color"
//			app:failureImageScaleType="centerCrop"
//			app:placeholderImage="@color/background_gray_color"
//			app:placeholderImageScaleType="centerCrop"
//			app:viewAspectRatio="1.78"/>
			View view = LayoutInflater.from( this ).inflate( R.layout.widget_video_detail_header, null );
			mStandardGSYVideoPlayer.setThumbImageView( view );
			ImageLoaderUtil.loadNetImage( playerVideoInfo.getVideoCover(), mStandardGSYVideoPlayer.getThumbImageView().findViewById( R.id.detail_video_cover ) );
		}
	}
}

