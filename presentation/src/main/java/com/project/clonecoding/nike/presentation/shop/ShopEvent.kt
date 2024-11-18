package com.project.clonecoding.nike.presentation.shop

import com.project.clonecoding.nike.presentation.shop.item.FilterCategory
import com.project.clonecoding.nike.presentation.shop.item.ShopFilter

sealed class ShopEvent{
    data object FetchFilterBrands: ShopEvent()
    data object FetchProducts: ShopEvent()
    data class ChangeCategory(val category: FilterCategory): ShopEvent()
    data class ApplyFilter(val filter: ShopFilter): ShopEvent()
}