package com.project.clonecoding.nike.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.clonecoding.nike.designsystem.R
import com.project.clonecoding.nike.designsystem.theme.gray200
import com.project.clonecoding.nike.designsystem.theme.gray400
import com.project.clonecoding.nike.designsystem.theme.nikeTypography
import com.project.clonecoding.nike.designsystem.ui.BaseBottomNavBar
import com.project.clonecoding.nike.designsystem.ui.BaseButton
import com.project.clonecoding.nike.designsystem.ui.BaseToggleButton
import com.project.clonecoding.nike.designsystem.ui.ButtonStyle
import com.project.clonecoding.nike.designsystem.ui.ToggleButtonStyle

/**
 * Nike 홈 화면 UI 컴포저블
 * - 상단 바, 메인 콘텐츠 리스트, 하단 네비게이션을 포함하는 전체 화면
 * @param navController 바텀 네비게이션을 위한 NavHostController
 */

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    // 샘플 데이터 생성
    val sampleItems = listOf(
        MainContentData(
            imageRes = com.project.clonecoding.nike.presentation.R.drawable.img_news_sample_1,
            subtitle = stringResource(id = com.project.clonecoding.nike.presentation.R.string.home_banner_subtitle),
            title = stringResource(id = com.project.clonecoding.nike.presentation.R.string.home_banner_title),
            buttonText = stringResource(id = com.project.clonecoding.nike.presentation.R.string.home_purchase_button_text),
            onButtonClick = { navController.navigate("homeNewsDetail") } // 버튼 클릭 시 네비게이션 처리
        ),
        MainContentData(
            imageRes = com.project.clonecoding.nike.presentation.R.drawable.img_news_sample_2,
            subtitle = stringResource(id = com.project.clonecoding.nike.presentation.R.string.home_banner_subtitle),
            title = stringResource(id = com.project.clonecoding.nike.presentation.R.string.home_banner_title),
            buttonText = stringResource(id = com.project.clonecoding.nike.presentation.R.string.home_purchase_button_text),
            onButtonClick = { navController.navigate("homeNewsDetail") } // 버튼 클릭 시 네비게이션 처리
        ),
        // 필요한 만큼 데이터를 추가 가능
    )

    // 상단 바와 바텀 네비게이션 설정
    Scaffold(
        topBar = { TopNavigationBar() },
        bottomBar = { BaseBottomNavBar(navController) }
    ) { innerPadding ->
        // 메인 콘텐츠 영역
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MainContentList(mainContentItems = sampleItems)
        }
    }
}

/**
 * 상단 네비게이션 바 UI
 * 추후 Base로 분리 가능하다면 분리 예정
 */

@Composable
fun TopNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp, // 좌측 여백
                top = 48.dp,   // 상단 여백
                end = 16.dp,   // 우측 여백
                bottom = 24.dp  // 하단 여백
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 가운데 타이틀
        Text(
            text = stringResource(com.project.clonecoding.nike.presentation.R.string.home_news_title),
            style = nikeTypography.text2XlBold,
            textAlign = TextAlign.Left,
            modifier = Modifier.weight(1f)
        )

        // 오른쪽 상단 '모두 보기' 텍스트
        Text(
            text = stringResource(com.project.clonecoding.nike.presentation.R.string.home_top_view_all),
            style = nikeTypography.textMdRegular,
            color = gray400
        )
    }
}

/**
 * 메인 콘텐츠 리스트 컴포저블
 * - LazyColumn을 사용하여 다수의 메인 콘텐츠 항목을 스크롤 가능한 리스트로 표시
 * @param mainContentItems 메인 콘텐츠 항목의 리스트
 */

@Composable
fun MainContentList(mainContentItems: List<MainContentData>) {
    LazyColumn {
        items(mainContentItems) { item ->
            MainContent(item)
            Spacer(modifier = Modifier.height(6.dp)) // 각 아이템 사이에 16dp 간격 추가
        }
    }
}


/**
 * 개별 메인 콘텐츠 항목 UI
 * - 이미지와 텍스트로 구성된 배너와 '구매하기' 버튼을 포함
 * @param data MainContentData 객체로, 해당 항목의 데이터 정보 포함
 */

@Composable
fun MainContent(data: MainContentData) {
    Column {
        // 이미지와 텍스트로 구성된 메인 광고 배너
        Box {
            Image(
                painter = painterResource(id = data.imageRes),
                contentDescription = "Main Banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .padding(bottom = 24.dp)
            ) {
                // 배너 서브 타이틀
                Text(
                    text = data.subtitle,
                    style = nikeTypography.textSmBold,
                    color = Color.White
                )

                // 배너 메인 타이틀
                Text(
                    text = data.title,
                    style = nikeTypography.display2XlBold,
                    color = Color.White
                )

                // '구매하기' 버튼
                BaseButton(
                    text = data.buttonText,
                    style = ButtonStyle.FillLight,
                    modifier = Modifier.padding(top = 8.dp),
                    onClick = data.onButtonClick
                )
            }
        }
    }
}

/**
 * 메인 콘텐츠 데이터 클래스
 * - 이미지 리소스 ID, 서브 타이틀, 메인 타이틀, 버튼 텍스트, 버튼 클릭 리스너 포함
 */

data class MainContentData(
    val imageRes: Int,
    val subtitle: String,
    val title: String,
    val buttonText: String,
    val onButtonClick: () -> Unit
)


/**
 * NikeHomeScreen 미리보기
 */

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}