package com.hubertyoung.component_acfunmine.ui.mine.control;


import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.base.BaseView;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.entity.User;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/16 13:06
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunmine.mine.control
 */
public interface MineControl {

	interface Model extends BaseModel {
		Flowable< Boolean > requestCheckOfflineInfo( MyRequestMap map );

		Flowable< User > requestUserInfo( MyRequestMap map );

		Flowable< Sign > requestPlatformLogin( Map< String, String > map );
	}

	interface View extends BaseView {
		void setCheckOfflineInfo( Boolean b );

		void setUserInfo( User user );

		void setUserGroupInfo( boolean b );

		void setLoginState( boolean isLogin );

		void stopDialogLoading();

		void setPlatformLoginInfo( Sign sign );
	}

	abstract class Presenter extends BasePresenter< View, Model > {
		public abstract void requestUserInfo( MyRequestMap map );

		public abstract void requestCheckOfflineInfo( MyRequestMap map );

		public abstract void requestPlatformLogin( Map< String, String > hashMap );
	}
}
