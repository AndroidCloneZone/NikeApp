package com.project.clonecoding.nike.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.project.clonecoding.nike.designsystem.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

private val HelveticaNowTextFontFamily = FontFamily(
    Font(R.font.helvetica_nowtext_bold, weight = FontWeight.Bold),
    Font(R.font.helvetica_nowtext_medium, weight = FontWeight.Medium),
    Font(R.font.helvetica_nowtext_regular, weight = FontWeight.Normal),
)

val nikeTypography = NikeTypography(
    display2XlRegular = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 38.4.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    display2XlMedium = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 38.4.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    display2XlBold = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 38.4.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    displayXlRegular = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 33.6.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    displayXlMedium = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 33.6.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    displayXlBold = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 33.6.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    displayLgRegular = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 30.8.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    displayLgMedium = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 30.8.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    displayLgBold = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 30.8.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    displayMdRegular = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 28.8.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    displayMdMedium = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 28.8.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    displayMdBold = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.8.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    displaySmRegular = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    displaySmMedium = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    displaySmBold = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    text2XlRegular = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    text2XlMedium = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    text2XlBold = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textXlRegular = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.2.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textXlMedium = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textXlBold = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 19.2.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textLgRegular = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textLgMedium = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textLgBold = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textMdRegular = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.8.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textMdMedium = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.8.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textMdBold = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 16.8.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textSmRegular = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textSmMedium = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textSmBold = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textXsRegular = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 10.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textXsMedium = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 10.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
    textXsBold = TextStyle(
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 10.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = true)
    ),
)

@Immutable
data class NikeTypography(
    // Display 2xl
    val display2XlRegular: TextStyle,
    val display2XlMedium: TextStyle,
    val display2XlBold: TextStyle,

    // Display xl
    val displayXlRegular: TextStyle,
    val displayXlMedium: TextStyle,
    val displayXlBold: TextStyle,

    // Display lg
    val displayLgRegular: TextStyle,
    val displayLgMedium: TextStyle,
    val displayLgBold: TextStyle,

    // Display md
    val displayMdRegular: TextStyle,
    val displayMdMedium: TextStyle,
    val displayMdBold: TextStyle,

    // Display sm
    val displaySmRegular: TextStyle,
    val displaySmMedium: TextStyle,
    val displaySmBold: TextStyle,

    // Text 2xl
    val text2XlRegular: TextStyle,
    val text2XlMedium: TextStyle,
    val text2XlBold: TextStyle,

    // Text xl
    val textXlRegular: TextStyle,
    val textXlMedium: TextStyle,
    val textXlBold: TextStyle,

    // Text lg
    val textLgRegular: TextStyle,
    val textLgMedium: TextStyle,
    val textLgBold: TextStyle,

    // Text md
    val textMdRegular: TextStyle,
    val textMdMedium: TextStyle,
    val textMdBold: TextStyle,

    // Text sm
    val textSmRegular: TextStyle,
    val textSmMedium: TextStyle,
    val textSmBold: TextStyle,

    // Text xs
    val textXsRegular: TextStyle,
    val textXsMedium: TextStyle,
    val textXsBold: TextStyle,
)