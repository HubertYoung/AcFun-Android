package com.hubertyoung.component_acfundynamic.dynamic.presenter;


import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.component_acfundynamic.dynamic.control.DynamicAcfunControl;
import com.hubertyoung.component_acfundynamic.entity.RecommendBangumiEntity;

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
public class DynamicAcfunPresenterImp extends DynamicAcfunControl.Presenter {
	@Override
	public void requestRecommendUp( MyRequestMap map ) {
//		mView.showLoading( "Loading...", 1 );
//		mRxManage.add( mModel.requestRecommendUp( map )
////				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
//				.subscribe( new Consumer< RecommendBangumiEntity >() {
//					@Override
//					public void accept( RecommendBangumiEntity recommendBangumiEntity ) throws Exception {
//
//					}
//				} ));
		mRxManage.add( mModel.requestRecommendUp( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< RecommendBangumiEntity >() {
					@Override
					protected void showLoading() {
						mView.showLoading( "Loading...", 1 );
					}

					@Override
					public void onComplete() {
						mView.stopLoading();
					}

					@Override
					public void onSuccess( RecommendBangumiEntity recommendBangumiEntity ) {
						mView.setRecommendUpInfo( recommendBangumiEntity.list );

					}

					@Override
					public void onFailure( String msg ) {
						mView.showErrorTip( msg );

					}
				} ) );
	}
}
