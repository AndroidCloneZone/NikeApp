package com.project.clonecoding.nike.domain.repository

import com.project.clonecoding.nike.common.data.DataState
import com.project.clonecoding.nike.domain.model.NewsCommentModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun addNewsComment(model: NewsCommentModel): Flow<DataState<Boolean>>

    suspend fun fetchNewsCommentEntities(
        newsId: Int
    ): Flow<DataState<List<NewsCommentModel>>>
}