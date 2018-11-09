package com.hubertyoung.common.base;

import android.app.Application;

import com.hubertyoung.common.utils.TUtil;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


/**
 * <br>
 * function:ViewModel 实现
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/9 17:04
 * @since:V1.0.0
 * @desc:com.hubertyoung.common.base
 */
public class AbsViewModel<T extends AbsRepository> extends AndroidViewModel {


    public T mRepository;

    public AbsViewModel( @NonNull Application application) {
        super(application);
        mRepository = TUtil.getNewInstance(this, 0);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mRepository != null) {
            mRepository.unDisposable();
        }
    }

}
