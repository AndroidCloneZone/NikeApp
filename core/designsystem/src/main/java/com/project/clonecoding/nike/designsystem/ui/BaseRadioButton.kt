package com.project.clonecoding.nike.designsystem.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.clonecoding.nike.designsystem.theme.black
import com.project.clonecoding.nike.designsystem.theme.gray100
import com.project.clonecoding.nike.designsystem.theme.gray200
import com.project.clonecoding.nike.designsystem.theme.gray300
import com.project.clonecoding.nike.designsystem.theme.white

/**
 * BaseRadioButton 컴포저블
 * 라디오 버튼을 표시하고 클릭 시 상태를 변경합니다.
 *
 * @param selected 라디오 버튼의 선택 상태
 * @param enabled 라디오 버튼 활성화 여부
 * @param style 라디오 버튼의 스타일 (Default 또는 WithRipple)
 * @param modifier 라디오 버튼에 적용할 Modifier
 * @param onSelectedChange 선택 상태가 변경될 때 실행할 콜백 함수
 */

@Composable
fun BaseRadioButton(
    selected: Boolean = false,
    enabled: Boolean = true,
    style: RadioButtonStyle = RadioButtonStyle.Default,
    modifier: Modifier = Modifier,
    onSelectedChange: (Boolean) -> Unit
) {
    val colors = if (selected) style.selectedColors else style.unselectedColors

    RadioButtonContainer(
        backgroundColor = colors.backgroundColor,
        modifier = modifier
            .size(28.dp)
            .clickable(
                enabled = enabled,
                indication = ripple(bounded = true, color = colors.rippleColor),
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onSelectedChange(!selected)
            }
    ) {
        RadioButtonIndicator(
            colors = colors,
            selected = selected
        )
    }
}

/**
 * RadioButtonContainer
 * 라디오 버튼의 배경과 클릭 영역을 담당하는 외부 원형 컨테이너
 *
 * @param backgroundColor 컨테이너의 배경색
 * @param modifier 컨테이너에 적용할 Modifier
 * @param content 내부에 표시할 컴포저블 요소
 */

@Composable
private fun RadioButtonContainer(
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val radiusPx = with(LocalDensity.current) { 28.dp.toPx() / 2 }

    Box(
        modifier = modifier
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(backgroundColor, Color.Transparent),
                    radius = radiusPx
                ),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

/**
 * RadioButtonIndicator
 * 라디오 버튼의 선택 상태를 표시하는 내부 점 (선택된 경우에만 표시)
 *
 * @param colors 라디오 버튼의 색상 속성
 * @param selected 라디오 버튼이 선택된 상태인지 여부
 */

@Composable
private fun RadioButtonIndicator(
    colors: RadioButtonAttrColors,
    selected: Boolean
) {
    Box(
        modifier = Modifier
            .size(20.dp)
            .border(width = 1.5.dp, color = colors.borderColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        // 선택된 상태일 때 중앙에 점 표시
        if (selected) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(colors.iconColor, shape = CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseRadioButtonPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        // 리플 효과 및 기본 스타일을 가진 라디오 버튼 미리보기
        Row {
            BaseRadioButton(selected = false, style = RadioButtonStyle.Default, onSelectedChange = {})
            Spacer(modifier = Modifier.width(8.dp))
            BaseRadioButton(selected = true, style = RadioButtonStyle.Default, onSelectedChange = {})
            Spacer(modifier = Modifier.width(8.dp))
            BaseRadioButton(selected = false, style = RadioButtonStyle.WithRipple, onSelectedChange = {})
            Spacer(modifier = Modifier.width(8.dp))
            BaseRadioButton(selected = true, style = RadioButtonStyle.WithRipple, onSelectedChange = {})
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 텍스트와 함께 라디오 버튼 미리보기
        var selectedState1 by remember { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            BaseRadioButton(selected = selectedState1, onSelectedChange = { selectedState1 = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign up for emails to get updates", color = black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        var selectedState2 by remember { mutableStateOf(true) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            BaseRadioButton(selected = selectedState2, onSelectedChange = { selectedState2 = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign up for emails to get updates", color = black)
        }
    }
}

/**
 * RadioButton 스타일 정의
 * Default: 일반 스타일 (리플 없음)
 * WithRipple: 선택 상태에 따라 리플 효과가 있는 스타일
 */

sealed class RadioButtonStyle(
    val selectedColors: RadioButtonAttrColors,
    val unselectedColors: RadioButtonAttrColors
) {
    object Default : RadioButtonStyle(
        selectedColors = RadioButtonAttrColors(black, black, Color.Transparent, Color.Transparent),
        unselectedColors = RadioButtonAttrColors(Color.Transparent, gray300, white, Color.Transparent)
    )

    object WithRipple : RadioButtonStyle(
        selectedColors = RadioButtonAttrColors(black, black, Color.Transparent, gray100),
        unselectedColors = RadioButtonAttrColors(Color.Transparent, gray300, white, gray100)
    )
}

/**
 * RadioButtonAttrColors
 * 라디오 버튼의 색상 속성을 정의하는 데이터 클래스
 *
 * @param iconColor 아이콘 색상 (선택된 경우 중앙에 표시될 점의 색상)
 * @param borderColor 테두리 색상
 * @param backgroundColor 배경 색상
 * @param rippleColor 리플 색상
 */

data class RadioButtonAttrColors(
    val iconColor: Color,
    val borderColor: Color,
    val backgroundColor: Color,
    val rippleColor: Color
)