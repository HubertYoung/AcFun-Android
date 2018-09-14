package com.hubertyoung.baseplatform.share;


import com.hubertyoung.baseplatform.PlatformShareConfiguration;
import com.hubertyoung.baseplatform.sdk.IResult;
import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.share.shareparam.BaseShareParam;

public interface IShareHandler extends IResult {

    void share( BaseShareParam param, PlatformShareConfiguration platformShareConfiguration, OnCallback< String > callback ) throws Exception;
}
