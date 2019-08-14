package com.hubertyoung.aggregation.dialog;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.hubertyoung.component_aggregation.R;
import com.lxj.xpopup.core.BasePopupView;

import java.util.List;
import java.util.Locale;


/**
 */
public class ShareBottomPopup extends BasePopupView {

    private final Activity mContext;
    private RecyclerView mRvList;
    private RecyclerView mMRvThirdPartyList;
    private List< BottomShareEntity > list;
    private List< BottomShareEntity > list2;

    public ShareBottomPopup( @NonNull Activity context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected int getPopupLayoutId() {
        return R.layout.aggregation_share_bottom_dialog;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        LinearLayout recycler = findViewById( R.id.recycler );
        findViewById( R.id.title ).setVisibility( View.GONE );
        findViewById( R.id.cancel ).setOnClickListener( new OnClickListener() {
            @Override
            public void onClick( View v ) {
                dismiss();
            }
        } );
        if ( recycler != null ) {
            recycler.removeAllViews();
            View view = LayoutInflater.from( getContext() ).inflate( R.layout.aggregation_share_selector_dialog, null );
            recycler.addView( view );
            mRvList = findViewById( R.id.rv_list );
            mMRvThirdPartyList = findViewById( R.id.rv_third_party_list );
        }

    }

    @Override
    public void doAfterShow() {
        initRecyclerView( mRvList, list );
        initRecyclerView( mMRvThirdPartyList, list2 );
    }

    public void create( List< BottomShareEntity > list, List< BottomShareEntity > list2 ) {
        this.list = list;
        this.list2 = list2;
    }

    private void initRecyclerView( RecyclerView rvList, List< BottomShareEntity > data ) {
        rvList.setLayoutManager( new LinearLayoutManager( mContext, RecyclerView.HORIZONTAL, false ) );
        ShareBottomAdapter adapter = new ShareBottomAdapter( mContext, data );
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
                            mContext.startActivity( chooser );
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
        param.setThumb( new ShareImage( IMAGE_URL ) );

        if ( SocializeMedia.Weibo.equals( platformName ) ) {
            param.setContent( String.format( Locale.CHINA, "%s #%s# ", CONTENT, AppUtils.getAppName() ) );
        } else if ( SocializeMedia.Copy.equals( platformName ) || SocializeMedia.More.equals( platformName ) ) {
            param.setContent( CONTENT + " " + TARGET_URL );
        }
        PlatformShareConfiguration configuration = new PlatformShareConfiguration.Builder( mContext )//
                .imageDownloader( new ShareFrescoImageDownloader() )//
                .defaultShareImage( R.mipmap.ic_launcher )//
                .build();
        ShareSDK.make( mContext, param, configuration )//
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
}
