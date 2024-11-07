package com.project.clonecoding.nike.presentation.home

import com.project.clonecoding.nike.domain.model.NewsCommentModel
import com.project.clonecoding.nike.presentation.home.item.MainContentItem

data class HomeState (
    val userNickname: String? = null,

    val mainContentItem: List<MainContentItem> = listOf(),

    val selectedNewsId: Int? = null,

    val inputComment: String = "",
    val newsCommentList: List<NewsCommentModel> = listOf(),

    val isLoading: Boolean = false
)