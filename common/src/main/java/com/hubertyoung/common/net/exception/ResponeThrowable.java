package com.hubertyoung.common.net.exception;

/**
 * 作者：JIUU on 2017-7-10 16:00:51
 * QQ号：1344393464
 * 作用：
 */

public class ResponeThrowable extends Exception {
    public String status;
    public String result;

    public ResponeThrowable(Throwable throwable, String status) {
        super(throwable);
        this.status = status;
    }

    @Override
    public String toString() {
        return result;
    }
}
