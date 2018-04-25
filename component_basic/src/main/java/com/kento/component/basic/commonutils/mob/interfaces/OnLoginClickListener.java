package com.kento.component.basic.commonutils.mob.interfaces;

import java.util.Map;


/**
 * @author:Yang
 * @date:22/07/17 16:56
 * @since:v1.0
 * @desc:ddframework.gent.common.commonutils.mob.interfaces
 * @param: 登录回调
 */
public interface OnLoginClickListener {
    void onUserInfo( String platformName, Map< String, String > res );
}
