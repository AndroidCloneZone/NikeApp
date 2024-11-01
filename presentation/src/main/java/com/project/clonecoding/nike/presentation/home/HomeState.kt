package com.project.clonecoding.nike.presentation.home

import com.project.clonecoding.nike.domain.model.NewsCommentModel

data class HomeState (
    val userNickname: String? = null,
    val selectedNewsId: Int? = null,

    val inputComment: String = "",

    val newsCommentList: List<NewsCommentModel> = listOf(),

    val isLoading: Boolean = false
)