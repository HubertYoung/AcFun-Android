package com.hubertyoung.common.utils.sign;

import android.app.Activity;

import com.billy.cc.core.component.CC;
import com.hubertyoung.baseplatform.AuthorizeSDK;
import com.hubertyoung.baseplatform.authorize.AuthorizeVia;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.share.shareparam.BaseShareParam;
import com.hubertyoung.baseplatform.tools.Hashon;
import com.hubertyoung.common.R;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.LoadingDialog;

import java.util.Map;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/1 14:02
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.utils.sign
 */
public class SignInUtil {

	private Activity mActivity;
	private LoadingDialog mLoadingDialog;

	public SignInUtil( Activity activity ) {
		this.mActivity = activity;
	}

	public void showLoading() {
		if ( mLoadingDialog == null ) {
			mLoadingDialog = new LoadingDialog( mActivity );
			mLoadingDialog.setText( R.string.login_view_loading_text );
		}
		mLoadingDialog.show();
	}

	public void dismissLoading() {
		if ( mLoadingDialog != null ) {
			mLoadingDialog.dismiss();
		}
	}

	public boolean isShowing() {
		if ( mLoadingDialog != null ) {
			return mLoadingDialog.isShowing();
		}
		return false;
	}
	public void loginAccount() {
		CC.obtainBuilder( "ComponentMine" ).setActionName( "startLoginActivity" ).build().call();
	}
	public void loginQQ() {
		login( AuthorizeVia.QQ );
	}

	public void loginWechat() {
		login( AuthorizeVia.Wechat );
	}

	private void login( String platformName ) {
		AuthorizeSDK.authorize( mActivity, platformName, new OnCallback< String >() {
			@Override
			public void onStart( Activity activity ) {
				showLoading();
			}

			@Override
			public void onCompleted( Activity activity ) {
			}

			@Override
			public void onSuccess( Activity activity, String result ) {
//				0 = {HashMap$Node@8054} "expiresIn" -> "7776000"
//				1 = {HashMap$Node@8057} "pfkey" -> "15466464f3695f3387c8f3a339a4c130"
//				2 = {HashMap$Node@8060} "gender" -> "0"
//				3 = {HashMap$Node@8063} "pay_token" -> "7760FAB10C8C36EFB801D99E3B4CF4B7"
//				4 = {HashMap$Node@8066} "openid" -> "76AF7773957EBDEFFB10253B8F43CC4F"
//				5 = {HashMap$Node@8069} "pf" -> "desktop_m_qq-10000144-android-2002-"
//				6 = {HashMap$Node@8072} "icon" -> "http://thirdqq.qlogo.cn/qqapp/1106891112/76AF7773957EBDEFFB10253B8F43CC4F/40"
//				7 = {HashMap$Node@8075} "nickname" -> "白いバラの夜"
//				8 = {HashMap$Node@8078} "iconQzone" -> "http://thirdqq.qlogo.cn/qqapp/1106891112/76AF7773957EBDEFFB10253B8F43CC4F/100"
//				9 = {HashMap$Node@8081} "token" -> "858EDD62834ED22F8898DBBB96FA63F3"
				Map< String, String > map = new Hashon().fromJson( result );
				if ( mOnPlatformNameListener != null ) {
					mOnPlatformNameListener.onSuccess( platformName, map );
				}
			}

			@Override
			public void onError( Activity activity, int code, String message ) {
				ToastUtil.showError( message );
				dismissLoading();
			}

			@Override
			public void onProgress( Activity activity, BaseShareParam params, String msg ) {

			}
		} );
	}


	public interface OnPlatformNameListener {
		void onSuccess( String platformName, Map< String, String > map );
	}

	private OnPlatformNameListener mOnPlatformNameListener;

	public void setOnPlatformNameListener( OnPlatformNameListener onPlatformNameListener ) {
		mOnPlatformNameListener = onPlatformNameListener;
	}
}
