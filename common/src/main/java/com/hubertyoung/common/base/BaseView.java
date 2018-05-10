package com.hubertyoung.common.base;


public interface BaseView {
	/*******内嵌加载*******/
	void showLoading( String title, int type );

	void stopLoading();

	void showErrorTip( String msg );
}
