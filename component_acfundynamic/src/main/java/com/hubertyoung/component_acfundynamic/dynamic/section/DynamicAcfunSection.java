package com.hubertyoung.component_acfundynamic.dynamic.section;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseActivityNew;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.data.StringUtil;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.component_acfundynamic.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/31 17:17
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfundynamic.dynamic.section
 */
public class DynamicAcfunSection extends Section {
	private final BaseActivityNew mActivity;
	private List< RegionBodyContent > data;

	public DynamicAcfunSection( BaseActivityNew activity ) {
		super( new SectionParameters.Builder( R.layout.item_recommend_uploader_rss )//
//				.headerResourceId( R.layout.widget_region_header_text )//
//				.footerResourceId( R.layout.widget_region_bottom_menu )//
//				.emptyResourceId( R.layout.item_region_empty_bangumi )
				.build() );
		this.mActivity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null ? 0 : data.size();
	}

	@Override
	public int getSpanSizeLookup( int position ) {
		return 6;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new DynamicAcfunViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		DynamicAcfunViewHolder viewHolderRecommendUploader = ( DynamicAcfunViewHolder ) holder;
		RegionBodyContent regionBodyContent2 = data.get( position );
		if ( !( regionBodyContent2 == null || regionBodyContent2.children == null ) ) {
			RegionBodyContent regionBodyContent3 = regionBodyContent2.children.get( 0 );
			regionBodyContent3.reqId = regionBodyContent2.reqId;
			regionBodyContent3.groupId = regionBodyContent2.groupId;
			ImageLoaderUtil.loadNetImage( regionBodyContent2.images.get( 0 ), viewHolderRecommendUploader.avatar );
			viewHolderRecommendUploader.nickName.setText( regionBodyContent2.title );
			viewHolderRecommendUploader.time.setText( StringUtil.formattingTimeByPlaceholder( regionBodyContent3.time ) );
			StringBuilder stringBuilder4;
			// TODO: 2018/11/1 投稿人
//			if ( !TextUtils.isEmpty( ServerChannelHelper.a().e( regionBodyContent3.channel.id ) ) ) {
//				textView = viewHolderRecommendUploader.belong;
//				stringBuilder4 = new StringBuilder();
//				stringBuilder4.append( "投稿于 " );
//				stringBuilder4.append( ServerChannelHelper.a().e( regionBodyContent3.channel.id ) );
//				textView.setText( stringBuilder4.toString() );
//			} else if ( TextUtils.isEmpty( regionBodyContent3.channel.name ) ) {
			viewHolderRecommendUploader.belong.setVisibility( View.INVISIBLE );
//			} else {
//				textView = viewHolderRecommendUploader.belong;
//				stringBuilder4 = new StringBuilder();
//				stringBuilder4.append( "投稿于 " );
//				stringBuilder4.append( regionBodyContent3.channel.name );
//				textView.setText( stringBuilder4.toString() );
//			}
			viewHolderRecommendUploader.uploaderRL.setOnClickListener( new View.OnClickListener() {
				public void onClick( View view ) {
//					if ( DynamicAcfunAdapter.this.am != null ) {
//						Serializable user = new User();
//						user.setUid( Integer.parseInt( regionBodyContent2.contentId ) );
//						Bundle bundle = new Bundle();
//						bundle.putSerializable( "user", user );
//						Intent intent = new Intent( DynamicAcfunAdapter.this.al, NewContributionActivity.class );
//						intent.putExtras( bundle );
//						DynamicAcfunAdapter.this.am.startActivityForResult( intent, 4 );
//					}
				}
			} );
			viewHolderRecommendUploader.followLayout.setVisibility( View.VISIBLE );
			viewHolderRecommendUploader.follow.setVisibility( View.VISIBLE );
			viewHolderRecommendUploader.followImg.setVisibility( View.INVISIBLE );
//			if ( a_( Integer.parseInt( regionBodyContent2.contentId ) ) ) {
//				viewHolderRecommendUploader.followLayout.setBackgroundResource( R.drawable.shape_bg_clocked_in );
//				viewHolderRecommendUploader.follow.setTextColor( this.al.getResources().getColor( R.color.text_deep_gray_color ) );
//				viewHolderRecommendUploader.follow.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0, 0 );
//				viewHolderRecommendUploader.follow.setText( R.string.fragment_attention_me_cancel );
//				viewHolderRecommendUploader.followImg.setBackgroundResource( R.drawable.animation_unfollow_loading );
//			} else {
			viewHolderRecommendUploader.followLayout.setBackgroundResource( R.drawable.shape_bg_clock_in );
			viewHolderRecommendUploader.follow.setTextColor( mActivity.getResources().getColor( R.color.white ) );
			viewHolderRecommendUploader.follow.setTextColor( mActivity.getResources().getColor( R.color.white ) );
			viewHolderRecommendUploader.follow.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_follow_img, 0, 0, 0 );
			viewHolderRecommendUploader.follow.setText( R.string.fragment_attention_me );
			viewHolderRecommendUploader.followImg.setBackgroundResource( R.drawable.animation_follow_loading );
//			}
			viewHolderRecommendUploader.follow.setOnClickListener( new View.OnClickListener() {
				public void onClick( View view ) {
					ToastUtil.showWarning( "关注" );
//					MobclickAgent.onEvent( DynamicAcfunAdapter.this.al, UmengCustomAnalyticsIDs.bl );
//					if ( DynamicAcfunAdapter.this.a_( Integer.parseInt( regionBodyContent2.contentId ) ) ) {
//						ApiHelper.a()
//								.b( DynamicAcfunAdapter.this.av, Integer.parseInt( regionBodyContent2.contentId ), new FollowBangumiCallback( viewHolderRecommendUploader.follow,
// viewHolderRecommendUploader.followImg, Integer
//
//										.parseInt( regionBodyContent2.contentId ), false ) );
//					} else {
//						ApiHelper.a()
//								.c( DynamicAcfunAdapter.this.av, Integer.parseInt( regionBodyContent2.contentId ), 0, new FollowBangumiCallback( viewHolderRecommendUploader.follow,
// viewHolderRecommendUploader.followImg, Integer
//										.parseInt( regionBodyContent2.contentId ), true ) );
//					}
				}
			} );
			viewHolderRecommendUploader.title.setText( regionBodyContent3.title );
			viewHolderRecommendUploader.title.setOnClickListener( new View.OnClickListener() {
				public void onClick( View view ) {
					ToastUtil.showWarning( "title" );
//					DynamicAcfunAdapter.this.a( regionBodyContent3.title, DynamicAcfunAdapter.this.a( regionBodyContent3.contentId ), regionBodyContent3.user != null ? regionBodyContent3.user.id :
// 0, false );
//					DynamicAcfunAdapter.this.a( regionBodyContent3 );
				}
			} );
			viewHolderRecommendUploader.viewLayout.setOnClickListener( new View.OnClickListener() {
				public void onClick( View view ) {
					ToastUtil.showWarning( "viewLayout" );
//					DynamicAcfunAdapter.this.a( regionBodyContent3.title, DynamicAcfunAdapter.this.a( regionBodyContent3.contentId ), regionBodyContent3.user != null ? regionBodyContent3.user.id :
// 0, false );
//					DynamicAcfunAdapter.this.a( regionBodyContent3 );
				}
			} );
			TextView textView;
			if ( regionBodyContent3.visit != null ) {
				viewHolderRecommendUploader.views.setText( StringUtil.formatChineseNum( regionBodyContent3.visit.views ) );
				textView = viewHolderRecommendUploader.danmaku;
				StringBuilder stringBuilder5 = new StringBuilder();
				stringBuilder5.append( regionBodyContent3.visit.danmakuSize );
				stringBuilder5.append( "" );
				textView.setText( stringBuilder5.toString() );
				textView = viewHolderRecommendUploader.banana;
				stringBuilder5 = new StringBuilder();
				stringBuilder5.append( regionBodyContent3.visit.banana );
				stringBuilder5.append( "" );
				textView.setText( stringBuilder5.toString() );
			}
			viewHolderRecommendUploader.contentArea.setOnClickListener( new View.OnClickListener() {
				public void onClick( View view ) {
					ToastUtil.showWarning( "contentArea" );

//					Owner owner = new Owner();
//					owner.id = Integer.parseInt( regionBodyContent2.contentId );
//					owner.avatar = ( String ) regionBodyContent2.images.get( 0 );
//					owner.name = regionBodyContent2.title;
//					Video video = new Video();
//					video.setTitle( regionBodyContent3.title );
//					video.setVid( regionBodyContent3.videoId );
//					DynamicAcfunAdapter.this.a( regionBodyContent3.channel.id, video.getVid(), video, owner, video.getTitle(), regionBodyContent3.intro );
				}
			} );
			if ( regionBodyContent3.images != null && regionBodyContent3.images.size() > 0 ) {
				ImageLoaderUtil.loadNetImage( regionBodyContent3.images.get( 0 ), viewHolderRecommendUploader.img );
			}
		}
	}

	public void setRecommendUpInfo( List< RegionBodyContent > regionBodyContentList ) {
		this.data = regionBodyContentList;
	}

	public void addRecommendUpInfo( List< RegionBodyContent > regionBodyContentList ) {
		this.data.addAll( regionBodyContentList );
	}

	static class DynamicAcfunViewHolder extends RecyclerView.ViewHolder {
		SimpleDraweeView avatar;
		public TextView banana;
		TextView belong;
		View contentArea;
		public TextView danmaku;
		TextView follow;
		ImageView followImg;
		RelativeLayout followLayout;
		SimpleDraweeView img;
		TextView nickName;
		TextView time;
		TextView title;
		View uploaderRL;
		public RelativeLayout viewLayout;
		public TextView views;

		DynamicAcfunViewHolder( View view ) {
			super( view );
			avatar = ( SimpleDraweeView ) view.findViewById( R.id.avatar );
			nickName = ( TextView ) view.findViewById( R.id.nick_name );
			time = ( TextView ) view.findViewById( R.id.time );
			belong = ( TextView ) view.findViewById( R.id.belong );
			follow = ( TextView ) view.findViewById( R.id.follow );
			followImg = ( ImageView ) view.findViewById( R.id.follow_img );
			followLayout = ( RelativeLayout ) view.findViewById( R.id.follow_layout );
			uploaderRL = ( RelativeLayout ) view.findViewById( R.id.uploader_rl );
			title = ( TextView ) view.findViewById( R.id.title );
			img = ( SimpleDraweeView ) view.findViewById( R.id.img );
//			imgFrame = ( FrameLayout ) view.findViewById( R.id.img_frame );
			contentArea = ( RelativeLayout ) view.findViewById( R.id.content_area );
			views = ( TextView ) view.findViewById( R.id.rss_video_views );
			danmaku = ( TextView ) view.findViewById( R.id.rss_video_danmaku );
			banana = ( TextView ) view.findViewById( R.id.rss_video_banana );
			viewLayout = ( RelativeLayout ) view.findViewById( R.id.view_count_layout );
		}
	}
}
