package com.hubertyoung.common.utils.display;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hubertyoung.common.CommonApplication;

import android.support.v7.app.AppCompatActivity;


/**
 * 屏幕相关的辅助类
 */
public class DisplayUtil {
	private static final String EXTRA_DEF_KEYBOARDHEIGHT = "DEF_KEYBOARDHEIGHT";
	private static int sSDefKeyboardHeight;

	private DisplayUtil() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException( "cannot be instantiated" );
	}


	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 *
	 * @param pxValue （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int px2dip( float pxValue ) {
		final float scale = CommonApplication.getAppContext().getResources().getDisplayMetrics().density;
		return ( int ) ( pxValue / scale + 0.5f );
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 *
	 * @param dipValue （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int dip2px( float dipValue ) {
		final float scale = CommonApplication.getAppContext().getResources().getDisplayMetrics().density;
		return ( int ) ( dipValue * scale + 0.5f );
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp( float pxValue ) {
		final float fontScale = CommonApplication.getAppContext().getResources().getDisplayMetrics().scaledDensity;
		return ( int ) ( pxValue / fontScale + 0.5f );
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 *
	 * @param spValue （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px( float spValue ) {
		final float fontScale = CommonApplication.getAppContext().getResources().getDisplayMetrics().scaledDensity;
		return ( int ) ( spValue * fontScale + 0.5f );
	}

	/**
	 * 直接获取控件的宽、高
	 *
	 * @param view
	 * @return int[]
	 */
	public static int[] getWidgetWH( final View view ) {
		ViewTreeObserver vto2 = view.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener( new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				view.getViewTreeObserver().removeGlobalOnLayoutListener( this );
			}
		} );
		return new int[]{ view.getWidth(), view.getHeight() };
	}

	/**
	 * 直接获取控件的宽、高
	 *
	 * @param view
	 * @return int[]
	 */
	public static int getViewHeight( final View view ) {
		ViewTreeObserver vto2 = view.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener( new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				view.getViewTreeObserver().removeGlobalOnLayoutListener( this );
			}
		} );
		return view.getHeight();
	}

	/**
	 * 直接获取控件的宽、高
	 *
	 * @param view
	 * @return int[]
	 */
	public static int getViewWidth( final View view ) {
		ViewTreeObserver vto2 = view.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener( new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				view.getViewTreeObserver().removeGlobalOnLayoutListener( this );
			}
		} );
		return view.getWidth();
	}

	/**
	 * 获得屏幕宽度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenWidth( Context context ) {
		WindowManager wm = ( WindowManager ) context.getSystemService( Context.WINDOW_SERVICE );
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics( outMetrics );
		return outMetrics.widthPixels;
	}
	/**
	 * 获得屏幕高度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenHeight( Context context ) {
		WindowManager wm = ( WindowManager ) context.getSystemService( Context.WINDOW_SERVICE );
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics( outMetrics );
		return outMetrics.heightPixels;
	}


	/**
	 * 获取控件的宽
	 *
	 * @param view
	 * @return
	 */
	public static int getWidgetWidth( View view ) {
		int w = View.MeasureSpec.makeMeasureSpec( 0, View.MeasureSpec.UNSPECIFIED );
		int h = View.MeasureSpec.makeMeasureSpec( 0, View.MeasureSpec.UNSPECIFIED );
		view.measure( w, h );//先度量
		int width = view.getMeasuredWidth();
		return width;
	}

	/**
	 * 获取控件的高
	 *
	 * @param view
	 * @return
	 */
	public static int getWidgetHeight( View view ) {
		int w = View.MeasureSpec.makeMeasureSpec( 0, View.MeasureSpec.UNSPECIFIED );
		int h = View.MeasureSpec.makeMeasureSpec( 0, View.MeasureSpec.UNSPECIFIED );
		view.measure( w, h );//先度量
		int height = view.getMeasuredHeight();
		return height;
	}

	/**
	 * 设置控件宽
	 *
	 * @param view
	 * @param width
	 */
	public static void setWidgetWidth( View view, int width ) {
		LinearLayout.LayoutParams params = ( LinearLayout.LayoutParams ) view.getLayoutParams();
		params.width = width;
		view.setLayoutParams( params );
	}

	/**
	 * 设置控件高
	 *
	 * @param view
	 * @param height
	 */
	public static void setWidgetHeight( View view, int height ) {
		LinearLayout.LayoutParams params = ( LinearLayout.LayoutParams ) view.getLayoutParams();
		params.height = height;
		view.setLayoutParams( params );
	}


	//----------------------------------------------

	/**
	 * 获取当前屏幕截图，包含状态栏（这个方法没测试通过）
	 *
	 * @param activity
	 * @return Bitmap
	 */
	public static Bitmap snapShotWithStatusBar( AppCompatActivity activity ) {
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled( true );
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		int width = getScreenWidth( activity );
		int height = getScreenHeight( activity );
		Bitmap bp = null;
		bp = Bitmap.createBitmap( bmp, 0, 0, width, height );
		view.destroyDrawingCache();
		return bp;
	}

	/**
	 * 获取当前屏幕截图，不包含状态栏（这个方法没测试通过）
	 *
	 * @param activity
	 * @return Bitmap
	 */
	public static Bitmap snapShotWithoutStatusBar( AppCompatActivity activity ) {
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled( true );
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame( frame );
		int statusBarHeight = frame.top;

		int width = getScreenWidth( activity );
		int height = getScreenHeight( activity );
		Bitmap bp = null;
		bp = Bitmap.createBitmap( bmp, 0, statusBarHeight, width, height - statusBarHeight );
		view.destroyDrawingCache();
		return bp;
	}

	public static int getDefKeyboardHeight( Context context ) {
		sSDefKeyboardHeight = 0;
		if ( sSDefKeyboardHeight == 0 ) {   //evaluate keyboard height
			sSDefKeyboardHeight = getScreenHeight( context ) * 3 / 7;
		}
		final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences( context );
		int height = settings.getInt( EXTRA_DEF_KEYBOARDHEIGHT, 0 );
		if ( height > 0 && sSDefKeyboardHeight != height ) {
			setDefKeyboardHeight( context, height );
		}
		return sSDefKeyboardHeight;
	}

	public static void setDefKeyboardHeight( Context context, int height ) {
		if ( sSDefKeyboardHeight != height ) {
			final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences( context );
			settings.edit().putInt( EXTRA_DEF_KEYBOARDHEIGHT, height ).apply();
		}
		sSDefKeyboardHeight = height;
	}

	/**
	 * 获取屏幕大小
	 *
	 * @param context
	 * @return
	 */
	public static int[] getScreenPixelSize( Context context ) {
		DisplayMetrics metrics = getDisplayMetrics( context );
		return new int[]{ metrics.widthPixels, metrics.heightPixels };
	}

	private static DisplayMetrics getDisplayMetrics( Context context ) {
		AppCompatActivity activity;
		if ( !( context instanceof AppCompatActivity ) && context instanceof ContextWrapper ) {
			activity = ( AppCompatActivity ) ( ( ContextWrapper ) context ).getBaseContext();
		} else {
			activity = ( AppCompatActivity ) context;
		}
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics( metrics );
		return metrics;
	}
}
