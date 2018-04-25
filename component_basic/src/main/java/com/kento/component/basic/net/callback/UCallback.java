package com.kento.component.basic.net.callback;

/**
 * @author:Yang
 * @date:2017/11/28 10:19
 * @since:V1.0
 * @desc:ddframework.gent.common.net.callback
 * @param:上传进度回调
 */
public interface UCallback {
    void onProgress( long currentLength, long totalLength, float percent );
    void onFail( int errCode, String errMsg );
}
