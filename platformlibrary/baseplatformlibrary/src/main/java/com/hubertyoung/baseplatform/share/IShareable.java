package com.hubertyoung.baseplatform.share;


import com.hubertyoung.baseplatform.sdk.IResult;
import com.hubertyoung.baseplatform.sdk.OnCallback;

public interface IShareable extends IResult {
    void share( ShareData data, OnCallback< String > callback );
}
