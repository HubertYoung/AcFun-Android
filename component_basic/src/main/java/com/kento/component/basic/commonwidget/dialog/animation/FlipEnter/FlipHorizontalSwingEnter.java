package com.kento.component.basic.commonwidget.dialog.animation.FlipEnter;

import android.animation.ObjectAnimator;
import android.view.View;

import com.kento.component.basic.commonwidget.dialog.animation.BaseAnimatorSet;


public class FlipHorizontalSwingEnter extends BaseAnimatorSet {
	public FlipHorizontalSwingEnter() {
		duration = 1000;
	}

	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(view, "rotationY", 90, -10, 10, 0),//
				ObjectAnimator.ofFloat(view, "alpha", 0.25f, 0.5f, 0.75f, 1));
	}
}
