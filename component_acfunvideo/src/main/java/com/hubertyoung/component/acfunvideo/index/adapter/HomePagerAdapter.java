package com.hubertyoung.component.acfunvideo.index.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/3 17:19
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.index.adapter
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
	private List<Fragment > mFragments = new ArrayList<>(  );
	private List<String> mTitles = new ArrayList<>(  );
	public HomePagerAdapter( FragmentManager fm ) {
		super( fm );
	}

	@Override
	public Fragment getItem( int position ) {
		return mFragments.get( position );
	}

	@Override
	public int getCount() {
		return mFragments == null ? 0 : mFragments.size();
	}

	public void clear() {
		this.mFragments.clear();
		this.mTitles.clear();
		notifyDataSetChanged();
	}

	public void add(Fragment fragment, String str) {
		this.mFragments.add(fragment);
		this.mTitles.add(str);
		notifyDataSetChanged();
	}

	@Nullable
	@Override
	public CharSequence getPageTitle( int position ) {
		return mTitles.get( position );
	}
}
