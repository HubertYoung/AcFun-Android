package com.kento.component.basic.commonwidget.skin.auto;

import android.content.Context;
import android.util.AttributeSet;

import com.zhy.autolayout.widget.AutoCardView;

import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

/**
 * Created by pengfengwang on 2017/3/15.
 */

public class SkinAutoCardView extends AutoCardView implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinAutoCardView( Context context) {
        this(context, null);
    }

    public SkinAutoCardView( Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinAutoCardView( Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
    }

//    public SkinAutoCardView( Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
