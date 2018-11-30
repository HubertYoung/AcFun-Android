package com.hubertyoung.baseplatform.sdk;

import android.app.Activity;

public interface IFactory<T extends IResult> {

    OtherPlatform getPlatform();

    T create( Activity activity );
}
