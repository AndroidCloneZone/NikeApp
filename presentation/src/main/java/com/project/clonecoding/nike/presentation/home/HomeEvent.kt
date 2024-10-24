package com.project.clonecoding.nike.presentation.home

sealed class HomeEvent {
    data object FetchNewsComments: HomeEvent()
    data class OnInputCommentChanged(val newComment: String): HomeEvent()
    data class AddNewsComment(val comment: String): HomeEvent()
}