package com.hubertyoung.component_acfunarticle.article.presenter;


import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.component_acfunarticle.article.control.ArticleGeneralSecondControl;
import com.hubertyoung.component_acfunarticle.entity.RankAc;

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
public class ArticleGeneralSecondPresenterImp extends ArticleGeneralSecondControl.Presenter {
	public void requestArticleGeneralSecond( MyRequestMap map ) {
		mView.showLoading( "Loading...", 1 );
		mRxManage.add( mModel.requestArticleGeneralSecond( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribe( new Consumer< RankAc >() {

					@Override
					public void accept( @NonNull RankAc rankAc ) throws Exception {
						mView.stopLoading();
						mView.setArticleGeneralSecondInfo( rankAc );

					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( @NonNull Throwable throwable ) throws Exception {
						mView.stopLoading();
						mView.showErrorTip( throwable.getMessage().toString() );
					}
				} ) );
	}
}
