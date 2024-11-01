package com.project.clonecoding.nike.domain.usecase

import com.project.clonecoding.nike.domain.repository.HomeRepository
import javax.inject.Inject

class GetNewsCommentUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(newsId: Int) = homeRepository.fetchNewsCommentEntities(newsId = newsId)
}