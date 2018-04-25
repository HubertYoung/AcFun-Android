package com.kento.component.basic.commonutils;

import com.facebook.stetho.common.LogUtil;

import java.lang.reflect.ParameterizedType;

/**
 * 类转换初始化
 */
public class TUtil {
	public static synchronized <T> T getT(Object o, int i) {
		try {
			return ((Class<T>) ((ParameterizedType) (o.getClass()
													  .getGenericSuperclass())).getActualTypeArguments()[i]).newInstance();
		} catch (Exception e) {
			LogUtil.e(e.getMessage());
		}
		return null;
	}

	public static Class<?> forName(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
