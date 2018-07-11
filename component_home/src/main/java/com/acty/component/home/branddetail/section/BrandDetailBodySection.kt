package com.acty.component.home.branddetail.section

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.acty.component.home.entity.Goods
import com.acty.component_home.R
import com.hubertyoung.common.ImageLoaderUtils
import com.hubertyoung.common.base.BaseActivity
import com.hubertyoung.common.widget.sectioned.Section
import com.hubertyoung.common.widget.sectioned.SectionParameters
import kotlinx.android.synthetic.main.home_brand_detail_head_body.view.*
import kotlinx.android.synthetic.main.home_item_floor_goods_body.view.*
import java.util.*

/**
 * <br>
 * function:
 * <p>
 * @author:HubertYoung
 * @date:2018/5/18 2018/5/18
 * @since:V1.0
 * @desc:com.acty.component.home.branddetail.section
 */
class BrandDetailBodySection(var activity: BaseActivity<*, *>) : Section(SectionParameters.Builder(R.layout.home_item_floor_goods_body).failedResourceId(R.layout.common_section_error).headerResourceId(R.layout
		.home_brand_detail_head_body).build
()) {

	private var data: List<Goods> = ArrayList()
	private var name: String = ""
	private var frontName: String = ""

	override fun getContentItemsTotal(): Int {
		return data?.size
	}

	override fun getHeaderViewHolder(view: View?): RecyclerView.ViewHolder {
		return BrandDetailBodyHeadViewHolder(view)
	}

	override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
		(holder as BrandDetailBodyHeadViewHolder).bindView(activity, name,frontName)
	}

	override fun getItemViewHolder(view: View?, itemType: Int): RecyclerView.ViewHolder {
		return BrandDetailBodyViewHolder(view)
	}

	override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
		(holder as BrandDetailBodyViewHolder).bindView(activity, data[position])
	}

	fun setList(goodsList: List<Goods>, name: String?, frontName: String?) {
		this.data = goodsList
		name?.let { this.name = name }
		frontName?.let { this.frontName = frontName }
	}
	fun getList() : List<Goods>{
		return data
	}
	class BrandDetailBodyViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
		fun bindView(context: Context, goods: Goods) {
			with(itemView) {
				ImageLoaderUtils.getInstance().display(context, iv_index_floor_goods_icon, goods.listPicUrl)
				tv_index_floor_goods_name.text = goods.name
				tv_index_floor_goods_price.text = String.format("￥%s", goods.retailPrice)
			}
		}

	}

	class BrandDetailBodyHeadViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
		fun bindView(context: Context, name: String, frontName: String) {
			with(itemView) {
				tv_home_index_head.text = name
				tv_home_index_title.text = frontName
			}
		}

	}
}
