package com.hubertyoung.component_acfundynamic.dynamic.vm;

import android.app.Application;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_acfundynamic.config.DynamicConstants;
import com.hubertyoung.component_acfundynamic.dynamic.source.DynamicAcfunRepository;
import com.hubertyoung.component_acfundynamic.entity.RecommendBangumiEntity;

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
public class DynamicAcfunViewModel extends AbsViewModel< DynamicAcfunRepository > {

    public DynamicAcfunViewModel( @NonNull Application application ) {
        super( application );
    }

    public void requestRecommendUp( String pageNo, String pageSize ) {
        addDisposable( mRepository.requestRecommendUp( pageNo, pageSize )
                .subscribeWith( new RxSubscriber< RecommendBangumiEntity >() {
                    @Override
                    protected void showLoading() {
                        showDialogLoading( "" );
                    }

                    @Override
                    protected void finishLoading() {
                        stopLoading();
                    }

                    @Override
                    public void onSuccess( RecommendBangumiEntity recommendBangumiEntity ) {
                        sendData( DynamicConstants.EVENT_KEY_DYNAMIC_RECOMMENDUP, recommendBangumiEntity );
                    }

                    @Override
                    public void onFailure( String msg ) {
                        ToastUtil.showError( msg );

                    }
                } ) );
    }
}
