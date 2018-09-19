package com.hubertyoung.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/19 10:47
 * @since:V$VERSION
 * @desc:com.hubertyoung.base.annotation
 */
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.CLASS )
public @interface Module {
	String alias() default "";
}
