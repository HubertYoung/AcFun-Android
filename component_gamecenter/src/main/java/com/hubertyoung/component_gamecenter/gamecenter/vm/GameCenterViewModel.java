package com.hubertyoung.component_gamecenter.gamecenter.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.entity.Regions;
import com.hubertyoung.common.entity.RegionsEntity;
import com.hubertyoung.common.net.response.BaseCodeResponse;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_gamecenter.config.GameCenterConstants;
import com.hubertyoung.component_gamecenter.gamecenter.fragment.GameCenterFragment;
import com.hubertyoung.component_gamecenter.gamecenter.source.GameCenterRepository;

import java.util.List;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2018/12/4 14:17
 * @since:V5.7.0
 * @see GameCenterViewModel
 */
public class GameCenterViewModel extends AbsViewModel< GameCenterRepository > {

	public GameCenterViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestGameCenter() {
		addDisposable( mRepository.requestGameCenter()
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< BaseCodeResponse< List< Regions > > >() {
					@Override
					protected void showLoading() {
						showLoadingLayout( GameCenterFragment.class.getSimpleName(), "" );
					}

					@Override
					protected void finishLoading() {
						stopLoading( GameCenterFragment.class.getSimpleName() );
					}

					@Override
					public void onSuccess( BaseCodeResponse< List< Regions > > baseCodeResponse ) {
						if ( baseCodeResponse.errno == 200 ) {
							sendData( GameCenterConstants.EVENT_KEY_GAMECENTER, new RegionsEntity( baseCodeResponse.data ) );
						} else {
							onFailure( baseCodeResponse.errordesc );
						}
//                        if ( isAddInfo == 0 ) {
//                        } else {
//                            sendData( VideoConstants.EVENT_KEY_CHANNEL_OPERATE_ADD, channelOperate );
//                        }
					}

					@Override
					public void onFailure( String msg ) {
						ToastUtil.showError( msg );
						showErrorLayout( GameCenterFragment.class.getSimpleName() );
					}
				} ) );
	}

}
