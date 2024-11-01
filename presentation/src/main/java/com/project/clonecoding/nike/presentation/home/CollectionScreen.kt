package com.project.clonecoding.nike.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.project.clonecoding.nike.designsystem.ui.BaseBottomNavBar
import com.project.clonecoding.nike.designsystem.ui.BaseIconButton
import com.project.clonecoding.nike.presentation.R

/**
 * Nike 컬렉션 화면 UI 컴포저블
 * - 상단 앱 바, 카테고리 탭, 상품 그리드, 하단 네비게이션을 포함하는 전체 화면
 */

@Composable
fun CollectionScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { CollectionTopAppBar() },
        bottomBar = { CollectionBottomNavBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            CollectionTabs()
            CollectionItemsGrid()
        }
    }
}

/**
 * 상단 앱 바 UI 컴포저블
 * - 타이틀과 뒤로가기, 정렬, 검색 아이콘을 포함합니다.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "N7 Collection",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center // 타이틀을 중앙 정렬
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* TODO: 뒤로가기 처리 */ }) {
                Icon(painter = painterResource(com.project.clonecoding.nike.designsystem.R.drawable.ic_left_24), contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: 정렬 아이콘 클릭 처리 */ }) {
                Icon(painter = painterResource(com.project.clonecoding.nike.designsystem.R.drawable.ic_horizontal_seek_bar_24), contentDescription = "Sort")
            }
            IconButton(onClick = { /* TODO: 검색 아이콘 클릭 처리 */ }) {
                Icon(painter = painterResource(com.project.clonecoding.nike.designsystem.R.drawable.ic_list_search_24), contentDescription = "Search")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        )
    )
}

/**
 * 컬렉션 탭 UI 컴포저블
 * - 카테고리별로 선택 가능한 탭을 제공합니다.
 */

@Composable
fun CollectionTabs() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("All", "Tops & T-Shirts", "Hoodies & Pullovers", "Shoes", "Accessories")

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.White,
        contentColor = Color.Black,
        edgePadding = 0.dp
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                text = { Text(title, fontSize = 14.sp, fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal) }
            )
        }
    }
}

/**
 * 컬렉션 아이템 그리드 UI 컴포저블
 * - 각 상품 아이템을 카드 형식으로 그리드에 표시합니다.
 */

@Composable
fun CollectionItemsGrid() {
    val items = listOf(
        CollectionItemData(R.drawable.img_sample_item1, "Cosmic Unity 3 N7", "Basketball Shoes", "1 Colours", "US\$170"),
        CollectionItemData(R.drawable.img_sample_item2, "Nike Benassi N7", "Slides", "1 Colours", "US\$35"),
        CollectionItemData(R.drawable.img_sample_item3, "Nike Sportswear Club Fleece N7", "Pullover Hoodie", "1 Colours", "US\$70"),
        CollectionItemData(R.drawable.img_sample_item4, "Nike Sportswear Club Fleece N7", "Joggers", "1 Colours", "US\$65"),
                CollectionItemData(R.drawable.img_sample_item1, "Cosmic Unity 3 N7", "Basketball Shoes", "1 Colours", "US\$170"),
    CollectionItemData(R.drawable.img_sample_item2, "Nike Benassi N7", "Slides", "1 Colours", "US\$35"),
    CollectionItemData(R.drawable.img_sample_item3, "Nike Sportswear Club Fleece N7", "Pullover Hoodie", "1 Colours", "US\$70"),
    CollectionItemData(R.drawable.img_sample_item4, "Nike Sportswear Club Fleece N7", "Joggers", "1 Colours", "US\$65")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 6.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.background(Color.White)
    ) {
        items(items) { item ->
            CollectionItemCard(item)
        }
    }
}

/**
 * 컬렉션 아이템 카드 UI 컴포저블
 * - 각 상품 정보를 카드 형태로 표시하며, 즐겨찾기 버튼 포함
 * @param data 표시할 상품 정보
 */

@Composable
fun CollectionItemCard(data: CollectionItemData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White), // 카드 배경색 회색 설정
        colors = CardDefaults.cardColors(containerColor = Color.White), // Material3 Card의 배경색 설정
        shape = RectangleShape // 카드 모양을 직사각형으로 설정
    ) {
        Column() { // 여백 설정
            Box {
                Image(
                    painter = painterResource(id = data.imageRes),
                    contentDescription = data.title,
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
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = data.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = data.subtitle, color = Color.Gray, fontSize = 14.sp)
            Text(text = data.color, color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = data.price, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
        }
    }
}

/**
 * CollectionItemData 데이터 클래스
 * - 각 상품 카드에서 표시할 상품 정보를 저장합니다.
 */

data class CollectionItemData(
    val imageRes: Int,
    val title: String,
    val subtitle: String,
    val color: String,
    val price: String
)

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
    CollectionScreen()
}