package com.acty.component.home.branddetail.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.acty.component.home.branddetail.activity.BrandDetailActivity
import com.acty.component.home.branddetail.fragment.BrandDetailListFragment
import com.acty.component.home.entity.BrotherCategory
import java.util.*

class BrandDetailAdapter(fm: FragmentManager?, var activity: BrandDetailActivity) : FragmentStatePagerAdapter(fm) {

	private var data: List<BrotherCategory> = ArrayList()

	override fun getItem(position: Int): Fragment {
		val fragment = BrandDetailListFragment.newInstance(data[position].id.toString(), "")
//		fragments.add(fragment)
		return fragment
	}

	override fun getCount(): Int {
		return data?.size
	}

	fun setList(brotherCategory: List<BrotherCategory>) {
		this.data = brotherCategory
	}

	override fun getPageTitle(position: Int): CharSequence? {
		return data[position].name
	}
}
