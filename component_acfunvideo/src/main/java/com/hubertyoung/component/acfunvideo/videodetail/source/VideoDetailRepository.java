package com.hubertyoung.component.acfunvideo.videodetail.source;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.api.ApiImpl;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.entity.VideoDetail;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component.acfunvideo.api.ApiHomeService;
import com.hubertyoung.component.acfunvideo.videodetail.vm.VideoDetailViewModel;
import com.hubertyoung.component_acfunvideo.BuildConfig;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

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

	public Flowable< VideoDetail > requestVideoDetailInfo( int contentId, String from ) {
		return ApiImpl.getInstance( HostType.APP_NEWAPI_HOST )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getAppEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiHomeService.class )
				.requestVideoDetailInfo( contentId ,from )
				.compose( new DefaultTransformer() );
//		return null;
	}

}