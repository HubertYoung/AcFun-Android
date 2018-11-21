package com.hubertyoung.component_dynamicsoreview.dynamicsoreview.Interface;

import android.view.View;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:Yang
 * @date:2018/2/6 下午4:57
 * @since:V1.0
 * @desc:com.hubertyoung.common.commonwidget.dynamicsoreview.Interface
 */
public interface IDynamicSore<T> {
    void setGridView( View view, int type, List< T > data );
}
