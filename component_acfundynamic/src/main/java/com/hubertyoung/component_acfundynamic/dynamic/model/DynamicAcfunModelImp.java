package com.hubertyoung.component_acfundynamic.dynamic.model;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component_acfundynamic.BuildConfig;
import com.hubertyoung.component_acfundynamic.api.ApiDynamicService;
import com.hubertyoung.component_acfundynamic.dynamic.control.DynamicAcfunControl;
import com.hubertyoung.component_acfundynamic.entity.RecommendBangumiEntity;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

import java.util.HashMap;

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
public class DynamicAcfunModelImp implements DynamicAcfunControl.Model {
	@Override
	public Flowable< RecommendBangumiEntity > requestRecommendUp( HashMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG) )
				.builder( ApiDynamicService.class )
				.requestRecommendUp( map )
				.compose( new DefaultTransformer() );
	}
}
