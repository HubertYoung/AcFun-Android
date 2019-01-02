package com.hubertyoung.component.acfunvideo.videodetail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/2 14:40
 * @since:
 * @see VideoDetailPagerAdapter
 */
public class VideoDetailPagerAdapter extends FragmentStatePagerAdapter {
	private List< Fragment > mFragmentList = new ArrayList();
	private List< String > mTitles = new ArrayList();

	public VideoDetailPagerAdapter( FragmentManager fragmentManager ) {
		super( fragmentManager );
	}

	public void setData( Fragment fragment, String str ) {
		mFragmentList.add( fragment );
		mTitles.add( str );
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem( int i ) {
		return mFragmentList.get( i );
	}

	@Override
	public int getCount() {
		return this.mFragmentList.size();
	}

	@Override
	public CharSequence getPageTitle( int i ) {
		return this.mTitles.get( i );
	}
}