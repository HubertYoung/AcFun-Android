package pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hubertyoung.baseplatform.AuthorizeSDK;
import com.hubertyoung.baseplatform.authorize.AuthorizeVia;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.tools.Hashon;
import com.hubertyoung.component_pay.R;

import java.util.Map;

public class MainActivity extends AppCompatActivity{


	private Button mBtnQQ;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.pay_activity_main );
		mBtnQQ = ( Button ) findViewById( R.id.btn_qq );
		initAction();
	}

	private void initAction() {
		mBtnQQ.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				AuthorizeSDK.authorize( MainActivity.this, AuthorizeVia.QQ, new OnCallback< String >() {
					@Override
					public void onStart( Activity activity ) {

					}

					@Override
					public void onCompleted( Activity activity ) {

					}

					@Override
					public void onSuccess( Activity activity, String result ) {
						Map< String, String > map = new Hashon().fromJson( result );

					}

					@Override
					public void onError( Activity activity, int code, String message ) {
						Log.e( "TAG", "message ==> " + message );
					}
				} );
			}
		} );

	}

	@Override
	protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
		super.onActivityResult( requestCode, resultCode, data );
		AuthorizeSDK.onHandleResult( MainActivity.this, requestCode, resultCode, data );
	}
}
