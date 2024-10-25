package com.project.clonecoding.nike.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.project.clonecoding.nike.designsystem.theme.NikeTheme
import com.project.clonecoding.nike.designsystem.theme.gray600
import com.project.clonecoding.nike.designsystem.theme.nikeTypography
import com.project.clonecoding.nike.designsystem.ui.BaseButton
import com.project.clonecoding.nike.designsystem.ui.ButtonStyle
import com.project.clonecoding.nike.domain.model.NewsCommentModel
import com.project.clonecoding.nike.presentation.R
import com.project.clonecoding.nike.presentation.util.DatetimeUtil
import kotlinx.coroutines.launch
import java.time.ZoneId

@Composable
@Preview(showBackground = true)
fun HomeNewsDetailScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    NikeTheme {
        Scaffold {
            Column(modifier = modifier.padding(it)) {
                val lazyListState = rememberLazyListState()
                val isComment by remember { derivedStateOf {
                    lazyListState.firstVisibleItemIndex > 1
                } }

                HomeNewsDetailScreenHeader(
                    isComment = isComment,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(51.dp)
                )
                HomeNewsDetailScreenBody(
                    viewModel = viewModel,
                    lazyListState = lazyListState,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun HomeNewsDetailScreenHeader(isComment: Boolean, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = com.project.clonecoding.nike.designsystem.R.drawable.ic_left_24),
                    contentDescription = "HomeNewsDetailScreenHeaderArrow"
                )
            }
        }
        if (isComment) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.home_detail_comments).uppercase(),
                style = nikeTypography.text2XlMedium
            )
        }
    }
}

@Composable
fun HomeNewsDetailScreenBody(
    viewModel: HomeViewModel,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        item {
            HomeNewsDetailContent(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(37.dp))
        }

        item {
            HomeNewsDetailAddCommentTitle(
                commentSize = state.value.newsCommentList.size,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            HomeNewsDetailAddCommentArea(
                inputComment = state.value.inputComment,
                modifier = Modifier.fillMaxWidth(),
                onCommentChanged = { newValue ->
                    viewModel.onEvent(HomeEvent.OnInputCommentChanged(newValue))
                }
            )
            Spacer(modifier = Modifier.height(21.dp))
        }

        items(state.value.newsCommentList) { item ->
            HomeNewsDetailCommentItem(
                item = item,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@Composable
fun HomeNewsDetailContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 400.dp),
            painter = painterResource(id = R.drawable.img_news_sample_1),
            contentScale = ContentScale.Crop,
            contentDescription = "HomeNewsDetailScreenBodyImage"
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = "Soyeon’s Dance \n" +
                    "Challenge \uD83D\uDE0E",
            style = nikeTypography.displayMdMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = "Hip hop dancer Soyeon Jang shows us an epic dance challenge in the latest Playlist episode. Soyeon dances three parts of the routine - first fast, then slow. Then she combines all the moves for an awesome dance party with her buddy, Disco Dancer. Kids will get inspired to dance along and make up a dance routine of their own.",
            style = nikeTypography.textLgRegular,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(66.dp))

        Row(
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(34.dp)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = com.project.clonecoding.nike.designsystem.R.drawable.ic_export_24),
                contentDescription = "HomeNewsDetailScreenBodyShare"
            )
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = com.project.clonecoding.nike.designsystem.R.drawable.ic_chat_24),
                contentDescription = "HomeNewsDetailScreenBodyBubble"
            )
        }

        Spacer(modifier = Modifier.height(53.dp))

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 400.dp),
            painter = painterResource(id = R.drawable.img_news_sample_2),
            contentScale = ContentScale.Crop,
            contentDescription = "HomeNewsDetailScreenBodyImage"
        )

        Spacer(modifier = Modifier.height(36.dp))

        BaseButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = stringResource(id = R.string.home_detail_explore),
            style = ButtonStyle.FillDark,
            onClick = {

            }
        )
    }
}

@Composable
fun HomeNewsDetailAddCommentTitle(
    commentSize: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xffe4e4e4))
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "${stringResource(id = R.string.home_detail_comments)}(${commentSize})",
            style = nikeTypography.textXlMedium,
            color = Color.Black
        )
    }
}

@Composable
fun HomeNewsDetailAddCommentArea(
    inputComment: String,
    modifier: Modifier = Modifier,
    onCommentChanged: (String) -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(27.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .border(width = 1.dp, color = Color(0xffe4e4e4), shape = CircleShape)
                .background(Color.Transparent)
                .padding(horizontal = 16.dp),
            value = inputComment,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.home_detail_add_comment),
                    style = nikeTypography.textMdRegular,
                    color = Color(0xff767676)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            onValueChange = onCommentChanged
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeNewsDetailCommentItem(
    item: NewsCommentModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val reviewDatetime = item.ldt?.let {
        DatetimeUtil.getReviewDateTime(
            context = context,
            targetDateTime = it.atZone(ZoneId.of("Asia/Seoul"))
        )
    }
    Row(modifier = modifier.padding(vertical = 20.dp)) {
        GlideImage(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape),
            model = "",
            contentDescription = "HomeNewsDetailCommentItemProfile"
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.5.dp),
                text = item.writer,
                style = nikeTypography.textXlMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.comment, style = nikeTypography.textMdRegular, color = Color.Black)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = reviewDatetime ?: "", style = nikeTypography.textSmRegular, color = gray600)
        }

    }
}