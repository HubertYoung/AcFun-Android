package com.hubertyoung.aggregation.dialog;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hubertyoung.component_aggregation.R;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/13 16:15
 * @since:V$VERSION
 * @desc:com.hubertyoung.aggregation.dialog
 */
class ShareBottomAdapter extends RecyclerView.Adapter {
	private List< BottomShareEntity > data;
	private Context mContext;

	public ShareBottomAdapter( Context context, List< BottomShareEntity > data ) {
		this.mContext = context;
		this.data = data;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
		View view = LayoutInflater.from( mContext ).inflate( R.layout.aggregation_item_dialog_bottomsheet, parent, false );
		return new ShareBottomViewHolder(view);
	}

	@Override
	public void onBindViewHolder( @NonNull RecyclerView.ViewHolder holder, int position ) {
		ShareBottomViewHolder viewHolder = ( ShareBottomViewHolder ) holder;
		BottomShareEntity bean = data.get( position );
		viewHolder.mShareIcon.setImageDrawable( bean.icon );
		viewHolder.mShareTitle.setText( bean.title );
		if(mOnItemClickListener != null) {
			viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
				@Override
				public void onClick( View v ) {
					mOnItemClickListener.onItemClick( v,bean.platform );
				}
			} );
		}
	}

	@Override
	public int getItemCount() {
		return data == null ? 0 : data.size();
	}
	public interface OnItemClickListener {
		void onItemClick( View v, String platform );
	}
	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener( OnItemClickListener onItemClickListener ) {
		mOnItemClickListener = onItemClickListener;
	}

	static class ShareBottomViewHolder extends RecyclerView.ViewHolder {
		ImageView mShareIcon;
		TextView mShareTitle;

		ShareBottomViewHolder( View view) {
			super(view);
			this.mShareIcon = ( ImageView ) view.findViewById( R.id.share_icon );
			this.mShareTitle = ( TextView ) view.findViewById( R.id.share_title );
		}
	}
}
