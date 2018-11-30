package com.hubertyoung.common.baserx.event;

/**
 * @author:Yang
 * @date:2017/11/30 10:33
 * @since:V1.0
 * @desc:com.hubertyoung.common.baserx.event
 * @param:事件总线接口
 */
public interface IBus {
    void register( Object object );

    void unregister( Object object );

    void post( IEvent event );

    void postSticky( IEvent event );
}
