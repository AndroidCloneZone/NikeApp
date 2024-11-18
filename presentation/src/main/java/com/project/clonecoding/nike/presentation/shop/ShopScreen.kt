package com.project.clonecoding.nike.presentation.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.project.clonecoding.nike.designsystem.navigation.BaseBottomNavBar
import com.project.clonecoding.nike.designsystem.navigation.NavItem
import com.project.clonecoding.nike.designsystem.theme.black
import com.project.clonecoding.nike.designsystem.theme.gray600
import com.project.clonecoding.nike.designsystem.theme.nikeTypography
import com.project.clonecoding.nike.designsystem.theme.white
import com.project.clonecoding.nike.designsystem.ui.BaseControlBar

@Composable
fun ShopScreen(
    navController: NavHostController,
    viewModel: ShopViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            BaseControlBar(
                title = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(white)
                    .statusBarsPadding(),
                onSearch = {}
            )
        },
        bottomBar = {
            BaseBottomNavBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(white)
        ) {
            ShopScreenHeader(modifier = Modifier.fillMaxWidth())
            ShopMainTab(modifier = Modifier.fillMaxWidth())
            ShopMainContent(
                cardList = state.value.cardList,
                bannerList = state.value.bannerList,
                onCardClick = {
                    navController.navigate(NavItem.ShopCollection.route)
                },
                onBannerClick = {
                    navController.navigate(NavItem.ShopCollection.route)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShopScreenPreview() {
    Scaffold(
        topBar = {
            BaseControlBar(
                title = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(white)
                    .statusBarsPadding(),
                onSearch = {}
            )
        },
        bottomBar = {
            BaseBottomNavBar()
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(white)
        ) {
            ShopScreenHeader(modifier = Modifier.fillMaxWidth())
            ShopMainTab(modifier = Modifier.fillMaxWidth())
            ShopMainContent(
                cardList = listOf(),
                bannerList = listOf(),
                onCardClick = {},
                onBannerClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ShopScreenHeader(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 24.dp),
        text = stringResource(id = com.project.clonecoding.nike.designsystem.R.string.bottom_nav_item_shop),
        style = nikeTypography.displayXlMedium,
        color = black
    )
}

@Composable
private fun ShopMainTab(
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val tabs = listOf("Men", "Women", "Kids")

    Column(modifier = modifier.fillMaxWidth()) {
        ScrollableTabRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            selectedTabIndex = selectedTabIndex,
            containerColor = white,
            contentColor = black,
            edgePadding = 0.dp
        ) {
            tabs.forEachIndexed { index, str ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(
                            text = str,
                            style = nikeTypography.text2XlMedium,
                            color = if (selectedTabIndex == index) black else gray600
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ShopMainContent(
    cardList: List<Pair<String, String>>,
    bannerList: List<Pair<String, String>>,
    onCardClick: () -> Unit,
    onBannerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                text = "Must-Haves, Best Sellers & More",
                style = nikeTypography.displaySmMedium,
                color = black
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = cardList) {
                    ShopMainContentSampleCard(
                        text = it.first,
                        imgPath = it.second,
                        onCardClick = onCardClick
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        items(items = bannerList) {
            ShopMainContentBanner(
                text = it.first,
                imgPath = it.second,
                onBannerClick = onBannerClick,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ShopMainContentSampleCard(
    text: String,
    imgPath: String,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .width(216.dp)
        .clickable { onCardClick() }) {
        GlideImage(
            modifier = Modifier.size(216.dp),
            model = imgPath,
            contentScale = ContentScale.Crop,
            contentDescription = "ShopMainContentSampleCardImg"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = text, style = nikeTypography.textMdBold, color = black)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ShopMainContentBanner(
    text: String,
    imgPath: String,
    onBannerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .padding(bottom = 4.dp)
        .clickable { onBannerClick() }) {
        GlideImage(
            modifier = Modifier.aspectRatio(375f / 110f),
            model = imgPath,
            contentDescription = "ShopMainContentSampleCardImg"
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 24.dp),
            text = text,
            style = nikeTypography.displaySmMedium,
            color = white
        )
    }
}