package com.project.clonecoding.nike.designsystem.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.clonecoding.nike.designsystem.theme.gray100
import com.project.clonecoding.nike.designsystem.theme.black
import com.project.clonecoding.nike.designsystem.theme.gray200
import com.project.clonecoding.nike.designsystem.theme.gray400
import com.project.clonecoding.nike.designsystem.theme.white

/**
 * BaseSizeTab 컴포저블
 * 탭을 표시하고 클릭 시 상태를 변경합니다.
 *
 * @param text 탭에 표시할 텍스트
 * @param selected 탭이 선택된 상태인지 여부
 * @param enabled 탭 활성화 여부
 * @param style 탭의 스타일 (선택됨, 선택되지 않음, 비활성화)
 * @param modifier 탭에 적용할 Modifier
 * @param onClick 탭 클릭 시 실행할 콜백 함수
 */

@Composable
fun BaseSizeTab(
    text: String,
    selected: Boolean = false,
    enabled: Boolean = true,
    style: BaseSizeTabStyle = BaseSizeTabStyle.Default,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colors = when {
        !enabled -> style.disabledColors
        selected -> style.selectedColors
        else -> style.unselectedColors
    }

    Box(
        modifier = modifier
            .width(166.dp)
            .height(48.dp)
            .background(
                color = colors.backgroundColor,
                shape = RoundedCornerShape(4.dp)
            )
            .then(
                if (enabled) {
                    Modifier.clickable(
                        enabled = enabled,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // 리플 효과 제거
                    ) {
                        onClick()
                    }
                } else Modifier
            )
            .border(width = 1.dp, color = colors.borderColor, shape = RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = colors.textColor)
    }
}

/**
 * BaseSizeTabStyle
 * 선택, 선택되지 않음, 비활성화 상태의 색상 스타일을 정의하는 클래스
 */

sealed class BaseSizeTabStyle(
    val selectedColors: TabColors,
    val unselectedColors: TabColors,
    val disabledColors: TabColors
) {
    object Default : BaseSizeTabStyle(
        selectedColors = TabColors(white, black, black),
        unselectedColors = TabColors(white, black, gray200),
        disabledColors = TabColors(gray100, gray400, gray200)
    )
}

/**
 * TabColors
 * 탭의 색상 속성을 정의하는 데이터 클래스
 *
 * @param backgroundColor 배경색
 * @param textColor 텍스트 색상
 * @param borderColor 테두리 색상
 */

data class TabColors(
    val backgroundColor: Color,
    val textColor: Color,
    val borderColor: Color,
)

@Preview(showBackground = true)
@Composable
fun BaseSizeTabPreview() {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        // 세 가지 상태의 탭 미리보기
        BaseSizeTab(text = "L(W 10-13 / M 8-12)", selected = true, onClick = {})
        BaseSizeTab(text = "L(W 10-13 / M 8-12)", selected = false, onClick = {})
        BaseSizeTab(text = "L(W 10-13 / M 8-12)", enabled = false, onClick = {})
    }
}
