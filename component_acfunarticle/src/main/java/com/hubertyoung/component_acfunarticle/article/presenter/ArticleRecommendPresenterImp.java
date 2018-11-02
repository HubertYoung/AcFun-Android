package com.hubertyoung.component_acfunarticle.article.presenter;


import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.component_acfunarticle.article.control.ArticleRecommendControl;
import com.hubertyoung.component_acfunarticle.entity.ArticleRecommendEntity;

import java.util.List;

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
public class ArticleRecommendPresenterImp extends ArticleRecommendControl.Presenter {
	@Override
	public void requestArticleRecommend( MyRequestMap map ) {
		mRxManage.add( mModel.requestArticleRecommend( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< List< ArticleRecommendEntity > >() {
					@Override
					protected void showLoading() {
						mView.showLoading( "Loading...", 1 );

					}

					@Override
					public void onComplete() {
						mView.stopLoading();
					}

					@Override
					public void onSuccess( List< ArticleRecommendEntity > articleRecommendEntities ) {
						mView.setArticleRecommend( articleRecommendEntities );

					}

					@Override
					public void onFailure( String msg ) {
						mView.showErrorTip( msg );
					}
				} ) );
	}
}
