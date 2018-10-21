package com.hubertyoung.component_acfunmine.mine.presenter;


import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.constant.AppSpConfig;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.utils.SPUtils;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.component_acfunmine.mine.control.MineControl;
import com.hubertyoung.common.utils.SigninHelper;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

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
	public void requestUserInfo( MyRequestMap map ) {
		if ( !SigninHelper.getInstance().isUnLogin() || SigninHelper.getInstance().getUserUid() <= 0){
			mView.setLoginState( false );
			return;
		}
		mView.showLoading( "Loading...", 0 );

		mRxManage.add( mModel.requestUserInfo( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribe( new Consumer< User >() {

					@Override
					public void accept( @NonNull User user ) throws Exception {
						if(user != null) {
							SigninHelper.getInstance().setUserInfo(user);
							SPUtils.setSharedStringData( AppSpConfig.USERGROUPLEVEL ,user.getUserGroupLevel() +"");
							SPUtils.setSharedStringData( AppSpConfig.MOBILECHECK ,user.getMobileCheck() +"");
						}
						mView.stopLoading();
						mView.setUserInfo( user );
						mView.setUserGroupInfo( user.getUserGroupLevel() == Constants.USER_GROUP_LEVEL_FORMAL );
					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( @NonNull Throwable throwable ) throws Exception {
						mView.stopLoading();
						mView.showErrorTip( throwable.getMessage()
								.toString() );
					}
				} ) );
	}

	@Override
	public void requestCheckOfflineInfo( MyRequestMap map ) {
		mView.showLoading( "Loading...", 1 );
		mRxManage.add( mModel.requestCheckOfflineInfo( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribe( new Consumer< Boolean >() {

					@Override
					public void accept( @NonNull Boolean b ) throws Exception {
						mView.stopLoading();
						mView.setCheckOfflineInfo( b );

					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( @NonNull Throwable throwable ) throws Exception {
						mView.stopLoading();
						mView.showErrorTip( throwable.getMessage()
								.toString() );
					}
				} ) );
	}
}
