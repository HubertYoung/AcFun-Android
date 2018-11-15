package com.hubertyoung.component.acfunvideo.index.source;

import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component.acfunvideo.api.ApiHomeService;
import com.hubertyoung.component.acfunvideo.entity.Regions;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Flowable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/15 15:36
 * @since:V5.2.7
 * @desc:com.hubertyoung.component.acfunvideo.index.source
 */
public class NewRecommendRepository extends AbsRepository {
	public Flowable<List< Regions >> requestRecommend( String channelId ) {
		HashMap map = new HashMap< String, String >();
		map.put( "channelId", channelId );
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestRecommend( map )
				.compose( new DefaultTransformer() );
	}

	public Flowable<List< RegionBodyContent >> requestNewRecommend( String channelId, String pageNo ) {
		HashMap map = new HashMap< String, String >();
		map.put( "pageNo", pageNo );
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestNewRecommend( channelId, map )
				.compose( new DefaultTransformer() );
	}
}
