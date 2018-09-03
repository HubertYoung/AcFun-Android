package activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.jakewharton.rxbinding2.view.RxView;
import com.just.agentweb.ChromeClientCallbackManager;
import com.hubertyoung.common.net.http.HttpUtils;
import com.hubertyoung.common.os.ClipboardUtils;
import com.hubertyoung.common.utils.BarUtils;
import com.hubertyoung.common.utils.CommonLog;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.common.widget.SlowlyProgressBar;
import com.hubertyoung.component_jsbridge.R;
import com.hubertyoung.component_jsbridge.jsbridge.BridgeWebViewClient;
import com.hubertyoung.component_jsbridge.jsbridge.ClientListener;
import com.hubertyoung.component_jsbridge.jsbridge.tool.ScrollWebView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import base.BaseWebViewActivity;
import butterknife.BindView;
import okhttp3.Cookie;

/**
 * <br>
 * function:网页webview
 * <p>
 *
 * @author:Yang
 * @date:2018/2/8 下午4:39
 * @since:V1.0
 * @desc:com.gent.cardsharelife.ui.activity
 */
public class WebViewActivity extends BaseWebViewActivity {
	@BindView( R.id.appbar )
	AppBarLayout appbar;
	@BindView( R.id.toolbar_head )
	Toolbar mToolbarHead;
	@BindView( R.id.tv_head_title )
	AppCompatTextView tvHeadTitle;
	@BindView( R.id.fl_web_view_content )
	FrameLayout mFlwebViewContent;
	@BindView( R.id.srl_refresh_layout )
	SmartRefreshLayout mSrlRefreshLayout;
	@BindView( R.id.tv_card_share_details_collection )
	AppCompatTextView mTvCardShareDetailsCollection;
	@BindView( R.id.btn_card_share_details_buy )
	AppCompatButton btnCardShareDetailsBuy;
	@BindView( R.id.ll_details_button_bar_root )
	LinearLayoutCompat mLlDetailsButtonBarRoot;
	@BindView( R.id.pb_webview_loading )
	ProgressBar mPbWebviewLoading;
	@BindView( R.id.tv_card_consume_record )
	AppCompatTextView mTvCardConsumeRecord;

	private String url;
	private String nametitle;
	private AlertDialog mAlertDialog;
	private ScrollWebView mBridgeWebView;

	private int mOffset = 0;
	private int mScrollY = 0;
	private SlowlyProgressBar mSlowlyProgressBar;

	@Override
	public int getLayoutId() {
		return R.layout.activity_web_view_details;
	}

	@Override
	public void initPresenter() {
	}

	@Override
	public void doBeforeSetcontentView() {
		BarUtils.statusBarLightMode( this, true, 0 );
		BarUtils.immersiveStatusBar( this.getWindow(), 0 );
	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		mBridgeWebView = new ScrollWebView( this );
		mSlowlyProgressBar = new SlowlyProgressBar( mPbWebviewLoading );
		super.initView( savedInstanceState );
		BarUtils.setPaddingSmart( mToolbarHead );

		getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN );

		Bundle bundle = getIntent().getExtras();
		if ( bundle == null || TextUtils.isEmpty( bundle.getString( "url" ) ) ) {
			url = "https://www.jd.com/";
		} else {
			url = bundle.getString( "url" );
			nametitle = bundle.getString( "title" );
		}

//		mPbWebviewLoading.setColor( getColorAccent() );
		initData();
//		if ( AppUtils.isDebuggable() ) {
//			url = "http://114.113.149.49:11006/cardshare-api/share/toCardDetail?shareID=3";
//		}
		CommonLog.logi( "TAG", "url===" + url );

		initRefreshLayout();
		initAction();

//        initCookie( url );
		List< Cookie > cookies = HttpUtils.getInstance().getPersistentCookieJar().getPersistor().loadAll();
		if ( cookies != null && !cookies.isEmpty() ) {
			Cookie cookie = cookies.get( cookies.size() - 1 );
			url = url + ( url.contains( "?" ) ? "&" : "?" ) + "uuid=" + cookie.value();
		}
		refreshWebView( url );
	}

	private void initData() {

	}

	@Override
	protected WebViewClient webViewClient() {
		BridgeWebViewClient bridgeWebViewClient = new BridgeWebViewClient( mBridgeWebView );
		bridgeWebViewClient.setClientListener( new ClientListener() {
			@Override
			public void onPageStarted() {
				if ( mSlowlyProgressBar != null ) {
					mSlowlyProgressBar.onProgressStart();
				}
			}

			@Override
			public void onPageFinished() {
//				if ( mPbWebviewLoading != null ) {
//					mPbWebviewLoading.hide();
//				}
			}

			@Override
			public void callPhone( WebView view, String url ) {
				Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
				startActivity( intent );
			}
		} );
		return bridgeWebViewClient;
	}

	@Override
	protected int getIndicatorColor() {
		return 0;
	}

	@Override
	protected WebView getWebView() {
		return mBridgeWebView;
	}

	@Override
	protected WebChromeClient webChromeClient() {
		return mWebChromeClient;
	}

	@Override
	protected ChromeClientCallbackManager.ReceivedTitleCallback chromeClientCallbackManager() {
		return mCallback;
	}

	@Override
	protected ViewGroup getWebContentView() {
		return mFlwebViewContent;
	}

	public int getColorAccent() {
		TypedValue typedValue = new TypedValue();
		getTheme().resolveAttribute( R.attr.colorAccent, typedValue, true );
		return typedValue.data;
	}

	@SuppressLint( "WrongConstant" )
	private void initAction() {
		mToolbarHead.setNavigationOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				if ( mAgentWeb != null && !mAgentWeb.back() )// true表示AgentWeb处理了该事件
					finish();
			}
		} );
		mToolbarHead.setOnMenuItemClickListener( mOnMenuItemClickListener );
		mSrlRefreshLayout.setOnRefreshListener( new OnRefreshListener() {
			@Override
			public void onRefresh( RefreshLayout refreshLayout ) {
				mSrlRefreshLayout.finishRefresh( 500 );
				mAgentWeb.getLoader().reload(); //刷新
			}
		} );
		mSrlRefreshLayout.setOnMultiPurposeListener( new SimpleMultiPurposeListener() {
			@Override
			public void onHeaderMoving( RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight ) {
				super.onHeaderMoving( header, isDragging, percent, offset, headerHeight, maxDragHeight );
				if ( isDragging ) {
					mOffset = offset / 2;
					mToolbarHead.setAlpha( 1 - Math.min( percent, 1 ) );
				} else {
					mOffset = offset / 2;
					mToolbarHead.setAlpha( 1 - Math.min( percent, 1 ) );
				}
			}
		} );
		mBridgeWebView.setOnScrollChangeListener( new ScrollWebView.OnScrollChangeListener() {
			private int color = ContextCompat.getColor( getApplicationContext(), R.color.white ) & 0x00ffffff;

			@Override
			public void onPageEnd( int l, int t, int oldl, int oldt ) {

			}

			@Override
			public void onPageTop( int l, int t, int oldl, int oldt ) {
				appbar.setBackgroundColor( ( 0 << 24 ) | color );
				tvHeadTitle.setVisibility( View.GONE );
			}

			@Override
			public void onScrollChanged( int l, int t, int oldl, int oldt ) {
				appbar.setBackgroundColor( ( Math.min( ( int ) ( t * 0.7 ), 255 ) << 24 ) | color );
				tvHeadTitle.setVisibility( Math.min( t * 0.7, 255 ) > 200 ? View.VISIBLE : View.GONE );
			}
		} );
		RxView.clicks( btnCardShareDetailsBuy ).throttleFirst( 500, TimeUnit.MILLISECONDS ).subscribe( ( Object o ) -> {
			ToastUtil.showSuccess( "去支付" );
		} );
	}

	/**
	 * 更新背景
	 *
	 * @param f
	 */
	private void updataBackgroundAlpha( @FloatRange( from = 0.0, to = 1.0 ) float f ) {
		try {
			WindowManager.LayoutParams lp = this.getWindow().getAttributes();
			lp.alpha = f;
			this.getWindow().addFlags( WindowManager.LayoutParams.FLAG_DIM_BEHIND );
			this.getWindow().setAttributes( lp );
		} catch ( Exception e ) {

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void initRefreshLayout() {
		mSrlRefreshLayout.setRefreshHeader( new ClassicsHeader( this ).setSpinnerStyle( SpinnerStyle.Translate ) );
		mSrlRefreshLayout.setRefreshFooter( new ClassicsFooter( this ).setSpinnerStyle( SpinnerStyle.Translate ) );
	}

	@Override
	public void loadData() {
	}

	//	private WebViewClient mWebViewClient = new WebViewClient() {
//		@Override
//		public boolean shouldOverrideUrlLoading( WebView view, WebResourceRequest request ) {
//			return super.shouldOverrideUrlLoading( view, request );
//		}
//
//		@Override
//		public void onPageStarted( WebView view, String url, Bitmap favicon ) {
//			//do you  work
//			Log.i( "Info", "BaseWebActivity onPageStarted" );
//		}
//	};
	private WebChromeClient mWebChromeClient = new WebChromeClient() {
		@Override
		public void onProgressChanged( WebView view, int newProgress ) {
			if ( mSlowlyProgressBar != null ) {
				mSlowlyProgressBar.onProgressChange( newProgress );
			}
		}

	};
	private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
		@Override
		public void onReceivedTitle( WebView view, String title ) {
			if ( tvHeadTitle != null ) tvHeadTitle.setText( title );
		}
	};

	@Override
	public void initToolBar() {
		mToolbarHead.setNavigationIcon( R.drawable.icon_nav_next );
		mToolbarHead.setBackgroundResource( R.color.transparent );
		mToolbarHead.setTitle( "" );
		mToolbarHead.inflateMenu( R.menu.menu_webview );
//		if ( AppUtils.isDebuggable() ) {
//			mToolbarHead.getMenu()
//						.findItem( R.id.error_website )
//						.setVisible( true );
//		}
		tvHeadTitle.setVisibility( View.GONE );
	}

	//菜单事件
	private Toolbar.OnMenuItemClickListener mOnMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick( MenuItem item ) {

			switch ( item.getItemId() ) {
				case R.id.more:
					return true;
				case R.id.refresh:
					if ( mAgentWeb != null ) mAgentWeb.getLoader().reload(); //刷新
					return true;

				case R.id.copy:
					if ( mAgentWeb != null ) ClipboardUtils.copyText( mAgentWeb.getWebCreator().get().getUrl() );
					return true;
				case R.id.default_browser:
					if ( mAgentWeb != null ) openBrowser( mAgentWeb.getWebCreator().get().getUrl() );
					return true;
				case R.id.default_clean:
					toCleanWebCache();
					return true;
				case R.id.error_website:
					loadErrorWebSite();
					return true;
				default:
					return false;
			}

		}
	};


	public static void launch( Context context, String url, String title ) {
		Intent intent = new Intent( context, WebViewActivity.class );
		Bundle bundle = new Bundle();
		bundle.putString( "url", url );
		bundle.putString( "title", title );
		intent.putExtras( bundle );
		context.startActivity( intent );
	}


//	public void showLoading( String title, int type ) {
//
//	}
//
//	public void stopLoading() {
//		if ( mDialog != null ) {
//			mDialog.showButtonText();
//		}
//	}
//
//	public void showErrorTip( String msg ) {
//		ToastUtil.showError( msg );
//	}

	@Override
	public void finish() {
		super.finish();
		if ( mSlowlyProgressBar != null ) {
			mSlowlyProgressBar.destroy();
			mSlowlyProgressBar = null;
		}
	}

	@Override
	protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
		super.onActivityResult( requestCode, resultCode, data );
//		ShareSDK.onHandleResult( activity, requestCode, resultCode, data );
	}


}
