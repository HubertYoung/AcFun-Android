package com.kento.component.basic.commonwidget.skin.auto;

import android.content.Context;
import android.util.AttributeSet;

import com.zhy.autolayout.AutoLinearLayoutCompat;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

public class SkinAutoLinearLayoutCompat extends AutoLinearLayoutCompat implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinAutoLinearLayoutCompat( Context context) {
        this(context, null);
    }

    public SkinAutoLinearLayoutCompat( Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinAutoLinearLayoutCompat( Context context, AttributeSet attrs, int defStyleAttr) {
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
