package pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hubertyoung.baseplatform.AuthorizeSDK;
import com.hubertyoung.baseplatform.authorize.AuthorizeVia;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.tools.Hashon;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.component_pay.R;

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
public class MainActivity extends AppCompatActivity{
	private Button mBtnQQ;
	private Button mBtnWechat;
	private Button mBtnWeibo;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.pay_activity_main );
		mBtnQQ = ( Button ) findViewById( R.id.btn_qq );
		mBtnWechat = ( Button ) findViewById( R.id.btn_wechat );
		mBtnWeibo = ( Button ) findViewById( R.id.btn_weibo );
		initAction();
	}

	private void initAction() {
		mBtnQQ.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				login(AuthorizeVia.QQ);
			}
		} );
		mBtnWechat.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				login(AuthorizeVia.Wechat );
			}
		} );
		mBtnWeibo.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				login(AuthorizeVia.Weibo );
			}
		} );
	}

	private void login(String platformName) {
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
	}
}
