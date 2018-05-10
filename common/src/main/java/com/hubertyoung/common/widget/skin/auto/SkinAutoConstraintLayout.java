package com.hubertyoung.common.widget.skin.auto;

import android.content.Context;
import android.util.AttributeSet;

import com.zhy.autolayout.AutoConstraintLayout;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

/**
 * Created by pengfengwang on 2017/3/15.
 */

public class SkinAutoConstraintLayout extends AutoConstraintLayout implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinAutoConstraintLayout( Context context) {
        this(context, null);
    }

    public SkinAutoConstraintLayout( Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinAutoConstraintLayout( Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
    }

//    public SkinAutoConstraintLayout( Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
//        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
//    }

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
