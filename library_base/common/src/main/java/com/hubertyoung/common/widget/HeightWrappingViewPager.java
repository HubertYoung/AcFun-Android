package com.hubertyoung.common.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * @desc:高度自适应viewpager
 * @author:HubertYoung
 * @date 2018/12/18 17:42
 * @since:
 * @see HeightWrappingViewPager
 */
public class HeightWrappingViewPager extends ViewPager {
	public HeightWrappingViewPager( @NonNull Context context ) {
		super( context );
	}

	public HeightWrappingViewPager( @NonNull Context context, @Nullable AttributeSet attrs ) {
		super( context, attrs );
	}

	@Override
	protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
		int height = 0;
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt( i );
			child.measure( widthMeasureSpec, MeasureSpec.makeMeasureSpec( 0, MeasureSpec.UNSPECIFIED ) );
			int measuredHeight = child.getMeasuredHeight();
			if ( measuredHeight > height ) //采用最大的view的高度。
				height = measuredHeight;
		}

		heightMeasureSpec = MeasureSpec.makeMeasureSpec( height, MeasureSpec.EXACTLY );
		super.onMeasure( widthMeasureSpec, heightMeasureSpec );
	}
}
