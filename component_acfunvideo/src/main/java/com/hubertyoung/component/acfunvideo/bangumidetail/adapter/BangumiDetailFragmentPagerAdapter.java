package com.hubertyoung.component.acfunvideo.bangumidetail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hubertyoung.component.acfunvideo.bangumidetail.fragment.BangumiDetailFragment;
import com.hubertyoung.component.acfunvideo.entity.BangumiDetailBean;

import java.util.ArrayList;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2018/12/19 15:45
 * @since:
 * @see BangumiDetailFragmentPagerAdapter
 */
public class BangumiDetailFragmentPagerAdapter extends FragmentStatePagerAdapter {
	private ArrayList< BangumiDetailBean.VideoGroupContentBean > mNetVideoList = new ArrayList();
	private ArrayList< BangumiDetailBean.VideoGroupTitleBean > mGroupTitleBeanList = new ArrayList();
	private String mContentId;
	private BangumiDetailBean mBangumiDetailBean;

	/* JADX WARNING: inconsistent code. */
	/* Code decompiled incorrectly, please refer to instructions dump. */
	public void setData( String mContentId, ArrayList< BangumiDetailBean.VideoGroupTitleBean > mGroupTitleBeanList, ArrayList< BangumiDetailBean.VideoGroupContentBean > mNetVideoList, BangumiDetailBean mBangumiDetailBean ) {
		this.mContentId = mContentId;
		this.mGroupTitleBeanList = mGroupTitleBeanList;
		this.mNetVideoList = mNetVideoList;
		this.mBangumiDetailBean = mBangumiDetailBean;
		notifyDataSetChanged();
	}

	public BangumiDetailFragmentPagerAdapter( FragmentManager fragmentManager ) {
		super( fragmentManager );
	}

	@Override
	public Fragment getItem( int i ) {
		return BangumiDetailFragment.newInstance( mGroupTitleBeanList, mNetVideoList.get( i ).list, mContentId, this.mBangumiDetailBean );
	}

	public int getCount() {
		return mGroupTitleBeanList == null ? 0 : mGroupTitleBeanList.size();
	}

	public CharSequence getPageTitle( int i ) {
		return mGroupTitleBeanList.get( i ).name;
	}
}
