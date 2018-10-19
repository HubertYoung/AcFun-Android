package com.hubertyoung.component.acfunvideo.index.model;

import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.baserx.RxSchedulers;
import com.hubertyoung.component.acfunvideo.api.ApiHomeService;
import com.hubertyoung.component.acfunvideo.index.control.HomePageControl;

import java.util.HashMap;

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
public class HomePageModelImp implements HomePageControl.Model {
	@Override
	public Observable< HashMap< String, String > > requestDomainAndroidCfg( MyRequestMap map ) {
		return Api.getDefault( HostType.SSL_APP_HOST )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestDomainAndroidCfg( map.map )
				.compose( RxSchedulers.io_main() );
	}
}
