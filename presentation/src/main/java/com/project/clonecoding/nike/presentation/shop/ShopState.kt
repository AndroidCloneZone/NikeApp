package com.project.clonecoding.nike.presentation.shop

import com.project.clonecoding.nike.domain.model.ProductModel
import com.project.clonecoding.nike.presentation.shop.item.FilterCategory
import com.project.clonecoding.nike.presentation.shop.item.ShopFilter

data class ShopState (
    val cardList: List<Pair<String, String>> = listOf(
        Pair(
            "BestSellers",
            "https://firebasestorage.googleapis.com/v0/b/nikeclonecoding.firebasestorage.app/o/shop_main_img_1.png?alt=media&token=029f407d-8ad9-436f-bfca-d10e71556d88"
        ),
        Pair(
            "Featured in Nike Air",
            "https://firebasestorage.googleapis.com/v0/b/nikeclonecoding.firebasestorage.app/o/shop_main_img_2.png?alt=media&token=f6311565-4c27-4c9d-9b39-f0c68c77b362"
        )
    ),

    val bannerList: List<Pair<String, String>> = listOf(
        Pair(
            "New & Featured",
            "https://firebasestorage.googleapis.com/v0/b/nikeclonecoding.firebasestorage.app/o/shop_main_banner_img_2.png?alt=media&token=157dca7e-72e2-4259-a978-7b16b375520f"
        ),
        Pair(
            "New & Featured",
            "https://firebasestorage.googleapis.com/v0/b/nikeclonecoding.firebasestorage.app/o/shop_main_banner_img_1.png?alt=media&token=8e5a0de0-8644-4401-ba68-c78f018120a3"
        )
    ),

    // 상품 목록
    var productList: List<ProductModel> = listOf(),

    // 카테고리
    val category: FilterCategory = FilterCategory.All,

    // 상품 필터 관련
    var shopFilter: ShopFilter = ShopFilter(),
    var loadedBrandList: List<String> = listOf(),

    var productStartAfter: String? = null,
    var brandStartAfter: String? = null,

    // product 페이지 당 개수
    var productLimit: Int = 20,
    // brand 페이지 당 개수
    var brandLimit: Int = 40,

    var isLoading: Boolean = false,
    var isProductsLoading: Boolean = false,
    var isFilterBrandsLoading: Boolean = false
)