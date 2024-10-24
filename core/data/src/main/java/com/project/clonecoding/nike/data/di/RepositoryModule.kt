package com.project.clonecoding.nike.data.di

import com.project.clonecoding.nike.data.local.db.NikeDatabase
import com.project.clonecoding.nike.data.repository.HomeRepositoryImpl
import com.project.clonecoding.nike.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideHomeRepository(nikeDatabase: NikeDatabase): HomeRepository {
        return HomeRepositoryImpl(nikeDatabase = nikeDatabase)
    }
}