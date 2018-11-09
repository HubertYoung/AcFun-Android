package com.hubertyoung.component_acfunarticle.article.presenter;


import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.component_acfunarticle.article.control.ArticleGeneralSecondControl;
import com.hubertyoung.component_acfunarticle.entity.RankAc;

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
public class ArticleGeneralSecondPresenterImp extends ArticleGeneralSecondControl.Presenter {
	public void requestArticleGeneralSecond( HashMap map ) {
		mRxManage.add( mModel.requestArticleGeneralSecond( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< RankAc >() {
					@Override
					protected void showLoading() {
						mView.showLoading( "Loading...", 1 );

					}

					@Override
					public void onComplete() {
						mView.stopLoading();

					}

					@Override
					public void onSuccess( RankAc rankAc ) {
						mView.setArticleGeneralSecondInfo( rankAc );

					}

					@Override
					public void onFailure( String msg ) {
						mView.showErrorTip( msg );

					}
				} ) );
	}
}
