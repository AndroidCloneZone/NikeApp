package com.project.clonecoding.nike.designsystem.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.project.clonecoding.nike.designsystem.theme.black
import com.project.clonecoding.nike.designsystem.theme.gray100
import com.project.clonecoding.nike.designsystem.theme.gray200
import com.project.clonecoding.nike.designsystem.theme.gray400
import com.project.clonecoding.nike.designsystem.theme.gray500
import com.project.clonecoding.nike.designsystem.theme.gray600
import com.project.clonecoding.nike.designsystem.theme.gray800
import com.project.clonecoding.nike.designsystem.theme.nikeTypography
import com.project.clonecoding.nike.designsystem.theme.white


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

@Composable
fun BaseIconButton(
    @DrawableRes iconId: Int,
    style: ButtonStyle = ButtonStyle.FillLight,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(style.enabledColors.backgroundColor)
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            tint = style.enabledColors.iconColor,
            painter = painterResource(id = iconId),
            contentDescription = "base_icon_button_icon"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BaseButtonPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gray200)
            .padding(20.dp)
    ) {
        BaseButton(
            text = "Join Us",
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )

        Spacer(modifier = Modifier.height(10.dp))

        BaseButton(
            text = "Join Us",
            startIcon = R.drawable.ic_filled_camera_24,
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )

        Spacer(modifier = Modifier.height(10.dp))

        BaseButton(
            text = "Join Us",
            endIcon = R.drawable.ic_filled_camera_24,
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
                    endIcon = R.drawable.ic_filled_camera_24,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_filled_camera_24,
                    style = ButtonStyle.FillDark,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_filled_camera_24,
                    style = ButtonStyle.EmptyLight,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_filled_camera_24,
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
                    endIcon = R.drawable.ic_filled_camera_24,
                    enabled = false,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_filled_camera_24,
                    style = ButtonStyle.FillDark,
                    enabled = false,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_filled_camera_24,
                    style = ButtonStyle.EmptyLight,
                    enabled = false,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    text = "Join Us",
                    endIcon = R.drawable.ic_filled_camera_24,
                    style = ButtonStyle.EmptyDark,
                    enabled = false,
                    onClick = {}
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            BaseIconButton(
                iconId = R.drawable.ic_line_heart_24,
                modifier = Modifier.size(46.dp)
            )

            BaseIconButton(
                iconId = R.drawable.ic_line_heart_24,
                style = ButtonStyle.FillDark,
                modifier = Modifier.size(46.dp)
            )
        }
    }
}


/**
 * 버튼 스타일이 정의된 Sealed Class
 * @param enabledColors 버튼이 활성화 되었을 때 지정되는 색상 모음
 * @param disabledColors 버튼이 비활성화 되었을 때 지정되는 색상 모음
 */
sealed class ButtonStyle(
    val enabledColors: BtnAttrColors,
    val disabledColors: BtnAttrColors
) {
    data object FillLight : ButtonStyle(
        enabledColors = BtnAttrColors(
            textColor = black,
            iconColor = black,
            borderColor = null,
            backgroundColor = white
        ),
        disabledColors = BtnAttrColors(
            textColor = gray500,
            iconColor = gray500,
            borderColor = null,
            backgroundColor = gray800
        )
    )

    data object FillDark : ButtonStyle(
        enabledColors = BtnAttrColors(
            textColor = white,
            iconColor = white,
            borderColor = null,
            backgroundColor = black
        ),
        disabledColors = BtnAttrColors(
            textColor = gray600,
            iconColor = gray600,
            borderColor = null,
            backgroundColor = gray100
        )
    )

    data object EmptyLight : ButtonStyle(
        enabledColors = BtnAttrColors(
            textColor = white,
            iconColor = white,
            borderColor = white,
            backgroundColor = Color.Transparent
        ),
        disabledColors = BtnAttrColors(
            textColor = gray500,
            iconColor = gray500,
            borderColor = gray500,
            backgroundColor = Color.Transparent
        )
    )

    data object EmptyDark : ButtonStyle(
        enabledColors = BtnAttrColors(
            textColor = white,
            iconColor = white,
            borderColor = gray200,
            backgroundColor = Color.Transparent
        ),
        disabledColors = BtnAttrColors(
            textColor = gray400,
            iconColor = gray400,
            borderColor = gray400,
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
