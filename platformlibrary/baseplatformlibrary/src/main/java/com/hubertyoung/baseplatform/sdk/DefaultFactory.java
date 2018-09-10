package com.hubertyoung.baseplatform.sdk;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.lang.reflect.Constructor;


public class DefaultFactory< T extends IResult > implements IFactory< T > {
	final OtherPlatform platform;

	final Class< T > clazz;

	public DefaultFactory( @NonNull String name, String appId, Class< T > clazz ) {
		this.platform = new OtherPlatform( name, appId );
		this.clazz = clazz;
	}

	public DefaultFactory( @NonNull OtherPlatform platform, Class< T > clazz ) {
		this.platform = platform;
		this.clazz = clazz;
	}

	@Override
	public OtherPlatform getPlatform() {
		return platform;
	}

	@Override
	public T create( Activity activity ) {
		try {
//            if(activity instanceof FragmentActivity) {
//                Constructor<T> constructor = clazz.getDeclaredConstructor(FragmentActivity.class, Platform.class);
//                constructor.setAccessible(true);
//                return constructor.newInstance((FragmentActivity)activity, platform);
//            }else {
			Constructor< T > constructor = clazz.getDeclaredConstructor( Activity.class, OtherPlatform.class );
			constructor.setAccessible( true );
			return constructor.newInstance( activity, platform );
//            }
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}
}
