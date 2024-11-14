package com.project.clonecoding.nike.designsystem.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.project.clonecoding.nike.designsystem.theme.black
import com.project.clonecoding.nike.designsystem.theme.gray600
import com.project.clonecoding.nike.designsystem.theme.nikeTypography
import com.project.clonecoding.nike.designsystem.theme.white

/**
 * 기본으로 들어가는 바텀 네비게이션
 * @author 이유호
 * @param navController 바텀 네비게이션 조작을 위한 NavHostController
 * @param items 바텀 네비게이션에 들어갈 아이템, default: list(Home, Shop, Favorites, Bag, Profile)
 */
@Composable
fun BaseBottomNavBar(
    navController: NavHostController = rememberNavController(),
    items: List<NavItem> = listOf(
        NavItem.Home,
        NavItem.Shop,
        NavItem.Favorites,
        NavItem.Bag,
        NavItem.Profile
    )
) {
    NavigationBar(
        containerColor = white,
        contentColor = gray600
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.route == item.route,
                icon = {
                    if(item.icon != null){
                        Icon(
                            modifier = Modifier.size(28.dp),
                            painter = painterResource(id = item.icon),
                            contentDescription = "BaseBottomNavBarItemIcon${item.route}"
                        )
                    }
                },
                label = {
                    if(item.strId != null){
                        Text(
                            text = stringResource(id = item.strId),
                            style = nikeTypography.textXsRegular,
                        )
                    }
                },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = black,
                    unselectedIconColor = gray600,
                    selectedTextColor = black,
                    unselectedTextColor = gray600,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BaseBottomNavBarPreview() {
    val navHostController = rememberNavController()
    Scaffold(bottomBar = {
        BaseBottomNavBar(
            navController = navHostController
        )
    }) {
        NavigationGraph(navController = navHostController)
        Column(modifier = Modifier.padding(it)) {

        }
    }
}

@Composable
private fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.Home.route,
        modifier = modifier
    ) {
        composable(NavItem.Home.route) {

        }
        composable(NavItem.Shop.route) {

        }
        composable(NavItem.Favorites.route) {

        }
        composable(NavItem.Bag.route) {

        }
        composable(NavItem.Profile.route) {

        }
    }
}
