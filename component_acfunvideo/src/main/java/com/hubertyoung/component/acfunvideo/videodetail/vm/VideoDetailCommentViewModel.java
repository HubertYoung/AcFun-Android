package com.hubertyoung.component.acfunvideo.videodetail.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.component.acfunvideo.videodetail.source.VideoDetailCommentRepository;


/**
 * desc:
 *
 * @author:HubertYoung
 * @date 2019/01/02 14:49
 * @since:
 * @see com.hubertyoung.component.acfunvideo.videodetail.fragment.VideoDetailCommentFragment
 */
public class VideoDetailCommentViewModel extends AbsViewModel< VideoDetailCommentRepository > {

	public VideoDetailCommentViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestCommentInfo( int sourceId, int sourceType, String pcursor, int count, int showHotComments, String access_token ) {
//		addDisposable( mRepository.requestCommentInfo(sourceId, sourceType, pcursor, count, showHotComments, access_token ).subscribeWith( new RxSubscriber< Object >() {
////			@Override
////			protected void showLoading() {
////				showDialogLoading( "" );
////			}
////
////			@Override
////			protected void finishLoading() {
////				stopLoading();
////			}
//
//			@Override
//			public void onSuccess( Object o ) {
//				//sendData( DynamicConstants.EVENT_KEY_DYNAMIC_ALL_CHANNEL, o );
//			}
//
//			@Override
//			public void onFailure( String msg ) {
////				ToastUtil.showError( msg );
//			}
//		} ) );
	}
}