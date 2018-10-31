package com.hubertyoung.component.acfunvideo.index.presenter;


import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.utils.Utils;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.component.acfunvideo.entity.Regions;
import com.hubertyoung.component.acfunvideo.index.control.NewRecommendControl;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * <br>
 * function:首页Presenter
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 11:19 AM
 * @since:V1.0
 * @desc:com.hubertyoung.component.home.index.presenter
 */
public class NewRecommendPresenterImp extends NewRecommendControl.Presenter {
	@Override
	public void requestRecommend( MyRequestMap map ) {
		mView.showLoading( "Loading...", 0 );
		mRxManage.add( mModel.requestRecommend( map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribe( new Consumer< List< Regions > >() {
					@Override
					public void accept( @NonNull List< Regions > regionsList ) throws Exception {
						mView.stopLoading();
						mView.refreshViewInfo( 0 );
						for (Regions regions : regionsList) {
							switch ( regions.schema ) {
								case Utils.carousels:
									mView.showNewRecommendCarouselsSection( regions );
									break;
								case Utils.banners:
									mView.showNewRecommendBannersSection( regions );
									break;
								case Utils.videos:
								case Utils.videos_new:
									mView.showNewRecommendVideosSection( regions );
									break;
								case Utils.videos_rank:
									mView.showNewRecommendVideosRankSection( regions );
									break;
								case Utils.bangumis:
									mView.showNewRecommendBangumisSection( regions );
									break;
								case Utils.articles:
									mView.showArticlesRecommendSection( regions );
									break;
								case Utils.articles_rank:
									mView.showArticlesRankRecommendSection( regions );
									break;
								case Utils.articles_new:
									mView.showArticlesNewRecommendSection( regions );
									break;
							}
						}
						mView.refreshViewInfo( 1 );
					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( @NonNull Throwable throwable ) throws Exception {
						mView.stopLoading();
						mView.showErrorTip( throwable.getMessage().toString() );
					}
				} ) );
	}

	public void requestNewRecommend( String channelId, MyRequestMap map ) {
		mView.showLoading( "Loading...", 1 );
		mRxManage.add( mModel.requestNewRecommend( channelId, map )
//				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribe( new Consumer< List< RegionBodyContent > >() {

					@Override
					public void accept( @NonNull List< RegionBodyContent > regionsList ) throws Exception {
						mView.stopLoading();
						mView.addNewRecommendInfo( regionsList );

					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( @NonNull Throwable throwable ) throws Exception {
						mView.stopLoading();
						mView.showErrorTip( throwable.getMessage().toString() );
					}
				} ) );
	}
}
