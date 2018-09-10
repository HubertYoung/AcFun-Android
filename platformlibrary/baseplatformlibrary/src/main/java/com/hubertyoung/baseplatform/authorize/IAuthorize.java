package com.hubertyoung.baseplatform.authorize;


import com.hubertyoung.baseplatform.sdk.IResult;
import com.hubertyoung.baseplatform.sdk.OnCallback;

/**
 * Created by ezy on 17/3/18.
 */

public interface IAuthorize extends IResult {
    void authorize( OnCallback< String > callback );
}
