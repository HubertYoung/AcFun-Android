package com.hubertyoung.component.acfunvideo.videodetail.section;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.data.StringUtil;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.component_acfunvideo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/2 19:01
 * @since:
 * @see RecommondVideoList
 */
public class RecommondVideoList extends StatelessSection {
	private Activity mActivity;
	private List< RegionBodyContent > mRegionBodyContents = new ArrayList();

	public RecommondVideoList( Activity activity ) {
		super( SectionParameters.builder()//
				.itemResourceId( R.layout.item_video_detail_recommend )//
				.build() );
		this.mActivity = activity;
	}

	@Override
	public int getContentItemsTotal() {
		return mRegionBodyContents == null ? 0 : mRegionBodyContents.size();
	}

	@Override
	public int getSpanSizeLookup( int position ) {
		return 2;
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new RecommondVideoViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		RecommondVideoViewHolder viewHolder = ( RecommondVideoViewHolder ) holder;
		RegionBodyContent regionBodyContent = mRegionBodyContents.get( position );
		if ( regionBodyContent.images != null && regionBodyContent.images.size() > 0 ) {
			ImageLoaderUtil.loadNetImage( regionBodyContent.images.get( 0 ), viewHolder.mVideoImage );
		}
		viewHolder.mVideoPlays.setText( StringUtil.formatChineseNum( regionBodyContent.visit.views ) );
		TextView textView = viewHolder.mVideoDanmuCount;
		textView.setText( regionBodyContent.visit.danmakuSize + "" );
		viewHolder.mTitle.setText( regionBodyContent.title );
		viewHolder.mUploadTime.setText( StringUtil.formatNumTime(regionBodyContent.time ) );
//		if ( ChannelHelper.b( regionBodyContent.channel.id ) == null ) {
//			textView = viewHolder.mUploadBelong;
//			stringBuilder = new StringBuilder();
//			stringBuilder.append( "投稿于 " );
//			stringBuilder.append( regionBodyContent.channel.name );
//			textView.setText( stringBuilder.toString() );
//		} else {
//			textView = viewHolder.mUploadBelong;
//			stringBuilder = new StringBuilder();
//			stringBuilder.append(  );
//			stringBuilder.append( ChannelHelper.b( regionBodyContent.channel.id ) );
			textView.setText( "投稿于 ");
//		}
		viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
				String replace = regionBodyContent.contentId.replace( "ac", "" );
				boolean z = true;
				ToastUtil.showWarning( replace );
//				if ( regionBodyContent != null ) {
//					SensorsAnalyticsUtil.g( regionBodyContent.title );
//					Bundle bundle = new Bundle();
//					bundle.putString( "name", regionBodyContent.title );
//					bundle.putInt( KanasConstants.al, intValue + 1 );
//					bundle.putString( "ac_id", replace );
//					KanasCommonUtil.c( KanasConstants.bQ, bundle );
//				}
//				EventHelper a = EventHelper.a();
//				if ( regionBodyContent.channel.id != 63 ) {
//					z = false;
//				}
//				a.a( new OnStartVideoDetailActivityEvent( replace, z, regionBodyContent.reqId, regionBodyContent.groupId ) );
//				MobclickAgent.onEvent( VideoDetailRecommondVideoListAdapter.this.b, UmengCustomAnalyticsIDs.R );
//				AnalyticsUtil.b( VideoDetailRecommondVideoListAdapter.this.b, VideoDetailRecommondVideoListAdapter.this.d, Integer.valueOf( replace ).intValue() );
			}
		} );
	}

	public void setData( List< RegionBodyContent > list ) {
		this.mRegionBodyContents.addAll( list );
	}

	static class RecommondVideoViewHolder extends RecyclerView.ViewHolder {
		SimpleDraweeView mVideoImage;
		TextView mVideoPlays;
		TextView mVideoDanmuCount;
		TextView mTitle;
		TextView mUploadTime;
		TextView mUploadBelong;

		RecommondVideoViewHolder( View view ) {
			super( view );
			this.mVideoImage = ( SimpleDraweeView ) view.findViewById( R.id.recommend_video_item_view_img );
			this.mVideoPlays = ( TextView ) view.findViewById( R.id.recommend_video_item_view_plays );
			this.mVideoDanmuCount = ( TextView ) view.findViewById( R.id.recommend_video_item_view_danmaku );
			this.mTitle = ( TextView ) view.findViewById( R.id.recommend_video_item_view_title );
			this.mUploadTime = ( TextView ) view.findViewById( R.id.recommed_video_item_view_uploader_time );
			this.mUploadBelong = ( TextView ) view.findViewById( R.id.recommend_video_item_view_uploader_belong );
		}
	}
}
