package com.hubertyoung.baseplatform.sdk;

import android.app.Activity;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/10 18:47
 * @since:V1.0.0
 * @desc:com.hubertyoung.baseplatform.sdk
 */
public class Sdk< T extends IResult > {

	private Map< Activity, Map< String, T > > mMap = new WeakHashMap<>();
	private Map< String, IFactory< T > > mFactories = new HashMap<>();

	private OnCallback mDefaultCallback;

	public OnCallback getDefaultCallback() {
		return mDefaultCallback;
	}

	public void setDefaultCallback( OnCallback callback ) {
		mDefaultCallback = callback;
	}

	public void register( String name, String appId, String appSecret, Class clazz ) {
		mFactories.put( name, new DefaultFactory( name, appId, appSecret, clazz ) );

	}

	public void register( IFactory factory ) {
		mFactories.put( factory.getPlatform().getName(), factory );
	}

	public boolean isSupport( String platform ) {
		return mFactories.get( platform ) != null;
	}

	public T get( Activity activity, String platform ) {
		Map< String, T > sub = mMap.get( activity );
		if ( sub == null ) {
			sub = new HashMap<>();
			mMap.put( activity, sub );
		}
		T api = sub.get( platform );
		if ( api == null ) {
			IFactory< T > factory = mFactories.get( platform );
			if ( factory != null ) {
				api = factory.create( activity );
				if ( api != null ) {
					sub.put( platform, api );
				}
			}
		}
		return api;
	}

	public void onHandleResult( Activity activity, int requestCode, int resultCode, Intent data ) {
		if ( mMap.containsKey( activity ) ) {
			for (T api : mMap.get( activity ).values()) {
				if ( api != null ) {
					api.onResult( requestCode, resultCode, data );
				}
			}
		}
	}
}
