package com.acty.component.acfunvideo.index.model;

import com.acty.component.acfunvideo.api.ApiHomeService;
import com.acty.component.acfunvideo.entity.Regions;
import com.acty.component.acfunvideo.index.control.NewRecommendControl;
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
	public Observable< List<Regions > > requestNewRecommend( MyRequestMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestNewRecommend( map.map )
				.compose( new DefaultTransformer() );
	}
}
