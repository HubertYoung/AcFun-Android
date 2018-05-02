package com.kento.common.net.transformer;


import com.kento.common.net.response.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：JIUU on 2017-7-10 16:00:51
 * QQ号：1344393464
 * 作用：预处理异常信息
 */
public class DefaultTransformer <T>  implements ObservableTransformer<BaseResponse<T>,T>{
    @Override
    public ObservableSource< T > apply( @NonNull Observable< BaseResponse< T > > upstream ) {
        return upstream.subscribeOn(Schedulers.io())
                       .observeOn(Schedulers.newThread())
                       .compose(ErrorTransformer.<T>getInstance())
                       .observeOn(AndroidSchedulers.mainThread());
    }
}

