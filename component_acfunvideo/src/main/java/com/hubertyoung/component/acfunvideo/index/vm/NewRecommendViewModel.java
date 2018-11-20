package com.hubertyoung.component.acfunvideo.index.vm;

import android.app.Application;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.entity.Regions;
import com.hubertyoung.component.acfunvideo.entity.RegionsEntity;
import com.hubertyoung.component.acfunvideo.index.fragment.NewRecommendFragment;
import com.hubertyoung.component.acfunvideo.index.source.NewRecommendRepository;

import java.util.List;

import androidx.annotation.NonNull;

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
public class NewRecommendViewModel extends AbsViewModel< NewRecommendRepository > {

	public NewRecommendViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestRecommend( String channelId ) {
		addDisposable( mRepository.requestRecommend( channelId )//
				.subscribeWith( new RxSubscriber< List< Regions > >() {
					@Override
					protected void showLoading() {
						showDialogLoading( NewRecommendFragment.class.getSimpleName(), "" );
					}

					@Override
					protected void finishLoading() {
						stopLoading( NewRecommendFragment.class.getSimpleName() );
					}

					@Override
					public void onSuccess( List< Regions > regionsList ) {
						sendData( VideoConstants.EVENT_KEY_NEW_RECOMMEND_STATUS, 0 );
						sendData( VideoConstants.EVENT_KEY_NEW_RECOMMEND_INFO, new RegionsEntity( regionsList ) );
						sendData( VideoConstants.EVENT_KEY_NEW_RECOMMEND_STATUS, 1 );
					}

					@Override
					public void onFailure( String msg ) {
						ToastUtil.showError( msg );
					}
				} ) );
	}

	public void requestNewRecommend( String channelId, String pageNo ) {
		addDisposable( mRepository.requestNewRecommend( channelId, pageNo )//
				.subscribeWith( new RxSubscriber< List< RegionBodyContent > >() {
					@Override
					protected void showLoading() {
						showDialogLoading( NewRecommendFragment.class.getSimpleName(), "" );
					}

					@Override
					protected void finishLoading() {
						stopLoading( NewRecommendFragment.class.getSimpleName() );
					}

					@Override
					public void onSuccess( List< RegionBodyContent > regionBodyContents ) {
						Regions regions = new Regions();
						regions.changeContents = regionBodyContents;
						sendData( VideoConstants.EVENT_KEY_NEW_RECOMMEND_ADD_INFO, regions );
					}

					@Override
					public void onFailure( String msg ) {
						ToastUtil.showError( msg );
					}
				} ) );
	}
}
