package com.kento.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.kento.common.os.OSUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 透明状态栏
 */
public class StatusBarCompat {

    private static final int COLOR_TRANSLUCENT = Color.parseColor( "#00000000" );

    public static final int DEFAULT_COLOR_ALPHA = 255;

    /**
     * set statusBarColor
     *
     * @param statusColor color
     * @param alpha       0 - 255
     */
    public static void setStatusBarColor( Activity activity, int statusColor, int alpha ) {
        setStatusBarColor( activity, calculateStatusBarColor( statusColor, alpha ) );
    }

    public static void setStatusBarColor( Activity activity,@ColorInt int statusColor ) {
        Window window = activity.getWindow();
        ViewGroup mContentView = ( ViewGroup ) activity.findViewById( Window.ID_ANDROID_CONTENT );

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
            //First translucent status bar.
            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
                window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
                //After LOLLIPOP not translucent status bar
                window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
                //Then call setStatusBarColor.
                window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS );
                window.setStatusBarColor( statusColor );
                //set child View not fill the system window
                View mChildView = mContentView.getChildAt( 0 );
                if ( mChildView != null ) {
                    ViewCompat.setFitsSystemWindows( mChildView, true );
                }
            } else {
                ViewGroup mDecorView = ( ViewGroup ) window.getDecorView();
                if ( mDecorView.getTag() != null && mDecorView.getTag() instanceof Boolean && ( Boolean ) mDecorView.getTag() ) {
                    //if has add fake status bar view
                    window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
                    View mStatusBarView = mDecorView.getChildAt( 0 );
                    if ( mStatusBarView != null ) {
                        mStatusBarView.setBackgroundColor( statusColor );
                    }
                } else {
                    int statusBarHeight = getStatusBarHeight( activity );
                    //add margin
                    View mContentChild = mContentView.getChildAt( 0 );
                    if ( mContentChild != null ) {
                        window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
                        ViewCompat.setFitsSystemWindows( mContentChild, false );
                        FrameLayout.LayoutParams lp = ( FrameLayout.LayoutParams ) mContentChild.getLayoutParams();
                        lp.topMargin += statusBarHeight;
                        mContentChild.setLayoutParams( lp );

                        //add fake status bar view
                        View mStatusBarView = new View( activity );
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight );
                        layoutParams.gravity = Gravity.TOP;
                        mStatusBarView.setLayoutParams( layoutParams );
                        mStatusBarView.setBackgroundColor( statusColor );
                        mDecorView.addView( mStatusBarView, 0 );
                        mDecorView.setTag( true );
                    }

                }
            }
        }
    }

    public static void translucentStatusBar( Activity activity ) {
        translucentStatusBar( activity, false );
    }

    /**
     * change to full screen mode
     *
     * @param hideStatusBarBackground hide status bar alpha Background when SDK > 21, true if hide it
     */
    public static void translucentStatusBar( Activity activity, boolean hideStatusBarBackground ) {
//        Window window = activity.getWindow();
//        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
//
//        //set child View not fill the system window
//        View mChildView = mContentView.getChildAt(0);
//        if (mChildView != null) {
//            ViewCompat.setFitsSystemWindows(mChildView, false);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            int statusBarHeight = getStatusBarHeight(activity);
//
//            //First translucent status bar.
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                //After LOLLIPOP just set LayoutParams.
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                if (hideStatusBarBackground) {
//                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                    window.setStatusBarColor(COLOR_TRANSLUCENT);
//                } else {
//                    window.setStatusBarColor(calculateStatusBarColor(COLOR_TRANSLUCENT, DEFAULT_COLOR_ALPHA));
//                }
//                //must call requestApplyInsets, otherwise it will have space in screen bottom
//                if (mChildView != null) {
//                    ViewCompat.requestApplyInsets(mChildView);
//                }
//            } else {
//                ViewGroup mDecorView = (ViewGroup) window.getDecorView();
//                if (mDecorView.getTag() != null && mDecorView.getTag() instanceof Boolean && (Boolean)mDecorView.getTag()) {
//                    mChildView = mDecorView.getChildAt(0);
//                    //remove fake status bar view.
//                    mContentView.removeView(mChildView);
//                    mChildView = mContentView.getChildAt(0);
//                    if (mChildView != null) {
//                        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
//                        //cancel the margin top
//                        if (lp != null && lp.topMargin >= statusBarHeight) {
//                            lp.topMargin -= statusBarHeight;
//                            mChildView.setLayoutParams(lp);
//                        }
//                    }
//                    mDecorView.setTag(false);
//                }
//            }
//        }
        if ( activity == null ) return;
        Window window = activity.getWindow();
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP ) {
            window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION );
        } else if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION );
            window.getDecorView()
                  .setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View
                          .SYSTEM_UI_FLAG_LIGHT_STATUS_BAR );
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS );
            window.setStatusBarColor( Color.TRANSPARENT );
        }

        if ( OSUtil.isMIUI() ) {
            setXiaomiStatusBar( window, hideStatusBarBackground );
        } else if ( OSUtil.isFlyme() ) {
            setMeizuStatusBar( window, hideStatusBarBackground );
        }
    }

    // 设置小米状态栏
    public static void setXiaomiStatusBar( Window window, boolean isLightStatusBar ) {
        Class< ? extends Window > clazz = window.getClass();
        try {
            Class< ? > layoutParams = Class.forName( "android.view.MiuiWindowManager$LayoutParams" );
            Field field = layoutParams.getField( "EXTRA_FLAG_STATUS_BAR_DARK_MODE" );
            int darkModeFlag = field.getInt( layoutParams );
            Method extraFlagField = clazz.getMethod( "setExtraFlags", int.class, int.class );
            extraFlagField.invoke( window, isLightStatusBar ? darkModeFlag : 0, darkModeFlag );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    // 设置魅族状态栏
    public static void setMeizuStatusBar( Window window, boolean isLightStatusBar ) {
        WindowManager.LayoutParams params = window.getAttributes();
        try {
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField( "MEIZU_FLAG_DARK_STATUS_BAR_ICON" );
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField( "meizuFlags" );
            darkFlag.setAccessible( true );
            meizuFlags.setAccessible( true );
            int bit = darkFlag.getInt( null );
            int value = meizuFlags.getInt( params );
            if ( isLightStatusBar ) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt( params, value );
            window.setAttributes( params );
            darkFlag.setAccessible( false );
            meizuFlags.setAccessible( false );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    //Get status bar height
    public static int getStatusBarHeight( Context context ) {
        int result = 0;
        int resId = context.getResources()
                           .getIdentifier( "status_bar_height", "dimen", "android" );
        if ( resId > 0 ) {
            result = context.getResources()
                            .getDimensionPixelOffset( resId );
        }
        return result;
    }

    //Get alpha color
    private static int calculateStatusBarColor( int color, int alpha ) {
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = ( int ) ( red * a + 0.5 );
        green = ( int ) ( green * a + 0.5 );
        blue = ( int ) ( blue * a + 0.5 );
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }
}