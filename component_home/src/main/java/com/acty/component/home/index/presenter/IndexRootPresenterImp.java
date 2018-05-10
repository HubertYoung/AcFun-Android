package com.acty.component.home.index.presenter;


import com.acty.component.home.entity.HomeIndexEntity;
import com.acty.component.home.index.control.IndexRootControl;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.basebean.MyRequestMap;

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
 * @desc:com.kento.component.home.index.presenter
 */
public class IndexRootPresenterImp extends IndexRootControl.Presenter {
	@Override
	public void requestHomeIndex( MyRequestMap map ) {
		mView.showLoading( "Loading...", 0 );
		mRxManage.add( mModel.requestHomeIndex( map )
				.compose( ( ( BaseActivity ) mContext ).bindToLifecycle() )
				.subscribe( new Consumer< HomeIndexEntity >() {

					@Override
					public void accept( @NonNull HomeIndexEntity homeIndexEntity ) throws Exception {
						mView.stopLoading();
						mView.setHomeIndexInfo( homeIndexEntity );

					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( @NonNull Throwable throwable ) throws Exception {
						mView.stopLoading();
						mView.showErrorTip( throwable.getMessage()
								.toString() );
					}
				} ) );
	}
}
