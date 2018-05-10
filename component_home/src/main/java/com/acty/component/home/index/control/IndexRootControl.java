package com.acty.component.home.index.control;


import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.acty.component.home.entity.HomeIndexEntity;

import io.reactivex.Observable;

/**
 * <br>
 * function:首页Control
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 11:17 AM
 * @since:V1.0
 * @desc:com.kento.component.home.index.control
 */
public interface IndexRootControl {

	interface Model extends BaseModel {
		Observable< HomeIndexEntity > requestHomeIndex( MyRequestMap map );
	}

	interface View extends BaseView {
		void setHomeIndexInfo( HomeIndexEntity homeIndexEntity );
	}

	abstract class Presenter extends BasePresenter< View, Model > {
		public abstract void requestHomeIndex( MyRequestMap map );
	}
}
