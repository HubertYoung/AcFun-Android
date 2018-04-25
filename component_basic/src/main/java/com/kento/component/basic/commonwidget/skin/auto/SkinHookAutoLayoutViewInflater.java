package com.kento.component.basic.commonwidget.skin.auto;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import skin.support.app.SkinLayoutInflater;

/**
 * Created by pengfengwang on 2017/3/15.
 */

public class SkinHookAutoLayoutViewInflater implements SkinLayoutInflater {
	@Override
	public View createView( @NonNull Context context, final String name, @NonNull AttributeSet attrs ) {
		View view = null;
		switch ( name ) {
			case "LinearLayout":
				view = new SkinAutoLinearLayout( context, attrs );
				break;
			case "RelativeLayout":
				view = new SkinAutoRelativeLayout( context, attrs );
				break;
			case "FrameLayout":
				view = new SkinAutoFrameLayout( context, attrs );
				break;
			case "RadioGroup":
				view = new SkinAutoRadioGroup( context, attrs );
				break;
			case "android.support.v7.widget.CardView":
				view = new SkinAutoCardView( context, attrs );
				break;
			case "android.support.constraint.ConstraintLayout":
				view = new SkinAutoConstraintLayout( context, attrs );
				break;
			case "android.support.v7.widget.LinearLayoutCompat":
				view = new SkinAutoLinearLayoutCompat( context, attrs );
				break;
			case "android.support.design.widget.CoordinatorLayout":
				view = new SkinAutoCoordinatorLayout( context, attrs );
				break;
			case "android.support.v7.widget.Toolbar":
				view = new SkinAutoToolbar( context, attrs );
				break;
			case "android.support.design.widget.AppBarLayout":
				view = new SkinAutoAppBarLayout( context, attrs );
				break;
			case "android.support.v4.widget.NestedScrollView":
				view = new SkinAutoNestedScrollView( context, attrs );
				break;
		}
		return view;
	}
}