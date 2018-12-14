package com.hubertyoung.component.acfunvideo.index.source;

import com.hubertyoung.common.api.ApiImpl;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.baserx.RxSchedulers;
import com.hubertyoung.component.acfunvideo.api.ApiHomeService;

import java.util.HashMap;

import io.reactivex.Flowable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/15 15:36
 * @since:V5.2.7
 * @desc:com.hubertyoung.component.acfunvideo.index.source
 */
public class HomePageRepository extends AbsRepository {
	public Flowable< HashMap< String, String > > requestDomainAndroidCfg() {
		return ApiImpl.getInstance( HostType.APP_SLL_HOST )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestDomainAndroidCfg()
				.compose( RxSchedulers.io_main() );
	}
}
