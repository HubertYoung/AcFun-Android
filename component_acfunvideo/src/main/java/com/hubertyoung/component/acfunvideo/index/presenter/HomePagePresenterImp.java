package com.hubertyoung.component.acfunvideo.index.presenter;


import com.hubertyoung.common.basebean.MyRequestMap;
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
	public void requestDomainAndroidCfg( MyRequestMap map ) {
		mRxManage.add( mModel.requestDomainAndroidCfg( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< HashMap< String, String > >() {
					@Override
					protected void showLoading() {
						mView.showLoading( "Loading...", 0 );
					}

					@Override
					public void onComplete() {
						mView.stopLoading();
					}

					@Override
					public void onSuccess( HashMap< String, String > stringStringHashMap ) {
						CommonLog.logd( stringStringHashMap.toString() );

					}

					@Override
					public void onFailure( String msg ) {
						mView.showErrorTip( msg );
					}
				} ) );
	}
}
