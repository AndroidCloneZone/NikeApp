package com.project.clonecoding.nike.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.project.clonecoding.nike.designsystem.navigation.BaseBottomNavBar
import com.project.clonecoding.nike.designsystem.navigation.NavItem
import com.project.clonecoding.nike.designsystem.theme.black
import com.project.clonecoding.nike.designsystem.theme.error500
import com.project.clonecoding.nike.designsystem.theme.gray600
import com.project.clonecoding.nike.designsystem.theme.nikeTypography
import com.project.clonecoding.nike.designsystem.theme.warning500
import com.project.clonecoding.nike.designsystem.theme.white
import com.project.clonecoding.nike.designsystem.ui.BaseControlBar
import com.project.clonecoding.nike.designsystem.ui.BaseIconButton
import com.project.clonecoding.nike.domain.model.ProductModel
import com.project.clonecoding.nike.presentation.R
import com.project.clonecoding.nike.presentation.shop.ShopEvent
import com.project.clonecoding.nike.presentation.shop.ShopViewModel
import com.project.clonecoding.nike.presentation.shop.item.FilterCategory
import com.project.clonecoding.nike.presentation.util.StringUtil.toPriceFormat

/**
 * Nike 컬렉션 화면 UI 컴포저블
 * - 상단 앱 바, 카테고리 탭, 상품 그리드, 하단 네비게이션을 포함하는 전체 화면
 */

@Composable
fun CollectionScreen(
    navController: NavHostController,
    viewModel: ShopViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    // 클릭 시 차단 상태를 관리할 플래그
    var isExitCalled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            BaseControlBar(
                title = "N7 Collection",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(white)
                    .statusBarsPadding(),
                onBackPressed = {
                    if (!isExitCalled) {
                        navController.popBackStack()
                        isExitCalled = true
                    }
                },
                onSearch = {},
                onFilter = {
                    navController.navigate(NavItem.ProductFilter.route)
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            CollectionTabs(
                selectedCategory = state.value.category,
                onCategorySelect = { category ->
                    if (state.value.category != category) {
                        viewModel.onEvent(ShopEvent.ChangeCategory(category))
                    }
                }
            )
            CollectionItemsGrid(
                productList = state.value.productList,
                isProductsLoading = state.value.isProductsLoading,
                onFetchProducts = {
                    viewModel.onEvent(ShopEvent.FetchProducts)
                }
            )
        }
    }
}

/**
 * 컬렉션 탭 UI 컴포저블
 * - 카테고리별로 선택 가능한 탭을 제공합니다.
 */

@Composable
fun CollectionTabs(
    selectedCategory: FilterCategory,
    onCategorySelect: (FilterCategory) -> Unit
) {
    val tabs = listOf(
        FilterCategory.All,
        FilterCategory.Beauty,
        FilterCategory.Shoes,
        FilterCategory.Top,
        FilterCategory.Outer,
        FilterCategory.Pants,
        FilterCategory.Skirt,
        FilterCategory.Bag,
        FilterCategory.Accessories,
        FilterCategory.Underwear,
        FilterCategory.Sportswear,
        FilterCategory.Digital,
        FilterCategory.Kids
    )

    val selectedTabIndex = tabs.indexOf(selectedCategory)

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.White,
        contentColor = Color.Black,
        edgePadding = 0.dp
    ) {
        tabs.forEachIndexed { index, category ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    onCategorySelect(tabs[index])
                },
                text = {
                    Text(
                        stringResource(id = category.strId),
                        fontSize = 14.sp,
                        fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}

/**
 * 컬렉션 아이템 그리드 UI 컴포저블
 * - 각 상품 아이템을 카드 형식으로 그리드에 표시합니다.
 */

@Composable
fun CollectionItemsGrid(
    productList: List<ProductModel>,
    isProductsLoading: Boolean,
    onFetchProducts: () -> Unit
) {
    val listState = rememberLazyGridState()

    // listState, productList, isProductsLoading이 변경될 때마다 내부 LaunchEffect를 수행시켜줌
    LaunchedEffect(listState, productList, isProductsLoading) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItem = visibleItems.lastOrNull()
                if (productList.isNotEmpty() && !isProductsLoading) {
                    if (lastVisibleItem != null && lastVisibleItem.index == listState.layoutInfo.totalItemsCount - 1) {
                        onFetchProducts()
                    }
                }
            }
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 6.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.background(Color.White),
        state = listState
    ) {
        items(productList) { item ->
            CollectionItemCard(data = item, isBestSeller = item.likeCount > 9500)
        }
    }
}

/**
 * 컬렉션 아이템 카드 UI 컴포저블
 * - 각 상품 정보를 카드 형태로 표시하며, 즐겨찾기 버튼 포함
 * @param data 표시할 상품 정보
 */

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CollectionItemCard(
    data: ProductModel,
    isBestSeller: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White), // 카드 배경색 회색 설정
        colors = CardDefaults.cardColors(containerColor = Color.White), // Material3 Card의 배경색 설정
        shape = RectangleShape // 카드 모양을 직사각형으로 설정
    ) {
        Column() { // 여백 설정
            Box {
                GlideImage(
                    model = data.imgPath,
                    contentDescription = data.id,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(184.dp),
                    contentScale = ContentScale.Crop
                )
                BaseIconButton(
                    iconId = com.project.clonecoding.nike.designsystem.R.drawable.ic_line_heart_14, // 즐겨찾기 아이콘
                    modifier = Modifier
                        .padding(8.dp) // 외부 마진
                        .size(34.dp)
                        .align(Alignment.TopEnd)
                        .background(Color.White, shape = CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            Column(modifier = Modifier.padding(horizontal = 14.dp)) {
                if (isBestSeller) {
                    Text(text = "BestSeller", style = nikeTypography.textMdBold, color = warning500)
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = data.name, style = nikeTypography.textMdBold, color = black)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = data.category, style = nikeTypography.textMdRegular, color = gray600)
                Spacer(modifier = Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = com.project.clonecoding.nike.designsystem.R.drawable.ic_line_heart_14),
                        tint = error500,
                        contentDescription = "${data.id}_like_icon"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${data.likeCount}",
                        style = nikeTypography.textMdRegular,
                        color = gray600
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${data.price.toPriceFormat()} ${stringResource(id = R.string.filter_price_unit)}",
                    style = nikeTypography.textMdBold,
                    color = black
                )
            }
        }
    }
}

/**
 * 하단 네비게이션 바 UI 컴포저블
 * - 기존의 BaseBottomNavBar를 사용하여 네비게이션 구현
 */

@Composable
fun CollectionBottomNavBar() {
    val navController = rememberNavController()
    BaseBottomNavBar(navController = navController)
}

/**
 * CollectionScreen 미리보기 컴포저블
 */

@Preview(showBackground = true)
@Composable
fun CollectionScreenPreview() {
    var currCategory by rememberSaveable {
        mutableStateOf(FilterCategory.All)
    }
    Scaffold(
        topBar = {
            BaseControlBar(
                title = "N7 Collection",
                modifier = Modifier.background(white),
                onBackPressed = {},
                onSearch = {},
                onFilter = {},
            )
        },
        bottomBar = { CollectionBottomNavBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            CollectionTabs(
                selectedCategory = currCategory,
                onCategorySelect = { category ->
                    currCategory = category
                }
            )
            CollectionItemsGrid(
                productList = listOf(),
                isProductsLoading = false,
                onFetchProducts = {

                }
            )
        }
    }
}