package com.hubertyoung.component.acfunvideo.bangumidetail.activity;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.AbsLifecycleActivity;
import com.hubertyoung.common.baserx.LiveBus;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.bar.BarUtils;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.utils.os.AppUtils;
import com.hubertyoung.common.widget.HeightWrappingViewPager;
import com.hubertyoung.component.acfunvideo.bangumidetail.adapter.BangumiDetailFragmentPagerAdapter;
import com.hubertyoung.component.acfunvideo.bangumidetail.vm.BangumiDetailViewModel;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.entity.BangumiDetailBean;
import com.hubertyoung.component.acfunvideo.entity.BangumiDetailBeanRaw;
import com.hubertyoung.component_acfunvideo.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * desc: BangumiDetailActivityNew 视频详情
 *
 * @author:HubertYoung
 * @date: 2018/12/18 16:55
 * @since:
 * @see BangumiDetailViewModel
 */
public class BangumiDetailActivityNew extends AbsLifecycleActivity< BangumiDetailViewModel > {
	public static final String BANGUMIDETAILID = "bangumi_detail_id";
	private SimpleDraweeView mTitleBackgroundImage;
	private TextView mTitleTextView;
	private TextView mDesCribeTextView;
	private TextView mPlayZhengPianTextView;
	private TextView mPlayCountTextView;
	private TextView mFavouriteCountTextView;
	private TextView mUpdateTextView;
	private TextView mCommentCountTextView;
	private TextView mFavouriteTextView;
	private TextView mDownloadTextView;
	private TextView mShareTextView;
	private RadioGroup mRelatedBangumisGroup;
	private SmartTabLayout mPagerTab;
	private HeightWrappingViewPager mPager;
	private LinearLayout mCanotPlayLayout;
	private LinearLayout llBangumiDetailTagsLayout;
	private Toolbar toolbar;
	private String mContentId;

//	private int userId = 273722;

//	private String mTitle;

	public static void launch( Context context, String contentId ) {
		Intent intent = new Intent( context, BangumiDetailActivityNew.class );
		if ( !( context instanceof Activity ) ) {
			//调用方没有设置context或app间组件跳转，context为application
			intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		}
		Bundle bundle = new Bundle();
		bundle.putString( BangumiDetailActivityNew.BANGUMIDETAILID, contentId );
		intent.putExtras( bundle );
		context.startActivity( intent );
	}

	@Override
	public void doBeforeSetContentView() {
		super.doBeforeSetContentView();
		BarUtils.setStatusBarTranslucent( getWindow(), true );
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_bangumi_detail_new;
	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		super.initView( savedInstanceState );
		if ( getIntent() != null && getIntent().getExtras() != null ) {
			mContentId = getIntent().getExtras().getString( BangumiDetailActivityNew.BANGUMIDETAILID );
		}
		if ( AppUtils.isDebuggable() ) {
			mContentId = "5022158";
		}
		mTitleBackgroundImage = findViewById( R.id.sdv_background );
		mTitleTextView = findViewById( R.id.tv_title );
		mDesCribeTextView = findViewById( R.id.tv_describe );
		mPlayZhengPianTextView = findViewById( R.id.tv_play_zheng_pian );
		mPlayCountTextView = findViewById( R.id.tv_play_number );
		mFavouriteCountTextView = findViewById( R.id.tv_favourite_number );
		mUpdateTextView = findViewById( R.id.tv_update_status );
		mCommentCountTextView = findViewById( R.id.tv_comment_number );
		mFavouriteTextView = findViewById( R.id.tv_favourite );
		mDownloadTextView = findViewById( R.id.tv_download );
		mShareTextView = findViewById( R.id.tv_share );
		mRelatedBangumisGroup = findViewById( R.id.ll_related_bangumi );
		mPagerTab = findViewById( R.id.tab_bangumi_detail_down );
		mPager = findViewById( R.id.vp_bangumi_detail_down );
		mCanotPlayLayout = findViewById( R.id.ll_bangumi_detail_can_not_play );
		llBangumiDetailTagsLayout = findViewById( R.id.ll_bangumi_detail_tags_layout );
		toolbar = findViewById( R.id.toolbar_head );

		BarUtils.setPaddingSmart( toolbar );

		initAction();
		loadData();
	}

	private void initAction() {
		toolbar.setNavigationOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				finish();
			}
		} );

	}

	@Override
	protected void loadData() {
		mViewModel.requestBangumisDetail( mContentId );
	}

	@Override
	protected void showLoading( String title ) {
		super.showLoading( title );

	}

	@Override
	protected void dataObserver() {
		LiveBus.getDefault().subscribe( VideoConstants.EVENT_KEY_BANGUMI_DETAIL_INFO, BangumiDetailBeanRaw.class ).observe( this, new Observer< BangumiDetailBeanRaw >() {
			@Override
			public void onChanged( BangumiDetailBeanRaw bangumiDetailBeanRaw ) {
				setBangumiDetailInfo( bangumiDetailBeanRaw );
			}
		} );
	}

	private void setBangumiDetailInfo( BangumiDetailBeanRaw bangumiDetailBeanRaw ) {
//		if ( bangumiDetailBeanRaw.userId > 0 ) {
//			userId = bangumiDetailBeanRaw.userId;
//		}
		// TODO: 2018/12/19 登录后获取追番状态
		BangumiDetailBean bangumiDetailBean = bangumiDetailBeanRaw.convertRawToBangumiDetailBean();
		if ( bangumiDetailBean != null ) {
//			mTitle = bangumiDetailBean.title;
			ImageLoaderUtil.loadNetImage( bangumiDetailBean.titleImage, mTitleBackgroundImage );
			setViewInfo( bangumiDetailBean );
			isShowPager( bangumiDetailBean );
			// TODO: 2018/12/19 显示流式布局标签
//			bindView( bangumiDetailBean.tags );
		}
	}


	@Override
	public void initToolBar() {
		if ( toolbar != null ) {
			toolbar.setTitle( "" );
			toolbar.setNavigationIcon( R.drawable.icon_back );
//			setSupportActionBar( toolbar );
//			getSupportActionBar().setDisplayHomeAsUpEnabled( true );
		}
	}

	private void isShowPager( BangumiDetailBean bangumiDetailBean ) {
		ArrayList< BangumiDetailBean.VideoGroupTitleBean > titleBeanList = bangumiDetailBean.videoGroupTitle;
		ArrayList< BangumiDetailBean.VideoGroupContentBean > groupContent = bangumiDetailBean.videoGroupContent;
		showCanPlay( bangumiDetailBean.isCanPlay, titleBeanList, groupContent );
		BangumiDetailFragmentPagerAdapter bangumiDetailFragmentPagerAdapter = new BangumiDetailFragmentPagerAdapter( getSupportFragmentManager() );
		bangumiDetailFragmentPagerAdapter.setData( mContentId, titleBeanList, groupContent, bangumiDetailBean);
		mPager.setAdapter( bangumiDetailFragmentPagerAdapter );
		mPagerTab.setViewPager( mPager );
		mPagerTab.setCustomTabView( R.layout.widget_tab_bangumi_ditail_page, R.id.detail_tab_text );
		mPagerTab.getTabAt( 17170445 );
	}

	private void showCanPlay( boolean isCanPlay, ArrayList< BangumiDetailBean.VideoGroupTitleBean > titleBeanList, ArrayList< BangumiDetailBean.VideoGroupContentBean > groupContent ) {
		mCanotPlayLayout.setVisibility( isCanPlay ? View.GONE : View.VISIBLE );
		isShowPager( isCanPlay );
		if ( !isCanPlay ) {
			return;
		}
		if ( titleBeanList == null || titleBeanList.isEmpty() || groupContent == null || groupContent.isEmpty() ) {
			isShowPager( false );
			return;
		}
		isShowPager( true );
		if ( titleBeanList.size() == 1 ) {
			mPagerTab.setVisibility( View.GONE );
		} else {
			mPagerTab.setVisibility( View.VISIBLE );
		}
	}
	private void isShowPager( boolean isShowPager) {
		if (isShowPager) {
			mPagerTab.setVisibility(View.VISIBLE);
			mPager.setVisibility(View.VISIBLE);
		}else {
			mPagerTab.setVisibility( View.GONE );
			mPager.setVisibility( View.GONE );
		}
	}
	private void setViewInfo( BangumiDetailBean bangumiDetailBean ) {
		List< BangumiDetailBean.RelatedBangumisBean > list = bangumiDetailBean.relatedBangumis;
		mTitleTextView.setText( bangumiDetailBean.title );
		mPlayCountTextView.setText( bangumiDetailBean.viewsCount );
		mFavouriteCountTextView.setText( bangumiDetailBean.favouriteCount );
		mUpdateTextView.setText( bangumiDetailBean.updateContent );
		mCommentCountTextView.setText( bangumiDetailBean.commentCount );
		if ( bangumiDetailBean.isCanComment ) {
			mCommentCountTextView.setClickable( true );
			mCommentCountTextView.setCompoundDrawablesWithIntrinsicBounds( getResources().getDrawable( R.drawable.icon_detail_comment_number ), null, null, null );
			mCommentCountTextView.setTextColor( getResources().getColor( R.color.white ) );
		} else {
			mCommentCountTextView.setClickable( false );
			mCommentCountTextView.setCompoundDrawablesWithIntrinsicBounds( getResources().getDrawable( R.drawable.icon_detail_comment_number_disable ), null, null, null );
			mCommentCountTextView.setTextColor( getResources().getColor( R.color.text_gray2_color ) );
		}
		if ( bangumiDetailBean.isCanDownload ) {
			mDownloadTextView.setCompoundDrawablesWithIntrinsicBounds( getResources().getDrawable( R.drawable.icon_detail_cache ), null, null, null );
			mDownloadTextView.setTextColor( getResources().getColor( R.color.white ) );
		} else {
			mDownloadTextView.setCompoundDrawablesWithIntrinsicBounds( getResources().getDrawable( R.drawable.icon_detail_cache_disable ), null, null, null );
			mDownloadTextView.setTextColor( getResources().getColor( R.color.text_gray2_color ) );
		}
		mDownloadTextView.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
				if ( !bangumiDetailBean.isCanDownload ) {
					ToastUtil.showError( R.string.bangumi_detail_can_not_cache_text );
				} else {
					ToastUtil.showError( "2018/12/19 视频去缓存 " );
				}
				// TODO: 2018/12/19 视频去缓存
//				else if ( BangumiDetailActivityNew.i() ) {
//					BangumiDetailActivityNew.t();
//				}
			}
		} );
		mDesCribeTextView.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
				ToastUtil.showError( "2018/12/19 BangumiDetailDescribeActivity" );
//				Intent intent = new Intent( BangumiDetailActivityNew.ad_(), BangumiDetailDescribeActivity.class );
//				intent.putExtra( BangumiDetailDescribeActivity.e, str );
//				intent.putExtra( BangumiDetailDescribeActivity.f, BangumiDetailActivityNew.v );
//				IntentHelper.bindView( BangumiDetailActivityNew.ad_(), intent );
			}
		} );
		if ( TextUtils.isEmpty( bangumiDetailBean.targetUrl ) ) {
			mPlayZhengPianTextView.setVisibility( View.GONE );
		} else {
			mPlayZhengPianTextView.setVisibility( View.VISIBLE );
			mPlayZhengPianTextView.setOnClickListener( new View.OnClickListener() {
				public void onClick( View view ) {
					// TODO: 2018/12/19 去播放
					ToastUtil.showWarning( "去播放 " + bangumiDetailBean.targetUrl );
//					if ( !TextUtils.isEmpty( bangumiDetailBean.targetUrl ) ) {
//						Intent intent = new Intent( BangumiDetailActivityNew.ad_(), WebViewActivity.class );
//						intent.putExtra( "url", obj );
//						IntentHelper.bindView( BangumiDetailActivityNew.ad_(), intent );
//					}
				}
			} );
		}
		mCommentCountTextView.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
				if ( bangumiDetailBean.isCanComment ) {
					// TODO: 2018/12/19 去评论
					ToastUtil.showWarning( "去评论" );
//					Intent intent = new Intent( BangumiDetailActivityNew.ad_(), CommentActivity.class );
//					Bundle bundle = new Bundle();
//					bundle.putInt( VideoDetailActivity.i, Integer.valueOf( BangumiDetailActivityNew.v ).intValue() );
//					bundle.putString( "title", BangumiDetailActivityNew.u.title );
//					bundle.putInt( "upId", BangumiDetailActivityNew.C );
//					bundle.putString( KanasConstants.av, KanasConstants.ba );
//					bundle.putInt( "pid", BangumiDetailActivityNew.u.parentChannelId );
//					bundle.putInt( RankActivity.e, BangumiDetailActivityNew.u.channelId );
//					intent.putExtras( bundle );
//					IntentHelper.bindView( BangumiDetailActivityNew.ad_(), intent );
				} else {
					ToastUtil.showWarning( R.string.bangumi_detail_cant_comment );
				}
			}
		} );
		mFavouriteTextView.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
				// TODO: 2018/12/19 标记特喜欢
				ToastUtil.showWarning( "标记特喜欢" );
//				if ( SigninHelper.bindView().u() ) {
//					BangumiDetailActivityNew.q();
//				} else {
//					BangumiDetailActivityNew.s();
//				}
			}
		} );
		mShareTextView.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
				ToastUtil.showWarning( "去分享" );
//				Share share = new Share();
//				share.setShareUrl( BangumiDetailActivityNew.u.share.shareUrl );
//				StringBuilder stringBuilder = new StringBuilder();
//				stringBuilder.append( "aa" );
//				stringBuilder.append( BangumiDetailActivityNew.v );
//				share.contentId = stringBuilder.toString();
//				share.title = BangumiDetailActivityNew.u.title;
//				share.description = BangumiDetailActivityNew.u.intro;
//				share.cover = BangumiDetailActivityNew.u.titleImage;
//				share.type = ContentType.BANGUMI;
//				tv.acfun.core.utils.Utils.bindView( BangumiDetailActivityNew.ad_(), share, null );
			}
		} );
		if ( list == null || list.size() <= 1 ) {
			mRelatedBangumisGroup.setVisibility( View.GONE );
			return;
		}
		mRelatedBangumisGroup.setVisibility( View.VISIBLE );
		mRelatedBangumisGroup.removeAllViews();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			BangumiDetailBean.RelatedBangumisBean relatedBangumisBean = list.get( i );
			if ( relatedBangumisBean != null ) {
				String name = relatedBangumisBean.name;
				String relatedBangumisId = relatedBangumisBean.id;
				if ( !( name == null || relatedBangumisId == null ) ) {
					RadioButton radioButton = ( RadioButton ) LayoutInflater.from( mContext ).inflate( R.layout.item_bangumi_detail_releated_bangumi, mRelatedBangumisGroup, false );
					radioButton.setText( name );
					radioButton.setOnClickListener( new View.OnClickListener() {
						public void onClick( View view ) {
							ToastUtil.showWarning( "item_bangumi_detail_releated_bangumi" );
							mContentId = relatedBangumisId;
//							BangumiDetailActivityNew.o();
						}
					} );
					mRelatedBangumisGroup.addView( radioButton );
					if ( relatedBangumisId.equals( mContentId ) ) {
						radioButton.setBackground( getResources().getDrawable( R.drawable.shape_bg_red_background_50dp_corners ) );
						radioButton.setTextColor( getResources().getColor( R.color.white ) );
					}
				}
			}
		}
	}
}

