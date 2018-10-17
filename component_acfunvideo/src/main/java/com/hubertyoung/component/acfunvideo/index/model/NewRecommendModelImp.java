package com.hubertyoung.component.acfunvideo.index.model;

import com.hubertyoung.component.acfunvideo.api.ApiHomeService;
import com.hubertyoung.component.acfunvideo.entity.RegionBodyContent;
import com.hubertyoung.component.acfunvideo.entity.Regions;
import com.hubertyoung.component.acfunvideo.index.control.NewRecommendControl;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.net.transformer.DefaultTransformer;

import java.util.List;

import io.reactivex.Observable;


/**
 * <br>
 * function:首页model
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 11:18 AM
 * @since:V1.0
 * @desc:com.hubertyoung.component.home.index.model
 */
public class NewRecommendModelImp implements NewRecommendControl.Model {
	@Override
	public Observable< List<Regions > > requestRecommend( MyRequestMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestRecommend( map.map )
				.compose( new DefaultTransformer() );
	}
	@Override
	public Observable< List< RegionBodyContent > > requestNewRecommend( String channelId, MyRequestMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestNewRecommend(channelId, map.map )
				.compose( new DefaultTransformer() );
	}
}
