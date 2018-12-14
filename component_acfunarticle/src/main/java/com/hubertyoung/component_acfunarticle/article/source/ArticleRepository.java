package com.hubertyoung.component_acfunarticle.article.source;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.api.ApiImpl;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component_acfunarticle.BuildConfig;
import com.hubertyoung.component_acfunarticle.api.ApiArticleService;
import com.hubertyoung.component_acfunarticle.entity.Channel;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

import io.reactivex.Flowable;

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
public class ArticleRepository extends AbsRepository {

	public Flowable< Channel > requestAllChannel() {
		return ApiImpl.getInstance( HostType.APP_NEWAPI_HOST )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiArticleService.class )
				.requestAllChannel()
				.compose( new DefaultTransformer() );

	}
}
