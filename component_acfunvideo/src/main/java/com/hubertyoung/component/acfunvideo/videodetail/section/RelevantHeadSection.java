package com.hubertyoung.component.acfunvideo.videodetail.section;

import android.app.Activity;
import android.support.design.internal.FlowLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.entity.FullContent;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.data.StringUtil;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.component_acfunvideo.R;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/2 16:05
 * @since:
 * @see RelevantHeadSection
 */
public class RelevantHeadSection extends StatelessSection {
	private Activity mActivity;
	private FullContent mFullContent;

	public RelevantHeadSection( Activity activity ) {
		super( SectionParameters.builder()//
				.itemResourceId( R.layout.widget_video_detail_content_header )//
				.build() );
		this.mActivity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return 1;
	}

	@Override
	public int getSpanSizeLookup( int position ) {
		return 2;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new RelevantHeadViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		RelevantHeadViewHolder viewHolder = ( RelevantHeadViewHolder ) holder;
		User mUser = mFullContent.getUser();
		viewHolder.mDetailVideoTitle.setText( mFullContent.getTitle() );
		TextView textView = viewHolder.mDetailVideoViewChannel;
		Activity activity = this.mActivity;
		Object[] objArr = new Object[ 1 ];
		int i = 0;
		objArr[ 0 ] = mFullContent.getChannelName() == null ? "" : mFullContent.getChannelName();
		textView.setText( activity.getString( R.string.video_detail_content_play_channel, objArr ) );
		viewHolder.mDetailVideoViewCount.setText( mActivity.getString( R.string.video_detail_content_play_count_text, StringUtil.formatChineseNum( mFullContent.getViews() ) ) );
		viewHolder.mDetailVideoDanmakuCount.setText( mActivity.getString( R.string.video_detail_content_danmu_count_text, StringUtil.formatChineseNum( mFullContent.getDanmakuCount() ) ) );
		viewHolder.mBananaCount.setText( StringUtil.formatChineseNum( mFullContent.getGoldBananaCount() ) );
		viewHolder.mStowsNum.setText( StringUtil.formatChineseNum( mFullContent.getStows() ) );
		viewHolder.mDetailVideoContentId.setText( mActivity.getString( R.string.video_detail_content_id_text, Integer.valueOf( mFullContent.getCid() ) ) );
		viewHolder.mDetailVideoDescription.setText( Html.fromHtml( String.valueOf( mFullContent.getDescription() ) ) );

		LinkBuilder.on( textView ).addLink(//
				new Link( Pattern.compile( "(aa|AA|ab|AB|ac|AC)\\d+" ) )//
						.setTextColor( mActivity.getResources().getColor( R.color.bangou_clickable_color ) )//
						.setBold( false )//
						.setOnClickListener( new Link.OnClickListener() {//
							@Override
							public void onClick( @NotNull String s ) {
								ToastUtil.showWarning( s );
//								Bundle bundle = new Bundle();
//								bundle.putString(BangouJumpActivity.e, str);
//								IntentHelper.a(this.mActivity, BangouJumpActivity.class, bundle);
							}
						} ) )//
				.build();
		showBottomTag( viewHolder );
		viewHolder.mDetailVideoToggleOpenArea.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
				if ( viewHolder.mTextToggleArea.getText().toString().equals( mActivity.getString( R.string.video_detail_content_toggle_tip ) ) ) {
					viewHolder.mDetailVideoToggleCloseArea.setVisibility( View.VISIBLE );
					viewHolder.mTextToggleArea.setText( mActivity.getString( R.string.video_detail_content_toggle_close ) );
//					MobclickAgent.onEvent( viewHolder.mActivity, UmengCustomAnalyticsIDs.bG );
//					KanasCommonUtil.c( KanasConstants.bO, null );
					return;
				}
				viewHolder.mDetailVideoToggleCloseArea.setVisibility( View.GONE );
				viewHolder.mTextToggleArea.setText( mActivity.getString( R.string.video_detail_content_toggle_tip ) );
			}
		} );
		viewHolder.mDetailVideoToggleCloseArea.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
				viewHolder.mDetailVideoToggleCloseArea.setVisibility( View.GONE );
				viewHolder.mTextToggleArea.setText( mActivity.getString( R.string.video_detail_content_toggle_tip ) );
			}
		} );
		viewHolder.mDetailVideoUploaderArea.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
//				MobclickAgent.onEvent( viewHolder.mActivity, UmengCustomAnalyticsIDs.cg );
//				IntentHelper.a( viewHolder.mFragment, viewHolder.mUser, 0, 0, 0, 0, 444 );
			}
		} );
		ImageLoaderUtil.loadNetImage( mUser.getAvatar(), viewHolder.mDetailVideoUploaderAvatar );
		viewHolder.mDetailVideoUploaderName.setText( mUser.getName() );
//		if ( !TextUtils.isEmpty( mFullContent.getAlbumTitle() ) ) {
//			( ( ViewStub ) ButterKnife.a( viewHolder.mView, ( int ) R.id.vs_ablum_item ) ).inflate();
//			ButterKnife.a( viewHolder.mView, ( int ) R.id.item_ablum_layout ).setOnClickListener( new View.OnClickListener() {
//				public void onClick( View view ) {
//					IntentHelper.d( viewHolder.mActivity, fullContent.getAlbumID(), "video_detail" );
//				}
//			} );
//			Utils.a( viewHolder.mActivity, fullContent.getAlbumCoverImg(), ( SimpleDraweeView ) ButterKnife.a( viewHolder.mView, ( int ) R.id.item_ablum_img ) );
//			( ( TextView ) ButterKnife.a( viewHolder.mView, ( int ) R.id.text_ablum_info ) ).setText( fullContent.getAlbumTitle() );
//		}
//		f();
//		viewHolder.mDetailStarLayout.setOnClickListener( new View.OnClickListener() {
//			public void onClick( View view ) {
//				if ( SigninHelper.a().u() ) {
//					viewHolder.a();
//					if ( viewHolder.F ) {
//						ApiHelper.a().f( ( Object ) viewHolder, viewHolder.mFullContent.getCid(), new ExtStowCallBack( false ) );
//					} else {
//						ApiHelper.a().b( ( Object ) viewHolder, viewHolder.mFullContent.getCid(), new ExtStowCallBack( true ) );
//						int parentChannelId = viewHolder.mFullContent.getParentChannelId();
//						int channelId = viewHolder.mFullContent.getChannelId();
//						StringBuilder stringBuilder = new StringBuilder();
//						stringBuilder.append( "ac" );
//						stringBuilder.append( viewHolder.mFullContent.getCid() );
//						AnalyticsUtil.a( parentChannelId, channelId, stringBuilder.toString(), viewHolder.mUser.getUid() );
//					}
//					return;
//				}
//				IntentHelper.a( viewHolder.mActivity, DialogLoginActivity.class, 222 );
//			}
//		} );
//		viewHolder.mDetailBananaLayout.setOnClickListener( new View.OnClickListener() {
//			public void onClick( View view ) {
//				KanasCommonUtil.c( KanasConstants.ch, null );
//				if ( !SigninHelper.a().u() ) {
//					IntentHelper.a( viewHolder.mActivity, DialogLoginActivity.class, 222 );
//				} else if ( SigninHelper.a().b() == viewHolder.mFullContent.getUser().getUid() ) {
//					ToastUtil.a( ( int ) R.string.video_detail_push_banana_error_text );
//				} else if ( SigninHelper.a().d() ) {
//					viewHolder.mIVideoDetailView.b();
//				} else {
//					DialogFragment a = DialogUtils.a( R.string.registered_user_guide_tip, R.string.registered_user_guide_cancel, R.string.registered_user_guide_confirm, null, new DialogInterface
//							.OnClickListener() {
//						public void onClick( DialogInterface dialogInterface, int i ) {
//							IntentHelper.a( viewHolder.mActivity, QuestionActivity.class, 111 );
//						}
//					} );
//					a.setCancelable( true );
//					a.show( viewHolder.mActivity.getFragmentManager(), class.getName() );
//				}
//			}
//		} ); viewHolder.mDetailDownloadLayout.setOnClickListener( new View.OnClickListener() {
//			public void onClick( View view ) {
//				viewHolder.mIVideoDetailView.c();
//			}
//		} );
//		viewHolder.mDetailShareLayout.setOnClickListener( new View.OnClickListener() {
//			public void onClick( View view ) {
//				Bundle bundle = new Bundle();
//				bundle.putString( "name", viewHolder.mFullContent.getTitle() );
//				bundle.putInt( "ac_id", viewHolder.mFullContent.getCid() );
//				bundle.putInt( KanasConstants.ai, viewHolder.mUser.getUid() );
//				KanasCommonUtil.c( KanasConstants.dc, bundle );
//				viewHolder.mIVideoDetailView.d();
//			}
//		} );
//		viewHolder.I = new ArrayList();
//		viewHolder.J = new ArrayList();
//		viewHolder.H = new VideoPartListAdapter( viewHolder.mActivity, false );
//		viewHolder.mVideoDetailPartList.setAdapter( viewHolder.H );
//		viewHolder.J.addAll( viewHolder.mFullContent.getVideos() );
//		if ( viewHolder.mFullContent.getVideos().size() > 6 ) {
//			viewHolder.mVideoDetailPartsTotalButtonLayout.setVisibility( 0 );
//			viewHolder.mVideoDetailPartsTotalButton.setOnClickListener( new View.OnClickListener() {
//				public void onClick( View view ) {
//					viewHolder.mIVideoDetailView.j();
//				}
//			} );
//			while ( i < 6 ) {
//				viewHolder.I.add( viewHolder.mFullContent.getVideos().get( i ) );
//				i++;
//			}
//		} else {
//			viewHolder.I.addAll( viewHolder.mFullContent.getVideos() );
//			viewHolder.mVideoDetailPartsTotalButtonLayout.setVisibility( 8 );
//		}
//		viewHolder.H.a( viewHolder.I );
	}

	private void showBottomTag( RelevantHeadViewHolder viewHolder ) {
		if ( mFullContent.getTags() == null ) {
			viewHolder.mDetailVideoTag.setVisibility( View.GONE );
			return;
		}
		viewHolder.mDetailVideoTag.setVisibility( View.VISIBLE );
		for (int i = 0; i < this.mFullContent.getTags().size(); i++) {
			String tag = mFullContent.getTags().get( i );
			View view = mActivity.getLayoutInflater().inflate( R.layout.item_bangumi_detail_bottom_tag, null, false );
			TextView textView = ( TextView ) view.findViewById( R.id.tag_text );
			view.setLayoutParams( new LinearLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT ) );
			view.setOnClickListener( new View.OnClickListener() {
				public void onClick( View view ) {
					ToastUtil.showWarning( tag );
//					Bundle bundle = new Bundle();
//					String str = (String) view.getTag();
//					bundle.putString("query", str);
//					IntentHelper.a(this.mActivity, SearchActivity.class, bundle);
//					MobclickAgent.onEvent(this.mActivity, UmengCustomAnalyticsIDs.aI);
//					AnalyticsUtil.k(this.mActivity, str);
				}
			} );
			textView.setText( tag );
			viewHolder.mDetailVideoTag.addView( view );
		}
	}

	public void setData( FullContent fullContent ) {
		this.mFullContent = fullContent;
	}

	static class RelevantHeadViewHolder extends RecyclerView.ViewHolder {
		SimpleDraweeView mDetailVideoUploaderAvatar;
		TextView mDetailVideoUploaderName;
		TextView mDetailVideoUploadDate;
		TextView mDetailVideoFollowBt;
		RelativeLayout mFollowLayout;
		ImageView mImgGoNotificationSetting;
		LinearLayout mDetailVideoUploaderArea;
		TextView mDetailVideoTitle;
		TextView mDetailVideoViewChannel;
		TextView mDetailVideoViewCount;
		TextView mDetailVideoDanmakuCount;
		TextView mTextToggleArea;
		LinearLayout mDetailVideoToggleOpenArea;
		TextView mDetailVideoContentId;
		TextView mDetailVideoDescription;
		FlowLayout mDetailVideoTag;
		LinearLayout mDetailVideoToggleCloseArea;
		TextView mBananaCount;
		LinearLayout mDetailBananaLayout;
		ImageButton mDetailStarImage;
		TextView mStowsNum;
		LinearLayout mDetailStarLayout;
		ImageButton mDetailDownloadImg;
		TextView mDetailDownloadText;
		LinearLayout mDetailDownloadLayout;
		LinearLayout mDetailShareLayout;

		RelevantHeadViewHolder( View view ) {
			super( view );
			mDetailVideoUploaderAvatar = ( SimpleDraweeView ) view.findViewById( R.id.detail_video_uploader_avatar );
			mDetailVideoUploaderName = ( TextView ) view.findViewById( R.id.detail_video_uploader_name );
			mDetailVideoUploadDate = ( TextView ) view.findViewById( R.id.detail_video_upload_date );
			mDetailVideoFollowBt = ( TextView ) view.findViewById( R.id.detail_video_follow_bt );
			mFollowLayout = ( RelativeLayout ) view.findViewById( R.id.follow_layout );
			mImgGoNotificationSetting = ( ImageView ) view.findViewById( R.id.img_go_notification_setting );
			mDetailVideoUploaderArea = ( LinearLayout ) view.findViewById( R.id.detail_video_uploader_area );
			mDetailVideoTitle = ( TextView ) view.findViewById( R.id.detail_video_title );
			mDetailVideoViewChannel = ( TextView ) view.findViewById( R.id.detail_video_view_channel );
			mDetailVideoViewCount = ( TextView ) view.findViewById( R.id.detail_video_view_count );
			mDetailVideoDanmakuCount = ( TextView ) view.findViewById( R.id.detail_video_danmaku_count );
			mTextToggleArea = ( TextView ) view.findViewById( R.id.text_toggle_area );
			mDetailVideoToggleOpenArea = ( LinearLayout ) view.findViewById( R.id.detail_video_toggle_open_area );
			mDetailVideoContentId = ( TextView ) view.findViewById( R.id.detail_video_content_id );
			mDetailVideoDescription = ( TextView ) view.findViewById( R.id.detail_video_description );
			mDetailVideoTag = ( FlowLayout ) view.findViewById( R.id.detail_video_tag );
			mDetailVideoToggleCloseArea = ( LinearLayout ) view.findViewById( R.id.detail_video_toggle_close_area );
			mBananaCount = ( TextView ) view.findViewById( R.id.banana_count );
			mDetailBananaLayout = ( LinearLayout ) view.findViewById( R.id.detail_banana_layout );
			mDetailStarImage = ( ImageButton ) view.findViewById( R.id.detail_star_image );
			mStowsNum = ( TextView ) view.findViewById( R.id.stows_num );
			mDetailStarLayout = ( LinearLayout ) view.findViewById( R.id.detail_star_layout );
			mDetailDownloadImg = ( ImageButton ) view.findViewById( R.id.detail_download_img );
			mDetailDownloadText = ( TextView ) view.findViewById( R.id.detail_download_text );
			mDetailDownloadLayout = ( LinearLayout ) view.findViewById( R.id.detail_download_layout );
			mDetailShareLayout = ( LinearLayout ) view.findViewById( R.id.detail_share_layout );
		}
	}
}
