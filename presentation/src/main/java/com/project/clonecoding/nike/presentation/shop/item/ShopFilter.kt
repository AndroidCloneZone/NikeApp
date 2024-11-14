package com.project.clonecoding.nike.presentation.shop.item

data class ShopFilter(
    val sortBy: FilterSortBy = FilterSortBy.Like,
    val gender: List<FilterGender> = listOf(),
    val brand: List<String> = listOf(),
    val minPrice: Int? = null,
    val maxPrice: Int? = null
)