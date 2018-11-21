package com.hubertyoung.common.base;

import android.os.Bundle;
import android.text.TextUtils;

import com.hubertyoung.common.stateview.StateConstants;
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


	protected Observer observer = new Observer< String >() {
		@Override
		public void onChanged( @Nullable String state ) {
			if ( !TextUtils.isEmpty( state ) ) {
				if ( StateConstants.ERROR_STATE.equals( state ) ) {

				} else if ( StateConstants.NET_WORK_STATE.equals( state ) ) {
				} else if ( StateConstants.LOADING_STATE.equals( state ) ) {
					showLoading( "" );
				} else if ( StateConstants.SUCCESS_STATE.equals( state ) ) {
					stopLoading();
				}
			}
		}
	};
}
