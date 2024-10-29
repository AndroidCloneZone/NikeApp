package com.project.clonecoding.nike.designsystem.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.clonecoding.nike.designsystem.R
import com.project.clonecoding.nike.designsystem.theme.black
import com.project.clonecoding.nike.designsystem.theme.nikeTypography

/**
 * 화면 상단에 그려지는 ControlBar에 대한 컴포넌트입니다.
 * @author 이유호
 * @param title 중앙에 표기되는 타이틀명, default: emptyString
 * @param modifier Modifier
 * @param onBackPressed 뒤로가기 버튼 클릭 시 수행될 작업, default: null
 * @param onSearch 검색 버튼 클릭 시 수행될 작업, default: null
 * @param onFilter 필터 버튼 클릭 시 수행될 작업, default: null
 */
@Composable
fun BaseControlBar(
    title: String = "",
    modifier: Modifier = Modifier,
    onBackPressed: (() -> Unit)? = null,
    onSearch: (() -> Unit)? = null,
    onFilter: (() -> Unit)? = null
) {
    Box(modifier = modifier.fillMaxWidth().height(56.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBackPressed != null) {
                Icon(
                    modifier = Modifier
                        .clickable { onBackPressed() }
                        .padding(16.dp)
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.ic_left_24),
                    contentDescription = "BaseControlBarIconBack"
                )
            }

            Box(modifier = Modifier.weight(1f))

            if (onFilter != null) {
                Icon(
                    modifier = Modifier
                        .clickable { onFilter() }
                        .padding(16.dp)
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.ic_horizontal_seek_bar_24),
                    contentDescription = "BaseControlBarIconFilter"
                )
            }
            if (onSearch != null) {
                Icon(
                    modifier = Modifier
                        .clickable { onSearch() }
                        .padding(16.dp)
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.ic_list_search_24),
                    contentDescription = "BaseControlBarIconSearch"
                )
            }
        }

        if (title.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    // icon(56.dp) * 2 + padding(10.dp) = 122.dp
                    .padding(horizontal = 122.dp)
                    .align(Alignment.Center),
                text = title,
                textAlign = TextAlign.Center,
                style = nikeTypography.text2XlMedium,
                color = black
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun BaseControlBarPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        BaseControlBar(
            title = "hahaha",
            modifier = Modifier.fillMaxWidth(),
            onBackPressed = {},
            onFilter = {},
            onSearch = {}
        )
    }
}