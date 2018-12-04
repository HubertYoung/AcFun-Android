package com.hubertyoung.component_gamecenter.api;


import com.hubertyoung.common.entity.Regions;
import com.hubertyoung.common.net.response.BaseCodeResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * @desc:api
 * @author:HubertYoung
 * @date 2018/12/4 14:19
 * @since:V5.7.0
 * @see ApiGameCenterService
 */
public interface ApiGameCenterService {

	@GET( "/game/gameCenter" )
	Flowable< BaseCodeResponse< List< Regions > > > requestGameCenter();
}
