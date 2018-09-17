package com.hubertyoung.component.acfunvideo.index.control;


import com.hubertyoung.component.acfunvideo.entity.ChannelOperate;
import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;
import com.hubertyoung.common.basebean.MyRequestMap;

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
public interface ChannelControl {

	interface Model extends BaseModel {
		Observable< ChannelOperate > requestChannel( MyRequestMap map );
	}

	interface View extends BaseView {
		void setChannelOperateInfo( ChannelOperate channelOperate );
	}

	abstract class Presenter extends BasePresenter< View, Model > {
		public abstract void requestChannel( MyRequestMap map );
	}
}
