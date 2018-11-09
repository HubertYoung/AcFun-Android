package com.hubertyoung.common.base;

import android.os.Bundle;

import com.hubertyoung.common.utils.TUtil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
//
//    protected void onStateRefresh() {
//        showLoading();
//    }
//
//    protected void showError( Class<? extends BaseStateControl> stateView, Object tag) {
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
//                if (StateConstants.ERROR_STATE.equals(state)) {
//                    showError(ErrorState.class, "2");
//                } else if (StateConstants.NET_WORK_STATE.equals(state)) {
//                    showError(ErrorState.class, "1");
//                } else if (StateConstants.LOADING_STATE.equals(state)) {
//                    showLoading();
//                } else if (StateConstants.SUCCESS_STATE.equals(state)) {
//                    showSuccess();
//                }
//            }
//        }
//    };
}
