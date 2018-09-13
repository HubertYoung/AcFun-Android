package com.hubertyoung.aggregation.dialog;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hubertyoung.component_aggregation.R;
import com.hubertyoung.dialog.TDialog;
import com.hubertyoung.dialog.base.BindViewHolder;
import com.hubertyoung.dialog.listener.OnBindViewListener;
import com.hubertyoung.dialog.listener.OnViewClickListener;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/12 19:54
 * @since:V$VERSION
 * @desc:com.hubertyoung.aggregation.dialog
 */
public class ShareBottomDialog {
	private Activity mActivity;
	private TDialog mTDialog;
	private TDialog.Builder mBuilder;

	public ShareBottomDialog( FragmentActivity activity ) {
		this.mActivity = activity;
		mBuilder = new TDialog.Builder( activity.getSupportFragmentManager() )//
				.setLayoutRes( R.layout.aggregation_share_bottom_dialog )//
				.setScreenWidthAspect( mActivity, 1.0f )//
				.setGravity( Gravity.BOTTOM )//
				.setDimAmount( 0.5f ).setDialogAnimationRes( R.style.animate_dialog_scale ).addOnClickListener( R.id.cancel );

	}

	public ShareBottomDialog create( List< BottomShareEntity > list, List< BottomShareEntity > list2 ) {
		mTDialog = mBuilder.setOnBindViewListener( new OnBindViewListener() {
			@Override
			public void bindView( BindViewHolder viewHolder ) {
				final LinearLayout recycler = viewHolder.getView( R.id.recycler );
				viewHolder.getView( R.id.title ).setVisibility( View.GONE );
				if ( recycler != null ) {
					recycler.removeAllViews();
					View view = LayoutInflater.from( mActivity ).inflate( R.layout.aggregation_share_selector_dialog, null );
					recycler.addView( view );
					RecyclerView rvList = viewHolder.getView( R.id.rv_list );
					RecyclerView mRvThirdPartyList = viewHolder.getView( R.id.rv_third_party_list );
					initRecyclerView( rvList, list );
					initRecyclerView( mRvThirdPartyList, list2 );
				}
			}
		} ).setOnViewClickListener( new OnViewClickListener() {
			@Override
			public void onViewClick( BindViewHolder viewHolder, View view, TDialog tDialog ) {
				switch ( view.getId() ) {
					case R.id.cancel:
						tDialog.dismiss();
						break;
				}
			}
		} ).create();
		return this;
	}

	private void initRecyclerView( RecyclerView rvList, List< BottomShareEntity > data ) {
		rvList.setLayoutManager( new LinearLayoutManager( mActivity, RecyclerView.HORIZONTAL, false ) );
		rvList.setAdapter( new ShareBottomAdapter(mActivity, data ) );
	}

	public ShareBottomDialog show() {
		mTDialog.show();
		return this;
	}
}
