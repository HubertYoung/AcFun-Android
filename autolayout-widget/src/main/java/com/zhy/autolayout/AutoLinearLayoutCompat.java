package com.zhy.autolayout;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.zhy.autolayout.utils.AutoLayoutHelper;

public class AutoLinearLayoutCompat extends LinearLayoutCompat {

	private AutoLayoutHelper mHelper = new AutoLayoutHelper( this );

	public AutoLinearLayoutCompat( Context context ) {
		super( context );
	}

	public AutoLinearLayoutCompat( Context context, AttributeSet attrs ) {
		super( context, attrs );
	}

	public AutoLinearLayoutCompat( Context context, AttributeSet attrs, int defStyleAttr ) {
		super( context, attrs, defStyleAttr );
	}

	@Override
	protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
		if ( !isInEditMode() ) mHelper.adjustChildren();
		super.onMeasure( widthMeasureSpec, heightMeasureSpec );
	}


	@Override
	protected void onLayout( boolean changed, int l, int t, int r, int b ) {
		super.onLayout( changed, l, t, r, b );
	}


	@Override
	public LayoutParams generateLayoutParams( AttributeSet attrs ) {
		return new LayoutParams( getContext(), attrs );
	}


	public static class LayoutParams extends LinearLayoutCompat.LayoutParams implements AutoLayoutHelper.AutoLayoutParams {
		private AutoLayoutInfo mAutoLayoutInfo;

		public LayoutParams(Context c, AttributeSet attrs)
		{
			super(c, attrs);

			mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
		}

		public LayoutParams(int width, int height)
		{
			super(width, height);
		}

		public LayoutParams(int width, int height, int gravity)
		{
			super(width, height, gravity);
		}

		public LayoutParams(ViewGroup.LayoutParams source)
		{
			super(source);
		}

		public LayoutParams(MarginLayoutParams source)
		{
			super(source);
		}

		public LayoutParams(LinearLayoutCompat.LayoutParams source)
		{
			super((MarginLayoutParams) source);
			gravity = source.gravity;
		}

		public LayoutParams(LayoutParams source)
		{
			this((LinearLayoutCompat.LayoutParams) source);
			mAutoLayoutInfo = source.mAutoLayoutInfo;
		}

		@Override
		public AutoLayoutInfo getAutoLayoutInfo()
		{
			return mAutoLayoutInfo;
		}

	}

}
