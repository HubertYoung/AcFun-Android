package com.hubertyoung.component_acfunarticle.article.source;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.data.BaseRepository;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_acfunarticle.BuildConfig;
import com.hubertyoung.component_acfunarticle.api.ApiArticleService;
import com.hubertyoung.component_acfunarticle.config.ArticleConstants;
import com.hubertyoung.component_acfunarticle.entity.Channel;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

import io.reactivex.disposables.Disposable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/12 11:23
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.ui.sign.source
 */
public class ArticleRepository extends BaseRepository {

	public void requestAllChannel() {
		addDisposable( ( Disposable ) Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiArticleService.class )
				.requestAllChannel( )
				.compose( new DefaultTransformer() )
				.subscribeWith( new RxSubscriber< Channel >() {
					@Override
					protected void showLoading() {
						showDialogLoading( "" );
					}

					@Override
					protected void finishLoading() {
						stopLoading();
					}

					@Override
					public void onSuccess( Channel channel ) {
						sendData( ArticleConstants.EVENT_KEY_ARTICLE_PLATFORM_LOGIN, channel );
					}

					@Override
					public void onFailure( String msg ) {
						ToastUtil.showError( msg );
					}
				} ) );
	}
}
