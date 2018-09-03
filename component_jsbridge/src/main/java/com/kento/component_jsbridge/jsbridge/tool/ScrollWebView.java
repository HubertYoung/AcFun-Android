package com.hubertyoung.component_jsbridge.jsbridge.tool;

import android.content.Context;
import android.util.AttributeSet;

import com.hubertyoung.component_jsbridge.jsbridge.BridgeWebView;


/**
 * <br>
 * function:滑动的webview
 * <p>
 *
 * @author:Yang
 * @date:2018/2/9 上午12:17
 * @since:V1.0
 * @desc:com.hubertyoung.common.commonwidget
 */
public class ScrollWebView extends BridgeWebView {
	private OnScrollChangeListener mOnScrollChangeListener;

	public ScrollWebView( Context context, AttributeSet attrs ) {
		super( context, attrs );
	}

	public ScrollWebView( Context context, AttributeSet attrs, int defStyle ) {
		super( context, attrs, defStyle );
	}

	public ScrollWebView( Context context ) {
		super( context );
	}

	@Override
	protected void onScrollChanged( int l, int t, int oldl, int oldt ) {
		super.onScrollChanged( l, t, oldl, oldt );
		// webview的高度
		float webcontent = getContentHeight() * getScale();
		// 当前webview的高度
		float webnow = getHeight() + getScrollY();
		if ( Math.abs( webcontent - webnow ) < 1 ) {
			//处于底端
			mOnScrollChangeListener.onPageEnd( l, t, oldl, oldt );
		} else if ( getScrollY() == 0 ) {
			//处于顶端
			mOnScrollChangeListener.onPageTop( l, t, oldl, oldt );
		} else {
			mOnScrollChangeListener.onScrollChanged( l, t, oldl, oldt );
		}
	}

	public void setOnScrollChangeListener( OnScrollChangeListener listener ) {
		this.mOnScrollChangeListener = listener;
	}

	public interface OnScrollChangeListener {

		public void onPageEnd( int l, int t, int oldl, int oldt );

		public void onPageTop( int l, int t, int oldl, int oldt );

		public void onScrollChanged( int l, int t, int oldl, int oldt );

	}

}