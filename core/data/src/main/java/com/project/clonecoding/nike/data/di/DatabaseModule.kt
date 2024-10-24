package com.project.clonecoding.nike.data.di

import android.app.Application
import androidx.room.Room
import com.project.clonecoding.nike.data.local.dao.NewsDao
import com.project.clonecoding.nike.data.local.db.NikeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    internal fun provideNikeDatabase(application: Application): NikeDatabase{
        return Room.databaseBuilder(
            application,
            NikeDatabase::class.java,
            NikeDatabase.NIKE_DATABASE
        ).build()
    }

    @Provides
    internal fun provideNewsDao(calendarDatabase: NikeDatabase): NewsDao{
        return calendarDatabase.getNewsDao()
    }
}