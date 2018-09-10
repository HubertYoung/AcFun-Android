package com.hubertyoung.pay.authorize;


import com.hubertyoung.pay.sdk.IResult;
import com.hubertyoung.pay.sdk.OnCallback;

/**
 * Created by ezy on 17/3/18.
 */

public interface IAuthorize extends IResult {
    void authorize( OnCallback< String > callback );
}
