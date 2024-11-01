package com.project.clonecoding.nike.data.local.mapper

import com.project.clonecoding.nike.data.local.entity.NewsCommentEntity
import com.project.clonecoding.nike.domain.model.NewsCommentModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone

object Mapper {
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let {
            LocalDateTime.ofInstant(
                Instant.ofEpochMilli(value),
                TimeZone.getTimeZone("Asia/Seoul").toZoneId()
            )
        }
    }

    fun datetimeToTimestamp(ldt: LocalDateTime?): Long? {
        return ldt?.let {
            it.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli()
        }
    }

    fun List<NewsCommentEntity>.toNewsCommentModelList(): List<NewsCommentModel> {
        return if (this.isEmpty()) {
            listOf()
        } else {
            this.map {
                NewsCommentModel(
                    newsId = it.newsId,
                    writer = it.writer,
                    comment = it.comment,
                    ldt = fromTimestamp(it.datetime),
                )
            }
        }
    }
}