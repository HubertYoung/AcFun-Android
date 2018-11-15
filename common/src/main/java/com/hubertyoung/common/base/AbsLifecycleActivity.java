package com.hubertyoung.common.base;

import android.os.Bundle;

import com.hubertyoung.common.baserx.LiveBus;
import com.hubertyoung.common.utils.TUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * AbsLifecycleActivity
 */
public abstract class AbsLifecycleActivity< VM extends AbsViewModel > extends BaseActivity {

	protected VM mViewModel;

	public AbsLifecycleActivity() {

	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		mViewModel = VMProviders( this, TUtil.getInstance( this, 0 ) );
		/**
		 * 私有的ViewModel与View的契约事件回调逻辑
		 */
		registorUIChangeLiveDataCallBack();
		dataObserver();
	}

	protected < T extends ViewModel > VM VMProviders( AppCompatActivity activity, @NonNull Class modelClass ) {
		if ( modelClass == null ) {
			return null;
		}
		return ( VM ) ViewModelProviders.of( activity ).get( modelClass );

	}

	protected void dataObserver() {

	}

	private void registorUIChangeLiveDataCallBack() {
		//加载对话框显示
		LiveBus.getDefault().getUC().getShowDialogEvent().observe( this, new Observer< String >() {
			@Override
			public void onChanged( @Nullable String title ) {
				showLoading( title );
			}
		} );
		//加载对话框消失
		LiveBus.getDefault().getUC().getDismissDialogEvent().observe( this, new Observer< Boolean >() {
			@Override
			public void onChanged( @Nullable Boolean aBoolean ) {
				stopLoading();
			}
		} );
	}

	protected void stopLoading() {

	}

	protected void showLoading( String title ) {

	}


//	@Override
//    protected void onStateRefresh() {
//        showLoading();
//    }

//    protected void showError( Class<? extends BaseStateControl > stateView, Object tag) {
//        loadManager.showStateView(stateView, tag);
//    }
//
//    protected void showError(Class<? extends BaseStateControl> stateView) {
//        showError(stateView, null);
//    }
//
//    protected void showSuccess() {
//        loadManager.showSuccess();
//    }
//
//    protected void showLoading() {
//        loadManager.showStateView(LoadingState.class);
//    }


//    protected Observer observer = new Observer<String >() {
//        @Override
//        public void onChanged(@Nullable String state) {
//            if (!TextUtils.isEmpty(state)) {
//				switch ( state ) {
//					case StateConstants.ERROR_STATE:
//					case StateConstants.NET_WORK_STATE:
//						showError(ErrorState.class, state);
//					    break;
//					case StateConstants.LOADING_STATE:
//						showLoading();
//						break;
//					case StateConstants.SUCCESS_STATE:
//						showSuccess();
//						break;
//				}
//            }
//        }
//    };
}
