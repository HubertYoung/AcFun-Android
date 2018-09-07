package com.acty.component.acfunvideo.index.model;

import com.acty.component.acfunvideo.api.ApiHomeService;
import com.acty.component.acfunvideo.entity.ChannelOperate;
import com.acty.component.acfunvideo.index.control.ChannelControl;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.net.transformer.DefaultTransformer;

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
public class ChannelModelImp implements ChannelControl.Model {
	@Override
	public Observable< ChannelOperate > requestChannel( MyRequestMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestChannelOperate( map.map )
				.compose( new DefaultTransformer() );
	}
}
