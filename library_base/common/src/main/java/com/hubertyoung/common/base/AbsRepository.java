package com.hubertyoung.common.base;


import com.hubertyoung.common.baserx.RxManager;

import io.reactivex.disposables.Disposable;

/**
 * <br>
 * function:AbsRepository抽象
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/9 17:04
 * @since:V1.0.0
 * @desc:com.hubertyoung.common.base
 */
public abstract class AbsRepository {

    public RxManager mRxManager;


    public AbsRepository() {
        mRxManager = new RxManager();
    }

    protected void addDisposable(Disposable disposable) {
        mRxManager.add( disposable );
    }

    public void unDisposable() {
        mRxManager.clear();
    }
}
