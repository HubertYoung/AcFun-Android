package com.hubertyoung.common.utils.display;

import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.R;



/**
 * Toast统一管理类
 * 增加单利吐司 点击多次会显示后点击的
 */
public class ToastUtil {


	private static Toast toast;
	private static Toast toast2;

	private static long oneTime = 0;

	private static long twoTime = 0;
	private static CharSequence oldMsg;


//	public static void showToast( CharSequence message, int duration ) {
//		if ( toast == null ) {
//			toast = Toast.makeText( BaseApplication.getAppContext(), message, duration );
//			toast.show();
//			oneTime = System.currentTimeMillis();
//		} else {
//			twoTime = System.currentTimeMillis();
//			if ( message.equals( oldMsg ) ) {
//				if ( twoTime - oneTime > duration ) {
//					toast.show();
//				}
//			} else {
//				oldMsg = message;
//				toast.setText( message );
//				toast.show();
//			}
//		}
//		oneTime = twoTime;
//	}

	/**
	 * 长时间显示Toast
	 *
	 * @param strResId
	 */
//	public static void showLong( int strResId ) {
//		showToast( BaseApplication.getAppContext()
//								  .getResources()
//								  .getText( strResId ), Toast.LENGTH_LONG );
//	}

	/**
	 * 自定义显示Toast时间
	 *
	 * @param message
	 * @param duration
	 */
//	public static void show( CharSequence message, int duration ) {
//		showToast( message, duration );
//	}

	/**
	 * 自定义显示Toast时间
	 *
	 * @param context
	 * @param strResId
	 * @param duration
	 */
//	public static void show( Context context, int strResId, int duration ) {
//		showToast( context.getResources()
//						  .getText( strResId ), duration );
//	}

	/**
	 * 显示有image的toast
	 *
	 * @param tvStr
	 * @param imageResource
	 * @return
	 */
	private static Toast showToastWithImg( String tvStr, int imageResource ) {
		if ( toast2 == null ) {
			toast2 = new Toast( CommonApplication.getAppContext() );

			View view = LayoutInflater.from( CommonApplication.getAppContext() )
									  .inflate( R.layout.toast_custom, null );
			TextView tv = ( TextView ) view.findViewById( R.id.text );
			tv.setText( tvStr );
			ImageView iv = ( ImageView ) view.findViewById( R.id.icon );
			if ( imageResource > 0 ) {
				iv.setVisibility( View.VISIBLE );
				iv.setImageResource( imageResource );
			} else {
				iv.setVisibility( View.GONE );
			}
			toast2.setView( view );

//        toast2.setGravity(Gravity.CENTER, 0, 0);
			toast2.show();
			oneTime = System.currentTimeMillis();
		}else {

			TextView tv = ( TextView ) toast2.getView().findViewById( R.id.text );
			tv.setText( tvStr );
			ImageView iv = ( ImageView ) toast2.getView().findViewById( R.id.icon );
			if ( imageResource > 0 ) {
				iv.setVisibility( View.VISIBLE );
				iv.setImageResource( imageResource );
			} else {
				iv.setVisibility( View.GONE );
			}

			twoTime = System.currentTimeMillis();
			if ( tvStr.equals( oldMsg ) ) {
				if ( twoTime - oneTime > 10 ) {
					toast2.show();
				}
			} else {
				oldMsg = tvStr;
				toast2.show();
			}
		}
		oneTime = twoTime;

		return toast2;
	}

	public static void showSuccess( String tvStr ) {
		showToastWithImg( tvStr, R.drawable.ic_done_white_24dp );
	}

	public static void showError( String tvStr ) {
		showToastWithImg( tvStr, R.drawable.ic_clear_white_24dp );
	}

	public static void showWarning( String tvStr ) {
		showToastWithImg( tvStr, R.drawable.ic_error_outline_white_24dp );
	}
	public static void showSuccess(@StringRes int strResId ) {
		showToastWithImg( CommonApplication.getAppContext()
										 .getResources()
										 .getText( strResId ).toString(), R.drawable.ic_done_white_24dp );
	}

	public static void showError(@StringRes int strResId ) {
		showToastWithImg( CommonApplication.getAppContext()
										 .getResources()
										 .getText( strResId ).toString(), R.drawable.ic_clear_white_24dp );
	}

	public static void showWarning(@StringRes int strResId ) {
		showToastWithImg( CommonApplication.getAppContext()
										 .getResources()
										 .getText( strResId ).toString(), R.drawable.ic_error_outline_white_24dp );
	}
}
