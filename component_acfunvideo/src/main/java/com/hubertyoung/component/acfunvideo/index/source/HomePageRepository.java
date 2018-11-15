package com.hubertyoung.component.acfunvideo.index.source;

import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.baserx.RxSchedulers;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.data.BaseRepository;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.api.ApiHomeService;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;

import java.util.HashMap;

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
public class HomePageRepository extends BaseRepository {
	public void requestDomainAndroidCfg() {
		addDisposable( Api.getDefault( HostType.APP_HOST_SLL )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestDomainAndroidCfg()
				.compose( RxSchedulers.io_main() )
				.subscribeWith( new RxSubscriber< HashMap >() {
					@Override
					protected void showLoading() {
						showDialogLoading( "" );
					}

					@Override
					protected void finishLoading() {
						stopLoading();
					}

					@Override
					public void onSuccess( java.util.HashMap stringStringHashMap ) {
						sendData( VideoConstants.EVENT_KEY_CHANNEL_DOMAIN_ANDROIDCFG, stringStringHashMap );
					}

					@Override
					public void onFailure( String msg ) {
						ToastUtil.showError( msg );
					}
				} ) );
	}
}
