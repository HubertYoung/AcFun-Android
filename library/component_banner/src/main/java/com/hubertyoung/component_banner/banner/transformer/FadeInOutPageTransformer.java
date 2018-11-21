package com.hubertyoung.component_banner.banner.transformer;

import android.annotation.SuppressLint;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

/**
 * <br>
 * function:页面切换动画，只支持3.0以上版本
 * <p>
 *
 * @author:Yang
 * @date:2018/2/7 下午1:48
 * @since:V1.0
 * @desc:com.hubertyoung.common.commonwidget.banner.transformer
 */
public class FadeInOutPageTransformer implements ViewPager.PageTransformer {

    @SuppressLint("NewApi")
    @Override
    public void transformPage( View page, float position) {
        if (position < -1) {//页码完全不可见
            page.setAlpha(0);
        } else if (position < 0) {
            page.setAlpha(1 + position);
        } else if (position < 1) {
            page.setAlpha(1 - position);
        } else {
            page.setAlpha(0);
        }
    }
}
