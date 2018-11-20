package com.hubertyoung.component_acfundynamic.dynamic.vm;

import android.app.Application;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_acfundynamic.config.DynamicConstants;
import com.hubertyoung.component_acfundynamic.dynamic.fragment.DynamicFollowBangumiFragment;
import com.hubertyoung.component_acfundynamic.dynamic.source.DynamicFollwBangumiRepository;
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
public class DynamicFollowBangumiViewModel extends AbsViewModel< DynamicFollwBangumiRepository > {

    public DynamicFollowBangumiViewModel( @NonNull Application application ) {
        super( application );
    }

    public void requestRecommendBangumi( String pageNo, String pageSize ) {
        addDisposable( mRepository.requestRecommendBangumi( pageNo, pageSize )
                .subscribeWith( new RxSubscriber< RecommendBangumiEntity >() {
                    @Override
                    protected void showLoading() {
                        showLoadingLayout( DynamicFollowBangumiFragment.class.getSimpleName(), "" );
                    }

                    @Override
                    protected void finishLoading() {
                        stopLoading( DynamicFollowBangumiFragment.class.getSimpleName() );
                    }

                    @Override
                    public void onSuccess( RecommendBangumiEntity recommendBangumiEntity ) {
                        sendData( DynamicConstants.EVENT_KEY_DYNAMIC_FOLLOW_BANGUMI, recommendBangumiEntity );
                    }

                    @Override
                    public void onFailure( String msg ) {
                        ToastUtil.showError( msg );

                    }
                } ) );
    }
}
