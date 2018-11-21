package com.hubertyoung.common.image.fresco;

import com.facebook.common.util.ByteConstants;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 13:51
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.image.fresco
 */
public class ConfigConstants {
	public static final int MAX_DISK_CACHE_SIZE = 40 * ByteConstants.MB;
	private static final int MAX_HEAP_SIZE = ( int ) Runtime.getRuntime().maxMemory();
	public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;
}
