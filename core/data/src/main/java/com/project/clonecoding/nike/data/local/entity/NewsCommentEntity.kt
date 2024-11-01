package com.project.clonecoding.nike.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewsComment")
data class NewsCommentEntity(
    val newsId: Int,
    val writer: String,
    val comment: String,
    val datetime: Long? = null,
){
    @PrimaryKey(autoGenerate = true) var id: Int? = null
}