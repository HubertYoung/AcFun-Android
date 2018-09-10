package com.hubertyoung.baseplatform.authorize;


import com.hubertyoung.baseplatform.sdk.IResult;
import com.hubertyoung.baseplatform.sdk.OnCallback;

public interface IAuthorize extends IResult {
    void authorize( OnCallback< String > callback );
}
