package com.hubertyoung.component_acfunmine.ui.mine.source;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.data.BaseRepository;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_acfunmine.BuildConfig;
import com.hubertyoung.component_acfunmine.api.ApiHomeService;
import com.hubertyoung.component_acfunmine.config.MineConstants;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/12 16:45
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.ui.mine.source
 */
public class MineRepository extends AbsRepository {
	public void requestUserInfo( HashMap map ) {
		addDisposable( ( Disposable ) Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiHomeService.class )
				.requestUserInfo( map )
				.compose( new DefaultTransformer() )
				.subscribeWith( new RxSubscriber< User >() {

					@Override
					public void onSuccess( User user ) {
						sendData( MineConstants.EVENT_KEY_MINE, user );
					}

					@Override
					public void onFailure( String msg ) {
						ToastUtil.showError( msg );
					}
				} ) );
	}

	public void requestCheckOfflineInfo() {
		addDisposable( ( Disposable ) Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiHomeService.class )
				.requestCheckOfflineInfo()
				.compose( new DefaultTransformer() )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribeWith( new RxSubscriber< Boolean >() {

					@Override
					public void onSuccess( Boolean aBoolean ) {
						sendData( MineConstants.EVENT_KEY_MINE_CHECK_OFFLINE, aBoolean );
					}

					@Override
					public void onFailure( String msg ) {
						ToastUtil.showError( msg );
					}
				} ) );
	}

	public void requestPlatformLogin( String token, String openid, String platformName ) {
		Map< String, String > hashMap = new HashMap();
		hashMap.put( "clientId", Constants.cidKey );
		hashMap.put( "accessToken", token );
		hashMap.put( "openId", openid );
		hashMap.put( "type", platformName );
		addDisposable( Api.getDefault( HostType.APP_HOST_ACCOUNT )
				.getRetrofitClient()
				.setBaseUrl( EnvironmentSwitcher.getAccountEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiHomeService.class )
				.requestPlatformLogin( hashMap )
				.compose( new DefaultTransformer<>() )
				.subscribeWith( new RxSubscriber< Sign >() {
					@Override
					protected void showLoading() {
						showDialogLoading( "" );
					}

					@Override
					protected void finishLoading() {
						stopLoading();
					}

					@Override
					public void onSuccess( Sign sign ) {
						sendData( MineConstants.EVENT_KEY_MINE_PLATFORM_LOGIN, sign );
					}

					@Override
					public void onFailure( String msg ) {
						ToastUtil.showError( msg );
					}
				} ) );
	}
}
