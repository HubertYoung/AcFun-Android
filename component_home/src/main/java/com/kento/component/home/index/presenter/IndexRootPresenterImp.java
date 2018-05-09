package com.kento.component.home.index.presenter;


import com.kento.common.base.BaseActivity;
import com.kento.common.basebean.MyRequestMap;
import com.kento.component.home.entity.HomeIndexEntity;
import com.kento.component.home.index.control.IndexRootControl;

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
