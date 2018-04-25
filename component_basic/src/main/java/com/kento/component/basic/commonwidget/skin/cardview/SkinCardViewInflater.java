package com.kento.component.basic.commonwidget.skin.cardview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import skin.support.app.SkinLayoutInflater;

/**
 * Created by ximsf on 2017/3/5.
 */

public class SkinCardViewInflater implements SkinLayoutInflater {
    @Override
    public View createView( @NonNull Context context, final String name, @NonNull AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "android.support.v7.widget.CardView":
                view = new SkinCompatCardView(context, attrs);
                break;
        }
        return view;
    }
}
