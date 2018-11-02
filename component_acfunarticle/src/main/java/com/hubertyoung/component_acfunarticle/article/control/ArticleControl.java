package com.hubertyoung.component_acfunarticle.article.control;


import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.component_acfunarticle.entity.Channel;

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
public interface ArticleControl {

	interface Model extends BaseModel {
		Flowable< Channel > requestAllChannel( MyRequestMap map );
	}

	interface View extends BaseView {
		void setAllChannelInfo( Channel channel );
	}

	abstract class Presenter extends BasePresenter< View, Model > {
	}
}
