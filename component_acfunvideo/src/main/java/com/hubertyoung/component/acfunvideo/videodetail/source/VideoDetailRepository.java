package com.hubertyoung.component.acfunvideo.videodetail.source;

import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.component.acfunvideo.videodetail.vm.VideoDetailViewModel;

import io.reactivex.Flowable;

/**
 * desc:
 *
 * @author:HubertYoung
 * @date: 2018/12/18 15:15
 * @since:
 * @see VideoDetailViewModel
 */

public class VideoDetailRepository extends AbsRepository {

	public Flowable< Object > requestHttp() {
//		return ApiImpl.getInstance( HostType.APP_NEWAPI_HOST )
//				.getRetrofitClient()
//				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
//				.builder( ApiHomeService.class )
//				.requestUserInfo( map )
//				.compose( new DefaultTransformer() );
		return null;
	}

}