package com.hubertyoung.common.net.transformer;


import com.hubertyoung.common.net.response.BaseResponse;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：JIUU on 2017-7-10 16:00:51
 * QQ号：1344393464
 * 作用：预处理异常信息
 */
public class DefaultTransformer <T>  implements FlowableTransformer<BaseResponse<T>,T> {

    @Override
    public Publisher< T > apply( Flowable< BaseResponse< T > > upstream ) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .compose(ErrorTransformer.<T>getInstance())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

