package com.hubertyoung.component_acfunarticle.article.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_acfunarticle.article.fragment.ArticleGeneralSecondFragment;
import com.hubertyoung.component_acfunarticle.article.source.ArticleGeneralSecondRepository;
import com.hubertyoung.component_acfunarticle.config.ArticleConstants;
import com.hubertyoung.component_acfunarticle.entity.RankAc;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/15 15:22
 * @since:V5.2.7
 * @desc:com.hubertyoung.component_acfunarticle.article.vm
 */
public class ArticleGeneralSecondViewModel extends AbsViewModel< ArticleGeneralSecondRepository > {

	public ArticleGeneralSecondViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestArticleGeneralSecond( String mChannelId, String selectorTimeType, String selectorType, String realmIds, String pageNo, String pageSize ) {
		addDisposable( mRepository.requestArticleGeneralSecond( mChannelId, selectorTimeType, selectorType, realmIds, pageNo, pageSize )//
				.subscribeWith( new RxSubscriber< RankAc >() {
					@Override
					protected void showLoading() {
						showLoadingLayout( ArticleGeneralSecondFragment.class.getSimpleName(), "" );
					}

					@Override
					protected void finishLoading() {
						stopLoading( ArticleGeneralSecondFragment.class.getSimpleName() );
					}


					@Override
					public void onSuccess( RankAc rankAc ) {
						sendData( ArticleConstants.EVENT_KEY_ARTICLE_ARTICLE_GENERAL_SECOND, rankAc );
					}

					@Override
					public void onFailure( String msg ) {
						ToastUtil.showError( msg );
					}
				} ) );
	}
}
