package com.hubertyoung.component.acfunvideo.index.presenter;


import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.utils.log.CommonLog;
import com.hubertyoung.component.acfunvideo.index.control.HomePageControl;

import java.util.HashMap;

/**
 * <br>
 * function:Presenter
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 11:19 AM
 * @since:V1.0
 * @desc:com.hubertyoung.component.home.index.presenter
 */
public class HomePagePresenterImp extends HomePageControl.Presenter {
	@Override
	public void requestDomainAndroidCfg( HashMap map ) {
		mRxManage.add( mModel.requestDomainAndroidCfg( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< java.util.HashMap >() {
					@Override
					protected void showLoading() {
						mView.showLoading( "Loading...", 0 );
					}

					@Override
					public void onComplete() {
						mView.stopLoading();
					}

					@Override
					public void onSuccess( java.util.HashMap stringStringHashMap ) {
						CommonLog.logd( stringStringHashMap.toString() );

					}

					@Override
					public void onFailure( String msg ) {
						mView.showErrorTip( msg );
					}
				} ) );
	}
}
