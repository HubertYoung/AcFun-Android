package com.hubertyoung.component.acfunvideo.bangumidetail.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.bangumidetail.source.BangumiDetailRepository;


/**
 * desc:
 *
 * @author:HubertYoung
 * @date 2018/12/18 16:55
 * @since:
 * @see com.hubertyoung.component.acfunvideo.bangumidetail.activity.BangumiDetailActivity
 */
public class BangumiDetailViewModel extends AbsViewModel< BangumiDetailRepository > {

	public BangumiDetailViewModel( @NonNull Application application ) {
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