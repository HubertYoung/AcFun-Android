package com.acty.component.home.branddetail.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.acty.component.home.branddetail.control.BrandDetailListControl
import com.acty.component.home.branddetail.section.BrandDetailBodySection
import com.acty.component.home.entity.BrandDetailBodyEntity
import com.acty.component_home.R
import com.gent.youxidandan.ddsocial.ui.mytest.model.BrandDetailListModelImp
import com.gent.youxidandan.ddsocial.ui.mytest.presenter.BrandDetailListPresenterImp
import com.hubertyoung.common.base.BaseActivity
import com.hubertyoung.common.base.BaseFragment
import com.hubertyoung.common.basebean.MyRequestMap
import com.hubertyoung.common.utils.ToastUtil
import com.hubertyoung.common.widget.decoration.GridDividerItemDecoration
import com.hubertyoung.common.widget.sectioned.Section
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter
import com.kento.component_skeleton.skeleton.RecyclerViewSkeletonScreen
import com.kento.component_skeleton.skeleton.Skeleton
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.home_fragment_brand_detail_list.*

private const val ARG_PARAM1 = "BrandDetail"
private const val ARG_PARAM2 = "name"
private const val ARG_PARAM3 = "frontName"

class BrandDetailListFragment : BaseFragment<BrandDetailListPresenterImp, BrandDetailListModelImp>(), BrandDetailListControl.View {

	private var categoryId: String? = null
	private var name: String? = null
	private var frontName: String? = null
	private lateinit var mAdapter: SectionedRecyclerViewAdapter

	private lateinit var brandDetailBodySection: BrandDetailBodySection
	private lateinit var mViewSkeletonScreen: RecyclerViewSkeletonScreen
	private var isFirstEnter = true

	companion object {
		@JvmStatic
		fun newInstance(param1: String, param2: String, frontName: String) = BrandDetailListFragment().apply {
			arguments = Bundle().apply {
				putString(ARG_PARAM1, param1)
				putString(ARG_PARAM2, param2)
				putString(ARG_PARAM3, frontName )
			}
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		arguments?.let {
			categoryId = it.getString(ARG_PARAM1)
			name = it.getString(ARG_PARAM2)
			frontName = it.getString(ARG_PARAM3)
		}
	}

	override fun initToolBar() {
	}

	override fun getLayoutResource(): Int {
		return R.layout.home_fragment_brand_detail_list
	}

	override fun initPresenter() {
		mPresenter.setVM(this, mModel)
	}

	override fun initView(savedInstanceState: Bundle?) {
		isPrepared = true
		lazyLoad()
	}

	private fun initAction() {
		mSrlRefreshHomeIndex.setOnRefreshListener(OnRefreshListener {
			mAdapter?.pageBean.refresh = true
			mSrlRefreshHomeIndex.finishLoadMore()
			mSrlRefreshHomeIndex.setNoMoreData(false)
			loadData()
		})
		mSrlRefreshHomeIndex.setOnLoadMoreListener(OnLoadMoreListener {
			mAdapter?.pageBean.refresh = false
			loadData()
		})
	}


	private fun initRecyclerView() {
		rv_body.setHasFixedSize(true)
		rv_body.isNestedScrollingEnabled = false
		mAdapter = SectionedRecyclerViewAdapter()
		brandDetailBodySection = BrandDetailBodySection(activity as BaseActivity<*, *>)
		mAdapter.addSection(brandDetailBodySection)
		val gridLayoutManager = GridLayoutManager(activity, 2)
		gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
			override fun getSpanSize(position: Int): Int {
				return try {
					if (mAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) 2 else 1
				} catch (e: Exception) {
					1
				}
			}
		}
		rv_body.layoutManager = gridLayoutManager
		mViewSkeletonScreen = Skeleton.bind(rv_body)//
				.adapter(mAdapter)//
				.shimmer(true)//
				.duration(1200)//
				.angle(20)//
				.load(R.layout.common_item_skeleton)//
				.show()
		var dividerItemDecoration = GridDividerItemDecoration(activity, GridDividerItemDecoration.GRID_DIVIDER_VERTICAL)
		dividerItemDecoration.setVerticalDivider(activity.resources.getDrawable(R.drawable.home_brand_divider))
		dividerItemDecoration.setHorizontalDivider(activity.resources.getDrawable(R.drawable.home_brand_divider))
		rv_body.addItemDecoration(dividerItemDecoration)
	}

	override fun lazyLoad() {
		if (!isPrepared || !isVisible) {
			return
		}
		initRecyclerView()
		initAction()
		isPrepared = false
		if (isFirstEnter) {
			isFirstEnter = false
			loadData()
		}
	}

	override fun loadData() {
		val map = MyRequestMap()
		map.put("categoryId", categoryId)
		map.put("page", mAdapter?.mPageBean.loadPage.toString())
		map.put("size", mAdapter?.mPageBean.rows.toString())
		mPresenter.requestBrandDetailList(map)
	}

	override fun setBrandDetailListInfo(brandDetailBodyEntity: BrandDetailBodyEntity) {
		brandDetailBodySection.setList(brandDetailBodyEntity?.goodsList, name, frontName)
	}

	override fun showLoading(title: String?, type: Int) {
	}

	override fun stopLoading() {
		mSrlRefreshHomeIndex?.finishRefresh()
		mSrlRefreshHomeIndex?.finishLoadMore()
		if (mViewSkeletonScreen?.isShowing) {
			mViewSkeletonScreen?.hide()
		}
	}

	override fun showErrorTip(msg: String?) {
		if (mAdapter.pageBean.refresh) {
			brandDetailBodySection?.state = Section.State.FAILED
		}
		ToastUtil.showError(msg)
	}

}
