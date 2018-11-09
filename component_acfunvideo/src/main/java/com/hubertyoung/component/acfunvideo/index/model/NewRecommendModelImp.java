package com.hubertyoung.component.acfunvideo.index.model;

import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component.acfunvideo.api.ApiHomeService;
import com.hubertyoung.component.acfunvideo.entity.Regions;
import com.hubertyoung.component.acfunvideo.index.control.NewRecommendControl;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Flowable;


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
	public Flowable< List<Regions > > requestRecommend( HashMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestRecommend( map )
				.compose( new DefaultTransformer() );
	}
	@Override
	public Flowable< List< RegionBodyContent > > requestNewRecommend( String channelId, HashMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestNewRecommend(channelId, map )
				.compose( new DefaultTransformer() );
	}
}
