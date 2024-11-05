package com.project.clonecoding.nike.presentation.home

sealed class HomeEvent {
    data object FetchHomeMainData: HomeEvent()
    data class OnHomeNewsSelected(val newsId: Int): HomeEvent()
    data object FetchNewsComments: HomeEvent()
    data class OnInputCommentChanged(val newComment: String): HomeEvent()
    data object AddNewsComment: HomeEvent()
}