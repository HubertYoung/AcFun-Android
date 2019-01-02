package com.hubertyoung.component.acfunvideo.videodetail.source;

import com.hubertyoung.common.api.ApiImpl;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.entity.RecommendBangumiEntity;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.common.utils.SigninHelper;
import com.hubertyoung.component.acfunvideo.api.ApiHomeService;

import io.reactivex.Flowable;

/**
 * desc:
 *
 * @author:HubertYoung
 * @date: 2019/01/02 14:45
 * @since:
 * @see com.hubertyoung.component.acfunvideo.videodetail.vm.VideoDetailRelevantViewModel
 */

public class VideoDetailRelevantRepository extends AbsRepository {

	public Flowable< User > requestUserInfo( int uid ) {
		return ApiImpl.getInstance( HostType.APP_NEWAPI_HOST )//
				.getRetrofitClient()//
				.builder( ApiHomeService.class )//
				.requestUserInfo( uid, SigninHelper.getInstance().getUserToken() )//
				.compose( new DefaultTransformer() );
	}

	public Flowable< RecommendBangumiEntity > requestRelativeRecommendInfo( int cid, String userId, int loadPage ) {
		return ApiImpl.getInstance( HostType.APP_NEWAPI_HOST )//
				.getRetrofitClient()//
				.builder( ApiHomeService.class )//
				.requestRelativeRecommendInfo( cid, userId, loadPage, "20" )//
				.compose( new DefaultTransformer() );
	}
}