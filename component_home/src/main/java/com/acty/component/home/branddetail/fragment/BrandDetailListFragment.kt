package com.acty.component.home.branddetail.fragment

import android.os.Bundle
import com.acty.component_home.R
import com.gent.youxidandan.ddsocial.ui.mytest.model.BrandDetailListModelImp
import com.gent.youxidandan.ddsocial.ui.mytest.presenter.BrandDetailListPresenterImp
import com.hubertyoung.common.base.BaseFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BrandDetailListFragment : BaseFragment<BrandDetailListPresenterImp, BrandDetailListModelImp>() {

	private var param1: String? = null
	private var param2: String? = null

	companion object {
		@JvmStatic
		fun newInstance(param1: String, param2: String) = BrandDetailListFragment().apply {
			arguments = Bundle().apply {
				putString(ARG_PARAM1, param1)
				putString(ARG_PARAM2, param2)
			}
		}
	}
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		arguments?.let {
			param1 = it.getString(ARG_PARAM1)
			param2 = it.getString(ARG_PARAM2)
		}
	}

	override fun initToolBar() {
	}

	override fun getLayoutResource(): Int {
		return R.layout.fragment_brand_detail_list
	}

	override fun initPresenter() {
	}

	override fun initView(savedInstanceState: Bundle?) {
	}

}
