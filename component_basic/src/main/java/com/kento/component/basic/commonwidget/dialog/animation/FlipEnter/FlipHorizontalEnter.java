package com.kento.component.basic.commonwidget.dialog.animation.FlipEnter;

import android.animation.ObjectAnimator;
import android.view.View;

import com.kento.component.basic.commonwidget.dialog.animation.BaseAnimatorSet;


public class FlipHorizontalEnter extends BaseAnimatorSet {
	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(//
				// ObjectAnimator.ofFloat(view, "rotationY", -90, 0));
				ObjectAnimator.ofFloat(view, "rotationY", 90, 0));
	}
}
