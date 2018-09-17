package com.hubertyoung.component.acfunvideo.index.control;


import com.hubertyoung.component.acfunvideo.entity.RegionBodyContent;
import com.hubertyoung.component.acfunvideo.entity.Regions;
import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;
import com.hubertyoung.common.basebean.MyRequestMap;

import java.util.List;

import io.reactivex.Observable;

/**
 * <br>
 * function:首页Control
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 11:17 AM
 * @since:V1.0
 * @desc:com.hubertyoung.component.home.index.control
 */
public interface NewRecommendControl {

	interface Model extends BaseModel {
		Observable< List< Regions > > requestRecommend( MyRequestMap map );

		Observable< List< RegionBodyContent > > requestNewRecommend( MyRequestMap map );
	}

	interface View extends BaseView {
		void setRecommendInfo( List< Regions > regionsList );

		void addNewRecommendInfo( List< RegionBodyContent > regionsList );
	}

	abstract class Presenter extends BasePresenter< View, Model > {
		public abstract void requestRecommend( MyRequestMap map );
	}
}
