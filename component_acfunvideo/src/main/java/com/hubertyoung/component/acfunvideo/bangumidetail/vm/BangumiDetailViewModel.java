package com.hubertyoung.component.acfunvideo.bangumidetail.vm;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.bangumidetail.activity.BangumiDetailActivityNew;
import com.hubertyoung.component.acfunvideo.bangumidetail.source.BangumiDetailRepository;
import com.hubertyoung.component.acfunvideo.entity.BangumiDetailBeanRaw;


/**
 * desc:
 *
 * @author:HubertYoung
 * @date 2018/12/18 16:55
 * @since:
 * @see BangumiDetailActivityNew
 */
public class BangumiDetailViewModel extends AbsViewModel< BangumiDetailRepository > {

	public BangumiDetailViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestBangumisDetail( String contentId ) {
		addDisposable( mRepository.requestBangumisDetail(contentId).subscribeWith( new RxSubscriber< BangumiDetailBeanRaw >() {
			@Override
			protected void showLoading() {
//				showDialogLoading( "" );
			}

			@Override
			protected void finishLoading() {
				stopLoading("");
			}

			@Override
			public void onSuccess( BangumiDetailBeanRaw bangumiDetailBean ) {
				Log.e( "TAG", "bangumiDetailBean" );
				//sendData( DynamicConstants.EVENT_KEY_DYNAMIC_ALL_CHANNEL, o );
			}

			@Override
			public void onFailure( String msg ) {
				ToastUtil.showError( msg );
			}
		} ) );
	}
}