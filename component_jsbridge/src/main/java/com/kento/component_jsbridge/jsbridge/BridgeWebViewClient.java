package com.hubertyoung.component_jsbridge.jsbridge;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hubertyoung.component_jsbridge.jsbridge.tool.ADFilterTool;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


/**
 * Created by bruce on 10/28/15.
 */
public class BridgeWebViewClient extends WebViewClient {
    private BridgeWebView webView;

    private ClientListener clientListener;
    public BridgeWebViewClient(BridgeWebView webView) {
        this.webView = webView;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (clientListener != null && url != null ) {
            if (url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("tel:")) {
                clientListener.callPhone(view, url);
//            view.loadUrl(url);
                return true;
            }
        }else{
            return super.shouldOverrideUrlLoading(view, url);
        }
        if (url.startsWith(BridgeUtil.YY_RETURN_DATA)) { // 如果是返回数据
            webView.handlerReturnData(url);
            return true;
        } else if (url.startsWith(BridgeUtil.YY_OVERRIDE_SCHEMA)) { //
            webView.flushMessageQueue();
            return true;
        } else {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        String url = request.getUrl().toString();
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (clientListener != null && url != null ) {
            if (url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("tel:")) {
                clientListener.callPhone(view, url);
//            view.loadUrl(url);
                return true;
            }
        }else{
            return super.shouldOverrideUrlLoading(view, request);
        }

        if (url.startsWith(BridgeUtil.YY_RETURN_DATA)) { // 如果是返回数据
            webView.handlerReturnData(url);
            return true;
        } else if (url.startsWith(BridgeUtil.YY_OVERRIDE_SCHEMA)) { //
            webView.flushMessageQueue();
            return true;
        } else{
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if(clientListener != null) {
            clientListener.onPageStarted();
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if(clientListener != null) {
            clientListener.onPageFinished();
        }
        if (BridgeWebView.toLoadJs != null) {
            BridgeUtil.webViewLoadLocalJs(view, BridgeWebView.toLoadJs);
        }

        //
        if (webView.getStartupMessage() != null) {
            for (Message m : webView.getStartupMessage()) {
                webView.dispatchMessage(m);
            }
            webView.setStartupMessage(null);
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
    }
    public void setClientListener(ClientListener clientListener) {
        this.clientListener = clientListener;
    }
    @Override
    public WebResourceResponse shouldInterceptRequest( WebView view, String url) {
        url = url.toLowerCase();
        if ( !url.contains( url ) ) {
            if ( !ADFilterTool.hasAd( webView.getContext(), url ) ) {
                return super.shouldInterceptRequest( view, url );
            } else {
                return new WebResourceResponse( null, null, null );
            }
        } else {
            return super.shouldInterceptRequest( view, url );
        }
    }
}