package com.hubertyoung.component.acfunvideo.index.source;

import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.data.BaseRepository;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.api.ApiHomeService;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.entity.ChannelOperate;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;

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
public class ChannelRepository extends BaseRepository {
	public void requestChannel( String pos, int isAddInfo ) {
		HashMap map = new HashMap< String, String >();
		map.put( "pos", pos );
		addDisposable( ( Disposable ) Api.getDefault( HostType.MY_RESULT )//
				.getRetrofitClient()//
				.builder( ApiHomeService.class )//
				.requestChannelOperate( map )//
				.compose( new DefaultTransformer() )//
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< ChannelOperate >() {
					@Override
					protected void showLoading() {
						showDialogLoading( "" );
					}

					@Override
					protected void finishLoading() {
						stopLoading();
					}

					@Override
					public void onSuccess( ChannelOperate channelOperate ) {
						if ( isAddInfo == 0 ) {
							sendData( VideoConstants.EVENT_KEY_CHANNEL_OPERATE, channelOperate );
						} else {
							sendData( VideoConstants.EVENT_KEY_CHANNEL_OPERATE_ADD, channelOperate );
						}
					}

					@Override
					public void onFailure( String msg ) {
						ToastUtil.showError( msg );
					}
				} ) );
	}
}
