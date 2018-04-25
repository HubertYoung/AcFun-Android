package com.kento.component.basic.commonwidget.jsbridge;


public interface WebViewJavascriptBridge {
	
	void send( String data );
	void send( String data, CallBackFunction responseCallback );
	
	

}
