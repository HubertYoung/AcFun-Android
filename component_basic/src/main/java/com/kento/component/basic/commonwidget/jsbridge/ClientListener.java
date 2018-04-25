package com.kento.component.basic.commonwidget.jsbridge;

import android.webkit.WebView;

public interface ClientListener {
	
	void onPageStarted();
	void onPageFinished();
	void callPhone( WebView view, String url );
}
