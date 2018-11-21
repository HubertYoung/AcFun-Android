package com.hubertyoung.dialog.listener;

import android.view.View;

import com.hubertyoung.dialog.TDialog;
import com.hubertyoung.dialog.base.BindViewHolder;


public interface OnViewClickListener {
    void onViewClick( BindViewHolder viewHolder, View view, TDialog tDialog );
}
