package com.kento.common.widget.skin.auto;

import android.content.Context;
import android.util.AttributeSet;

import com.zhy.autolayout.AutoNestedScrollView;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

/**
 */

public class SkinAutoNestedScrollView extends AutoNestedScrollView implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinAutoNestedScrollView( Context context) {
        this(context, null);
    }

    public SkinAutoNestedScrollView( Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinAutoNestedScrollView( Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }
}
