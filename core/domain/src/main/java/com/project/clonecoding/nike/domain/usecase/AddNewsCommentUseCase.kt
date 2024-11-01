package com.project.clonecoding.nike.domain.usecase

import com.project.clonecoding.nike.common.data.DataState
import com.project.clonecoding.nike.domain.model.NewsCommentModel
import com.project.clonecoding.nike.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddNewsCommentUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(
        model: NewsCommentModel
    ): Flow<DataState<Boolean>> = homeRepository.addNewsComment(model = model)
}