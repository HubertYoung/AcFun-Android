package com.hubertyoung.aggregation.dialog;

import android.app.Activity;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hubertyoung.baseplatform.ShareSDK;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.share.image.resource.UrlResource;
import com.hubertyoung.baseplatform.share.media.MoWeb;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.component_aggregation.R;
import com.hubertyoung.dialog.TDialog;
import com.hubertyoung.dialog.base.BindViewHolder;
import com.hubertyoung.dialog.listener.OnBindViewListener;
import com.hubertyoung.dialog.listener.OnViewClickListener;

import java.io.File;
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
				.setDimAmount( 0.5f )//
				.setDialogAnimationRes( R.style.animate_dialog_scale )//
				.addOnClickListener( R.id.cancel );

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
		ShareBottomAdapter adapter = new ShareBottomAdapter( mActivity, data );
		rvList.setAdapter( adapter );
		adapter.setOnItemClickListener( new ShareBottomAdapter.OnItemClickListener() {
			@Override
			public void onItemClick( View v, String platform ) {
				initSharePlatform(platform);
			}
		} );
	}

	private void initSharePlatform( String platformName ) {
		UrlResource urlResource = new UrlResource( "https://goss3.vcg.com/creative/vcg/400/version23/VCG41598227336.jpg" );
		String input = new File( Environment.getExternalStorageDirectory(), "DCIM" + File.separator + "5211c896758e4d3d8200b9738d509687.jpg" ).getAbsolutePath();

		ShareSDK.make( mActivity, new MoWeb( "http://www.baidu.com" ) )//
				.withTitle( "123123" )//
				.withDescription( "asdfasdf" )//
				.withThumb( urlResource )//
				.share( platformName, new OnCallback< String >() {
					@Override
					public void onStart( Activity activity ) {

					}

					@Override
					public void onCompleted( Activity activity ) {

					}

					@Override
					public void onSuccess( Activity activity, String result ) {
						ToastUtil.showSuccess( "分享成功" );
					}

					@Override
					public void onError( Activity activity, int code, String message ) {
						ToastUtil.showSuccess( message );
					}
				} );
	}

	public ShareBottomDialog show() {
		mTDialog.show();
		return this;
	}
}
