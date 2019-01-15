package com.hubertyoung.component.acfunvideo.bangumidetail.source;

import com.hubertyoung.common.api.ApiImpl;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component.acfunvideo.api.ApiHomeService;
import com.hubertyoung.component.acfunvideo.entity.BangumiDetailBeanRaw;

import io.reactivex.Flowable;

/**
 * desc:
 *
 * @author:HubertYoung
 * @date: 2018/12/18 16:55
 * @since:
 * @see com.hubertyoung.component.acfunvideo.bangumidetail.vm.BangumiDetailViewModel
 */

public class BangumiDetailRepository extends AbsRepository {

	public Flowable< BangumiDetailBeanRaw > requestBangumisDetail( String contentId ) {
		return ApiImpl.getInstance( HostType.APP_NEWAPI_HOST )//
				.getRetrofitClient()//
//				.setBaseUrl( EnvironmentSwitcher.getAppEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )//
				.builder( ApiHomeService.class )//
				.requestBangumisDetail( contentId )//
				.compose( new DefaultTransformer() );
	}

}