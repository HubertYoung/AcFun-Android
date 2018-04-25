package com.kento.component.basic.commonwidget.dialog.animation.FlipEnter;

import android.animation.ObjectAnimator;
import android.util.DisplayMetrics;
import android.view.View;

import com.kento.component.basic.commonwidget.dialog.animation.BaseAnimatorSet;


public class FlipRightEnter extends BaseAnimatorSet {
	@Override
	public void setAnimation(View view) {
		DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();

		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(view, "rotationY", -90, 0),//
				ObjectAnimator.ofFloat(view, "translationX", 200 * dm.density, 0), //
				ObjectAnimator.ofFloat(view, "alpha", 0.2f, 1));
	}
}
