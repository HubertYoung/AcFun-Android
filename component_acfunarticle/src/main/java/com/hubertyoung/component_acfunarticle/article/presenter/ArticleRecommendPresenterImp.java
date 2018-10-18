package com.hubertyoung.component_acfunarticle.article.presenter;


import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.component_acfunarticle.entity.ArticleRecommendEntity;
import com.hubertyoung.component_acfunarticle.article.control.ArticleRecommendControl;

import java.util.List;

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
public class ArticleRecommendPresenterImp extends ArticleRecommendControl.Presenter {
	@Override
	public void requestArticleRecommend( MyRequestMap map ) {
				mView.showLoading( "Loading...", 1 );
		mRxManage.add( mModel.requestArticleRecommend( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribe( new Consumer< List< ArticleRecommendEntity > >() {

					@Override
					public void accept( @NonNull List< ArticleRecommendEntity > articleRecommendEntityList ) throws Exception {
						mView.stopLoading();
						mView.setArticleRecommend( articleRecommendEntityList );

					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( @NonNull Throwable throwable ) throws Exception {
						mView.stopLoading();
						mView.showErrorTip( throwable.getMessage()
								.toString() );
					}
				} ) );
	}
}
