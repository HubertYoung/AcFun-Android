package com.hubertyoung.common.net.callback;

/**
 * @author:Yang
 * @date:2017/11/28 10:19
 * @since:V1.0
 * @desc:com.hubertyoung.common.net.callback
 * @param:上传进度回调
 */
public interface UCallback {
    void onProgress( long currentLength, long totalLength, float percent );
    void onFail( int errCode, String errMsg );
}
