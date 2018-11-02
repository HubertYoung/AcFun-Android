package com.hubertyoung.component_acfunarticle.article.model;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component_acfunarticle.BuildConfig;
import com.hubertyoung.component_acfunarticle.api.ApiArticleService;
import com.hubertyoung.component_acfunarticle.article.control.ArticleGeneralSecondControl;
import com.hubertyoung.component_acfunarticle.entity.RankAc;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

import io.reactivex.Flowable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/16 17:17
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunarticle.mine.model
 */
public class ArticleGeneralSecondModelImp implements ArticleGeneralSecondControl.Model {
	@Override
	public Flowable< RankAc > requestArticleGeneralSecond( MyRequestMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG) )
				.builder( ApiArticleService.class )
				.requestArticleGeneralSecond( map.map )
				.compose( new DefaultTransformer() );
	}
}
