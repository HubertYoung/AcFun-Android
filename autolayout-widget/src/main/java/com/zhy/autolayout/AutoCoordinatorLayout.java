/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhy.autolayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.zhy.autolayout.utils.AutoLayoutHelper;

public class AutoCoordinatorLayout extends CoordinatorLayout
{
    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoCoordinatorLayout( Context context)
    {
        super(context);
    }

    public AutoCoordinatorLayout( Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public AutoCoordinatorLayout( Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

	@Override
	protected CoordinatorLayout.LayoutParams generateLayoutParams( ViewGroup.LayoutParams p ) {
		return super.generateLayoutParams( new LayoutParams(p) );
	}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (!isInEditMode())
        {
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
    }

    public static class LayoutParams extends ViewGroup.LayoutParams
            implements AutoLayoutHelper.AutoLayoutParams
    {
		private int gravity;
		private AutoLayoutInfo mAutoLayoutInfo;

        public LayoutParams( Context context, AttributeSet attrs ) {
            super( context, attrs );
			mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(context, attrs);
        }

        public LayoutParams(ViewGroup.LayoutParams source)
        {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source)
        {
            super(source);
        }

        public LayoutParams(CoordinatorLayout.LayoutParams source)
        {
            super((MarginLayoutParams) source);
            gravity = source.gravity;
        }

        public LayoutParams(LayoutParams source)
        {
            this((ViewGroup.LayoutParams) source);
            mAutoLayoutInfo = source.mAutoLayoutInfo;
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo()
        {
            return mAutoLayoutInfo;
        }


    }
}
