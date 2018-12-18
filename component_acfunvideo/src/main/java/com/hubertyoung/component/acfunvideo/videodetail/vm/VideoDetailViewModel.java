package com.hubertyoung.component.acfunvideo.videodetail.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.videodetail.activity.VideoDetailActivity;
import com.hubertyoung.component.acfunvideo.videodetail.source.VideoDetailRepository;


/**
 * desc:
 *
 * @author:HubertYoung
 * @date 2018/12/18 15:15
 * @since:
 * @see VideoDetailActivity
 */
public class VideoDetailViewModel extends AbsViewModel< VideoDetailRepository > {

	public VideoDetailViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestHttp() {
		addDisposable( mRepository.requestHttp().subscribeWith( new RxSubscriber< Object >() {
			@Override
			protected void showLoading() {
//				showDialogLoading( "" );
			}

			@Override
			protected void finishLoading() {
				stopLoading("");
			}

			@Override
			public void onSuccess( Object o ) {
				//sendData( DynamicConstants.EVENT_KEY_DYNAMIC_ALL_CHANNEL, o );
			}

			@Override
			public void onFailure( String msg ) {
				ToastUtil.showError( msg );
			}
		} ) );
	}
}