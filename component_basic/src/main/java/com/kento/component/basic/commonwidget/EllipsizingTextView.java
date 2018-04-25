package com.kento.component.basic.commonwidget;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author:Yang
 * @date:2017/12/10 17:19
 * @since:v1.0
 * @desc:ddframework.gent.common.commonwidget
 * @param:如果限制显示指定的行数，超出指定的行数显示省略号。已经有人自定义了
 */
public class EllipsizingTextView extends AppCompatTextView {

	public EllipsizingTextView(Context context) {
		super(context);
	}

	public EllipsizingTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public EllipsizingTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	private int getAvailableWidth() {
		return getWidth() - getPaddingLeft() - getPaddingRight();
	}
	public boolean isOverFlowed() {
		Paint paint = getPaint();
		float width = paint.measureText(getText().toString());
		if (width > getAvailableWidth()) return true;
		return false;
	}
}
