package com.hubertyoung.common.base

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.hubertyoung.common.R
import com.hubertyoung.common.image.fresco.ImageLoaderUtil
import com.hubertyoung.common.utils.LoadingThemeUtil
import com.hubertyoung.common.utils.display.ToastUtil
import com.hubertyoung.common.utils.os.NetworkUtil
import com.hubertyoung.common.widget.sectioned.Section
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter
import com.hubertyoung.component_skeleton.skeleton.ViewReplacer
import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * @desc:
 * @author:HubertYoung
 * @date 2019/1/7 13:10
 * @since:
 * @see com.hubertyoung.common.base.BaseListFragment
 */
abstract class BaseListFragment<VM : AbsViewModel<*>> : AbsLifecycleFragment<VM>() {

	lateinit var recyclerView: RecyclerView

	lateinit var srlContainer: SmartRefreshLayout

	protected lateinit var mToolbar: Toolbar

	private lateinit var mTitle: TextView

	lateinit var adapter: SectionedRecyclerViewAdapter

	private lateinit var mViewReplacer: ViewReplacer
	private var mLoadingLayout: View? = null
	private lateinit var mErrorLayout: View

	override fun getLayoutResource(): Int {
		return R.layout.fragment_list
	}

	override fun initView(state: Bundle?) {
		super.initView(state)
		recyclerView = findViewById(R.id.recycler_view)
		srlContainer = findViewById(R.id.srl_container)
		mToolbar = findViewById(R.id.view_toolbar)
		mTitle = findViewById(R.id.toolbar_title)

		adapter = SectionedRecyclerViewAdapter()
		recyclerView?.layoutManager = createLayoutManager()
		recyclerView?.setHasFixedSize(true)
		recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
			override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
				//				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
				//					if (activity != null) {
				//						Glide.with(activity).resumeRequests();
				//					}
				//				} else {
				//					if (activity != null) {
				//						Glide.with(activity).pauseRequests();
				//					}
				//				}
			}
		})
		recyclerView?.adapter = adapter
		mViewReplacer = ViewReplacer(srlContainer)

		showLoadingLayout()

		srlContainer?.setOnRefreshListener {
			adapter?.pageBean.refresh = true
			srlContainer?.finishLoadMore()
			srlContainer?.setNoMoreData(false)
			loadData()
		}
		srlContainer?.setOnLoadMoreListener {
			adapter?.pageBean.refresh = false
			loadNewData()
		}
	}

	private fun showLoadingLayout() {
		if (mViewReplacer != null) {
			if (mLoadingLayout == null) {
				mLoadingLayout = LayoutInflater.from(mActivity).inflate(R.layout.widget_loading_holder, null)
			}
			mViewReplacer?.replace(mLoadingLayout)

			if (mViewReplacer?.currentView != null) {
				mViewReplacer?.currentView.visibility = View.VISIBLE
				val simpleDraweeView = mViewReplacer?.currentView.findViewById<SimpleDraweeView>(R.id.widget_loading_holder_gif)
				simpleDraweeView.hierarchy.setPlaceholderImage(LoadingThemeUtil.getPageLoadingImages())
				ImageLoaderUtil.loadNetImage(LoadingThemeUtil.getPageLoadingFileImages(), simpleDraweeView)
			}
		}
	}

	protected open abstract fun loadNewData()

	protected open abstract fun loadData()

	override fun lazyLoad() {
		if (NetworkUtil.isNetAvailable()) {
			showLoadingLayout()
			loadData()
		} else {
			ToastUtil.showError(mActivity.getString(R.string.net_status_not_work))
			showErrorLayout("")
		}
	}

	public override fun stopLoading() {
		srlContainer?.finishRefresh()
		srlContainer?.finishLoadMore()
		if (mViewReplacer != null) {
			mViewReplacer?.restore()
		}
	}

	/**
	 * LayoutManager
	 *
	 * @return LayoutManager
	 */
	protected abstract fun createLayoutManager(): RecyclerView.LayoutManager

	/**
	 * addSection
	 *
	 * @param section
	 */
	protected fun addSection(section: Section) {
		if (adapter != null) {
			adapter?.addSection(section)
		}
	}

	/**
	 * show error layout
	 */
	override fun showErrorLayout(result: String) {
		if (mViewReplacer != null && adapter != null && adapter?.mPageBean.refresh) {
			if (mErrorLayout == null) {
				mErrorLayout = LayoutInflater.from(mActivity).inflate(R.layout.widget_error_holder, null)
			}
			val tvErrorContent = mErrorLayout?.findViewById<TextView>(R.id.tv_error_content)
			if (!TextUtils.isEmpty(result)) {
				tvErrorContent.text = result
			} else {
				tvErrorContent.setText(R.string.error_page_title)
			}
			mViewReplacer?.replace(mErrorLayout)
			if (mViewReplacer?.currentView != null) {
				mViewReplacer?.currentView.visibility = View.VISIBLE
				val refreshClick = mViewReplacer?.currentView.findViewById<TextView>(R.id.refresh_click)
				refreshClick.setOnClickListener { lazyLoad() }
			}
		} else {
			stopLoading()
		}
	}

	/**
	 * setTitleName
	 *
	 * @param titleName
	 * @param isCenter
	 */
	protected fun setTitle(titleName: String, isCenter: Boolean) {
		if (isCenter) {
			mTitle?.text = titleName
			mTitle?.visibility = View.VISIBLE
			mToolbar.visibility = View.GONE
			mToolbar.title = ""
		} else {
			mTitle?.visibility = View.GONE
			mToolbar.visibility = View.VISIBLE
			mToolbar.title = titleName
		}
	}

	/**
	 * setToolbarVisible
	 *
	 * @param isShow
	 */
	protected fun setTitleLayoutVisible(isShow: Boolean) {
		mToolbar.visibility = if (isShow) View.VISIBLE else View.GONE
	}


	override fun initToolBar() {

	}
}