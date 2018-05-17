package com.acty.component.home.entity


/**
 * <br>
 * function:
 * <p>
 * @author:HubertYoung
 * @date:2018/5/17 09:21
 * @since:V$VERSION
 * @desc:com.acty.component.home.entity
 */


data class BrandDetailEntity(
    val currentCategory: CurrentCategory,
    val brotherCategory: List<BrotherCategory>,
    val parentCategory: ParentCategory
)

data class CurrentCategory(
    val id: Int,
    val name: String,
    val keywords: String,
    val frontDesc: String,
    val parentId: Int,
    val sortOrder: Int,
    val showIndex: Int,
    val isShow: Boolean,
    val bannerUrl: String,
    val iconUrl: String,
    val imgUrl: String,
    val wapBannerUrl: String,
    val level: String,
    val type: Int,
    val frontName: String,
    val addTime: String,
    val deleted: Boolean
)

data class BrotherCategory(
    val id: Int,
    val name: String,
    val keywords: String,
    val frontDesc: String,
    val parentId: Int,
    val sortOrder: Int,
    val showIndex: Int,
    val isShow: Boolean,
    val bannerUrl: String,
    val iconUrl: String,
    val imgUrl: String,
    val wapBannerUrl: String,
    val level: String,
    val type: Int,
    val frontName: String,
    val addTime: String,
    val deleted: Boolean
)

data class ParentCategory(
    val id: Int,
    val name: String,
    val keywords: String,
    val frontDesc: String,
    val parentId: Int,
    val sortOrder: Int,
    val showIndex: Int,
    val isShow: Boolean,
    val bannerUrl: String,
    val iconUrl: String,
    val imgUrl: String,
    val wapBannerUrl: String,
    val level: String,
    val type: Int,
    val frontName: String,
    val addTime: String,
    val deleted: Boolean
)