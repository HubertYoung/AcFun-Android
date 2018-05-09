package com.kento.component.home.index.model;

import com.kento.common.api.Api;
import com.kento.common.api.HostType;
import com.kento.common.basebean.MyRequestMap;
import com.kento.common.net.transformer.DefaultTransformer;
import com.kento.component.home.api.ApiHomeService;
import com.kento.component.home.entity.HomeIndexEntity;
import com.kento.component.home.index.control.IndexRootControl;

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
