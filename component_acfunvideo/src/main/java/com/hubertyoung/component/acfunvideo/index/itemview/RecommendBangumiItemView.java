package com.hubertyoung.component.acfunvideo.index.itemview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hubertyoung.component_acfunvideo.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.utils.display.DisplayUtil;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 16:53
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.index.itemview
 */
public class RecommendBangumiItemView {
	private LayoutInflater mLayoutInflater;
	private int spaceWidth;
	private int spaceHeight = ( int ) (( spaceWidth * 4 )  / 3.0f);

	public RecommendBangumiItemView( Context context ) {
		mLayoutInflater = LayoutInflater.from( context );
		spaceWidth = ( DisplayUtil.getScreenWidth( context ) - ( ( DisplayUtil.dip2px( 15.0f ) * 2 ) + ( DisplayUtil.dip2px( 9.0f ) * 2 ) ) ) / 3;
	}

	public View init() {
		View inflate = mLayoutInflater.inflate( R.layout.recommend_bangumi_item_view, null );
		ViewHolder viewHolder = new ViewHolder( inflate );
		inflate.setTag( viewHolder );
		ViewGroup.LayoutParams layoutParams = viewHolder.mCover.getLayoutParams();
		layoutParams.width = spaceWidth;
		layoutParams.height = spaceHeight;
		viewHolder.mCover.setLayoutParams( layoutParams );
		return inflate;
	}

	public int getSpaceWidth() {
		return spaceWidth;
	}

	public int getSpaceHeight() {
		return spaceHeight;
	}

	public static class ViewHolder {
		public SimpleDraweeView mCover;
		public TextView mStows;
		public TextView mTitle;
		public TextView mText;

		ViewHolder( View view) {
			mCover = ( SimpleDraweeView ) view.findViewById( R.id.cover );
			mStows = ( TextView ) view.findViewById( R.id.stows );
			mTitle = ( TextView ) view.findViewById( R.id.title );
			mText = ( TextView ) view.findViewById( R.id.text );
		}
	}
}
