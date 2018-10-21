package com.hubertyoung.component_acfunmine.sign.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.component_acfunmine.R;
import com.hubertyoung.component_acfunmine.sign.control.SignInControl;
import com.hubertyoung.component_acfunmine.sign.model.SignInModelImp;
import com.hubertyoung.component_acfunmine.sign.presenter.SignInPresenterImp;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.widget.Toolbar;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/21 15:23
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.sign.activity
 */
public class SignInActivity extends BaseActivity<SignInPresenterImp,SignInModelImp > implements SignInControl.View {
	private Toolbar mToolbar;
	private TextView mToolbarTitle;
	private LinearLayout userNameLayout;
	private ImageView userNameIcon;
	private EditText userNameEdit;
	private LinearLayout passwordLayout;
	private ImageView passwordIcon;
	private EditText passWordEdit;
	private RelativeLayout mValidationLayout;
	//	private ImageView mLoginViewValidationIcon;
	private ImageView mValidationImage;
	private EditText mValidationEdit;
	private Button mLoginViewLogin;
	private TextView mLoginViewForgetPassword;
	private TextView mLoginViewCanNotLogin;
	private ImageView mWeChatImg;
	private TextView mWeChatText;
	private boolean mIsStartForVipLevel;

	@Override
	public int getLayoutId() {
		return R.layout.activity_login_view;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM( this,mModel );
	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		mIsStartForVipLevel = getIntent().getBooleanExtra( "isStartForVipLevel", false );
		mToolbar = findViewById( R.id.view_toolbar );
		mToolbarTitle = findViewById( R.id.toolbar_title );
		userNameLayout = findViewById( R.id.login_view_username_layout );
		userNameIcon = findViewById( R.id.login_view_username_icon );
		userNameEdit = findViewById( R.id.login_view_username_edit );
		passwordLayout = findViewById( R.id.login_view_password_layout );
		passwordIcon = findViewById( R.id.login_view_password_icon );
		passWordEdit = findViewById( R.id.login_view_password_edit );
		mValidationLayout = findViewById( R.id.login_view_validation_layout );
//		mLoginViewValidationIcon = findViewById( R.id.login_view_validation_icon );
		mValidationImage = findViewById( R.id.login_view_validation_img );
		mValidationEdit = findViewById( R.id.login_view_validation_edit );
		mLoginViewLogin = findViewById( R.id.login_view_login );
		mLoginViewForgetPassword = findViewById( R.id.login_view_forget_password );
		mLoginViewCanNotLogin = findViewById( R.id.login_view_can_not_login );
		mWeChatImg = findViewById( R.id.login_view_wechat );
		mWeChatText = findViewById( R.id.login_view_wechat_text );
		initAction();
		if ( !Constants.isShowWechat ) {
			mWeChatImg.setVisibility( View.GONE );
			mWeChatText.setVisibility( View.GONE );
		}

	}

	private void initAction() {
		mToolbar.setNavigationOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				finish();
			}
		} );
		mToolbar.setOnMenuItemClickListener( new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick( MenuItem item ) {
				if ( item.getItemId() != R.id.menu_register ) {
					return false;
				}
				if ( mIsStartForVipLevel ) {
//					IntentHelper.d(this, 5);
				} else {
//					IntentHelper.f(this, 1);
				}
				return true;
			}
		} );
		RxView.clicks( mValidationImage )//
				.throttleFirst( 500, TimeUnit.MILLISECONDS )//
				.subscribe( o -> {
					ToastUtil.showSuccess( "更改验证图片" );
				} );
		RxView.clicks( mLoginViewLogin )//
				.throttleFirst( 500, TimeUnit.MILLISECONDS )//
				.subscribe( o -> {

					String userNameStr = userNameEdit.getText().toString().trim();
					String passwordStr = passWordEdit.getText().toString().trim();
					String validationStr = mValidationEdit.getText().toString().trim();
					mPresenter.requestLoginInfo( userNameStr,passwordStr,validationStr );
				} );
	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {
		if ( mToolbar != null ) {
			mToolbar.setTitle( "" );
			mToolbar.inflateMenu( R.menu.menu_signin );
			mToolbarTitle.setText( R.string.login );
		}
	}

	@Override
	public void showLoading( String title, int type ) {

	}

	@Override
	public void stopLoading() {

	}

	@Override
	public void showErrorTip( String msg ) {
		ToastUtil.showError( msg );
	}
}
