package pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hubertyoung.baseplatform.AuthorizeSDK;
import com.hubertyoung.baseplatform.ShareSDK;
import com.hubertyoung.baseplatform.authorize.AuthorizeVia;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.share.ShareTo;
import com.hubertyoung.baseplatform.share.image.resource.UrlResource;
import com.hubertyoung.baseplatform.share.media.MoWeb;
import com.hubertyoung.baseplatform.tools.Hashon;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.component_pay.R;

import java.io.File;
import java.util.Map;

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

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.pay_activity_main );
		mBtnQQ = ( Button ) findViewById( R.id.btn_qq );
		mBtnWechat = ( Button ) findViewById( R.id.btn_wechat );
		mBtnWeibo = ( Button ) findViewById( R.id.btn_weibo );
		mBtnQQShare = ( Button ) findViewById( R.id.btn_qq_share );
		mBtnQQZoneShare = ( Button ) findViewById( R.id.btn_qqzone_share );
		mBtnWechatShare = ( Button ) findViewById( R.id.btn_wechat_share );
		mBtnWeiboShare = ( Button ) findViewById( R.id.btn_weibo_share );
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
				share(ShareTo.QQ );
			}
		} );
		mBtnQQZoneShare.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				share(ShareTo.QZone );
			}
		} );
		mBtnWechatShare.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				share( ShareTo.WXSession );
			}
		} );
		mBtnWeiboShare.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				share( ShareTo.Weibo );
			}
		} );
	}

	private void share( String platformName ) {
		UrlResource urlResource = new UrlResource( "https://goss3.vcg.com/creative/vcg/400/version23/VCG41598227336.jpg" );
		String input = new File( Environment.getExternalStorageDirectory(),"DCIM"+ File.separator +"5211c896758e4d3d8200b9738d509687.jpg").getAbsolutePath();

		ShareSDK.make( MainActivity.this,new MoWeb("http://www.baidu.com" ).setImageResource( new UrlResource(input)))//
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
		} );
	}

	@Override
	protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
		super.onActivityResult( requestCode, resultCode, data );
		AuthorizeSDK.onHandleResult( MainActivity.this, requestCode, resultCode, data );
		ShareSDK.onHandleResult( MainActivity.this, requestCode, resultCode, data );
	}
}
