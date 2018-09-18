package com.hubertyoung.common.widget.preference;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/18 17:11
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.widget.preference
 */
public abstract class BasePreferenceFragment extends PreferenceFragmentCompat {

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
		View view = super.onCreateView( inflater, container, savedInstanceState );
		RecyclerView listView = getListView();
		listView.setPadding( 0, 0, 0, 0 );
		return view;
	}
}
