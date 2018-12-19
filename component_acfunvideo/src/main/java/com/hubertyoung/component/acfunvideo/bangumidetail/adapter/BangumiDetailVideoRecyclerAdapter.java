package com.hubertyoung.component.acfunvideo.bangumidetail.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hubertyoung.common.entity.NetVideo;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.entity.BangumiDetailBean;
import com.hubertyoung.component_acfunvideo.R;

import java.util.ArrayList;
import java.util.List;

public class BangumiDetailVideoRecyclerAdapter extends RecyclerView.Adapter< BangumiDetailVideoRecyclerAdapter.ViewHolder > {
	private LayoutInflater mLayoutInflater;
	private Activity mActivity;
	private List< NetVideo > mNetVideoList = new ArrayList();
	private BangumiDetailBean mBangumiDetailBean;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		TextView mContentText;

		ViewHolder( View view ) {
			super( view );
			mContentText = view.findViewById( R.id.tv_content );
		}
	}

	@Override
	public void onBindViewHolder( @NonNull ViewHolder holder, int position, @NonNull List< Object > payloads ) {
		super.onBindViewHolder( holder, position, payloads );
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder( @NonNull ViewGroup viewGroup, int i ) {
		return new ViewHolder( this.mLayoutInflater.inflate( R.layout.item_bangumi_detail_fragment_recyclerview, viewGroup, false ) );
	}

	@Override
	public void onBindViewHolder( @NonNull ViewHolder viewHolder, int position ) {
		bindView( viewHolder, position );
	}

	public BangumiDetailVideoRecyclerAdapter( Activity activity ) {
		if ( activity != null ) {
			this.mActivity = activity;
			this.mLayoutInflater = LayoutInflater.from( this.mActivity );
		}
	}

	public void setData( List< NetVideo > list, BangumiDetailBean bangumiDetailBean ) {
		if ( !( list == null || list.size() == 0 ) && bangumiDetailBean != null ) {
			if ( list.size() > 6 ) {
				this.mNetVideoList = list.subList( 0, 6 );
			} else {
				this.mNetVideoList = list;
			}
			this.mBangumiDetailBean = bangumiDetailBean;
		}
	}

	public void bindView( ViewHolder viewHolder, int position ) {
		final NetVideo netVideo = this.mNetVideoList.get( position );
		if ( netVideo != null ) {
			viewHolder.mContentText.setText( netVideo.mTitle );
			viewHolder.mContentText.setOnClickListener( new OnClickListener() {
				public void onClick( View view ) {
					ToastUtil.showWarning( netVideo.toString() );
//                    if (BangumiDetailVideoRecyclerAdapter.this.b instanceof BangumiDetailActivityNew ) {
//                        BangumiDetailHelper.a(BangumiDetailVideoRecyclerAdapter.this.b, BangumiDetailVideoRecyclerAdapter.this.d.id, netVideo, BangumiDetailVideoRecyclerAdapter.this.d);
//                    }
				}
			} );
		}
	}

	public int getItemCount() {
		return mNetVideoList == null ? 0 : mNetVideoList.size();
	}

}
