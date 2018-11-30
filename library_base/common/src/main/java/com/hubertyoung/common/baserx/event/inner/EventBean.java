package com.hubertyoung.common.baserx.event.inner;


import com.hubertyoung.common.baserx.event.IEvent;

/**
 * @author:Yang
 * @date:2017/11/30 10:47
 * @since:v1.0
 * @desc:com.hubertyoung.common.baserx.event.inner
 * @param:
 */
public class EventBean implements IEvent {
	Object tag;
	Object content;

	public EventBean( Object tag, Object content ) {
		this.tag = tag;
		this.content = content;
	}

	public Object getTag() {
		return tag;
	}

	public Object getContent() {
		return content;
	}
}
