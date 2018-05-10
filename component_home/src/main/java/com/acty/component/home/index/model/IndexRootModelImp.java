package com.acty.component.home.index.model;

import com.acty.component.home.api.ApiHomeService;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.acty.component.home.entity.HomeIndexEntity;
import com.acty.component.home.index.control.IndexRootControl;

import io.reactivex.Observable;


/**
 * <br>
 * function:首页model
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 11:18 AM
 * @since:V1.0
 * @desc:com.kento.component.home.index.model
 */
public class IndexRootModelImp implements IndexRootControl.Model {
	@Override
	public Observable< HomeIndexEntity > requestHomeIndex( MyRequestMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestHomeIndex( map.map )
				.compose( new DefaultTransformer<>() );
	}
}
