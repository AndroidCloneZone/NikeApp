package com.project.clonecoding.nike.designsystem.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.clonecoding.nike.designsystem.R
import com.project.clonecoding.nike.designsystem.theme.nikeTypography


/**
 * Button의 Base UI
 * @param text 버튼에 넣을 텍스트, default: emptyString
 * @param enabled 버튼 활성화 여부, default: true
 * @param style 버튼 스타일 지정, default: ButtonStyle.FillLight
 * @param startIcon 텍스트 앞에 위치할 Icon의 drawableRes, default: null
 * @param endIcon 텍스트 뒤에 위치할 Icon의 drawableRes, default: null
 * @param modifier Button Modifier
 * @param content Button 내부 커스텀, default: null
 * @param onClick 버튼 눌렀을 때, 수행할 작업, Required
 */
@Composable
fun BaseButton(
    text: String = "",
    enabled: Boolean = true,
    style: ButtonStyle = ButtonStyle.FillLight,
    @DrawableRes startIcon: Int? = null,
    @DrawableRes endIcon: Int? = null,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    val colors = if (enabled) style.enabledColors else style.disabledColors
    Button(
        modifier = modifier
            .clip(CircleShape)
            .background(colors.backgroundColor)
            .then(
                if (colors.borderColor != null) {
                    Modifier.border(width = 1.dp, shape = CircleShape, color = colors.borderColor)
                } else Modifier
            ),
        enabled = enabled,
        colors = ButtonColors(
            containerColor = style.enabledColors.backgroundColor,
            contentColor = style.enabledColors.textColor,
            disabledContainerColor = style.disabledColors.backgroundColor,
            disabledContentColor = style.disabledColors.textColor,
        ),
        contentPadding = PaddingValues(horizontal = 33.dp, vertical = 16.dp),
        onClick = onClick
    ) {
        if (content != null) {
            content()
        } else {
            if (startIcon != null) {
                Icon(
                    painter = painterResource(id = startIcon),
                    tint = colors.iconColor,
                    contentDescription = "base_btn_start_icon"
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
            Text(text = text, color = colors.textColor, style = nikeTypography.textXlMedium)
            if (endIcon != null) {
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    painter = painterResource(id = endIcon),
                    tint = colors.iconColor,
                    contentDescription = "base_btn_end_icon"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseButtonPreview(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xffeeeeee))
        .padding(20.dp)) {
        BaseButton(
            text = "Join Us",
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )

        Spacer(modifier = Modifier.height(10.dp))

        BaseButton(
            text = "Join Us",
            startIcon = R.drawable.ic_sample_camera,
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )

        Spacer(modifier = Modifier.height(10.dp))

        BaseButton(
            text = "Join Us",
            endIcon = R.drawable.ic_sample_camera,
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Enabled")

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_sample_camera,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_sample_camera,
                    style = ButtonStyle.FillDark,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_sample_camera,
                    style = ButtonStyle.EmptyLight,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_sample_camera,
                    style = ButtonStyle.EmptyDark,
                    onClick = {}
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Disabled")

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_sample_camera,
                    enabled = false,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_sample_camera,
                    style = ButtonStyle.FillDark,
                    enabled = false,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_sample_camera,
                    style = ButtonStyle.EmptyLight,
                    enabled = false,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_sample_camera,
                    style = ButtonStyle.EmptyDark,
                    enabled = false,
                    onClick = {}
                )
            }
        }
    }
}


/**
 * 버튼 스타일이 정의된 Sealed Class
 * @param enabledColors 버튼이 활성화 되었을 때 지정되는 색상 모음
 * @param disabledColors 버튼이 비활성화 되었을 때 지정되는 색상 모음
 */
// TODO: 지정된 DS color 넣어주기
sealed class ButtonStyle(
    val enabledColors: BtnAttrColors,
    val disabledColors: BtnAttrColors
) {
    data object FillLight : ButtonStyle(
        enabledColors = BtnAttrColors(
            textColor = Color(0xff000000),
            iconColor = Color(0xff000000),
            borderColor = null,
            backgroundColor = Color(0xffffffff)
        ),
        disabledColors = BtnAttrColors(
            textColor = Color(0xff8c8c8c),
            iconColor = Color(0xff8c8c8c),
            borderColor = null,
            backgroundColor = Color(0xff1f1f1f)
        )
    )

    data object FillDark : ButtonStyle(
        enabledColors = BtnAttrColors(
            textColor = Color(0xffffffff),
            iconColor = Color(0xffffffff),
            borderColor = null,
            backgroundColor = Color(0xff000000)
        ),
        disabledColors = BtnAttrColors(
            textColor = Color(0xff767676),
            iconColor = Color(0xff767676),
            borderColor = null,
            backgroundColor = Color(0xfff6f6f6)
        )
    )

    data object EmptyLight : ButtonStyle(
        enabledColors = BtnAttrColors(
            textColor = Color(0xffffffff),
            iconColor = Color(0xffffffff),
            borderColor = Color(0xffffffff),
            backgroundColor = Color.Transparent
        ),
        disabledColors = BtnAttrColors(
            textColor = Color(0xff8c8c8c),
            iconColor = Color(0xff8c8c8c),
            borderColor = Color(0xff8c8c8c),
            backgroundColor = Color.Transparent
        )
    )

    data object EmptyDark : ButtonStyle(
        enabledColors = BtnAttrColors(
            textColor = Color(0xff000000),
            iconColor = Color(0xff000000),
            borderColor = Color(0xffe4e4e4),
            backgroundColor = Color.Transparent
        ),
        disabledColors = BtnAttrColors(
            textColor = Color(0xffbababa),
            iconColor = Color(0xffbababa),
            borderColor = Color(0xffbababa),
            backgroundColor = Color.Transparent
        )
    )
}

/**
 * Button의 각 속성들에 대한 Color정보 클래스
 * @param textColor 텍스트 색상
 * @param iconColor 아이콘 색상
 * @param borderColor 테두리 색상
 * @param backgroundColor 배경 색상
 */
data class BtnAttrColors(
    val textColor: Color,
    val iconColor: Color,
    val borderColor: Color?,
    val backgroundColor: Color,
)
