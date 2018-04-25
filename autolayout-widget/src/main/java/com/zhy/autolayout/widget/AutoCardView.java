package com.zhy.autolayout.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zhy.autolayout.AutoLayoutInfo;
import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * Created by zhy on 15/12/8.
 */
public class AutoCardView extends CardView {
	private final AutoLayoutHelper mHelper = new AutoLayoutHelper( this );

	public AutoCardView( Context context ) {
		super( context );
	}

	public AutoCardView( Context context, AttributeSet attrs ) {
		super( context, attrs );
	}

	@TargetApi( Build.VERSION_CODES.HONEYCOMB )
	public AutoCardView( Context context, AttributeSet attrs, int defStyleAttr ) {
		super( context, attrs, defStyleAttr );
	}

	@Override
	public LayoutParams generateLayoutParams( AttributeSet attrs ) {
		return new LayoutParams( getContext(), attrs );
	}

	@Override
	protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
		if ( !isInEditMode() ) {
			mHelper.adjustChildren();
		}
		super.onMeasure( widthMeasureSpec, heightMeasureSpec );
	}

	@Override
	protected void onLayout( boolean changed, int left, int top, int right, int bottom ) {
		super.onLayout( changed, left, top, right, bottom );
	}

	public static class LayoutParams extends FrameLayout.LayoutParams implements AutoLayoutHelper.AutoLayoutParams {
		private AutoLayoutInfo mAutoLayoutInfo;

		public LayoutParams( Context c, AttributeSet attrs ) {
			super( c, attrs );

			mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo( c, attrs );
		}

		public LayoutParams( int width, int height ) {
			super( width, height );
		}

		public LayoutParams( int width, int height, int gravity ) {
			super( width, height, gravity );
		}

		public LayoutParams( ViewGroup.LayoutParams source ) {
			super( source );
		}

		public LayoutParams( MarginLayoutParams source ) {
			super( source );
		}

		public LayoutParams( FrameLayout.LayoutParams source ) {
			super( ( MarginLayoutParams ) source );
			gravity = source.gravity;
		}

		public LayoutParams( LayoutParams source ) {
			this( ( FrameLayout.LayoutParams ) source );
			mAutoLayoutInfo = source.mAutoLayoutInfo;
		}

		@Override
		public AutoLayoutInfo getAutoLayoutInfo() {
			return mAutoLayoutInfo;
		}


	}
}
