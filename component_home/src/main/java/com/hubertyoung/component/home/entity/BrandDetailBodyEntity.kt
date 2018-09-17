package com.hubertyoung.component.home.entity


/**
 * <br>
 * function:
 * <p>
 * @author:HubertYoung
 * @date:2018/5/17 09:21
 * @since:V1.0
 * @desc:com.hubertyoung.component.home.entity
 */




data class BrandDetailBodyEntity(
    var goodsList: List<Goods>,
    var count: Int,
    var filterCategoryList: List<FilterCategory>
)

data class Goods(
    var id: Int,
    var name: String,
    var listPicUrl: String,
    var retailPrice: Double
)

data class FilterCategory(
    var id: Int,
    var name: String,
    var keywords: String,
    var frontDesc: String,
    var parentId: Int,
    var sortOrder: Int,
    var showIndex: Int,
    var isShow: Boolean,
    var bannerUrl: String,
    var iconUrl: String,
    var imgUrl: String,
    var wapBannerUrl: String,
    var level: String,
    var type: Int,
    var frontName: String,
    var addTime: String,
    var deleted: Boolean
)