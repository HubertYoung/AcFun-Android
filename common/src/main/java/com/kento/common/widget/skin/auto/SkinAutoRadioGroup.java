package com.kento.common.widget.skin.auto;

import android.content.Context;
import android.util.AttributeSet;

import com.zhy.autolayout.widget.AutoRadioGroup;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

/**
 */

public class SkinAutoRadioGroup extends AutoRadioGroup implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinAutoRadioGroup( Context context) {
        this(context, null);
    }

    public SkinAutoRadioGroup( Context context, AttributeSet attrs) {
        super(context, attrs);
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
