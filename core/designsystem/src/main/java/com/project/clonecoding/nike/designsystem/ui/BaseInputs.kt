package com.project.clonecoding.nike.designsystem.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.clonecoding.nike.common.AccountRegex
import com.project.clonecoding.nike.common.CardRegex
import com.project.clonecoding.nike.common.data.CardType
import com.project.clonecoding.nike.designsystem.R
import com.project.clonecoding.nike.designsystem.theme.nikeTypography

/**
 *
 */

/**
 * 테두리가 있는 Base Input
 * state와 관련된 모든 것들은 BaseInput을 사용하는 외부에서 관리되도록 해야함.
 * labelContent와 iconClick을 주입받도록 함.
 * @author 이유호
 * @param value 입력되는 값, Required
 * @param hint placeHolder에 들어가는 값, default: emptyString
 * @param type 사용할 타입, default: OutlinedInputType.Text
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
    type: OutlinedInputType = OutlinedInputType.Text,
    hintEffect: Boolean = true,
    enabled: Boolean = true,
    isError: Boolean = false,
    modifier: Modifier = Modifier,
    labelContent: @Composable ((Boolean) -> Unit)? = null,
    onValueChanged: (String) -> Unit,
    onIconClick: (() -> Unit)? = null
) {
    val pwVisible by remember {
        mutableStateOf(false)
    }
    val resHint = if (type.hintId != null) {
        stringResource(id = type.hintId)
    } else {
        hint
    }

    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            textStyle = nikeTypography.text2XlRegular,
            onValueChange = onValueChanged,
            trailingIcon = {
                if (type.iconIdList.isNotEmpty()) {
                    val iconId = when (type) {
                        is OutlinedInputType.Pw -> {
                            if (pwVisible) type.iconIdList[1] else type.iconIdList[0]
                        }

                        else -> type.iconIdList[0]
                    }
                    Icon(
                        modifier = Modifier
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
            visualTransformation = if ((type is OutlinedInputType.Pw || type is OutlinedInputType.SignupPw) && !pwVisible) PasswordVisualTransformation()
            else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = type.keyboardType),
            maxLines = 1,
            singleLine = true,
            shape = RoundedCornerShape(6.dp),
            label = if (hintEffect) {
                {
                    Text(
                        text = resHint,
                        textAlign = TextAlign.Center,
                        style = nikeTypography.text2XlRegular
                    )
                }
            } else null,
            placeholder = if (!hintEffect) {
                {
                    Text(
                        text = resHint,
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
                focusedTrailingIconColor = Color.Black,
                unfocusedTrailingIconColor = Color.Black,
                disabledTrailingIconColor = Color.Black.copy(alpha = 0.7f),
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
        } else {
            when (type) {
                OutlinedInputType.SignupPw -> {
                    // 조건1
                    val (c1Icon, c1Color) = if (value.length >= 8) {
                        Pair(Icons.Default.Check, Color(0xff32862b))
                    } else {
                        Pair(Icons.Default.Close, Color(0xff767676))
                    }

                    // 조건2
                    val (c2Icon, c2Color) = if (AccountRegex.checkPasswordRegex(value)) {
                        Pair(Icons.Default.Check, Color(0xff32862b))
                    } else {
                        Pair(Icons.Default.Close, Color(0xff767676))
                    }
                    Column(modifier = Modifier.padding(horizontal = 11.dp, vertical = 6.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(14.dp),
                                imageVector = c1Icon,
                                tint = c1Color,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = stringResource(id = R.string.label_password_1),
                                style = nikeTypography.textSmRegular,
                                color = c1Color
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(14.dp),
                                imageVector = c2Icon,
                                tint = c2Color,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = stringResource(id = R.string.label_password_2),
                                style = nikeTypography.textSmRegular,
                                color = c2Color
                            )
                        }
                    }
                }

                OutlinedInputType.Birth -> {
                    Row(modifier = Modifier.padding(horizontal = 11.dp, vertical = 6.dp)) {
                        Text(
                            text = stringResource(id = R.string.label_date_of_birth),
                            style = nikeTypography.textSmRegular,
                            color = Color(0xff767676)
                        )
                    }
                }

                else -> {

                }
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
 * @author 이유호
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


/**
 * 카드 정보를 입력하는 BaseInput
 * @author 이유호
 * @param cardNumber 카드 번호, Required
 * @param cardYM 카드 유효기간 (MM/YY), Required
 * @param cardCvc 카드 CVC 번호, Required
 * @param hint 아무 입력이 없을 때 보여주는 placeholder text
 * @param modifier Modifier
 * @param onNumberChanged 카드번호가 변경될 때 수행, Required
 * @param onYMChanged 카드 유효기간이 변경될 때 수행, Required
 * @param onCvcChanged 카드 CVC 번호가 변경될 때 수행, Required
 * @param onCameraClick 카메라 버튼을 클릭했을 때 수행, Required
 */
@Composable
fun BaseCardInput(
    cardNumber: String,
    cardYM: String,
    cardCvc: String,
    hint: String = stringResource(id = R.string.hint_card_input),
    modifier: Modifier = Modifier,
    onNumberChanged: (String) -> Unit,
    onYMChanged: (String) -> Unit,
    onCvcChanged: (String) -> Unit,
    onCameraClick: () -> Unit
) {
    val frontNumber = if (cardNumber.length > 4) cardNumber.substring(0 until 4) else cardNumber
    val cardType = CardRegex.getMatchedCardType(cardNumber = frontNumber)
    val cardNumberDigit = CardRegex.getCardTypeNumberDigit(cardType)
    var formattedCardNumber = remember(cardNumber) {
        val fcnText = CardRegex.getFormattedCardNumber(
            cardNumber = cardNumber,
            cardType = cardType
        )
        mutableStateOf(
            TextFieldValue(
                text = fcnText,
                selection = TextRange(fcnText.length)
            )
        )
    }

    var formattedYM = remember(cardYM) {
        val fcyText = if (cardYM.length > 2) {
            cardYM.chunked(2).joinToString("/")
        } else {
            cardYM
        }
        mutableStateOf(
            TextFieldValue(
                text = fcyText,
                selection = TextRange(fcyText.length)
            )
        )
    }

    val cardId = when (cardType) {
        CardType.AmexCard -> R.drawable.ic_payment_amex
        CardType.DinersclubCard -> R.drawable.ic_payment_dinersclub
        CardType.DiscoverCard -> R.drawable.ic_payment_discover
        CardType.JcbCard -> R.drawable.ic_payment_jcb
        CardType.MaestroCard -> R.drawable.ic_payment_maestro
        CardType.MasterCard -> R.drawable.ic_payment_mastercard
        CardType.UnionpayCard -> R.drawable.ic_payment_unionpay
        CardType.VisaCard -> R.drawable.ic_payment_visa
        else -> null
    }

    val numFocusRequester = remember { FocusRequester() }
    val mmyyFocusRequester = remember { FocusRequester() }
    val cvcFocusRequester = remember { FocusRequester() }
    var currNumFocus by remember { mutableStateOf(false) }
    var currMmyyFocus by remember { mutableStateOf(false) }
    var currCvcFocus by remember { mutableStateOf(false) }
    var numFocus by remember { mutableStateOf(false) }
    var mmyyFocus by remember { mutableStateOf(false) }
    var cvcFocus by remember { mutableStateOf(false) }

    LaunchedEffect(numFocus, mmyyFocus, cvcFocus) {
        if (numFocus) {
            numFocusRequester.requestFocus()
            numFocus = false
        }
        if (mmyyFocus) {
            mmyyFocusRequester.requestFocus()
            mmyyFocus = false
        }
        if (cvcFocus) {
            cvcFocusRequester.requestFocus()
            cvcFocus = false
        }
    }

    val boxBorderColor = if(currNumFocus || currMmyyFocus || currCvcFocus){
        Color.Black
    }else{
        Color(0xffcdcdcd)
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(2.dp))
            .border(width = 1.dp, shape = RoundedCornerShape(2.dp), color = boxBorderColor)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth().clickable {
                    numFocus = true
                }
                .padding(horizontal = 10.dp, vertical = 12.dp),
        ) {
            if (cardNumber.isEmpty() && !currNumFocus) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = hint,
                    style = nikeTypography.textMdRegular,
                    color = Color(0xff767676)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.size(43.5.dp, 29.dp)) {
                    if (cardId != null) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = cardId),
                            contentDescription = "BaseCardInputImg"
                        )
                    }
                }
                // 카드 번호
                BasicTextField(
                    modifier = Modifier
                        .focusRequester(numFocusRequester).onFocusChanged {
                            currNumFocus = it.isFocused
                        },
                    value = formattedCardNumber.value,
                    textStyle = nikeTypography.textMdRegular,
                    maxLines = 1,
                    onValueChange = { newValue ->
                        val inputValue = newValue.text.split("-").joinToString("")
                        if (inputValue.length <= cardNumberDigit) {
                            onNumberChanged(inputValue)
                            if (inputValue.length == cardNumberDigit) {
                                mmyyFocus = true
                            }
                        }
                    },
                )

                // 카드 유효기간(MM/YY)
                if (cardNumber.length == cardNumberDigit) {
                    Box(modifier = Modifier.width(50.dp)) {
                        if (cardYM.isEmpty()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "MM/YY",
                                style = nikeTypography.textMdRegular.copy(textAlign = TextAlign.Center),
                                color = Color(0xff767676)
                            )
                        }

                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(mmyyFocusRequester).onFocusChanged {
                                    currMmyyFocus = it.isFocused
                                },
                            value = formattedYM.value,
                            textStyle = nikeTypography.textMdRegular.copy(textAlign = TextAlign.Center),
                            maxLines = 1,
                            onValueChange = { newValue ->
                                val inputValue = newValue.text.split("/").joinToString("")
                                if (inputValue.length <= 4) {
                                    onYMChanged(inputValue)
                                    if (inputValue.length == 4) {
                                        cvcFocus = true
                                    }
                                }
                            }
                        )
                    }

                    Box(modifier = Modifier.width(30.dp)) {
                        if (cardCvc.isEmpty()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "CVC",
                                style = nikeTypography.textMdRegular.copy(textAlign = TextAlign.Center),
                                color = Color(0xff767676)
                            )
                        }

                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(cvcFocusRequester).onFocusChanged {
                                    currCvcFocus = it.isFocused
                                },
                            value = cardCvc,
                            textStyle = nikeTypography.textMdRegular.copy(textAlign = TextAlign.Center),
                            maxLines = 1,
                            onValueChange = { newValue ->
                                if (newValue.length <= 3) {
                                    onCvcChanged(newValue)
                                }
                            }
                        )
                    }
                } else {
                    Box(modifier = Modifier.width(50.dp))
                    Box(modifier = Modifier.width(30.dp))
                }

                Icon(
                    modifier = Modifier.clickable {
                        onCameraClick()
                    },
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_sample_camera_24),
                    contentDescription = "CardCamera"
                )
            }
        }
    }
}

/**
 * 긴 텍스트를 입력하는 Base Input
 * @author 이유호
 * @param value 입력되는 값, Required
 * @param title 타이틀, default: emptyString
 * @param hint placeholder text, default: emptyString
 * @param maxLength 최대 글자수, default: 150
 * @param modifier Modifier
 * @param onValueChanged 텍스트가 바뀔 때 수행될 작업, Required
 */
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
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
    ) {

        BaseFormTextFiled(
            modifier = Modifier.fillMaxWidth(),
            title = "Title",
            value = "",
            hint = "Postal code",
            onValueChanged = {}
        )

        Spacer(modifier = Modifier.height(30.dp))

        var cardNum by remember {
            mutableStateOf("")
        }

        var cardMmyy by remember {
            mutableStateOf("")
        }

        var cardCvc by remember {
            mutableStateOf("")
        }

        BaseCardInput(
            cardNumber = cardNum,
            cardYM = cardMmyy,
            cardCvc = cardCvc,
            modifier = Modifier.fillMaxWidth(),
            onNumberChanged = { newNumber ->
                cardNum = newNumber
            },
            onYMChanged = { newYM ->
                cardMmyy = newYM
            },
            onCvcChanged = { newCvc ->
                cardCvc = newCvc
            },
            onCameraClick = {

            }
        )
    }
}


sealed class OutlinedInputType(
    @StringRes val hintId: Int? = null,
    @DrawableRes val iconIdList: List<Int>,
    val keyboardType: KeyboardType
) {
    data object Text : OutlinedInputType(
        iconIdList = listOf(),
        keyboardType = KeyboardType.Text
    )

    data object Email : OutlinedInputType(
        hintId = R.string.hint_email,
        iconIdList = listOf(),
        keyboardType = KeyboardType.Email
    )

    data object Pw : OutlinedInputType(
        hintId = R.string.hint_password,
        iconIdList = listOf(R.drawable.ic_sample_eye_24, R.drawable.ic_sample_eye_slash_24),
        keyboardType = KeyboardType.Password
    )

    data object SignupPw : OutlinedInputType(
        hintId = R.string.hint_password,
        iconIdList = listOf(R.drawable.ic_sample_eye_24, R.drawable.ic_sample_eye_slash_24),
        keyboardType = KeyboardType.Password
    )

    data object Code : OutlinedInputType(
        hintId = R.string.hint_code,
        iconIdList = listOf(R.drawable.ic_sample_reload_24),
        keyboardType = KeyboardType.Number
    )

    data object Birth : OutlinedInputType(
        hintId = R.string.hint_date_of_birth,
        iconIdList = listOf(R.drawable.ic_sample_calendar_24),
        keyboardType = KeyboardType.Number
    )
}