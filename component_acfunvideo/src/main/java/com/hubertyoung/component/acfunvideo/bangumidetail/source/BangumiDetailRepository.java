package com.hubertyoung.component.acfunvideo.bangumidetail.source;

import com.hubertyoung.common.base.AbsRepository;

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

	public Flowable< Object > requestHttp() {
//		return Api.getDefault( HostType.MY_RESULT )//
//				.getRetrofitClient()//
//				.setBaseUrl( EnvironmentSwitcher.getObjectEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )//
//				.builder( ApiObject.class )//
//				.requestHttp()//
//				.compose( new DefaultTransformer() );
		return null;
	}

}