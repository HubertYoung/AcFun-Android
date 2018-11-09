package com.hubertyoung.component_acfunmine.ui.mine.presenter;


import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.constant.AppSpConfig;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.utils.SigninHelper;
import com.hubertyoung.common.utils.data.SPUtils;
import com.hubertyoung.component_acfunmine.ui.mine.control.MineControl;

import java.util.HashMap;
import java.util.Map;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/16 13:07
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunmine.mine.presenter
 */
public class MinePresenterImp extends MineControl.Presenter {
	//	<int name="thirdChannel" value="0" />
//	<int name="userGroupLevel" value="0" />
//	<int name="mobileCheck" value="1" />
//    <string name="mobile">17600696672</string>
//    <string name="avatar">http://cdn.aixifan.com/dotnet/20120923/style/image/avatar.jpg</string>
//	<int name="initPassword" value="0" />
//    <string name="token">f8ddb49692a3d844d616afe4002cf5e6</string>
//	<int name="check_real" value="0" />
//	<int name="uid" value="13608720" />
//    <string name="s2sCode">7ffebd6b6148780efc8ed462c8a84a16</string>
//	<long name="expire" value="1539197055" />
//	<int name="oauth" value="0" />
//    <string name="username">hubert520</string>
	@Override
	public void requestUserInfo( HashMap map ) {
		if ( !SigninHelper.getInstance().isUnLogin() || SigninHelper.getInstance().getUserUid() <= 0 ) {
			mView.setLoginState( false );
			return;
		}

		mRxManage.add( mModel.requestUserInfo( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< User >() {
					@Override
					protected void showLoading() {
						mView.showLoading( "Loading...", 0 );
					}

					@Override
					public void onComplete() {
						mView.stopLoading();
					}

					@Override
					public void onSuccess( User user ) {
						if ( user != null ) {
							SigninHelper.getInstance().setUserInfo( user );
							SPUtils.setSharedStringData( AppSpConfig.USERGROUPLEVEL, user.getUserGroupLevel() + "" );
							SPUtils.setSharedStringData( AppSpConfig.MOBILECHECK, user.getMobileCheck() + "" );
						}
						mView.setUserInfo( user );
						mView.setUserGroupInfo( user.getUserGroupLevel() == Constants.USER_GROUP_LEVEL_FORMAL );
					}

					@Override
					public void onFailure( String msg ) {
						mView.showErrorTip( msg );
					}
				} ) );
	}

	@Override
	public void requestCheckOfflineInfo( HashMap map ) {
		mRxManage.add( mModel.requestCheckOfflineInfo( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< Boolean >() {
					@Override
					protected void showLoading() {
						mView.showLoading( "Loading...", 1 );
					}

					@Override
					public void onComplete() {
						mView.stopLoading();
					}

					@Override
					public void onSuccess( Boolean aBoolean ) {
						mView.setCheckOfflineInfo( aBoolean );
					}

					@Override
					public void onFailure( String msg ) {
						mView.showErrorTip( msg );
					}
				} ) );
	}

	@Override
	public void requestPlatformLogin( Map<String,String> map ) {
		mRxManage.add( mModel.requestPlatformLogin( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< Sign >() {
					@Override
					protected void showLoading() {
						mView.showLoading( "Loading...", 2 );
					}

					@Override
					public void onComplete() {
						mView.stopLoading();
					}

					@Override
					public void onSuccess( Sign sign ) {
						mView.setPlatformLoginInfo( sign );
					}

					@Override
					public void onFailure( String msg ) {
						mView.showErrorTip( msg );
					}
				} ) );
	}
}
