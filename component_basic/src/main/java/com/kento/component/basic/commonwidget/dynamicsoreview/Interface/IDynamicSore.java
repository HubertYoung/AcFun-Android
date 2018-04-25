package com.kento.component.basic.commonwidget.dynamicsoreview.Interface;

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
 * @desc:ddframework.gent.common.commonwidget.dynamicsoreview.Interface
 */
public interface IDynamicSore<T> {
    void setGridView( View view, int type, List< T > data );
}
