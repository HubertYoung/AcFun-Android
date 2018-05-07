package com.kento.common.baserx.event.inner;


import com.kento.common.baserx.event.IEvent;

/**
 * @author:Yang
 * @date:2017/11/30 10:47
 * @since:v1.0
 * @desc:com.kento.common.baserx.event.inner
 * @param:
 */
public class EventBean implements IEvent {
	Object tag;
	Object content;

	public EventBean( Object tag, Object content ) {
		this.tag = tag;
		this.content = content;
	}
}
