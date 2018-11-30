package com.hubertyoung.component.acfunvideo.index.vm;

import android.app.Application;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.index.fragment.HomePageFragment;
import com.hubertyoung.component.acfunvideo.index.source.HomePageRepository;

import java.util.HashMap;

import android.support.annotation.NonNull;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/12 11:25
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.ui.sign.vm
 */
public class HomePageViewModel extends AbsViewModel< HomePageRepository > {

	public HomePageViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestDomainAndroidCfg() {
		addDisposable( mRepository.requestDomainAndroidCfg()//
				.subscribeWith( new RxSubscriber< HashMap >() {
					@Override
					protected void showLoading() {
						showLoadingLayout( HomePageFragment.class.getSimpleName(), "" );
					}

					@Override
					protected void finishLoading() {
						stopLoading( HomePageFragment.class.getSimpleName() );
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
