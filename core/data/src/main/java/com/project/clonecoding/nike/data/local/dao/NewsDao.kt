package com.project.clonecoding.nike.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.clonecoding.nike.data.local.entity.NewsCommentEntity

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: NewsCommentEntity): Long

    @Query("SELECT * FROM NewsComment WHERE newsId = :newsId ORDER BY datetime DESC")
    suspend fun fetchNewsCommentEntities(newsId: Int): List<NewsCommentEntity>
}