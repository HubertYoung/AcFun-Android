package com.hubertyoung.component_acfundynamic.dynamic.control;


import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.component_acfundynamic.entity.RecommendBangumiEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/16 17:17
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunarticle.mine.control
 */
public interface DynamicAcfunControl {

	interface Model extends BaseModel {
		Flowable< RecommendBangumiEntity > requestRecommendUp( MyRequestMap map );
	}

	interface View extends BaseView {
		void setRecommendUpInfo( List< RegionBodyContent > regionBodyContentList );
	}

	abstract class Presenter extends BasePresenter< View, Model > {
		public abstract void requestRecommendUp( MyRequestMap map );
	}
}
