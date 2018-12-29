package com.hubertyoung.component.acfunvideo.videodetail.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.entity.VideoDetail;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.videodetail.activity.VideoDetailActivity;
import com.hubertyoung.component.acfunvideo.videodetail.source.VideoDetailRepository;
import com.hubertyoung.component_acfunvideo.R;


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

	public void requestVideoDetailInfo( int contentId, String from ) {
		if ( contentId == 0 ) {
			showErrorLayout( VideoDetailActivity.class.getSimpleName(), getApplication().getString( R.string.detail_content_not_exist ) );
			return;
		}
		addDisposable( mRepository.requestVideoDetailInfo( contentId, from ).subscribeWith( new RxSubscriber< VideoDetail >() {
			@Override
			protected void showLoading() {
//				showDialogLoading( "" );
			}

			@Override
			protected void finishLoading() {
				stopLoading( VideoDetailActivity.class.getSimpleName() );
			}

			@Override
			public void onSuccess( VideoDetail videoDetail ) {
				if ( videoDetail == null || videoDetail.mVideos.isEmpty() ){
					showErrorLayout( VideoDetailActivity.class.getSimpleName(), getApplication().getString( R.string.detail_content_not_exist ) );
					return;
				}
				sendData( VideoConstants.EVENT_KEY_VIDEODETAIL, videoDetail );
			}

			@Override
			public void onFailure( String msg ) {
				ToastUtil.showError( msg );
				showErrorLayout( VideoDetailActivity.class.getSimpleName(), getApplication().getString( R.string.detail_content_not_exist ) );
			}
		} ) );
	}
}