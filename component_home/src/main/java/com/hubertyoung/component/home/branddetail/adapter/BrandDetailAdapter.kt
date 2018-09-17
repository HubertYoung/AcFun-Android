package com.hubertyoung.component.home.branddetail.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import com.hubertyoung.component.home.branddetail.activity.BrandDetailActivity
import com.hubertyoung.component.home.branddetail.fragment.BrandDetailListFragment
import com.hubertyoung.component.home.entity.BrotherCategory
import com.hubertyoung.common.base.BaseFragment
import java.util.*

class BrandDetailAdapter(fm: FragmentManager?, var activity: BrandDetailActivity) : FragmentStatePagerAdapter(fm) {

	private var data: List<BrotherCategory> = ArrayList()

	private var fragments: LinkedList<BaseFragment<*,*>> = LinkedList()

	override fun getItem(position: Int): Fragment {
		val fragment = BrandDetailListFragment.newInstance(data[position].id.toString(), data[position].name, "" )
		fragments.add(fragment)
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

	override fun destroyItem(container: View, position: Int, `object`: Any) {
		fragments?.removeAt(position)
		super.destroyItem(container, position, `object`)
	}

	fun getList(): List<BrotherCategory> {
		return data
	}

	fun refreshTabInfo(currentPosition: Int) {
		fragments?.get(currentPosition).refreshData()
	}
}
