package com.hubertyoung.component_acfunarticle.article.control;


import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.component_acfunarticle.entity.RankAc;

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
public interface ArticleGeneralSecondControl {

	interface Model extends BaseModel {
		Observable< RankAc > requestArticleGeneralSecond( MyRequestMap map );
	}

	interface View extends BaseView {
		void setArticleGeneralSecondInfo( RankAc rankAc );
	}

	abstract class Presenter extends BasePresenter< View, Model > {
	}
}
