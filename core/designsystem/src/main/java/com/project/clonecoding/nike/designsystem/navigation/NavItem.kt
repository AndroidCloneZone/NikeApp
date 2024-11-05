package com.project.clonecoding.nike.designsystem.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.project.clonecoding.nike.designsystem.R

/**
 * 네비게이션 아이템
 * @author 이유호
 * @param route 네비게이션 컨트롤 간 사용하는 route string
 */
sealed class NavItem(
    val route: String,
    @DrawableRes val icon: Int? = null,
    @StringRes val strId: Int? = null
) {
    /** 바템 네비게이션 아이템 */
    data object Home : NavItem(
        route = "home",
        icon = R.drawable.ic_simple_house_24,
        strId = R.string.bottom_nav_item_home
    )

    data object Shop : NavItem(
        route = "shop",
        icon = R.drawable.ic_list_search_24,
        strId = R.string.bottom_nav_item_shop
    )

    data object Favorites : NavItem(
        route = "favorites",
        icon = R.drawable.ic_line_heart_24,
        strId = R.string.bottom_nav_item_favorites
    )

    data object Bag : NavItem(
        route = "bag",
        icon = R.drawable.ic_simple_bag_24,
        strId = R.string.bottom_nav_item_bag
    )

    data object Profile : NavItem(
        route = "user",
        icon = R.drawable.ic_user_24,
        strId = R.string.bottom_nav_item_profile
    )

    /** 일반 네비게이션 아이템 */
    data object HomeNewsDetail : NavItem(
        route = "home_news_detail"
    )
}