package com.hubertyoung.component_acfundynamic.dynamic.vm;

import android.app.Application;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_acfundynamic.config.DynamicConstants;
import com.hubertyoung.component_acfundynamic.dynamic.fragment.DynamicFragment;
import com.hubertyoung.component_acfundynamic.dynamic.source.DynamicRepository;

import androidx.annotation.NonNull;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/18 14:33
 * @since:V5.2.7
 * @desc:com.hubertyoung.component_acfundynamic.dynamic.vm
 */
public class DynamicViewModel extends AbsViewModel< DynamicRepository > {

    public DynamicViewModel( @NonNull Application application ) {
        super( application );
    }

    public void requestAllChannel() {
        addDisposable( mRepository.requestAllChannel( )
                .subscribeWith( new RxSubscriber< User >() {
                    @Override
                    protected void showLoading() {
                        showDialogLoading( DynamicFragment.class.getSimpleName(), "" );
                    }

                    @Override
                    protected void finishLoading() {
                        stopLoading( DynamicFragment.class.getSimpleName() );
                    }

                    @Override
                    public void onSuccess( User user ) {
                        sendData( DynamicConstants.EVENT_KEY_DYNAMIC_ALL_CHANNEL, user );
                    }

                    @Override
                    public void onFailure( String msg ) {
                        ToastUtil.showError( msg );

                    }
                } ) );
    }
}
