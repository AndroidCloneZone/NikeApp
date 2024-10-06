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
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 120.sp,
        letterSpacing = (-3).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    display2XlMedium = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 120.sp,
        letterSpacing = (-3).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    display2XlBold = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 120.sp,
        letterSpacing = (-3).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displayXlRegular = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 120.sp,
        letterSpacing = (-0.6).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displayXlMedium = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 120.sp,
        letterSpacing = (-0.6).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displayXlBold = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 120.sp,
        letterSpacing = (-0.6).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displayLgRegular = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 110.sp,
        letterSpacing = (-2.5).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displayLgMedium = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 110.sp,
        letterSpacing = (-2.5).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displayLgBold = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 110.sp,
        letterSpacing = (-2.5).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displayMdRegular = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 120.sp,
        letterSpacing = (-4).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displayMdMedium = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 120.sp,
        letterSpacing = (-4).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displayMdBold = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 120.sp,
        letterSpacing = (-4).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displaySmRegular = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 120.sp,
        letterSpacing = 0.em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displaySmMedium = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 120.sp,
        letterSpacing = 0.em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    displaySmBold = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 120.sp,
        letterSpacing = 0.em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    text2XlRegular = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 100.sp,
        letterSpacing = (-2.5).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    text2XlMedium = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp,
        lineHeight = 100.sp,
        letterSpacing = (-2.5).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    text2XlBold = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 100.sp,
        letterSpacing = (-2.5).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textXlRegular = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 120.sp,
        letterSpacing = 0.em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textXlMedium = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 120.sp,
        letterSpacing = 0.em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textXlBold = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 120.sp,
        letterSpacing = 0.em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textLgRegular = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 150.sp,
        letterSpacing = (-2.4).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textLgMedium = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 150.sp,
        letterSpacing = (-2.4).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textLgBold = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 150.sp,
        letterSpacing = (-2.4).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textMdRegular = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 120.sp,
        letterSpacing = (-1).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textMdMedium = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 120.sp,
        letterSpacing = (-1).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textMdBold = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 120.sp,
        letterSpacing = (-1).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textSmRegular = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 100.sp,
        letterSpacing = (-2.5).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textSmMedium = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 100.sp,
        letterSpacing = (-2.5).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textSmBold = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 100.sp,
        letterSpacing = (-2.5).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textXsRegular = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 100.sp,
        letterSpacing = (-2).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textXsMedium = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 100.sp,
        letterSpacing = (-2).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    textXsBold = TextStyle(
        color = Color.Black,
        fontFamily = HelveticaNowTextFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 100.sp,
        letterSpacing = (-2).em,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
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