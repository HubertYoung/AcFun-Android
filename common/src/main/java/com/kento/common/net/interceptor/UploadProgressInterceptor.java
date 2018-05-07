package com.kento.common.net.interceptor;

import android.support.annotation.NonNull;

import com.kento.common.net.callback.UCallback;
import com.kento.common.net.body.UploadProgressRequestBody;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author:Yang
 * @date:2017/11/28 10:19
 * @since:V1.0
 * @desc:com.kento.common.net.interceptor
 * @param:上传进度拦截
 */
public class UploadProgressInterceptor implements Interceptor {

	private UCallback callback;

	public UploadProgressInterceptor( UCallback callback ) {
		this.callback = callback;
		if ( callback == null ) {
			throw new NullPointerException( "this callback must not null." );
		}
	}

	@Override
	public Response intercept( @NonNull Chain chain ) throws IOException {
		Request originalRequest = chain.request();
		if ( originalRequest.body() == null ) {
			return chain.proceed( originalRequest );
		}
		Request progressRequest = originalRequest.newBuilder()
												 .method( originalRequest.method(), new UploadProgressRequestBody( originalRequest.body(), callback ) )
												 .build();
		return chain.proceed( progressRequest );
	}
}
