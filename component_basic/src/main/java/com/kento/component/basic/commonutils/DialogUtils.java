//package com.kento.component.basic.commonutils;
//
//import android.content.Context;
//import android.graphics.Typeface;
//import android.support.v4.content.ContextCompat;
//import android.text.TextUtils;
//import android.view.Gravity;
//
//import ddframework.gent.common.R;
//import ddframework.gent.common.commonwidget.dialog.dialog.listener.OnBtnClickL;
//import ddframework.gent.common.commonwidget.dialog.dialog.widget.NormalDialog;
//
///**
// * Created by ${lei} on 2017/12/16.
// * Desc:dialog工具类
// * Since：
// */
//
//public class DialogUtils {
//
//	private DialogUtils() {
//	}
//
//	public static DialogUtils getInstance() {
//
//		return new DialogUtils();
//	}
//
//	NormalDialog dialog;
//
//	public DialogUtils showDialog( Context context, String title, String msg, boolean cancleable, String negitiveBtnDec, String positionBtnDec ) {
//		if ( dialog == null ) {
//			dialog = new NormalDialog( context );
//		}
//		if ( TextUtils.isEmpty( title ) ) {
//			dialog.isTitleShow( false );
//		} else {
//			dialog.title( title )
//				  .isTitleShow( true )
//				  .titleTextSize( 18 )
//				  .titleTextColor( ContextCompat.getColor( context, R.color.colorPrimary ) )
//				  .titleLineColor( ContextCompat.getColor( context, R.color.colorPrimary ) );
//		}
//		dialog.setBtnRightTextType( Typeface.DEFAULT_BOLD );
//		if ( TextUtils.isEmpty( negitiveBtnDec ) ) {
//			dialog.btnNum( 1 )
//				  .style( NormalDialog.STYLE_ONE )
//				  .btnText( positionBtnDec );
//		} else {
//			dialog.style( NormalDialog.STYLE_TWO )
//				  .btnText( negitiveBtnDec, positionBtnDec )
//				  .show();
//		}
//		dialog.content( msg )
//			  .contentGravity( Gravity.CENTER )//
//			  .contentTextColor( ContextCompat.getColor( context, R.color.text_color_normal ) )
//			  .cornerRadius( 5 )
//			  .contentTextSize( 16 )
//			  .btnTextColor( ContextCompat.getColor( context, R.color.text_color_normal ), ContextCompat.getColor( context, R.color.colorPrimary ) )
//			  .widthScale( 0.85f )//
//			  .show();
//		dialog.setCanceledOnTouchOutside( cancleable );
//		return this;
//	}
//
//	public DialogUtils setOnBtnClickL( OnBtnClickLeft onBtnClickLeft, OnBtnClickRight onBtnClickRight ) {
//		OnBtnClickL onBtnClickL = new OnBtnClickL() {
//			@Override
//			public void onBtnClick() {
//				if ( dialog.isShowing() ) {
//					dialog.dismiss();
//					if ( onBtnClickLeft != null ) {
//						onBtnClickLeft.onBtnClickL();
//					}
//				}
//			}
//		};
//		OnBtnClickL onBtnClickR = new OnBtnClickL() {
//			@Override
//			public void onBtnClick() {
//				if ( dialog.isShowing() ) {
//					dialog.dismiss();
//					if ( onBtnClickRight != null ) {
//						onBtnClickRight.onBtnClickR();
//					}
//				}
//			}
//		};
//		if ( onBtnClickRight != null ) {
//			dialog.setOnBtnClickL( onBtnClickL, onBtnClickR );
//		} else {
//			dialog.setOnBtnClickL( onBtnClickL );
//		}
//		return this;
//	}
//
//	public NormalDialog getDialog() {
//		return dialog;
//	}
//
//	public interface OnBtnClickLeft {
//		void onBtnClickL();
//	}
//
//	public interface OnBtnClickRight {
//		void onBtnClickR();
//	}
//
//}
