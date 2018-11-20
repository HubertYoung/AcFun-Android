package com.hubertyoung.component_acfunarticle.article.source;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_acfunarticle.BuildConfig;
import com.hubertyoung.component_acfunarticle.api.ApiArticleService;
import com.hubertyoung.component_acfunarticle.entity.RankAc;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/15 15:22
 * @since:V5.2.7
 * @desc:com.hubertyoung.component_acfunarticle.article.source
 */
public class ArticleGeneralSecondRepository extends AbsRepository {

	public void requestArticleGeneralSecond( String mChannelId, String selectorTimeType, String selectorType, String realmIds, String pageNo, String pageSize ) {
		HashMap map = new HashMap< String, String >();
		map.put( "channelId", mChannelId );
		map.put( "day", selectorTimeType );
		map.put( "sort", selectorType );
		map.put( "realmIds", realmIds);
		map.put( "pageNo", pageNo );
		map.put( "pageSize", pageSize );
		addDisposable( ( Disposable ) Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG) )
				.builder( ApiArticleService.class )
				.requestArticleGeneralSecond( map )
				.compose( new DefaultTransformer() )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< RankAc >() {
					@Override
					protected void showLoading() {
//						showLoadingLayout( "" );
					}

					@Override
					protected void finishLoading() {
//						stopLoading();
					}

					@Override
					public void onSuccess( RankAc rankAc ) {
//						sendData( ArticleConstants.EVENT_KEY_ARTICLE_ARTICLE_GENERAL_SECOND, rankAc );
					}

					@Override
					public void onFailure( String msg ) {
						ToastUtil.showError( msg );
					}
				} ) );
	}
}
