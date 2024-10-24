package com.project.clonecoding.nike.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.clonecoding.nike.data.local.dao.NewsDao
import com.project.clonecoding.nike.data.local.entity.NewsCommentEntity

@Database(entities = [NewsCommentEntity::class], version = 1)
abstract class NikeDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao

    companion object{
        const val NIKE_DATABASE = "nike-db"
    }
}
