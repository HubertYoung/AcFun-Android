package com.hubertyoung.component.acfunvideo.index.source;

import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.data.BaseRepository;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.api.ApiHomeService;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.entity.Regions;
import com.hubertyoung.component.acfunvideo.entity.RegionsEntity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/15 15:36
 * @since:V5.2.7
 * @desc:com.hubertyoung.component.acfunvideo.index.source
 */
public class NewRecommendRepository extends BaseRepository {
	public void requestRecommend( String channelId ) {
		HashMap map = new HashMap< String, String >();
		map.put( "channelId", channelId );
		addDisposable( ( Disposable ) Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestRecommend( map )
				.compose( new DefaultTransformer() )
				.subscribeWith( new RxSubscriber< List< Regions > >() {
					@Override
					protected void showLoading() {
						showDialogLoading( "" );
					}

					@Override
					protected void finishLoading() {
						stopLoading();
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
		HashMap map = new HashMap<String,String>();
		map.put( "pageNo",pageNo );
		addDisposable( ( Disposable ) Api.getDefault( HostType.MY_RESULT )
				.getRetrofitClient()
				.builder( ApiHomeService.class )
				.requestNewRecommend(channelId, map )
				.compose( new DefaultTransformer() )
				.subscribeWith( new RxSubscriber< List< RegionBodyContent > >() {
					@Override
					protected void showLoading() {
						showDialogLoading( "" );
					}

					@Override
					protected void finishLoading() {
						stopLoading();
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
