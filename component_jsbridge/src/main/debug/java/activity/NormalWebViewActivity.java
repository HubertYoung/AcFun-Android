package activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.just.agentweb.ChromeClientCallbackManager;
import com.kento.common.utils.BarUtils;
import com.kento.common.utils.CommonLog;
import com.kento.component_jsbridge.R;
import com.kento.component_jsbridge.jsbridge.BridgeWebViewClient;
import com.kento.component_jsbridge.jsbridge.tool.ScrollWebView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import base.BaseWebViewActivity;
import butterknife.BindView;

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
public class NormalWebViewActivity extends BaseWebViewActivity {
	@BindView( R.id.appbar )
	AppBarLayout appbar;
	@BindView( R.id.toolbar_head )
	Toolbar mToolbarHead;
	@BindView( R.id.tv_head_title )
	AppCompatTextView myHeadTitle;
	@BindView( R.id.fl_web_view_content )
	FrameLayout mFlwebViewContent;
	@BindView( R.id.srl_refresh_layout )
	SmartRefreshLayout mSrlRefreshLayout;

	private String url;
	private String nametitle;
	private boolean enableRefresh = true;
	private ScrollWebView mBridgeWebView;

	@Override
	public int getLayoutId() {
		return R.layout.activity_web_view_normal;
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
		super.initView( savedInstanceState );
		BarUtils.setPaddingSmart( mToolbarHead );

		getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN );
		url = getIntent().getStringExtra( "url" );
		nametitle = getIntent().getStringExtra( "title" );
		enableRefresh = getIntent().getBooleanExtra("refreshOperate",true);

		CommonLog.logi( "TAG", "url===" + url );

		initRefreshLayout();
		initAction();


		initCookie( url );
		refreshWebView( url );
	}

	@Override
	protected int getIndicatorColor() {
		return R.color.colorAccent;
//		return 0;
	}

	@Override
	protected WebView getWebView() {
		return mBridgeWebView;
	}
	@Override
	protected WebViewClient webViewClient() {
		BridgeWebViewClient bridgeWebViewClient = new BridgeWebViewClient( mBridgeWebView );
		return bridgeWebViewClient;
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

	private void initAction() {
		mToolbarHead.setNavigationOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				if (mAgentWeb!= null && !mAgentWeb.back() )// true表示AgentWeb处理了该事件
					finish();
			}
		} );
		mToolbarHead.setOnMenuItemClickListener( mOnMenuItemClickListener );
		mSrlRefreshLayout.setOnRefreshListener( new OnRefreshListener() {
			@Override
			public void onRefresh( RefreshLayout refreshLayout ) {
				mSrlRefreshLayout.finishRefresh( 500 );
				mAgentWeb.getLoader()
						 .reload(); //刷新
			}
		} );
//		mSrlRefreshLayout.setOnMultiPurposeListener( new SimpleMultiPurposeListener() {
//			@Override
//			public void onHeaderPulling( RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight ) {
//				mOffset = offset / 2;
//				mToolbarHead.setAlpha( 1 - Math.min( percent, 1 ) );
//			}
//
//			@Override
//			public void onHeaderReleasing( RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight ) {
//				mOffset = offset / 2;
//				mToolbarHead.setAlpha( 1 - Math.min( percent, 1 ) );
//			}
//		} );
		mBridgeWebView.setOnScrollChangeListener( new ScrollWebView.OnScrollChangeListener() {
			private int color = ContextCompat.getColor( getApplicationContext(), R.color.white ) & 0x00ffffff;

			@Override
			public void onPageEnd( int l, int t, int oldl, int oldt ) {

			}

			@Override
			public void onPageTop( int l, int t, int oldl, int oldt ) {
//				appbar.setBackgroundColor( ( 0 << 24 ) | color );
//				myHeadTitle.setVisibility( View.GONE );
			}

			@Override
			public void onScrollChanged( int l, int t, int oldl, int oldt ) {
//				appbar.setBackgroundColor( ( Math.min( ( int ) ( t * 0.7 ), 255 ) << 24 ) | color );
//				myHeadTitle.setVisibility( Math.min( t * 0.7, 255 ) > 200 ? View.VISIBLE : View.GONE );
			}
		} );
	}

	private void initRefreshLayout() {

		mSrlRefreshLayout.setRefreshHeader(new ClassicsHeader(this).setSpinnerStyle(SpinnerStyle.Translate));
		mSrlRefreshLayout.setRefreshFooter(new ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Translate));

		mSrlRefreshLayout.setEnableRefresh(this.enableRefresh);
		mSrlRefreshLayout.setEnableLoadMore(this.enableRefresh);

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
//			mPbWebviewLoading.setProgress( newProgress );
		}
	};
	private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
		@Override
		public void onReceivedTitle( WebView view, String title ) {
			if ( myHeadTitle != null ) myHeadTitle.setText( title );
		}
	};

	@Override
	public void initToolBar() {
		mToolbarHead.setBackgroundResource( R.color.transparent );
		mToolbarHead.setTitle( "" );
		mToolbarHead.inflateMenu( R.menu.menu_webview );
//		if ( AppUtils.isDebuggable() ) {
//			mToolbarHead.getMenu()
//						.findItem( R.id.error_website )
//						.setVisible( true );
//		}
		myHeadTitle.setVisibility( View.VISIBLE );
	}

	//菜单事件
	private Toolbar.OnMenuItemClickListener mOnMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick( MenuItem item ) {

			switch ( item.getItemId() ) {
//				case R.id.share:
////					MobShareBottomDialog dialog = new MobShareBottomDialog( activity, "", "", "", MobShareBottomDialog.NORMAL );
////					dialog.showAnim( new BounceTopEnter() );
////					dialog.setCanceledOnTouchOutside( false );
////					dialog.show();
//					return true;
//				case R.id.refresh:
//					if ( mAgentWeb != null ) mAgentWeb.getLoader()
//													  .reload(); //刷新
//					return true;
//
//				case R.id.copy:
//					if ( mAgentWeb != null ) ClipboardUtils.copyText( mAgentWeb.getWebCreator()
//																			   .get()
//																			   .getUrl() );
//					return true;
//				case R.id.default_browser:
//					if ( mAgentWeb != null ) openBrowser( mAgentWeb.getWebCreator()
//																   .get()
//																   .getUrl() );
//					return true;
//				case R.id.default_clean:
//					toCleanWebCache();
//					return true;
//				case R.id.error_website:
//					loadErrorWebSite();
//					return true;
				default:
					return false;
			}

		}
	};


	public static void launch( Context context, String url, String title ,boolean enableRefreshOperate) {
		Intent intent = new Intent( context, NormalWebViewActivity.class );
		intent.putExtra( "url", url );
		intent.putExtra( "title", title );
		intent.putExtra("refreshOperate",enableRefreshOperate);
		context.startActivity( intent );
	}

	/**
	 * 启动NormalWebViewActivity 默认启动刷新操作
	 * @param context
	 * @param url
	 * @param title
	 */
	public static void launch( Context context, String url, String title) {
		NormalWebViewActivity.launch(context,url,title,true);
	}
}
