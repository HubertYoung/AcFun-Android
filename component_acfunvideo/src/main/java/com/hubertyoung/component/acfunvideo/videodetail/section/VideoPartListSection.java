package com.hubertyoung.component.acfunvideo.videodetail.section;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hubertyoung.common.entity.FullContent;
import com.hubertyoung.common.entity.Video;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.component_acfunvideo.R;

import java.util.List;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/2 18:01
 * @since:
 * @see VideoPartListSection
 */
public class VideoPartListSection extends StatelessSection {
	private Activity mActivity;
	private List< Video > mVideos;
	private boolean isShowBottomButton;
	private int c;
	private boolean isHapame;

	public VideoPartListSection( Activity activity ) {
		super( SectionParameters.builder()//
				.headerResourceId( R.layout.item_video_detail_part_head )//
				.footerResourceId( R.layout.item_video_detail_part_foot )//
				.itemResourceId( R.layout.item_video_detail_part )//
				.build() );
		this.mActivity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return mVideos == null ? 0 : mVideos.size();
	}

	@Override
	public RecyclerView.ViewHolder getFooterViewHolder( View view ) {
		return new VideoPartFootViewHolder( view );
	}

	@Override
	public RecyclerView.ViewHolder getHeaderViewHolder( View view ) {
		return new VideoPartHeadViewHolder( view );
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new VideoPartViewHolder( view );
	}

	@Override
	public void onBindHeaderViewHolder( RecyclerView.ViewHolder holder ) {
		super.onBindHeaderViewHolder( holder );
	}

	@Override
	public void onBindFooterViewHolder( RecyclerView.ViewHolder holder ) {
		super.onBindFooterViewHolder( holder );
		VideoPartFootViewHolder viewHolder = ( VideoPartFootViewHolder ) holder;
		if ( isShowBottomButton ) {
			viewHolder.mVideoDetailPartsTotalButtonLayout.setVisibility( View.VISIBLE );
			viewHolder.mVideoDetailPartsTotalButton.setOnClickListener( new View.OnClickListener() {
				public void onClick( View view ) {
					ToastUtil.showWarning( "查看视频" );
//					VideoDetailContentViewController.this.mIVideoDetailView.j();
				}
			} );
		} else {
			viewHolder.mVideoDetailPartsTotalButtonLayout.setVisibility( View.GONE );
		}
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		VideoPartViewHolder viewHolder = ( VideoPartViewHolder ) holder;
		Video video = mVideos.get( position );
		viewHolder.partTitle.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
//				EventHelper.a().a(new OnPlayVideoClickEvent(a, i));
			}
		} );
		if ( video.getVid() == this.c || ( this.c == 0 && position == 0 ) ) {
			SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder( "  " );
			spannableStringBuilder.append( video.getTitle() );
			Drawable drawable = ContextCompat.getDrawable( mActivity, R.drawable.icon_video_parts_play );
			drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );
			spannableStringBuilder.setSpan( new ImageSpan( drawable, DynamicDrawableSpan.ALIGN_BASELINE ), 0, 1, 17 );
			viewHolder.partTitle.setText( spannableStringBuilder );
		} else {
			viewHolder.partTitle.setText( video.getTitle() );
		}
		// TODO: 2019/1/2 播放历史
//		boolean a2 = PlayHistoryHelper.a().a( video.getVid() );
		boolean isHaveHistory = false;
		if ( video.getVid() == this.c || ( this.c == 0 && position == 0 ) ) {
			viewHolder.partTitle.setTextColor( ContextCompat.getColor( mActivity, R.color.white ) );
			if ( isHapame ) {
				viewHolder.partTitle.setBackgroundResource( R.drawable.shape_bg_videopart_p_hapame );
			} else {
				viewHolder.partTitle.setBackgroundResource( R.drawable.shape_bg_videopart_p );
			}
		} else if ( isHaveHistory ) {
			viewHolder.partTitle.setTextColor( ContextCompat.getColor( mActivity, R.color.selector_text_gray_white ) );
			viewHolder.partTitle.setBackgroundResource( R.drawable.shape_bg_videopart_n );
		} else {
			viewHolder.partTitle.setTextColor( ContextCompat.getColor( mActivity, R.color.text_deep_gray_color ) );
			viewHolder.partTitle.setBackgroundResource( R.drawable.shape_bg_videopart_n );
		}
	}

	public void setData( FullContent fullContent ) {
		if ( fullContent.getVideos().size() > 6 ) {
			isShowBottomButton = true;
			mVideos = fullContent.getVideos().subList( 0, 6 );
		} else {
			mVideos = fullContent.getVideos();
			isShowBottomButton = false;
		}
	}

	static class VideoPartViewHolder extends RecyclerView.ViewHolder {
		TextView partTitle;

		VideoPartViewHolder( View view ) {
			super( view );
			this.partTitle = ( TextView ) view.findViewById( R.id.video_detail_part_title );
		}
	}

	static class VideoPartHeadViewHolder extends RecyclerView.ViewHolder {
		TextView mWidgetVideoDetailCommentPartCount;

		VideoPartHeadViewHolder( View view ) {
			super( view );
			this.mWidgetVideoDetailCommentPartCount = ( TextView ) view.findViewById( R.id.video_part_name_size );
		}
	}

	static class VideoPartFootViewHolder extends RecyclerView.ViewHolder {
		TextView mVideoDetailPartsTotalButton;
		RelativeLayout mVideoDetailPartsTotalButtonLayout;

		VideoPartFootViewHolder( View view ) {
			super( view );
			this.mVideoDetailPartsTotalButton = ( TextView ) view.findViewById( R.id.video_detail_parts_total_button );
			this.mVideoDetailPartsTotalButtonLayout = ( RelativeLayout ) view.findViewById( R.id.video_detail_parts_total_button_layout );
		}
	}
}
