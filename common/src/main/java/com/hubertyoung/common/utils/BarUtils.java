package com.hubertyoung.common.utils;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.R;
import com.hubertyoung.common.os.OSUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/09/23
 *     desc  : 栏相关工具类
 * </pre>
 */
public final class BarUtils {

	///////////////////////////////////////////////////////////////////////////
	// status bar
	///////////////////////////////////////////////////////////////////////////
	private static final int MIN_API = 19;

	private BarUtils() {
		throw new UnsupportedOperationException( "u can't instantiate me..." );
	}

	/**
	 * 获取状态栏高度
	 *
	 * @return 状态栏高度
	 */
	public static int getStatusBarHeight() {
		int result = 24;
		int resId = CommonApplication.getAppContext().getResources().getIdentifier( "status_bar_height", "dimen", "android" );
		if ( resId > 0 ) {
			result = CommonApplication.getAppContext().getResources().getDimensionPixelSize( resId );
		} else {
			result = ( int ) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, result, Resources.getSystem().getDisplayMetrics() );
		}
		return result;
	}

	public static void setStatusBarTranslucent( Window window, boolean translucent ) {
//		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
//			View decorView = window.getDecorView();
//			if ( translucent ) {
//				decorView.setOnApplyWindowInsetsListener( new View.OnApplyWindowInsetsListener() {
//					@TargetApi( Build.VERSION_CODES.LOLLIPOP )
//					@Override
//					public WindowInsets onApplyWindowInsets( View v, WindowInsets insets ) {
//						WindowInsets defaultInsets = v.onApplyWindowInsets( insets );
//						return defaultInsets.replaceSystemWindowInsets( defaultInsets.getSystemWindowInsetLeft(), 0, defaultInsets.getSystemWindowInsetRight(), defaultInsets
//								.getSystemWindowInsetBottom() );
//					}
//				} );
//			} else {
//				decorView.setOnApplyWindowInsetsListener( null );
//			}
//
//			ViewCompat.requestApplyInsets( decorView );
//		} else if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
//			if ( translucent ) {
//				window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
//			} else {
//				window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
//			}
//			ViewCompat.requestApplyInsets( window.getDecorView() );
//		}
		if ( OSUtil.isMiui() || OSUtil.isFlyme() ) {
			if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
				transparentStatusBarAbove21( window, translucent );
			} else if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
				window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
			}
		} else if ( ( OSUtil.isOppo() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) ) {
			transparentStatusBarAbove21( window, translucent );
		} else if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
			transparentStatusBarAbove21( window, translucent );
		}
	}

	@TargetApi( 21 )
	private static void transparentStatusBarAbove21( Window window, boolean translucent ) {
		if ( translucent ) {
			window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS );
			window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
			window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE );
			window.setStatusBarColor( Color.TRANSPARENT );
		} else {
			window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS );
			window.clearFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
			window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE );
			window.setStatusBarColor( getColorPrimary(window));
		}
	}
	/**
	 * 获取主题颜色
	 * @return
	 */
	public static int getColorPrimary( Window window ){
		TypedValue typedValue = new  TypedValue();
		window.getContext().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
		return typedValue.data;
	}
	/**
	 * 设置状态栏颜色
	 *
	 * @param activity activity
	 * @param color    状态栏颜色值
	 * @param animated
	 */
	public static void setStatusBarColor( @NonNull final Activity activity, @ColorInt int color, boolean animated ) {
		Window window = activity.getWindow();
		setStatusBarColor( window, color, animated );
	}

	public static void setStatusBarColor( @NonNull Window window, @ColorInt int color, boolean animated ) {
		if ( ( window.getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN ) == WindowManager.LayoutParams.FLAG_FULLSCREEN ) {
			color = Color.TRANSPARENT;
		}

		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
			window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS );
			if ( animated ) {
				int curColor = window.getStatusBarColor();
				ValueAnimator colorAnimation = ValueAnimator.ofObject( new ArgbEvaluator(), curColor, color );

				colorAnimation.addUpdateListener( new ValueAnimator.AnimatorUpdateListener() {
					@TargetApi( 21 )
					@Override
					public void onAnimationUpdate( ValueAnimator animator ) {
						window.setStatusBarColor( ( Integer ) animator.getAnimatedValue() );
					}
				} );
				colorAnimation.setDuration( 200 ).setStartDelay( 0 );
				colorAnimation.start();
			} else {
				window.setStatusBarColor( color );
			}
		} else if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
			ViewGroup decorViewGroup = ( ViewGroup ) window.getDecorView();
			View statusBarView = decorViewGroup.findViewWithTag( "custom_status_bar_tag" );
			if ( statusBarView == null ) {
				statusBarView = new View( window.getContext() );
				statusBarView.setTag( "custom_status_bar_tag" );
				FrameLayout.LayoutParams params = new FrameLayout.LayoutParams( FrameLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight() );
				params.gravity = Gravity.TOP;
				statusBarView.setLayoutParams( params );
				decorViewGroup.addView( statusBarView );
			}


			if ( animated ) {
				Drawable drawable = statusBarView.getBackground();
				int curColor = Integer.MAX_VALUE;
				if ( drawable != null && drawable instanceof ColorDrawable ) {
					ColorDrawable colorDrawable = ( ColorDrawable ) drawable;
					curColor = colorDrawable.getColor();
				}
				if ( curColor != Integer.MAX_VALUE ) {
					final View barView = statusBarView;
					ValueAnimator colorAnimation = ValueAnimator.ofObject( new ArgbEvaluator(), curColor, color );
					colorAnimation.addUpdateListener( new ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate( ValueAnimator animator ) {
							barView.setBackground( new ColorDrawable( ( Integer ) animator.getAnimatedValue() ) );
						}
					} );
					colorAnimation.setDuration( 200 ).setStartDelay( 0 );
					colorAnimation.start();
				} else {
					statusBarView.setBackground( new ColorDrawable( color ) );
				}
			} else {
				statusBarView.setBackground( new ColorDrawable( color ) );
			}
		}
	}

	/**
	 * 状态栏亮色模式，设置状态栏黑色文字、图标，
	 * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
	 *
	 * @param activity
	 */
	public static void statusBarLightMode( Activity activity, boolean dark ) {
		Window window = activity.getWindow();
		statusBarLightMode( window, dark );
	}

	public static void statusBarLightMode( Window window, boolean dark ) {
		if ( OSUtil.isMiui() ) {
			setMIUIStatusBarDarkMode( window, dark );
		} else if ( OSUtil.isFlyme() ) {
			setFlymeStatusBarDarkMode( window, dark );
		} else if ( OSUtil.isOppo() ) {
			setOppoStatusBarDarkMode( window, dark );
		} else {
			setStatusBarDarkMode( window, dark );
		}
	}

	private static final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 0x00000010;

	private static void setOppoStatusBarDarkMode( Window window, boolean darkMode ) {
		int vis = window.getDecorView().getSystemUiVisibility();
		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
			if ( darkMode ) {
				vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
			} else {
				vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
			}
		} else if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
			if ( darkMode ) {
				vis |= SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
			} else {
				vis &= ~SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
			}
		}
		window.getDecorView().setSystemUiVisibility( vis );
	}

	private static void setFlymeStatusBarDarkMode( Window window, boolean darkMode ) {
		FlymeStatusBarUtils.setStatusBarDarkIcon( window, darkMode );
	}

	/**
	 * android 6.0设置字体颜色
	 */
	@RequiresApi( Build.VERSION_CODES.M )
	private static void darkModeForM( Window window, boolean dark ) {
		int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
		if ( dark ) {
			systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
		} else {
			systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
		}
		window.getDecorView().setSystemUiVisibility( systemUiVisibility );
	}

	/**
	 * 设置Flyme4+的darkMode,darkMode时候字体颜色及icon变黑
	 * http://open-wiki.flyme.cn/index.php?title=Flyme%E7%B3%BB%E7%BB%9FAPI
	 */
	private static boolean darkModeForFlyme4( Window window, boolean dark ) {
		boolean result = false;
		if ( window != null ) {
			try {
				WindowManager.LayoutParams e = window.getAttributes();
				Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField( "MEIZU_FLAG_DARK_STATUS_BAR_ICON" );
				Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField( "meizuFlags" );
				darkFlag.setAccessible( dark );
				meizuFlags.setAccessible( dark );
				int bit = darkFlag.getInt( null );
				int value = meizuFlags.getInt( e );
				if ( dark ) {
					value |= bit;
				} else {
					value &= ~bit;
				}

				meizuFlags.setInt( e, value );
				window.setAttributes( e );
				result = true;
			} catch ( Exception var8 ) {
				Log.e( "StatusBar", "darkIcon: failed" );
			}
		}

		return result;
	}

	/**
	 * 需要MIUIV6以上
	 *
	 * @param window
	 * @param darkMode 是否把状态栏文字及图标颜色设置为深色
	 * @return boolean 成功执行返回true
	 */
	private static void setMIUIStatusBarDarkMode( Window window, boolean darkMode ) {
		if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.M ) {
			Class< ? extends Window > clazz = window.getClass();
			try {
				Class< ? > layoutParams = Class.forName( "android.view.MiuiWindowManager$LayoutParams" );
				Field field = layoutParams.getField( "EXTRA_FLAG_STATUS_BAR_DARK_MODE" );
				int darkModeFlag = field.getInt( layoutParams );
				Method extraFlagField = clazz.getMethod( "setExtraFlags", int.class, int.class );
				extraFlagField.invoke( window, darkMode ? darkModeFlag : 0, darkModeFlag );
			} catch ( Exception e ) {
			}
		}
		setStatusBarDarkMode( window, darkMode );
	}

	private static void setStatusBarDarkMode( Window window, boolean darkMode ) {
		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
			if ( darkMode ) {
				window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN );
			} else {
				window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN );
			}
		}
	}

	public static void setStatusBarHidden( Activity activity, boolean hidden ) {
		Window window = activity.getWindow();
		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
			if ( hidden ) {
				window.addFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN );
				window.clearFlags( WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN );
			} else {
				window.addFlags( WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN );
				window.clearFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN );
			}
		}

		if ( Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT ) {
			ViewGroup decorViewGroup = ( ViewGroup ) window.getDecorView();
			final View statusBarView = decorViewGroup.findViewWithTag( "custom_status_bar_tag" );
			if ( statusBarView != null ) {
				boolean hiding = statusBarView.isClickable();
				if ( hiding == hidden ) {
					return;
				}

				if ( hidden ) {
					statusBarView.setClickable( true );
					ObjectAnimator animator = ObjectAnimator.ofFloat( statusBarView, "y", -getStatusBarHeight() );
					animator.setDuration( 200 );
					animator.setStartDelay( 200 );
					animator.start();
				} else {
					statusBarView.setClickable( false );
					ObjectAnimator animator = ObjectAnimator.ofFloat( statusBarView, "y", 0 );
					animator.setDuration( 200 );
					animator.start();
				}
			}
		}
	}

	/**
	 * 增加View的paddingTop,增加的值为状态栏高度 (智能判断，并设置高度)
	 */
	public static void setPaddingSmart( View view ) {
		if ( Build.VERSION.SDK_INT >= MIN_API ) {
			ViewGroup.LayoutParams lp = view.getLayoutParams();
			if ( lp != null && lp.height > 0 ) {
				lp.height += getStatusBarHeight();//增高
			}
			view.setPadding( view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(), view.getPaddingRight(), view.getPaddingBottom() );
		}
	}

	public static void removePaddingSmart( View view ) {
		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
			if ( view != null ) {
				view.setPadding( view.getPaddingLeft(), 0, view.getPaddingRight(), view.getPaddingBottom() );
				view.getLayoutParams().height -= getStatusBarHeight();
			}
		}
	}

	/**
	 * 计算View Id
	 *
	 * @return
	 */
	public static int generateViewId() {
		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 ) {
			return View.generateViewId();
		} else {
			return UUID.randomUUID().hashCode();
		}
	}
}
