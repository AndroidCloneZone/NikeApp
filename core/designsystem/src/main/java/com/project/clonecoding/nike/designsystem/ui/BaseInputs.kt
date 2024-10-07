package com.project.clonecoding.nike.designsystem.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.clonecoding.nike.designsystem.R
import com.project.clonecoding.nike.designsystem.theme.nikeTypography

/**
 * 테두리가 있는 Base Input
 * state와 관련된 모든 것들은 BaseInput을 사용하는 외부에서 관리되도록 해야함.
 * labelContent와 iconClick을 주입받도록 함.
 * @param value 입력되는 값, Required
 * @param hint placeHolder에 들어가는 값, default: emptyString
 * @param iconId icon drawable id, default: null
 * @param hintEffect placeHolder가 좌측 상단으로 올라오는 효과 사용 여부, default: true
 * @param enabled 사용 여부, default = true
 * @param isError 에러 여부, default = false
 * @param modifier Modifier
 * @param labelContent 하단에 달아줄 Custom Label, default: null
 * @param onValueChanged 텍스트가 바뀔 때 수행할 작업, Required
 * @param onIconClick 우측 아이콘 클릭 시 수생할 작업, default: null
 */
@Composable
fun BaseOutlinedInput(
    value: String,
    hint: String = "",
    @DrawableRes iconId: Int? = null,
    hintEffect: Boolean = true,
    enabled: Boolean = true,
    isError: Boolean = false,
    modifier: Modifier = Modifier,
    labelContent: @Composable ((Boolean) -> Unit)? = null,
    onValueChanged: (String) -> Unit,
    onIconClick: (() -> Unit)? = null
) {
    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            textStyle = nikeTypography.text2XlRegular,
            onValueChange = onValueChanged,
            trailingIcon = {
                // iconId가 있을 때만 icon을 표시해줌
                if (iconId != null) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .then(
                                if (onIconClick != null) {
                                    Modifier.clickable {
                                        onIconClick()
                                    }
                                } else {
                                    Modifier
                                }
                            ),
                        painter = painterResource(id = iconId),
                        contentDescription = "base_outline_input_icon"
                    )
                }
            },
            maxLines = 1,
            singleLine = true,
            shape = RoundedCornerShape(6.dp),
            label = if (hintEffect) {
                {
                    Text(
                        text = hint,
                        textAlign = TextAlign.Center,
                        style = nikeTypography.text2XlRegular
                    )
                }
            } else null,
            placeholder = if (!hintEffect) {
                {
                    Text(
                        text = hint,
                        textAlign = TextAlign.Center,
                        style = nikeTypography.text2XlRegular
                    )
                }
            } else null,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Black.copy(alpha = 0.7f),
                errorTextColor = Color(0xffca462a),
                focusedBorderColor = Color(0xff767676),
                unfocusedBorderColor = Color(0xff767676),
                disabledBorderColor = Color(0xff767676).copy(alpha = 0.7f),
                errorBorderColor = Color(0xffca462a),
                focusedTrailingIconColor = Color(0xff767676),
                unfocusedTrailingIconColor = Color(0xff767676),
                disabledTrailingIconColor = Color(0xff767676).copy(alpha = 0.7f),
                errorTrailingIconColor = Color(0xffca462a),
                focusedLabelColor = Color(0xff767676),
                unfocusedLabelColor = Color(0xff767676),
                disabledLabelColor = Color(0xff767676).copy(alpha = 0.7f),
                errorLabelColor = Color(0xffca462a),
                focusedPlaceholderColor = Color(0xff767676),
                unfocusedPlaceholderColor = Color(0xff767676),
                disabledPlaceholderColor = Color(0xff767676).copy(alpha = 0.7f),
                errorPlaceholderColor = Color(0xffca462a),
            ),
            enabled = enabled,
            isError = isError,
        )
        if (labelContent != null) {
            Row(modifier = Modifier.padding(horizontal = 11.dp, vertical = 6.dp)) {
                labelContent(isError)
            }
        }
    }
}

@Composable
fun BaseFormDropdown(
    title: String? = null,
    hint: String = "",
    onItemSelected: (Int) -> Unit
) {

}

/**
 * 일반 텍스트 Base Input
 * @param value 입력되는 값, Required
 * @param title 타이틀, default: emptyString
 * @param hint placeHolder에 들어가는 값, default: emptyString
 * @param modifier Modifier
 * @param onValueChanged 텍스트가 바뀔 때 수행할 작업, Required
 */
@Composable
fun BaseFormTextFiled(
    value: String,
    title: String = "",
    hint: String = "",
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit
) {
    Column {
        if (title.isNotEmpty()) {
            Text(text = title, style = nikeTypography.textSmRegular, color = Color(0xff767676))
            Spacer(modifier = Modifier.height(10.dp))
        }
        OutlinedTextField(
            modifier = modifier,
            value = value,
            textStyle = nikeTypography.textMdRegular,
            onValueChange = onValueChanged,
            placeholder = {
                Text(text = hint, style = nikeTypography.textMdRegular)
            },
            maxLines = 1,
            singleLine = true,
            shape = RoundedCornerShape(2.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Black.copy(alpha = 0.7f),
                errorTextColor = Color(0xffca462a),
                focusedBorderColor = Color(0xffcdcdcd),
                unfocusedBorderColor = Color(0xffcdcdcd),
                disabledBorderColor = Color(0xffcdcdcd).copy(alpha = 0.7f),
                errorBorderColor = Color(0xffca462a),
                focusedTrailingIconColor = Color(0xff767676),
                unfocusedTrailingIconColor = Color(0xff767676),
                disabledTrailingIconColor = Color(0xff767676).copy(alpha = 0.7f),
                errorTrailingIconColor = Color(0xffca462a),
                focusedLabelColor = Color(0xffbababa),
                unfocusedLabelColor = Color(0xffbababa),
                disabledLabelColor = Color(0xffbababa).copy(alpha = 0.7f),
                errorLabelColor = Color(0xffca462a),
                focusedPlaceholderColor = Color(0xffbababa),
                unfocusedPlaceholderColor = Color(0xffbababa),
                disabledPlaceholderColor = Color(0xffbababa).copy(alpha = 0.7f),
                errorPlaceholderColor = Color(0xffca462a),
            ),
        )
    }
}

@Composable
fun BaseCardInput(
    cardValue: String = "",
    hint: String = "Enter Credit Card Number"
) {

}

@Composable
fun BaseTextArea(
    value: String,
    title: String = "",
    hint: String = "",
    maxLength: Int = 150,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit
) {
    val filteredValue = if (value.length <= maxLength) value else value.substring(0 until maxLength)
    Column {
        if (title.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title, style = nikeTypography.textSmRegular, color = Color(0xff767676))

                Text(
                    text = "${filteredValue.length}/${maxLength}",
                    style = nikeTypography.textSmRegular,
                    color = Color(0xff767676)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        OutlinedTextField(
            modifier = modifier,
            value = filteredValue,
            textStyle = nikeTypography.textMdRegular,
            onValueChange = { newValue ->
                if (newValue.length <= maxLength) {
                    onValueChanged(newValue)
                }
            },
            placeholder = {
                Text(text = hint, style = nikeTypography.textMdRegular)
            },
            shape = RoundedCornerShape(2.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Black.copy(alpha = 0.7f),
                errorTextColor = Color(0xffca462a),
                focusedBorderColor = Color(0xffcdcdcd),
                unfocusedBorderColor = Color(0xffcdcdcd),
                disabledBorderColor = Color(0xffcdcdcd).copy(alpha = 0.7f),
                errorBorderColor = Color(0xffca462a),
                focusedTrailingIconColor = Color(0xff767676),
                unfocusedTrailingIconColor = Color(0xff767676),
                disabledTrailingIconColor = Color(0xff767676).copy(alpha = 0.7f),
                errorTrailingIconColor = Color(0xffca462a),
                focusedLabelColor = Color(0xffbababa),
                unfocusedLabelColor = Color(0xffbababa),
                disabledLabelColor = Color(0xffbababa).copy(alpha = 0.7f),
                errorLabelColor = Color(0xffca462a),
                focusedPlaceholderColor = Color(0xffbababa),
                unfocusedPlaceholderColor = Color(0xffbababa),
                disabledPlaceholderColor = Color(0xffbababa).copy(alpha = 0.7f),
                errorPlaceholderColor = Color(0xffca462a),
            ),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BaseInputsPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        BaseOutlinedInput(
            modifier = Modifier.fillMaxWidth(),
            hint = "Email",
            value = "cdcd",
            iconId = R.drawable.ic_sample_eye,
            onValueChanged = {}
        )

        Spacer(modifier = Modifier.height(30.dp))

        BaseFormTextFiled(
            modifier = Modifier.fillMaxWidth(),
            title = "Title",
            value = "",
            hint = "Postal code",
            onValueChanged = {}
        )

        Spacer(modifier = Modifier.height(30.dp))

        BaseTextArea(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            title = "Title",
            value = "",
            hint = "Postal code",
            onValueChanged = {}
        )
    }
}