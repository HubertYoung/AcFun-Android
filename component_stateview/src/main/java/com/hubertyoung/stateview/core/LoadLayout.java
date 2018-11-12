package com.hubertyoung.stateview.core;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.hubertyoung.stateview.stateview.BaseStateControl;
import com.hubertyoung.stateview.stateview.SuccessState;
import com.hubertyoung.stateview.util.LoadUtil;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;


public class LoadLayout extends FrameLayout {
    private Map<Class<? extends BaseStateControl >, BaseStateControl> stateViews = new HashMap<>();
    private Context context;
    private BaseStateControl.OnRefreshListener onRefreshListener;
    private Class<? extends BaseStateControl> preStateView;
    private Class<? extends BaseStateControl> curStateView;

    private static final int STATEVIEW_CUSTOM_INDEX = 1;

    public LoadLayout(@NonNull Context context) {
        super(context);
    }

    public LoadLayout(@NonNull Context context, BaseStateControl.OnRefreshListener onRefreshListener) {
        this(context);
        this.context = context;
        this.onRefreshListener = onRefreshListener;
    }

    public void setSuccessLayout(BaseStateControl baseStateControl) {
        addStateView(baseStateControl);
        View successView = baseStateControl.getRootView(null);
        successView.setVisibility(View.GONE);
        addView(successView);
        curStateView = SuccessState.class;
    }

    public void setStateView(BaseStateControl stateview) {
        BaseStateControl cloneStateView = stateview.copy();
        cloneStateView.setStateView(null, context, onRefreshListener);
        addStateView(cloneStateView);
    }

    public void addStateView(BaseStateControl stateview) {
        if (!stateViews.containsKey(stateview.getClass())) {
            stateViews.put(stateview.getClass(), stateview);
        }
    }

    public void showStateView(final Class<? extends BaseStateControl> curStateView) {
        showStateView(curStateView, null);
    }

    public void showStateView(final Class<? extends BaseStateControl> curStateView, Object tag) {
        checkStateViewExist(curStateView);
        if (LoadUtil.isMainThread()) {
            showStateViewView(curStateView, tag);
        } else {
            postMainThread(curStateView, tag);
        }
    }

    public Class<? extends BaseStateControl> getCurrentStateView() {
        return curStateView;
    }

    private void postMainThread(final Class<? extends BaseStateControl> status, final Object tag) {
        post(new Runnable() {
            @Override
            public void run() {
                showStateViewView(status, tag);
            }
        });
    }

    private void showStateViewView(Class<? extends BaseStateControl> status, Object tag) {
        if (preStateView != null) {
            if (preStateView == status) {
                return;
            }
            stateViews.get(preStateView).onDetach();
        }
        if (getChildCount() > 1) {
            removeViewAt(STATEVIEW_CUSTOM_INDEX);
        }

        for (Class key : stateViews.keySet()) {
            if (key == status) {
                SuccessState successView = (SuccessState) stateViews.get(SuccessState.class);
                if (key == SuccessState.class) {
                    successView.show();
                } else {
                    successView.showWithStateView(stateViews.get(key).isVisible());
                    View rootView = stateViews.get(key).getRootView(tag);
                    addView(rootView);
                    stateViews.get(key).onAttach(context, rootView);
                }
                preStateView = status;
            }
        }
        curStateView = status;
    }
    private void checkStateViewExist(Class<? extends BaseStateControl> stateView) {
        if (!stateViews.containsKey(stateView)) {
            throw new IllegalArgumentException(String.format("The BaseStateControl (%s) is nonexistent.", stateView
                    .getSimpleName()));
        }
    }
}
