package com.hubertyoung.common.utils;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.webkit.URLUtil;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.R;
import com.hubertyoung.common.constant.AppSpConfig;
import com.hubertyoung.common.entity.LoadingTheme;
import com.hubertyoung.common.utils.data.JsonUtils;
import com.hubertyoung.common.utils.data.SPUtils;

import java.io.File;

import android.support.v4.content.ContextCompat;

public class LoadingThemeUtil {
	public static final String LOADING_RESOURCE;
	public static final String PTR = "ptr";
	public static final String LOADING = "loading";
	public static final String EMPTY = "empty";
	public static final int e = 100;
	public static final int f = 1;
	static Drawable[] sDrawables = new Drawable[]{ ContextCompat.getDrawable( CommonApplication.getAppContext(), R.drawable.ptr_loading_1 ), //
			ContextCompat.getDrawable( CommonApplication.getAppContext(), R.drawable.ptr_loading_2 ), //
			ContextCompat.getDrawable( CommonApplication.getAppContext(), R.drawable.ptr_loading_3 ), //
			ContextCompat.getDrawable( CommonApplication.getAppContext(), R.drawable.ptr_loading_4 ), //
			ContextCompat.getDrawable( CommonApplication.getAppContext(), R.drawable.ptr_loading_5 ) };

	static {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( CommonApplication.getAppContext().getFilesDir().getPath() );
		stringBuilder.append( File.separator );
		stringBuilder.append( "loading_resource" );
		LOADING_RESOURCE = stringBuilder.toString();
	}

	public static int a() {
		LoadingTheme loadingTheme = haveShowLoadingTheme();
		if ( loadingTheme == null ) {
			return R.layout.widget_loading_holder;
		}
		String guessFileName;
		if ( loadingTheme.pageLoadingImages.length > 1 ) {
			guessFileName = URLUtil.guessFileName( loadingTheme.pageLoadingImages[ 1 ], null, null );
		} else {
			guessFileName = URLUtil.guessFileName( loadingTheme.pageLoadingImages[ 0 ], null, null );
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( LOADING_RESOURCE );
		stringBuilder.append( File.separator );
		stringBuilder.append( loadingTheme.id );
		stringBuilder.append( File.separator );
		stringBuilder.append( "loading" );
		stringBuilder.append( File.separator );
		stringBuilder.append( guessFileName );
		if ( new File( stringBuilder.toString() ).exists() ) {
			return R.layout.widget_loading_holder_spring_festival;
		}
		return R.layout.widget_loading_holder;
	}

	public static String getPageLoadingFileImages() {
		LoadingTheme loadingTheme = haveShowLoadingTheme();
		if ( loadingTheme == null ) {
			return Utils.getDrawableStr( R.drawable.image_loading_holder );
		}
		String guessFileName;
		if ( loadingTheme.pageLoadingImages.length > 1 ) {
			guessFileName = URLUtil.guessFileName( loadingTheme.pageLoadingImages[ 1 ], null, null );
		} else {
			guessFileName = URLUtil.guessFileName( loadingTheme.pageLoadingImages[ 0 ], null, null );
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( LOADING_RESOURCE );
		stringBuilder.append( File.separator );
		stringBuilder.append( loadingTheme.id );
		stringBuilder.append( File.separator );
		stringBuilder.append( "loading" );
		stringBuilder.append( File.separator );
		stringBuilder.append( guessFileName );
		File file = new File( stringBuilder.toString() );
		if ( file.exists() ) {
			return Utils.getDrawableStr( file );
		}
		return Utils.getDrawableStr( R.drawable.image_loading_holder );
	}

	public static Drawable getPageLoadingImages() {
		LoadingTheme loadingTheme = haveShowLoadingTheme();
		if ( loadingTheme == null || loadingTheme.pageLoadingImages == null || loadingTheme.pageLoadingImages.length <= 0 ) {
			return ContextCompat.getDrawable( CommonApplication.getAppContext(), R.drawable.image_loading_holder_start );
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( LOADING_RESOURCE );
		stringBuilder.append( File.separator );
		stringBuilder.append( loadingTheme.id );
		stringBuilder.append( File.separator );
		stringBuilder.append( "loading" );
		stringBuilder.append( File.separator );
		stringBuilder.append( URLUtil.guessFileName( loadingTheme.pageLoadingImages[ 0 ], null, null ) );
		File file = new File( stringBuilder.toString() );
		if ( file.exists() ) {
			return Drawable.createFromPath( file.getPath() );
		}
		return ContextCompat.getDrawable( CommonApplication.getAppContext(), R.drawable.image_loading_holder_start );
	}

	public static Drawable d() {
		LoadingTheme loadingTheme = haveShowLoadingTheme();
		if ( loadingTheme == null || loadingTheme.pullDownImages == null || loadingTheme.pullDownImages.length <= 0 ) {
			return ContextCompat.getDrawable( CommonApplication.getAppContext(), R.drawable.animation_acptr_loading );
		}
		AnimationDrawable animationDrawable = new AnimationDrawable();
		for (String str : loadingTheme.pullDownImages) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append( LOADING_RESOURCE );
			stringBuilder.append( File.separator );
			stringBuilder.append( loadingTheme.id );
			stringBuilder.append( File.separator );
			stringBuilder.append( PTR );
			stringBuilder.append( File.separator );
			stringBuilder.append( URLUtil.guessFileName( str, null, null ) );
			File file = new File( stringBuilder.toString() );
			if ( file.exists() && animationDrawable != null ) {
				animationDrawable.addFrame( Drawable.createFromPath( file.getPath() ), 100 );
			}
		}
		return animationDrawable;
	}

	public static Drawable[] e() {
		LoadingTheme loadingTheme = haveShowLoadingTheme();
		if ( loadingTheme == null || loadingTheme.pullDownImages == null || loadingTheme.pullDownImages.length <= 0 ) {
			return sDrawables;
		}
		int i = 5;
		if ( loadingTheme.pullDownImages.length < 5 ) {
			i = loadingTheme.pullDownImages.length;
		}
		Drawable[] drawableArr = new Drawable[ i ];
		for (i = 0; i < drawableArr.length; i++) {
			String str = loadingTheme.pullDownImages[ i ];
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append( LOADING_RESOURCE );
			stringBuilder.append( File.separator );
			stringBuilder.append( loadingTheme.id );
			stringBuilder.append( File.separator );
			stringBuilder.append( PTR );
			stringBuilder.append( File.separator );
			stringBuilder.append( URLUtil.guessFileName( str, null, null ) );
			drawableArr[ i ] = Drawable.createFromPath( new File( stringBuilder.toString() ).getPath() );
		}
		return drawableArr;
	}

	public static Drawable f() {
		LoadingTheme loadingTheme = haveShowLoadingTheme();
		if ( loadingTheme == null || loadingTheme.pullDownImages == null || loadingTheme.pullDownImages.length <= 0 ) {
			return ContextCompat.getDrawable( CommonApplication.getAppContext(), R.drawable.ptr_loading_9 );
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( LOADING_RESOURCE );
		stringBuilder.append( File.separator );
		stringBuilder.append( loadingTheme.id );
		stringBuilder.append( File.separator );
		stringBuilder.append( PTR );
		stringBuilder.append( File.separator );
		stringBuilder.append( URLUtil.guessFileName( loadingTheme.pullDownImages[ loadingTheme.pullDownImages.length - 1 ], null, null ) );
		File file = new File( stringBuilder.toString() );
		if ( file.exists() ) {
			return Drawable.createFromPath( file.getPath() );
		}
		return ContextCompat.getDrawable( CommonApplication.getAppContext(), R.drawable.ptr_loading_9 );
	}

	public static Drawable g() {
		return h();
	}

	public static Drawable h() {
		LoadingTheme loadingTheme = haveShowLoadingTheme();
		if ( loadingTheme == null || loadingTheme.pageEmptyImages == null || loadingTheme.pageEmptyImages.length <= 0 ) {
			return ContextCompat.getDrawable( CommonApplication.getAppContext(), R.drawable.img_load_not_success );
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( LOADING_RESOURCE );
		stringBuilder.append( File.separator );
		stringBuilder.append( loadingTheme.id );
		stringBuilder.append( File.separator );
		stringBuilder.append( EMPTY );
		stringBuilder.append( File.separator );
		stringBuilder.append( URLUtil.guessFileName( loadingTheme.pageEmptyImages[ 0 ], null, null ) );
		File file = new File( stringBuilder.toString() );
		if ( file.exists() ) {
			return Drawable.createFromPath( file.getPath() );
		}
		return ContextCompat.getDrawable( CommonApplication.getAppContext(), R.drawable.img_load_not_success );
	}

	public static LoadingTheme getLoadingTheme() {
		String string = SPUtils.getSharedStringData( AppSpConfig.LOADING_THEME, "" );

		if ( TextUtils.isEmpty( string ) ) {
			return null;
		}
		try {
			return JsonUtils.jsonToBean( string, LoadingTheme.class );
		} catch ( Exception e ) {
			return null;
		}
	}

	public static void setLoadingTheme( LoadingTheme loadingTheme ) {
		if ( loadingTheme != null ) {
//            CommonApplication.getAppContext().getSharedPreferences(, 0).edit().putString(HomeDataRepository.e, JSON.toJSONString(loadingTheme)).apply();
			SPUtils.setSharedStringData( AppSpConfig.LOADING_THEME, JsonUtils.objToString( loadingTheme ) );
		}
	}

	public static LoadingTheme haveShowLoadingTheme() {
		LoadingTheme loadingTheme = getLoadingTheme();
		if ( loadingTheme != null && loadingTheme.themeVersion == 1 && loadingTheme.hasFinishDownload && System.currentTimeMillis() > loadingTheme.startTime && System.currentTimeMillis() <
                loadingTheme.endTime ) {
			return loadingTheme;
		}
		return null;
	}
}
