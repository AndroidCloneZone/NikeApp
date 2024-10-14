package com.project.clonecoding.nike.designsystem.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

//Primary
val white = Color(0xFFFFFFFF)
val black = Color(0xFF000000)

//Gray
val gray100 = Color(0xFFF6F6F6)
val gray200 = Color(0xFFE4E4E4)
val gray300 = Color(0xFFCDCDCD)
val gray400 = Color(0xFFBABABA)
val gray500 = Color(0xFF8C8C8C)
val gray600 = Color(0xFF767676)
val gray700 = Color(0xFF57595B)
val gray800 = Color(0xFF28292A)

//Success
val success100 = Color(0xFFCFF2D8)
val success200 = Color(0xFF9DE5B0)
val success300 = Color(0xFF6ED989)
val success400 = Color(0xFF35C75A)
val success500 = Color(0xFF2AA147)
val success600 = Color(0xFF32862B)
val success700 = Color(0xFF19612B)
val success800 = Color(0xFF11401D)

//Warning
val warning100 = Color(0xFFFFE1C8)
val warning200 = Color(0xFFFFBF8C)
val warning300 = Color(0xFFFF9E4F)
val warning400 = Color(0xFFFF821D)
val warning500 = Color(0xFFFC5100)
val warning600 = Color(0xFFD94601)
val warning700 = Color(0xFFA33501)
val warning800 = Color(0xFF622001)

//Error
val error100 = Color(0xFFF8E2DD)
val error200 = Color(0xFFEDB7AA)
val error300 = Color(0xFFE79A88)
val error400 = Color(0xFFDC6E57)
val error500 = Color(0xFFCA462A)
val error600 = Color(0xFF99351F)
val error700 = Color(0xFF662415)
val error800 = Color(0xFF44180E)
  
val overlayA200 = Brush.linearGradient(
    colors = listOf(Color(0x33000000), Color.Transparent), // 시작과 끝 색상
    start = Offset(0f, 0f),
    end = Offset(0f, 100f) // 그라데이션 길이에 맞춰 조정
)
val overlayA500 = Color(0x80000000)
val overlayA800 = Color(0xCC000000)
