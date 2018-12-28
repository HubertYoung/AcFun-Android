package com.hubertyoung.common.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.hubertyoung.common.baserx.LiveBus;
import com.hubertyoung.common.stateview.StateConstants;
import com.hubertyoung.common.utils.TUtil;

import io.reactivex.disposables.Disposable;


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
public class AbsViewModel< R extends AbsRepository > extends AndroidViewModel {

	protected R mRepository;

	public AbsViewModel( @NonNull Application application ) {
		super( application );
		mRepository = TUtil.getNewInstance( this, 0 );
	}

	protected void sendData( Object eventKey, Object t ) {
		sendData( eventKey, null, t );
	}

	protected void sendData( Object eventKey, String tag, Object t ) {
		LiveBus.getDefault().postEvent( eventKey, tag, t );
	}

	protected void showPageState( Object eventKey, String state ) {
		sendData( eventKey, state );
	}

	protected void showPageState( Object eventKey, String tag, String state ) {
		sendData( eventKey, tag, state );
	}

	public void showLoadingLayout( String simpleName, String title ) {
		sendData( simpleName, StateConstants.LOADING_STATE );
	}

	public void showLoadingLayout( String simpleName, String tag, String title ) {
		sendData( simpleName, tag, StateConstants.LOADING_STATE );
	}

	public void stopLoading( String simpleName, String tag ) {
		sendData( simpleName, tag, StateConstants.SUCCESS_STATE );
	}

	public void stopLoading( String simpleName ) {
		sendData( simpleName, StateConstants.SUCCESS_STATE );
	}

	public void showErrorLayout( String simpleName ) {
		sendData( simpleName, StateConstants.ERROR_STATE );
	}

	public void showErrorLayout( String simpleName, String tag ) {
		sendData( simpleName, tag, StateConstants.ERROR_STATE );
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		if ( mRepository != null ) {
			mRepository.unDisposable();
		}
	}

	protected void addDisposable( Disposable disposable ) {
		if ( mRepository == null ) {
			mRepository.addDisposable( disposable );
		}
	}
}
