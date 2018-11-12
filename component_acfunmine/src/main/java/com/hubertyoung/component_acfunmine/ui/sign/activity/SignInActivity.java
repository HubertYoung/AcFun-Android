package com.hubertyoung.component_acfunmine.ui.sign.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hubertyoung.common.base.AbsLifecycleActivity;
import com.hubertyoung.common.baserx.LiveBus;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.common.utils.SigninHelper;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.LoadingDialog;
import com.hubertyoung.component_acfunmine.R;
import com.hubertyoung.component_acfunmine.config.MineConstants;
import com.hubertyoung.component_acfunmine.entity.SignEntity;
import com.hubertyoung.component_acfunmine.ui.sign.vm.SignInViewModel;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

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
public class SignInActivity extends AbsLifecycleActivity< SignInViewModel > {
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
	private LoadingDialog mLoadingDialog;

	public static void launch( Context context ) {
		Intent intent = new Intent( context, SignInActivity.class );
		if ( !( context instanceof Activity ) ) {
			//调用方没有设置context或app间组件跳转，context为application
			intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		}
		context.startActivity( intent );
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_login_view;
	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		super.initView( savedInstanceState );
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
//		RxToolbar.navigationClicks( mToolbar )//
//				.subscribeOn( AndroidSchedulers.mainThread() )//
//				.subscribe( o -> finish() );
		RxView.clicks( mValidationImage )//
				.throttleFirst( 500, TimeUnit.MILLISECONDS )//
				.subscribeOn( AndroidSchedulers.mainThread() )//
				.subscribe( o -> {
					ToastUtil.showSuccess( "更改验证图片" );
				} );
		Observable.combineLatest( RxTextView.textChanges( userNameEdit ), RxTextView.textChanges( passWordEdit ), new BiFunction< CharSequence, CharSequence, Boolean >() {
			@Override
			public Boolean apply( CharSequence charSequence, CharSequence charSequence2 ) throws Exception {
				return !TextUtils.isEmpty( charSequence ) && !TextUtils.isEmpty( charSequence2 );
			}
		} ).subscribeOn( AndroidSchedulers.mainThread() )//
				.subscribe( new Consumer< Boolean >() {
					@Override
					public void accept( Boolean aBoolean ) throws Exception {
						mLoginViewLogin.setEnabled( aBoolean );
					}
				} );
		RxView.clicks( mLoginViewLogin )//
				.throttleFirst( 500, TimeUnit.MILLISECONDS )//
				.subscribeOn( AndroidSchedulers.mainThread() )//
				.subscribe( o -> {
					String userNameStr = userNameEdit.getText().toString().trim();
					String passwordStr = passWordEdit.getText().toString().trim();
					String validationStr = mValidationEdit.getText().toString().trim();
					mViewModel.requestLoginInfo( userNameStr, passwordStr, validationStr );
				} );
		RxView.clicks( mValidationImage )//
				.throttleFirst( 500, TimeUnit.MILLISECONDS )//
				.subscribeOn( AndroidSchedulers.mainThread() )//
				.subscribe( o -> {
					mViewModel.requestVerificationCodeInfo();
				} );
		RxView.clicks( mLoginViewForgetPassword )//
				.throttleFirst( 500, TimeUnit.MILLISECONDS )//
				.subscribeOn( AndroidSchedulers.mainThread() )//
				.subscribe( o -> {
//					FindPasswordActivity.launch( this, 2 );
				} );
		RxView.clicks( mLoginViewCanNotLogin )//
				.throttleFirst( 500, TimeUnit.MILLISECONDS )//
				.subscribeOn( AndroidSchedulers.mainThread() )//
				.subscribe( o -> {
					new AlertDialog.Builder( this )//
							.setTitle( R.string.login_view_can_not_login_title_text )//
							.setMessage( R.string.login_view_can_not_login_content_text )//
							.setNegativeButton( R.string.other_regist_btn_text, new DialogInterface.OnClickListener() {
								public void onClick( DialogInterface dialogInterface, int i ) {
									dialogInterface.dismiss();
								}
							} ).create().show();
				} );
	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {
		if ( mToolbar != null ) {
			mToolbar.setTitle( R.string.login );
			mToolbarTitle.setVisibility( View.GONE );
		}
	}

	@Override
	protected void dataObserver() {
//		LiveBus.getDefault().subscribe( MineConstants.EVENT_KEY_SIGN_STATE ).observe( this, observer );
		LiveBus.getDefault().subscribe( MineConstants.EVENT_KEY_SIGN, SignEntity.class ).observe( this, new Observer< SignEntity >() {
			@Override
			public void onChanged( SignEntity signEntity ) {
				BaseResponse< Sign > response = signEntity.mSignBaseResponse;
				if ( response.errno == 0 ) {
					//success
//						{
//							"token": "935b4692999e2fc654146ab35c21595e",
//								"expiration": 1543015656,
//								"check_password": 0,
//								"check_real": 0,
//								"oauth": 0,
//								"acPasstoken": "ChVpbmZyYS5hY2Z1bi5wYXNzdG9rZW4SYDo57DP6CfhrceiovnpfJl5BwiW9eZT26NWQbClyfMTe4
// -wGVcqUdLc_UM4ev2dRxVjUHpHJsObDvWLFMP85t9WEuVoUAJ1c_lwm6V31iq7mnx09dj8sbS1-aNZrLuhunRoSY5RL5hdSyg5ILxKRSiTMY7LUIiD6Te0ntRdYMdM4ZURXXiAOHrapG573gd9Oe-F8t-OOqygFMAE",
//								"acSecurity": "gPTOOAPz9QJJ5tQ6DxbXFQ==",
//								"acPostHint": "3332540703dc3d5982c019ddf863c42dea5c",
//								"passCheck": true,
//								"info": {
//							"avatar": "http:\/\/cdn.aixifan.com\/dotnet\/20120923\/style\/image\/avatar.jpg",
//									"username": "hubert520",
//									"userid": 13608720,
//									"mobile": "17600696672",
//									"group-level": 0,
//									"mobile-check": 1
//						},
//							"s2s-code": "22015c03c407892f84112603e131217d"
//						}
					Sign sign = response.getData();
					SigninHelper.getInstance().setUserSign( sign );
					showLoginSuccess( sign );
				} else {
					mViewModel.mRepository.tryConnectCount++;
					if ( response.errno == 20285 ) {
						mViewModel.mRepository.tryConnectCount = 3;
						response.errordesc = mContext.getResources().getString( R.string.login_view_need_input_image_code_text );
					}
					if ( mViewModel.mRepository.tryConnectCount > 2 && !getValidationLayoutShown() ) {
						setValidationLayoutShown();
					}
					if ( mViewModel.mRepository.tryConnectCount > 2 ) {
						mViewModel.requestVerificationCodeInfo();
						setValidationLayoutText( "" );
					}
					showErrorTip( response.errordesc );
				}
			}
		} );
	}

	@Override
	public void showLoading( String title ) {
		if ( mLoadingDialog == null ) {
			mLoadingDialog = new LoadingDialog( this );
			mLoadingDialog.setText( R.string.login_view_loading_text );
		}
		mLoadingDialog.show();
	}

	@Override
	public void stopLoading() {
		if ( mLoadingDialog != null ) {
			mLoadingDialog.dismiss();
		}
	}

	public void showErrorTip( String msg ) {
		if ( TextUtils.isEmpty( msg ) ) {
			ToastUtil.showError( R.string.activity_signin_error );
		} else {
			ToastUtil.showError( msg );
		}
	}

	public boolean getValidationLayoutShown() {
		return mValidationLayout == null ? false : mValidationLayout.isShown();
	}

	public void setValidationLayoutShown() {
		if ( mValidationLayout != null ) {
			mValidationLayout.setVisibility( View.VISIBLE );
		}
	}

	public void setValidationLayoutText( String text ) {
		if ( mValidationEdit != null ) {
			mValidationEdit.setText( text );
		}
	}

	//
//	@Override
//	public void setValidationImage( Bitmap bitmap ) {
//		mValidationImage.setImageBitmap( bitmap );
//	}
//
	public void showLoginSuccess( Sign sign ) {
		Bundle bundle = new Bundle();
		bundle.putInt( "uid", sign.info.userid );
		bundle.putString( "status", "success" );
		mRxManager.post( Constants.LoginStatus, Constants.LoginSuccess );
		ToastUtil.showSuccess( R.string.activity_signin_success );
		finish();
	}
}
