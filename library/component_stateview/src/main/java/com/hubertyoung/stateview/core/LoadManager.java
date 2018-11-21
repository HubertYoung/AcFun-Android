package com.hubertyoung.stateview.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hubertyoung.stateview.stateview.BaseStateControl;
import com.hubertyoung.stateview.stateview.SuccessState;
import com.hubertyoung.stateview.util.LoadUtil;

import java.util.List;

/**
 * @author zhangtianqiu
 */
public class LoadManager {

    private LoadLayout loadLayout;

    public static class Builder {

        private StateViewParams stateViewParams;

        private BaseStateControl.OnRefreshListener onRefreshListener;

        public Builder setListener(BaseStateControl.OnRefreshListener listener) {
            this.onRefreshListener = listener;
            return this;
        }

        public Builder setViewParams(Object stateView) {
            this.stateViewParams = LoadUtil.getViewParams(stateView);
            return this;
        }

        public LoadManager build() {
            return new LoadManager(stateViewParams, onRefreshListener, LoadState.newInstance().getBuilder());
        }

    }


    public LoadManager(StateViewParams stateViewParams, BaseStateControl.OnRefreshListener onRefreshListener,
                       LoadState.Builder builder) {

        Context context = stateViewParams.context;
        View rootView = stateViewParams.rootView;
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        loadLayout = new LoadLayout(context, onRefreshListener);
        loadLayout.setSuccessLayout(new SuccessState(rootView, context,
                onRefreshListener));
        if (stateViewParams.parentView != null) {
            stateViewParams.parentView.addView(loadLayout, stateViewParams.childIndex, layoutParams);
        }
        initStateViews(builder);
    }

    private void initStateViews( LoadState.Builder builder) {
        if (builder == null) {
            throw new IllegalArgumentException("The builder must be  set stateview");
        }
        /**
         * 获取储存的所有view
         */
        List<BaseStateControl> stateViews = builder.getStateViews();

        /**
         * 获取默认view
         */
        Class<? extends BaseStateControl> defalutStateView = builder.getDefaultStateView();
        if (stateViews != null && stateViews.size() > 0) {
            for (BaseStateControl stateView : stateViews) {
                loadLayout.setStateView(stateView);
            }
        }
        if (defalutStateView != null) {
            loadLayout.showStateView(defalutStateView);
        }
    }

    public void showSuccess() {
        loadLayout.showStateView(SuccessState.class);
    }

    public void showStateView(Class<? extends BaseStateControl> stateView) {
        loadLayout.showStateView(stateView);
    }

    public void showStateView(Class<? extends BaseStateControl> stateView, Object tag) {
        loadLayout.showStateView(stateView, tag);
    }

    public LoadLayout getLoadLayout() {
        return loadLayout;
    }

    public Class<? extends BaseStateControl> getCurrentStateView() {
        return loadLayout.getCurrentStateView();
    }

    public static class StateViewParams {
        public Context context;
        public ViewGroup parentView;
        public View rootView;
        public int childIndex;

        public StateViewParams(Context context, ViewGroup parentView, View rootView, int childIndex) {
            this.context = context;
            this.parentView = parentView;
            this.rootView = rootView;
            this.childIndex = childIndex;
        }
    }

}
