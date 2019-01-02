package com.hubertyoung.component.acfunvideo.videodetail.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.entity.RecommendBangumiEntity;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.utils.SigninHelper;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.videodetail.source.VideoDetailRelevantRepository;


/**
 * desc:
 *
 * @author:HubertYoung
 * @date 2019/01/02 14:45
 * @since:
 * @see com.hubertyoung.component.acfunvideo.videodetail.fragment.VideoDetailRelevantFragment
 */
public class VideoDetailRelevantViewModel extends AbsViewModel< VideoDetailRelevantRepository > {

	public VideoDetailRelevantViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestHttp() {
//		addDisposable( mRepository.requestHttp().subscribeWith( new RxSubscriber< Object >() {
//			@Override
//			protected void showLoading() {
//				showDialogLoading( "" );
//			}
//
//			@Override
//			protected void finishLoading() {
//				stopLoading();
//			}
//
//			@Override
//			public void onSuccess( Object o ) {
//				//sendData( DynamicConstants.EVENT_KEY_DYNAMIC_ALL_CHANNEL, o );
//			}
//
//			@Override
//			public void onFailure( String msg ) {
//				ToastUtil.showError( msg );
//			}
//		} ) );
	}

	public void requestUserInfo( int uid ) {
		if ( !SigninHelper.getInstance().isLogin() ){
			return;
		}
		addDisposable( mRepository.requestUserInfo( uid ).subscribeWith( new RxSubscriber< User >() {

			@Override
			public void onSuccess( User user ) {
				sendData( VideoConstants.EVENT_KEY_VIDEO_RELEVANT_USERINFO, user );
			}

			@Override
			public void onFailure( String msg ) {
//				ToastUtil.showError( msg );
			}
		} ) );
	}

	public void requestRelativeRecommendInfo( int cid, String s, int loadPage ) {
		addDisposable( mRepository.requestRelativeRecommendInfo( cid,s,loadPage ).subscribeWith( new RxSubscriber< RecommendBangumiEntity >() {

			@Override
			public void onSuccess( RecommendBangumiEntity recommendBangumiEntity ) {
				sendData( VideoConstants.EVENT_KEY_VIDEO_RELATIVERECOMMEND_INFO, recommendBangumiEntity );
			}

			@Override
			public void onFailure( String msg ) {
//				ToastUtil.showError( msg );
			}
		} ) );
	}
}