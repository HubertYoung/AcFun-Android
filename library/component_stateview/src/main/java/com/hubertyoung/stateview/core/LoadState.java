package com.hubertyoung.stateview.core;


import com.hubertyoung.stateview.stateview.BaseStateControl;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;


/**
 */
public class LoadState {

    private static volatile LoadState loadState;

    private Builder builder;

    public static LoadState newInstance() {
        if (loadState == null) {
            synchronized (LoadState.class) {
                if (loadState == null) {
                    loadState = new LoadState();
                }
            }
        }
        return loadState;
    }

    public Builder getBuilder() {
        return builder;
    }

    private LoadState() {
        this.builder = new Builder();
    }

    public void setBuilder(@NonNull Builder builder) {
        this.builder = builder;
    }

    public static class Builder {

        private List<BaseStateControl > stateViews = new ArrayList<>();

        private Class<? extends BaseStateControl> defaultStateView;

        /**
         * 添加不同状态页面
         *
         * @param stateView
         * @return
         */
        public Builder register(@NonNull BaseStateControl stateView) {
            stateViews.add(stateView);
            return this;
        }

        /**
         * 设置默认现实状态UI
         *
         * @param defaultStateView
         * @return
         */
        public Builder setDefaultStateView(@NonNull Class<? extends BaseStateControl> defaultStateView) {
            this.defaultStateView = defaultStateView;
            return this;
        }

        List<BaseStateControl> getStateViews() {
            return stateViews;
        }

        Class<? extends BaseStateControl> getDefaultStateView() {
            return defaultStateView;
        }

        public void build() {
            newInstance().setBuilder(this);
        }


    }
}
