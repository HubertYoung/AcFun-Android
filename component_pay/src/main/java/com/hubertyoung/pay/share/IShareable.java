package com.hubertyoung.pay.share;


import com.hubertyoung.pay.sdk.IResult;
import com.hubertyoung.pay.sdk.OnCallback;

public interface IShareable extends IResult {
    void share( ShareData data, OnCallback< String > callback );
}
