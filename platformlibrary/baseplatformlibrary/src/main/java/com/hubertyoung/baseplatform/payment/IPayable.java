package com.hubertyoung.baseplatform.payment;


import com.hubertyoung.baseplatform.sdk.IResult;
import com.hubertyoung.baseplatform.sdk.OnCallback;

public interface IPayable extends IResult {
    void pay( String data, OnCallback< String > callback );
}
