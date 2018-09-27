package com.hubertyoung.component_filter.filter.util;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 */
public class UIUtil {

    public static int dp( Context context, int dp) {
        return (int) ( TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics()) + 0.5F);
    }


    public static View infalte( Context context, @LayoutRes int layoutId, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(layoutId, parent, false);

    }


}
