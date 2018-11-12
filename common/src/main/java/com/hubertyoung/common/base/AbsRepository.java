package com.hubertyoung.common.base;


import io.reactivex.disposables.CompositeDisposable;
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

    private CompositeDisposable mCompositeDisposable;


    public AbsRepository() {

    }

    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void unDisposable() {
        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }
}
