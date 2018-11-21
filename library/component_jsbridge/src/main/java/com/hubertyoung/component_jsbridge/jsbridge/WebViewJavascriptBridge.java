package com.hubertyoung.component_jsbridge.jsbridge;


public interface WebViewJavascriptBridge {
	
	void send( String data );
	void send( String data, CallBackFunction responseCallback );
	
	

}
