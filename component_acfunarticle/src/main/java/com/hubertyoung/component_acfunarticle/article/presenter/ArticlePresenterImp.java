package com.hubertyoung.component_acfunarticle.article.presenter;


import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.component_acfunarticle.entity.Channel;
import com.hubertyoung.component_acfunarticle.article.control.ArticleControl;

import androidx.annotation.NonNull;
import io.reactivex.functions.Consumer;

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
	public void requestAllChannel( MyRequestMap map ) {
		mView.showLoading( "Loading...", 0 );
		mRxManage.add( mModel.requestAllChannel( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribe( new Consumer< Channel >() {

					@Override
					public void accept( @NonNull Channel channel ) throws Exception {
						mView.stopLoading();
						mView.setAllChannelInfo(channel);
					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( @NonNull Throwable throwable ) throws Exception {
						mView.stopLoading();
						mView.showErrorTip( throwable.getMessage() );
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
