package com.hubertyoung.acfun.ui.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hubertyoung.acfun.R;
import com.hubertyoung.common.base.BaseFragment;


/**
 * <br>
 * function:加载失败组件framgment
 * <p>
 *
 * @author:Yang
 * @date:2018/5/8 4:14 PM
 * @since:V1.0
 * @desc:com.hubertyoung.welcome.commonlibrary.ui.fragment
 */

public class PlaceHolderFragment extends BaseFragment {

	private static final String ARG_PARAM1 = "componentName";
	private static final String ARG_PARAM2 = "actionName";
	private String componentName;
	private String actionName;

	public static PlaceHolderFragment newInstance( String componentName, String actionName ) {
		PlaceHolderFragment placeHolderFragment = new PlaceHolderFragment();
		Bundle args = new Bundle();
		args.putString( ARG_PARAM1, componentName );
		args.putString( ARG_PARAM2, actionName );
		placeHolderFragment.setArguments( args );
		return placeHolderFragment;
	}
    @Override
    protected void initToolBar() {

    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
		if ( getArguments() != null ) {
			componentName = getArguments().getString( ARG_PARAM1 );
			actionName = getArguments().getString( ARG_PARAM2 );
		}
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setGravity( Gravity.CENTER);
        textView.setText("componentName ==> " + componentName +"\n\n" + "actionName ==> " + actionName);
        textView.setTextColor(getColor( R.color.color_bgf83c3a));
        textView.setTextSize( 15 );
        return textView;
    }

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView( Bundle savedInstanceState ) {

    }
}
