package com.kento.common.baserx.event;

/**
 * @author:Yang
 * @date:2017/11/30 10:32
 * @since:V1.0
 * @desc:ddframework.gent.common.baserx.event
 * @param:事件管理，可定制事件发送方式，可替换成EventBus。
 */
public class BusManager {
    private static IBus innerBus;
    private static IBus externalBus;

    public static void setBus(IBus bus) {
        if (externalBus == null && bus != null) {
            externalBus = bus;
        }
    }

    public static IBus getInstance() {
        if (innerBus == null) {
            synchronized (BusManager.class) {
                if (innerBus == null) {
                    if (externalBus != null) {
                        innerBus = externalBus;
                    } else {
                        innerBus = new RxBusImpl();
                    }
                }
            }
        }
        return innerBus;
    }
}
