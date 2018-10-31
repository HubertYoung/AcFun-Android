package com.hubertyoung.component_acfundynamic.dynamic.presenter;


import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.component_acfundynamic.dynamic.control.DynamicFollowBangumiControl;
import com.hubertyoung.component_acfundynamic.entity.RecommendBangumiEntity;

import io.reactivex.annotations.NonNull;
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
public class DynamicFollowBangumiPresenterImp extends DynamicFollowBangumiControl.Presenter {
	@Override
	public void requestRecommendBangumi( MyRequestMap map ) {
		mView.showLoading( "Loading...", 1 );
		mRxManage.add( mModel.requestRecommendBangumi( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribe( new Consumer< RecommendBangumiEntity >() {

					@Override
					public void accept( @NonNull RecommendBangumiEntity recommendBangumiEntity ) throws Exception {
						mView.stopLoading();
						mView.setRecommendBangumiInfo( recommendBangumiEntity.list );

					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( @NonNull Throwable throwable ) throws Exception {
						mView.stopLoading();
						mView.showErrorTip( throwable.getMessage() );
					}
				} ) );
	}
}
