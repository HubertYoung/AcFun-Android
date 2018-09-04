package com.acty.component.acfunvideo.index.control;


import com.acty.component.acfunvideo.entity.Regions;
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
		Observable< List<Regions > > requestNewRecommend( MyRequestMap map );
	}

	interface View extends BaseView {
		void setNewRecommendInfo( List<Regions > searchBangumiList );
	}

	abstract class Presenter extends BasePresenter< View, Model > {
		public abstract void requestNewRecommend( MyRequestMap map );
	}
}
