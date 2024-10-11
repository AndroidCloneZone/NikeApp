package com.project.clonecoding.nike.designsystem.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.clonecoding.nike.designsystem.R
import com.project.clonecoding.nike.designsystem.theme.black
import com.project.clonecoding.nike.designsystem.theme.gray500
import com.project.clonecoding.nike.designsystem.theme.white

/**
 * BaseCircleCheckbox 컴포저블 - 원형 모양 체크박스
 * 체크박스를 표시하고 클릭 시 상태를 변경합니다.
 *
 * @param checked 체크박스의 체크 상태
 * @param enabled 체크박스 활성화 여부
 * @param style 체크박스의 스타일 (Default 또는 WithRipple)
 * @param modifier 체크박스에 적용할 Modifier
 * @param onCheckedChange 체크 상태가 변경될 때 실행할 콜백 함수
 */

@Composable
fun BaseCircleCheckbox(
    checked: Boolean = false,
    enabled: Boolean = true,
    style: CircleCheckboxStyle = CircleCheckboxStyle.Default,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit
) {
    val colors = if (checked) style.checkedColors else style.uncheckedColors

    CircleCheckboxContainer(
        backgroundColor = colors.backgroundColor,
        modifier = modifier
            .size(20.dp)
            .clickable(
                enabled = enabled,
                indication = ripple(bounded = true, color = colors.rippleColor),
                interactionSource = remember { MutableInteractionSource() }
            ) { onCheckedChange(!checked) }
    ) {
        CircleCheckboxIndicator(
            colors = colors,
            checked = checked
        )
    }
}

/**
 * CircleCheckboxContainer
 * 체크박스의 배경과 클릭 영역을 담당하는 외부 원형 컨테이너
 *
 * @param backgroundColor 컨테이너의 배경색
 * @param modifier 컨테이너에 적용할 Modifier
 * @param content 내부에 표시할 컴포저블 요소
 */

@Composable
private fun CircleCheckboxContainer(
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

/**
 * CircleCheckboxIndicator
 * 체크박스의 체크 상태를 표시하는 내부 원형 인디케이터
 *
 * @param colors 체크박스의 색상 속성
 * @param checked 체크박스가 체크 상태인지 여부
 */

@Composable
private fun CircleCheckboxIndicator(
    colors: CircleCheckboxAttrColors,
    checked: Boolean
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(colors.backgroundColor, shape = CircleShape)
            .border(width = 1.dp, color = colors.borderColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check_14),
                contentDescription = "Checked",
                tint = colors.iconColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseCircleCheckboxPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        // 리플 효과 및 기본 스타일을 가진 체크박스 미리보기
        Row {
            BaseCircleCheckbox(checked = false, style = CircleCheckboxStyle.Default, onCheckedChange = {})
            Spacer(modifier = Modifier.width(8.dp))
            BaseCircleCheckbox(checked = true, style = CircleCheckboxStyle.Default, onCheckedChange = {})
            Spacer(modifier = Modifier.width(8.dp))
            BaseCircleCheckbox(checked = false, style = CircleCheckboxStyle.WithRipple, onCheckedChange = {})
            Spacer(modifier = Modifier.width(8.dp))
            BaseCircleCheckbox(checked = true, style = CircleCheckboxStyle.WithRipple, onCheckedChange = {})
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 텍스트와 함께 체크박스 미리보기
        var checkedState1 by remember { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            BaseCircleCheckbox(checked = checkedState1, onCheckedChange = { checkedState1 = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign up for emails to get updates", color = black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        var checkedState2 by remember { mutableStateOf(true) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            BaseCircleCheckbox(checked = checkedState2, onCheckedChange = { checkedState2 = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign up for emails to get updates", color = black)
        }
    }
}

/**
 * Checkbox 스타일 정의
 * Default: 일반 스타일 (리플 없음)
 * WithRipple: 체크 상태에 따라 리플 효과가 있는 스타일
 */

sealed class CircleCheckboxStyle(
    val checkedColors: CircleCheckboxAttrColors,
    val uncheckedColors: CircleCheckboxAttrColors
) {
    object Default : CircleCheckboxStyle(
        checkedColors = CircleCheckboxAttrColors(white, black, black, Color.Transparent),
        uncheckedColors = CircleCheckboxAttrColors(Color.Transparent, gray500, white, Color.Transparent)
    )

    object WithRipple : CircleCheckboxStyle(
        checkedColors = CircleCheckboxAttrColors(white, black, black, black),
        uncheckedColors = CircleCheckboxAttrColors(Color.Transparent, gray500, white, black)
    )
}

/**
 * CheckboxAttrColors
 * 체크박스의 색상 속성을 정의하는 데이터 클래스
 *
 * @param iconColor 아이콘 색상
 * @param borderColor 테두리 색상
 * @param backgroundColor 배경 색상
 * @param rippleColor 리플 색상
 */

data class CircleCheckboxAttrColors(
    val iconColor: Color,
    val borderColor: Color,
    val backgroundColor: Color,
    val rippleColor: Color
)
