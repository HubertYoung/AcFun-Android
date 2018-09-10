package com.hubertyoung.pay.payment;


import com.hubertyoung.pay.sdk.IResult;
import com.hubertyoung.pay.sdk.OnCallback;

public interface IPayable extends IResult {
    void pay( String data, OnCallback< String > callback );
}
