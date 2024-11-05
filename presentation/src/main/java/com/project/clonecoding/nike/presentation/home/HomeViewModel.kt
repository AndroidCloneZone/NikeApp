package com.project.clonecoding.nike.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.clonecoding.nike.common.data.DataState
import com.project.clonecoding.nike.domain.model.NewsCommentModel
import com.project.clonecoding.nike.domain.usecase.AddNewsCommentUseCase
import com.project.clonecoding.nike.domain.usecase.GetNewsCommentUseCase
import com.project.clonecoding.nike.presentation.R
import com.project.clonecoding.nike.presentation.home.item.MainContentItem
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
        // TODO 이유호: 게정 관련 feature 완성되면, 해당 부분을 받아온 계정과 연결
        _state.value = _state.value.copy(
            userNickname = "Tester"
        )

        onEvent(HomeEvent.FetchHomeMainData)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.FetchHomeMainData -> {
                getHomeMainContent()
            }

            is HomeEvent.OnHomeNewsSelected -> {
                _state.value = _state.value.copy(
                    selectedNewsId = event.newsId
                )
                getNewsReview()
            }

            is HomeEvent.FetchNewsComments -> {
                getNewsReview()
            }

            is HomeEvent.AddNewsComment -> {
                addNewsReview(review = _state.value.inputComment)
            }

            is HomeEvent.OnInputCommentChanged -> {
                _state.value = _state.value.copy(
                    inputComment = event.newComment
                )
            }
        }
    }

    /**
     * Home 메인화면의 데이터를 가져온다.
     * 임시방편으로 더미데이터를 가져오도록 구성
     */
    private fun getHomeMainContent(){
        _state.value = _state.value.copy(
            mainContentItem = listOf(
                MainContentItem(
                    id = 1,
                    imageRes = R.drawable.img_news_sample_1,
                    subtitleId = R.string.home_banner_subtitle,
                    titleId = R.string.home_banner_title,
                    buttonTextId = R.string.home_purchase_button_text,
                ),
                MainContentItem(
                    id = 2,
                    imageRes = R.drawable.img_news_sample_2,
                    subtitleId = R.string.home_banner_subtitle,
                    titleId = R.string.home_banner_title,
                    buttonTextId = R.string.home_purchase_button_text,
                ),
                // 필요한 만큼 데이터를 추가 가능
            )
        )
    }

    /**
     * 현재 보여지는 뉴스에 댓글을 추가한다.
     * @author 이유호
     * @param review 리뷰 내용
     */
    private fun addNewsReview(review: String) {
        viewModelScope.launch {
            if (_state.value.selectedNewsId != null) {
                addNewsCommentUseCase(
                    model = NewsCommentModel(
                        newsId = _state.value.selectedNewsId!!,
                        writer = _state.value.userNickname ?: "",
                        comment = review,
                        ldt = LocalDateTime.now(),
                    )
                ).collect { result ->
                    when (result) {
                        is DataState.Loading -> {
                            _state.value = _state.value.copy(isLoading = result.isLoading)
                        }

                        is DataState.Success -> {
                            if (result.data == true) {
                                _state.value = _state.value.copy(
                                    inputComment = "", isLoading = false
                                )
                                onEvent(HomeEvent.FetchNewsComments)
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

    /**
     * 선택한 뉴스의 리뷰 목록을 가져온다.
     * @author 이유호
     */
    private fun getNewsReview() {
        viewModelScope.launch {
            if (_state.value.selectedNewsId != null) {
                getNewsCommentUseCase(
                    newsId = _state.value.selectedNewsId!!
                ).collect { result ->
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