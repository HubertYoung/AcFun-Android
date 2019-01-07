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
import com.hubertyoung.common.stateview.StateEntity;
import com.hubertyoung.common.utils.TUtil;
import com.hubertyoung.common.utils.log.CommonLog;

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
			mStateEventKey = getTAG();
			mStateEventTag = getStateEventTag();
			events.add( new StringBuilder( ( String ) mStateEventKey ).append( mStateEventTag ).toString() );
			LiveBus.getDefault().subscribe( mStateEventKey, mStateEventTag, StateEntity.class ).observe( this, observer );
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
		if(modelClass != null) {
			return ViewModelProviders.of( fragment ).get( modelClass );
		}
		CommonLog.logw( "viewModel is null" );
		return null;
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

	protected Observer observer = new Observer< StateEntity >() {
		@Override
		public void onChanged( @Nullable StateEntity stateEntity ) {
			String state = stateEntity.getCode();
			if ( !TextUtils.isEmpty( state ) ) {
				if ( StateConstants.ERROR_STATE.getCode().equals( state ) ) {
					showErrorLayout( stateEntity.getResult() );
				} else if ( StateConstants.NET_WORK_STATE.getCode().equals( state ) ) {
				} else if ( StateConstants.LOADING_STATE.getCode().equals( state ) ) {
					showLoading( "" );
				} else if ( StateConstants.SUCCESS_STATE.getCode().equals( state ) ) {
					stopLoading();
				}
			}
		}
	};

	protected void showErrorLayout(String result) {
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
