package com.hubertyoung.common.base;

import android.os.Bundle;
import android.text.TextUtils;

import com.hubertyoung.common.stateview.ErrorState;
import com.hubertyoung.common.stateview.LoadingState;
import com.hubertyoung.common.stateview.StateConstants;
import com.hubertyoung.common.utils.TUtil;
import com.hubertyoung.stateview.stateview.BaseStateControl;

import javax.annotation.Nullable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * AbsLifecycleActivity
 */
public abstract class AbsLifecycleActivity<T extends AbsViewModel> extends BaseActivity {

    protected T mViewModel;

    public AbsLifecycleActivity() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mViewModel = VMProviders(this, TUtil.getInstance(this, 0));
        dataObserver();
    }


    protected <T extends ViewModel > T VMProviders( AppCompatActivity fragment, @NonNull Class modelClass) {
        return (T) ViewModelProviders.of(fragment).get(modelClass);

    }

    protected void dataObserver() {

    }
	@Override
    protected void onStateRefresh() {
        showLoading();
    }

    protected void showError( Class<? extends BaseStateControl > stateView, Object tag) {
        loadManager.showStateView(stateView, tag);
    }

    protected void showError(Class<? extends BaseStateControl> stateView) {
        showError(stateView, null);
    }

    protected void showSuccess() {
        loadManager.showSuccess();
    }

    protected void showLoading() {
        loadManager.showStateView(LoadingState.class);
    }


    protected Observer observer = new Observer<String >() {
        @Override
        public void onChanged(@Nullable String state) {
            if (!TextUtils.isEmpty(state)) {
				switch ( state ) {
					case StateConstants.ERROR_STATE:
					case StateConstants.NET_WORK_STATE:
						showError(ErrorState.class, state);
					    break;
					case StateConstants.LOADING_STATE:
						showLoading();
						break;
					case StateConstants.SUCCESS_STATE:
						showSuccess();
						break;
				}
            }
        }
    };
}
