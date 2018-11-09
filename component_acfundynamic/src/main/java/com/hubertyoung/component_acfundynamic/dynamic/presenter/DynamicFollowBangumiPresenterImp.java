package com.hubertyoung.component_acfundynamic.dynamic.presenter;


import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.component_acfundynamic.dynamic.control.DynamicFollowBangumiControl;
import com.hubertyoung.component_acfundynamic.entity.RecommendBangumiEntity;

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
public class DynamicFollowBangumiPresenterImp extends DynamicFollowBangumiControl.Presenter {
	@Override
	public void requestRecommendBangumi( HashMap map ) {
		mRxManage.add( mModel.requestRecommendBangumi( map )
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
						mView.setRecommendBangumiInfo( recommendBangumiEntity.list );
					}

					@Override
					public void onFailure( String msg ) {
						mView.showErrorTip( msg );
					}
				} ) );
	}
}
