package com.hubertyoung.component.acfunvideo.index.presenter;


import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.utils.CommonLog;
import com.hubertyoung.component.acfunvideo.index.control.HomePageControl;

import java.util.HashMap;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

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
		mView.showLoading( "Loading...", 0 );
		mRxManage.add( mModel.requestDomainAndroidCfg( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribe( new Consumer< HashMap< String, String > >() {
					@Override
					public void accept( HashMap< String, String > stringStringHashMap ) throws Exception {
						CommonLog.logd( stringStringHashMap.toString() );
					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( @NonNull Throwable throwable ) throws Exception {
						mView.stopLoading();
						mView.showErrorTip( throwable.getMessage() );
					}
				} ) );
	}
}
