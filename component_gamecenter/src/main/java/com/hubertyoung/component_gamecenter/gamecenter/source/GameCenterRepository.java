package com.hubertyoung.component_gamecenter.gamecenter.source;

import com.hubertyoung.common.api.ApiImpl;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.baserx.RxSchedulers;
import com.hubertyoung.common.entity.Regions;
import com.hubertyoung.common.net.response.BaseCodeResponse;
import com.hubertyoung.component_gamecenter.api.ApiGameCenterService;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2018/12/4 14:16
 * @since:V5.7.0
 * @see GameCenterRepository
 */
public class GameCenterRepository extends AbsRepository {
	public Flowable< BaseCodeResponse< List< Regions > > > requestGameCenter() {
		return ApiImpl.getInstance( HostType.APP_NEWAPI_HOST )//
				.getRetrofitClient()//
				.builder( ApiGameCenterService.class )//
				.requestGameCenter()//
				.compose( RxSchedulers.io_main() );
	}
}
