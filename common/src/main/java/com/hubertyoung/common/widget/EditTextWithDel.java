package com.hubertyoung.common.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.acty.litemall.R;
import com.hubertyoung.common.utils.DisplayUtil;



/**
 * 作者：JIUU on 2017/3/7 11:30
 * QQ号：1344393464
 * 作用：自定义带有删除功能的EditText
 */
public class EditTextWithDel extends AppCompatEditText {
	private final static String TAG = "EditTextWithDel";
	private Drawable imgInable;
	private Drawable imgAble;
	private Context mContext;

	public EditTextWithDel( Context context ) {
		super( context );
		mContext = context;
		init();
	}

	public EditTextWithDel( Context context, AttributeSet attrs, int defStyle ) {
		super( context, attrs, defStyle );
		mContext = context;
		init();
	}

	public EditTextWithDel( Context context, AttributeSet attrs ) {
		super( context, attrs );
		mContext = context;
		init();
	}

	private void init() {
		imgAble = mContext.getResources()
						  .getDrawable( R.drawable.edittext_delete );
		addTextChangedListener( new TextWatcher() {
			@Override
			public void onTextChanged( CharSequence s, int start, int before, int count ) {
			}

			@Override
			public void beforeTextChanged( CharSequence s, int start, int count, int after ) {
			}

			@Override
			public void afterTextChanged( Editable s ) {
				setDrawable();
			}
		} );
		setDrawable();
	}

	// 设置删除图片
	private void setDrawable() {
		if ( length() < 1 ) {
			setCompoundDrawablesWithIntrinsicBounds( getCompoundDrawables()[ 0 ], getCompoundDrawables()[ 1 ], null, getCompoundDrawables()[ 3 ] );
		} else {
			setPadding( 0, 0, DisplayUtil.dip2px( 10 ), 0 );
			setCompoundDrawablesWithIntrinsicBounds( getCompoundDrawables()[ 0 ], getCompoundDrawables()[ 1 ], imgAble, getCompoundDrawables()[ 3 ] );

		}
	}

	// 处理删除事件
	@Override
	public boolean onTouchEvent( MotionEvent event ) {
		if ( imgAble != null && event.getAction() == MotionEvent.ACTION_UP ) {
			int eventX = ( int ) event.getRawX();
			int eventY = ( int ) event.getRawY();
			Log.e( TAG, "eventX = " + eventX + "; eventY = " + eventY );
			Rect rect = new Rect();
			getGlobalVisibleRect( rect );
			rect.left = rect.right - 50;
			if ( rect.contains( eventX, eventY ) && !TextUtils.isEmpty( getText() ) ) setText( "" );
		}
		return super.onTouchEvent( event );
	}
}
