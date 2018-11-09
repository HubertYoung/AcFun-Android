package com.hubertyoung.common.utils;

import java.lang.reflect.ParameterizedType;

import androidx.annotation.NonNull;

/**
 * <br>
 * function:类转换初始化
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/9 17:00
 * @since:V1.0.0
 * @desc:com.hubertyoung.common.utils
 */
public class TUtil {
	public static synchronized < T > T getNewInstance( Object o, int i ) {
		try {
			return ( ( Class< T > ) ( ( ParameterizedType ) ( o.getClass().getGenericSuperclass() ) ).getActualTypeArguments()[ i ] ).newInstance();
		} catch ( InstantiationException e ) {
			e.printStackTrace();
		} catch ( IllegalAccessException e ) {
			e.printStackTrace();
		} catch ( ClassCastException e ) {
			e.printStackTrace();
		}
		return null;
	}

	public static < T > T getInstance( Object object, int i ) {
		if ( object != null ) {
			return ( T ) ( ( ParameterizedType ) object.getClass().getGenericSuperclass() ).getActualTypeArguments()[ i ];
		}
		return null;
	}

	public static @NonNull
	< T > T checkNotNull( final T reference ) {
		if ( reference == null ) {
			throw new NullPointerException();
		}
		return reference;
	}
}
