package gridpasswordview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.hubertyoung.component_gridpasswordview.R;
import com.hubertyoung.component_gridpasswordview.gridpasswordview.GridPasswordView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class MainActivity extends AppCompatActivity {

	@BindView( R.id.gpv_normal )
	GridPasswordView mGpvNormal;
	@BindView( R.id.gpv_length )
	GridPasswordView mGpvLength;
	@BindView( R.id.gpv_customUi )
	GridPasswordView mGpvCustomUi;
	@BindView( R.id.gpv_transformation )
	GridPasswordView mGpvTransformation;
	@BindView( R.id.pswtype_tv )
	TextView mPswtypeTv;
	@BindView( R.id.pswtype_sp )
	Spinner mPswtypeSp;
	@BindView( R.id.psw_visibility_switcher )
	Switch mPswVisibilitySwitcher;
	@BindView( R.id.gpv_passwordType )
	GridPasswordView mGpvPasswordType;
	@BindView( R.id.gpv_normail_twice )
	GridPasswordView mGpvNormailTwice;

	boolean isFirst = true;
	String firstPwd;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.gridpasswordview_activity_main );
		ButterKnife.bind( this );
		onPwdChangedTest();
	}
	@OnCheckedChanged(R.id.psw_visibility_switcher)
	void onCheckedChanged(boolean isChecked) {
		mGpvNormailTwice.togglePasswordVisibility();
	}

	// Test GridPasswordView.clearPassword() in OnPasswordChangedListener.
	// Need enter the password twice and then check the password , like Alipay
	void onPwdChangedTest(){
		mGpvNormailTwice.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
			@Override
			public void onTextChanged(String psw) {
				if (psw.length() == 6 && isFirst){
					mGpvNormailTwice.clearPassword();
					isFirst = false;
					firstPwd = psw;
				}else if (psw.length() == 6 && !isFirst){
					if (psw.equals(firstPwd)){
						Log.d("MainActivity", "The password is: " + psw);
					}else {
						Log.d("MainActivity", "password doesn't match the previous one, try again!");
						mGpvNormailTwice.clearPassword();
						isFirst = true;
					}
				}
			}

			@Override
			public void onInputFinish(String psw) { }
		});
	}
}
