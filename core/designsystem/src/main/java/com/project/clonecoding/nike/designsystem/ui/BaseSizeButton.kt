package com.project.clonecoding.nike.designsystem.ui

import androidx.compose.foundation.background
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
import com.project.clonecoding.nike.designsystem.theme.black
import com.project.clonecoding.nike.designsystem.theme.gray800
import com.project.clonecoding.nike.designsystem.theme.white

/**
 * BaseSizeButton 컴포저블
 * 버튼을 표시하고 클릭 시 상태를 변경합니다.
 *
 * @param text 버튼에 표시할 텍스트
 * @param selected 버튼이 선택된 상태인지 여부
 * @param enabled 버튼 활성화 여부
 * @param style 버튼의 스타일 (기본, 선택됨, 비활성화)
 * @param modifier 버튼에 적용할 Modifier
 * @param onClick 버튼 클릭 시 실행할 콜백 함수
 */

@Composable
fun BaseSizeButton(
    text: String,
    selected: Boolean = false,
    enabled: Boolean = true,
    style: BaseSizeButtonStyle = BaseSizeButtonStyle.Default,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colors = when {
        selected -> style.selectedColors
        else -> style.unselectedColors
    }

    Box(
        modifier = modifier
            .width(48.dp)
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
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = colors.textColor)
    }
}

/**
 * BaseSizeButtonStyle
 * 기본, 선택, 비활성화 상태의 색상 스타일을 정의하는 클래스
 */

sealed class BaseSizeButtonStyle(
    val selectedColors: ButtonColors,
    val unselectedColors: ButtonColors
) {
    object Default : BaseSizeButtonStyle(
        selectedColors = ButtonColors(gray800, white),
        unselectedColors = ButtonColors(white, black)
    )
}

/**
 * ButtonColors
 * 버튼의 색상 속성을 정의하는 데이터 클래스
 *
 * @param backgroundColor 배경색
 * @param textColor 텍스트 색상
 */

data class ButtonColors(
    val backgroundColor: Color,
    val textColor: Color
)

@Preview(showBackground = true)
@Composable
fun BaseSizeButtonPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            BaseSizeButton(text = "4", selected = true, onClick = {})
            BaseSizeButton(text = "4", selected = false, onClick = {})
        }
    }
}
