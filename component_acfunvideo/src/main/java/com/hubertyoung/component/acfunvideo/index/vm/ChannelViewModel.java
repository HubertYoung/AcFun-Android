package com.hubertyoung.component.acfunvideo.index.vm;

import android.app.Application;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.entity.ChannelOperate;
import com.hubertyoung.component.acfunvideo.index.fragment.ChannelFragment;
import com.hubertyoung.component.acfunvideo.index.source.ChannelRepository;

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
public class ChannelViewModel extends AbsViewModel< ChannelRepository > {

    public ChannelViewModel( @NonNull Application application ) {
        super( application );
    }

    public void requestChannel( String pos, int isAddInfo ) {
        addDisposable( mRepository.requestChannel( pos )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
                .subscribeWith( new RxSubscriber< ChannelOperate >() {
                    @Override
                    protected void showLoading() {
                        showLoadingLayout( ChannelFragment.class.getSimpleName(), "" );
                    }

                    @Override
                    protected void finishLoading() {
                        stopLoading( ChannelFragment.class.getSimpleName() );
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
                        showErrorLayout( ChannelFragment.class.getSimpleName() );
                    }
                } ) );
    }
}
