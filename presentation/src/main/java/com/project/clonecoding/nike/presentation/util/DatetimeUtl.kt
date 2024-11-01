package com.project.clonecoding.nike.presentation.util

import android.content.Context
import com.project.clonecoding.nike.presentation.R
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object DatetimeUtil {
    private val ymdDotFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.KOREAN)

    private const val MINUTES_UNIT_TO_SECONDS = 60
    private const val HOUR_UNIT_TO_SECONDS = MINUTES_UNIT_TO_SECONDS * 60
    private const val DAY_UNIT_TO_MINUTES = HOUR_UNIT_TO_SECONDS * 24
    private const val WEEK_UNIT_TO_DAYS = DAY_UNIT_TO_MINUTES * 7

    fun getReviewDateTime(context: Context, targetDateTime: ZonedDateTime): String {
        val targetMilliSeconds = targetDateTime.toEpochSecond()
        val currMilliSeconds = LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toEpochSecond()

        val diffSeconds = currMilliSeconds - targetMilliSeconds
        return when {
            diffSeconds < 0 -> context.resources.getString(R.string.datetime_review_error)
            diffSeconds < MINUTES_UNIT_TO_SECONDS -> context.resources.getString(R.string.datetime_review_now)
            diffSeconds < HOUR_UNIT_TO_SECONDS -> context.resources.getString(R.string.datetime_review_minutes)
                .replace(
                    "#VALUE#",
                    diffSeconds.toDuration(DurationUnit.SECONDS).inWholeMinutes.toString()
                )

            diffSeconds < DAY_UNIT_TO_MINUTES -> context.resources.getString(R.string.datetime_review_hours).replace(
                "#VALUE#",
                diffSeconds.toDuration(DurationUnit.SECONDS).inWholeHours.toString()
            )
            diffSeconds < WEEK_UNIT_TO_DAYS -> context.resources.getString(R.string.datetime_review_days).replace(
                "#VALUE#",
                diffSeconds.toDuration(DurationUnit.SECONDS).inWholeDays.toString()
            )
            else -> targetDateTime.format(ymdDotFormatter)
        }
    }
}