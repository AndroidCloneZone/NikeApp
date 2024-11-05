package com.project.clonecoding.nike.data.repository

import com.project.clonecoding.nike.common.data.DataState
import com.project.clonecoding.nike.data.local.db.NikeDatabase
import com.project.clonecoding.nike.data.local.entity.NewsCommentEntity
import com.project.clonecoding.nike.data.local.mapper.Mapper
import com.project.clonecoding.nike.data.local.mapper.Mapper.toNewsCommentModelList
import com.project.clonecoding.nike.domain.model.NewsCommentModel
import com.project.clonecoding.nike.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val nikeDatabase: NikeDatabase
) : HomeRepository {

    override suspend fun addNewsComment(
        model: NewsCommentModel
    ): Flow<DataState<Boolean>> {
        return flow {
            emit(DataState.Loading(isLoading = true))
            try {
                val res = nikeDatabase.getNewsDao().insert(
                    entity = NewsCommentEntity(
                        newsId = model.newsId,
                        writer = model.writer,
                        comment = model.comment,
                        datetime = Mapper.datetimeToTimestamp(model.ldt),
                    )
                )
                if (res != -1L) {
                    emit(DataState.Success(data = true))
                }
            } catch (e: Exception) {
                emit(DataState.Error(message = e.message ?: ""))
            } finally {
                emit(DataState.Loading(isLoading = false))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchNewsCommentEntities(newsId: Int): Flow<DataState<List<NewsCommentModel>>> {
        return flow {
            emit(DataState.Loading(isLoading = true))
            try {
                val resList = nikeDatabase.getNewsDao().fetchNewsCommentEntities(
                    newsId = newsId
                )
                emit(DataState.Success(data = resList.toNewsCommentModelList()))
            } catch (e: Exception) {
                emit(DataState.Error(message = e.message ?: ""))
            } finally {
                emit(DataState.Loading(isLoading = false))
            }
        }.flowOn(Dispatchers.IO)
    }
}