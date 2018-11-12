package com.hubertyoung.component_acfunmine.ui.mine.vm;

import android.app.Application;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.component_acfunmine.ui.mine.source.MineRepository;

import java.util.HashMap;

import androidx.annotation.NonNull;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/12 16:45
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.ui.mine.vm
 */
public class MineViewModel extends AbsViewModel< MineRepository > {
	public MineViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestPlatformLogin( String token, String openid, String platformName ) {
		mRepository.requestPlatformLogin(token, openid, platformName );
	}

	public void requestCheckOfflineInfo() {
		mRepository.requestCheckOfflineInfo();
	}

	public void requestUserInfo( String userId ) {
		HashMap map = new HashMap<String,String>();
		map.put( "userId", userId );
		mRepository.requestUserInfo(map);
	}
}
