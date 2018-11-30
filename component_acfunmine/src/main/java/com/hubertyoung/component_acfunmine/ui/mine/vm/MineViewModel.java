package com.hubertyoung.component_acfunmine.ui.mine.vm;

import android.app.Application;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_acfunmine.config.MineConstants;
import com.hubertyoung.component_acfunmine.ui.mine.fragment.MineFragment;
import com.hubertyoung.component_acfunmine.ui.mine.source.MineRepository;

import android.support.annotation.NonNull;

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
        addDisposable( mRepository.requestPlatformLogin( token, openid, platformName )
                .subscribeWith( new RxSubscriber< Sign >() {
                    @Override
                    protected void showLoading() {
                        showLoadingLayout( MineFragment.class.getSimpleName(), "" );
                    }

                    @Override
                    protected void finishLoading() {
                        stopLoading( MineFragment.class.getSimpleName() );
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

    public void requestCheckOfflineInfo() {
        addDisposable( mRepository.requestCheckOfflineInfo()      //
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

    public void requestUserInfo( String userId ) {

        addDisposable( mRepository.requestUserInfo( userId )
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
}
