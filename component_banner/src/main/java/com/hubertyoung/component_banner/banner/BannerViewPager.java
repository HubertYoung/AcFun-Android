package com.hubertyoung.component_banner.banner;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author:Yang
 * @date:2017-7-15 23:04:55
 * @since:v1.0
 * @desc:
 * @param:banner的ViewPager
 */
public class BannerViewPager extends ViewPager {
	private boolean scrollable = true;
	private ArrayList< Integer > childCenterXAbs = new ArrayList();
	private SparseArray< Integer > childIndex = new SparseArray();

	public BannerViewPager( Context context ) {
		this( context, null );
	}

	public BannerViewPager( Context context, AttributeSet attrs ) {
		super( context, attrs );
		init();
	}

	private void init() {
		this.setClipToPadding( false );
		this.setOverScrollMode( 2 );
	}

	protected int getChildDrawingOrder( int childCount, int n ) {
		if ( n == 0 || this.childIndex.size() != childCount ) {
			this.childCenterXAbs.clear();
			this.childIndex.clear();
			int viewCenterX = this.getViewCenterX( this );

			for (int i = 0; i < childCount; ++i) {
				int indexAbs = Math.abs( viewCenterX - this.getViewCenterX( this.getChildAt( i ) ) );
				if ( this.childIndex.get( indexAbs ) != null ) {
					++indexAbs;
				}

				this.childCenterXAbs.add( indexAbs );
				this.childIndex.append( indexAbs, i );
			}

			Collections.sort( this.childCenterXAbs );
		}

		return ( Integer ) this.childIndex.get( ( Integer ) this.childCenterXAbs.get( childCount - 1 - n ) );
	}

	private int getViewCenterX( View view ) {
		int[] array = new int[ 2 ];
		view.getLocationOnScreen( array );
		return array[ 0 ] + view.getWidth() / 2;
	}

	@Override
	public boolean onTouchEvent( MotionEvent ev ) {
		return this.scrollable && super.onTouchEvent( ev );
	}

	@Override
	public boolean onInterceptTouchEvent( MotionEvent ev ) {
		return this.scrollable && super.onInterceptTouchEvent( ev );
	}

	public void setScrollable( boolean scrollable ) {
		this.scrollable = scrollable;
	}
}
