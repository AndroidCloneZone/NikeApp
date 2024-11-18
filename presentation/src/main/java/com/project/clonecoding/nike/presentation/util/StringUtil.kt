package com.project.clonecoding.nike.presentation.util

import android.icu.text.DecimalFormat

object StringUtil {
    private val priceFormat = DecimalFormat("#,###")

    fun Int.toPriceFormat(): String = priceFormat.format(this)
}