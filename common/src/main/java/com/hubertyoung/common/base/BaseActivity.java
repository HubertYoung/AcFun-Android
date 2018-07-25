package com.hubertyoung.common.base;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.facebook.stetho.common.LogUtil;
import com.hubertyoung.common.BuildConfig;
import com.hubertyoung.common.R;
import com.hubertyoung.common.baserx.RxManager;
import com.hubertyoung.common.utils.AppActivityManager;
import com.hubertyoung.common.utils.BarUtils;
import com.hubertyoung.common.utils.CommonLog;
import com.hubertyoung.common.utils.StatusBarCompat;
import com.hubertyoung.common.utils.TUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

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
public abstract class BaseActivity< T extends BasePresenter, E extends BaseModel > extends RxAppCompatActivity {
	public String TAG = this.getClass()
							.getSimpleName();

	public T mPresenter;
	public E mModel;
	public Context mContext;
	public RxManager mRxManager;
	private boolean isConfigChange = false;
	private Unbinder bind;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		isConfigChange = false;
		mRxManager = new RxManager();
		if ( isRegisterEvent() ) {
			mRxManager.mRxBus.register( this );
		}
		// 把actvity放到application栈中管理
		AppActivityManager.getAppManager()
				  .addActivity( this );
		// 无标题
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		// 设置竖屏
		setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

		doBeforeSetContentView();
		if ( getLayoutId() != 0 ) {
			setContentView( getLayoutId() );
			//初始化黄油刀控件绑定框架
			bind = ButterKnife.bind( this );
			mContext = this;
			mPresenter = TUtil.getT( this, 0 );
			mModel = TUtil.getT( this, 1 );
			if ( mPresenter != null ) {
				mPresenter.mContext = this;
			}
			//初始化ToolBar
			initToolBar();
			this.initPresenter();
			this.initView( savedInstanceState );
			loadData();
		} else {
			LogUtil.e( "--->bindLayout() return 0" );
		}
	}

	/**
	 * 设置layout前配置
	 */
	public void doBeforeSetContentView() {

		// 默认着色状态栏
		setStatusBarColor();

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

	/**
	 * 着色状态栏（4.4以上系统有效）
	 */
	protected void setStatusBarColor() {
		StatusBarCompat.setStatusBarColor( this, ContextCompat.getColor( this, R.color.colorPrimary ) );
//		StatusBarCompat.translucentStatusBar(this);
	}

	/**
	 * 着色状态栏（4.4以上系统有效）
	 */
	protected void setStatusBarColor( @ColorInt int color ) {
		StatusBarCompat.setStatusBarColor( this, color );
	}

	/**
	 * 沉浸状态栏（4.4以上系统有效）
	 */
	public void setTranslanteBar() {
//		StatusBarCompat.translucentStatusBar(this);
		BarUtils.setStatusBar4Bg( this );
	}

	/**
	 * 沉浸状态栏（4.4以上系统有效）
	 */
	protected void setTranslanteBar( @FloatRange( from = 0.0, to = 1.0 ) float alpha ) {
//		StatusBarCompat.translucentStatusBar(this);
		BarUtils.setStatusBar4Bg( this, alpha );
	}

	/**
	 * 为头部ImageView设置状态栏透明度
	 *
	 * @param view
	 */
	public void setHeightAndPadding( View view ) {
//		StatusBarCompat.translucentStatusBar(this);
		BarUtils.setStatusBar4ImageView( this, view );
	}

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
		return this.getSupportFragmentManager()
				   .beginTransaction();
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
		super.onDestroy();
		CommonLog.loge( "activity: " + getClass().getSimpleName() + " onDestroy()" );
		destroyed = true;
		if ( mPresenter != null ) mPresenter.onDestroy();
		if ( mRxManager != null ) {
			if ( isRegisterEvent() ) mRxManager.mRxBus.unregister( this );
			mRxManager.clear();
		}
		if ( !isConfigChange ) {
			AppActivityManager.getAppManager()
					  .finishActivity( this );
		}
		if ( bind != null ) bind.unbind();

	}

	//im的基类activity

	private boolean destroyed = false;



	@Override
	public void onBackPressed() {

		super.onBackPressed();
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		switch ( item.getItemId() ) {
			case android.R.id.home:
				onNavigateUpClicked();
				return true;
		}
		return onOptionsItemSelectedDispose( item );
	}
	public boolean onOptionsItemSelectedDispose( MenuItem item ) {
		return super.onOptionsItemSelected( item );
	}


	public void onNavigateUpClicked() {
		onBackPressed();
	}

	protected void showKeyboard( boolean isShow ) {
		InputMethodManager imm = ( InputMethodManager ) getSystemService( Context.INPUT_METHOD_SERVICE );
		if ( isShow ) {
			if ( getCurrentFocus() == null ) {
				imm.toggleSoftInput( InputMethodManager.SHOW_FORCED, 0 );
			} else {
				imm.showSoftInput( getCurrentFocus(), 0 );
			}
		} else {
			if ( getCurrentFocus() != null ) {
				imm.hideSoftInputFromWindow( getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
			}
		}
	}

//	/**
//	 * 延时弹出键盘
//	 *
//	 * @param focus 键盘的焦点项
//	 */
//	protected void showKeyboardDelayed( View focus ) {
//		final View viewToFocus = focus;
//		if ( focus != null ) {
//			focus.requestFocus();
//		}
//
//		getHandler().postDelayed( new Runnable() {
//			@Override
//			public void run() {
//				if ( viewToFocus == null || viewToFocus.isFocused() ) {
//					showKeyboard( true );
//				}
//			}
//		}, 200 );
//	}


	public boolean isDestroyedCompatible() {
		if ( Build.VERSION.SDK_INT >= 17 ) {
			return isDestroyedCompatible17();
		} else {
			return destroyed || super.isFinishing();
		}
	}

	@TargetApi( 17 )
	private boolean isDestroyedCompatible17() {
		return super.isDestroyed();
	}


	protected boolean displayHomeAsUpEnabled() {
		return true;
	}

	@Override
	public boolean onKeyDown( int keyCode, KeyEvent event ) {
		switch ( keyCode ) {
			case KeyEvent.KEYCODE_MENU:
				return onMenuKeyDown();

			default:
				return super.onKeyDown( keyCode, event );
		}
	}

	protected boolean onMenuKeyDown() {
		return false;
	}

	public void finishAndTransition() {
		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
			finishAfterTransition();
		} else {
			finish();
		}
	}
}
