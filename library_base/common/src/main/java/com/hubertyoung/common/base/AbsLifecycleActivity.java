package com.hubertyoung.common.base;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.hubertyoung.common.baserx.LiveBus;
import com.hubertyoung.common.stateview.StateConstants;
import com.hubertyoung.common.stateview.StateEntity;
import com.hubertyoung.common.utils.TUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * AbsLifecycleActivity
 */
public abstract class AbsLifecycleActivity< VM extends AbsViewModel > extends BaseActivity {

	protected VM mViewModel;
	//
	protected Object mStateEventKey;

	protected String mStateEventTag;

	private List< Object > events = new ArrayList<>();

	public AbsLifecycleActivity() {

	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		mViewModel = VMProviders( this, TUtil.getInstance( this, 0 ) );
		if ( null != mViewModel ) {
			dataObserver();
			mStateEventKey = TAG;
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

	protected < T extends ViewModel > VM VMProviders( AppCompatActivity activity, @NonNull Class modelClass ) {
		if ( modelClass == null ) {
			return null;
		}
		return ( VM ) ViewModelProviders.of( activity ).get( modelClass );

	}

	protected void dataObserver() {

	}

	protected void showLoading( String title ) {

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
}
