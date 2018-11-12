package com.hubertyoung.common.data;

import java.util.ArrayList;
import java.util.Collection;

import androidx.annotation.NonNull;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/12 17:19
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.data
 */
public class ItemData extends ArrayList<Object> {
	public ItemData() {
	}

	public ItemData(int initialCapacity) {
		super(initialCapacity);
	}

	public ItemData(@NonNull Collection<?> c) {
		super(c);
	}
}
