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
class BrandDetailBodySection(var activity: BaseActivity<*, *>) : Section(SectionParameters.Builder(R.layout.home_item_floor_goods_body).build()) {

	private var data: List<Goods> = ArrayList()

	override fun getContentItemsTotal(): Int {
		return data?.size
	}

	override fun getItemViewHolder(view: View?, itemType: Int): RecyclerView.ViewHolder {
		return BrandDetailBodyViewHolder(view)
	}

	override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
		(holder as BrandDetailBodyViewHolder).bindView(activity, data[position])
	}


	fun setList(goodsList: List<Goods>) {
		this.data = goodsList
	}

	class BrandDetailBodyViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
		fun bindView(context: Context, goods: Goods) {
			with(itemView) {
				ImageLoaderUtils.getInstance().display(context, itemView.findViewById(R.id.tv_index_floor_goods_more_icon), goods.listPicUrl)
			}
		}

	}
}
