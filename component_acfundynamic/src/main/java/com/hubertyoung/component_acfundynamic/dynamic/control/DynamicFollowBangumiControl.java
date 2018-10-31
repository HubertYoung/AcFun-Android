package com.hubertyoung.component_acfundynamic.dynamic.control;


import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.entity.RegionBodyContent;
import com.hubertyoung.component_acfundynamic.entity.RecommendBangumiEntity;

import java.util.List;

import io.reactivex.Observable;

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
public interface DynamicFollowBangumiControl {

	interface Model extends BaseModel {
		Observable< RecommendBangumiEntity > requestRecommendBangumi( MyRequestMap map );
	}

	interface View extends BaseView {
		void setRecommendBangumiInfo( List<RegionBodyContent> regionBodyContentList );
	}

	abstract class Presenter extends BasePresenter< View, Model > {
		public abstract void requestRecommendBangumi( MyRequestMap map );
	}
}
