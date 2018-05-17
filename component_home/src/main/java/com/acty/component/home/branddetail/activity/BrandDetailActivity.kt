package com.acty.component.home.branddetail.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.AppCompatTextView
import com.acty.component.home.branddetail.adapter.BrandDetailAdapter
import com.acty.component.home.branddetail.control.BrandDetailControl
import com.acty.component.home.entity.BrandDetailEntity
import com.acty.component_banner.banner.BannerScroller
import com.acty.component_banner.banner.transformer.FadeInOutPageTransformer
import com.acty.component_home.R
import com.gent.youxidandan.ddsocial.ui.mytest.model.BrandDetailModelImp
import com.gent.youxidandan.ddsocial.ui.mytest.presenter.BrandDetailPresenterImp
import com.hubertyoung.common.base.BaseActivity
import com.hubertyoung.common.basebean.MyRequestMap
import com.hubertyoung.common.utils.BarUtils
import com.hubertyoung.common.utils.CommonLog
import com.hubertyoung.common.utils.ToastUtil
import com.kento.component_skeleton.skeleton.Skeleton
import com.kento.component_skeleton.skeleton.ViewSkeletonScreen
import kotlinx.android.synthetic.main.home_activity_brand_detail.*

/**
 * <br>
 * function:品牌详情
 * <p>
 * @author:HubertYoung
 * @date:2018/5/15 2018/5/15
 * @since:V1.0
 * @desc:com.acty.component.home.branddetail.activity
 */
class BrandDetailActivity : BaseActivity<BrandDetailPresenterImp, BrandDetailModelImp>(), BrandDetailControl.View {

	private var brandDetailAdapter = BrandDetailAdapter(fm = supportFragmentManager, activity = this@BrandDetailActivity)
	private var currentPosition: Int = 0

	override fun getLayoutId(): Int {
		return R.layout.home_activity_brand_detail
	}

	override fun initPresenter() {
		mPresenter.setVM(this, mModel)
	}

	override fun doBeforeSetContentView() {
		BarUtils.statusBarLightMode(this, true, 0f)
		BarUtils.immersiveStatusBar(window, 0f)
	}

	override fun initView(savedInstanceState: Bundle?) {
		initViewPager()
	}

	private lateinit var mViewSkeletonScreen: ViewSkeletonScreen

	private fun initViewPager() {
		mViewSkeletonScreen = Skeleton.bind(vpHomeBrandDetail).shimmer(true).duration(1200).angle(20).load(R.layout.common_item_skeleton).show()
		initViewPagerScroll(vpHomeBrandDetail)

		vpHomeBrandDetail.adapter = brandDetailAdapter

		vpHomeBrandDetail.setPageTransformer(true, FadeInOutPageTransformer())

		vpHomeBrandDetail.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
			override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

			}

			override fun onPageSelected(position: Int) {
				currentPosition = position
			}

			override fun onPageScrollStateChanged(state: Int) {

			}
		})
	}

	private fun initViewPagerScroll(viewPager: ViewPager) {
		try {
			val mField = ViewPager::class.java.getDeclaredField("mScroller")
			mField.isAccessible = true
			val mScroller = BannerScroller(viewPager.context)
			mScroller.duration = 600
			mField.set(viewPager, mScroller)
		} catch (e: Exception) {
			CommonLog.loge(e)
		}

	}
	override fun loadData() {
		val map = MyRequestMap()
		map.put("id","1008009")
		mPresenter.requestBrandDetail(map)
	}
	override fun setBrandDetailInfo(brandDetailEntity: BrandDetailEntity) {
		var tvHeadTitle = findViewById<AppCompatTextView>(R.id.tv_head_title)
		tvHeadTitle.text = brandDetailEntity.parentCategory.name
		tlHomeBrandDetail.removeAllTabs()

		vpHomeBrandDetail.offscreenPageLimit = brandDetailEntity.brotherCategory.size
		tlHomeBrandDetail.setupWithViewPager(vpHomeBrandDetail)
		brandDetailAdapter.setList( brandDetailEntity.brotherCategory )

		for (i in brandDetailEntity.brotherCategory.indices) {
			tlHomeBrandDetail.addTab(tlHomeBrandDetail.newTab())
		}
		brandDetailAdapter.notifyDataSetChanged()
	}
	override fun initToolBar() {
	}

	override fun showLoading(title: String?, type: Int) {
	}

	override fun stopLoading() {
		if (mViewSkeletonScreen != null && mViewSkeletonScreen.isShowing()) {
			mViewSkeletonScreen.hide()
		}
	}

	override fun showErrorTip(msg: String?) {
		ToastUtil.showError(msg)
	}

}
