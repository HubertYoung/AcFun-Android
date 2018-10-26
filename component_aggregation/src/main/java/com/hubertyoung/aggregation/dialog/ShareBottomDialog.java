package com.hubertyoung.aggregation.dialog;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hubertyoung.baseplatform.PlatformShareConfiguration;
import com.hubertyoung.baseplatform.ShareSDK;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.share.SocializeMedia;
import com.hubertyoung.baseplatform.share.shareparam.BaseShareParam;
import com.hubertyoung.baseplatform.share.shareparam.ShareImage;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamWebPage;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.utils.os.AppUtils;
import com.hubertyoung.common.utils.os.ClipboardUtils;

import java.util.List;
import java.util.Locale;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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
				int i = view.getId();
				if ( i == R.id.cancel ) {
					tDialog.dismiss();
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
				switch ( platform ) {
					case SocializeMedia.Copy:
						ClipboardUtils.copyText( "url" );
						ToastUtil.showSuccess( "复制成功" );
						break;
					case SocializeMedia.More:
						Intent shareIntent = createIntent( "title", "content" );
						Intent chooser = Intent.createChooser( shareIntent, "分享到：" );
						try {
							mActivity.startActivity( chooser );
						} catch ( ActivityNotFoundException ignored ) {
//							if (shareListener != null) {
//								shareListener.onError(getShareMedia(), BiliShareStatusCode.ST_CODE_ERROR, new ShareException("activity not found"));
//							}
							Log.e( "TAG", "error" );
						}
						break;
					default:
						initSharePlatform( platform );
						break;
				}
			}
		} );
	}

	private Intent createIntent( String subject, String text ) {
		Intent intent = new Intent( Intent.ACTION_SEND );
		intent.putExtra( Intent.EXTRA_SUBJECT, subject );
		intent.putExtra( Intent.EXTRA_TEXT, text );
		intent.setType( "text/plain" );
		return intent;
	}

	private static final String TITLE = "哔哩哔哩";
	private static final String CONTENT = "b站播放破亿以及百万以上追番的5部国漫 #哔哩哔哩动画# ";
	private static final String TARGET_URL = "http://www.bilibili.com/video/av21227479";
	private static final String IMAGE_URL = "http://i2.hdslb.com/320_200/video/85/85ae2b17b223a0cd649a49c38c32dd10.jpg";

	private void initSharePlatform( String platformName ) {
		ShareParamWebPage param = new ShareParamWebPage( TITLE, CONTENT, TARGET_URL );
		param.setThumb(new ShareImage(IMAGE_URL));

		if ( SocializeMedia.Weibo.equals( platformName ) ) {
			param.setContent( String.format( Locale.CHINA, "%s #%s# ", CONTENT, AppUtils.getAppName() ) );
		} else if ( SocializeMedia.Copy.equals( platformName ) || SocializeMedia.More.equals( platformName ) ) {
			param.setContent( CONTENT + " " + TARGET_URL );
		}
		PlatformShareConfiguration configuration = new PlatformShareConfiguration.Builder( mActivity )//
				.imageDownloader( new ShareFrescoImageDownloader() )//
				.defaultShareImage( R.mipmap.ic_launcher )//
				.build();
		ShareSDK.make( mActivity, param, configuration )//
//				.withTitle( "123123" )//
//				.withDescription( "asdfasdf" )//
//				.withThumb( urlResource )//
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

					@Override
					public void onProgress( Activity activity, BaseShareParam params, String msg ) {

					}
				} );
	}

	public ShareBottomDialog show() {
		mTDialog.show();
		return this;
	}
}
