package com.project.clonecoding.nike.domain.model

import java.time.LocalDateTime

data class NewsCommentModel(
    val newsId: Int,
    val writer: String,
    val comment: String,
    val ldt: LocalDateTime? = null,
)