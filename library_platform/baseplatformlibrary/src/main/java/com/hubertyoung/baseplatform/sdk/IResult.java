package com.hubertyoung.baseplatform.sdk;

import android.content.Intent;

public interface IResult {
    void onResult( int requestCode, int resultCode, Intent data );
}
