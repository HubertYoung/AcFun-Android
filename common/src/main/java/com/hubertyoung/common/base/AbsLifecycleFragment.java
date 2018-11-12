package com.hubertyoung.common.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hubertyoung.common.baserx.LiveBus;
import com.hubertyoung.common.utils.TUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;


/**
 * AbsLifecycleFragment
 */
public abstract class AbsLifecycleFragment< VM extends AbsViewModel > extends BaseFragment {

	protected VM mViewModel;
//
//	protected Object mStateEventKey;
//
//	protected String mStateEventTag;

	private List< Object > events = new ArrayList<>();

	@Override
	public void initView( Bundle state ) {
		mViewModel = VMProviders( this, TUtil.getInstance( this, 0 ) );
		if ( null != mViewModel ) {
			dataObserver();
//			mStateEventKey = getStateEventKey();
//			mStateEventTag = getStateEventTag();
//			events.add( new StringBuilder( ( String ) mStateEventKey ).append( mStateEventTag ).toString() );
//			LiveBus.getDefault().subscribe( mStateEventKey, mStateEventTag ).observe( this, observer );
		}
	}

//	/**
//	 * ViewPager +fragment tag
//	 *
//	 * @return
//	 */
//	protected String getStateEventTag() {
//		return "";
//	}
//
//	/**
//	 * get state page event key
//	 *
//	 * @return
//	 */
//	protected abstract Object getStateEventKey();

	/**
	 * create ViewModelProviders
	 *
	 * @return ViewModel
	 */
	protected < T extends ViewModel > T VMProviders( BaseFragment fragment, @NonNull Class< T > modelClass ) {
		return ViewModelProviders.of( fragment ).get( modelClass );

	}

	protected void dataObserver() {

	}

	@Override
	public void onViewCreated( View view, @Nullable Bundle savedInstanceState ) {
		super.onViewCreated( view, savedInstanceState );
		//私有的ViewModel与View的契约事件回调逻辑
		registorUIChangeLiveDataCallBack();
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
//	protected Observer observer = new Observer< String >() {
//		@Override
//		public void onChanged( @Nullable String state ) {
//			if ( !TextUtils.isEmpty( state ) ) {
////				if ( StateConstants.ERROR_STATE.equals( state ) ) {
////					showError( ErrorState.class, "2" );
////				} else if ( StateConstants.NET_WORK_STATE.equals( state ) ) {
////					showError( ErrorState.class, "1" );
////				} else if ( StateConstants.LOADING_STATE.equals( state ) ) {
////					showLoading();
////				} else if ( StateConstants.SUCCESS_STATE.equals( state ) ) {
////					showSuccess();
////				}
//			}
//		}
//	};
	/**
	 * =====================================================================
	 **/
	/**
	 * 注册ViewModel与View的契约UI回调事件
	 */
	private void registorUIChangeLiveDataCallBack() {
		/**
		 *		加载对话框显示
		 */
		LiveBus.getDefault().getUC().getShowDialogEvent().observe( this, new Observer< String >() {
			@Override
			public void onChanged( @Nullable String title ) {
				showLoading( title );
			}
		} );
		/**
		 * 		加载对话框消失
		 */
		LiveBus.getDefault().getUC().getDismissDialogEvent().observe( this, new Observer< Boolean >() {
			@Override
			public void onChanged( @Nullable Boolean aBoolean ) {
				stopLoading();
			}
		} );

//		//跳入新页面
//		viewModel.getUC().getStartActivityEvent().observe(this, new Observer<Map<String, Object> >() {
//			@Override
//			public void onChanged(@Nullable Map<String, Object> params) {
//				Class<?> clz = (Class<?>) params.get(ParameterField.CLASS);
//				Bundle bundle = (Bundle) params.get(ParameterField.BUNDLE);
//				startActivity(clz, bundle);
//			}
//		});
//		//跳入ContainerActivity
//		viewModel.getUC().getStartContainerActivityEvent().observe(this, new Observer<Map<String, Object>>() {
//			@Override
//			public void onChanged(@Nullable Map<String, Object> params) {
//				String canonicalName = (String) params.get(ParameterField.CANONICAL_NAME);
//				Bundle bundle = (Bundle) params.get(ParameterField.BUNDLE);
//				startContainerActivity(canonicalName, bundle);
//			}
//		});
//		//关闭界面
//		viewModel.getUC().getFinishEvent().observe(this, new Observer<Boolean>() {
//			@Override
//			public void onChanged(@Nullable Boolean aBoolean) {
//				getActivity().finish();
//			}
//		});
//		//关闭上一层
//		viewModel.getUC().getOnBackPressedEvent().observe(this, new Observer<Boolean>() {
//			@Override
//			public void onChanged(@Nullable Boolean aBoolean) {
//				getActivity().onBackPressed();
//			}
//		});
	}

	protected abstract void stopLoading();

	protected abstract void showLoading( String title );

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
