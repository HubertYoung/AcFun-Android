package com.zhy.autolayout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

;import com.zhy.autolayout.widget.AutoCardView;

/**
 * Created by zhy on 15/11/19.
 */
public class AutoLayoutActivity extends AppCompatActivity {
	private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
	private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
	private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
	private static final String LAYOUT_CARDVIEW = "CardView";
	private static final String LAYOUT_CONSTRAINTLAYOUT = "ConstraintLayout";


	@Override
	public View onCreateView( String name, Context context, AttributeSet attrs ) {
		View view = null;
		if ( name.equals( LAYOUT_FRAMELAYOUT ) ) {
			view = new AutoFrameLayout( context, attrs );
		}

		if ( name.equals( LAYOUT_LINEARLAYOUT ) ) {
			view = new AutoLinearLayout( context, attrs );
		}

		if ( name.equals( LAYOUT_RELATIVELAYOUT ) ) {
			view = new AutoRelativeLayout( context, attrs );
		}
		if ( name.equals( LAYOUT_CARDVIEW ) ) {
			view = new AutoCardView( context, attrs );
		}
		if ( name.equals( LAYOUT_CONSTRAINTLAYOUT ) ) {
			view = new AutoConstraintLayout( context, attrs );
		}

		if ( view != null ) return view;

		return super.onCreateView( name, context, attrs );
	}


}
