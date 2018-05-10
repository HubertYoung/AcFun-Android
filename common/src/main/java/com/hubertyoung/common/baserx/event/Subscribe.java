package com.hubertyoung.common.baserx.event;


import com.hubertyoung.common.baserx.event.inner.ThreadMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author:Yang
 * @date:2017/11/30 10:32
 * @since:V1.0
 * @desc:com.kento.common.baserx.event
 * @param:接收事件注解，必须在接收事件地方定制该注解
 */
@Retention( RetentionPolicy.RUNTIME)
@Target( ElementType.METHOD)
public @interface Subscribe {
    ThreadMode threadMode() default ThreadMode.MAIN_THREAD;
}
