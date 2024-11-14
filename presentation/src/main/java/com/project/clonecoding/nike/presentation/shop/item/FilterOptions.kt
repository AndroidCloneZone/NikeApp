package com.project.clonecoding.nike.presentation.shop.item

import androidx.annotation.StringRes
import com.project.clonecoding.nike.presentation.R

enum class FilterSortBy(val value: String, @StringRes val strId: Int) {
    Like(value = "like", strId = R.string.filter_sort_by_like),
    Name(value = "name", strId = R.string.filter_sort_by_name),
    PriceAsc(value = "priceAsc", strId = R.string.filter_sort_by_price_asc),
    PriceDesc(value = "priceDesc", strId = R.string.filter_sort_by_price_desc),
}

enum class FilterGender(val value: String, @StringRes val strId: Int) {
    Male(value = "M", strId = R.string.filter_gender_men),
    Female(value = "F", strId = R.string.filter_gender_woman),
    Unisex(value = "FM", strId = R.string.filter_gender_unisex),
}

enum class FilterCategory(val value: String?, @StringRes val strId: Int) {
    All(value = null, strId = R.string.filter_category_all),
    Beauty(value = "Beauty", strId = R.string.filter_category_beauty),
    Shoes(value = "Shoes", strId = R.string.filter_category_shoes),
    Top(value = "Top", strId = R.string.filter_category_top),
    Outer(value = "Outer", strId = R.string.filter_category_outer),
    Pants(value = "Pants", strId = R.string.filter_category_pants),
    Skirt(value = "Skirt", strId = R.string.filter_category_skirt),
    Bag(value = "Bag", strId = R.string.filter_category_bag),
    Accessories(value = "Accessories", strId = R.string.filter_category_accessories),
    Underwear(value = "Underwear", strId = R.string.filter_category_underwear),
    Sportswear(value = "Sportswear", strId = R.string.filter_category_sportswear),
    Digital(value = "Digital", strId = R.string.filter_category_digital),
    Kids(value = "Kids", strId = R.string.filter_category_kids)
}