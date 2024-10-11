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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.clonecoding.nike.designsystem.R
import com.project.clonecoding.nike.designsystem.theme.black
import com.project.clonecoding.nike.designsystem.theme.gray500
import com.project.clonecoding.nike.designsystem.theme.white

/**
 * BaseCheckbox 컴포저블
 * 체크박스를 표시하고 클릭 시 상태를 변경합니다.
 *
 * @param checked 체크박스의 체크 상태
 * @param enabled 체크박스 활성화 여부
 * @param style 체크박스의 스타일 (Default 또는 WithRipple)
 * @param modifier 체크박스에 적용할 Modifier
 * @param onCheckedChange 체크 상태가 변경될 때 실행할 콜백 함수
 */

@Composable
fun BaseCheckbox(
    checked: Boolean = false,
    enabled: Boolean = true,
    style: CheckboxStyle = CheckboxStyle.Default,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit
) {
    // 체크박스 상태에 따른 색상 선택
    val colors = if (checked) style.checkedColors else style.uncheckedColors

    // 체크박스의 외부 컨테이너
    CheckboxContainer(
        backgroundColor = colors.backgroundColor,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .size(20.dp)
            .clickable(
                enabled = enabled,
                indication = ripple(bounded = true, color = colors.rippleColor),
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onCheckedChange(!checked)
            }
    ) {
        // 체크박스의 내부 인디케이터 (체크 아이콘 표시)
        CheckboxIndicator(
            colors = colors,
            shape = RoundedCornerShape(4.dp),
            checked = checked
        )
    }
}

/**
 * CheckboxContainer
 * 체크박스의 배경과 클릭 영역을 담당하는 외부 컨테이너
 *
 * @param backgroundColor 컨테이너의 배경색
 * @param shape 컨테이너의 모양
 * @param modifier 컨테이너에 적용할 Modifier
 * @param content 내부에 표시할 컴포저블 요소
 */

@Composable
private fun CheckboxContainer(
    backgroundColor: Color,
    shape: Shape,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.background(color = backgroundColor, shape = shape),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

/**
 * CheckboxIndicator
 * 체크박스의 체크 상태를 표시하는 내부 요소
 *
 * @param colors 체크박스의 색상 속성
 * @param shape 인디케이터의 모양
 * @param checked 체크박스가 체크 상태인지 여부
 */

@Composable
private fun CheckboxIndicator(
    colors: CheckboxAttrColors,
    shape: Shape,
    checked: Boolean
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(shape)
            .background(colors.backgroundColor)
            .border(width = 1.dp, color = colors.borderColor, shape = shape)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        // 체크된 상태일 때 체크 아이콘 표시
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
fun BaseCheckboxPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        // 리플 효과 및 기본 스타일을 가진 체크박스 미리보기
        Row {
            BaseCheckbox(checked = false, style = CheckboxStyle.Default, onCheckedChange = {})
            Spacer(modifier = Modifier.width(8.dp))
            BaseCheckbox(checked = true, style = CheckboxStyle.Default, onCheckedChange = {})
            Spacer(modifier = Modifier.width(8.dp))
            BaseCheckbox(checked = false, style = CheckboxStyle.WithRipple, onCheckedChange = {})
            Spacer(modifier = Modifier.width(8.dp))
            BaseCheckbox(checked = true, style = CheckboxStyle.WithRipple, onCheckedChange = {})
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 텍스트와 함께 체크박스 미리보기
        var checkedState1 by remember { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            BaseCheckbox(checked = checkedState1, onCheckedChange = { checkedState1 = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign up for emails to get updates", color = black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        var checkedState2 by remember { mutableStateOf(true) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            BaseCheckbox(checked = checkedState2, onCheckedChange = { checkedState2 = it })
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

sealed class CheckboxStyle(
    val checkedColors: CheckboxAttrColors,
    val uncheckedColors: CheckboxAttrColors
) {
    // 일반 스타일 (리플 없음)
    object Default : CheckboxStyle(
        checkedColors = CheckboxAttrColors(white, black, black, Color.Transparent),
        uncheckedColors = CheckboxAttrColors(Color.Transparent, gray500, white, Color.Transparent)
    )

    // 리플 효과가 있는 스타일
    object WithRipple : CheckboxStyle(
        checkedColors = CheckboxAttrColors(white, black, black, black),
        uncheckedColors = CheckboxAttrColors(Color.Transparent, gray500, white, black)
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

data class CheckboxAttrColors(
    val iconColor: Color,
    val borderColor: Color,
    val backgroundColor: Color,
    val rippleColor: Color
)
