package aggregation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hubertyoung.aggregation.dialog.BottomShareEntity;
import com.hubertyoung.aggregation.dialog.ShareBottomDialog;
import com.hubertyoung.aggregation.dialog.ShareFrescoImageDownloader;
import com.hubertyoung.baseplatform.AuthorizeSDK;
import com.hubertyoung.baseplatform.PlatformShareConfiguration;
import com.hubertyoung.baseplatform.ShareSDK;
import com.hubertyoung.baseplatform.authorize.AuthorizeVia;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.share.SocializeMedia;
import com.hubertyoung.baseplatform.share.shareparam.BaseShareParam;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamText;
import com.hubertyoung.baseplatform.tools.Hashon;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.utils.os.AppUtils;
import com.hubertyoung.component_aggregation.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.ContextCompat;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/11 09:54
 * @since:V1.0.0
 * @desc:pay
 */
public class MainActivity extends AppCompatActivity {
	private Button mBtnQQ;
	private Button mBtnWechat;
	private Button mBtnWeibo;
	private Button mBtnQQShare;
	private Button mBtnWechatShare;
	private Button mBtnWeiboShare;
	private Button mBtnQQZoneShare;
	private Button mBtnShowShareDialog;
	private ShareBottomDialog mBottomDialog;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.aggregation_activity_main );
		mBtnQQ = ( Button ) findViewById( R.id.btn_qq );
		mBtnWechat = ( Button ) findViewById( R.id.btn_wechat );
		mBtnWeibo = ( Button ) findViewById( R.id.btn_weibo );
		mBtnQQShare = ( Button ) findViewById( R.id.btn_qq_share );
		mBtnQQZoneShare = ( Button ) findViewById( R.id.btn_qqzone_share );
		mBtnWechatShare = ( Button ) findViewById( R.id.btn_wechat_share );
		mBtnWeiboShare = ( Button ) findViewById( R.id.btn_weibo_share );
		mBtnShowShareDialog = ( Button ) findViewById( R.id.btn_show_share_dialog );

		mBottomDialog = new ShareBottomDialog( MainActivity.this );

		initAction();
	}

	private void initAction() {
		mBtnQQ.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				login( AuthorizeVia.QQ );
			}
		} );
		mBtnWechat.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				login( AuthorizeVia.Wechat );
			}
		} );
		mBtnWeibo.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				login( AuthorizeVia.Weibo );
			}
		} );
		mBtnQQShare.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				share( SocializeMedia.QQ );
			}
		} );
		mBtnQQZoneShare.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				share( SocializeMedia.QZone );
			}
		} );
		mBtnWechatShare.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				share( SocializeMedia.WXSession );
			}
		} );
		mBtnWeiboShare.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				share( SocializeMedia.Weibo );
			}
		} );
		mBtnShowShareDialog.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				List< BottomShareEntity > list = new ArrayList<>();
				list.add( new BottomShareEntity("", "动态", ContextCompat.getDrawable( MainActivity.this, R.drawable.bili_socialize_dynamic ) ) );
				list.add( new BottomShareEntity( "","消息", ContextCompat.getDrawable( MainActivity.this, R.drawable.bili_socialize_im ) ) );

				List< BottomShareEntity > list2 = new ArrayList<>();
				list2.add( new BottomShareEntity( SocializeMedia.QQ,"QQ", ContextCompat.getDrawable( MainActivity.this, R.drawable.bili_socialize_qq_chat ) ) );
				list2.add( new BottomShareEntity( SocializeMedia.QZone,"QQ空间", ContextCompat.getDrawable( MainActivity.this, R.drawable.bili_socialize_qq_zone ) ) );
				list2.add( new BottomShareEntity( SocializeMedia.WXTimeline,"微信", ContextCompat.getDrawable( MainActivity.this, R.drawable.bili_socialize_wx_chat ) ) );
				list2.add( new BottomShareEntity( SocializeMedia.WXTimeline,"朋友圈", ContextCompat.getDrawable( MainActivity.this, R.drawable.bili_socialize_wx_moment ) ) );
				list2.add( new BottomShareEntity( SocializeMedia.Weibo,"微博", ContextCompat.getDrawable( MainActivity.this, R.drawable.bili_socialize_sina ) ) );
				list2.add( new BottomShareEntity( SocializeMedia.Copy,"复制链接", ContextCompat.getDrawable( MainActivity.this, R.drawable.bili_socialize_copy ) ) );
				list2.add( new BottomShareEntity( SocializeMedia.More,"更多", ContextCompat.getDrawable( MainActivity.this, R.drawable.bili_socialize_generic ) ) );
				mBottomDialog.create( list, list2 );
				mBottomDialog.show();
			}
		} );
	}
	private static final String TITLE = "哔哩哔哩2016拜年祭";
	private static final String CONTENT = "【哔哩哔哩2016拜年祭】 UP主: 哔哩哔哩弹幕网 #哔哩哔哩动画# ";
	private static final String TARGET_URL = "http://www.bilibili.com/video/av3521416";
	private static final String IMAGE_URL = "http://i2.hdslb.com/320_200/video/85/85ae2b17b223a0cd649a49c38c32dd10.jpg";
	private void share( String platformName ) {
		ShareParamText param = new ShareParamText( TITLE, CONTENT, TARGET_URL );

		if ( SocializeMedia.Weibo.equals( platformName ) ) {
			param.setContent( String.format( Locale.CHINA, "%s #%s# ", CONTENT, AppUtils.getAppName() ) );
		} else if ( SocializeMedia.Copy.equals( platformName ) || SocializeMedia.More.equals( platformName ) ) {
			param.setContent( CONTENT + " " + TARGET_URL );
		}
		PlatformShareConfiguration configuration = new PlatformShareConfiguration.Builder(this)
				.imageDownloader(new ShareFrescoImageDownloader())
				.defaultShareImage(R.mipmap.ic_launcher)
				.build();
		ShareSDK.make( MainActivity.this, param,configuration )//
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

	private void login( String platformName ) {
		AuthorizeSDK.authorize( MainActivity.this, platformName, new OnCallback< String >() {
			@Override
			public void onStart( Activity activity ) {

			}

			@Override
			public void onCompleted( Activity activity ) {

			}

			@Override
			public void onSuccess( Activity activity, String result ) {
				Map< String, String > map = new Hashon().fromJson( result );
				ToastUtil.showSuccess( map.toString() );
			}

			@Override
			public void onError( Activity activity, int code, String message ) {
				ToastUtil.showError( message );
			}

			@Override
			public void onProgress( Activity activity, BaseShareParam params, String msg ) {

			}
		} );
	}

	@Override
	protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
		super.onActivityResult( requestCode, resultCode, data );
		AuthorizeSDK.onHandleResult( MainActivity.this, requestCode, resultCode, data );
		ShareSDK.onHandleResult( MainActivity.this, requestCode, resultCode, data );
	}
}
