package com.project.clonecoding.nike.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.clonecoding.nike.common.data.DataState
import com.project.clonecoding.nike.domain.model.NewsCommentModel
import com.project.clonecoding.nike.domain.usecase.AddNewsCommentUseCase
import com.project.clonecoding.nike.domain.usecase.GetNewsCommentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addNewsCommentUseCase: AddNewsCommentUseCase,
    private val getNewsCommentUseCase: GetNewsCommentUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state get() = _state.asStateFlow()

    init {
        _state.value = _state.value.copy(
            userNickname = "Tester",
            selectedNewsId = 1
        )
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.AddNewsComment -> {
                addNewsReview(event.comment)
            }
            is HomeEvent.FetchNewsComments -> {
                getNewsReview()
            }

            is HomeEvent.OnInputCommentChanged -> {
                _state.value = _state.value.copy(
                    inputComment = event.newComment
                )
            }
        }
    }

    private fun addNewsReview(
        review: String
    ) {
        viewModelScope.launch {
            if(_state.value.selectedNewsId != null){
                addNewsCommentUseCase(
                    model = NewsCommentModel(
                        newsId = _state.value.selectedNewsId!!,
                        writer = _state.value.userNickname ?: "",
                        comment = review,
                        ldt = LocalDateTime.now(),
                    )
                ).collect{ result ->
                    when (result) {
                        is DataState.Loading -> {
                            _state.value = _state.value.copy(isLoading = result.isLoading)
                        }

                        is DataState.Success -> {
                            result.data?.let { items ->
                                _state.value = _state.value.copy(isLoading = false)
                            }
                        }

                        is DataState.Error -> {
                            _state.value = _state.value.copy(isLoading = false)
                        }
                    }
                }
            }
        }
    }

    private fun getNewsReview(){
        viewModelScope.launch {
            if(_state.value.selectedNewsId != null){
                getNewsCommentUseCase(
                    newsId = _state.value.selectedNewsId!!
                ).collect{ result ->
                    when (result) {
                        is DataState.Loading -> {
                            _state.value = _state.value.copy(isLoading = result.isLoading)
                        }

                        is DataState.Success -> {
                            result.data?.let { items ->
                                _state.value = _state.value.copy(
                                    newsCommentList = items,
                                    isLoading = false
                                )
                            }
                        }

                        is DataState.Error -> {
                            _state.value = _state.value.copy(isLoading = false)
                        }
                    }
                }
            }
        }
    }
}