package com.hubertyoung.baseplatform.error;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/14 11:00
 * @since:V1.0.0
 * @desc:com.hubertyoung.baseplatform.error
 */
public class ShareException extends Exception {
    private int mCode = -1;

    public ShareException(String detailMessage) {
        super(detailMessage);
    }

    public ShareException( String detailMessage, int code) {
        super(detailMessage);
        mCode = code;
    }

    public ShareException( String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ShareException(Throwable throwable) {
        super(throwable);
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }
}
