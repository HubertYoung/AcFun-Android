package com.hubertyoung.component_acfunarticle.article.section;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.component_acfunarticle.R;
import com.hubertyoung.component_acfunarticle.entity.ServerChannel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/18 14:59
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunarticle.article.section
 */
public class ArticleRealmSection extends Section {
	private List< ServerChannel > mRealm;
	private Activity mActivity;

	public ArticleRealmSection( Activity activity, List< ServerChannel > realm ) {
		super( new SectionParameters.Builder( R.layout.item_article_realm )//
				.build() );
		this.mActivity = activity;
		this.mRealm = realm;
	}

	@Override
	public int getContentItemsTotal() {
		return mRealm == null ? 0 : mRealm.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new ViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		ServerChannel channel = mRealm.get( position );
		ViewHolder viewHolder = ( ViewHolder ) holder;
		if ( !TextUtils.isEmpty( channel.name ) ) {
			viewHolder.titleText.setText( channel.name );
		}
		viewHolder.titleText.setEnabled( channel.id >= 0 );
		viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				ToastUtil.showSuccess( channel.name );
			}
		} );
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		TextView titleText;
		RelativeLayout rootView;

		ViewHolder( View view ) {
			super( view );
			titleText = ( TextView ) view.findViewById( R.id.article_realm_title );
			rootView = ( RelativeLayout ) view.findViewById( R.id.rootView );
		}
	}
}
