package com.hubertyoung.component.acfunvideo.index.model;

import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component.acfunvideo.api.ApiHomeService;
import com.hubertyoung.component.acfunvideo.entity.ChannelOperate;
import com.hubertyoung.component.acfunvideo.index.control.ChannelControl;

import java.util.HashMap;

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
public class ChannelModelImp implements ChannelControl.Model {
	@Override
	public Flowable< ChannelOperate > requestChannel( HashMap map ) {
		return Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestChannelOperate( map )
				.compose( new DefaultTransformer() );
	}
}
