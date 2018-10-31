package com.hubertyoung.component_acfundynamic.dynamic.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/21 13:23
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfundynamic.dynamic.adapter
 */
public class DynamicPagerAdapter extends FragmentStatePagerAdapter {
	private List< Fragment > mFragments = new ArrayList();
	private List< String > mStrings = new ArrayList();

	public DynamicPagerAdapter( FragmentManager fm ) {
		super( fm );
	}

	public List< Fragment > getFragments() {
		return mFragments;
	}

	@Override
	public Fragment getItem( int position ) {
		return mFragments.get( position );
	}

	@Override
	public int getCount() {
		return mFragments == null ? 0 : mFragments.size();
	}

	@Override
	public CharSequence getPageTitle( int position ) {

		return mStrings.get( position );
	}

	public void clear() {
		mFragments.clear();
		notifyDataSetChanged();
	}

	public void add( Fragment fragment, String str ) {
		mFragments.add( fragment );
		mStrings.add( str );
		notifyDataSetChanged();
	}
}
