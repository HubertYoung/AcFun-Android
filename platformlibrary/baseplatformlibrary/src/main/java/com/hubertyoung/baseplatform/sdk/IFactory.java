package com.hubertyoung.baseplatform.sdk;

import android.app.Activity;

/**
 * Created by ezy on 17/3/18.
 */

public interface IFactory<T extends IResult> {

    OtherPlatform getPlatform();

    T create( Activity activity );
}
