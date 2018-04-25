package com.kento.component.basic.commonutils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author:Yang
 * @date:2017/8/2 16:00
 * @since:v1.0
 * @desc:CloseUtils.java
 * @param:关闭相关工具类
 */
public final class CloseUtils {

	private CloseUtils() {
		throw new UnsupportedOperationException( "u can't instantiate me..." );
	}

	/**
	 * 关闭IO
	 *
	 * @param closeables closeables
	 */
	public static void closeIO( final Closeable... closeables ) {
		if ( closeables == null ) return;
		for (Closeable closeable : closeables) {
			if ( closeable != null ) {
				try {
					closeable.close();
				} catch ( IOException e ) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 安静关闭IO
	 *
	 * @param closeables closeables
	 */
	public static void closeIOQuietly( final Closeable... closeables ) {
		if ( closeables == null ) return;
		for (Closeable closeable : closeables) {
			if ( closeable != null ) {
				try {
					closeable.close();
				} catch ( IOException ignored ) {
				}
			}
		}
	}
}
