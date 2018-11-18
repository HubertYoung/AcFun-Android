package com.hubertyoung.component_acfunmine.ui.findpassword.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hubertyoung.common.base.AbsLifecycleActivity;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.component_acfunmine.R;

import androidx.appcompat.widget.Toolbar;

public class FindPasswordActivity extends AbsLifecycleActivity {

	private Toolbar mToolbar;
	private TextView mToolbarTitle;
	private LinearLayout mValidationPhoneLayout;
	private EditText findPasswordViewPhoneEdit;
	private EditText mValidationPhoneCodeEdit;
	private Button mValidationPhoneCodeBtn;
	private TextView mValidationPhoneSendCompleteText;
	private Button mValidationPhoneNext;
	private LinearLayout mSetPasswordLayout;
	private TextView mSetPasswordTopText;
	private EditText mSetPasswordPassword;
	private EditText mSetPasswordAgainPassword;
	private Button mSetPasswordComplete;

	public static void launch( BaseActivity activity, int requestCode ) {
		activity.startActivityForResult( FindPasswordActivity.class, requestCode );
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_find_password;
	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		super.initView( savedInstanceState );
		mToolbar = findViewById( R.id.view_toolbar );
		mToolbarTitle = findViewById( R.id.toolbar_title );
		mValidationPhoneLayout = findViewById( R.id.find_password_view_validation_phone_layout );
		findPasswordViewPhoneEdit = findViewById( R.id.find_password_view_phone_edit );
		mValidationPhoneCodeEdit = findViewById( R.id.find_password_view_verification_code_edit );
		mValidationPhoneCodeBtn = findViewById( R.id.find_password_view_verification_code_btn );
		mValidationPhoneSendCompleteText = findViewById( R.id.find_password_view_validation_send_complete_text );
		mValidationPhoneNext = findViewById( R.id.find_password_view_next_btn );
		mSetPasswordLayout = findViewById( R.id.find_password_view_set_password_layout );
		mSetPasswordTopText = findViewById( R.id.find_password_view_set_password_top_text );
		mSetPasswordPassword = findViewById( R.id.find_password_view_password_edit );
		mSetPasswordAgainPassword = findViewById( R.id.find_password_view_again_password_edit );
		mSetPasswordComplete = findViewById( R.id.regist_view_complete_btn );
	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {
		if ( mToolbar != null ) {
			mToolbar.setTitle( R.string.find_password_text );
			mToolbarTitle.setVisibility( View.GONE );
		}
	}
}
