package base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.ColorRes;
import androidx.appcompat.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.ChromeClientCallbackManager;
import com.just.agentweb.DefaultWebClient;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.base.BaseModel;
import com.hubertyoung.common.base.BasePresenter;
import com.hubertyoung.common.net.http.HttpUtils;
import com.hubertyoung.common.utils.CommonLog;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.component_jsbridge.R;

import java.util.List;

import okhttp3.Cookie;

/**
 * <br>
 * function:base的webview
 * <p>
 *
 * @author:Yang
 * @date:2018/3/5 下午5:52
 * @since:V$VERSION
 * @desc:com.gent.cardsharelife.ui.webview.base
 */
public abstract class BaseWebViewActivity< T extends BasePresenter, E extends BaseModel > extends BaseActivity< T, E > {
	public BaseActivity activity;
	public AgentWeb mAgentWeb;
	private AgentWeb.PreAgentWeb mPreAgentWeb;

	@Override
	public void initView( Bundle savedInstanceState ) {
		activity = this;
		//
//
//
//							.defaultProgressBarColor()
//							.setIndicatorColorWithHeight( getResources().getColor( R.color.colorAccent ), 3 )
//							.setWebLayout(new WebLayout(this))
//打开并行下载 , 默认串行下载
//下载图标
//打开其他应用时，弹窗咨询用户是否前往其他应用
//拦截找不到相关页面的Scheme
		AgentWeb.ConfigIndicatorBuilder configIndicatorBuilder = AgentWeb.with( this )
																		 .setAgentWebParent( getWebContentView(), new LinearLayoutCompat.LayoutParams( -1, -1 ) );
		AgentWeb.CommonAgentBuilder commonAgentBuilder;
		if ( getIndicatorColor() != 0 ) {
			commonAgentBuilder = configIndicatorBuilder.useDefaultIndicator()
													   .setIndicatorColorWithHeight( getResources().getColor( getIndicatorColor() ), 3 );
		} else {
			commonAgentBuilder = configIndicatorBuilder.closeProgressBar();
		}

		mPreAgentWeb = commonAgentBuilder.setReceivedTitleCallback( chromeClientCallbackManager() )
										 .setWebChromeClient( webChromeClient() )
										 .setWebViewClient( webViewClient() )
										 .setMainFrameErrorView( R.layout.agentweb_error_page, -1 )
										 .setSecurityType( AgentWeb.SecurityType.strict )
//							.setWebLayout(new WebLayout(this))
										 .setWebView( getWebView() )
										 .openParallelDownload()//打开并行下载 , 默认串行下载
										 .setNotifyIcon( R.drawable.ic_file_download_black_24dp ) //下载图标
										 .setOpenOtherPageWays( DefaultWebClient.OpenOtherPageWays.ASK )//打开其他应用时，弹窗咨询用户是否前往其他应用
										 .interceptUnkownScheme() //拦截找不到相关页面的Scheme
										 .createAgentWeb()//
										 .ready();

	}

	protected abstract WebViewClient webViewClient();

	@ColorRes
	protected abstract int getIndicatorColor();

	protected void initCookie( String url ) {
		//测试Cookies
		try {

			CommonLog.logi( "WebViewActivity", "cookies:" + AgentWebConfig.getCookiesByUrl( url ) );
			AgentWebConfig.removeAllCookies( new ValueCallback< Boolean >() {
				@Override
				public void onReceiveValue( Boolean value ) {
					CommonLog.logi( "WebViewActivity", "onResume():" + value );
				}
			} );

			String tagInfo = AgentWebConfig.getCookiesByUrl( url );
			CommonLog.logi( "WebViewActivity", "tag:" + tagInfo );

			List< Cookie > cookies = HttpUtils.getInstance()
											  .getPersistentCookieJar()
											  .getPersistor()
											  .loadAll();
			for (Cookie cookie : cookies) {
				AgentWebConfig.syncCookie( url, cookie.toString() );
			}

			String tag = AgentWebConfig.getCookiesByUrl( url );
			CommonLog.logi( "WebViewActivity", "tag:" + tag );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	protected void refreshWebView( String url ) {
//		if ( mAgentWeb != null ) {
			mAgentWeb = mPreAgentWeb.go( url );
//		}
	}

	protected abstract WebView getWebView();

	protected abstract WebChromeClient webChromeClient();

	protected abstract ChromeClientCallbackManager.ReceivedTitleCallback chromeClientCallbackManager();

	protected abstract ViewGroup getWebContentView();

	//清除 WebView 缓存
	public void toCleanWebCache() {

		if ( this.mAgentWeb != null ) {
			//清理所有跟WebView相关的缓存 ，数据库， 历史记录 等。
			this.mAgentWeb.clearWebCache();
			ToastUtil.showSuccess( "已清理缓存" );
			//清空所有 AgentWeb 硬盘缓存，包括 WebView 的缓存 , AgentWeb 下载的图片 ，视频 ，apk 等文件。
//            AgentWebConfig.clearDiskCache(this.getContext());
		}
	}

	/**
	 * 打开浏览器
	 *
	 * @param targetUrl 外部浏览器打开的地址
	 */
	public void openBrowser( String targetUrl ) {
		if ( TextUtils.isEmpty( targetUrl ) || targetUrl.startsWith( "file://" ) ) {
			Toast.makeText( this, targetUrl + " 该链接无法使用浏览器打开。", Toast.LENGTH_SHORT )
				 .show();
			return;
		}
		Intent intent = new Intent();
		intent.setAction( "android.intent.action.VIEW" );
		Uri mUri = Uri.parse( targetUrl );
		intent.setData( mUri );
		startActivity( intent );
	}

	@Override
	protected void onPause() {
		if ( mAgentWeb != null ) {
			mAgentWeb.getWebLifeCycle()
					 .onPause();
		}
		super.onPause();
	}

	//这里用于测试错误页的显示
	public void loadErrorWebSite() {
		if ( mAgentWeb != null ) {
			mAgentWeb.getLoader()
					 .loadUrl( "http://www.unkownwebsiteblog.me" );
		}
	}

	@Override
	public boolean onKeyDown( int keyCode, KeyEvent event ) {
		if ( mAgentWeb != null ) {

			if ( mAgentWeb.handleKeyEvent( keyCode, event ) ) {
				return true;

			}
		}
		return super.onKeyDown( keyCode, event );
	}

	@Override
	protected void onResume() {
		super.onResume();
		if ( mAgentWeb != null ) {
			mAgentWeb.getWebLifeCycle()
					 .onResume();

		}
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		//mAgentWeb.destroy();
		if ( mAgentWeb != null ) {
			mAgentWeb.getWebLifeCycle()
					 .onDestroy();
		}
	}

	@Override
	protected void onActivityResult( int requestCode, int resultCode, Intent data ) {

		Log.i( "Info", "result:" + requestCode + " result:" + resultCode );
		if ( mAgentWeb != null ) {
			mAgentWeb.uploadFileResult( requestCode, resultCode, data );
		}
		super.onActivityResult( requestCode, resultCode, data );
	}
}
