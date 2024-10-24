package com.project.clonecoding.nike.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import java.time.ZoneId

@Composable
@Preview(showBackground = true)
fun HomeNewsDetailScreen(viewModel: HomeViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    NikeTheme {
        Scaffold{
            Column(modifier = modifier.padding(it)) {
                HomeNewsDetailScreenHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(51.dp)
                )
                HomeNewsDetailScreenBody(viewModel = viewModel, modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun HomeNewsDetailScreenHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
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
}

@Composable
fun HomeNewsDetailScreenBody(viewModel: HomeViewModel, modifier: Modifier = Modifier) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier,
    ) {
        item {
            HomeNewsDetailContent(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(37.dp))
        }

        item {
            HomeNewsDetailAddCommentArea(
                inputComment = state.value.inputComment,
                commentSize = state.value.newsCommentList.size,
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

@OptIn(ExperimentalGlideComposeApi::class)
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
fun HomeNewsDetailAddCommentArea(
    inputComment: String,
    commentSize: Int,
    modifier: Modifier = Modifier,
    onCommentChanged: (String) -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
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