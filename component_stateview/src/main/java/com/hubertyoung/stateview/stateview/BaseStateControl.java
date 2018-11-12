package com.hubertyoung.stateview.stateview;

import android.content.Context;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 */
public abstract class BaseStateControl implements Serializable {
    private View rootView;

    private Context context;
    /**
     * 刷新监听
     */
    private OnRefreshListener onRefreshListener;
    private boolean isVisible;

    public BaseStateControl() {
    }

    BaseStateControl(View view, Context context, OnRefreshListener onRefreshListener) {
        this.rootView = view;
        this.context = context;
        this.onRefreshListener = onRefreshListener;
    }

    public BaseStateControl setStateView(View view, Context context, OnRefreshListener onRefreshListener) {
        this.rootView = view;
        this.context = context;
        this.onRefreshListener = onRefreshListener;
        return this;
    }

    public View getRootView(Object tag) {
        int resId = onCreateView();
        if (resId == 0 && rootView != null) {
            return rootView;
        }

        if (onBuildView(context) != null) {
            rootView = onBuildView(context);
        }

        if (rootView == null) {
            rootView = View.inflate(context, onCreateView(), null);
        }
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadEvent(context, rootView)) {
                    return;
                }
                onClickRefresh( v );
            }
        });

        if (tag != null) {
            rootView.setTag(null);
            rootView.setTag(tag);
        }

        onViewCreate(context, rootView);
        return rootView;
    }

    protected void onClickRefresh( View v ) {
        if (onRefreshListener != null) {
            onRefreshListener.onRefresh(v);
        }
    }

    protected View onBuildView(Context context) {
        return null;
    }

    public boolean isVisible() {
        return isVisible;
    }

    void isVisible(boolean visible) {
        this.isVisible = visible;
    }

    protected boolean onRetry(Context context, View view) {
        return false;
    }

    protected boolean onReloadEvent(Context context, View view) {
        return false;
    }

    public BaseStateControl copy() {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        Object obj = null;
        try {
            oos = new ObjectOutputStream(bao);
            oos.writeObject(this);
            oos.close();
            ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (BaseStateControl) obj;
    }

    public View getRootView() {
        if (rootView == null) {
            rootView = View.inflate(context, onCreateView(), null);
        }
        return rootView;
    }

    public interface OnRefreshListener extends Serializable {
        void onRefresh(View v);
    }

    protected abstract int onCreateView();

    protected void onViewCreate(Context context, View view) {
    }

    public void onAttach(Context context, View view) {
    }

    public void onDetach() {
    }

}
