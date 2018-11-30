package com.hubertyoung.common.base;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.hubertyoung.common.baserx.LiveBus;
import com.hubertyoung.common.stateview.StateConstants;
import com.hubertyoung.common.utils.TUtil;

import java.util.ArrayList;
import java.util.List;



/**
 * AbsLifecycleFragment
 */
public abstract class AbsLifecycleFragment< VM extends AbsViewModel > extends BaseFragment {

	protected VM mViewModel;
	//
	protected Object mStateEventKey;

	protected String mStateEventTag;

	private List< Object > events = new ArrayList<>();

	@Override
	protected void initView( Bundle state ) {
		mViewModel = VMProviders( this, TUtil.getInstance( this, 0 ) );
		if ( null != mViewModel ) {
			dataObserver();
			mStateEventKey = TAG;
			mStateEventTag = getStateEventTag();
			events.add( new StringBuilder( ( String ) mStateEventKey ).append( mStateEventTag ).toString() );
			LiveBus.getDefault().subscribe( mStateEventKey, mStateEventTag ).observe( this, observer );
		}
	}
	/**
	 * ViewPager + fragment tag
	 *
	 * @return
	 */
	protected String getStateEventTag() {
		return "";
	}
	/**
	 * create ViewModelProviders
	 *
	 * @return ViewModel
	 */
	protected < T extends ViewModel > T VMProviders( Fragment fragment, @NonNull Class< T > modelClass ) {
		return ViewModelProviders.of( fragment ).get( modelClass );
	}

	protected void dataObserver() {

	}

	@Override
	public void onViewCreated( View view, @Nullable Bundle savedInstanceState ) {
		super.onViewCreated( view, savedInstanceState );
	}

	protected < T > MutableLiveData< T > registerObserver( Object eventKey, Class< T > tClass ) {

		return registerObserver( eventKey, null, tClass );
	}

	protected < T > MutableLiveData< T > registerObserver( Object eventKey, String tag, Class< T > tClass ) {
		String event;
		if ( TextUtils.isEmpty( tag ) ) {
			event = ( String ) eventKey;
		} else {
			event = eventKey + tag;
		}
		events.add( event );
		return LiveBus.getDefault().subscribe( eventKey, tag, tClass );
	}


	//	@Override
//	protected void onStateRefresh() {
//		showLoading();
//	}
//
//
//	/**
//	 * 获取网络数据
//	 */
//	protected void getRemoteData() {
//
//	}
//
//	protected void showError( Class< ? extends BaseStateControl > stateView, Object tag ) {
//		loadManager.showStateView( stateView, tag );
//	}
//
//	protected void showError( Class< ? extends BaseStateControl > stateView ) {
//		showError( stateView, null );
//	}
//
//	protected void showSuccess() {
//		loadManager.showSuccess();
//	}
//
//	protected void showLoading() {
//		loadManager.showStateView( LoadingState.class );
//	}
//
//
	protected Observer observer = new Observer< String >() {
		@Override
		public void onChanged( @Nullable String state ) {
			if ( !TextUtils.isEmpty( state ) ) {
				if ( StateConstants.ERROR_STATE.equals( state ) ) {
					showErrorLayout();
				} else if ( StateConstants.NET_WORK_STATE.equals( state ) ) {
				} else if ( StateConstants.LOADING_STATE.equals( state ) ) {
					showLoading( "" );
				} else if ( StateConstants.SUCCESS_STATE.equals( state ) ) {
					stopLoading();
				}
			}
		}
	};

	protected void showErrorLayout() {
	}

	protected void stopLoading() {

	}

	protected void showLoading( String title ) {

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if ( events != null && events.size() > 0 ) {
			for (int i = 0; i < events.size(); i++) {
				LiveBus.getDefault().clear( events.get( i ) );
			}
		}
	}
}
