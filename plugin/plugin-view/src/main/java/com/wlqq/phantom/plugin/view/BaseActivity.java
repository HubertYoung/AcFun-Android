package com.wlqq.phantom.plugin.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.wlqq.phantom.communication.PhantomUtils;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/3/19 18:46
 * @since:
 * @see BaseActivity
 */
public abstract class BaseActivity extends FragmentActivity {

	protected Context that;

	@Override
	protected void onCreate( @Nullable Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		if ( getLayoutId() != 0 ) {
			setContentView( getLayoutId() );
			that = PhantomUtils.getHostContext( this );
			initView( savedInstanceState );
		} else {
			Log.e( "TAG", "--->bindLayout() return 0" );
		}
	}

	protected abstract void initView( Bundle savedInstanceState );

	protected abstract int getLayoutId();

	protected int getHostMipmap(String res){
		try {
			return that.getResources().getIdentifier( res, "mipmap", BuildConfig.HOST_PKG );
		} catch ( Exception e ) {
			e.printStackTrace();
			return 0;
		}
	}
}
