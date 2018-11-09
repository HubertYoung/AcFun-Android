package com.hubertyoung.component_acfunarticle.article.presenter;


import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.component_acfunarticle.article.control.ArticleControl;
import com.hubertyoung.component_acfunarticle.entity.Channel;

import java.util.HashMap;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/16 17:17
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunarticle.mine.presenter
 */
public class ArticlePresenterImp extends ArticleControl.Presenter {
	@Override
	public void requestAllChannel( HashMap map ) {
		mRxManage.add( mModel.requestAllChannel( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< Channel >() {
					@Override
					protected void showLoading() {
						mView.showLoading( "Loading...", 0 );
					}

					@Override
					public void onComplete() {
						mView.stopLoading();
					}

					@Override
					public void onSuccess( Channel channel ) {
						mView.setAllChannelInfo( channel );
					}

					@Override
					public void onFailure( String msg ) {
						mView.showErrorTip( msg );
					}
				} ) );
	}

//	@Override
//	public void requestCheckOfflineInfo( MyRequestMap map ) {
//		mView.showLoading( "Loading...", 1 );
//		mRxManage.add( mModel.requestCheckOfflineInfo( map )
////				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
//				.subscribe( new Consumer< Boolean >() {
//
//					@Override
//					public void accept( @NonNull Boolean b ) throws Exception {
//						mView.stopLoading();
//						mView.setCheckOfflineInfo( b );
//
//					}
//				}, new Consumer< Throwable >() {
//					@Override
//					public void accept( @NonNull Throwable throwable ) throws Exception {
//						mView.stopLoading();
//						mView.showErrorTip( throwable.getMessage()
//								.toString() );
//					}
//				} ) );
//	}
}
