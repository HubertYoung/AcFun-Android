package com.hubertyoung.component.acfunvideo.index.presenter;


import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.component.acfunvideo.entity.ChannelOperate;
import com.hubertyoung.component.acfunvideo.index.control.ChannelControl;

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
public class ChannelPresenterImp extends ChannelControl.Presenter {
	@Override
	public void requestChannel( MyRequestMap map, int isAddInfo ) {
		mRxManage.add( mModel.requestChannel( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< ChannelOperate >() {
					@Override
					protected void showLoading() {
						mView.showLoading( "Loading...", 0 );
					}

					@Override
					public void onComplete() {
						mView.stopLoading();
					}

					@Override
					public void onSuccess( ChannelOperate channelOperate ) {
						if ( isAddInfo == 0 ) {
							mView.setChannelOperateInfo( channelOperate );
						} else {
							mView.addChannelOperateInfo( channelOperate );
						}
					}

					@Override
					public void onFailure( String msg ) {
						mView.showErrorTip(msg);
					}
				} ) );
	}
}
