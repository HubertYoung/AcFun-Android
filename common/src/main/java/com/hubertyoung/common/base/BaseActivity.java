package com.hubertyoung.common.base;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.facebook.stetho.common.LogUtil;
import com.hubertyoung.base.bean.EnvironmentBean;
import com.hubertyoung.base.bean.ModuleBean;
import com.hubertyoung.base.listener.OnEnvironmentChangeListener;
import com.hubertyoung.common.BuildConfig;
import com.hubertyoung.common.baserx.RxManager;
import com.hubertyoung.common.utils.AppActivityManager;
import com.hubertyoung.common.utils.AppUtils;
import com.hubertyoung.common.utils.BarUtils;
import com.hubertyoung.common.utils.CommonLog;
import com.hubertyoung.common.utils.TUtil;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

/**
 * 基类
 */

/***************使用例子*********************/
//1.mvp模式
//public class SampleActivity extends BaseActivity<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
//    }
//
//    @Override
//    public void initView() {
//    }
//}
//2.普通模式
//public class SampleActivity extends BaseActivity {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//
//    @Override
//    public void initView() {
//    }
//}
public abstract class BaseActivity< T extends BasePresenter, E extends BaseModel > extends AppCompatActivity implements OnEnvironmentChangeListener {
	public String TAG = this.getClass().getSimpleName();
	private static final String SAVED_STATE_STATUS_BAR_TRANSLUCENT = "saved_state_status_bar_translucent";

	public T mPresenter;
	public E mModel;
	public Context mContext;
	public RxManager mRxManager;
	private boolean isConfigChange = false;
	private boolean statusBarTranslucent;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		isConfigChange = false;
		mRxManager = new RxManager();
		if ( isRegisterEvent() ) {
			mRxManager.mRxBus.register( this );
		}
		// 把actvity放到application栈中管理
		AppActivityManager.getAppManager().addActivity( this );
		// 无标题
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		// 设置竖屏
		setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

		if ( savedInstanceState != null ) {
			statusBarTranslucent = savedInstanceState.getBoolean( SAVED_STATE_STATUS_BAR_TRANSLUCENT );
			BarUtils.setStatusBarTranslucent( getWindow(), statusBarTranslucent );
		}

		doBeforeSetContentView();
		if ( getLayoutId() != 0 ) {
			setContentView( getLayoutId() );
			mContext = this;
			mPresenter = TUtil.getT( this, 0 );
			mModel = TUtil.getT( this, 1 );
			if ( mPresenter != null ) {
				mPresenter.mContext = this;
			}
			this.initPresenter();
			this.initView( savedInstanceState );
			//初始化ToolBar
			initToolBar();

			loadData();
			EnvironmentSwitcher.addOnEnvironmentChangeListener( this );
		} else {
			LogUtil.e( "--->bindLayout() return 0" );
		}
	}

	/**
	 * 设置layout前配置
	 */
	public void doBeforeSetContentView() {
		// 默认着色状态栏
//		setStatusBarColor();

	}

	/*********************子类实现*****************************/
	//获取布局文件
	public abstract int getLayoutId();

	//简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
	public abstract void initPresenter();

	//初始化view
	public abstract void initView( Bundle savedInstanceState );

	protected abstract void loadData();

	//初始化toolBar
	public abstract void initToolBar();

	/**
	 * 是否开启事件订阅
	 *
	 * @return
	 */
	protected boolean isRegisterEvent() {
		return false;
	}

	public boolean isStatusBarTranslucent() {
		return statusBarTranslucent;
	}

//	/**
//	 * 沉浸状态栏（4.4以上系统有效）
//	 */
//	protected void setTranslanteBar( @FloatRange( from = 0.0, to = 1.0 ) float alpha ) {
////		StatusBarCompat.translucentStatusBar(this);
//		BarUtils.setStatusBar4Bg( this, alpha );
//	}

	/**
	 * 通过Class跳转界面
	 **/
	public void startActivity( Class< ? > cls ) {
		startActivity( cls, null );
	}

	/**
	 * 通过Class跳转界面
	 **/
	public void startActivityForResult( Class< ? > cls, int requestCode ) {
		startActivityForResult( cls, null, requestCode );
	}

	/**
	 * 含有Bundle通过Class跳转界面
	 **/
	public void startActivityForResult( Class< ? > cls, Bundle bundle, int requestCode ) {
		Intent intent = new Intent();
		intent.setClass( this, cls );
		if ( bundle != null ) {
			intent.putExtras( bundle );
		}
		startActivityForResult( intent, requestCode );
	}

	/**
	 * 含有Bundle通过Class跳转界面
	 **/
	public void startActivity( Class< ? > cls, Bundle bundle ) {
		Intent intent = new Intent();
		intent.setClass( this, cls );
		if ( bundle != null ) {
			intent.putExtras( bundle );
		}
		startActivity( intent );
	}

	/**
	 * 显示fragment 可返回
	 *
	 * @param fragment
	 * @param framlayoutID
	 */
	public void addViewFrame( BaseFragment fragment, int framlayoutID ) {
		FragmentTransaction fragmentTransaction = getFragmentTransaction();
		fragmentTransaction.replace( framlayoutID, fragment );
		fragmentTransaction.addToBackStack( fragment.TAG );
		fragmentTransaction.commitAllowingStateLoss();
	}

	/**
	 * 获取fragment beginTransaction
	 *
	 * @return
	 */
	public FragmentTransaction getFragmentTransaction() {
		return this.getSupportFragmentManager().beginTransaction();
	}

	@Override
	protected void onResume() {
		super.onResume();
		//debug版本不统计crash
		if ( !BuildConfig.DEBUG ) {
			//友盟统计
//			MobclickAgent.onResume(this);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		//debug版本不统计crash
		if ( !BuildConfig.DEBUG ) {
			//友盟统计
//			MobclickAgent.onPause(this);
		}
	}

	@Override
	public void onConfigurationChanged( Configuration newConfig ) {
		super.onConfigurationChanged( newConfig );
		isConfigChange = true;
	}

	@Override
	protected void onDestroy() {
		EnvironmentSwitcher.removeOnEnvironmentChangeListener( this );
		super.onDestroy();
		CommonLog.loge( "activity: " + getClass().getSimpleName() + " onDestroy()" );
		if ( mPresenter != null ) mPresenter.onDestroy();
		if ( mRxManager != null ) {
			if ( isRegisterEvent() ) mRxManager.mRxBus.unregister( this );
			mRxManager.clear();
		}
		if ( !isConfigChange ) {
			AppActivityManager.getAppManager().finishActivity( this );
		}

	}

	@Override
	protected void onSaveInstanceState( Bundle outState ) {
		super.onSaveInstanceState( outState );
		outState.putBoolean( SAVED_STATE_STATUS_BAR_TRANSLUCENT, statusBarTranslucent );
	}

	@Override
	public void onEnvironmentChanged( ModuleBean module, EnvironmentBean oldEnvironment, EnvironmentBean newEnvironment ) {
		if ( AppUtils.isDebuggable() ) {
			Log.e( TAG, module.getName() + "由" + oldEnvironment.getName() + "环境，Url=" + oldEnvironment.getUrl() + ",切换为" + newEnvironment.getName() + "环境，Url=" + newEnvironment.getUrl() );
			Toast.makeText( this, module.getName() + "由" + oldEnvironment.getName() + "环境，Url=" + oldEnvironment.getUrl() + "切换为" + newEnvironment.getName() + "环境，Url=" + newEnvironment.getUrl(),
					Toast.LENGTH_SHORT )
					.show();
		}
	}
}
