package com.kento.common.widget.skin.auto;

import android.content.Context;
import android.util.AttributeSet;

import com.zhy.autolayout.AutoAppBarLayout;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

/**
 * Created by pengfengwang on 2017/3/15.
 */

public class SkinAutoAppBarLayout extends AutoAppBarLayout implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinAutoAppBarLayout( Context context) {
        this(context, null);
    }

    public SkinAutoAppBarLayout( Context context, AttributeSet attrs) {
        super(context, attrs);
//        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
//        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
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
