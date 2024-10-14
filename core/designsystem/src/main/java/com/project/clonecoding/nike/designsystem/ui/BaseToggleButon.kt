package com.project.clonecoding.nike.designsystem.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.clonecoding.nike.designsystem.theme.gray100
import com.project.clonecoding.nike.designsystem.theme.gray200
import com.project.clonecoding.nike.designsystem.theme.gray300
import com.project.clonecoding.nike.designsystem.theme.white
import com.project.clonecoding.nike.designsystem.theme.success400

/**
 * BaseToggleButton 컴포저블
 * 토글 버튼을 표시하고 클릭 시 상태를 변경합니다.
 *
 * @param toggled 토글 버튼의 활성화 상태
 * @param enabled 토글 버튼 활성화 여부
 * @param style 토글 버튼의 스타일 (Default 또는 WithRipple)
 * @param modifier 토글 버튼에 적용할 Modifier
 * @param onToggleChange 토글 상태가 변경될 때 실행할 콜백 함수
 */

@Composable
fun BaseToggleButton(
    toggled: Boolean = false,
    enabled: Boolean = true,
    style: ToggleButtonStyle = ToggleButtonStyle.Default,
    modifier: Modifier = Modifier,
    onToggleChange: (Boolean) -> Unit
) {
    val colors = if (toggled) style.toggledColors else style.untoggledColors

    Box(
        modifier = modifier
            .width(52.dp)
            .height(30.dp)
            .background(
                color = colors.backgroundColor,
                shape = RoundedCornerShape(30.dp)
            )
            .clickable(
                enabled = enabled,
                indication = ripple(bounded = true, color = colors.rippleColor),
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onToggleChange(!toggled)
            },
        contentAlignment = if (toggled) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(26.dp)
                .offset(x = if (toggled) (-2).dp else 2.dp)
                .background(color = colors.toggleColor, shape = CircleShape)
                .shadow(elevation = 0.dp, shape = CircleShape)
            //TODO: 그림자 제대로 넣기
        )
    }
}

/**
 * ToggleButton 스타일 정의
 * Default: 일반 스타일 (리플 없음)
 * WithRipple: 선택 상태에 따라 리플 효과가 있는 스타일
 */

sealed class ToggleButtonStyle(
    val toggledColors: ToggleButtonColors,
    val untoggledColors: ToggleButtonColors
) {
    // 일반 스타일 (리플 없음)
    object Default : ToggleButtonStyle(
        toggledColors = ToggleButtonColors(success400, white, Color.Transparent),
        untoggledColors = ToggleButtonColors(gray200, gray100, Color.Transparent)
    )

    // 리플 효과가 있는 스타일
    object WithRipple : ToggleButtonStyle(
        toggledColors = ToggleButtonColors(success400, white, gray100),
        untoggledColors = ToggleButtonColors(gray200, gray100, gray100)
    )
}

/**
 * ToggleButtonColors
 * 토글 버튼의 색상 속성을 정의하는 데이터 클래스
 *
 * @param backgroundColor 배경색 (활성화된 상태)
 * @param toggleColor 토글 버튼의 색상
 * @param rippleColor 리플 색상
 */

data class ToggleButtonColors(
    val backgroundColor: Color,
    val toggleColor: Color,
    val rippleColor: Color
)

@Preview(showBackground = true)
@Composable
fun BaseToggleButtonPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            var toggleState1 by remember { mutableStateOf(false) }
            BaseToggleButton(toggled = toggleState1, onToggleChange = { toggleState1 = it })

            var toggleState2 by remember { mutableStateOf(true) }
            BaseToggleButton(toggled = toggleState2, onToggleChange = { toggleState2 = it })

            // WithRipple 스타일 미리보기
            var toggleState3 by remember { mutableStateOf(false) }
            BaseToggleButton(
                toggled = toggleState3,
                style = ToggleButtonStyle.WithRipple,
                onToggleChange = { toggleState3 = it }
            )

            var toggleState4 by remember { mutableStateOf(true) }
            BaseToggleButton(
                toggled = toggleState4,
                style = ToggleButtonStyle.WithRipple,
                onToggleChange = { toggleState4 = it }
            )
        }
    }
}
