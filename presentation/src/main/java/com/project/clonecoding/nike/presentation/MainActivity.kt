package com.project.clonecoding.nike.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.clonecoding.nike.designsystem.navigation.NavItem
import com.project.clonecoding.nike.designsystem.theme.NikeTheme
import com.project.clonecoding.nike.presentation.home.HomeScreen
import com.project.clonecoding.nike.presentation.home.HomeViewModel
import com.project.clonecoding.nike.presentation.home.screen.CollectionScreen
import com.project.clonecoding.nike.presentation.home.screen.HomeNewsDetailScreen
import com.project.clonecoding.nike.presentation.shop.ShopFilterScreen
import com.project.clonecoding.nike.presentation.shop.ShopScreen
import com.project.clonecoding.nike.presentation.shop.ShopViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            NikeTheme {
                NavigationGraph(navHostController = navHostController)
            }
        }
    }
}

@Composable
private fun NavigationGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val shopViewModel = hiltViewModel<ShopViewModel>()

    NavHost(
        navController = navHostController,
        startDestination = NavItem.Home.route,
        modifier = modifier
    ) {
        composable(NavItem.Home.route) {
            HomeScreen(
                navHostController = navHostController,
                viewModel = homeViewModel
            )
        }

        composable(NavItem.HomeNewsDetail.route) {
            HomeNewsDetailScreen(
                navHostController = navHostController,
                viewModel = homeViewModel
            )
        }

        composable(NavItem.Shop.route) {
            ShopScreen(
                navController = navHostController,
                viewModel = shopViewModel
            )
        }

        composable(NavItem.ShopCollection.route) {
            CollectionScreen(
                navController = navHostController,
                viewModel = shopViewModel
            )
        }

        composable(NavItem.Favorites.route) {

        }
        composable(NavItem.Bag.route) {

        }
        composable(NavItem.Profile.route) {

        }

        composable(NavItem.ProductFilter.route) {
            ShopFilterScreen(
                navController = navHostController,
                viewModel = shopViewModel
            )
        }
    }
}